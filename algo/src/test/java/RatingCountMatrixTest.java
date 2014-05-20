import algo.RatingCountMatrix;
import models.Rating;
import org.joda.time.DateTime;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: stud
 * Date: 14.05.14
 * Time: 10:36
 * To change this template use File | Settings | File Templates.
 */
public class RatingCountMatrixTest {
    private static List<Rating> ratingsByA, ratingsByB;

    @BeforeClass
    public static void initRatings() {
        ratingsByA = new ArrayList() {{
            add(new Rating(1, 1, (short) 1, new DateTime(2014, 5, 10, 12, 17, 11, 105)));
            add(new Rating(1, 2, (short) 1, new DateTime(2014, 5, 10, 12, 18, 11, 105)));
            add(new Rating(1, 3, (short) 3, new DateTime(2014, 5, 10, 12, 19, 11, 105)));
        }};
        ratingsByB = new ArrayList() {{
            add(new Rating(2, 1, (short) 2, new DateTime(2014, 5, 10, 11, 17, 11, 105)));
            add(new Rating(2, 2, (short) 1, new DateTime(2014, 5, 10, 11, 18, 11, 105)));
            add(new Rating(2, 3, (short) 3, new DateTime(2014, 5, 10, 11, 19, 11, 105)));
        }};
    }

    @Test
    public void testInitMatrix() {
        RatingCountMatrix rcm = new RatingCountMatrix(3);
        rcm.initMatrix(ratingsByA, ratingsByB);
        rcm.calcSimilarityCount();
        rcm.calcTotalCount();
        int[][] actual = rcm.matrix;
        int[][] expected = new int[][]{
                {1, 1, 0},
                {0, 0, 0},
                {0, 0, 1},
        };
        Assert.assertArrayEquals("Matrix equality", actual, expected);
        Assert.assertEquals("Similarity count", rcm.similarityCount, 2);
        Assert.assertEquals("Total count", rcm.totalCount, 3);

    }
}

