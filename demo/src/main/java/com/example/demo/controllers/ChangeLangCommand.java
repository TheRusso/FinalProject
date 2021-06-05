package com.example.demo.controllers;

import com.example.demo.Command;
import com.example.demo.CommandUtil;
import com.example.demo.RedirectType;
import com.example.demo.ServletResponse;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.logging.Logger;

public class ChangeLangCommand extends Command {
    Logger logger = Logger.getLogger(ChangeLangCommand.class.getName());

    @Override
    public ServletResponse execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        System.out.println(request.getHeader("referer"));
        if(request.getParameter("lang") != null || !request.getParameter("lang").isEmpty()){
            if(request.getParameter("lang").equals("en") || request.getParameter("lang").equals("ru")){
                session.setAttribute("lang", request.getParameter("lang"));
            }
        }

        String wentFromPath = CommandUtil.wentFromPath(request);


        ServletResponse servletResponse = new ServletResponse(wentFromPath);
        servletResponse.setRedirectType(RedirectType.REDIRECT);

        return servletResponse;
    }
}
