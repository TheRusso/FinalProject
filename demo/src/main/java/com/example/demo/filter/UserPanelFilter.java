package com.example.demo.filter;

import com.example.demo.utils.Configuration;
import com.example.demo.controllers.Path;
import com.example.demo.utils.ErrorPropNamesHandler;
import com.example.demo.utils.ErrorUtil;
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
            ErrorUtil.printErrorMessage(ErrorPropNamesHandler.CANT_FIND_PAGE, request);

            log.info("Set the request attribute: errorMessage --> " + Configuration.getInstance().getErrorMessage(ErrorPropNamesHandler.CANT_FIND_PAGE.getPropName()));

            request.getRequestDispatcher(Path.PAGE_ERROR_PAGE.getValue())
                    .forward(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {

    }
}
