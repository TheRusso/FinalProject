package com.example.demo.controllers;


import com.example.demo.Command;
import com.example.demo.Path;
import com.example.demo.RedirectType;
import com.example.demo.ServletResponse;
import com.example.demo.db.services.serviceImpl.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogoutCommand extends Command {

    @Override
    public ServletResponse execute(HttpServletRequest request, HttpServletResponse response) {
        return new UserService().logout(request);
    }
}
