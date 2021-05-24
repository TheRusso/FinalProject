package com.example.demo;

import db.Role;
import db.UserDAO;
import db.entities.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class OpenLoginPageCommand extends Command{
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();

        // error handler
        String errorMessage = null;
        String forward = Path.PAGE__ERROR_PAGE.value;

        if(session.getAttribute("user") == null){
            forward = Path.LOGIN.value;
            return forward;
        }

        forward = Path.MAIN_PAGE.value;
        return forward;
    }

}
