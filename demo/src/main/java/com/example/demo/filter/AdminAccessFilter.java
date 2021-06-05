package com.example.demo.filter;

import com.example.demo.utils.Configuration;
import com.example.demo.controllers.Path;
import com.example.demo.db.Role;
import com.example.demo.db.entities.User;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AdminAccessFilter implements Filter {

    private Logger logger = Logger.getLogger(AdminAccessFilter.class.getName());

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;

        try{
            logger.info("User: " + request.getSession().getAttribute("user"));

            Role role = Role.getRole((User) request.getSession().getAttribute("user"));

            logger.info("Role: " + role);

            if(role == Role.ADMIN){
                filterChain.doFilter(servletRequest,servletResponse);
            }else{
                openErrorPage((HttpServletRequest) servletRequest, (HttpServletResponse) servletResponse);
            }

        }catch (NullPointerException exception){
            openErrorPage((HttpServletRequest) servletRequest, (HttpServletResponse) servletResponse);
        }

    }

    @Override
    public void destroy() {

    }

    private void openErrorPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String errorMessage;

        errorMessage = Configuration.getInstance().getErrorMessage("cantFindPage");
        request.setAttribute("errorMessage", errorMessage);

        logger.info("Access denied");

        request.getRequestDispatcher(Path.PAGE__ERROR_PAGE.getValue())
                .forward(request, response);
    }
}
