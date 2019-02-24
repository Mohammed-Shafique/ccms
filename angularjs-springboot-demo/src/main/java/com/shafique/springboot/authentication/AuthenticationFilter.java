package com.shafique.springboot.authentication;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;



public class AuthenticationFilter implements Filter {

    private ServletContext context;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.context = filterConfig.getServletContext();
        this.context.log("AuthenticationFilter initialized");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        String uri = req.getRequestURI();
        this.context.log("Requested Resource::"+uri);

        HttpSession session = req.getSession(false);
        String userName = (String)session.getAttribute("userName");
        System.out.println("************** logged in User ************");
        System.out.println(userName);
        this.context.log(userName);
        if(session == null){
            this.context.log("Unauthorized access request");
            res.sendRedirect("/");
        }else{
            // pass the request along the filter chain
            filterChain.doFilter(request, response);
        }

    }

    @Override
    public void destroy() {

    }
}
