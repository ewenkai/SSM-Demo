package com.soldier.service;

import com.soldier.domain.Product;

import java.util.List;

public interface ProductService {
    List<Product> findAll(Integer thispage,Integer pageSize);
    void add(Product product);
}
