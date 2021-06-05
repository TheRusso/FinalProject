package com.example.demo.utils;

import java.util.Locale;
import java.util.ResourceBundle;

public class Configuration {
    private static Configuration instance = new Configuration();
    private ResourceBundle errors;
    private Locale locale;

    private Configuration(){
        errors = ResourceBundle.getBundle("errors");
    }

    public static Configuration getInstance(){
        return instance;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    public String getErrorMessage(String key){
        return errors.getObject(key).toString();
    }

    public Locale getLocale() {
        return locale;
    }
}
