package com.example.demo.controllers.post;

import com.example.demo.controllers.Command;
import com.example.demo.controllers.Path;
import com.example.demo.controllers.RedirectType;
import com.example.demo.controllers.ServletResponse;
import com.example.demo.db.entities.Item;
import com.example.demo.services.serviceImpl.ItemService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
