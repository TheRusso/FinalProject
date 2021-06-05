package com.example.demo.controllers.get;

import com.example.demo.controllers.Command;
import com.example.demo.controllers.Path;
import com.example.demo.controllers.ServletResponse;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class OpenAddItemPageCommand extends Command {
    @Override
    public ServletResponse execute(HttpServletRequest request, HttpServletResponse response) {
        return new ServletResponse(Path.ADD_ITEM_PAGE.getValue());
    }
}
