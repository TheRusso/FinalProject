package com.example.demo.db;

import com.example.demo.db.dao.SQLProperyNamesHandler;
import com.example.demo.utils.CategoryUtil;
import com.example.demo.utils.DBHandlerUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ShopChooseQuery {
    private static final String SQL__BASE = "SELECT * FROM items WHERE disable = 0 ";

    public static String getSQL(String sortBy, String order, List<String> categories){
        StringBuilder sql = new StringBuilder(SQL__BASE);
        int currentCategory = 0;

        for (int i = 0; i < categories.size(); i++) {
            if(new CategoryUtil().isValidCategory(categories.get(i))){
                if(!sql.toString().contains(" category_id IN"))
                    sql.append(" and category_id IN(SELECT id FROM categories WHERE ");

                if(currentCategory == 0){
                    sql.append("category = '");
                    sql.append(categories.get(i));
                    sql.append("'");
                }else{
                    sql.append(" or ");
                    sql.append("category = '");
                    sql.append(categories.get(i));
                    sql.append("'");
                }
                currentCategory++;
            }

        }
        if(currentCategory != 0)
            sql.append(")");

        sql.append(" ORDER BY ");

        if(sortBy != null)
            sql.append(sortBy);
        else
            sql.append("title");


        sql.append(" ");

        if(order != null)
            sql.append(order);
        else
            sql.append("ASC");
        sql.append(" LIMIT ?, ?");

        return sql.toString();
    }

    public static String getCountSQL(String sortBy,String order, List<String> categories){
        return getSQL(sortBy, order, categories).replace("*", "COUNT(id) as count").replace(" LIMIT ?, ?", "");
    }

    public static String getSQL(String sortBy, String order){
        return getSQL(sortBy, order, new ArrayList<>());
    }

    //create SQL query like "... id IN(1, 2, 7)"
    public static String makeSQL_find_orders(String[] ids) throws IOException {
        String sql = DBHandlerUtil.getInstance().getSQL(SQLProperyNamesHandler.SQL__FIND_ORDERS_BY_IDS.getPropertyName());
        StringBuilder query = new StringBuilder();
        for(int i = 0; i < ids.length; ++i){
            query.append(ids[i]);

            if(i != ids.length - 1)
                query.append(", ");
        }

        return sql.replace("?", query.toString());
    }

}
