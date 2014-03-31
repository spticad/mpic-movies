import db.DbiManager;
import liquibase.Liquibase;
import liquibase.database.jvm.HsqlConnection;
import liquibase.resource.FileSystemResourceAccessor;
import org.dbunit.IDatabaseTester;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

/**
 * Created with IntelliJ IDEA.
 * User: vitaly
 * Date: 25.03.14
 * Time: 15:55
 */
public class DbInitializer {

    private static final String CHANGE_LOG = "liquibase/db.changelog.main.xml";

    private String dbDriverName;
    private String dbUrl;
    private String dbUserName;
    private String dbPassword;
    private IDatabaseTester databaseTester = null;

    public void setUpSchema() {
        try {
            //read config and init DbiManager
            Properties config = new Properties();
            config.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("config.properties"));

            dbDriverName = config.getProperty("db.driverClassName");
            dbUrl = config.getProperty("db.url");
            dbUserName = config.getProperty("db.user");
            dbPassword = config.getProperty("db.password");

            DbiManager.setUp(dbDriverName, dbUrl, dbUserName, "");

            //setup h2 schema
            Class.forName(dbDriverName);
            Connection holdingConnection = DriverManager.getConnection(dbUrl, dbUserName, dbPassword);

            HsqlConnection hsconn = new HsqlConnection(holdingConnection);
            String pathToChangelog = Utils.getFilePath(CHANGE_LOG);
            Liquibase liquibase = new Liquibase(pathToChangelog, new FileSystemResourceAccessor(), hsconn);
            liquibase.dropAll();
            liquibase.update("");
            hsconn.close();
        } catch (Exception ex) {
            throw new RuntimeException("Error during database initialization", ex);
        }
    }

    public void prepareDataset() throws Exception {
        IDataSet dataSet = readDataSet("datasets/dataset.xml");
        cleanlyInsert(dataSet);
    }

    public IDataSet readDataSet(String fileName) throws Exception {
        return new FlatXmlDataSetBuilder().build(new File(Utils.getFilePath(fileName)));
    }

    public void setUpDBTester() throws ClassNotFoundException {
        databaseTester = new JdbcDatabaseTester(
                dbDriverName, dbUrl,
                dbUserName, dbPassword);
    }

    public ITable getActualTable(String tableName) throws Exception {
        setUpDBTester();
        IDataSet databaseDataSet = databaseTester.getConnection().createDataSet();
        return databaseDataSet.getTable(tableName);
    }

    private void cleanlyInsert(IDataSet dataSet) throws Exception {
        setUpDBTester();
        databaseTester.setSetUpOperation(DatabaseOperation.CLEAN_INSERT);
        databaseTester.setDataSet(dataSet);
        databaseTester.onSetup();
    }
}
