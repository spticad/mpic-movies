package config;

/**
 * Created by vitaly on 3/16/14.
 */
public class DbConfig {
    private String driverClassName;
    private String url;
    private String user;

    public DbConfig(String driverClassName, String url, String user) {
        this.driverClassName = driverClassName;
        this.url = url;
        this.user = user;
    }

    public String getDriverClassName() {
        return driverClassName;
    }

    public String getUrl() {
        return url;
    }

    public String getUser() {
        return user;
    }

    @Override
    public String toString() {
        return "DbConfig{" +
                "driverClassName='" + driverClassName + '\'' +
                ", url='" + url + '\'' +
                ", user='" + user + '\'' +
                '}';
    }
}
