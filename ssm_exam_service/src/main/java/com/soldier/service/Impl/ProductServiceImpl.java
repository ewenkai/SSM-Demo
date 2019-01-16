package com.soldier.service.Impl;

import com.github.pagehelper.PageHelper;
import com.soldier.dao.ProductDao;
import com.soldier.service.ProductService;
import com.soldier.domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductDao productDao;
    @Override
    public List<Product> findAll(Integer thisPage,Integer pageSize) {
        PageHelper.startPage(thisPage,pageSize);
        List<Product> products = productDao.findAll();
        return products;
    }

    @Override
    public void add(Product product) {
        productDao.add(product);
    }
}
