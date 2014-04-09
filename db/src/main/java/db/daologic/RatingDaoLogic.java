package db.daologic;

import db.DbiManager;
import db.dao.RatingDao;
import models.Rating;
import org.joda.time.DateTime;
import org.skife.jdbi.v2.DBI;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by vitaly on 3/22/14.
 */
public class RatingDaoLogic {

    private DBI dbi;

    public RatingDaoLogic(DBI dbi) {
        this.dbi = dbi;
    }

    public List<Rating> getByUserId(long userId) {
        RatingDao dao = dbi.open(RatingDao.class);
        List<Rating> ratings = dao.getByUserId(userId);
        dao.close();
        return ratings;
    }

    public void insert(long userId, long movieId, short rating, DateTime timestamp) {
        RatingDao dao = dbi.open(RatingDao.class);
        dao.insert(userId, movieId, rating, new Timestamp(timestamp.getMillis()));
        dao.close();
    }
    public void updateRating(long userId, long movieId, short rating, DateTime timestamp){
        RatingDao dao = dbi.open(RatingDao.class);
        dao.updateRating(userId, movieId, rating, new Timestamp(timestamp.getMillis()));
        dao.close();
    }
}
