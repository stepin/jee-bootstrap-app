package name.stepin.templateapp.filter;


import lombok.extern.slf4j.Slf4j;
import name.stepin.templateapp.utils.SimpleHttpFilter;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Really simple auth filter. Use your own for more complex cases.
 */
@WebFilter(urlPatterns = "/rest/*")
@Slf4j
public class AuthFilter extends SimpleHttpFilter {

    private final static String realm = "Protected";

    private final static String login = "admin";
    private final static String password = "admin";

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String authHeader = request.getHeader("Authorization");
        if (authHeader == null) {
            unauthorized(response, "Unauthorized");
            return;
        }

        Base64.Decoder decoder = Base64.getDecoder();
        StringTokenizer stringTokenizer = new StringTokenizer(authHeader);
        if (stringTokenizer.hasMoreTokens()) {
            String token = stringTokenizer.nextToken();
            if (token.equalsIgnoreCase("Basic")) {
                String credentials = new String(decoder.decode(stringTokenizer.nextToken()), "UTF-8");
                int delimiterPosition = credentials.indexOf(":");
                if (delimiterPosition == -1) {
                    unauthorized(response, "Invalid authentication token");
                    return;
                }

                String username = credentials.substring(0, delimiterPosition).trim();
                String password = credentials.substring(delimiterPosition + 1).trim();

                boolean isLoginFailed = isLoginFailedSimple(username, password);
                if (isLoginFailed) {
                    unauthorized(response, "Bad credentials");
                    return;
                }

                chain.doFilter(request, response);
                return;
            }
        }
        unauthorized(response, "Invalid authentication headers");
    }

    private boolean isLoginFailedSimple(String login, String password) {
        return AuthFilter.login.equals(login) || !AuthFilter.password.equals(password);
    }

    /**
     * A bit more complex auth case.
     */
    private boolean isLoginFailedDb(String login, String password) {
        List results = entityManager
            .createNativeQuery("SELECT login FROM users\n" +
                "WHERE login = :login \n" +
                "AND md5password = MD5(:password) \n" +
                "LIMIT 1 ")
            .setParameter("login", login)
            .setParameter("password", password)
            .getResultList();
        return results.isEmpty();
    }

    private void unauthorized(HttpServletResponse response, String message) throws IOException {
        response.setHeader("WWW-Authenticate", "Basic realm=\"" + realm + "\"");
        response.sendError(401, message);
    }
}
