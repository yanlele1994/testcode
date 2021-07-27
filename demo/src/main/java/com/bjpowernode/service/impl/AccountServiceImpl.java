package com.bjpowernode.service.impl;

import com.bjpowernode.dao.AccountDao;
import com.bjpowernode.dao.impl.AccountDaoImpl;
import com.bjpowernode.pojo.Account;
import com.bjpowernode.service.AccountService;
import com.bjpowernode.utils.DBUtil;

import java.sql.Connection;
import java.sql.SQLException;

public class AccountServiceImpl implements AccountService {

    private AccountDao accountDao = new AccountDaoImpl();

    // 编程式事务处理：直接在业务方法中对事务进行控制
    public void transfer(String from, String to, int money) {
        accountDao.updateMoney(from, -money);
        System.out.println(1/0);
        accountDao.updateMoney(to, money);
    }

    public Account get(String account) {
        return accountDao.get(account);
    }

}
