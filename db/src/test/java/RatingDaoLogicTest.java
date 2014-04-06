import db.DbiManager;
import db.daologic.RatingDaoLogic;
import models.Rating;
import org.dbunit.Assertion;
import org.dbunit.dataset.ITable;
import org.joda.time.DateTime;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alex on 31/03/2014.
 */
public class RatingDaoLogicTest {
    private static DbInitializer dbInitializer = new DbInitializer();

    private RatingDaoLogic dao = new RatingDaoLogic(DbiManager.getDbi());

    @BeforeClass
    public static void createScheme() {
        dbInitializer.setUpSchema();
    }

    @Before
    public void importDataSet() throws Exception {
        dbInitializer.prepareDataset();
    }

    @Test
    public void testGetByUserId() {
        List<Rating> actual = dao.getByUserId(1);
        List<Rating> expected = new ArrayList() {{
            add(new Rating(1, 1, (short) 10, new DateTime(2014, 3, 30, 12, 15, 11, 105)));
            add(new Rating(1, 2, (short) 8, new DateTime(2014, 3, 30, 12, 17, 11, 105)));
            add(new Rating(1, 3, (short) 5, new DateTime(2014, 3, 30, 12, 18, 11, 105)));
        }};

        Assert.assertArrayEquals(expected.toArray(), actual.toArray());
    }

    @Test
    public void testUpdateRating() throws Exception {
        dao.updateRating(1, 3, (short) 2, new DateTime(2014, 3, 31, 15, 18, 11, 105));
        ITable actual = dbInitializer.getActualTable("ratings");
        ITable expected = dbInitializer.readDataSet("datasets/ratings-after-update-rating.xml").getTable("ratings");
        Assertion.assertEquals(expected, actual);
    }

    @Test
    public void testInsertRating() throws Exception {
        dao.insert(2, 1, (short) 7, new DateTime(2014, 3, 31, 15, 18, 11, 105));
        ITable actual = dbInitializer.getActualTable("ratings");
        ITable expected = dbInitializer.readDataSet("datasets/ratings-after-insert.xml").getTable("ratings");
        Assertion.assertEquals(expected, actual);
    }
}
