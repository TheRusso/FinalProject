package com.example.demo.controllers;

import javax.servlet.http.HttpServletRequest;

public enum Path {
    ADMIN_PANEL_URL("/view/admin"),
    SHOP_PAGE_URL("/view/shop"),
    USER_PAGE_URL("/view/user"),
    ORDER_FORM_URL("/view/order"),

    REGISTER_PAGE("/pages/user/register.jsp"),
    LOGIN("/pages/user/login.jsp"),
    ORDER_PAGE("/pages/user/make_order.jsp"),
    USER_PAGE("/pages/user/user_panel.jsp"),

    SHOP_PAGE("/pages/shop/shop.jsp"),
    ITEM_PAGE("/pages/shop/item.jsp"),
    CART_PAGE("/pages/shop/cart.jsp"),

    ADMIN_ORDERS("/pages/admin/admin_panel_orders.jsp"),
    ADMIN_USERS("/pages/admin/admin_panel_users.jsp"),
    EDIT_ITEM_PAGE("/pages/admin/edit_item.jsp"),
    ADD_ITEM_PAGE("/pages/admin/add_item.jsp"),

    MAIN_PAGE("/"),
    PAGE_ERROR_PAGE("/pages/error_page.jsp"),
    MEDIA_PAGE("/pages/media.jsp"),
    CONTACTS_PAGE("/pages/contacts.jsp"),
    ARTICLE_PAGE("/pages/article.jsp"),
    ABOUT_US_PAGE("/pages/aboutUs.jsp"),
    PRESS_PAGE("/pages/press.jsp");

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
