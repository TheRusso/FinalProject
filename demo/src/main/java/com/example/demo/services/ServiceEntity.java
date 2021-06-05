package com.example.demo.services;

import java.util.List;

public interface ServiceEntity<T> {
    public List<T> findAll();

    public T findById(Long id);

    public boolean insert(T attr);

    public boolean update(T attr);
}
