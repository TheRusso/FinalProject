package com.example.demo.controllers;

import com.example.demo.*;
import com.example.demo.db.services.serviceImpl.CartService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CartCommand extends Command {
    private Logger logger = Logger.getLogger(CartCommand.class.getName());
    
    @Override
    public ServletResponse execute(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("text/plain");


        ServletResponse servletResponse = new ServletResponse(Path.PAGE__ERROR_PAGE.getValue());

        logger.info("Command starts");

        servletResponse = new CartService().doAction(request, servletResponse);

        logger.info("The action have done his work");

        return servletResponse;
    }


}
