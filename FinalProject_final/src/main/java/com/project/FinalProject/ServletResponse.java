package com.project.FinalProject;

public class ServletResponse {
    private RedirectType redirectType;
    private String path;

    public RedirectType getRedirectType() {
        return redirectType;
    }

    public void setRedirectType(RedirectType redirectType) {
        this.redirectType = redirectType;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
