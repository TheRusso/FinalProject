package com.example.demo.utils;

import com.example.demo.db.Role;
import com.example.demo.db.entities.User;

import javax.servlet.http.HttpServletRequest;

public class LoginUtil {
    public static boolean isAuthorized(HttpServletRequest request){
        return request.getSession().getAttribute("user") != null;
    }

    public static boolean isAuthorizedAdmin(HttpServletRequest request){
        if(isAuthorized(request))
            return Role.getRole((User) request.getSession().getAttribute("user")) == Role.ADMIN;

        return false;
    }
}
