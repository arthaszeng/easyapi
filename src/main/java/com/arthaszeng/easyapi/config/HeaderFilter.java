package com.arthaszeng.easyapi.config;


import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Component
public class HeaderFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest servletRequest = (HttpServletRequest) request;
        servletRequest.getHeader("header");

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
