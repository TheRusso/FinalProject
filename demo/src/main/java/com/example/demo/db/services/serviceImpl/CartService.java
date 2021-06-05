package com.example.demo.db.services.serviceImpl;

import com.example.demo.*;
import com.example.demo.utils.Configuration;

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
                    request.setAttribute("errorMessage", Configuration.getInstance().getErrorMessage("wrongURL"));
                    return new ServletResponse.Builder()
                            .withPath(Path.PAGE__ERROR_PAGE.getValue())
                            .build();
            }

        }else{
            request.setAttribute("errorMessage", Configuration.getInstance().getErrorMessage("wrongURL"));
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
