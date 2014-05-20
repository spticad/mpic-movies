package services;

import algo.PreferencesAlgo;
import algo.RatedObject;
import algo.SimilarityMatrix;
import algo.SuggestionsAlgo;
import db.DbiManager;
import db.PopularMoviesManager;
import db.daologic.MovieDaoLogic;
import db.daologic.RatingDaoLogic;
import db.daologic.UserDaoLogic;
import models.Movie;
import models.Rating;
import models.User;
import org.joda.time.DateTime;

import java.util.*;

/**
 * Created by vitaly on 3/12/14.
 */
public class MovieService {

    private static final long FAKE_USER_ID = 2145;
    private UserDaoLogic userDaoLogic = new UserDaoLogic(DbiManager.getDbi());
    private MovieDaoLogic movieDaoLogic = new MovieDaoLogic(DbiManager.getDbi());
    private RatingDaoLogic ratingDaoLogic = new RatingDaoLogic(DbiManager.getDbi());
    private PreferencesAlgo preferencesAlgo = new PreferencesAlgo();
    private SuggestionsAlgo suggestionsAlgo = new SuggestionsAlgo();
    private SimilarityMatrix similarityMatrix;

    public Movie getForRating() {
//        Movie[] movies = new Movie[] {
//            new Movie(1, "Toy story", "http://ia.media-imdb.com/images/M/MV5BMTMwNDU0NTY2Nl5BMl5BanBnXkFtZTcwOTUxOTM5Mw@@._V1._SX214_CR0,0,214,314_.jpg", "tt0114709"),
//            new Movie(2, "Jumanji", "http://ia.media-imdb.com/images/M/MV5BMzM5NjE1OTMxNV5BMl5BanBnXkFtZTcwNDY2MzEzMQ@@._V1._SY314_CR3,0,214,314_.jpg", "tt0113497"),
//            new Movie(3, "Puccini for Beginners", "http://ia.media-imdb.com/images/M/MV5BMjE5NzQyNDEwNF5BMl5BanBnXkFtZTcwNTYxNDI0MQ@@._V1._SX214_CR0,0,214,314_.jpg", "tt0492481"),
//            new Movie(3, "Grumpy Old Men", "http://ia.media-imdb.com/images/M/MV5BMTI5MTgyMzE0OF5BMl5BanBnXkFtZTYwNzAyNjg5._V1._SX214_CR0,0,214,314_.jpg", "tt0107050")
//        };
//        Random r = new Random();
//        return movies[r.nextInt(4)];

        List<Movie> watched = movieDaoLogic.getWatchedMovies(FAKE_USER_ID);
        return preferencesAlgo.getForRating(PopularMoviesManager.getPopularMovies(), watched);

    }

    public Rating addRating(long movieId, short rating) {
        //TODO: get user id by token
        Rating r = new Rating(FAKE_USER_ID, movieId, rating, DateTime.now());
        ratingDaoLogic.insert(r.getUserId(), r.getMovieId(), r.getRating(), r.getTimestamp());
        return r;
    }

    public List<Object> getRecommended(int limit) {
        List<User> allUsers = userDaoLogic.getAll();
        Map<User, List<Rating>> ratings = getRatings(allUsers);
        SimilarityMatrix similarityMatrix = SimilarityMatrix.getInstance(allUsers, ratings);
                //TODO: get user by token
        User user = new User(2145, "", "", "", "", "", null);
        List<User> similarUsers = getMostSimilarUsers(user, 10, allUsers);
        Map<User, List<Rating>> ratingsByUsers = getRatings(similarUsers);
        List<Movie> notRatedMovies = movieDaoLogic.getNotRatedMovies(user.getId());
        return suggestionsAlgo.getRecommended(similarUsers, user, similarityMatrix, ratingsByUsers, notRatedMovies, limit);
    }

    private Map<User, List<Rating>> getRatings(List<User> users) {
        Map<User, List<Rating>> ratingsByUsers = new HashMap<>();
        for (User user : users) {
            ratingsByUsers.put(user, ratingDaoLogic.getByUserId(user.getId()));
        }
        return ratingsByUsers;
    }
//TODO: make private
    public static List<User> getMostSimilarUsers(User user, int limit, List<User> users) {
        List<User> similarUsers = new ArrayList<>();
        List<RatedObject> ratedUsers = new ArrayList<>();
        Double userToUserSimilarity = Double.NaN;

        for (User userB:users){
            userToUserSimilarity = SimilarityMatrix.getUserToUserSimilarity(user, userB);
            if(userToUserSimilarity != Double.NaN && userB.getId() != user.getId()){
                ratedUsers.add(new RatedObject(userB, userToUserSimilarity));
            }
        }
        Collections.sort(ratedUsers);
        for(int i = 0; i< (ratedUsers.size()>=limit?limit:ratedUsers.size()); i++){
            similarUsers.add((User)ratedUsers.get(i).getObject());
        }
        return similarUsers;
    }
}
