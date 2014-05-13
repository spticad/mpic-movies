package db.daologic;

import db.dao.UserDao;
import models.User;
import org.joda.time.DateTime;
import org.skife.jdbi.v2.DBI;

import java.sql.Timestamp;

/**
 * Created by vitaly on 3/12/14.
 */
public class UserDaoLogic {

    private DBI dbi;

    public UserDaoLogic(DBI dbi) {
        this.dbi = dbi;
    }

    public User getByGoogleId(String googleId) {
        UserDao dao = dbi.open(UserDao.class);
        User user = dao.getByGoogleId(googleId);
        dao.close();
        return user;
    }

    public User getByToken(String token) {
        UserDao dao = dbi.open(UserDao.class);
        User user = dao.getByToken(token);
        dao.close();
        return user;
    }

    public void updateToken(long id, String token) {
        UserDao dao = dbi.open(UserDao.class);
        dao.updateToken(id, token);
        dao.close();
    }

    public long insert(User user) {
        return insert(user.getGoogleId(), user.getGoogleName(), user.getGoogleEmail(), user.getGoogleImage(),
                user.getToken(), user.getRegistrationDate());
    }

    public long insert(String googleId, String googleName, String googleEmail, String googleImage, String token, DateTime registrationDate) {
        UserDao dao = dbi.open(UserDao.class);
        long id = dao.insert(googleId, googleName, googleEmail, googleImage, token, new Timestamp(registrationDate.getMillis()));
        dao.close();
        return id;
    }
}
