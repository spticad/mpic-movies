package resources;

import models.Movie;
import models.Rating;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import services.MovieService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.lang.invoke.MethodHandles;
import java.util.List;

/**
 * Created by vitaly on 3/12/14.
 */
@Path("/movies")
public class MovieResource {

    private static final Logger log = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @Inject
    MovieService service;

    @GET
    @Path("/forRating")
    @Produces(MediaType.APPLICATION_JSON)
    public Movie getForRating(@QueryParam("googleid") String googleId) {
        log.info("get for rating");
        return service.getForRating(googleId);
    }


    @POST
    @Path("{id}/rate/{rating}")
    @Produces(MediaType.APPLICATION_JSON)

    public Response rate(@PathParam("id") long movieId, @PathParam("rating") short rating, @FormParam("googleID") String id) {

        log.info("post rating {} for movie with id {} googleId = {}", rating, movieId, id);
        Rating r = service.addRating(id, movieId, rating);
        //    Rating r = service.addRating(movieId, rating);
        return Response.status(Response.Status.CREATED).entity(r).build();
    }

    @GET
    @Path("/recommended")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Movie> getRecommended(@QueryParam("googleid") String googleId ) {
        int limit=3;
        log.info("recommended films with limit {}", limit);
        return service.getRecommended( googleId, limit);
    }
}
