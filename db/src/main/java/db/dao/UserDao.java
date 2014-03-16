package db.dao;

import db.mapper.UserMapper;
import models.User;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.customizers.Mapper;

/**
 * Created by vitaly on 3/12/14.
 */
public interface UserDao {
    @SqlQuery("select * from users where g_id = :g_id")
    @Mapper(UserMapper.class)
    User getByGoogleId(@Bind("g_id") String googleId);

    @SqlQuery("select * from users where access_token = :access_token")
    @Mapper(UserMapper.class)
    User getByToken(@Bind("access_token") String token);

    @SqlQuery("update users set access_token = :access_token where id = :id")
    void updateToken(@Bind("access_token") String token, @Bind("id") long id);

    @SqlQuery("insert into users(registration_date, g_id, g_name, g_email, g_image, access_token) " +
            "values(current_timestamp, :g_id, :g_name, :g_email, :g_image, :access_token) returning id")
    long insert(@Bind("g_id") String googleId, @Bind("g_name") String googleName,
                @Bind("g_email") String googleEmail, @Bind("g_image") String googleImage,
                @Bind("access_token") String token);

    void close();
}
