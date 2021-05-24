package com.example.demo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.logging.Logger;

public class ChangeLangCommand extends Command{
    Logger logger = Logger.getLogger(ChangeLangCommand.class.getName());

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();

        if(request.getParameter("lang") != null || !request.getParameter("lang").isEmpty()){
            if(request.getParameter("lang").equals("en") || request.getParameter("lang").equals("ru")){
                session.setAttribute("lang", request.getParameter("lang"));
            }
        }

        return Path.MAIN_PAGE.value;
    }
}
