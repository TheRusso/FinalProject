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
    public ServletResponse execute(HttpServletRequest request, HttpServletResponse response) {
        log.severe("Command starts");

        ServletResponse servletResponse = new ServletResponse(Path.NOT_FOUND.value);

        if(request.getMethod().equals("POST")){
            HttpSession session = request.getSession();

            //obtain login and password from the request
            String email = request.getParameter("email");
            log.severe("Request parameter: login -->" + email);

            String password = request.getParameter("password");



            //error handler
            String errorMessage = null;
            servletResponse.setPath(Path.PAGE__ERROR_PAGE.value);
            servletResponse.setRedirectType(RedirectType.FORWARD);

            if(email == null || password == null || email.isEmpty() || password.isEmpty()){
                errorMessage = "Login/Password cannot be empty";
                request.setAttribute("errorMessage", errorMessage);
                log.severe("errorMessage --> " + errorMessage);
                return servletResponse;
            }

            User user = new UserDAO().findUser(email);
            System.out.println(user);
            log.severe("Found in DB: user --> " + user);

            if(user == null || !password.equals(user.getPass())) {
                errorMessage = "Cannot find user with such login/password";
                request.setAttribute("errorMessage", errorMessage);
                log.severe("errorMessage --> " + errorMessage);
                return servletResponse;
            }else {
                Role userRole = Role.getRole(user);
                log.severe("userRole --> " + userRole);

                if(userRole == Role.ADMIN)
                    servletResponse.setPath(Path.COMMAND__LIST_ORDERS.value);

                if(userRole == Role.CLIENT)
                    servletResponse.setPath(Path.COMMAND__LIST_ITEM.value);

                System.out.println("Session");
                session.setAttribute("user", user);
                log.severe("Set the session attribute: email --> " + user.getEmail());
                log.severe("Set the session attribute: pass --> " + user.getPass());

                session.setAttribute("userRole", userRole);
                log.severe("Set the session attribute: userRole --> " + userRole);

                log.info("User " + user + " logged as " + userRole.toString().toLowerCase());
            }
            log.severe("Command finished");

            servletResponse.setRedirectType(RedirectType.REDIRECT);
            servletResponse.setPath(Path.MAIN_PAGE.value);
        }

        return servletResponse;
    }
}
