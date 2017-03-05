package name.stepin.templateapp.rest;

import lombok.extern.slf4j.Slf4j;

import javax.ejb.Stateless;
import javax.servlet.ServletContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import java.io.IOException;
import java.util.Properties;

@Path("version")
@Stateless
@Slf4j
public class VersionResource {

    private static final String UNKNOWN_VERSION = "unknown";

    @Context
    private ServletContext servletContext;

    @GET
    public String getVersion() {
        return getWarVersion();
    }

    public String getWarVersion() {
        Properties prop = new Properties();

        try {
            prop.load(servletContext.getResourceAsStream("/META-INF/MANIFEST.MF"));
        } catch (IOException e) {
            log.warn("Could not read MANIFEST.MF", e);
            return UNKNOWN_VERSION;
        }

        String version = prop.getProperty("Implementation-Version");
        if (version == null || version.trim().isEmpty()) {
            log.warn("There is no Implementation-Version in the MANIFEST.MF, check Maven config.");
            return UNKNOWN_VERSION;
        }

        return version;
    }
}
