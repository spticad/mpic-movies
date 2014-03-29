package db.daologic;

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

    public void insert(Rating rating) {
        RatingDao dao = dbi.open(RatingDao.class);
        dao.insert(rating.getUserId(), rating.getMovieId(), rating.getRating(), new Timestamp(rating.getTimestamp().getMillis()));
        dao.close();
    }
}
