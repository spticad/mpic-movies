package services;

import algo.PreferencesAlgo;
import algo.SuggestionsAlgo;
import db.DbiManager;
import db.daologic.MovieDaoLogic;
import db.daologic.RatingDaoLogic;
import models.Movie;
import models.Rating;
import models.User;
import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by vitaly on 3/12/14.
 */
public class MovieService {

    private MovieDaoLogic movieDaoLogic = new MovieDaoLogic(DbiManager.getDbi());
    private RatingDaoLogic ratingDaoLogic = new RatingDaoLogic(DbiManager.getDbi());
    private PreferencesAlgo preferencesAlgo = new PreferencesAlgo();
    private SuggestionsAlgo suggestionsAlgo = new SuggestionsAlgo();

    public Movie getForRating() {
        //TODO: get popular movies
        //TODO: cache popular movies, maybe during context initialization
        List<Movie> popular = null;
        //TODO: get rated movies (add method for getting rated movies by user with specified id)
        List<Movie> watched = null;

        return preferencesAlgo.getForRating(popular, watched);
    }

    public Rating addRating(long movieId, short rating) {
        //TODO: get user id by token
        Rating r = new Rating(1, movieId, rating, DateTime.now());
        ratingDaoLogic.insert(r.getUserId(), r.getMovieId(), r.getRating(), r.getTimestamp());
        return r;
    }

    public List<Movie> getRecommended(int limit) {
        //TODO: get user by token
        //TODO: get all users
        //TODO: get all ratings for users
        return suggestionsAlgo.getRecommended(null, new HashMap<User, List<Rating>>(), limit);
    }
}
