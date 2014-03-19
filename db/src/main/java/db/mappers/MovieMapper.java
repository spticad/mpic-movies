package db.mappers;

import models.Movie;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Alex on 15/03/14.
 */
public class MovieMapper implements ResultSetMapper<Movie> {
    @Override
    public Movie map(int i, ResultSet rs, StatementContext statementContext) throws SQLException {
        return new Movie(rs.getLong("id"), rs.getString("title"), rs.getString("imdb_picture_url"), rs.getString("imdb_id"));
    }
}
