package db.daologic;

import db.DbiManager;
import db.dao.RatingDao;
import models.Rating;

import java.sql.Timestamp;

/**
 * Created by vitaly on 3/22/14.
 */
public class RatingDaoLogic {

    public void insert(Rating rating) {
        RatingDao dao = DbiManager.getDbi().open(RatingDao.class);
        dao.insert(rating.getUserId(), rating.getMovieId(), rating.getRating(), new Timestamp(rating.getTimestamp().getMillis()));
        dao.close();
    }
}
