//package cz.upce.votingsystemapplication.config;
//
//import org.springframework.boot.web.servlet.ServletComponentScan;
//import org.springframework.stereotype.Component;
//
//import javax.servlet.*;
//import javax.servlet.annotation.WebFilter;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//@Component
//@ServletComponentScan
//@WebFilter(urlPatterns = "/*",filterName = "CrossDomainAccessFilter")
//public class CORS implements Filter {
//    @Override
//    public void init(FilterConfig filterConfig) throws ServletException {
//
//    }
//
//    @Override
//    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//        HttpServletResponse response = (HttpServletResponse) servletResponse;
//        response.setHeader("Access-Control-Allow-Origin", "*");
//        response.setHeader("Access-Control-Allow-Credentials", "true");
//        filterChain.doFilter(servletRequest, response);
//    }
//
//    @Override
//    public void destroy() {
//
//    }
//}