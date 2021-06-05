package com.example.demo.controllers.get;

import com.example.demo.controllers.Command;
import com.example.demo.controllers.Path;
import com.example.demo.controllers.ServletResponse;
import com.example.demo.db.entities.Item;
import com.example.demo.services.serviceImpl.ItemService;
import com.example.demo.utils.CategoryUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class OpenShopPageCommand extends Command {
    @Override
    public ServletResponse execute(HttpServletRequest request, HttpServletResponse response) {
        int currentPage = 1;
        List<Item> items;

        ItemService itemService = new ItemService();

        List<String> categories = new CategoryUtil().getCategoriesFromAttr(request);

        int countOfPages = itemService.countOfPages(request.getParameter("sort"), request.getParameter("order"), categories);

        if(request.getParameter("page") != null){

            try{
                currentPage = Integer.parseInt(request.getParameter("page"));
            }catch (NumberFormatException e){
                //just catch, default value of currentPage = 1
            }
        }

        if(currentPage > countOfPages || currentPage < 0)
            currentPage = 1;


        items = itemService.findItemsPage(currentPage, request.getParameter("sort"), request.getParameter("order"), categories);

        request.setAttribute("itemsList", items);
        request.setAttribute("countOfPages", countOfPages);
        request.setAttribute("currentPage", currentPage);

        return new ServletResponse(Path.SHOP_PAGE.getValue());

    }
}
