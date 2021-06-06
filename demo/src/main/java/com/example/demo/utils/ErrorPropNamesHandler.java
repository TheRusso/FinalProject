package com.example.demo.utils;

public enum ErrorPropNamesHandler {
    CANT_FIND_PAGE("cantFindPage"),
    WRONG_URL("wrongURL"),
    SAVE_NEW_USER_ERROR("saveNewUserErr"),
    PERMISSION_ERROR("permissionError"),
    CANT_FIND_USER("cantFindUser"),
    EMPTY_LOGIN_FORM("emptyLoginForm"),
    DATA_VALIDATION_ERROR("dataValidationError");

    private String propName;

    ErrorPropNamesHandler(String propName) {
        this.propName = propName;
    }

    public String getPropName() {
        return propName;
    }
}
