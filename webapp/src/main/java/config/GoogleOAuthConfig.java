package config;

/**
 * Created by vitaly on 3/16/14.
 */
public class GoogleOAuthConfig {
    private String clientId;
    private String secret;

    public GoogleOAuthConfig(String clientId, String secret) {
        this.clientId = clientId;
        this.secret = secret;
    }

    public String getClientId() {
        return clientId;
    }

    public String getSecret() {
        return secret;
    }

    @Override
    public String toString() {
        return "GoogleOAuthConfig{" +
                "clientId='" + clientId + '\'' +
                ", secret='" + secret + '\'' +
                '}';
    }
}
