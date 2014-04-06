package resources;

import models.Movie;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import services.MovieService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.lang.invoke.MethodHandles;
import java.util.List;

/**
 * Created by vitaly on 3/12/14.
 */
@Path("/movies")
public class MovieResource {

    private static final Logger log = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    MovieService service = new MovieService();

    @GET
    @Path("/forRating")
    @Produces(MediaType.APPLICATION_JSON)
    public Movie getForRating() {
        log.info("get for rating");
        return service.getForRating();
    }

    @POST
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public void rating(@PathParam("id") long id, @FormParam("rating") short rating) {
        log.info("post rating {} for movie with id {} ", rating, id);
        service.addRating(id, rating);
    }

    @GET
    @Path("/recommended")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Movie> recommended(@DefaultValue("3") @QueryParam("limit") int limit) {
        log.info("recommended films with limit {}", limit);
        return service.recommendedMovies(limit);
    }
}
