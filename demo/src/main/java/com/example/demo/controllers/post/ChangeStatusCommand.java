package com.example.demo.controllers.post;

import com.example.demo.controllers.Command;
import com.example.demo.controllers.ServletResponse;
import com.example.demo.services.serviceImpl.OrderService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ChangeStatusCommand extends Command {
    private Logger logger = Logger.getLogger(ChangeStatusCommand.class.getName());

    @Override
    public ServletResponse execute(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("text/plain");

        Long id = Long.parseLong(request.getParameter("order_id"));

        Integer integer = Integer.parseInt(request.getParameter("status_id"));

        logger.info(String.format("Change status order: order_id = %s, status_id = %s", id, integer));

        new OrderService().updateStatus(id, integer);

        return null;
    }
}
