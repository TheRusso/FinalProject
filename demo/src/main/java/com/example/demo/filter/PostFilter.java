package com.example.demo.filter;

import com.example.demo.controllers.Path;
import com.example.demo.utils.ErrorPropNamesHandler;
import com.example.demo.utils.ErrorUtil;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class PostFilter implements Filter {
    private Logger logger = Logger.getLogger(PostFilter.class.getName());

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        logger.info("Filter starts work");
        HttpServletRequest request = (HttpServletRequest) servletRequest;

        logger.info("Method: " + request.getMethod());

        if(request.getMethod().equals("POST")){
            filterChain.doFilter(servletRequest, servletResponse);

            return;
        }

        logger.info("Threw an exception");
        ErrorUtil.printErrorMessage(ErrorPropNamesHandler.CANT_FIND_PAGE, request);
        servletRequest.getRequestDispatcher(Path.PAGE_ERROR_PAGE.getValue()).forward(servletRequest, servletResponse);
    }
}
