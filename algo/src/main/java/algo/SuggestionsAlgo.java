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

    public List<Movie> getRecommended(User user, Map<User, List<Rating>> ratings, List<Movie> notRatedMovies, int limit) {
        List<RatedMovie> recommendations = new ArrayList<>();
       // predictMovieRatings(user, recommendations, notRatedMovies, ratings);
        Collections.sort(recommendations);
        List<Movie> recommendedMovies = new ArrayList<>(limit);
        for (int i = 0; i < limit; i++) {
            recommendedMovies.add(recommendations.get(i).getMovie());
        }
        return recommendedMovies;
    }

    private void predictMovieRatings(List<User> closeUsers, User user, List<RatedMovie> recommendations, List<Movie> notRated, Map<User, List<Rating>> ratings) {
        for (Movie movie : notRated) {
            double predictedRating = PredictRating(closeUsers, user, movie, ratings);
            if (!Double.isNaN(predictedRating)) {
                recommendations.add(new RatedMovie(movie, predictedRating));
            }
        }
    }

    private double PredictRating(List<User> closeUsers, User user, Movie movie, Map<User, List<Rating>> ratings) {
        double predictedRating = Double.NaN;
        double similaritySum = 0.0;
        double weightRatingSum = 0.0;
        SimilarityMatrix similarityMatrix;

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
        return predictedRating;
    }

    private double getRatingByUser(User user, Movie movie, Map<User, List<Rating>> ratings) {
        double movieRating = Double.NaN;
        for (Rating rating : ratings.get(user)) {
            if (rating.getMovieId() == movie.getId())
                movieRating = (double) rating.getRating();
        }
        return movieRating;
    }


}