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
    public Movie getForRating() {
        log.info("get for rating");
        return service.getForRating();
    }

    @POST
    @Path("{id}/rate")
    @Produces(MediaType.APPLICATION_JSON)
    public Response rate(@PathParam("id") long movieId, @FormParam("rating") short rating) {
        log.info("post rating {} for movie with id {} ", rating, movieId);
        Rating r = service.addRating(movieId, rating);
        return Response.status(Response.Status.CREATED).entity(r).build();
    }

    @GET
    @Path("/recommended")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Movie> getRecommended(@DefaultValue("3") @QueryParam("limit") int limit) {
        log.info("recommended films with limit {}", limit);
        return service.getRecommended(limit);
    }
}
