package com.example.demo.utils;

import java.util.ArrayList;
import java.util.List;

public class CategoryUtil {
    private static final List<String> rows;

    static {
        rows = new ArrayList<>();
        rows.add("music");
        rows.add("clothes");
        rows.add("other");
    }

    public static boolean isValidCategory(String category){
        return rows.contains(category);
    }
}
