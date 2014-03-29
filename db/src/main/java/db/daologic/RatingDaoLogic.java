package db.daologic;

import db.DbiManager;
import db.dao.RatingDao;
import models.Rating;
import org.skife.jdbi.v2.DBI;

import java.sql.Timestamp;

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

    public void insert(Rating rating) {
        RatingDao dao = dbi.open(RatingDao.class);
        dao.insert(rating.getUserId(), rating.getMovieId(), rating.getRating(), new Timestamp(rating.getTimestamp().getMillis()));
        dao.close();
    }
}
