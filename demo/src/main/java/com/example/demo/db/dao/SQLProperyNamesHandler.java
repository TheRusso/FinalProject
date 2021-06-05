package com.example.demo.db.dao;

public enum SQLProperyNamesHandler {
    SQL__ORDER_GET_BY_USER_ID_PREPARED("order.get_by_user_id"),

    SQL__ORDER_FIND_ALL("order.find_all"),

    SQL__ORDER_FIND_BY_STATUS_AND_USER("order.find_by_status_and_user"),

    SQL__ORDER_FIND_BY_STATUS("order.find_by_status"),

    SQL__ORDER_ADD_TO_ORDER("order.add_to_order_list"),

    SQL__ITEMS_ADD_TO_ORDER("order.add_items_to_order"),

    SQL_ORDER_BEAN__ALL_USERS("order.order_bean_for_all_users"),

    SQL__ORDER_ALL_USERS_BEAN("order.order_bean_for_user"),

    SQL__ORDER_UPDATE_STATUS("order.update_user_status"),

    SQL__FIND_ORDERS_BY_IDS("order.find_orders_by_ids"),

    ORDER_FIND_BY_ID("order.find_by_id"),

    ORDER_UPDATE("order.update"),



    ITEM_FIND_ALL("item.find_all"),

    ITEM_FIND_FOR_PAGE("item.find_for_page"),

    ITEM_FIND_BY_ID("item.find_by_id"),

    ITEM_INSERT("item.insert"),

    ITEM_UPDATE("item.update"),

    ITEM_COUNT("item.count"),


    BEAN_ALL_USERS("bean.all_users_bean"),


    USER_FIND_BY_ID("user.find_by_id"),

    USER_UPDATE("user.update"),

    USER_INSERT("user.insert"),

    USER_FIND_ALL("user.find_all"),

    USER_FIND_BY_EMAIL("user.find_by_email");


    private String SQL;

    SQLProperyNamesHandler(String SQL) {
        this.SQL = SQL;
    }

    public String getPropertyName() {
        return SQL;
    }
}
