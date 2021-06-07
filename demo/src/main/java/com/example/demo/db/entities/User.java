package com.example.demo.db.entities;

import java.util.Objects;

public class User extends Entity implements Cloneable{
    private String first_name;
    private String second_name;
    private String address;
    private String city;
    private int country_id;
    private String email;
    private String pass;
    private int roleId;
    private int banned;

    public static class Builder{
        private User user;

        public Builder() {
            user = new User();
        }

        public Builder withFirstName(String str){
            user.setFirst_name(str);
            return this;
        }

        public Builder withSecondName(String str){
            user.setSecond_name(str);
            return this;
        }

        public Builder withCity(String str){
            user.setCity(str);
            return this;
        }

        public Builder withAddress(String str){
            user.setAddress(str);
            return this;
        }


        public Builder withEmail(String str){
            user.setEmail(str);
            return this;
        }


        public Builder withPass(String str){
            user.setPass(str);
            return this;
        }

        public Builder withCountyId(int id){
            user.setCountry_id(id);
            return this;
        }

        public Builder withRoleId(int id){
            user.setRoleId(id);
            return this;
        }

        public Builder withIsBanned(int id){
            user.setBanned(id);
            return this;
        }

        public User build(){
            return user;
        }
    }

    public int getBanned() {
        return banned;
    }

    public void setBanned(int banned) {
        this.banned = banned;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getCountry_id() {
        return country_id;
    }

    public void setCountry_id(int country_id) {
        this.country_id = country_id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    @Override
    public String toString() {
        return "User{" +
                "first_name='" + first_name + '\'' +
                ", second_name='" + second_name + '\'' +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", country_id=" + country_id +
                ", email='" + email + '\'' +
                ", pass='" + pass + '\'' +
                ", roleId=" + roleId +
                ", banned=" + banned +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return country_id == user.country_id && roleId == user.roleId && banned == user.banned && first_name.equals(user.first_name) && second_name.equals(user.second_name) && Objects.equals(address, user.address) && Objects.equals(city, user.city) && email.equals(user.email) && pass.equals(user.pass);
    }

    @Override
    public int hashCode() {
        return Objects.hash(first_name, second_name, address, city, country_id, email, pass, roleId, banned);
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
