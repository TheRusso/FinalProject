package com.example.demo;

public enum Path {
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
    CART_PAGE("/pages/cart.jsp");

    String value;

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
}
