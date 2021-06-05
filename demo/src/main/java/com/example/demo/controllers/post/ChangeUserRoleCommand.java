package com.example.demo.controllers.post;

import com.example.demo.controllers.Command;
import com.example.demo.controllers.ServletResponse;
import com.example.demo.db.entities.User;
import com.example.demo.services.serviceImpl.UserService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ChangeUserRoleCommand extends Command {
    private Logger logger = Logger.getLogger(BanUserCommand.class.getName());

    @Override
    public ServletResponse execute(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("text/plain");

        Long id = Long.parseLong(request.getParameter("user_id"));

        int roleId = Integer.parseInt(request.getParameter("role_id"));

        logger.info(String.format("Change user role: user_id = %s, role_id = %s", id, roleId));

        UserService userService = new UserService();

        User user = userService.findById(id);
        user.setRoleId(roleId);

        userService.update(user);

        return null;
    }
}
