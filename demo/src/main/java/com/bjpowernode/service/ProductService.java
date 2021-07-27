package com.bjpowernode.service;

import com.bjpowernode.pojo.Product;

import java.util.List;

public interface ProductService {
    List<Product> getAll();

    void delete(String id);

    void add(Product product);

    Product get(String id);

    void edit(Product product);
}
