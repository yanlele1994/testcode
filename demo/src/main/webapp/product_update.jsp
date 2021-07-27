<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link type="text/css" rel="stylesheet" href="/static/css/style.css" />
</head>
<body>
	<%--
		action:
		method:
		表单元素要有name属性：
	--%>
	<form id="myForm" action="/product/edit.do" method="post" style="margin: 1px;">
		<%--把需要提交而又不希望用户看到的数据，用隐藏域存储--%>
		<input type="hidden" name="id" value="${product.id}" />


		<table cellSpacing="1" cellPadding="5" width="100%" align="center" bgColor="#eeeeee" style="border: 1px solid #8ba7e3" border="0">
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
					<input type="text" name="name" value="${product.name}" id="userAction_save_do_logonName" class="bg"/>
				</td>
				<td width="20%" align="center" bgColor="#f5fafe" class="ta_01">
					所属分类：
				</td>
				<td class="ta_01" bgColor="#ffffff">
					<select name="tid" class="bg">
						<c:forEach items="${tList}" var="t">
							<%--当option中不书写value属性时，会提交文本值--%>
							<option ${product.tid == t.id ? "selected" : ""} value="${t.id}">${t.name}</option>
						</c:forEach>
					</select>
				</td>
			</tr>
			<tr>
				<td align="center" bgColor="#f5fafe" class="ta_01">
					商品价格：
				</td>
				<td class="ta_01" bgColor="#ffffff">
					<input type="text" name="price" value="${product.price}" class="bg"/>
				</td>
				<td align="center" bgColor="#f5fafe" class="ta_01">
					库存数量：
				</td>
				<td class="ta_01" bgColor="#ffffff">
					<input type="text" name="num" value="${product.num}" class="bg" />
				</td>
			</tr>
			<tr>
				<td align="center" bgColor="#f5fafe" class="ta_01">
					商品图片：
				</td>
				<td class="ta_01" bgColor="#ffffff" colspan="3">
					<input type="file" name="upFile" class="bg" />
				</td>
			</tr>
			<tr>
				<td align="center" bgColor="#f5fafe" class="ta_01">
					商品描述：
				</td>
				<td class="ta_01" bgColor="#ffffff" colspan="3">
					<textarea name="description" rows="5" style="width:100%;">${product.description}</textarea>
				</td>
			</tr>
			<tr>
				<td class="ta_01" style="WIDTH: 100%" align="center" bgColor="#f5fafe" colSpan="4">
					<input type="submit" value="确定" />&nbsp;&nbsp;
					<input type="reset" value="重置" />&nbsp;&nbsp;
					<input id="goBack" type="button" value="返回" onclick="history.go(-1)" />
				</td>
			</tr>
		</table>
	</form>
</body>
</html>