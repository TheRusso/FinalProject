package com.example.demo;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommandUtil {
    public static void goToPage(HttpServletRequest request, HttpServletResponse response, String path){
        try {
            RequestDispatcher dispatcher = request.getRequestDispatcher(path);
            dispatcher.forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }
}
