package config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.lang.invoke.MethodHandles;
import java.util.Properties;

import static java.lang.Boolean.parseBoolean;
import static java.lang.Integer.parseInt;

/**
 * Created by vitaly on 3/16/14.
 */
public final class Configuration {
    private static final Logger log = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    private static final Properties config = new Properties();

    public static final DbConfig DB = new DbConfig();
    public static final GoogleOAuthConfig OAUTH = new GoogleOAuthConfig();

    static {
        try {
            config.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("config.properties"));
        } catch (IOException e) {
            log.error("loading config failed", e);
            throw new RuntimeException("loading config failed", e);
        }
    }

    public static boolean isParseDataset() {
        return parseBoolean(get("parseDataset"));
    }

    public static int getPopularMoviesCount() { return parseInt(get("popularMoviesCount")); }

    public static String get(String key) {
        String res = System.getProperty(key);
        return (res != null) ? res : config.getProperty(key);
    }

    public static int getMoviesCount(){
        return Integer.parseInt(get("topMoviesCount"));
    }

    private Configuration() {
    }
}
