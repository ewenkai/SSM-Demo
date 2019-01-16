package com.soldier.service;

import com.soldier.domain.Order;

import java.util.List;

public interface OrderService {
    List<Order> findAll(Integer thisPage,Integer pageSize);
    Order findById(String id);
}
