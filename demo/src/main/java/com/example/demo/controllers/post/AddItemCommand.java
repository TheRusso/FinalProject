package com.example.demo.controllers.post;

import com.example.demo.controllers.Command;
import com.example.demo.controllers.Path;
import com.example.demo.controllers.RedirectType;
import com.example.demo.controllers.ServletResponse;
import com.example.demo.db.entities.Item;
import com.example.demo.services.serviceImpl.ItemService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddItemCommand extends Command {

    private Logger logger = Logger.getLogger(AddItemCommand.class.getName());

    @Override
    public ServletResponse execute(HttpServletRequest request, HttpServletResponse response) {
        logger.info("Start adding");

        Item item = new Item.Builder()
                .withTitle(request.getParameter("title"))
                .withDescription(request.getParameter("description"))
                .withPrice(Float.parseFloat(request.getParameter("price")))
                .withCount(Integer.parseInt(request.getParameter("count")))
                .withCategory_id(Integer.parseInt(request.getParameter("category")))
                .withImg("/item/img/ref.jpg")
                .build();

        logger.info("Item: " + item);

        new ItemService().insert(item);

        return new ServletResponse(Path.SHOP_PAGE_URL.getValue(), RedirectType.REDIRECT);
    }
}
