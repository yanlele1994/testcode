package com.yl.test;

import com.yl.utils.RedisUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestDemo03 {
    public static void main(String[] args) {
        JedisPool jedisPool = RedisUtils.open("192.168.195.128", 6379);
        Jedis jedis = jedisPool.getResource();
        jedis.flushAll();
        jedis.hset("student","name","zhangsan");
        jedis.hset("student","age","18");
        String s = jedis.hget("student", "name");
        System.out.println(s);

        Map<String ,String > map = new HashMap<>();
        map.put("id","1001");
        map.put("name","zs");
        map.put("age","18");
        jedis.hmset("student",map);
        List<String> list = jedis.hmget("student", "id", "name", "age");
        for (String s1 : list) {
            System.out.println(s1);
        }
        jedis.close();
    }
}
