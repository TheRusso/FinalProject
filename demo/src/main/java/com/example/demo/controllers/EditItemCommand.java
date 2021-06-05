package com.example.demo.controllers;

import com.example.demo.Command;
import com.example.demo.Path;
import com.example.demo.RedirectType;
import com.example.demo.ServletResponse;
import com.example.demo.db.dao.ItemDAO;
import com.example.demo.db.entities.Item;
import com.example.demo.db.services.serviceImpl.ItemService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class EditItemCommand extends Command {

    private Logger logger = Logger.getLogger(EditItemCommand.class.getName());

    @Override
    public ServletResponse execute(HttpServletRequest request, HttpServletResponse response) {
        Long id = Long.parseLong(request.getParameter("id"));

        Item item = new ItemService().findById(id);
        item.setTitle(request.getParameter("title"));
        item.setDescription(request.getParameter("description"));

        item.setPrice(Float.parseFloat(request.getParameter("price")));

        logger.info("Item: " + item);

        logger.info("Is added: " + new ItemDAO().updateItem(item));

        return new ServletResponse(Path.SHOP_PAGE_URL.getValue(), RedirectType.REDIRECT);
    }
}
