package services;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URISyntaxException;

import static java.lang.String.format;
import static org.apache.commons.lang3.StringUtils.isEmpty;

/**
 * Created by LeoMal on 14.05.2014.
 */
public class UrlUtil {
    public static String getHost(HttpServletRequest request) {
        String host = request.getHeader("Host");
        return isEmpty(host) ? request.getRemoteHost() : host;
    }
    public static String getUrlTo(HttpServletRequest request, String resource, String subResource) throws URISyntaxException {
        return getUrlTo(request, null, resource, subResource);
    }

    public static String getUrlTo(HttpServletRequest request, String path, String resource, String subResource) throws URISyntaxException {
        return format("http://%s%s/%s/%s",
                getHost(request),
                (path != null) ? path : request.getRequestURI(),
                resource,
                subResource);
    }

}
