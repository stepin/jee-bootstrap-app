package name.stepin.templateapp.filter;

import lombok.extern.slf4j.Slf4j;
import name.stepin.templateapp.utils.SimpleHttpFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Fixes default encoding.
 * Should be first in the filters chain (use web.xml).
 */
@WebFilter(urlPatterns = "/rest/*")
@Slf4j
public class CharsetFilter extends SimpleHttpFilter {
    @Override
    public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        if (request.getCharacterEncoding() == null) {
            request.setCharacterEncoding("UTF-8");
        }

        chain.doFilter(request, response);
    }
}
