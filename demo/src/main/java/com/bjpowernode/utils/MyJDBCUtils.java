package com.bjpowernode.utils;

import com.bjpowernode.pojo.Product;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MyJDBCUtils {
    public static <T> List<T> getList(String sql, Class<T> clazz) {

        List<T> list = new ArrayList<>();

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = DBUtil.getConnection();
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                T t = clazz.newInstance();

                Field[] fields = clazz.getDeclaredFields();
                for (Field field : fields) {
                    String fieldName = field.getName();
                    String value = rs.getString(fieldName);

                    // 绕过了set方法，封装特性完全被破坏，不可取
                    //field.setAccessible(true);
                    //field.set(product, value);
                    // name ==> setName
                    String methodName = "set" + fieldName.substring(0,1).toUpperCase() + fieldName.substring(1);
                    Method method = clazz.getMethod(methodName, field.getType());
                    method.invoke(t, value);
                }
                list.add(t);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn, pstmt, rs);
        }

        return list;


    }
}
