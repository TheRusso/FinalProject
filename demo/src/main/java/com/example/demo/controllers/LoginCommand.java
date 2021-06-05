package com.example.demo.controllers;

import com.example.demo.Command;
import com.example.demo.Path;
import com.example.demo.RedirectType;
import com.example.demo.ServletResponse;
import com.example.demo.db.Role;
import com.example.demo.db.dao.UserDAO;
import com.example.demo.db.entities.User;
import com.example.demo.db.services.serviceImpl.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.logging.Logger;

public class LoginCommand extends Command {
    private static final long serialVersionUID = -3071536593627692473L;

    private static final Logger log = Logger.getLogger(LoginCommand.class.getName());

    @Override
    public ServletResponse execute(HttpServletRequest request, HttpServletResponse response) {
        log.severe("Command starts");

        return new UserService().loginUser(request);
    }
}
