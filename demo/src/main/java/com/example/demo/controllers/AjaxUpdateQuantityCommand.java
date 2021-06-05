package com.example.demo.controllers;

import com.example.demo.Command;
import com.example.demo.ServletResponse;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AjaxUpdateQuantityCommand extends Command {
    @Override
    public ServletResponse execute(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("text/plain");

        HttpSession session = request.getSession();
        session.setAttribute("goodsItem_"+request.getParameter("id"), request.getParameter("quantity"));

        return null;
    }
}
