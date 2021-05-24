package com.example.demo;

import db.Role;
import db.UserDAO;
import db.entities.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.logging.Logger;

public class LoginCommand extends Command{
    private static final long serialVersionUID = -3071536593627692473L;

    private static final Logger log = Logger.getLogger(LoginCommand.class.getName());

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        log.severe("Command starts");

        HttpSession session = request.getSession();

        //obtain login and password from the request
        String email = request.getParameter("email");
        log.severe("Request parameter: login -->" + email);

        String password = request.getParameter("password");

        //error handler
        String errorMessage = null;
        String forward = Path.PAGE__ERROR_PAGE.value;

        if(email == null || password == null || email.isEmpty() || password.isEmpty()){
            errorMessage = "Login/Password cannot be empty";
            request.setAttribute("errorMessage", errorMessage);
            log.severe("errorMessage --> " + errorMessage);
            return forward;
        }

        User user = new UserDAO().findUser(email);
        System.out.println(user);
        log.severe("Found in DB: user --> " + user);

        if(user == null || !password.equals(user.getPass())) {
            errorMessage = "Cannot find user with such login/password";
            request.setAttribute("errorMessage", errorMessage);
            log.severe("errorMessage --> " + errorMessage);
            return forward;
        }else {
            Role userRole = Role.getRole(user);
            log.severe("userRole --> " + userRole);

            if(userRole == Role.ADMIN)
                forward = Path.COMMAND__LIST_ORDERS.value;

            if(userRole == Role.CLIENT)
                forward = Path.COMMAND__LIST_ITEM.value;

            System.out.println("Session");
            session.setAttribute("user", user);
            log.severe("Set the session attribute: email --> " + user.getEmail());
            log.severe("Set the session attribute: pass --> " + user.getPass());

            session.setAttribute("userRole", userRole);
            log.severe("Set the session attribute: userRole --> " + userRole);

            log.info("User " + user + " logged as " + userRole.toString().toLowerCase());
        }
        log.severe("Command finished");

        return forward;
    }
}
