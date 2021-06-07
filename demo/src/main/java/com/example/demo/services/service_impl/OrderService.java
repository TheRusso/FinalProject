package com.example.demo.services.service_impl;

import com.example.demo.db.DBManager;
import com.example.demo.db.bean.UserOrderBean;
import com.example.demo.db.dao.OrderDAO;
import com.example.demo.db.entities.Order;
import com.example.demo.db.entities.User;
import com.example.demo.services.ServiceEntity;
import com.example.demo.utils.CartUtil;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.*;

public class OrderService implements ServiceEntity<Order> {
    private final Logger logger = Logger.getLogger(OrderService.class.getName());

    private final OrderDAO orderDAO = new OrderDAO();

    @Override
    public List<Order> findAll() {
        try {
            return orderDAO.findOrders(DBManager.getInstance().getConnection());
        } catch (SQLException exception) {
            logger.error(exception);
        }

        return new ArrayList<>();
    }

    @Override
    public Order findById(Long id) {
        Order order = null;
        try {
            order = orderDAO.findOrderById(id, DBManager.getInstance().getConnection());
        } catch (SQLException exception) {
            logger.error(exception);
        }

        return order;
    }

    @Override
    public boolean insert(Order attr) {
        try {
            return orderDAO.makeOrder(attr, DBManager.getInstance().getConnection());
        } catch (SQLException exception) {
            logger.error(exception);
        }

        return false;
    }

    @Override
    public boolean update(Order attr) {
        try {
            return orderDAO.update(attr, DBManager.getInstance().getConnection());
        } catch (SQLException exception) {
            logger.error(exception);
        }
        return false;
    }

    public List<UserOrderBean> findBeanAllOrders(){
        try {
            return new OrderDAO().findBeanAllOrders(DBManager.getInstance().getConnection());
        } catch (SQLException exception) {
            logger.error(exception);
        }

        return new ArrayList<>();
    }

    public List<UserOrderBean> findBeanForUser(User user){
        try {
            return new OrderDAO().findBeanForUser(user, DBManager.getInstance().getConnection());
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        return new ArrayList<>();
    }

    public boolean makeOrder(Order order,
                                     HttpServletRequest request){
        CartUtil cartUtil = new CartUtil();
        // id, quantity
        Map<Long, Integer> items = new HashMap<>();

        HttpSession session = request.getSession();
        Enumeration<String> attributeNames = session.getAttributeNames();

        while (attributeNames.hasMoreElements()){
            String attribute = attributeNames.nextElement();
            if(cartUtil.isValid(attribute)){
                items.put(cartUtil.getIdSuffix(attribute), Integer.parseInt(session.getAttribute(attribute).toString()));
            }
        }

        order.setItems(items);
        logger.info("Order: " + order);

        return insert(order);
    }

    public boolean updateStatus(Long id, int statusId){
        try {
            return orderDAO.updateStatus(id, statusId, DBManager.getInstance().getConnection());
        } catch (SQLException exception) {
            logger.error(exception);
        }
        return false;
    }
}
