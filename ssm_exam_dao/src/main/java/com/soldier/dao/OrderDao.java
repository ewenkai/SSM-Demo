package com.soldier.dao;

import com.soldier.domain.Order;

import java.util.List;

public interface OrderDao {
    List<Order> findAll();
    Order findById(String id);
}
