import config.JerseyConfig;
import models.Movie;
import models.Rating;
import org.glassfish.jersey.test.JerseyTest;
import org.joda.time.DateTime;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import services.MovieService;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by vitaly on 4/6/14.
 */
@RunWith(MockitoJUnitRunner.class)
public class MovieResourceTest extends JerseyTest {

    private MovieService movieService;

    @Override
    protected Application configure() {
        movieService = Mockito.mock(MovieService.class);
        return new JerseyConfig(movieService);
    }

    @Override
    @After
    public void tearDown() throws Exception {
		/*
		 * The super-class tearDown method takes down the server, so it has to be called.
		 */
        super.tearDown();

		/*
		 * Reset the mocking on this object so that the field can be safely re-used between tests.
		 */
        Mockito.reset(movieService);
    }

    @Test
    public void testGetForRating() throws IOException {
        Mockito.when(movieService.getForRating()).thenReturn(new Movie(5, "Casper", "pic url", "tt123"));
        String expectedJSON = JsonTestUtils.readExpectedJsonAnswer("getForRating.json");

        String pathToCall = "movies/forRating";
        Response response = target(pathToCall).request(MediaType.APPLICATION_JSON_TYPE).get();

        Assert.assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        Assert.assertEquals(expectedJSON, response.readEntity(String.class));
    }


    @Test
    public void testRate() throws IOException {
        final int movieId = 5;
        final short rating = 7;
        String googleId = "1";
        Mockito.when(movieService.addRating(googleId, movieId, rating)).thenReturn(new Rating(1, movieId, rating, new DateTime(1396802521757L)));
        String expectedJSON = JsonTestUtils.readExpectedJsonAnswer("rate.json");

        String pathToCall = "movies/" + movieId + "/rate";
        Form form = new Form("rating", rating + "");

        Response response =
                target(pathToCall).request(MediaType.APPLICATION_JSON_TYPE)
                        .post(Entity.entity(form, MediaType.APPLICATION_FORM_URLENCODED_TYPE));

        Assert.assertEquals(Response.Status.CREATED.getStatusCode(), response.getStatus());
        Assert.assertEquals(expectedJSON, response.readEntity(String.class));
    }

    @Test
    public void testGetRecommended() throws IOException {
        Mockito.when(movieService.getRecommended("123", 3)).thenReturn(new ArrayList<Movie>(){{
            add(new Movie(1, "Casper", "url1", "tt1"));
            add(new Movie(2, "Casper 2", "url2", "tt2"));
            add(new Movie(3, "Casper 3", "url3", "tt3"));
        }});
        String expectedJSON = JsonTestUtils.readExpectedJsonAnswer("getRecommended.json");

        String pathToCall = "movies/recommended";

        Response response = target(pathToCall).request(MediaType.APPLICATION_JSON_TYPE).get();

        Assert.assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        Assert.assertEquals(expectedJSON, response.readEntity(String.class));
    }
}
