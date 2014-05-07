package db.dao;

import db.mappers.UserMapper;
import models.User;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.GetGeneratedKeys;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.Mapper;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by vitaly on 3/12/14.
 */
public interface UserDao {
    @SqlQuery("select * from users")
    @Mapper(UserMapper.class)
    List<User> getAll();

    @SqlQuery("select * from users where g_id = :g_id")
    @Mapper(UserMapper.class)
    User getByGoogleId(@Bind("g_id") String googleId);

    @SqlQuery("select * from users where id = :id")
    @Mapper(UserMapper.class)
    User getById(@Bind("id") long id);

    @SqlQuery("select * from users where access_token = :access_token")
    @Mapper(UserMapper.class)
    User getByToken(@Bind("access_token") String token);

    @SqlUpdate("update users set access_token = :access_token where id = :id")
    void updateToken(@Bind("id") long id, @Bind("access_token") String token);

    @SqlUpdate("insert into users(registration_date, g_id, g_name, g_email, g_image, access_token) " +
            "values(:registration_date, :g_id, :g_name, :g_email, :g_image, :access_token)")
    @GetGeneratedKeys
    long insert(@Bind("g_id") String googleId, @Bind("g_name") String googleName,
                @Bind("g_email") String googleEmail, @Bind("g_image") String googleImage,
                @Bind("access_token") String token, @Bind("registration_date") Timestamp registrationDate);

    void close();
}
