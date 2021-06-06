package com.example.demo.controllers.post;

import com.example.demo.controllers.Command;
import com.example.demo.controllers.RedirectType;
import com.example.demo.controllers.ServletResponse;
import com.example.demo.db.dao.UserDAO;
import com.example.demo.db.entities.User;
import com.example.demo.services.service_impl.UserService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BanUserCommand extends Command {
    private Logger logger = Logger.getLogger(BanUserCommand.class.getName());

    @Override
    public ServletResponse execute(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("text/plain");

        Long id = Long.parseLong(request.getParameter("user_id"));

        Integer ban = Integer.parseInt(request.getParameter("ban"));

        logger.info(String.format("Change user role: user_id = %s, isBanned = %s", id, ban == 1 ? "yes" : "no"));

        User user = new UserDAO().findUser(id);
        user.setBanned(ban);

        new UserService().update(user);

        return new ServletResponse(request.getContextPath() + "/", RedirectType.REDIRECT);
    }
}
