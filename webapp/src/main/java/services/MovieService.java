package services;

import models.Movie;

/**
 * Created by vitaly on 3/12/14.
 */
public class MovieService {

    public Movie getForRating() {
        return new Movie(1, "Casper", "url", "imdb id");
    }

    public String addRating(String id, Integer rating ){
        return  id+rating.toString() ;
    }

    public String recommendedMovie() {
        return "[{\"title\":\"Casper\",\"imdb\":\"tt0111161\",\"imdb_pic\":\"%scr%\"},\n" +
                "{\"title\":\"Casper: Revenge\",\"imdb\":\"tt0111162\",\"imdb_pic\":\"%scr1%\"},\n" +
                "{\"title\":\"Casper: Blood Juice\",\"imdb\":\"tt0111163\",\"imdb_pic\":\"%scr3%\"}]";
    }
}
