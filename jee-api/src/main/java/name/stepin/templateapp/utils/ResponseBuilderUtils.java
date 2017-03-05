package name.stepin.templateapp.utils;

import javax.ws.rs.core.CacheControl;
import javax.ws.rs.core.Response;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;


public class ResponseBuilderUtils {

    public static Response.ResponseBuilder doNotCache(Response.ResponseBuilder responseBuilder) {
        CacheControl cacheControl = new CacheControl();
        cacheControl.setNoCache(true);
        responseBuilder.cacheControl(cacheControl);

        responseBuilder.expires(new Date(0));

        return responseBuilder;
    }

    public static Response.ResponseBuilder filename(Response.ResponseBuilder responseBuilder, String filename) {
        return filename(responseBuilder, filename, false, false);
    }

    public static Response.ResponseBuilder filename(Response.ResponseBuilder responseBuilder, String filename,
                                                    boolean isIe8OrLess, boolean isAndroid) {
        if (isAndroid) {
            return responseBuilder.header("Content-Disposition", "attachment; filename=" + filename);
        }

        String encodedFilename = encodeForUrl(filename);

        if (isIe8OrLess) {
            return responseBuilder.header("Content-Disposition", "attachment; filename=" + encodedFilename);
        }

        return responseBuilder.header("Content-Disposition", "attachment; filename*=UTF-8''" + encodedFilename);
    }

    public static Response.ResponseBuilder nginxContent(Response.ResponseBuilder builder, String uri) {
        return builder.header("X-Accel-Redirect", uri);
    }

    public static Response.ResponseBuilder attachment(String contentType, String filename, Object entity) {
        return filename(doNotCache(Response.ok(entity, contentType)), filename);
    }

    private static String encodeForUrl(String s) {
        try {
            return URLEncoder.encode(s, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }
}
