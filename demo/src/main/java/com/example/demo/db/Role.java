package com.example.demo.db;

import com.example.demo.db.entities.User;

public enum Role {
    ADMIN, CLIENT;

    public static Role getRole(User user){
        int roleId = user.getRoleId();
        return Role.values()[roleId];
    }

    public String getName(){
        return name().toUpperCase();
    }
}
