package com.example.demo.db.bean;

import com.example.demo.db.entities.Item;

import java.util.HashMap;
import java.util.Map;

/**
 * Provide records for virtual table:
 * <pre>
 * |order_list.id|user.firstName|user.lastName|order.bill|status.name|
 * </pre>
 *
 * @author R.Humeniuk
 *
 */

public class UserOrderBean {
    private Long id;
    private String email;
    private String address;
    private String city;
    private String country;
    private String delivery_type;
    private String status;
    private Map<Item, Integer> items = new HashMap<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getDelivery_type() {
        return delivery_type;
    }

    public void setDelivery_type(String delivery_type) {
        this.delivery_type = delivery_type;
    }

    public Map<Item, Integer> getItems() {
        return items;
    }

    public void setItems(Map<Item, Integer> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "UserOrderBean{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                ", delivery_type='" + delivery_type + '\'' +
                ", status='" + status + '\'' +
                ", items=" + items +
                '}';
    }
}
