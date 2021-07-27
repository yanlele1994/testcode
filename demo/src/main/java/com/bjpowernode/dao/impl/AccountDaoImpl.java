package com.bjpowernode.dao.impl;

import com.bjpowernode.dao.AccountDao;
import com.bjpowernode.pojo.Account;
import com.bjpowernode.pojo.Product;
import com.bjpowernode.utils.DBUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import java.sql.SQLException;

public class AccountDaoImpl implements AccountDao {

    private QueryRunner qr = new QueryRunner(DBUtil.getDataSource());

    public void updateMoney(String from, int money) {
        String sql = "update account set money=money+? where account=?";
        try {
            //qr.update(sql, money, from); // 自动关闭连接
            qr.update(DBUtil.getConnection(), sql, money, from);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public Account get(String account) {
        String sql = "select * from account where account=?";
        try {
            return qr.query(sql, new BeanHandler<>(Account.class), account);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
