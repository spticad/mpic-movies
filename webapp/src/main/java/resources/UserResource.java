package resources;

import beans.UserResponse;
import db.DbiManager;
import db.daologic.UserDaoLogic;
import models.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Cookie;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;

/**
 * @author smecsia
 */
@Path("/user")
public class UserResource {

    public static final String AUTH_TOKEN = "authToken";

    @GET
    @Path("/me")
    @Produces(MediaType.APPLICATION_JSON)
    public UserResponse getMyself(@Context HttpServletRequest request,
                                  @Context HttpHeaders headers) {
        final UserDaoLogic userDaoLogic = new UserDaoLogic(DbiManager.getDbi());
        boolean googleSaysOK = false;
        if (headers.getCookies().get(AUTH_TOKEN) != null && googleSaysOK) {
            final String googleId = ""; // TODO
            final String token = ""; // TODO
            User user = userDaoLogic.getByGoogleId(googleId);
            if (user == null) {
                user = new User(token, googleId);
                // user.setEmail ... )
                // ...
                userDaoLogic.insert(user);
            }
            return new UserResponse(user);
        } else {
            String urlToRedirect = ""; // TODO
            // redirect_url = getUrlTo(request, "user", "auth", "")
            return new UserResponse(urlToRedirect);
        }
    }


    @GET
    @Path("/auth")
    public void authFromGoogle(@Context HttpServletResponse response,
                               @QueryParam("code") String token,
                               @Context HttpHeaders headers) {
        headers.getCookies().put(AUTH_TOKEN, new Cookie(AUTH_TOKEN, token));
//        response.sendRedirect(getUrlTo("/"));
    }
}
