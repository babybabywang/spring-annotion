package com.study.web.servlet;

import javax.servlet.*;
import java.io.IOException;

/**
 * 通过SPI机制在容器启动时注册过滤器
 */
public class UserFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("自定义Filter");
        filterChain.doFilter(servletRequest,servletResponse);
    }
}
