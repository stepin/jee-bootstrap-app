package name.stepin.templateapp.utils;


/**
 * MediaTypes to define output encoding (@Produces).
 * Do not use for @Consumes -- this will filter all non utf-8 requests.
 */
public class MediaTypeUtf8 {
    public static final String APPLICATION_JSON = "application/json; charset=UTF-8";
    public static final String APPLICATION_XML = "application/xml; charset=UTF-8";
    public static final String TEXT_PLAIN = "text/plain; charset=UTF-8";
    public static final String JAVASCRIPT = "text/javascript; charset=UTF-8";
    public static final String HTML = "text/html; charset=UTF-8";
    public static final String TEXT_CSV = "text/csv; charset=UTF-8";
    public static final String FORM = "application/x-www-form-urlencoded; charset=utf-8";
}
