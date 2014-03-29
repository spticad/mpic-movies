
import com.cedarsoftware.util.DeepEquals;
import db.daologic.MovieDaoLogic;
import models.Movie;
import org.dbunit.Assertion;
import org.dbunit.dataset.ITable;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Created by vitaly on 3/26/14.
 */
public class MovieDaoLogicTest {

    private static DbInitializer dbInitializer = new DbInitializer();

    private MovieDaoLogic dao = new MovieDaoLogic();

    @BeforeClass
    public static void createScheme() {
        dbInitializer.setUpSchema();
    }

    @Before
    public void importDataSet() throws Exception {
        dbInitializer.prepareDataset();
    }

    @Test
    public void testSelectById() {
        Movie actual = dao.getById(1);
        Movie expected = new Movie(1, "Casper", "url", "tt1");
        Assert.assertTrue(DeepEquals.deepEquals(expected,actual));
    }

    @Test
    public void testInsert() throws Exception {
        dao.updateImdbId("updated imdbId", 1);

        ITable actual = dbInitializer.getActualTable("movies");
        ITable expected = dbInitializer.readDataSet("datasets/after-update-imdbid.xml").getTable("movies");

        // Assert actual database table match expected table
        Assertion.assertEquals(expected, actual);
    }

}
