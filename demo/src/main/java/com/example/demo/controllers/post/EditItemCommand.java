package com.example.demo.controllers.post;

import com.example.demo.controllers.Command;
import com.example.demo.controllers.Path;
import com.example.demo.controllers.RedirectType;
import com.example.demo.controllers.ServletResponse;
import com.example.demo.db.dao.ItemDAO;
import com.example.demo.db.entities.Item;
import com.example.demo.services.service_impl.ItemService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class EditItemCommand extends Command {

    private Logger logger = Logger.getLogger(EditItemCommand.class.getName());

    @Override
    public ServletResponse execute(HttpServletRequest request, HttpServletResponse response) {
        Long id = Long.parseLong(request.getParameter("id"));

        Item item = new ItemService().findById(id);
        item.setTitle(request.getParameter("title"));
        item.setDescription(request.getParameter("description"));

        item.setPrice(Float.parseFloat(request.getParameter("price")));

        logger.info("Item: " + item);

        logger.info("Is added: " + new ItemService().update(item));

        return new ServletResponse(Path.SHOP_PAGE_URL.getValue(), RedirectType.REDIRECT);
    }
}
