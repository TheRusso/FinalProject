package com.example.demo.services;

import com.example.demo.controllers.CommandUtil;
import com.example.demo.controllers.Path;
import com.example.demo.controllers.RedirectType;
import com.example.demo.controllers.ServletResponse;
import com.example.demo.utils.Configuration;
import com.example.demo.utils.ErrorPropNamesHandler;
import com.example.demo.utils.ErrorUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class CartService {
    private final String ACTION = "action";

    public ServletResponse doAction(HttpServletRequest request,
                                    ServletResponse servletResponse){
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
                        servletResponse.setPath(request.getContextPath() + "/view/login?to=order");
                    }

                    servletResponse.setRedirectType(RedirectType.REDIRECT);
                    break;
                default:
                    ErrorUtil.printErrorMessage(ErrorPropNamesHandler.WRONG_URL, request);
                    return new ServletResponse.Builder()
                            .withPath(Path.PAGE_ERROR_PAGE.getValue())
                            .build();
            }

        }else{
            ErrorUtil.printErrorMessage(ErrorPropNamesHandler.WRONG_URL, request);
        }

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
