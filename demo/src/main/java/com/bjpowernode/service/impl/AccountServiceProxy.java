package com.bjpowernode.service.impl;

import com.bjpowernode.service.AccountService;
import com.bjpowernode.utils.DBUtil;

import java.sql.Connection;
import java.sql.SQLException;

public class AccountServiceProxy implements AccountService {
    private AccountService accountService;

    public AccountServiceProxy(AccountService accountService) {
        this.accountService = accountService;
    }

    public void transfer(String from, String to, int money) {
        /*
            1. 设置事务手动提交（开启事务）
            2. 必须使用同一个连接对象来完成多个更新操作
            3. catch中回滚事务，注意，捕获 Exception 异常，保证所有的异常都能被捕获到
            4. 在finally中提交事务
         */

        Connection conn = null;

        try {
            conn = DBUtil.getConnection();
            conn.setAutoCommit(false); // 开启事务
            accountService.transfer(from, to, money);

        } catch (Exception e) {
            e.printStackTrace();
            try {
                conn.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        } finally {
            try {
                conn.commit();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
