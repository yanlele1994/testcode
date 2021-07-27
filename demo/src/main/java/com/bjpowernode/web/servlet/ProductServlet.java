package com.bjpowernode.web.servlet;

import com.bjpowernode.pojo.Product;
import com.bjpowernode.pojo.Type;
import com.bjpowernode.service.ProductService;
import com.bjpowernode.service.TypeService;
import com.bjpowernode.service.impl.ProductServiceImpl;
import com.bjpowernode.service.impl.TypeServiceImpl;
import com.bjpowernode.utils.BeanFactory;
import com.bjpowernode.web.DataAndView;
import com.bjpowernode.web.annotation.AJAX;
import com.bjpowernode.web.annotation.URI;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/product/*")
public class ProductServlet extends BaseServlet {

    private ProductService productService = BeanFactory.getBean(ProductService.class);

    @URI("/product/list.html")
    public DataAndView list() {
        List<Product> pList = productService.getAll();
        DataAndView dv = new DataAndView();
        dv.addData("pList", pList);
        dv.setUrl("/product.jsp");
        return dv;
    }
    @URI("/product/del.do")
    // 变量名和参数名一致
    public String del(String id) throws ServletException, IOException {

        productService.delete(id);
        // 返回值作为跳转的页面
        return "r:/product/list.html";
    }


    @URI("/product/add.do")
    public String add(Product product) throws ServletException, IOException {

        productService.add(product);
        return "r:/product/list.html";

    }

    @URI("/product/delete.do")
    @AJAX
    public Map delete(String id) {

        productService.delete(id);

        Map map = new HashMap();
        map.put("success", true);
        map.put("msg", "操作成功！");
        return map;

    }

    @URI("/product/deleteSelected.do")
    public String deleteSelected(String ids) throws ServletException, IOException {
        productService.delete(ids);
        return "r:/product/list.html";
    }

    @URI("/product/edit.do")
    public String edit(Product product) {
        productService.edit(product);
        // 查询操作使用转发，更新操作使用重定向
        return ("r:/product/list.html");
    }

    @URI("/product/list.json")
    @AJAX
    public List<Product> listJSON() {
        return productService.getAll();
    }

    @URI("/product/save.do")
    @AJAX
    public Map save(Product product) {
        productService.add(product);

        // 查询操作使用转发，更新操作使用重定向
        Map map = new HashMap();
        map.put("success", true);
        map.put("msg", "操作成功！");
        return map;
    }

    @URI("/product/add.html")
    public DataAndView toAdd() {
        // 查询所有商品类型
        TypeService typeService = new TypeServiceImpl();
        List<Type> tList = typeService.getAll();
        DataAndView dv = new DataAndView();
        dv.addData("tList", tList);
        dv.setUrl("/product_add.jsp");
        return dv;

    }

    @URI("/product/edit.html")
    public DataAndView toEdit(String id) throws ServletException, IOException {
        Product product = productService.get(id);

        // 查询所有商品类型
        TypeService typeService = new TypeServiceImpl();
        List<Type> tList = typeService.getAll();


        DataAndView dv = new DataAndView();
        dv.addData("tList", tList);
        dv.addData("product", product);
        dv.setUrl("/product_update.jsp");

        return dv;

    }
}
