package com.example.demo.controllers.post;

import com.example.demo.controllers.Command;
import com.example.demo.controllers.Path;
import com.example.demo.controllers.ServletResponse;
import com.example.demo.services.CartService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CartCommand extends Command {
    private Logger logger = Logger.getLogger(CartCommand.class.getName());
    
    @Override
    public ServletResponse execute(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("text/plain");


        ServletResponse servletResponse = new ServletResponse(Path.PAGE_ERROR_PAGE.getValue());

        logger.info("Command starts");

        servletResponse = new CartService().doAction(request);

        logger.info("The action have done his work");

        return servletResponse;
    }


}
