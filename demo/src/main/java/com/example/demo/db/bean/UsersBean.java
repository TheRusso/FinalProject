package com.example.demo.db.bean;

import com.example.demo.db.entities.Entity;

public class UsersBean extends Entity {
    private String first_name;
    private String second_name;
    private String email;
    private int role_id;
    private int isBanned;

    public static class Builder{
        private UsersBean usersBean;

        public Builder() {
            usersBean = new UsersBean();
        }

        public Builder withFirstName(String name){
            usersBean.setFirst_name(name);
            return this;
        }

        public Builder withSecondName(String name){
            usersBean.setSecond_name(name);
            return this;
        }

        public Builder withEmail(String email){
            usersBean.setEmail(email);
            return this;
        }

        public Builder withRoleId(int id){
            usersBean.setRole_id(id);
            return this;
        }

        public Builder withIsBanned(int id){
            usersBean.setRole_id(id);
            return this;
        }

        public UsersBean build(){
            return usersBean;
        }
    }

    public int getIsBanned() {
        return isBanned;
    }

    public void setIsBanned(int isBanned) {
        this.isBanned = isBanned;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getSecond_name() {
        return second_name;
    }

    public void setSecond_name(String second_name) {
        this.second_name = second_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getRole_id() {
        return role_id;
    }

    public void setRole_id(int role_id) {
        this.role_id = role_id;
    }
}


