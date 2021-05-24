package com.example.demo;

import db.ItemDAO;
import db.entities.Item;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class OpenShopPageCommand extends Command{
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        int currentPage = 1;
        List<Item> items;
        int countOfPages = new ItemDAO().countOfPages();

        if(request.getParameter("page") != null){

            try{
                currentPage = Integer.parseInt(request.getParameter("page"));
            }catch (NumberFormatException e){
                items = new ItemDAO().findItemsPage(currentPage);

                request.setAttribute("itemsList", items);
                request.setAttribute("countOfPages", countOfPages);
                request.setAttribute("currentPage", currentPage);

                return Path.SHOP_PAGE.value;
            }
        }

        if(currentPage > countOfPages || currentPage < 0)
            currentPage = 1;

        items = new ItemDAO().findItemsPage(currentPage);

        request.setAttribute("itemsList", items);
        request.setAttribute("countOfPages", countOfPages);
        request.setAttribute("currentPage", currentPage);

        return Path.SHOP_PAGE.value;

    }
}
