package services;

import models.Movie;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vitaly on 3/12/14.
 */
public class MovieService {

    public Movie getForRating() {
        return new Movie(1, "Casper", "url", "imdb id");
    }
}
