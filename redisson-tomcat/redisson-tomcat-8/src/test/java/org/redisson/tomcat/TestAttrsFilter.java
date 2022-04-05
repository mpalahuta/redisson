package org.redisson.tomcat;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Populates internal session attributes with values of
 * unusual type to verify robustness of the RedissonSession.
 */
public class TestAttrsFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        HttpSession sess = ((HttpServletRequest) servletRequest).getSession(true);
        sess.setAttribute("session:creationTime", 20);
        sess.setAttribute("session:lastAccessedTime", 12);
        sess.setAttribute("session:maxInactiveInterval", 2000L);
        sess.setAttribute("session:thisAccessedTime", 11);
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
    }

}
