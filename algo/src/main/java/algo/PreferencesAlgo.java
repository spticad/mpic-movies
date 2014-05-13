package algo;

import models.Movie;
import org.apache.commons.collections.CollectionUtils;

import java.util.List;
import java.util.Random;

/**
 * Created by vitaly on 4/9/14.
 */
public class PreferencesAlgo {

    public Movie getForRating(List<Movie> candidates, List<Movie> watched) {
        List<Movie> forRating = (List<Movie>) CollectionUtils.subtract(candidates, watched);
        return forRating.get(new Random().nextInt(forRating.size()));
    }
}
