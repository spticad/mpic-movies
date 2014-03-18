package config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.lang.invoke.MethodHandles;
import java.util.Properties;

/**
 * Created by vitaly on 3/16/14.
 */
public final class Configuration {
    private static final Logger log = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    public static final DbConfig DB;
    public static final GoogleOAuthConfig OAUTH;

    static {
        try {
            Properties config = new Properties();
            config.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("config.properties"));

            DB = new DbConfig(config.getProperty("db.driverClassName"),
                    config.getProperty("db.url"), config.getProperty("db.user"));
            OAUTH = new GoogleOAuthConfig(config.getProperty("google.clientId"),
                    config.getProperty("google.secret"));
        } catch (IOException e) {
            log.error("loading config failed", e);
            throw new RuntimeException("loading config failed", e);
        }
    }

    private Configuration() {}
}
