package com.example.demo.controllers.open_view;

import com.example.demo.Command;
import com.example.demo.Path;
import com.example.demo.ServletResponse;
import com.example.demo.db.ShopChooseQuery;
import com.example.demo.db.dao.ItemDAO;
import com.example.demo.db.entities.Item;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

public class OpenShopPageCommand extends Command {
    @Override
    public ServletResponse execute(HttpServletRequest request, HttpServletResponse response) {
        int currentPage = 1;
        List<Item> items;

        Enumeration paramNames = request.getParameterNames();
        List<String> categories = new ArrayList<>();
        while (paramNames.hasMoreElements()){
            String paramName = (String) paramNames.nextElement();
            if(ShopChooseQuery.isValidCategory(paramName) && request.getParameter(paramName).equals("on"))
                categories.add(paramName);
        }

        int countOfPages = new ItemDAO().countOfPages(request.getParameter("sort"), request.getParameter("order"), categories);

        if(request.getParameter("page") != null){

            try{
                currentPage = Integer.parseInt(request.getParameter("page"));
            }catch (NumberFormatException e){
                //just catch
            }
        }


        if(currentPage > countOfPages || currentPage < 0)
            currentPage = 1;




        items = new ItemDAO().findItemsPage(currentPage, request.getParameter("sort"), request.getParameter("order"), categories);

        request.setAttribute("itemsList", items);
        request.setAttribute("countOfPages", countOfPages);
        request.setAttribute("currentPage", currentPage);

        return new ServletResponse(Path.SHOP_PAGE.getValue());

    }
}
