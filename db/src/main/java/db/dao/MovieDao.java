package db.dao;

import db.mappers.MovieMapper;
import models.Movie;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.GetGeneratedKeys;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.Mapper;

import java.util.List;

/**
 * Created by Alex on 15/03/14.
 */
public interface MovieDao {

    @SqlQuery("select count(*) from movies")
    long moviesCount();

    @SqlQuery("select * from movies where id = :id")
    @Mapper(MovieMapper.class)
    Movie getById(@Bind("id") long id);

    @SqlQuery("select * from movies where imdb_id = :imdb_id")
    @Mapper(MovieMapper.class)
    Movie getByImdbId(@Bind("imdb_id") String imdbId);

    @SqlUpdate("update movies set imdb_id = :imdb_id where id=:id")
    void updateImdbId(@Bind("id") long id, @Bind("imdb_id") String imdbId);

    @SqlUpdate("update movies set imdb_picture_url = :imdb_picture_url where id=:id")
    void updateImdbPictureUrl(@Bind("id") long id, @Bind("imdb_picture_url") String imdbPictureURL);

    @SqlUpdate("insert into movies(title, imdb_id, imdb_picture_url) " +
            "values (:title, :imdb_id, :imdb_picture_url)")
    @GetGeneratedKeys
    long insert(@Bind("title") String title, @Bind("imdb_id") String imdbId,
                @Bind("imdb_picture_url") String imdbPictureURL);
    @SqlQuery("select * from movies, (select movie_id, count(*) as c from ratings " +
            "group by movie_id order by c desc limit :topMoviesCount) r where movies.id = r.movie_id " +
            "order by c desc")
    @Mapper(MovieMapper.class)
    List<Movie> getTopMovies (@Bind("topMoviesCount") int topMoviesCount);


    void close();
}
