package com.example.demo;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogoutCommand extends Command{

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        HttpSession session = request.getSession(false);
        if (session != null)
            session.invalidate();

        return Path.MAIN_PAGE.value;
    }
}
