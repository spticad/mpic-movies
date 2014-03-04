package models;

import org.joda.time.DateTime;

public class User {
    private long id;
    private String email;
    private String password;
    private DateTime registrationDate;

    public User(long id, String email, String password, DateTime registrationDate) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.registrationDate = registrationDate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public DateTime getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(DateTime registrationDate) {
        this.registrationDate = registrationDate;
    }
}
