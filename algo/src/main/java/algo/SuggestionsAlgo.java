package algo;

import models.Movie;
import models.Rating;
import models.User;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Created by vitaly on 4/9/14.
 */
public class SuggestionsAlgo {

    public List<Object> getRecommended(List<User> similarUsers, User user, SimilarityMatrix similarityMatrix, Map<User, List<Rating>> ratings, List<Movie> notRatedMovies, int limit) {
        List<RatedObject> candidatesToRecommend = new ArrayList<>();
        List<Object> recommended = new ArrayList<>();
        double commonRatting = 0;
        for (Movie movie : notRatedMovies) {
            double similarityBetweenUsers = -1;
            for (User otherUser : similarUsers) {
                similarityBetweenUsers += similarityMatrix.getUserToUserSimilarity(user, otherUser);
                Double predictedRating = similarityMatrix.getWeightedRating(ratings.get(otherUser), user, otherUser, movie.getId());
                if (predictedRating != Double.NaN && predictedRating >= 0 && similarityBetweenUsers != Double.NaN) {
                    commonRatting += predictedRating / similarityBetweenUsers;
                }
            }
            if (commonRatting != Double.NaN) {
                candidatesToRecommend.add(new RatedObject(movie, commonRatting));
            }
        }
        Collections.sort(candidatesToRecommend);
        for (int i = 0; i < limit; i++) {
            recommended.add(candidatesToRecommend.get(i).getObject());
        }
        return recommended;
    }

//    private double PredictRating(List<User> closeUsers, User user, Movie movie, Map<User, List<Rating>> ratings) {
//        double predictedRating = Double.NaN;
//        for (User anotherUser : closeUsers) {
//            double ratingByAnotherUser = getRatingByUser(anotherUser, movie, ratings);
//            if (!Double.isNaN(ratingByAnotherUser)) {   //if item was rated
//                //double similarityBetweenUsers = similarityMatrix.getWeightedRating(users, ratings, users.get(0), users.get(1)); //= similarityValues[allUsers.indexOf(user)][allUsers.indexOf(anotherUser)];
//                double weightRating = similarityBetweenUsers * ratingByAnotherUser;
//                weightRatingSum += weightRating;
//                similaritySum += similarityBetweenUsers;
//            }
//            if (similaritySum > 0.0) {
//                predictedRating = weightRatingSum / similaritySum;
//            }
//        }
//        return predictedRating;
//    }

//    private double getRatingByUser(User user, Movie movie, Map<User, List<Rating>> ratings) {
//        double movieRating = Double.NaN;
//        for (Rating rating : ratings.get(user)) {
//            if (rating.getMovieId() == movie.getId())
//                movieRating = (double) rating.getRating();
//        }
//        return movieRating;
//    }


}