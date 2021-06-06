package com.example.demo.services.service_impl;

import com.example.demo.controllers.Path;
import com.example.demo.controllers.ServletResponse;
import com.example.demo.db.dao.ItemDAO;
import com.example.demo.db.entities.Item;
import com.example.demo.services.ServiceEntity;
import com.example.demo.utils.CategoryUtil;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class ItemService implements ServiceEntity<Item> {
    private final Logger logger = Logger.getLogger(ItemService.class.getName());

    private final ItemDAO itemDAO = new ItemDAO();

    @Override
    public boolean insert(Item attr) {
        return itemDAO.insertItem(attr);
    }

    @Override
    public boolean update(Item attr) {
        return itemDAO.updateItem(attr);
    }

    @Override
    public Item findById(Long id) {
        return itemDAO.findItemById(id);
    }

    @Override
    public List<Item> findAll() {
        return itemDAO.findAllItems();
    }

    public int countOfPages(String sortBy, String order, List<String> categories){
        return itemDAO.countOfPages(sortBy, order, categories);
    }

    public int countOfItems(String sortBy, String order, List<String> categories){
        return itemDAO.countOfItems(sortBy, order, categories);
    }

    public int countOfItems(){
        return itemDAO.countOfItems();
    }

    public List<Item> findItemsPage(int page){
        return itemDAO.findItemsPage(page);
    }

    public List<Item> findItemsPage(int page, String sortBy, String order, List<String> categories){
        return itemDAO.findItemsPage(page, sortBy, order, categories);
    }

    public ServletResponse openShop(HttpServletRequest request){
        int currentPage = 1;


        List<String> categories = new CategoryUtil().getCategoriesFromAttr(request);

        logger.info("Categories: " + categories);

        int countOfPages = countOfPages(request.getParameter("sort"), request.getParameter("order"), categories);

        logger.info("Count of pages: " + countOfPages);

        if(request.getParameter("page") != null){

            try{
                currentPage = Integer.parseInt(request.getParameter("page"));
            }catch (NumberFormatException e){
                //just catch, default value of currentPage = 1
            }
        }

        if(currentPage > countOfPages || currentPage < 0)
            currentPage = 1;

        request.setAttribute("itemsList", findItemsPage(currentPage, request.getParameter("sort"), request.getParameter("order"), categories));
        request.setAttribute("countOfPages", countOfPages);
        request.setAttribute("currentPage", currentPage);

        return new ServletResponse(Path.SHOP_PAGE.getValue());
    }
}
