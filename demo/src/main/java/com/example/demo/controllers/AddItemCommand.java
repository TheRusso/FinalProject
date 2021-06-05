package com.example.demo.controllers;

import com.example.demo.Command;
import com.example.demo.Path;
import com.example.demo.RedirectType;
import com.example.demo.ServletResponse;
import com.example.demo.db.dao.ItemDAO;
import com.example.demo.db.entities.Item;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

public class AddItemCommand extends Command {

    private Logger logger = Logger.getLogger(AddItemCommand.class.getName());

    @Override
    public ServletResponse execute(HttpServletRequest request, HttpServletResponse response) {
        logger.info("Start adding");

        Item item = new Item();
        item.setTitle(request.getParameter("title"));
        item.setDescription(request.getParameter("description"));
        item.setPrice(Float.parseFloat(request.getParameter("price")));
        item.setCount(Integer.parseInt(request.getParameter("count")));
        item.setCategory_id(Integer.parseInt(request.getParameter("category")));
        item.setImg("/item/img/ref.jpg");

        logger.info("Item: " + item);

        new ItemDAO().insertItem(item);

        return new ServletResponse(Path.SHOP_PAGE_URL.getValue(), RedirectType.REDIRECT);
    }
}
