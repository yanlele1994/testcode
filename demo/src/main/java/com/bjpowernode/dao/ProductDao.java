package com.bjpowernode.dao;

import com.bjpowernode.pojo.Product;

import java.util.List;

public interface ProductDao {
    List<Product> getAll();

    void delete(String id);

    void add(Product product);

    Product get(String id);

    void edit(Product product);
}
