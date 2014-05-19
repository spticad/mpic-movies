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
    private static Map<User, Map<User, Double>> matrix;


    public synchronized static SimilarityMatrix getInstance(List<User> users, Map<User, List<Rating>> ratings) {
        if (_instance == null) {
            _instance = new SimilarityMatrix();
            initMatrix(users, ratings);
        }
        return _instance;
    }

    public synchronized Double getWeightedRating(List<Rating> ratings, User userA, User userB, long movieId) {
        return getUserToUserSimilarity(userA, userB) * getMovieRatingByUser(movieId, ratings);
    }

    private SimilarityMatrix() {
        this.matrix = new HashMap<>();
    }

    private static void initMatrix(List<User> users, Map<User, List<Rating>> ratings) {
        for (int i = 0; i < users.size(); i++) {
            User userA = users.get(i);
            Map<User, Double> map = new HashMap<>();
            for (int j = i; j < users.size(); j++) {
                RatingCountMatrix rcm = calcRatingCountMatrix(userA, users.get(j), ratings);
                map.put(users.get(j), (double) rcm.similarityCount / rcm.totalCount);
            }
            matrix.put(userA, map);
        }
    }

    private static Short getMovieRatingByUser(long movieId, List<Rating> ratings) {
        Short movieRating = -1;
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

    public static synchronized Double getUserToUserSimilarity(User userA, User userB) {
        Double pairSimilarity = Double.NaN;
        if (matrix == null) {
        } else {
            Map<User, Double> usersSimilarities = matrix.get(userA);
            if (usersSimilarities.get(userB) == null) {
                usersSimilarities = matrix.get(userB);
                pairSimilarity = usersSimilarities.get(userA);
            } else {
                pairSimilarity = usersSimilarities.get(userB);
            }
        }
        return pairSimilarity;
    }

}
