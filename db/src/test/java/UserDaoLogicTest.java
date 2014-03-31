import db.DbiManager;
import db.daologic.UserDaoLogic;
import models.User;
import org.dbunit.Assertion;
import org.dbunit.dataset.ITable;
import org.joda.time.DateTime;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;



/**
 * Created by Alex on 31/03/2014.
 */
public class UserDaoLogicTest {
    private static DbInitializer dbInitializer = new DbInitializer();

    private UserDaoLogic dao = new UserDaoLogic(DbiManager.getDbi());

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
        User actual = dao.getByGoogleId("g1");
        User expected = new User(1, "g1", "gName1", "gName1@gmail.com", "gImage1", "token1", new DateTime(2014, 03, 25, 22, 13, 11));
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testSelectByToken() {
        User actual = dao.getByToken("token2");
        User expected = new User(2, "g2", "gName2", "gName2@gmail.com", "gImage2", "token2", new DateTime(2014, 03, 26, 22, 13, 11));
        Assert.assertEquals(expected, actual);
    }

    //Ошибка запроса
    @Test
    public void testUpdateToken() throws Exception {
        dao.updateToken(2, "update token");
        ITable actual  = dbInitializer.getActualTable("users");
        ITable expected = dbInitializer.readDataSet("datasets/users-after-update-token.xml").getTable("users");
        Assertion.assertEquals(expected, actual);
    }

    //Ошибка запроса + добавить проверку
    @Test
    public void testInsert() throws Exception {
        dao.insert("g4", "gName4", "gName4@gmail.com", "gImage4", "token4");
        ITable actual = dbInitializer.getActualTable("users");
        ITable expected = dbInitializer.readDataSet("datasets/users-after-insert.xml").getTable("users");
        String [] ignore = {"registration_date"};
        Assertion.assertEqualsIgnoreCols(expected, actual, ignore);

    }

}
