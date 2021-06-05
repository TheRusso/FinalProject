package com.example.demo.controllers;

import com.example.demo.*;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CartCommand extends Command {
    private Logger logger = Logger.getLogger(CartCommand.class.getName());
    private String path;
    
    @Override
    public ServletResponse execute(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("text/plain");
        final String ACTION = "action";

        ServletResponse servletResponse = new ServletResponse(Path.PAGE__ERROR_PAGE.getValue());

        path = request.getContextPath();

        logger.info("Command starts");

        if(request.getParameter(ACTION) != null){

            switch (request.getParameter(ACTION)) {
                case "add":
                    servletResponse = addToCart(request);
                    break;
                case "delete":
                    servletResponse = deleteFromCart(request);
                    break;
                case "order":
                    servletResponse = new ServletResponse();
                    HttpSession session = request.getSession();

                    if (session.getAttribute("user") != null) {
                        servletResponse.setPath(Path.ORDER_PAGE.getValue());
                    } else {
                        servletResponse.setPath(path + "/view/login?to=order");
                    }

                    servletResponse.setRedirectType(RedirectType.REDIRECT);
                    break;
            }

        }else{
            request.setAttribute("errorMessage", Configuration.getInstance().getErrorMessage("wrongURL"));
        }

        logger.info("The action have done his work");

        return servletResponse;
    }

    private ServletResponse addToCart(HttpServletRequest request){
        try{
            Long id = Long.parseLong(request.getParameter("id"));

            HttpSession session = request.getSession();
            session.setAttribute("goodsItem_"+id, 1);
        }catch (NumberFormatException e){
            //won't add to cart
        }

        return new ServletResponse(CommandUtil.wentFromPath(request), RedirectType.REDIRECT);
    }

    private ServletResponse deleteFromCart(HttpServletRequest request){
        HttpSession session = request.getSession();
        System.out.println("goodsItem_" + request.getParameter("id"));
        session.setAttribute("goodsItem_" + request.getParameter("id"), null);

        return new ServletResponse(CommandUtil.wentFromPath(request), RedirectType.REDIRECT);
    }
}
