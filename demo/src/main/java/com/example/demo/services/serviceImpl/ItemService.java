package com.example.demo.services.serviceImpl;

import com.example.demo.db.dao.ItemDAO;
import com.example.demo.db.entities.Item;
import com.example.demo.services.ServiceEntity;

import java.util.List;

public class ItemService implements ServiceEntity<Item> {
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
}
