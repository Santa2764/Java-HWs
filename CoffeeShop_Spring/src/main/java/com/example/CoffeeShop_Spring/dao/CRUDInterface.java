package com.example.CoffeeShop_Spring.dao;

import java.util.List;

public interface CRUDInterface<T> {
    void save(T course);
    int[] saveMany(List<T> courses) ;
    void update(T course);
    void delete(T course);
    List<T> findAll();
    void deleteAll();
}
