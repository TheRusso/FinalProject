package com.example.demo.db.services;

import com.example.demo.db.services.serviceImpl.ItemService;
import com.example.demo.db.services.serviceImpl.OrderService;
import com.example.demo.db.services.serviceImpl.UserService;

public class ServicesFactory {
    public Service createService(ServicesEnum service){
        switch (service){
            case USER:
                return new UserService();
            case ITEM:
                return new ItemService();
            case ORDER:
                return new OrderService();
            default:
                throw new IllegalArgumentException("No such argument");
        }
    }
}
