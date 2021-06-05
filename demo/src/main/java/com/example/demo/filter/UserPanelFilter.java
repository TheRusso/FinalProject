package com.example.demo.filter;

import com.example.demo.Configuration;
import com.example.demo.Path;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class UserPanelFilter implements Filter {

    private Logger log = Logger.getLogger(UserPanelFilter.class.getName());

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpSession session = request.getSession();

        log.info("Filter started work");

        if(session.getAttribute("user") != null){
            filterChain.doFilter(servletRequest, servletResponse);
            log.info("Filter finished work");
        }else{
            String errorMessage = Configuration.getInstance().getErrorMessage("cantFindPage");
            request.setAttribute("errorMessage", errorMessage);
            log.info("Set the request attribute: errorMessage --> " + errorMessage);

            request.getRequestDispatcher(Path.PAGE__ERROR_PAGE.getValue())
                    .forward(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {

    }
}
