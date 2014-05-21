package resources;

import beans.UserResponse;
import com.google.api.client.http.HttpRequest;

import db.DbiManager;
import db.daologic.UserDaoLogic;
import models.User;
import oauth.GoogleAuthHelper;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.joda.time.DateTime;
import org.json.JSONException;
import org.json.JSONObject;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import java.io.IOException;

/**
 * @author smecsia
 */


@Path("/user")
public class UserResource {

    public static final String AUTH_TOKEN = "authToken";
    final GoogleAuthHelper helper = new GoogleAuthHelper();
    @GET
    @Path("/me")
    @Produces(MediaType.APPLICATION_JSON)
    public UserResponse getMyself(@Context HttpServletRequest request,
                                  @Context HttpHeaders headers) throws IOException, JSONException {

        final UserDaoLogic userDaoLogic = new UserDaoLogic(DbiManager.getDbi());

        if (headers.getCookies().get(AUTH_TOKEN) != null) {
            final String authToken = headers.getCookies().get(AUTH_TOKEN).toString();

            String googleAnswer = helper.getUserInfoJson(authToken);
            JSONObject json = new JSONObject(googleAnswer);

            final String googleId =json.get("id").toString();
            final String token = headers.getCookies().get(AUTH_TOKEN).toString();
            final String email = json.get("email").toString();
            final String name = json.get("name").toString();
            final String image = json.get("picture").toString();

            User user = userDaoLogic.getByGoogleId(googleId);
            if (user == null) {
               userDaoLogic.insert(googleId,name,email,image,token,DateTime.now());
            }
            user = userDaoLogic.getByGoogleId(googleId);
            return new UserResponse(user, "");
        } else {
            String urlToRedirect = helper.buildLoginUrl();
            return new UserResponse(null, urlToRedirect);
        }
    }


    @GET
    @Path("/auth")
    public void authFromGoogle(@Context HttpServletResponse response,
                               @QueryParam("code") String token,
                               @Context HttpHeaders headers) throws IOException {
        response.addCookie(new Cookie(AUTH_TOKEN, token));
        response.sendRedirect("/");
    }
}
