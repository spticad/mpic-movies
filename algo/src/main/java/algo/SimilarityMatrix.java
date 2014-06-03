package algo;

import models.Rating;
import models.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Alina
 * Date: 12.05.14
 * Time: 20:02
 * To change this template use File | Settings | File Templates.
 */
public class SimilarityMatrix {
    private static SimilarityMatrix _instance = null;
    private static final int NUMBER_OF_RATINGS = 10;
    private static Map<User, Double> matrix;


    public synchronized static SimilarityMatrix getInstance(User userA, List<User> users, Map<User, List<Rating>> ratings) {
        if (_instance == null) {
            _instance = new SimilarityMatrix();
            initMatrix(userA, users, ratings);
        }
        return _instance;
    }

    public synchronized static Double getWeightedRating(List<Rating> ratings, User userB, long movieId) {
        Double weightedRating = getUserToUserSimilarity(userB) * getMovieRatingByUser(movieId, ratings);
        return weightedRating;
    }

    private SimilarityMatrix() {
        this.matrix = new HashMap<>();
    }

    private static void initMatrix(User userA, List<User> users, Map<User, List<Rating>> ratings) {
            for (int j = 0; j < users.size(); j++) {
                RatingCountMatrix rcm = calcRatingCountMatrix(userA, users.get(j), ratings);
                matrix.put(users.get(j), (double) rcm.similarityCount / rcm.totalCount);
            }
    }

    public static Short getMovieRatingByUser(long movieId, List<Rating> ratings) {
        Short movieRating = 0;
        for (Rating rating : ratings) {
            if (rating.getMovieId() == movieId) {
                movieRating = rating.getRating();
            }
        }
        return movieRating;
    }

    private static RatingCountMatrix calcRatingCountMatrix(User userA, User userB, Map<User, List<Rating>> ratings) {
        RatingCountMatrix rcm = new RatingCountMatrix(NUMBER_OF_RATINGS);
        List<Rating> ratingsByA = ratings.get(userA);
        List<Rating> ratingsByB = ratings.get(userB);
        rcm.initMatrix(ratingsByA, ratingsByB);
        rcm.calcTotalCount();
        rcm.calcSimilarityCount();

        return rcm;
    }

    public static synchronized Double getUserToUserSimilarity(User userB) {
        Double pairSimilarity = Double.NaN;
        if (matrix == null) {
        } else {
            pairSimilarity = matrix.get(userB);
        }
        return pairSimilarity;
    }

}
