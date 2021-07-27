<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" type="text/css" href="/static/css/style.css" />
	<script type="text/javascript" src="/static/js/jquery-1.8.3.min.js"></script>
	<style>
		td:nth-child(6) div {
			padding-left: 5px;
			width: 495px;
			white-space: nowrap; /* 强制不换行 */
			overflow: hidden; /* 超出的内容隐藏 */
			text-overflow: ellipsis; /* 溢出的文本显示... */
		}
		td:nth-child(3) {
			padding-left: 5px;
		}
	</style>
	<script type="text/javascript">
		// 页面加载完成事件
		jQuery(function ($) {

			// 根据选择器获取要操作的元素
			$("#add").click(function(){
				// 因为页面上要显示类别下拉框（数据在数据库中），因此需要请求servlet
				//location = '/product/add.html';
				location = '/product_add.jsp';
			});

			$("#del").on("click", function () {
				/*// 获取选中的复选框
				// 1. 先获取所有的复选框
				var arr = document.getElementsByName("chk");

				var ids = ""; // 选中的所有id，格式：1,2,3

				// 2. 遍历：筛选选中的复选框
				for ( var i = 0; i < arr.length; i++ ) {
					if ( arr[i].checked ) {
						ids += arr[i].value + ",";
					}
				}

				// ids: 1,2,3, ===> 1,2,3
				// substr: 参数1：从第几个开始截取，参数2：截取几个
				// substring: 参数1：从第几个开始截取，参数2：截取到第几个
				ids = ids.substr(0, ids.length - 1);*/
				var ids = "";
				$(":checkbox[name=chk]:checked").each(function () {
					//ids += $(this).val() + ",";
					ids += this.value + ",";
				});
				ids = ids.substr(0, ids.length - 1);

				if (ids == "") {
					alert("请选择删除项！");
					return ;
				}

				if ( !confirm("确定删除吗？") ) return;

				// 发送删除请求
				location = "/product/deleteSelected.do?ids=" + ids;

			});


			$("img[del]").click(function () {
				// 在事件中，this 是原生的DOM对象
				// var id = this.del; // 不能通过“原生DOM对象.属性名”的方式来操作
				// var id = this.getAttribute("del"); // 获取标签的自定义（辅助）属性
				// attr方法用于操作标签的自定义属性，自定义属性，不能通过“原生DOM对象.属性名”的方式来操作
				var id = $(this).attr("del");
				// confirm弹出确认框，包含确定和取消两个按钮，点击确定返回true，取消返回false
				if (confirm("确定删除吗？")) {
					// 发送删除请求
					//location.href = "/product/del.do?id=" + id;
					location = "/product/del.do?id=" + id;
				}
			});

			$("img[edit]").click(function () {
				location = "/product/edit.html?id="+$(this).attr("edit");
			});

			$("#selectAll").click(function () {
				// 根据名称获取元素，结果是数组
				/*var arr = document.getElementsByName("chk");
				for ( var i = 0; i < arr.length; i++ ) {
					// 通过改变元素的checked属性，来改变选中状态
					arr[i].checked = this.checked;
				}*/
				//$("input[type=checkbox][name=chk]")
				// 非自定义属性，都使用prop方法来操作
				//$(":checkbox[name=chk]").prop("checked", $(this).prop("checked"));
				$(":checkbox[name=chk]").prop("checked", this.checked);
			});

			// 改变复选框时，同步全选按钮的状态
			$(":checkbox[name=chk]").click(function () {
				// 选中的个数与总个数比较
				//var checked = $(":checkbox[name=chk]").size() == $(":checkbox[name=chk]:checked").size();
				//$("#selectAll").prop("checked", checked);
				// 是否有未选择的
				var checked = $(":checkbox[name=chk]:not(:checked)").size() == 0;
				$("#selectAll").prop("checked", checked);
			});
		});

	</script>
</head>
<body>
<div style="padding: 3px;">
	<div class="option">
		<input id="add" type="button" value="添加" />
		<input id="del" type="button" value="删除选中" />
	</div>
	<table cellspacing="0" cellpadding="0" bordercolor="gray" border="1" width="100%">
		<tr style="font-weight: bold; font-size: 12pt; height: 25px; background-color: #afd1f3">
			<td width="40px" align="center">
				<input type="checkbox" id="selectAll" />
			</td>
			<td width="80px" align="center">序号</td>
			<td width="" align="center">商品名称</td>
			<td width="100px" align="center">商品价格</td>
			<td width="100px" align="center">剩余数量</td>
			<td width="500px" align="center">商品描述</td>
			<td width="120px" align="center">编辑</td>
		</tr>
		<c:forEach items="${pList}" var="p" varStatus="sta">
			<tr>
				<td align="center">
					<input value="${p.id}" type="checkbox" name="chk" />
				</td>
				<td style="height: 22px" align="center">${sta.count}</td>
				<td style="height: 22px" align="left">${p.name}</td>
				<td style="height: 22px" align="center">${p.price}</td>
				<td style="height: 22px" align="center">${p.num}</td>
				<td style="height: 22px" align="left"><div>${p.description}</div></td>
				<td align="center">
					<img edit="${p.id}" src="/static/images/i_edit.gif" border="0" style="cursor: hand">&nbsp;&nbsp;
					<img del="${p.id}" src="/static/images/i_del.gif" border="0" style="cursor: hand">
				</td>
			</tr>
		</c:forEach>
	</table>
	<%@ include file="page.jsp" %>
</div>
</body>
</html>