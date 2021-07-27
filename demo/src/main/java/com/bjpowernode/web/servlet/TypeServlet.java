package com.bjpowernode.web.servlet;

import com.bjpowernode.pojo.Type;
import com.bjpowernode.service.TypeService;
import com.bjpowernode.service.impl.TypeServiceImpl;
import com.bjpowernode.web.annotation.AJAX;
import com.bjpowernode.web.annotation.URI;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;


@WebServlet("/type/*")
public class TypeServlet extends BaseServlet {

    @URI("/type/types.json")
    @AJAX
    public List list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 查询所有商品类型
        TypeService typeService = new TypeServiceImpl();
        return typeService.getAll();
    }

}
