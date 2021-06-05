package com.example.demo.controllers;

import com.example.demo.Command;
import com.example.demo.Path;
import com.example.demo.RedirectType;
import com.example.demo.ServletResponse;
import com.example.demo.db.dao.OrderDAO;
import com.example.demo.db.Status;
import com.example.demo.db.entities.Order;
import com.example.demo.db.entities.User;
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

        Order order = new Order();
        User user = (User)request.getSession().getAttribute("user");
        order.setUser_id(user.getId());
        order.setCity(request.getParameter("city"));
        order.setCountry_id(Long.parseLong(request.getParameter("country_id")));
        order.setFirstName(request.getParameter("first_name"));
        order.setLastName(request.getParameter("second_name"));
        order.setAddress(request.getParameter("address"));
        order.setDelivery_type_id(Long.parseLong(request.getParameter("delivery_type_id")));
        order.setStatus_id(Status.REGISTERED.getId());

        // id, quantity
        Map<Integer, Integer> items = new HashMap<>();

        HttpSession session = request.getSession();
        Enumeration attributeNames = session.getAttributeNames();

        while (attributeNames.hasMoreElements()){
            String attribute = attributeNames.nextElement().toString();
            if(isValid(attribute)){
                items.put(getId(attribute), Integer.parseInt(session.getAttribute(attribute).toString()));
            }
        }

        order.setItems(items);
        logger.info("Order: " + order);

        new OrderDAO().makeOrder(order);

        return new ServletResponse(Path.MAIN_PAGE.getValue(), RedirectType.REDIRECT);
    }

    private Integer getId(String str){
        return Integer.parseInt(str.substring(str.indexOf("_") + 1));
    }

    private boolean isValid(String str){
        return str.contains("goodsItem_");
    }
}
