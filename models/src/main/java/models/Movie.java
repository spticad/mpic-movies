package models;

public class Movie {
    private long id;
    private String title;
    private String imdbPictureURL;
    private String imdbId;

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
}
