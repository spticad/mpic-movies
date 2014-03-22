package db.dao;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;

import java.sql.Timestamp;

/**
 * Created by vitaly on 3/22/14.
 */
public interface RatingDao {
    @SqlUpdate("insert into ratings(user_id, movie_id, rating, timestamp) values(:user_id, :movie_id, :rating, :timestamp)")
    void insert(@Bind("user_id") long userId, @Bind("movie_id") long movieId, @Bind("rating") short rating, @Bind("timestamp") Timestamp timestamp);

    void close();
}
