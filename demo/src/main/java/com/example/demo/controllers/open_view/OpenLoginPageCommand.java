package com.example.demo.controllers.open_view;

import com.example.demo.Command;
import com.example.demo.Path;
import com.example.demo.RedirectType;
import com.example.demo.ServletResponse;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class OpenLoginPageCommand extends Command {
    @Override
    public ServletResponse execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();

        request.setAttribute("to", request.getParameter("to"));

        // error handler
        String errorMessage = null;
        ServletResponse servletResponse = new ServletResponse(Path.PAGE__ERROR_PAGE.getValue());


        if(session.getAttribute("user") == null){
            servletResponse.setPath(Path.LOGIN.getValue());
            return servletResponse;
        }

        servletResponse.setPath(Path.MAIN_PAGE.getValue());
        servletResponse.setRedirectType(RedirectType.REDIRECT);
        return servletResponse;
    }

}
