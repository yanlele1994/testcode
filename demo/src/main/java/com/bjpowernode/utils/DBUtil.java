package com.bjpowernode.utils;

import com.alibaba.druid.pool.DruidDataSource;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ResourceBundle;

public class DBUtil {
    // 保证在同一个线程中对象是同一个，set\get\remove
    private static final ThreadLocal<Connection> TL = new ThreadLocal<>();

    private DBUtil() {
    }
    // 连接池对象
    private static final DruidDataSource DATA_SOURCE = new DruidDataSource();

    public static DataSource getDataSource() {
        return DATA_SOURCE;
    }

    //使用静态代码块在类加载时注册驱动
    static {

        ResourceBundle rb = ResourceBundle.getBundle("db");
        DATA_SOURCE.setDriverClassName(rb.getString("driver"));
        DATA_SOURCE.setUrl(rb.getString("url"));
        DATA_SOURCE.setUsername(rb.getString("username"));
        DATA_SOURCE.setPassword(rb.getString("password"));

    }

    /**
     *获取连接对象
     * @return返回新的连接对象
     * @throws SQLException
     */
    public static Connection getConnection() {
        Connection conn = null;
        try {
            conn = TL.get();
            // 当线程第一次调用该方法时，TL中的对象是null
            if (conn == null) {
                conn = DATA_SOURCE.getConnection();
                TL.set(conn);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(conn);
        return conn;
    }

    /**
     * 关闭所有资源，避免浪费空间
     * @param conn 连接对象
     * @param ste 操作对象
     * @param rs 查询结果集对象
     *           如果没有查询结果集对象，请传递"null",不能随便传。
     */
    public static void close(Connection conn, Statement ste, ResultSet rs){
        if (rs != null){
            try {
                rs.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        if (ste != null){
            try {
                ste.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        if (conn != null){
            try {
                // 不是真正的关闭，而是将连接返回到连接池中
                conn.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws SQLException {
        System.out.println(getConnection());
    }
}
