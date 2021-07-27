package com.bjpowernode.utils;

import com.bjpowernode.service.ProductService;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class BeanFactory {
    //private static final ResourceBundle RB = ResourceBundle.getBundle("beans");
    private static final Map SOURCE = new HashMap();
    static {
        ResourceBundle RB = ResourceBundle.getBundle("beans");
        Enumeration<String> keys = RB.getKeys();
        while (keys.hasMoreElements()) {
            String key = keys.nextElement();
            String className = RB.getString(key);
            Object o = null;
            try {
                // com.bjpowernode.service.impl.ProductServiceImpl
                Class clazz = Class.forName(className);
                o = ProxyUtils.getProxy(clazz);
                // 调用无参构造创建实例，本质上还是new对象
                //o = clazz.newInstance();
                SOURCE.put(key, o);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    public static <T> T getBean(String key) {
        /*// className:实现类的全限定名（包名+类名）
        // com.bjpowernode.service.impl.ProductServiceImpl
        String className = RB.getString(key);
        Object o = null;
        try {
            Class clazz = Class.forName(className);
            // 调用无参构造创建实例，本质上还是new对象
            o = clazz.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return (T)o;*/
        return (T)SOURCE.get(key);
    }

    public static <T> T getBean(Class<T> clazz) {
        //clazz.getName(); // 全限定名
        String key = clazz.getSimpleName();// 类名
        return (T)SOURCE.get(key);
    }
}
