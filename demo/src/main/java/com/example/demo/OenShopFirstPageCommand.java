package com.example.demo;

import db.ItemDAO;
import db.entities.Item;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class OenShopFirstPageCommand extends Command{
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        List<Item> items = new ItemDAO().findItemsPage(1);

        request.setAttribute("items", items);

        return Path.SHOP_PAGE.value;
    }
}
