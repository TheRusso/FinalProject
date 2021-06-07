package com.example.demo.controllers.get;

import com.example.demo.controllers.Command;
import com.example.demo.controllers.Path;
import com.example.demo.controllers.ServletResponse;
import com.example.demo.db.bean.UserOrderBean;
import com.example.demo.db.entities.Item;
import com.example.demo.services.service_impl.ItemService;
import com.example.demo.services.service_impl.OrderService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OpenAdminOrdersCommand extends Command {
    @Override
    public ServletResponse execute(HttpServletRequest request, HttpServletResponse response) {
        List<UserOrderBean> list = null;

        list = new OrderService().findBeanAllOrders();

        

        list.sort(Comparator.comparingLong(UserOrderBean::getId).reversed());


        Map<Item, Integer> map = new HashMap<>();

        for (UserOrderBean userOrderBean :
                list) {
            for (Map.Entry<Long, Integer> entry :
                    userOrderBean.getPreItems().entrySet()) {
                map.put(new ItemService().findById(entry.getKey()), entry.getValue());
            }
            userOrderBean.setItems(map);
        }

        request.setAttribute("user_bean", list.toArray());
        return new ServletResponse(Path.ADMIN_ORDERS.getValue());
    }
}
