package com.example.demo.controllers;

import com.example.demo.Command;
import com.example.demo.Path;
import com.example.demo.RedirectType;
import com.example.demo.ServletResponse;
import com.example.demo.db.dao.OrderDAO;
import com.example.demo.db.Status;
import com.example.demo.db.entities.Order;
import com.example.demo.db.entities.User;
import com.example.demo.db.services.serviceImpl.OrderService;
import com.example.demo.utils.Configuration;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

public class MakeOrderCommand extends Command {
    private Logger logger = Logger.getLogger(MakeOrderCommand.class.getName());

    @Override
    public ServletResponse execute(HttpServletRequest request, HttpServletResponse response) {

        User user = (User)request.getSession().getAttribute("user");

        Order order = new Order.Builder()
                .withUserId(user.getId())
                .withCity(request.getParameter("city"))
                .withCountryId(Long.parseLong(request.getParameter("country_id")))
                .withFirstName(request.getParameter("first_name"))
                .withSecondName(request.getParameter("second_name"))
                .withAddress(request.getParameter("address"))
                .withDeliveryTypeId(Long.parseLong(request.getParameter("delivery_type_id")))
                .withStatusId(Status.REGISTERED.getId())
                .build();

        boolean isInserted = new OrderService().makeOrder(order, request);

        if(!isInserted){
            request.setAttribute("errorMessage", Configuration.getInstance().getErrorMessage("wrongURL"));

            return new ServletResponse.Builder()
                    .withPath(Path.PAGE__ERROR_PAGE.getValue())
                    .build();
        }

        return new ServletResponse.Builder()
                .withPath(Path.MAIN_PAGE.getValue())
                .withRedirect(RedirectType.REDIRECT)
                .build();
    }

    private Integer getId(String str){
        return Integer.parseInt(str.substring(str.indexOf("_") + 1));
    }

    private boolean isValid(String str){
        return str.contains("goodsItem_");
    }
}
