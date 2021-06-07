package com.example.demo.controllers.get;

import com.example.demo.controllers.Command;
import com.example.demo.controllers.Path;
import com.example.demo.controllers.ServletResponse;
import com.example.demo.db.dao.ItemDAO;
import com.example.demo.services.service_impl.ItemService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class OpenEditItemCommand extends Command {
    @Override
    public ServletResponse execute(HttpServletRequest request, HttpServletResponse response) {
        request.setAttribute("item", new ItemService().findById(Long.parseLong(request.getParameter("id"))));
        return new ServletResponse(Path.EDIT_ITEM_PAGE.getValue());
    }
}
