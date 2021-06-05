package com.example.demo.controllers;

import com.example.demo.*;
import com.example.demo.db.dao.UserDAO;
import com.example.demo.db.entities.User;
import com.example.demo.utils.Configuration;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class RegisterUserCommand extends Command {

    private final Logger logger = Logger.getLogger(RegisterUserCommand.class.getName());

    @Override
    public ServletResponse execute(HttpServletRequest request, HttpServletResponse response) {
        ServletResponse servletResponse = new ServletResponse(Path.NOT_FOUND.getValue());


        logger.info("Started registering user");

        if(request.getMethod().equals("POST")){

            logger.info("Method POST");


            if(!request.getParameter("password").equals(request.getParameter("conf_password")) ||
                    request.getParameter("first_name").isEmpty() || request.getParameter("second_name").isEmpty() ||
                    request.getParameter("email").isEmpty()){

                logger.warn("Invalid data");
                request.setAttribute("errorMessage", Configuration.getInstance().getErrorMessage("dataValidationError"));
                servletResponse.setPath(Path.PAGE__ERROR_PAGE.getValue());
                return servletResponse;
            }

            logger.info("Valid data");

            User user = createUserFromRequest(request);
            UserDAO userDAO = new UserDAO();

            boolean isRegistered = userDAO.insertUser(user);

            if(isRegistered){
                HttpSession session = request.getSession();
                session.setAttribute("user", user);

                servletResponse.setRedirectType(RedirectType.REDIRECT);
                servletResponse.setPath(Path.MAIN_PAGE.getValue());

                logger.info("The user have been registered");

                return servletResponse;
            }else{
                logger.warn("The user have not been registered");

                servletResponse.setPath(Path.PAGE__ERROR_PAGE.getValue());
                servletResponse.setRedirectType(RedirectType.FORWARD);

                request.setAttribute("errorMessage", Configuration.getInstance().getErrorMessage("saveNewUserErr"));
            }

        }else{
            logger.warn("GET method, so we threw an exception");
            request.setAttribute("errorMessage", Configuration.getInstance().getErrorMessage("cantFindPage"));
        }


        if(request.getParameter("to") != null && request.getParameter("to").equals("order")){
            logger.info("Redirect to: " + request.getParameter("to"));

            servletResponse.setPath("view/order");
        }

        return servletResponse;
    }

    private User createUserFromRequest(HttpServletRequest request){
        User user = new User();
        user.setFirst_name(request.getParameter("first_name"));
        user.setSecond_name(request.getParameter("second_name"));
        user.setEmail(request.getParameter("email"));
        user.setPass(request.getParameter("password"));
        return user;
    }
}
