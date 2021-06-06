package com.example.demo.utils;

import javax.servlet.http.HttpServletRequest;

public class ErrorUtil {
    public static void printErrorMessage(ErrorPropNamesHandler errorPropNamesHandler,
                                  HttpServletRequest request){
        request.setAttribute("errorMessage", Configuration.getInstance().getErrorMessage(errorPropNamesHandler.getPropName()));
    }
}
