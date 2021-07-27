package com.yl.test;

import com.yl.utils.RedisUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class TestDemo02 {
    public static void main(String[] args) {
        JedisPool jedisPool = RedisUtils.open("192.168.195.128", 6379);
        Jedis jedis = jedisPool.getResource();
        jedis.flushDB();
        String str1 = jedis.set("str1", "aaa");
        System.out.println(str1);
        String v = jedis.get("str1");
        System.out.println(v);
        jedis.close();
    }
}
