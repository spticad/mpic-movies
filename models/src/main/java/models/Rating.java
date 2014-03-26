package models;

import org.joda.time.DateTime;

public class Rating {
    private long userId;
    private long movieId;
    private short rating;
    private DateTime timestamp;

    public Rating() {}

    public Rating(long userId, long movieId, short rating, DateTime timestamp) {
        this.userId = userId;
        this.movieId = movieId;
        this.rating = rating;
        this.timestamp = timestamp;
    }



    public long getUserId() {
        return userId;
    }

    public long getMovieId() {
        return movieId;
    }

    public short getRating() {
        return rating;
    }

    public DateTime getTimestamp() {
        return timestamp;
    }

    @Override
    public String toString() {
        return "Rating{" +
                "userId=" + userId +
                ", movieId=" + movieId +
                ", rating=" + rating +
                ", timestamp=" + timestamp +
                '}';
    }
}
