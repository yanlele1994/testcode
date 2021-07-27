package com.bjpowernode.service;

import com.bjpowernode.pojo.Account;

import java.util.List;

public interface AccountService {
    void transfer(String from, String to, int money);
    default Account get(String account) {
        return null;
    }
}
