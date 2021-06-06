package com.example.demo.controllers.post;


import com.example.demo.controllers.Command;
import com.example.demo.controllers.ServletResponse;
import com.example.demo.services.service_impl.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LogoutCommand extends Command {

    @Override
    public ServletResponse execute(HttpServletRequest request, HttpServletResponse response) {
        return new UserService().logout(request);
    }
}
