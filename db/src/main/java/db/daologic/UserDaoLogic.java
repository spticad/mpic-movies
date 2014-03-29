package db.daologic;

import db.DbiManager;
import db.dao.UserDao;
import models.User;

/**
 * Created by vitaly on 3/12/14.
 */
public class UserDaoLogic {

    public User getByGoogleId(String googleId) {
        UserDao dao = DbiManager.getDbi().open(UserDao.class);
        User user = dao.getByGoogleId(googleId);
        dao.close();
        return user;
    }

    public User getByToken(String token) {
        UserDao dao = DbiManager.getDbi().open(UserDao.class);
        User user = dao.getByToken(token);
        dao.close();
        return user;
    }

    public void updateToken(String token, long id) {
        UserDao dao = DbiManager.getDbi().open(UserDao.class);
        dao.updateToken(id, token);
        dao.close();
    }

    public long insert(String googleId, String googleName, String googleEmail, String googleImage, String token) {
        UserDao dao = DbiManager.getDbi().open(UserDao.class);
        long id = dao.insert(googleId, googleName, googleEmail, googleImage, token);
        dao.close();
        return id;
    }
}
