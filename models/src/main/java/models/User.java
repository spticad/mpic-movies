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

    public User(String token, String googleId) {
        this.token = token;
        this.googleId = googleId;
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (id != user.id) return false;
        if (googleEmail != null ? !googleEmail.equals(user.googleEmail) : user.googleEmail != null) return false;
        if (googleId != null ? !googleId.equals(user.googleId) : user.googleId != null) return false;
        if (googleImage != null ? !googleImage.equals(user.googleImage) : user.googleImage != null) return false;
        if (googleName != null ? !googleName.equals(user.googleName) : user.googleName != null) return false;
        if (registrationDate != null ? !registrationDate.equals(user.registrationDate) : user.registrationDate != null)
            return false;
        if (token != null ? !token.equals(user.token) : user.token != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }
}
