package com.example.demo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class OpenContactsPageCommand extends Command{
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        return Path.CONTACTS_PAGE.value;
    }
}
