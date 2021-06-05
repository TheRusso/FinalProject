package com.example.demo.controllers;

import com.example.demo.Command;
import com.example.demo.Path;
import com.example.demo.RedirectType;
import com.example.demo.ServletResponse;
import com.example.demo.db.dao.ItemDAO;
import com.example.demo.db.entities.Item;
import com.example.demo.db.services.serviceImpl.ItemService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

public class DeleteItemCommand extends Command {
    @Override
    public ServletResponse execute(HttpServletRequest request, HttpServletResponse response) {
        ItemService itemService = new ItemService();

        Item item = itemService.findById(Long.parseLong(request.getParameter("id")));

        item.setDisable(1);

        itemService.update(item);

        return new ServletResponse(Path.SHOP_PAGE_URL.getValue(), RedirectType.REDIRECT);
    }
}
