package com.example.demo;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.NotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Controller extends HttpServlet {
    private static final long serialVersionUID = 2423353715955164816L;

    private static final Logger log = Logger.getLogger(Controller.class.getName());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        xz(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req, resp);
    }



    private void xz(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        response.setContentType("text/html; charset=UTF-8");
        request.setCharacterEncoding("UTF-8");

        String path = request.getRequestURI();
        System.out.println(path);
        path = path.substring(path.indexOf("view") - 1);
        System.out.println(path);

        Pattern pattern = Pattern.compile("/([\\w]+)$");
        Matcher matcher = pattern.matcher(path);

        if(matcher.find())
            path = matcher.group(1);

        Command command = null;

        command = CommandContainer.get(path);
        command.execute(request, response);

        CommandUtil.goToPage(request, response, Path.SHOP_PAGE.value);
    }

    private void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.severe("Controller starts");

        String commandName = request.getParameter("command");
        log.severe("Request parameter: command --> " + commandName);

        Command command = CommandContainer.get(commandName);
        log.severe("Obtained command --> " + command);

        String forward = command.execute(request, response);
        log.severe("Forward address --> " + forward);

        log.severe("Controller finished, now go to forward address --> " + forward);

        // if the forward address is not null go to the address
        if (forward != null) {
            RequestDispatcher disp = request.getRequestDispatcher(forward);
            disp.forward(request, response);
        }
    }
}
