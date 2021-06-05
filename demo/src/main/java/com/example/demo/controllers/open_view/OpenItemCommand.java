package com.example.demo.controllers.open_view;

import com.example.demo.Command;
import com.example.demo.Path;
import com.example.demo.ServletResponse;
import com.example.demo.db.dao.ItemDAO;
import com.example.demo.db.entities.Item;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class OpenItemCommand extends Command {
    @Override
    public ServletResponse execute(HttpServletRequest request, HttpServletResponse response) {
        Long itemId;
        try{
            itemId = Long.parseLong(request.getParameter("item_id"));
        }catch (NumberFormatException e){
            itemId = 1L;
        }

        if(itemId > new ItemDAO().countOfItems() || itemId < 0)
            itemId = 1L;

        Item item = new ItemDAO().findItemById(itemId);
        request.setAttribute("shop_item", item);
        return new ServletResponse(Path.ITEM_PAGE.getValue());
    }
}
