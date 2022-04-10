package net.csdn.ac.c4.exam.config;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * XSS 过滤器
 *
 * @author lzpeng
 * @version 1.0
 * @date 2022/3/3 21:50
 */
public class XssFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        XssFilterRequestWrapper xssFilterRequestWrapper = new XssFilterRequestWrapper(request);
        filterChain.doFilter(xssFilterRequestWrapper, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
