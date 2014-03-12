package models;

import org.joda.time.DateTime;

public class User {
    private long id;
    private String googleId;
    private String googleName;
    private String googleEmail;
    private String googleImage;
    private String token;
    private DateTime registrationDate;

    public User(long id, String googleId, String googleName, String googleEmail, String googleImage, String token, DateTime registrationDate) {
        this.id = id;
        this.googleId = googleId;
        this.googleName = googleName;
        this.googleEmail = googleEmail;
        this.googleImage = googleImage;
        this.token = token;
        this.registrationDate = registrationDate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getGoogleId() {
        return googleId;
    }

    public void setGoogleId(String googleId) {
        this.googleId = googleId;
    }

    public String getGoogleName() {
        return googleName;
    }

    public void setGoogleName(String googleName) {
        this.googleName = googleName;
    }

    public String getGoogleEmail() {
        return googleEmail;
    }

    public void setGoogleEmail(String googleEmail) {
        this.googleEmail = googleEmail;
    }

    public String getGoogleImage() {
        return googleImage;
    }

    public void setGoogleImage(String googleImage) {
        this.googleImage = googleImage;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public DateTime getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(DateTime registrationDate) {
        this.registrationDate = registrationDate;
    }
}
