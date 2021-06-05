package com.example.demo.controllers.get;

import com.example.demo.controllers.Command;
import com.example.demo.controllers.Path;
import com.example.demo.controllers.RedirectType;
import com.example.demo.controllers.ServletResponse;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class OpenLoginPageCommand extends Command {
    @Override
    public ServletResponse execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();

        request.setAttribute("to", request.getParameter("to"));

        if(session.getAttribute("user") == null){
            return new ServletResponse.Builder()
                    .withPath(Path.LOGIN.getValue())
                    .build();
        }

        return new ServletResponse.Builder()
                .withPath(Path.MAIN_PAGE.getValue())
                .withRedirect(RedirectType.REDIRECT)
                .build();
    }

}
