package db;

import models.Movie;
import org.apache.jcs.JCS;
import org.apache.jcs.access.exception.CacheException;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Alina
 * Date: 21.04.14
 * Time: 13:22
 * To change this template use File | Settings | File Templates.
 */
public class JCSClass {
    private JCS cache;
    private static final org.slf4j.Logger log = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    public JCSClass() {
        try {
            cache = JCS.getInstance("testRegion");
        } catch (CacheException ex) {
            log.error(ex.getMessage());
        }
    }

    public void addMoviesElement(String key, List<Movie> value) {
        try {
            cache.put(key, value);
        } catch (CacheException ex) {
            log.error(ex.getMessage());
        }
    }

    public void addMatrixElement(String key, List<UserToUserRating> value) {
        try {
            cache.put(key, value);
        } catch (CacheException ex) {
            log.error(ex.getMessage());
        }
    }

    public String getElement(String key) {
        return (String) cache.get(key);
    }

    public List<UserToUserRating> getSimilarityMatrix(String key) {
        return (List<UserToUserRating>) cache.get(key);
    }
}
