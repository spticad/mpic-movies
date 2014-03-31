package db.daologic;

import db.DbiManager;
import db.dao.RatingDao;
import models.Rating;
import org.skife.jdbi.v2.DBI;

/**
 * Created by vitaly on 3/22/14.
 */
public class RatingDaoLogic {

    private DBI dbi;

    public RatingDaoLogic(DBI dbi) {
        this.dbi = dbi;
    }

    public Rating getByUserId(long userId) {
        RatingDao dao = DbiManager.getDbi().open(RatingDao.class);
        Rating rating = dao.getByUserId(userId);
        dao.close();
        return rating;
    }

    public void insert(long userId, long movieId, short rating) {
        RatingDao dao = dbi.open(RatingDao.class);
        dao.insert(userId, movieId, rating);
        dao.close();
    }
    public void updateRating(long userId, long movieId, short rating){
        RatingDao dao = dbi.open(RatingDao.class);
        dao.updateRating(userId, movieId, rating);
        dao.close();
    }
}
