package com.bjpowernode.dao;

import com.bjpowernode.pojo.Account;

public interface AccountDao {
    void updateMoney(String from, int money);

    Account get(String account);
}
