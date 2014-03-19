package db.daologic;

import db.DbiManager;
import db.dao.MovieDao;
import models.Movie;
import org.postgresql.ds.PGPoolingDataSource;
import org.postgresql.ds.PGSimpleDataSource;
import org.skife.jdbi.v2.DBI;

import java.sql.SQLException;

/**
 * Created by Alex on 15/03/14.
 */
public class MovieDaoLogic {

        private DBI dbi;

        public MovieDaoLogic(DBI dbi) {
            this.dbi = dbi;
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

        public void updateImdbId(String imdbId, long id) {
            MovieDao dao = dbi.open(MovieDao.class);
            dao.updateImdbId(imdbId, id);
            dao.close();
        }

        public void updateImdbPictureUrl(String imdbPictureURL, long id) {
            MovieDao dao = dbi.open(MovieDao.class);
            dao.updateImdbPictureUrl(imdbPictureURL, id);
            dao.close();
        }

        public long insert(String title, String imdbId, String ImdbPictureURL) {
            MovieDao dao = dbi.open(MovieDao.class);
            long id = dao.insert(title, imdbId, ImdbPictureURL);
            dao.close();
            return id;
        }
}


