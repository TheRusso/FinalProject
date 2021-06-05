package com.example.demo.controllers;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Controller extends HttpServlet {
    private static final long serialVersionUID = 2423353715955164816L;

    private static final Logger log = Logger.getLogger(Controller.class.getName());

    static {
        BasicConfigurator.configure();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        get(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req, resp);
    }



    private void get(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        response.setContentType("text/html; charset=UTF-8");
        request.setCharacterEncoding("UTF-8");

        String path = request.getRequestURI();
        path = path.substring(path.indexOf("view") - 1);

        Pattern pattern = Pattern.compile("view/([\\w/]+)$");
        Matcher matcher = pattern.matcher(path);

        if(matcher.find())
            path = matcher.group(1);

        log.info("Request parameter: command --> " + path);

        Command command = CommandContainer.get(path);
        log.info("Obtained command --> " + command);

        ServletResponse servletResponse = command.execute(request, response);
        if(servletResponse.getRedirectType() == RedirectType.FORWARD)
            log.info("Forward address --> " + servletResponse.getPath());
        else
            log.info("Redirect address --> " + servletResponse.getPath());

        CommandUtil.goToPage(request, response, servletResponse);
    }

    private void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.info("Controller starts");

        String commandName = request.getParameter("command");
        log.info("Request parameter: command --> " + commandName);

        Command command = CommandContainer.get(commandName);
        log.info("Obtained command --> " + command);

        ServletResponse servletResponse = command.execute(request, response);

        if (servletResponse.getPath() == null){
            servletResponse.setPath("/");
            servletResponse.setRedirectType(RedirectType.REDIRECT);
        }


        log.info("Forward address --> " + servletResponse.getPath());

        log.info("Controller finished, now go to forward address --> " + servletResponse.getPath());

        // if the forward address is not null go to the address
        if (servletResponse.getPath() != null) {
            CommandUtil.goToPage(request, response, servletResponse);
        }
    }
}
