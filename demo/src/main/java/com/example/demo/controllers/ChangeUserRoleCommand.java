package com.example.demo.controllers;

import com.example.demo.Command;
import com.example.demo.ServletResponse;
import com.example.demo.db.dao.UserDAO;
import com.example.demo.db.entities.User;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ChangeUserRoleCommand extends Command {
    private Logger logger = Logger.getLogger(BanUserCommand.class.getName());

    @Override
    public ServletResponse execute(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("text/plain");

        Long id = Long.parseLong(request.getParameter("user_id"));

        Integer role_id = Integer.parseInt(request.getParameter("role_id"));

        logger.info(String.format("Change user role: user_id = %s, role_id = %s", id, role_id));

        User user = new UserDAO().findUser(id);
        user.setRoleId(role_id);

        new UserDAO().updateUser(user);

        return null;
    }
}
