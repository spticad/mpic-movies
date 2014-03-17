package db.daologic;

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

    public class UserDaoLogic {

        private MovieDao getDao() {

            PGSimpleDataSource ds = new PGSimpleDataSource();
            ds.setDatabaseName("mpic-movies");
            ds.setServerName("localhost");
            ds.setPortNumber(5432);
            ds.setUser("postgres");

           /* PGPoolingDataSource ds = new PGPoolingDataSource();
            ds.setDataSourceName("DataSource");
            ds.setServerName("localhost");
            ds.setDatabaseName("mpic-movies");
            ds.setUser("postgres");
            ds.setPassword("");
            ds.setMaxConnections(10);
            */

            DBI dbi = new DBI(ds);
            MovieDao dao = dbi.open(MovieDao.class);
            return dao;
        }

        public Movie getById(long id) {
         /*
            MovieDao dao = null;
            Movie movie = null;
            try {

                dao = getDao();
                movie = dao.getById(id);
                dao.close();
            } catch (SQLException e) {
            } finally {
                if (dao != null) {
                    try {
                        dao.close();
                    } catch (SQLException e) {
                    }
                }
            }
         */

            MovieDao dao = getDao();
            Movie movie = dao.getById(id);
            dao.close();
            return movie;
        }

        public Movie getByImdbId(String imdbId) {
            MovieDao dao = getDao();
            Movie movie = dao.getByImdbId(imdbId);
            dao.close();
            return movie;
        }

        public void updateImdbId(String imdbId, long id) {
            MovieDao dao = getDao();
            dao.updateImdbId(imdbId, id);
            dao.close();
        }

        public void updateImdbPictureUrl(String imdbPictureURL, long id) {
            MovieDao dao = getDao();
            dao.updateImdbPictureUrl(imdbPictureURL, id);
            dao.close();
        }

        public long insert(String title, String imdbId, String ImdbPictureURL) {
            MovieDao dao = getDao();
            long id = dao.insert(title, imdbId, ImdbPictureURL);
            dao.close();
            return id;
        }
    }
}


