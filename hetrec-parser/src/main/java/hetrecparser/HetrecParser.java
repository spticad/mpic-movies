package hetrecparser;

import db.DbiManager;
import db.daologic.MovieDaoLogic;
import db.daologic.RatingDaoLogic;
import db.daologic.UserDaoLogic;
import models.Movie;
import models.Rating;
import models.User;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.lang.invoke.MethodHandles;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HetrecParser {
    private static final Logger log = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    private static final String DATASET_NAME = "hetrec2011-movielens-2k-v2";
    private static final int USER_ID_COL_INDEX = 0;
    private static final int MOVIE_ID_COL_INDEX = 1;
    private static final int RATING_COL_INDEX = 2;
    private static final int TIMESTAMP_COL_INDEX = 3;

    private static final ClassLoader classLoader = Thread.currentThread().getContextClassLoader();

    private static final Map<Long, Movie> movies = new HashMap<>();
    private static final Map<Long, User> users = new HashMap<>();
    private static final List<Rating> ratings = new ArrayList<>();

    public static void parseHetrecDataset() throws Exception {
        log.info("start parsing HetRec dataset");

        if(moviesCount() != 0) {
            log.warn("db is not empty! prevent parsing dataset");
            return;
        }

        parseMovies();
        parseUsersAndRatings();

        insertMovies();
        insertUsers();
        insertRatings();

        log.info("finish parsing HetRec dataset");
    }

    private static long moviesCount() {
        MovieDaoLogic movieDaoLogic = new MovieDaoLogic(DbiManager.getDbi());
        return movieDaoLogic.moviesCount();
    }

    private static void insertMovies() {
        log.info("start inserting movies into db");
        MovieDaoLogic movieDaoLogic = new MovieDaoLogic(DbiManager.getDbi());
        int insertCount = 0;
        for(Movie m : movies.values()) {
            long id = movieDaoLogic.insert(m.getTitle(), m.getImdbId(), m.getImdbPictureURL());
            m.setId(id);
            if(++insertCount % 500 == 0) log.info("inserted movies: {}", insertCount);
        }
        log.info("finish inserting movies into db");
    }

    private static void insertUsers() {
        log.info("start inserting users into db");
        UserDaoLogic userDaoLogic = new UserDaoLogic(DbiManager.getDbi());
        int insertCount = 0;
        for(User u : users.values()) {
            long id = userDaoLogic.insert(u.getGoogleId(), u.getGoogleName(), u.getGoogleEmail(), u.getGoogleImage(), u.getToken());
            u.setId(id);
            if(++insertCount % 500 == 0) log.info("inserted users: {}", insertCount);
        }
        log.info("finish inserting users into db");
    }

    private static void insertRatings() {
        log.info("start inserting ratings");
        RatingDaoLogic ratingDaoLogic = new RatingDaoLogic(DbiManager.getDbi());
        int insertCount = 0;
        for(Rating r : ratings) {
            User user = users.get(r.getUserId());
            Movie movie = movies.get(r.getMovieId());

            if(user == null || movie == null) continue;

            ratingDaoLogic.insert(user.getId(), movie.getId(), r.getRating());
            if(++insertCount % 500 == 0) log.info("inserted ratings: {}", insertCount);
        }
        log.info("finish inserting ratings");
    }

    private static void parseUsersAndRatings() throws URISyntaxException, IOException {
        log.info("start parsing users and ratings");

        try(BufferedReader br = new BufferedReader(new InputStreamReader(classLoader.getResourceAsStream(DATASET_NAME + "/user_ratedmovies-timestamps.dat")))) {
            //skip the first line with column titles
            br.readLine();

            for(String line; (line = br.readLine()) != null; ) {
                String[] pieces = line.split("\\s");
                long userId = Long.parseLong(pieces[USER_ID_COL_INDEX]);
                int movieId = Integer.parseInt(pieces[MOVIE_ID_COL_INDEX]);
                short rating = (short) (Double.parseDouble(pieces[RATING_COL_INDEX]) * 2);
                DateTime timestamp = new DateTime(Long.parseLong(pieces[TIMESTAMP_COL_INDEX]));

                users.put(userId, new User(userId, "", "", "", "", "", new DateTime()));
                Rating r = new Rating(userId, movieId, rating, timestamp);
                ratings.add(r);
                log.debug(r.toString());
            }
        }
        log.info("number of users parsed: {}", users.size());
        log.info("number of ratings parsed: {}", ratings.size());

    }

    private static void parseMovies() throws URISyntaxException, IOException {
        log.info("start parsing the movies");

        try(BufferedReader br = new BufferedReader(new InputStreamReader(classLoader.getResourceAsStream(DATASET_NAME + "/movies.dat")))) {
            //skip the first line with column titles
            br.readLine();

            int notParsedMovies = 0;

            for(String line; (line = br.readLine()) != null; ) {
                try {
                    Movie movie = parseMovie(line);
                    log.debug(movie.toString());
                    movies.put(movie.getId(), movie);
                } catch(Exception ex) {
                    log.debug("cannot parse some movie!!!");
                    notParsedMovies++;
                }
            }
            log.info("number of parsed movies: {}", movies.size());
            log.info("number of not parsed movies: {}", notParsedMovies);
        }
    }

    private static Movie parseMovie(String str) {

        Pattern pattern;
        Matcher matcher;

        //read id
        pattern = Pattern.compile("\\d+");
        matcher = pattern.matcher(str);
        matcher.find();
        int idEnd = matcher.end();
        int id = Integer.parseInt(matcher.group());
        str = str.substring(idEnd, str.length());

        //read title
        pattern = Pattern.compile("(?s).*?(?=\\d{7})");
        matcher = pattern.matcher(str);
        matcher.find();
        String title = matcher.group().trim();

        //read imdbID
        pattern = Pattern.compile("(\\d{7})");
        matcher = pattern.matcher(str);
        matcher.find();
        String imdbID = "tt" + matcher.group();

        //read imdbPictureURL
        pattern = Pattern.compile("(http.*?(?=\\s))");
        matcher = pattern.matcher(str);
        matcher.find();
        String imdbPictureURL = matcher.group();

        return new Movie(id, title, imdbPictureURL, imdbID);
    }
}