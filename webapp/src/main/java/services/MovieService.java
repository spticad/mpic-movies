package services;

import db.DbiManager;
import db.daologic.MovieDaoLogic;
import models.Movie;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vitaly on 3/12/14.
 */
public class MovieService {

    private MovieDaoLogic movieDaoLogic =
            new MovieDaoLogic(DbiManager.getDbi());

    public Movie getForRating() {
        return movieDaoLogic.getById(1);
    }

    public void addRating(long id, int rating ){
        //TODO: call db logic
    }

    public List<Movie> recommendedMovies() {
        List<Movie> movies = new ArrayList<>();
        movies.add(new Movie(1, "Casper", "url", "imdb id"));
        movies.add(new Movie(2, "Casper: Revenge", "url", "imdb id"));
        movies.add(new Movie(3, "Blood Juice", "url", "imdb id"));
        return movies;
    }
}
