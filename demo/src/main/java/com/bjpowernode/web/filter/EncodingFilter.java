package com.bjpowernode.web.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

// 扩展名匹配：不能以/开头，只能是*.do  *.action *.xxx
// 通配符匹配：/*   /xxx/*  但是不能是/abc/*/xxx, 即*只能在最后
// 精确匹配：/product/add.do
// 缺省匹配(/)： 以上三种匹配规则都没有匹配成功，则有缺省匹配的servlet来处理请求，一般该servlet是服务器默认的servlet
@WebFilter({"*.do", "*.json"})
public class EncodingFilter implements Filter {
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        servletRequest.setCharacterEncoding("UTF-8");
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        // ajax请求会携带 X-Requested-With  头
        if (request.getHeader("X-Requested-With") != null) {
            servletResponse.setContentType("application/json;charset=UTF-8");
        } else {
            servletResponse.setContentType("text/html;charset=UTF-8");
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }
}
