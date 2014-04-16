package algo;

import models.Movie;

/**
 * Created with IntelliJ IDEA.
 * User: Alina
 * Date: 16.04.14
 * Time: 1:36
 * To change this template use File | Settings | File Templates.
 */
public class RatedMovie implements Comparable<RatedMovie> {
    public Movie getMovie() {
        return movie;
    }

    public double getMovieRating() {
        return movieRating;
    }

    private Movie movie;
    private double movieRating;

    public RatedMovie(Movie movie, double movieRating) {
        this.movie = movie;
        this.movieRating = movieRating;
    }

    public int compareTo(RatedMovie otherMovie) {

       return (int)(otherMovie.getMovieRating() - this.movieRating)*10;

    }
}
