package com.bjpowernode.web.servlet;

import com.bjpowernode.pojo.PCD;
import com.bjpowernode.pojo.Product;
import com.bjpowernode.pojo.Type;
import com.bjpowernode.service.TypeService;
import com.bjpowernode.service.impl.TypeServiceImpl;
import com.bjpowernode.utils.DBUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@WebServlet("/load.json")
public class LoadServlet extends HttpServlet {
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pid = req.getParameter("pid");

        List list = new ArrayList();

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = DBUtil.getConnection();
            String sql = "select * from province_city_district where pid=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, pid);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                /*Map map = new HashMap();
                map.put("id", rs.getString("id"));
                map.put("name", rs.getString("name"));
                list.add(map);*/
                PCD pcd = new PCD();
                pcd.setId(rs.getString("id"));
                pcd.setName(rs.getString("name"));
                list.add(pcd);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn, pstmt, rs);
        }


        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(list);
        resp.setContentType("application/json;charset=UTF-8");
        resp.getWriter().write(json);

    }
}
