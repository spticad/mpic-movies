package resources;

import models.Movie;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import services.MovieService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.lang.invoke.MethodHandles;

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
    @Path("{id}/{rating}")
    @Produces(MediaType.APPLICATION_JSON)
    public String rating (){
        log.info("post rating");
        return service.addRating("id",2);
    }

    @GET
    @Path("/recommended?limit={limit}")
    @Produces(MediaType.APPLICATION_JSON)
    public String recommended(){
        log.info("recommended films");
        return service.recommendedMovie();
    }


}
