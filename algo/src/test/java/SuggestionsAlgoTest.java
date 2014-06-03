import algo.SimilarityMatrix;
import algo.SuggestionsAlgo;
import models.Movie;
import models.Rating;
import models.User;
import org.joda.time.DateTime;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Alex on 20/05/2014.
 */
public class SuggestionsAlgoTest {
    public static List<User> users, similarUsers;
    public static List<Rating> allRaitings;
    public static List<Movie> notRatedMovies;
    public static Map<User, List<Rating>> ratings = new HashMap<>();

//    @BeforeClass
//    public static void initData() {
//        users = new ArrayList<User>() {{
//            add(new User(1, "g1", "gName1", "gName1@gmail.com", "gImage1", "token1", new DateTime(2014, 05, 11, 22, 13, 11, 0)));
//            add(new User(2, "g2", "gName2", "gName2@gmail.com", "gImage2", "token2", new DateTime(2014, 05, 12, 22, 13, 11, 0)));
//            add(new User(3, "g3", "gName3", "gName3@gmail.com", "gImage3", "token3", new DateTime(2014, 05, 13, 22, 13, 11, 0)));
//        }};
//        similarUsers = new ArrayList<User>() {{
//            add(new User(2, "g2", "gName2", "gName2@gmail.com", "gImage2", "token2", new DateTime(2014, 05, 12, 22, 13, 11, 0)));
//            add(new User(3, "g3", "gName3", "gName3@gmail.com", "gImage3", "token3", new DateTime(2014, 05, 13, 22, 13, 11, 0)));
//        }};
//
//        allRaitings = new ArrayList() {{
//            add(new Rating(1, 1, (short) 1, new DateTime(2014, 5, 10, 12, 17, 11, 105)));
//            add(new Rating(1, 2, (short) 1, new DateTime(2014, 5, 10, 12, 18, 11, 105)));
//            add(new Rating(1, 3, (short) 3, new DateTime(2014, 5, 10, 12, 19, 11, 105)));
//
//            add(new Rating(2, 1, (short) 2, new DateTime(2014, 5, 10, 11, 17, 11, 105)));
//            add(new Rating(2, 2, (short) 1, new DateTime(2014, 5, 10, 11, 18, 11, 105)));
//            add(new Rating(2, 3, (short) 3, new DateTime(2014, 5, 10, 11, 19, 11, 105)));
//            add(new Rating(2, 4, (short) 3, new DateTime(2014, 5, 10, 11, 18, 11, 105)));
//            add(new Rating(2, 6, (short) 2, new DateTime(2014, 5, 10, 11, 18, 11, 105)));
//            add(new Rating(2, 5, (short) 1, new DateTime(2014, 5, 10, 11, 19, 11, 105)));
//
//            add(new Rating(3, 1, (short) 3, new DateTime(2014, 5, 7, 12, 17, 11, 105)));
//            add(new Rating(3, 2, (short) 3, new DateTime(2014, 5, 7, 12, 18, 11, 105)));
//            add(new Rating(3, 3, (short) 1, new DateTime(2014, 5, 7, 12, 19, 11, 105)));
//            add(new Rating(3, 7, (short) 3, new DateTime(2014, 5, 7, 12, 18, 11, 105)));
//            add(new Rating(3, 5, (short) 1, new DateTime(2014, 5, 7, 12, 19, 11, 105)));
//        }};
//        notRatedMovies = new ArrayList() {{
//            add(new Movie(7, "Casper 2", "url 2", "tt2"));
//            add(new Movie(6, "Casper 3", "url 3", "tt3"));
//            add(new Movie(4, "Casper 4", "url 4", "tt4"));
//            add(new Movie(5, "Casper 5", "url 5", "tt5"));
//        }};
//        for (User u : users) {
//            List<Rating> ratingsByUser = new ArrayList<>();
//            for (Rating r : allRaitings) {
//                if (r.getUserId() == u.getId()) {
//                    ratingsByUser.add(r);
//                }
//            }
//            ratings.put(u, ratingsByUser);
//        }
//    }
//
//    @Test
//    public void testGetRecommended() {
//        User user = users.get(0);
//        SimilarityMatrix similarityMatrix = SimilarityMatrix.getInstance(users, ratings);
//        int limit = 3;
//        SimilarityMatrix.getInstance(users, ratings);
//        List<Movie> actual = new SuggestionsAlgo().getRecommended(similarUsers, user, similarityMatrix, ratings, notRatedMovies, limit);
//        List<Movie> expected = new ArrayList() {{
//            add(new Movie(4, "Casper 4", "url 4", "tt4"));
//            add(new Movie(6, "Casper 3", "url 3", "tt3"));
//            add(new Movie(5, "Casper 5", "url 5", "tt5"));
//        }};
//        Assert.assertArrayEquals(expected.toArray(), actual.toArray());
//
//    }

}
