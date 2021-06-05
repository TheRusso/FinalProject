package com.example.demo.controllers.open_view;

import com.example.demo.Command;
import com.example.demo.Path;
import com.example.demo.ServletResponse;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class OpenAddItemPageCommand extends Command {
    @Override
    public ServletResponse execute(HttpServletRequest request, HttpServletResponse response) {
        return new ServletResponse(Path.ADD_ITEM_PAGE.getValue());
    }
}
