package services;

import algo.PreferencesAlgo;
import db.DbiManager;
import db.daologic.MovieDaoLogic;
import db.daologic.RatingDaoLogic;
import models.Movie;
import models.Rating;
import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vitaly on 3/12/14.
 */
public class MovieService {

    private MovieDaoLogic movieDaoLogic = new MovieDaoLogic(DbiManager.getDbi());
    private RatingDaoLogic ratingDaoLogic = new RatingDaoLogic(DbiManager.getDbi());
    private PreferencesAlgo preferencesAlgo = new PreferencesAlgo();

    public Movie getForRating() {
        //TODO: get popular movies
        //TODO: cache popular movies, maybe during context initialization
        List<Movie> popular = null;
        //TODO: get watched movies (add method for getting rated movies by user with specified id)
        List<Movie> watched = null;

        return preferencesAlgo.getForRating(popular, watched);
    }

    public Rating addRating(long movieId, short rating) {
        //TODO: call db logic
        Rating r = new Rating(1, movieId, rating, DateTime.now());
        ratingDaoLogic.insert(r.getUserId(), r.getMovieId(), r.getRating(), r.getTimestamp());
        return r;
    }

    public List<Movie> getRecommended(int limit) {
        List<Movie> movies = new ArrayList<Movie>(){{
            add(new Movie(1, "Casper", "url1", "tt1"));
            add(new Movie(2, "Casper 2", "url2", "tt2"));
            add(new Movie(3, "Casper 3", "url3", "tt3"));
        }};
//        for (int i = 0; i <= limit; i++) {
//            movies.add(movieDaoLogic.getById(i));
//        }

        return movies;
    }
}
