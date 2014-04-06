package services;

import db.DbiManager;
import db.daologic.MovieDaoLogic;
import db.daologic.RatingDaoLogic;
import models.Movie;
import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vitaly on 3/12/14.
 */
public class MovieService {

    private MovieDaoLogic movieDaoLogic = new MovieDaoLogic(DbiManager.getDbi());
    private RatingDaoLogic ratingDaoLogic = new RatingDaoLogic(DbiManager.getDbi());

    public Movie getForRating() {
        return new Movie(5, "Casper", "pic url", "123");
//        return movieDaoLogic.getById(1);
    }

    public void addRating(long movieId, short rating) {
        //TODO: call db logic
        ratingDaoLogic.insert(1, movieId, rating, DateTime.now());
    }

    public List<Movie> recommendedMovies(int limit) {
        List<Movie> movies = new ArrayList<>();
        for (int i = 0; i <= limit; i++) {
            movies.add(movieDaoLogic.getById(i));
        }

        return movies;
    }
}
