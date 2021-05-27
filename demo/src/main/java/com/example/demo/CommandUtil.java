package com.example.demo;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CommandUtil {
    public static void goToPage(HttpServletRequest request, HttpServletResponse response, String path){
        try {
            RequestDispatcher dispatcher = request.getRequestDispatcher(path);
            dispatcher.forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    public static void goToPage(HttpServletRequest request, HttpServletResponse response, ServletResponse servletResponse){
        try {
            if(servletResponse.getRedirectType() == RedirectType.FORWARD){
                RequestDispatcher dispatcher = request.getRequestDispatcher(servletResponse.getPath());
                dispatcher.forward(request, response);
            }else{
                response.sendRedirect(request.getContextPath() + servletResponse.getPath());
            }
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    public static String wentFromPath(HttpServletRequest request){
        String wentFromPath = request.getHeader("referer");
        if(wentFromPath.contains("view"))
            wentFromPath = wentFromPath.substring(wentFromPath.indexOf("view") - 1);
        else
            wentFromPath = "/";

        return wentFromPath;
    }
}
