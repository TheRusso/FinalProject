package com.example.demo.controllers.post;

import com.example.demo.controllers.Command;
import com.example.demo.controllers.Path;
import com.example.demo.controllers.ServletResponse;
import com.example.demo.db.DBManager;
import com.example.demo.db.dao.OrderDAO;
import com.example.demo.db.bean.UserOrderBean;
import com.example.demo.db.entities.User;
import com.example.demo.services.service_impl.OrderService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Comparator;
import java.util.List;

public class UserPanelCommand extends Command {
    @Override
    public ServletResponse execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();

        User user = (User)session.getAttribute("user");

        List<UserOrderBean> orderBeanList = new OrderService().findBeanForUser(user);

        orderBeanList.sort(Comparator.comparingLong(UserOrderBean::getId).reversed());

        request.setAttribute("user_bean", orderBeanList.toArray());

        return new ServletResponse(Path.USER_PAGE.getValue());
    }
}
