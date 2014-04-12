import db.DbiManager;
import db.daologic.MovieDaoLogic;
import models.Movie;
import org.dbunit.Assertion;
import org.dbunit.dataset.ITable;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vitaly on 3/26/14.
 */
public class MovieDaoLogicTest {

    private static DbInitializer dbInitializer = new DbInitializer();
    private MovieDaoLogic dao = new MovieDaoLogic(DbiManager.getDbi());

    private static final int POPULAR_MOVIES_COUNT = 2;


    @BeforeClass
    public static void createScheme() {
        dbInitializer.setUpSchema();
    }

    @Before
    public void importDataSet() throws Exception {
        dbInitializer.prepareDataset();
    }

    @Test
    public void testMoviesCount() throws Exception {
        long actual = dao.moviesCount();
        long expected = dbInitializer.readDataSet("datasets/movies-after-update-imdbid.xml").getTable("movies").getRowCount();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testSelectById() {
        Movie actual = dao.getById(1);
        Movie expected = new Movie(1, "Casper", "url", "tt1");
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testSelectByImdbId() {
        Movie actual = dao.getByImdbId("tt3");
        Movie expected = new Movie(3, "Casper 3", "url 3", "tt3");
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testUpdateImdbId() throws Exception {
        dao.updateImdbId(1, "updated imdbId");

        ITable actual = dbInitializer.getActualTable("movies");
        ITable expected = dbInitializer.readDataSet("datasets/movies-after-update-imdbid.xml").getTable("movies");
        Assertion.assertEquals(expected, actual);
    }

    @Test
    public void testUpdateImdbPictureUrl() throws Exception {
        dao.updateImdbPictureUrl(2, "update url");

        ITable actual = dbInitializer.getActualTable("movies");
        ITable expected = dbInitializer.readDataSet("datasets/movies-after-update-imdbpictureurl.xml").getTable("movies");

        Assertion.assertEquals(expected, actual);
    }

    @Test
    public void testInsert() throws Exception {
        dao.insert("Casper 6", "tt6", "url 6");

        ITable actual = dbInitializer.getActualTable("movies");
        ITable expected = dbInitializer.readDataSet("datasets/movies-after-insert.xml").getTable("movies");

        Assertion.assertEquals(expected, actual);
    }

    @Test
    public void testGetTopMovies() throws Exception {
        List<Movie> actual = dao.getTopMovies(POPULAR_MOVIES_COUNT);

        List<Movie> expected = new ArrayList() {{
            add(new Movie(4, "Casper 4", "url 4", "tt4"));
            add(new Movie(1, "Casper", "url", "tt1"));
        }};

        Assert.assertEquals(expected, actual);
    }

}
