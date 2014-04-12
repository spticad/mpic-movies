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
import java.util.Random;

/**
 * Created by vitaly on 3/12/14.
 */
public class MovieService {

    private MovieDaoLogic movieDaoLogic = new MovieDaoLogic(DbiManager.getDbi());
    private RatingDaoLogic ratingDaoLogic = new RatingDaoLogic(DbiManager.getDbi());
    private PreferencesAlgo preferencesAlgo = new PreferencesAlgo();
    private SuggestionsAlgo suggestionsAlgo = new SuggestionsAlgo();

    public Movie getForRating() {
        Movie[] movies = new Movie[] {
            new Movie(1, "Toy story", "http://ia.media-imdb.com/images/M/MV5BMTMwNDU0NTY2Nl5BMl5BanBnXkFtZTcwOTUxOTM5Mw@@._V1._SX214_CR0,0,214,314_.jpg", "tt0114709"),
            new Movie(2, "Jumanji", "http://ia.media-imdb.com/images/M/MV5BMzM5NjE1OTMxNV5BMl5BanBnXkFtZTcwNDY2MzEzMQ@@._V1._SY314_CR3,0,214,314_.jpg", "tt0113497"),
            new Movie(3, "Puccini for Beginners", "http://ia.media-imdb.com/images/M/MV5BMjE5NzQyNDEwNF5BMl5BanBnXkFtZTcwNTYxNDI0MQ@@._V1._SX214_CR0,0,214,314_.jpg", "tt0492481"),
            new Movie(3, "Grumpy Old Men", "http://ia.media-imdb.com/images/M/MV5BMTI5MTgyMzE0OF5BMl5BanBnXkFtZTYwNzAyNjg5._V1._SX214_CR0,0,214,314_.jpg", "tt0107050")
        };
        Random r = new Random();
        return movies[r.nextInt(4)];

        /*
        //TODO: get popular movies
        //TODO: cache popular movies, maybe during context initialization
        List<Movie> popular = null;
        //TODO: get rated movies (add method for getting rated movies by user with specified id)
        List<Movie> watched = null;

        return preferencesAlgo.getForRating(popular, watched);
        */
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
