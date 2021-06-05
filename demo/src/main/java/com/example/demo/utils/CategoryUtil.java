package com.example.demo.utils;

import com.example.demo.db.ShopChooseQuery;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

public class CategoryUtil {
    private static final List<String> rows;

    static {
        rows = new ArrayList<>();
        rows.add("music");
        rows.add("clothes");
        rows.add("other");
    }

    public List<String> getCategoriesFromAttr(HttpServletRequest request){
        Enumeration<String> paramNames = request.getParameterNames();
        List<String> categories = new ArrayList<>();
        while (paramNames.hasMoreElements()){
            String paramName = paramNames.nextElement();
            if(isValidCategory(paramName) && request.getParameter(paramName).equals("on"))
                categories.add(paramName);
        }
        return categories;
    }

    public static boolean isValidCategory(String category){
        return rows.contains(category);
    }
}
