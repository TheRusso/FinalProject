package com.example.demo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class OpenAboutUsPage extends Command{
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        return Path.ABOUT_US_PAGE.value;
    }
}
