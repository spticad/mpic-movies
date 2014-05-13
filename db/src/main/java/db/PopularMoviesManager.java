package db;

import db.daologic.MovieDaoLogic;
import models.Movie;
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
    private static Cache cache = new Cache();

    private PopularMoviesManager() {
    }

    public static void fillPopularMoviesList(int popularMoviesCount) {
        popularMovies = movieDaoLogic.getTopMovies(popularMoviesCount);
        //TODO: create key
        cache.setMovies(popularMovies);
    }

    public static List<Movie> getPopularMovies() {
        log.debug("popular movies list");
        log.debug("List items count: {},  ",
                popularMovies.size());
        return popularMovies;
    }
}
