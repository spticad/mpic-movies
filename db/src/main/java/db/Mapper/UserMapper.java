package db.Mapper;

import models.User;
import org.joda.time.DateTime;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by vitaly on 3/12/14.
 */
public class UserMapper implements ResultSetMapper<User> {
    @Override
    public User map(int i, ResultSet rs, StatementContext statementContext) throws SQLException {
        return new User(rs.getLong("id"), rs.getString("g_id"), rs.getString("g_name"),
                rs.getString("g_email"), rs.getString("g_image"), rs.getString("access_token"), new DateTime(rs.getTimestamp("registration_date")));
    }
}
