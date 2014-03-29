package models;

public class Movie {
    private long id;
    private String title;
    private String imdbPictureURL;
    private String imdbId;

    public Movie() {
    }

    public Movie(long id, String title, String imdbPictureURL, String imdbId) {
        this.id = id;
        this.title = title;
        this.imdbPictureURL = imdbPictureURL;
        this.imdbId = imdbId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImdbPictureURL() {
        return imdbPictureURL;
    }

    public void setImdbPictureURL(String imdbPictureURL) {
        this.imdbPictureURL = imdbPictureURL;
    }

    public String getImdbId() {
        return imdbId;
    }

    public void setImdbId(String imdbId) {
        this.imdbId = imdbId;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", imdbPictureURL='" + imdbPictureURL + '\'' +
                ", imdbId='" + imdbId + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Movie movie = (Movie) o;

        if (id != movie.id) return false;
        if (imdbId != null ? !imdbId.equals(movie.imdbId) : movie.imdbId != null) return false;
        if (imdbPictureURL != null ? !imdbPictureURL.equals(movie.imdbPictureURL) : movie.imdbPictureURL != null)
            return false;
        if (title != null ? !title.equals(movie.title) : movie.title != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }
}
