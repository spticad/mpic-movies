import algo.SimilarityMatrix;
import models.Rating;
import models.User;
import org.joda.time.DateTime;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Alina
 * Date: 14.05.14
 * Time: 22:57
 * To change this template use File | Settings | File Templates.
 */
public class SimilarityMatrixTest {
    public static List<User> users;
    public static List<Rating> ratings;
    public static SimilarityMatrix similarityMatrix;
    public static Map<User, Map<User, Double>> simMatrix;

    @BeforeClass
    public static void initData() {
        users = new ArrayList<User>() {{
            add(new User(1, "g1", "gName1", "gName1@gmail.com", "gImage1", "token1", new DateTime(2014, 05, 11, 22, 13, 11, 0)));
            add(new User(2, "g2", "gName2", "gName2@gmail.com", "gImage2", "token2", new DateTime(2014, 05, 12, 22, 13, 11, 0)));
            add(new User(3, "g3", "gName3", "gName3@gmail.com", "gImage3", "token3", new DateTime(2014, 05, 13, 22, 13, 11, 0)));
        }};
        ratings = new ArrayList() {{
            add(new Rating(1, 1, (short) 1, new DateTime(2014, 5, 10, 12, 17, 11, 105)));
            add(new Rating(1, 2, (short) 1, new DateTime(2014, 5, 10, 12, 18, 11, 105)));
            add(new Rating(1, 3, (short) 3, new DateTime(2014, 5, 10, 12, 19, 11, 105)));
            add(new Rating(2, 1, (short) 2, new DateTime(2014, 5, 10, 11, 17, 11, 105)));
            add(new Rating(2, 2, (short) 1, new DateTime(2014, 5, 10, 11, 18, 11, 105)));
            add(new Rating(2, 3, (short) 3, new DateTime(2014, 5, 10, 11, 19, 11, 105)));
            add(new Rating(3, 1, (short) 3, new DateTime(2014, 5, 7, 12, 17, 11, 105)));
            add(new Rating(3, 2, (short) 3, new DateTime(2014, 5, 7, 12, 18, 11, 105)));
            add(new Rating(3, 3, (short) 1, new DateTime(2014, 5, 7, 12, 19, 11, 105)));
        }};
    }

//    @Test
//    public void testGetUserToUserSimilarity(){
//        Double expected = similarityMatrix.getWeightedRating(users, ratings, users.get(0), users.get(2), 1);
//        Double actual = 0.0;
//        Assert.assertEquals(expected, actual);
//    }


}
