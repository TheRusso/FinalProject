package com.example.demo.controllers.post;

import com.example.demo.controllers.Command;
import com.example.demo.controllers.Path;
import com.example.demo.controllers.RedirectType;
import com.example.demo.controllers.ServletResponse;
import com.example.demo.db.Status;
import com.example.demo.db.entities.Order;
import com.example.demo.db.entities.User;
import com.example.demo.services.service_impl.OrderService;
import com.example.demo.utils.ErrorPropNamesHandler;
import com.example.demo.utils.ErrorUtil;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MakeOrderCommand extends Command {
    private Logger logger = Logger.getLogger(MakeOrderCommand.class.getName());

    @Override
    public ServletResponse execute(HttpServletRequest request, HttpServletResponse response) {

        User user = (User)request.getSession().getAttribute("user");

        Order order = new Order.Builder()
                .withUserId(user.getId())
                .withCity(request.getParameter("city"))
                .withCountryId(Long.parseLong(request.getParameter("country_id")))
                .withAddress(request.getParameter("address"))
                .withDeliveryTypeId(Long.parseLong(request.getParameter("delivery_type_id")))
                .withStatusId(Status.REGISTERED.getId())
                .build();

        boolean isInserted = new OrderService().makeOrder(order, request);

        if(!isInserted){
            ErrorUtil.printErrorMessage(ErrorPropNamesHandler.WRONG_URL, (HttpServletRequest) request);

            return new ServletResponse.Builder()
                    .withPath(Path.PAGE_ERROR_PAGE.getValue())
                    .build();
        }

        return new ServletResponse.Builder()
                .withPath(Path.MAIN_PAGE.getValue())
                .withRedirect(RedirectType.REDIRECT)
                .build();
    }

    private Integer getId(String str){
        return Integer.parseInt(str.substring(str.indexOf("_") + 1));
    }

    private boolean isValid(String str){
        return str.contains("goodsItem_");
    }
}
