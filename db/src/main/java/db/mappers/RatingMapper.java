package db.mappers;

import models.Rating;
import org.joda.time.DateTime;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Alex on 29/03/14.
 */
public class RatingMapper implements ResultSetMapper<Rating> {
    @Override
    public Rating map(int i, ResultSet rs, StatementContext statementContext) throws SQLException {
        return new Rating(rs.getLong("user_id"), rs.getLong("movie_id"), rs.getShort("rating"),
                new DateTime(rs.getTimestamp("timestamp")));
    }
}
