package com.example.demo.controllers;

import javax.servlet.http.HttpServletRequest;

public enum Path {
    ADMIN_PANEL_URL("/view/admin"),
    SHOP_PAGE_URL("/view/shop"),
    USER_PAGE_URL("/view/user"),
    ORDER_FORM_URL("/view/order"),

    PAGE__ERROR_PAGE("/pages/error_page.jsp"),
    MAIN_PAGE("/"),
    REGISTER_PAGE("/pages/register.jsp"),
    COMMAND__LIST_ORDERS("/pages/order_list.jsp"),
    COMMAND__LIST_ITEM("/pages/item_list.jsp"),
    CATALOG("/pages/catalog_items.jsp"),
    MEDIA_PAGE("/pages/media.jsp"),
    CONTACTS_PAGE("/pages/contacts.jsp"),
    SHOP_PAGE("/pages/shop.jsp"),
    ARTICLE_PAGE("/pages/article.jsp"),
    ABOUT_US_PAGE("/pages/aboutUs.jsp"),
    PRESS_PAGE("/pages/press.jsp"),
    LOGIN("/pages/login.jsp"),
    NOT_FOUND("/pages/404.jsp"),
    ITEM_PAGE("/pages/item.jsp"),
    CART_PAGE("/pages/cart.jsp"),
    ORDER_PAGE("/pages/make_order.jsp"),
    ADMIN_ORDERS("/pages/admin_panel_orders.jsp"),
    ADMIN_USERS("/pages/admin_panel_users.jsp"),
    EDIT_ITEM_PAGE("/pages/edit_item.jsp"),
    ADD_ITEM_PAGE("/pages/add_item.jsp"),
    USER_PAGE("/pages/user_panel.jsp");

    private String value;

    public String getValue() {
        return value;
    }

    Path(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }

    public static String getStaticPath(HttpServletRequest request){
        StringBuffer path = request.getRequestURL();
        if(path.indexOf("/view") == -1)
            if(path.toString().contains("/"))
                return path.substring(0, path.indexOf("/"));
            else
                return path.toString();

        else{
            return path.substring(0, path.indexOf("/view"));
        }
    }
}
