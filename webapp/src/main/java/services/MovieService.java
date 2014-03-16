package services;

import models.Movie;

/**
 * Created by vitaly on 3/12/14.
 */
public class MovieService {

    public Movie getForRating() {
        return new Movie(1, "Casper", "url", "imdb id");
    }
}
