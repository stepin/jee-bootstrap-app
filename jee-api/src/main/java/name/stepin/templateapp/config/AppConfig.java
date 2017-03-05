package name.stepin.templateapp.config;

import javax.ejb.Stateless;

/**
 * Simple KV config component.
 */
@Stateless
public class AppConfig {

    public String getAppUrl() {
        return getStringParameter("appUrl", "http://localhost:8080");
    }

    private String getStringParameter(String paramName, String defaultValue) {
        String value = System.getenv(paramName);
        return value != null ? value : defaultValue;
    }
}
