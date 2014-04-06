package services;

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

    public Movie getForRating() {
        return new Movie(5, "Casper", "pic url", "123");
//        return movieDaoLogic.getById(1);
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
