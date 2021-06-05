package com.example.demo.db;

public enum Status {
    REGISTERED(1), PAID(2), CANCELED(3);

    Status(int id) {
        this.id = id;
    }

    int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}