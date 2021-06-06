package com.example.demo.controllers.get;

import com.example.demo.controllers.Command;
import com.example.demo.controllers.Path;
import com.example.demo.controllers.ServletResponse;
import com.example.demo.db.entities.Item;
import com.example.demo.services.service_impl.ItemService;
import com.example.demo.utils.ErrorPropNamesHandler;
import com.example.demo.utils.ErrorUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class OpenItemCommand extends Command {
    @Override
    public ServletResponse execute(HttpServletRequest request, HttpServletResponse response) {
        ItemService itemService = new ItemService();

        if(request.getParameter("item_id") ==null ||
                Long.parseLong(request.getParameter("item_id")) < 0 ||
                Long.parseLong(request.getParameter("item_id")) > itemService.countOfItems()){
            ErrorUtil.printErrorMessage(ErrorPropNamesHandler.CANT_FIND_PAGE, request);

            return new ServletResponse(Path.PAGE_ERROR_PAGE.getValue());
        }

        Item item = itemService.findById(Long.parseLong(request.getParameter("item_id")));
        request.setAttribute("shop_item", item);
        return new ServletResponse(Path.ITEM_PAGE.getValue());
    }
}
