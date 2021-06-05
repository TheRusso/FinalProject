package com.example.demo.controllers.get;

import com.example.demo.controllers.Command;
import com.example.demo.controllers.Path;
import com.example.demo.controllers.ServletResponse;
import com.example.demo.db.entities.Item;
import com.example.demo.utils.CartUtil;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

public class OpenCartCommand extends Command {
    private Logger logger = Logger.getLogger(OpenCartCommand.class.getName());

    @Override
    public ServletResponse execute(HttpServletRequest request, HttpServletResponse response) {
        List<Item> items = new CartUtil().getItemsFromAttr(request);

        logger.info("Items in bucket: " + items);

        request.setAttribute("cart_items", items.toArray());

        return new ServletResponse(Path.CART_PAGE.getValue());
    }
}
