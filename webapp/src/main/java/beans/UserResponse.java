package beans;

import models.User;

/**
 * @author smecsia
 */
public class UserResponse {

    String urlToRedirect;
    User user;

    public UserResponse(User user) {
        this.user = user;
    }

    public UserResponse(String urlToRedirect) {
        this.urlToRedirect = urlToRedirect;
    }

    public String getUrlToRedirect() {
        return urlToRedirect;
    }

    public void setUrlToRedirect(String urlToRedirect) {
        this.urlToRedirect = urlToRedirect;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
