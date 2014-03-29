package db.daologic;

import db.DbiManager;
import db.dao.MovieDao;
import models.Movie;

/**
 * Created by Alex on 15/03/14.
 */
public class MovieDaoLogic {

        public long moviesCount() {
            MovieDao dao = DbiManager.getDbi().open(MovieDao.class);
            long moviesCount = dao.moviesCount();
            dao.close();
            return moviesCount;
        }

        public Movie getById(long id) {
            MovieDao dao = DbiManager.getDbi().open(MovieDao.class);
            Movie movie = dao.getById(id);
            dao.close();
            return movie;
        }

        public Movie getByImdbId(String imdbId) {
            MovieDao dao = DbiManager.getDbi().open(MovieDao.class);
            Movie movie = dao.getByImdbId(imdbId);
            dao.close();
            return movie;
        }

        public void updateImdbId(String imdbId, long id) {
            MovieDao dao = DbiManager.getDbi().open(MovieDao.class);
            dao.updateImdbId(id, imdbId);
            dao.close();
        }

        public void updateImdbPictureUrl(String imdbPictureURL, long id) {
            MovieDao dao = DbiManager.getDbi().open(MovieDao.class);
            dao.updateImdbPictureUrl(id, imdbPictureURL);
            dao.close();
        }

        public long insert(String title, String imdbId, String ImdbPictureURL) {
            MovieDao dao = DbiManager.getDbi().open(MovieDao.class);
            long id = dao.insert(title, imdbId, ImdbPictureURL);
            dao.close();
            return id;
        }
}


