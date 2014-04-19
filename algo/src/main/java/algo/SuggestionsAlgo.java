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
    private List<UserToUserRating> similarityValues;
    private List<User> allUsers;
    private List<Movie> allMovies;
    private List<Rating> allRatings;

    public List<Movie> getRecommended(User user, Map<User, List<Rating>> ratings, List<Movie> notRatedMovies, int limit) {
        CalculateUserSimilarity(allUsers, ratings);
        List<RatedMovie> recommendations = new ArrayList<>();
        predictMovieRatings(user, recommendations, notRatedMovies, ratings);
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
                //TODO: get similarity
                double similarityBetweenUsers = 0; //= similarityValues[allUsers.indexOf(user)][allUsers.indexOf(anotherUser)];
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

    public List<UserToUserRating> CalculateUserSimilarity(List<User> users, Map<User, List<Rating>> ratings) {
        usersCount = users.size();
        ratingValuesCount = RATING_VALUES_COUNT;
        List<UserToUserRating> similarityValues = new ArrayList<>();
        RatingCountMatrix rcm;
        for (int u = 0; u < usersCount; u++) {
            for (int v = u + 1; v < usersCount; v++) {
                rcm = new RatingCountMatrix(ratings.get(users.get(u)), ratings.get(users.get(u)), ratingValuesCount);
                int totalCount = rcm.GetTotalCount();
                int agreementCount = rcm.GetAgreementCount();
                if (agreementCount > 0) {
                    similarityValues.add(new UserToUserRating(new Tuple<>(users.get(u),users.get(v)), (double) agreementCount / (double) totalCount));
                } else {
                    similarityValues.add(new UserToUserRating(new Tuple<>(users.get(u),users.get(v)), 0.0));
                }
            }
            similarityValues.add(new UserToUserRating(new Tuple<>(users.get(u),users.get(u)), 1.0));
        }
        return similarityValues;
    }


}