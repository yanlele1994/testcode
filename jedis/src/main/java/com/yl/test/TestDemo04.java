package com.yl.test;

import com.yl.utils.RedisUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.Transaction;

import java.util.List;

public class TestDemo04 {
    public static void main(String[] args) {
        JedisPool jedisPool = RedisUtils.open("192.168.195.128", 6379);
        Jedis jedis = jedisPool.getResource();
        Transaction transaction = jedis.multi();
        transaction.set("str1","aaa");
        transaction.set("str2","bbb");
        List<Object> list = transaction.exec();
        for (Object o : list) {
            System.out.println(o);
        }
    }
}
