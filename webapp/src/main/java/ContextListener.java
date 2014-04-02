import config.Configuration;
import db.DbiManager;
import db.SchemaMigrator;
import hetrecparser.HetrecParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.lang.invoke.MethodHandles;

import static config.Configuration.DB;

/**
 * Created by vitaly on 3/16/14.
 */
public class ContextListener implements ServletContextListener {
    private static final Logger log = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        log.info("context initialized");

        try {
            log.info(DB.toString());
            log.info(Configuration.OAUTH.toString());

            DbiManager.setUp(DB.getDriverClassName(),
                    DB.getUrl(),
                    DB.getUser(),
                    DB.getPassword());

            SchemaMigrator migrator = new SchemaMigrator(
                    DB.getDriverClassName(),
                    DB.getUrl(),
                    DB.getUser(),
                    DB.getPassword()
            );
            migrator.migrate();

            log.info("parse dataset: {}", Configuration.isParseDataset());
            if (Configuration.isParseDataset()) {
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
