package com.example.demo.db.services.serviceImpl;

import com.example.demo.db.dao.OrderDAO;
import com.example.demo.db.entities.Order;
import com.example.demo.db.services.Service;

import java.util.List;

public class OrderService implements Service<Order> {
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

    public boolean updateStatus(Long id, int statusId){
        return orderDAO.updateStatus(id, statusId);
    }
}
