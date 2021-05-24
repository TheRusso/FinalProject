package com.example.demo;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogoutCommand extends Command{

    @Override
    public ServletResponse execute(HttpServletRequest request, HttpServletResponse response) {
        ServletResponse servletResponse = new ServletResponse(Path.NOT_FOUND.value);

        HttpSession session = request.getSession();
        if(session.getAttribute("user") != null){
            session.invalidate();

            servletResponse.setPath(Path.MAIN_PAGE.value);
            servletResponse.setRedirectType(RedirectType.REDIRECT);
        }

        return servletResponse;
    }
}
