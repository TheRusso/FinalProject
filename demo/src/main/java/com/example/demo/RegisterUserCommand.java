package com.example.demo;

import db.UserDAO;
import db.entities.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ResourceBundle;

public class RegisterUserCommand extends Command{

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String forward = Path.PAGE__ERROR_PAGE.value;


        if(!request.getParameter("password").equals(request.getParameter("conf_password")) ||
                request.getParameter("first_name").isEmpty() || request.getParameter("second_name").isEmpty() ||
                request.getParameter("email").isEmpty()){

            request.setAttribute("errorMessage", Configuration.getInstance().getErrorMessage("dataValidationError"));

            return forward;
        }

        User user = createUserFromRequest(request);
        UserDAO userDAO = new UserDAO();

        boolean isRegistered = userDAO.insertUser(user);



        if(isRegistered){
            HttpSession session = request.getSession();
            session.setAttribute("user", user);

            forward = Path.MAIN_PAGE.value;

            return forward;
        }else{

            request.setAttribute("errorMessage", Configuration.getInstance().getErrorMessage("saveNewUserErr"));
        }

        return forward;
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
