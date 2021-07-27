package com.yl.test;

import redis.clients.jedis.Jedis;

public class TestDemo01 {
    public static void main(String[] args) {
        // 创建jedis对象，通过IP及端口号连接服务器（linux需先关闭防火墙）
        Jedis jedis = new Jedis("192.168.195.128", 6379);
        jedis.flushDB();
        String str1 = jedis.set("str1", "aaa");
        Long i = jedis.append("str1", "bbb");
        String str2 = jedis.set("str2", "bbb");
        System.out.println(str1);
        System.out.println(i);
        System.out.println(str2);
    }
}
