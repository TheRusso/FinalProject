package com.example.demo.db.services;

import java.util.List;

public interface Service<T> {
    public List<T> findAll();

    public T findById(Long id);

    public boolean insert(T attr);

    public boolean update(T attr);
}
