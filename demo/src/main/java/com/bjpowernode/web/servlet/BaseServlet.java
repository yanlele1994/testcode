package com.bjpowernode.web.servlet;

import com.bjpowernode.utils.MethodUtils;
import com.bjpowernode.web.DataAndView;
import com.bjpowernode.web.annotation.AJAX;
import com.bjpowernode.web.annotation.URI;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Map;
import java.util.Set;

public class BaseServlet extends HttpServlet {
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 请求路径
        String uri = req.getRequestURI();
        Class clazz = this.getClass();
        // 类中所有的方法，包括从所有父类中继承的方法
        Method[] methods = clazz.getMethods();
        // 查找目标方法
        for (Method method : methods) {
            URI uri2 = method.getAnnotation(URI.class);
            // 方法上有@URI，并且值和访问的uri相对，则表示该方法就是要执行的方法
            if (uri2 != null && uri.equals(uri2.value())) {
                try {
                    // 调用目标方法
                    //method.invoke(this, req, resp);
                    // 由于方法的参数不再是固定的req和resp，因此不可以通过上面的形式来调用
                    // 方法的参数列表
                    Parameter[] parameters = method.getParameters();
                    // 方法参数
                    Object[] args = new Object[parameters.length];

                    // 通过字节码操纵框架来获取参数列表的名称
                    String[] paramNames = MethodUtils.getParamNames(method);

                    // 初始化参数，即给参数赋值
                    for (int i = 0; i < parameters.length; i++) {
                        Parameter parameter = parameters[i];
                        // 方法参数名，和请求参数名一致
                        //String name = parameter.getName();
                        String name = paramNames[i];

                        Class type = parameter.getType();// 参数类型
                        if (HttpServletRequest.class == type) {
                            args[i] = req;
                        } else if (HttpServletResponse.class == type) {
                            args[i] = resp;
                        } else if (String.class == type) {
                            String value = req.getParameter(name);
                            args[i] = value;
                        } else if (int.class == type || Integer.class == type) {
                            String value = req.getParameter(name);
                            args[i] = Integer.valueOf(value);
                        } else {
                            // Product product = new Product();
                            // type: 参数类型（Product.class）
                            Object o = type.newInstance();
                            BeanUtils.populate(o, req.getParameterMap());
                            args[i] = o;
                        }

                    }
                    Object result = null;
                    // invoke方法的参数是可变参数，可以传递数组
                    // 方法返回值为void时，返回null
                    result = method.invoke(this, args);

                    // 处理结果，方法的返回值就是跳转的路径
                    if ( result != null ) {

                        // 判断是否为ajax
                        AJAX ajax = method.getAnnotation(AJAX.class);
                        if (ajax != null) {
                            ObjectMapper mapper = new ObjectMapper();
                            // map和pojo转json的结果是一样的格式： {属性名(key): 值}
                            String json = mapper.writeValueAsString(result);
                            resp.getWriter().write(json);
                            return ;
                        }


                        // 仅表示跳转路径
                        if ( result instanceof String ) {
                            // "r:/product/list.html"
                            String url = result.toString();
                            if (url.startsWith("r:")) {
                                resp.sendRedirect(url.replace("r:", ""));
                            }
                            // 转发
                            else {
                                req.getRequestDispatcher(url).forward(req, resp);
                            }
                        }
                        // 数据和路径
                        else if (result instanceof DataAndView) {
                            DataAndView dv = (DataAndView)result;
                            String url = dv.getUrl();
                            if (url.startsWith("r:")) {
                                resp.sendRedirect(url.replace("r:", ""));
                            }
                            // 转发
                            else {
                                // 需要往request中保存的数据
                                Map data = dv.getData();
                                Set keys = data.keySet();
                                for (Object key : keys) {
                                    req.setAttribute(key.toString(), data.get(key));
                                }
                                req.getRequestDispatcher(url).forward(req, resp);
                            }

                        }
                    }
                    break;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
