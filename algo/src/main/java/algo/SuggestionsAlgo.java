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
    private final int RATING_VALUES_COUNT = 10;

    private int usersCount;     //number of users
    private int ratingValuesCount;    //number of ratings, size of rating count matrix
    private double[][] similarityValues;
    private List<User> allUsers;
    private List<Movie> allMovies;
    private List<Rating> allRatings;

    public List<Movie> getRecommended(User user, Map<User, List<Rating>> ratings, int limit) {
        CalculateUserSimilarity(allUsers, ratings.get(user));
        List<RatedMovie> recommendations = new ArrayList<>();
        List<Movie> notRated = null; //list of movies not rated by the user
        predictMovieRatings(user, recommendations, notRated, ratings);
        Collections.sort(recommendations);
        List<Movie> recommendedMovies = new ArrayList<>(limit);
        for(int i = 0; i < limit; i++){
            recommendedMovies.add(recommendations.get(i).getMovie());
        }
        return recommendedMovies;
    }

    private void predictMovieRatings(User user, List<RatedMovie> recommendations, List<Movie> notRated, Map<User, List<Rating>> ratings) {
        for (Movie movie : notRated) {
            double predictedRating = PredictRating(user, movie, ratings);
            if (!Double.isNaN(predictedRating)) {
                recommendations.add(new RatedMovie(movie, predictedRating));
            }
        }
    }

    private double PredictRating(User user, Movie movie, Map<User, List<Rating>> ratings) {
        double predictedRating = Double.NaN;
        double similaritySum = 0.0;
        double weightRatingSum = 0.0;

        for (User anotherUser : allUsers) {
            double ratingByAnotherUser = getRatingByUser(anotherUser, movie, ratings);
            if (!Double.isNaN(ratingByAnotherUser)) {   //if item was rated
                double similarityBetweenUsers = similarityValues[allUsers.indexOf(user)][allUsers.indexOf(anotherUser)];
                double weightRating = similarityBetweenUsers * ratingByAnotherUser;
                weightRatingSum += weightRating;
                similaritySum += similarityBetweenUsers;
            }
            if (similaritySum > 0.0) {
                predictedRating = weightRatingSum / similaritySum;
            }
        }
        return predictedRating;
    }

    private double getRatingByUser(User user, Movie movie, Map<User, List<Rating>> ratings){
        double movieRating = Double.NaN;
        for(Rating rating : ratings.get(user)){
            if (rating.getMovieId() == movie.getId())
                movieRating = (double)rating.getRating();
        }
        return movieRating;
    }

    private void CalculateUserSimilarity(List<User> users, List<Rating> ratingsByUserA) {
        usersCount = users.size();
        ratingValuesCount = RATING_VALUES_COUNT;
        similarityValues = new double[usersCount][usersCount];
        RatingCountMatrix rcm;
        for (int u = 0; u < usersCount; u++) {
            for (int v = u + 1; v < usersCount; v++) {
                List<Rating> ratingsByUserB = null;  //initialize list of ratings by user
                rcm = new RatingCountMatrix(ratingsByUserA, ratingsByUserB, ratingValuesCount);
                int totalCount = rcm.GetTotalCount();
                int agreementCount = rcm.GetAgreementCount();
                if (agreementCount > 0) {
                    similarityValues[u][v] = (double) agreementCount / (double) totalCount;
                } else {
                    similarityValues[u][v] = 0.0;
                }
            }
            similarityValues[u][u] = 1.0;
        }
    }


}