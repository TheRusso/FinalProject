package com.example.demo.controllers.post;

import com.example.demo.controllers.Command;
import com.example.demo.controllers.ServletResponse;
import com.example.demo.services.serviceImpl.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
