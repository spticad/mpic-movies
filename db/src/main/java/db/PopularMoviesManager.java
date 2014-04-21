package db;

import db.daologic.MovieDaoLogic;
import models.Movie;
import org.joda.time.DateTime;
import org.skife.jdbi.v2.DBI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;
import java.util.List;

/**
 * Created by Alex on 19/04/14.
 */
public class PopularMoviesManager {
    private static final Logger log = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    private static List<Movie> popularMovies = null;
    private static MovieDaoLogic movieDaoLogic = new MovieDaoLogic(DbiManager.getDbi());
    private static JCSClass jcs = new JCSClass();

    private PopularMoviesManager() {
    }

    public static void fillPopularMoviesList(int popularMoviesCount) {
        popularMovies = movieDaoLogic.getTopMovies(popularMoviesCount);
        //TODO: create key
        jcs.addElement(DateTime.now().hourOfDay().toString(), popularMovies);
    }

    public static List<Movie> getPopularMovies() {
        log.debug("popular movies list");
        log.debug("List items count: {},  ",
                popularMovies.size());
        return popularMovies;
    }
}
