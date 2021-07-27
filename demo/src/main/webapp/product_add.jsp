<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link type="text/css" rel="stylesheet" href="/static/css/style.css"/>
    <script type="text/javascript" src="/static/js/jquery-1.8.3.min.js"></script>
    <script>
        jQuery(function ($) {
            $.ajax({
                // ajax请求获取数据时，后缀名使用json
                url: "/type/types.json", // 请求路径
                //type: "get", // 请求方式 默认是get， get|post|delete|put
                // 成功从服务器拿到数据执行的函数
                success: function (data) {
                    //js对象: [{id:1,name:"手机数码"}, {id:2,name:"电脑办公"}]
                    /*
                        JSON(JavaScript Object Notation):javascript对象（简谱）表示法
                        本质是字符串，一种特殊格式的字符串，用于和后台完成复杂的数据交互
                        '[{id:1,name:"手机数码"}, {id:2,name:"电脑办公"}]'
                     */
                    // 将 JSON 转换为js对象
                    //data = JSON.parse(data);
                    var html = "";
                    $(data).each(function () {
                        //此处this是数组中的每个元素
                        html += '<option value="'+this.id+'">'+this.name+'</option>'
                    });

                    //document.getElementById("tid").innerHTML = html;
                    $("#tid").html(html);
                }
            })

        })

    </script>
</head>
<body>
<%--
完善表单：
    1. action: 提交的Servlet路径
    2. method: post | get
    3. 表单只会提交含有name属性的元素，因此需要提交的数据，对应的表单元素必须有name属性
        name属性的值，必须和类中的属性名一致

--%>
<form id="myForm" action="/product/add.do" method="post" style="margin: 1px;">
    <table cellSpacing="1" cellPadding="5" width="100%" align="center" bgColor="#eeeeee"
           style="border: 1px solid #8ba7e3" border="0">
        <tr>
            <td class="ta_01" bgColor="#afd1f3" colSpan="4" height="26">
                <strong>添加商品</strong>
            </td>
        </tr>
        <tr>
            <td width="20%" align="center" bgColor="#f5fafe" class="ta_01">
                商品名称：
            </td>
            <td width="30%" class="ta_01" bgColor="#ffffff">
                <input type="text" name="name" value="" id="userAction_save_do_logonName" class="bg"/>
            </td>
            <td width="20%" align="center" bgColor="#f5fafe" class="ta_01">
                所属分类：
            </td>
            <td class="ta_01" bgColor="#ffffff">
                <%--对于下拉框而言,在表单提交时，提交的值是option的value，而不是文本值--%>
                <select id="tid" name="tid" class="bg">
                    <c:forEach items="${tList}" var="t">
                        <option value="${t.id}">${t.name}</option>
                    </c:forEach>
                </select>
            </td>
        </tr>
        <tr>
            <td align="center" bgColor="#f5fafe" class="ta_01">
                商品价格：
            </td>
            <td class="ta_01" bgColor="#ffffff">
                <input type="text" name="price" class="bg"/>
            </td>
            <td align="center" bgColor="#f5fafe" class="ta_01">
                库存数量：
            </td>
            <td class="ta_01" bgColor="#ffffff">
                <input type="text" name="num" class="bg"/>
            </td>
        </tr>
        <tr>
            <td align="center" bgColor="#f5fafe" class="ta_01">
                商品图片：
            </td>
            <td class="ta_01" bgColor="#ffffff" colspan="3">
                <input type="file" name="upFile" class="bg"/>
            </td>
        </tr>
        <tr>
            <td align="center" bgColor="#f5fafe" class="ta_01">
                商品描述：
            </td>
            <td class="ta_01" bgColor="#ffffff" colspan="3">
                <textarea name="description" rows="5" style="width:100%;"></textarea>
            </td>
        </tr>
        <tr>
            <td class="ta_01" style="WIDTH: 100%" align="center" bgColor="#f5fafe" colSpan="4">
                <input type="submit" value="确定"/>&nbsp;&nbsp;
                <input type="reset" value="重置"/>&nbsp;&nbsp;
                <input id="goBack" type="button" value="返回" onclick="history.go(-1)"/>
            </td>
        </tr>
    </table>
</form>
</body>
</html>