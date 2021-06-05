package com.example.demo.db.services.serviceImpl;

import com.example.demo.ServletResponse;
import com.example.demo.db.dao.OrderDAO;
import com.example.demo.db.entities.Order;
import com.example.demo.db.services.Service;
import com.example.demo.utils.CartUtil;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderService implements Service<Order> {
    private Logger logger = Logger.getLogger(OrderService.class.getName());

    private final OrderDAO orderDAO = new OrderDAO();

    @Override
    public List<Order> findAll() {
        return orderDAO.findOrders();
    }

    @Override
    public Order findById(Long id) {
        return orderDAO.findOrderById(id);
    }

    @Override
    public boolean insert(Order attr) {
        return orderDAO.makeOrder(attr);
    }

    @Override
    public boolean update(Order attr) {
        return orderDAO.update(attr);
    }

    public boolean makeOrder(Order order,
                                     HttpServletRequest request){
        CartUtil cartUtil = new CartUtil();
        // id, quantity
        Map<Long, Integer> items = new HashMap<>();

        HttpSession session = request.getSession();
        Enumeration attributeNames = session.getAttributeNames();

        while (attributeNames.hasMoreElements()){
            String attribute = attributeNames.nextElement().toString();
            if(cartUtil.isValid(attribute)){
                items.put(cartUtil.getIdSuffix(attribute), Integer.parseInt(session.getAttribute(attribute).toString()));
            }
        }

        order.setItems(items);
        logger.info("Order: " + order);

        return insert(order);
    }

    public boolean updateStatus(Long id, int statusId){
        return orderDAO.updateStatus(id, statusId);
    }
}
