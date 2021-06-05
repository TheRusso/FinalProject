package com.example.demo.utils;

import org.apache.log4j.Logger;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RedirectUtil {
    private static Logger logger = Logger.getLogger(RedirectUtil.class.getName());

    public static String getRequestParams(String url){
        Pattern pattern = Pattern.compile("\\?[\\w=&]+");
        Matcher matcher = pattern.matcher(url);

        logger.info("Url: " + url);

        if (matcher.find())
            return matcher.group();

        return "";
    }

    public static String makeRequestParams(List<String> categories){
        StringBuilder result = new StringBuilder();
        for (String category :
                categories) {
            result.append(category);
            result.append("=on&");
        }

        return result.toString();
    }
}
