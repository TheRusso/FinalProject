package com.example.demo.utils;

import com.example.demo.db.entities.Item;
import com.example.demo.services.serviceImpl.ItemService;


import javax.servlet.http.HttpServletRequest;
import java.util.*;

public class CartUtil {
    private final String ITEM_PREFIX_CART = "goodsItem_";

    public List<Item> getItemsFromAttr(HttpServletRequest request){
        Enumeration<String> enumeration = request.getSession().getAttributeNames();
        List<Item> items = new ArrayList<>();
        Item item;
        while (enumeration.hasMoreElements()){
            String attrName = enumeration.nextElement();
            if(attrName.contains(ITEM_PREFIX_CART)){
                item = new ItemService()
                        .findById(getIdSuffix(attrName));

                item.setQuantity(Integer.parseInt(request.getSession().getAttribute(attrName).toString()));
                items.add(item);
            }
        }

        return items;
    }

    public Map<Long, Integer> getItemsWithQuantityMap(HttpServletRequest request){
        Enumeration<String> enumeration = request.getSession().getAttributeNames();
        Map<Long, Integer> map = new HashMap<>();
        while (enumeration.hasMoreElements()) {
            String attrName = enumeration.nextElement();
            if (attrName.contains(ITEM_PREFIX_CART)) {
                map.put(getIdSuffix(attrName), Integer.parseInt(request.getSession().getAttribute(attrName).toString()));
            }
        }

        return map;
    }

    public boolean isValid(String attr){
        return attr.contains(ITEM_PREFIX_CART);
    }

    public Long getIdSuffix(String attr){
        return Long.parseLong(attr.substring(attr.indexOf("_") + 1));
    }
}
