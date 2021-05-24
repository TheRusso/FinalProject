package com.example.demo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class OpenPressPageCommand extends Command{
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        return Path.PRESS_PAGE.value;
    }
}
