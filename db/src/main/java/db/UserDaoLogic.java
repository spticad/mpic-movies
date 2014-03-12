package db;

import models.User;
import org.postgresql.ds.PGSimpleDataSource;
import org.skife.jdbi.v2.DBI;
import org.skife.jdbi.v2.Handle;
import org.skife.jdbi.v2.sqlobject.Bind;

import javax.sql.DataSource;

/**
 * Created by vitaly on 3/12/14.
 */
public class UserDaoLogic {

    private UserDao getDao() {
        PGSimpleDataSource ds = new PGSimpleDataSource();
        ds.setDatabaseName("mpic-movies");
        ds.setServerName("localhost");
        ds.setPortNumber(5432);
        ds.setUser("postgres");
        DBI dbi = new DBI(ds);
        UserDao dao = dbi.open(UserDao.class);
        return dao;
    }

    public User getByGoogleId(String googleId) {
        UserDao dao = getDao();
        User user = dao.getByGoogleId(googleId);
        dao.close();
        return user;
    }

    public User getByToken(String token) {
        UserDao dao = getDao();
        User user = dao.getByToken(token);
        dao.close();
        return user;
    }

    public void updateToken(String token, long id) {
        UserDao dao = getDao();
        dao.updateToken(token, id);
        dao.close();
    }

    public long insert(String googleId, String googleName, String googleEmail, String googleImage, String token) {
        UserDao dao = getDao();
        long id = dao.insert(googleId, googleName, googleEmail, googleImage, token);
        dao.close();
        return id;
    }
}
