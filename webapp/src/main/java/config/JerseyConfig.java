package config;

import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.server.ResourceConfig;
import services.MovieService;

/**
 * This class is specified in the <tt>web.xml</tt> file, and is used when Jersey is loaded to set dependencies.
 */
public class JerseyConfig extends ResourceConfig {

    private static final String PACKAGES_PATH = "resources";

    public JerseyConfig() {
        this(new MovieService());
    }

    /**
     * This is the constructor called directly by unit tests. This allows us to pass in a mocked version of the
     * service objects to just test the functionality of the API and not the business logic itself.
     */
    public JerseyConfig(final MovieService movieService) {
        register(new AbstractBinder() {

            @Override
            protected void configure() {
                bind(movieService).to(MovieService.class);
            }
        });

        /*
		 * Specify where resource classes are located. These are the classes that constitute the API.
		 */
        packages(true, PACKAGES_PATH);
    }
}
