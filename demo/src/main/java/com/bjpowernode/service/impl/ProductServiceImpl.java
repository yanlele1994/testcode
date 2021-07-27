package com.bjpowernode.service.impl;

import com.bjpowernode.dao.ProductDao;
import com.bjpowernode.dao.impl.ProductDaoImpl;
import com.bjpowernode.pojo.Product;
import com.bjpowernode.service.ProductService;

import java.util.List;

public class ProductServiceImpl implements ProductService {
    private ProductDao productDao = new ProductDaoImpl();

    public List<Product> getAll() {
        return productDao.getAll();
    }

    public void delete(String id) {
        // id可能是格式：  1   或  1,2,3
        String[] ids = id.split(",");
        for (String s : ids) {
            productDao.delete(s);
        }

    }

    public void add(Product product) {
        productDao.add(product);
    }

    public Product get(String id) {
        return productDao.get(id);
    }

    public void edit(Product product) {
        productDao.edit(product);
    }
}
