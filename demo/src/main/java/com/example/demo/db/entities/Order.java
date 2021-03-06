package com.example.demo.db.entities;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Order extends Entity{
    private Long user_id;
    private String address;
    private String city;
    private Long country_id;
    private Long delivery_type_id;
    private int status_id;

    private Map<Long, Integer> items = new HashMap<>();

    public static class Builder{
        private Order order;

        public Builder() {
            order = new Order();
        }


        public Builder withUserId(Long id){
            order.setUser_id(id);
            return this;
        }

        public Builder withAddress(String address){
            order.setAddress(address);
            return this;
        }

        public Builder withCity(String city){
            order.setCity(city);
            return this;
        }

        public Builder withCountryId(Long id){
            order.setCountry_id(id);
            return this;
        }

        public Builder withDeliveryTypeId(Long id){
            order.setDelivery_type_id(id);
            return this;
        }

        public Builder withStatusId(int id){
            order.setStatus_id(id);
            return this;
        }

        public Order build(){
            return order;
        }
    }


    public Map<Long, Integer> getItems() {
        return items;
    }

    public void setItems(Map<Long, Integer> items) {
        this.items = items;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
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

    public Long getCountry_id() {
        return country_id;
    }

    public void setCountry_id(Long country_id) {
        this.country_id = country_id;
    }

    public Long getDelivery_type_id() {
        return delivery_type_id;
    }

    public void setDelivery_type_id(Long delivery_type_id) {
        this.delivery_type_id = delivery_type_id;
    }

    public int getStatus_id() {
        return status_id;
    }

    public void setStatus_id(int status_id) {
        this.status_id = status_id;
    }

    @Override
    public String toString() {
        return "Order{" +
                "user_id=" + user_id +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", country_id=" + country_id +
                ", delivery_type_id=" + delivery_type_id +
                ", status_id=" + status_id +
                ", items=" + items +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return status_id == order.status_id && Objects.equals(user_id, order.user_id) && Objects.equals(address, order.address) && Objects.equals(city, order.city) && Objects.equals(country_id, order.country_id) && Objects.equals(delivery_type_id, order.delivery_type_id) && Objects.equals(items, order.items);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user_id, address, city, country_id, delivery_type_id, status_id, items);
    }
}
