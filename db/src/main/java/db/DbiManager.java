package db;

import org.apache.commons.dbcp.ConnectionFactory;
import org.apache.commons.dbcp.DriverManagerConnectionFactory;
import org.apache.commons.dbcp.PoolableConnectionFactory;
import org.apache.commons.dbcp.PoolingDataSource;
import org.apache.commons.pool.impl.GenericObjectPool;
import org.skife.jdbi.v2.DBI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.lang.invoke.MethodHandles;

/**
 * Created by vitaly on 3/19/14.
 */
public class DbiManager {

    private static final Logger log = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    private static GenericObjectPool connectionPool = null;
    private static DBI dbi = null;

    private DbiManager() {}

    public static void setUp(String driverName, String dbUrl, String userName, String password) throws Exception {
        //
        // Load JDBC Driver class.
        //
        Class.forName(driverName).newInstance();

        //
        // Creates an instance of GenericObjectPool that holds our
        // pool of connections object.
        //
        connectionPool = new GenericObjectPool();
        connectionPool.setMaxActive(10);

        //
        // Creates a connection factory object which will be use by
        // the pool to create the connection object. We passes the
        // JDBC url info, username and password.
        //
        ConnectionFactory cf = new DriverManagerConnectionFactory(
                dbUrl,
                userName,
                password);

        //
        // Creates a PoolableConnectionFactory that will wraps the
        // connection object created by the ConnectionFactory to add
        // object pooling functionality.
        //
        PoolableConnectionFactory pcf =
                new PoolableConnectionFactory(cf, connectionPool,
                        null, null, false, true);
        PoolingDataSource poolingDataSource = new PoolingDataSource(connectionPool);
        dbi = new DBI(poolingDataSource);
    }

    public static DBI getDbi() {
        log.debug("connection pool info");
        log.debug("Max: {} Active: {}, Idle: {}",
                connectionPool.getMaxActive(),
                connectionPool.getNumActive(),
                connectionPool.getNumIdle());
        return dbi;
    }
}
