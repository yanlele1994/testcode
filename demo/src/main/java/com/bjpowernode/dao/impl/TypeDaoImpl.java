package com.bjpowernode.dao.impl;

import com.bjpowernode.dao.TypeDao;
import com.bjpowernode.pojo.Product;
import com.bjpowernode.pojo.Type;
import com.bjpowernode.utils.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class TypeDaoImpl implements TypeDao {
    public List<Type> getAll() {
        List<Type> list = new ArrayList<>();

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = DBUtil.getConnection();
            String sql = "select * from type";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                Type type = new Type();
                type.setId(rs.getString("id"));
                type.setName(rs.getString("name"));
                list.add(type);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn, pstmt, rs);
        }

        return list;
    }
}
