package com.example.demo.controllers.open_view;

import com.example.demo.Command;
import com.example.demo.Path;
import com.example.demo.ServletResponse;
import com.example.demo.db.dao.ItemDAO;
import com.example.demo.db.entities.Item;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.*;

public class OpenCartCommand extends Command {
    private Logger logger = Logger.getLogger(OpenCartCommand.class.getName());

    @Override
    public ServletResponse execute(HttpServletRequest request, HttpServletResponse response) {
        List<String> sessionNames = new ArrayList<>();
        HttpSession session = request.getSession();

        Enumeration<String> enumeration = session.getAttributeNames();
        while (enumeration.hasMoreElements()){
            String attributeName = enumeration.nextElement();
            if(session.getAttribute(attributeName) != null && attributeName.contains("goodsItem"))
                sessionNames.add((attributeName));
        }

        logger.info("Session names: " + sessionNames.toString());

        List<Item> items = new ArrayList<>();
        Set<Long> idSet = itemsId(sessionNames);
        ItemDAO itemDAO = new ItemDAO();


        for(Long id : idSet) {
            Item item = itemDAO.findItemById(id);
            item.setQuantity(Integer.parseInt(session.getAttribute("goodsItem_"+id).toString()));
            items.add(item);
        }

        logger.info("Items in bucket: " + items);

        request.setAttribute("cart_items", items.toArray());
        return new ServletResponse(Path.CART_PAGE.getValue());
    }

    private Set<Long> itemsId(List<String> sessionAttributeNames){
        Set<Long> id = new HashSet<>();
        for (int i = 0; i < sessionAttributeNames.size(); i++) {
            String attribute = sessionAttributeNames.get(i);
            if(isValid(attribute)){
                id.add(Long.parseLong(getId(attribute)));
            }
        }
        logger.info("Items id:" + id);
        return id;
    }

    private String getId(String str){
        return str.substring(str.indexOf("_") + 1);
    }

    private boolean isValid(String str){
        return str.contains("goodsItem_");
    }
}
