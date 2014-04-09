package db.daologic;

import db.dao.MovieDao;
import models.Movie;
import org.skife.jdbi.v2.DBI;

import java.util.List;

/**
 * Created by Alex on 15/03/14.
 */
public class MovieDaoLogic {

    private DBI dbi;

    public MovieDaoLogic(DBI dbi) {
        this.dbi = dbi;
    }

    public long moviesCount() {
        MovieDao dao = dbi.open(MovieDao.class);
        long moviesCount = dao.moviesCount();
        dao.close();
        return moviesCount;
    }

    public Movie getById(long id) {
        MovieDao dao = dbi.open(MovieDao.class);
        Movie movie = dao.getById(id);
        dao.close();
        return movie;
    }

    public Movie getByImdbId(String imdbId) {
        MovieDao dao = dbi.open(MovieDao.class);
        Movie movie = dao.getByImdbId(imdbId);
        dao.close();
        return movie;
    }

    public void updateImdbId(long id, String imdbId) {
        MovieDao dao = dbi.open(MovieDao.class);
        dao.updateImdbId(id, imdbId);
        dao.close();
    }

    public void updateImdbPictureUrl(long id, String imdbPictureURL) {
        MovieDao dao = dbi.open(MovieDao.class);
        dao.updateImdbPictureUrl(id, imdbPictureURL);
        dao.close();
    }

    public long insert(String title, String imdbId, String ImdbPictureURL) {
        MovieDao dao = dbi.open(MovieDao.class);
        long id = dao.insert(title, imdbId, ImdbPictureURL);
        dao.close();
        return id;
    }

    public List <Movie> getTopMovies (int topMoviesCount){
        MovieDao dao = dbi.open(MovieDao.class);
        List<Movie> movies = dao.getTopMovies(topMoviesCount);
        dao.close();
        return movies;
    }
}


