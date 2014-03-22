import config.Configuration;
import db.DbiManager;
import hetrecparser.HetrecParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.lang.invoke.MethodHandles;

/**
 * Created by vitaly on 3/16/14.
 */
public class ContextListener implements ServletContextListener {
    private static final Logger log = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        log.info("context initialized");

        try {
            log.info(Configuration.DB.toString());
            log.info(Configuration.OAUTH.toString());

            DbiManager.setUp(Configuration.DB.getDriverClassName(),
                    Configuration.DB.getUrl(),
                    Configuration.DB.getUser(),
                    "");

            if (Configuration.PARSE_DATASET) {
                HetrecParser.parseHetrecDataset();
            }
        } catch (Exception ex) {
            log.error("cannot setup configs", ex);
            throw new RuntimeException("context initialized failed", ex);
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        log.info("context destroyed");
    }
}
