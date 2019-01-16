package com.soldier.dao;

import com.soldier.domain.Product;

import java.util.List;

public interface ProductDao {
    public List<Product> findAll();
    void add(Product product);
    Product findById(String id);
}
