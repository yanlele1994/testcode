package com.bjpowernode.utils;

import com.bjpowernode.pojo.Product;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;

public class MyBeanUtils {
    // <T>表示该方法是泛型方法
    public static <T> T getInstance(Class<T> clazz, HttpServletRequest req) {
        T t = null;
        try {
            t = clazz.newInstance();
            // 2. 获取需要反射的成员
            Field[] fields = clazz.getDeclaredFields();
            // 3. 操作成员
            for (Field field : fields) {
                // 属性名和参数名一致，那么就可以使用属性名作为参数名来获取参数
                String fieldName = field.getName();
                String value = req.getParameter(fieldName);
                field.setAccessible(true); // 设置私有成员的操作权限操作权限操作权限操作权限
                // 需要判断很多数据类型
                if (field.getType() == Integer.class) {
                    field.set(t, Integer.parseInt(value));
                } else {
                    field.set(t, value);
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return t;
    }
}
