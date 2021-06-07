package com.example.demo.controllers.get;

import com.example.demo.controllers.Command;
import com.example.demo.controllers.Path;
import com.example.demo.controllers.ServletResponse;
import com.example.demo.db.DBManager;
import com.example.demo.db.dao.OrderDAO;
import com.example.demo.db.bean.UserOrderBean;
import com.example.demo.services.service_impl.OrderService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.Comparator;
import java.util.List;

public class OpenAdminOrdersCommand extends Command {
    @Override
    public ServletResponse execute(HttpServletRequest request, HttpServletResponse response) {
        List<UserOrderBean> list = null;

        list = new OrderService().findBeanAllOrders();


        list.sort(Comparator.comparingLong(UserOrderBean::getId).reversed());

        request.setAttribute("user_bean", list.toArray());

        return new ServletResponse(Path.ADMIN_ORDERS.getValue());
    }
}
