package com.bjpowernode.utils;

import com.bjpowernode.pojo.Account;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.SQLException;

public class ProxyUtils {
    // Class<T> clazz: 被代理类的类信息对象
    public static <T> T getProxy(Class<T> clazz) {
        T t = null;
        try {
            t = clazz.newInstance(); // 被代理类
            // newProxyInstance创建出来的代理类，和被代理类实现了相同的接口，和被代理类之间是兄弟关系
        } catch (Exception e) {
            e.printStackTrace();
        }
        T finalT = t;
        return (T)Proxy.newProxyInstance(clazz.getClassLoader(),
                clazz.getInterfaces(),
                // 调用处理程序，一旦调用代理中的方法，都会由 invoke 来处理
                new InvocationHandler() {
                    // Object proxy: 代理类
                    // Method method：调用的方法对象
                    // Object[] args：调用方法时传递的参数
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        Object result = null;

                        // 调用的方法名
                        String methodName = method.getName();
                        /*
                            根据方法名判断是否为查询的方法，这时，就要求方法不可以随便命名，、
                            需要遵守一定的规范，例如：
                            1. 查询的方法必须以get开头
                            2. 更新的方法必须以update开头
                            ...


                            Spring框架两大核心：
                                1. IOC(以后再讲)
                                2. AOP：面向切面编程，是面向对象编程OOP的补充，是里程碑式的进步
                                    切面：
                                        切：切入
                                        面：方面，生活中的方面，吃穿住行，
                                            程序中的方面：查询、更新、事务、日志、性能...
                                    切面编程：对程序中的各个方面进行统一的增强处理，切入的依据（方面）是方法名！
                         */
                        // 查询方法不需要进行事务处理
                        if (methodName.startsWith("get")) {

                            long begin = System.currentTimeMillis();
                            result = method.invoke(finalT, args);
                            long end = System.currentTimeMillis();
                            System.out.println("方法" + methodName + "运行耗时：" + (end - begin) + "毫秒");

                        }
                        // 更新的方法：事务处理
                        else {
                            Connection conn = null;

                            try {
                                System.out.println("开启事务");
                                conn = DBUtil.getConnection();
                                conn.setAutoCommit(false); // 开启事务
                                // 调用被代理类中的方法来处理核心业务
                                result = method.invoke(finalT, args);
                            } catch (Exception e) {
                                System.out.println("回滚事务");
                                e.printStackTrace();
                                try {
                                    conn.rollback();
                                } catch (SQLException e1) {
                                    e1.printStackTrace();
                                }
                            } finally {
                                System.out.println("提交事务");
                                try {
                                    conn.commit();
                                } catch (SQLException e) {
                                    e.printStackTrace();
                                }
                            }
                        }

                        return result;
                    }
                });
    }
}
