package org.arta.vopros.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.arta.vopros.dto.UserDto;

import java.io.IOException;
import java.util.Set;

import static org.arta.vopros.utils.UrlPath.LOGIN;
import static org.arta.vopros.utils.UrlPath.REGISTRATION;

@WebFilter("/*")
public class AuthorizationFilter implements Filter {
    private static final Set<String> PUBLIC_PATH = Set.of(LOGIN, REGISTRATION);
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String uri = ((HttpServletRequest) servletRequest).getRequestURI();
        if (isPublicPath(uri) || isLoggedIn(servletRequest, servletResponse)) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            ((HttpServletResponse)servletResponse).sendRedirect(LOGIN);
        }
    }

    private boolean isPublicPath(String uri) {
        return PUBLIC_PATH.stream().anyMatch(e -> uri.startsWith(e));
    }

    private boolean isLoggedIn(ServletRequest req, ServletResponse res) {
        var user = ((HttpServletRequest) req).getSession().getAttribute("user");
        return user != null;
    }
}
