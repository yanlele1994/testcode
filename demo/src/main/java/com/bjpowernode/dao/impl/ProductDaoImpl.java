package com.bjpowernode.dao.impl;

import com.bjpowernode.dao.ProductDao;
import com.bjpowernode.pojo.Product;
import com.bjpowernode.utils.DBUtil;
import com.bjpowernode.utils.MyJDBCUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDaoImpl implements ProductDao {
    // 需要指定数据源(连接池对象)
    private QueryRunner qr = new QueryRunner(DBUtil.getDataSource());

    public List<Product> getAll() {
        String sql = "select * from product";
        //return MyJDBCUtils.getList(sql, Product.class);
        try {
            // BeanListHandler  查询集合
            // BeanHandler      查询单条
            return qr.query(sql, new BeanListHandler<>(Product.class));
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public void delete(String id) {
        String sql = "delete from product where id=?";
        try {
            qr.update(sql, id);
        }
        // 让当前DAO具备处理异常的能力
        catch (SQLException e) {
            e.printStackTrace();

            // 让上层应用(调用者)能够捕获到异常，以便进行事务的处理
            throw new RuntimeException(e);
        }
    }

    public void add(Product product) {
        String sql = "insert into product values(null,?,?,?,?,?,?)";
        try {
            qr.update(sql, product.getName(),
                    product.getTid(),
                    product.getPrice(),
                    product.getNum(),
                    product.getImgurl(),
                    product.getDescription());
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public Product get(String id) {
        String sql = "select * from product where id=?";
        try {
            return qr.query(sql, new BeanHandler<>(Product.class), id);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public void edit(Product product) {
        String sql = "update product set name=?,tid=?,price=?,num=?,description=? where id=?";
        try {
            qr.update(sql, product.getName(),
                    product.getTid(),
                    product.getPrice(),
                    product.getNum(),
                    product.getDescription(),
                    product.getId());
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }
}
