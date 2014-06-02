package resources;

import beans.UserResponse;
import db.DbiManager;
import db.daologic.UserDaoLogic;
import models.User;
import oauth.GoogleAuthHelper;
import org.joda.time.DateTime;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.lang.invoke.MethodHandles;

/**
 * @author smecsia
 */


@Path("/user")
public class UserResource {
    final UserDaoLogic userDaoLogic = new UserDaoLogic(DbiManager.getDbi());
    private static final Logger log = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    public static final String AUTH_TOKEN = "authToken";
    final GoogleAuthHelper helper = new GoogleAuthHelper();


    @GET
    @Path("/url")
    @Produces(MediaType.APPLICATION_JSON)
    public UserResponse getUrl(@Context HttpServletRequest request,
                               @Context HttpHeaders headers) throws IOException, JSONException {

        String urlToRedirect = helper.buildLoginUrl();
        return new UserResponse(null, urlToRedirect);

    }


    @GET
    @Path("/auth")
    public void authFromGoogle(@Context HttpServletResponse response,
                               @QueryParam("code") String token,
                               @Context HttpHeaders headers) throws IOException {

        log.info("add cookie" + token);
        // get information about user by token
        String googleAnswer = helper.getUserInfoJson(token);
        JSONObject json = new JSONObject(googleAnswer);

        String googleId = json.get("id").toString();
        log.info(googleId);
        Cookie cookie = new Cookie(AUTH_TOKEN, googleId);
        cookie.setPath("/");
        response.addCookie(cookie);

        User user = userDaoLogic.getByGoogleId(googleId);
        // if user come to us for the first time - add to the DB and redirect to preferences page
        if (user == null) {
            final String email = json.get("email").toString();
            final String name = json.get("name").toString();
            final String image = json.get("picture").toString();
            userDaoLogic.insert(googleId, name, email, image, token, DateTime.now());
            response.sendRedirect("/preferences.html");
            // if this is already our user - redirect to the welcome page
        } else {
            response.sendRedirect("/welcome.html");
        }


    }

    @GET
    @Path("/name")
    public String GetName(@QueryParam("googleID") String Id) {
        System.out.println(Id);
        User user = userDaoLogic.getByGoogleId(Id);
        return user.getGoogleName().toString();

    }

    @GET
    @Path("/picture")
    public String GetPicture(@QueryParam("googleID") String Id) {
        System.out.println(Id);
        User user = userDaoLogic.getByGoogleId(Id);
        return user.getGoogleImage().toString();


    }
}
