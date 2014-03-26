package services;

import db.daologic.MovieDaoLogic;
import db.daologic.RatingDaoLogic;
import models.Movie;
import models.Rating;
import org.joda.time.DateTime;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by vitaly on 3/12/14.
 */
public class MovieService {

    private MovieDaoLogic movieDaoLogic = new MovieDaoLogic();
    private RatingDaoLogic ratingDaoLogic = new RatingDaoLogic();

    public Movie getForRating() {
        return new Movie(5, "Casper", "pic url", "123");
//        return movieDaoLogic.getById(1);
    }

    public void addRating(long id, int rating){
        //TODO: call db logic
       DateTime date = DateTime.now();
        Rating r = new Rating(1,id, (short) rating, date) ;
        ratingDaoLogic.insert(r);
    }

    public List<Movie> recommendedMovies(int limit) {
        List<Movie> movies = new ArrayList<>();
        for (int i=0;i<=limit;i++)
        {
            movies.add(movieDaoLogic.getById(i));
        }

        return movies;
    }
}
