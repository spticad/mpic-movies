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
        List<Movie> forRating = getUnratedMovies(candidates, watched);
        Random random = new Random();
        int randomNum = random.nextInt(candidates.size());
        return forRating.get(randomNum);
    }

    public List<Movie> getUnratedMovies(List<Movie> candidates, List<Movie> watched) {
        return (List<Movie>) CollectionUtils.disjunction(candidates, watched);
    }
}
