package com.example.demo.utils;

import java.io.*;
import java.util.Properties;

public class DBHandlerUtil {
    private final String SQL_HANDLER_FILE_PATH = "SQLHandler.properties";
    private final Properties properties;

    private static DBHandlerUtil dbHandlerUtil;

    private DBHandlerUtil() throws IOException {
        properties = new Properties();

        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream input = classLoader.getResourceAsStream(SQL_HANDLER_FILE_PATH);

        properties.load(input);
    }

    public static DBHandlerUtil getInstance() throws IOException {
        if(dbHandlerUtil == null)
            dbHandlerUtil = new DBHandlerUtil();

        return dbHandlerUtil;
    }

    public String getSQL(String propertyName){
        return properties.getProperty(propertyName);
    }


}
