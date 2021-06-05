package com.example.demo.db.services.serviceImpl;

import com.example.demo.db.dao.UserDAO;
import com.example.demo.db.entities.User;
import com.example.demo.db.services.Service;
import org.apache.log4j.Logger;

import java.util.List;

public class UserService implements Service<User>{
    private Logger logger = Logger.getLogger(UserService.class.getName());

    private UserService userService = new UserService();

    @Override
    public List<User> findAll() {
        return userService.findAll();
    }

    @Override
    public User findById(Long id) {
        return userService.findById(id);
    }

    @Override
    public boolean insert(User attr) {
        return userService.insert(attr);
    }

    @Override
    public boolean update(User attr) {
        return userService.update(attr);
    }

    public User findByEmail(String email){
        return userService.findByEmail(email);
    }

    public boolean isRegistered(String email, String password){
        return false;
    }
}
