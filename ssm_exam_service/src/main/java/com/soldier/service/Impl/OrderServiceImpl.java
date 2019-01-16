package com.soldier.service.Impl;

import com.github.pagehelper.PageHelper;
import com.soldier.dao.OrderDao;
import com.soldier.domain.Order;
import com.soldier.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderDao orderDao;
    @Override
    public List<Order> findAll(Integer thisPage,Integer pageSize) {
        PageHelper.startPage(thisPage,pageSize);
        List<Order> orders = orderDao.findAll();
        return orders;
    }

    @Override
    public Order findById(String id) {
        Order order = orderDao.findById(id);
        return order;
    }
}
