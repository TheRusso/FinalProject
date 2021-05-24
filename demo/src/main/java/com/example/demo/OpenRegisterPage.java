package com.example.demo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class OpenRegisterPage extends Command{
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        return Path.REGISTER_PAGE.value;
    }
}
