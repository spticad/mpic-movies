package db.dao;

import db.mappers.RatingMapper;
import models.Rating;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.Mapper;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by vitaly on 3/22/14.
 */
public interface RatingDao {
    @SqlQuery("select * from ratings where user_id=:user_id")
    @Mapper(RatingMapper.class)
    List<Rating> getByUserId(@Bind("user_id") long userId);

    @SqlUpdate("insert into ratings(user_id, movie_id, rating, timestamp) values(:user_id, :movie_id, :rating, :timestamp)")
    void insert(@Bind("user_id") long userId, @Bind("movie_id") long movieId, @Bind("rating") short rating, @Bind("timestamp") Timestamp timestamp);

    @SqlUpdate("update ratings set rating=:rating, timestamp=:timestamp where (user_id=:user_id and movie_id=:movie_id)")
    void updateRating(@Bind("user_id") long userId, @Bind("movie_id") long movieId, @Bind("rating") short rating, @Bind("timestamp") Timestamp timestamp);

    void close();
}
