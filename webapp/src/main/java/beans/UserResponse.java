package beans;

import models.User;

/**
 * @author smecsia
 */
public class UserResponse {

    private String urlToRedirect;
    private User user;

    public UserResponse() {
    }

    public UserResponse(User user, String urlToRedirect) {
        this.user = user;
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
