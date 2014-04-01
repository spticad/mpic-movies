package config;

/**
 * Created by vitaly on 3/16/14.
 */
public class GoogleOAuthConfig {

    public String getClientId() {
        return Configuration.get("google.clientId");
    }

    public String getSecret() {
        return Configuration.get("google.secret");
    }

    @Override
    public String toString() {
        return "GoogleOAuthConfig{" +
                "clientId='" + getClientId() + '\'' +
                ", secret='" + getSecret() + '\'' +
                '}';
    }
}
