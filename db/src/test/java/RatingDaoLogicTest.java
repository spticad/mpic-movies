import db.DbiManager;
import db.daologic.RatingDaoLogic;
import org.dbunit.Assertion;
import org.dbunit.dataset.ITable;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

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

/*
Список

    @Test
    public void testSelectById() {
     }
*/

    //Ошибка + добавить проверку
    @Test
    public void testUpdateRating() throws Exception {
        dao.updateRating(1, 3, (short) 2);
        ITable actual = dbInitializer.getActualTable("ratings");
        ITable expected = dbInitializer.readDataSet("datasets/ratings-after-update-rating.xml").getTable("ratings");
        String[] ignore = {"timestamp"};
        Assertion.assertEqualsIgnoreCols(expected, actual, ignore);
    }

    //Добавить проверку
    @Test
    public void testInsertRating() throws Exception {
        dao.insert(2, 1, (short) 7);
        ITable actual = dbInitializer.getActualTable("ratings");
        ITable expected = dbInitializer.readDataSet("datasets/ratings-after-insert.xml").getTable("ratings");
        String[] ignore = {"timestamp"};
        Assertion.assertEqualsIgnoreCols(expected, actual, ignore);
    }
}
