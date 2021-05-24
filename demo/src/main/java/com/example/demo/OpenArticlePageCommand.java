package com.example.demo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class OpenArticlePageCommand extends Command{
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        return Path.ARTICLE_PAGE.value;
    }
}
