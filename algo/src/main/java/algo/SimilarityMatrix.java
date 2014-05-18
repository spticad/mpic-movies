package algo;

import models.Rating;
import models.User;

import java.util.ArrayList;
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
    private static SimilarityMatrix _instance;
    private static final int NUMBER_OF_RATINGS = 10;
    private static Map<User, Map<User, Double>> matrix;

    public static synchronized Double getWeightedRating(List<User> users, List<Rating> ratings, User userA, User userB, long movieId) {
        if (_instance == null){
            _instance = new SimilarityMatrix();
            initMatrix(users, ratings);
        }
        return _instance.calculateUserToUserSimilarity(userA, userB)*getMovieRatingByUser(userB, movieId, ratings);
    }

    private SimilarityMatrix() {
        this.matrix = new HashMap<>();
    }

    private static void initMatrix(List<User> users, List<Rating> ratings) {
        for (int i = 0; i < users.size(); i++) {
            User userA = users.get(i);
            Map<User, Double> map = new HashMap<>();
            for (int j = i; j < users.size(); j++) {
                RatingCountMatrix rcm = calcRatingCountMatrix(userA, users.get(j), ratings);
                map.put(users.get(j), (double)rcm.similarityCount / rcm.totalCount);
            }
            matrix.put(userA, map);
        }
    }
    private static Double getMovieRatingByUser(User user, long movieId, List<Rating> ratings){
        Double movieRating = Double.NaN;
        for(Rating rating:ratings){
            if(rating.getUserId() == user.getId() && rating.getMovieId() == movieId){
                movieRating = rating.getRating();
            }
        }
        return movieRating;
    }

    private static RatingCountMatrix calcRatingCountMatrix(User userA, User userB, List<Rating> ratings) {
        RatingCountMatrix rcm = new RatingCountMatrix(NUMBER_OF_RATINGS);
        List<Rating> ratingsByA = new ArrayList<>();
        List<Rating> ratingsByB = new ArrayList<>();
        for (Rating r : ratings) {
            if(r.getUserId() == userA.getId()) {
                ratingsByA.add(r);
            }
            if(r.getUserId() == userB.getId()) {
                ratingsByB.add(r);
            }
        }
        rcm.initMatrix(ratingsByA, ratingsByB);
        rcm.calcTotalCount();
        rcm.calcSimilarityCount();

        return rcm;
    }
    private static Double calculateUserToUserSimilarity(User userA, User userB){
        Double pairSimilarity = Double.NaN;
        if(matrix == null){
        }
        else{
            Map<User, Double> usersSimilarities = matrix.get(userA);
            if(usersSimilarities.get(userB) == null){
                usersSimilarities = matrix.get(userB);
                pairSimilarity = usersSimilarities.get(userA);
            }
            else{
                pairSimilarity = usersSimilarities.get(userB);
            }
        }
        return pairSimilarity;
    }
}
