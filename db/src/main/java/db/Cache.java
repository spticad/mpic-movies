package db;

import models.Movie;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Alina
 * Date: 21.04.14
 * Time: 13:22
 * To change this template use File | Settings | File Templates.
 */
public class Cache {
    private List<Movie> movies;
    private List<UserToUserRating> similarityMatrix;

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public List<UserToUserRating> getSimilarityMatrix() {
        return similarityMatrix;
    }

    public void setSimilarityMatrix(List<UserToUserRating> similarityMatrix) {
        this.similarityMatrix = similarityMatrix;
    }
}
