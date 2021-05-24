package com.example.demo;

import db.ItemDAO;
import db.entities.Item;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class CatalogCommand extends Command{
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        List<Item> itemList = new ItemDAO().findAllItems();
        String forward = Path.CATALOG.value;
        request.setAttribute("menuItems", itemList);
        return forward;
    }
}
