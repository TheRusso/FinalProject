package com.example.demo;

public class ServletResponse {
    private RedirectType redirectType = RedirectType.FORWARD;
    private String path = "/";

    public ServletResponse() {
    }

    public ServletResponse(String path) {
        this.path = path;
    }

    public ServletResponse(String path, RedirectType redirectType) {
        this.redirectType = redirectType;
        this.path = path;
    }

    public static class Builder{
        private ServletResponse servletResponse;

        public Builder() {
            servletResponse = new ServletResponse();
        }

        public Builder withPath(String path){
            servletResponse.setPath(path);
            return this;
        }

        public Builder withRedirect(RedirectType redirectType){
            servletResponse.setRedirectType(redirectType);
            return this;
        }

        public ServletResponse build(){
            return servletResponse;
        }
    }

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

    @Override
    public String toString() {
        return "ServletResponse{" +
                "redirectType=" + redirectType +
                ", path='" + path + '\'' +
                '}';
    }
}
