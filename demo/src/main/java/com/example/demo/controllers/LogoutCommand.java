package com.example.demo.controllers;


import com.example.demo.Command;
import com.example.demo.Path;
import com.example.demo.RedirectType;
import com.example.demo.ServletResponse;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogoutCommand extends Command {

    @Override
    public ServletResponse execute(HttpServletRequest request, HttpServletResponse response) {
        ServletResponse servletResponse = new ServletResponse(Path.NOT_FOUND.getValue());

        HttpSession session = request.getSession();
        if(session.getAttribute("user") != null){
            session.setAttribute("user", null);

            servletResponse.setPath(Path.MAIN_PAGE.getValue());
            servletResponse.setRedirectType(RedirectType.REDIRECT);
        }

        return servletResponse;
    }
}
