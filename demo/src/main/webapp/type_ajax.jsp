<%@ page contentType="text/html; charset=UTF-8"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" type="text/css" href="static/css/style.css" />
	<script type="text/javascript" src="static/js/jquery-1.8.3.min.js"></script>
	<script type="text/javascript">
		jQuery(function($) {
			$("#add").click(function() {
				$("#updateDialog").show();
				$("#dataForm")[0].reset();
			});
			$("#closeDialog").click(function () {
				$("#updateDialog").hide();
			});
		});
	</script>
</head>
<body>
<div id="updateDialog">
	<form id="dataForm">
		<table cellSpacing="1" cellPadding="5" width="100%" align="center" bgColor="#eeeeee" style="border: 1px solid #8ba7e3" border="0">
			<tr>
				<td class="ta_01" align="center" bgColor="#afd1f3" colSpan="4"
					height="26">
					<strong>添加分类</strong>
				</td>
			</tr>
			<tr>
				<td width="18%" align="center" bgColor="#f5fafe" class="ta_01">
					分类名称：
				</td>
				<td class="ta_01" bgColor="#ffffff" colspan="3">
					<input type="text" name="name" value="" id="userAction_save_do_logonName" class="bg"/>
				</td>
			</tr>
			<tr>
				<td class="ta_01" style="padding: 10px;" align="center" bgColor="#f5fafe" colSpan="4">
					<input type="button" value="确定" />&nbsp;&nbsp;
					<input type="reset" value="重置" />&nbsp;&nbsp;
					<input id="closeDialog" type="button" value="关闭" />
				</td>
			</tr>
		</table>
	</form>
</div>

<div style="padding:3px;">
	<div class="option">
		<input id="add" type="button" value="添加" />
		<input id="del" type="button" value="删除选中(支持跨页)" />
		<input type="radio" name="cascade" checked /><span>同时删除商品</span>
		<input type="radio" name="cascade" /><span>将商品中的外键设置为NULL</span>
	</div>
	<table cellspacing="0" cellpadding="1" rules="all"
		   bordercolor="gray" border="1" width="100%">
		<tr style="font-weight: bold; font-size: 12pt; height: 25px; background-color: #afd1f3">
			<td width="40px" align="center">
				<input type="checkbox" id="selectAll" />
			</td>
			<td align="center" width="100px">序号</td>
			<td align="center">分类名称</td>
			<td width="120px" align="center">操作</td>
		</tr>
		<tr>
			<td align="center">
				<input type="checkbox" name="chk" />
			</td>
			<td style="height: 22px" align="center">1</td>
			<td style="height: 22px" align="center">手机数码</td>
			<td style="height: 22px" align="center">
				<img src="static/images/i_edit.gif" border="0" style="cursor: hand">&nbsp;&nbsp;
				<img src="static/images/i_del.gif" border="0" style="cursor: hand">
			</td>
		</tr>
		<tr>
			<td align="center">
				<input type="checkbox" name="chk" />
			</td>
			<td style="height: 22px" align="center">2</td>
			<td style="height: 22px" align="center">家用电器</td>
			<td style="height: 22px" align="center">
				<img src="static/images/i_edit.gif" border="0" style="cursor: hand">&nbsp;&nbsp;
				<img src="static/images/i_del.gif" border="0" style="cursor: hand">
			</td>
		</tr>
		<tr>
			<td align="center">
				<input type="checkbox" name="chk" />
			</td>
			<td style="height: 22px" align="center">3</td>
			<td style="height: 22px" align="center">电脑办公</td>
			<td style="height: 22px" align="center">
				<img src="static/images/i_edit.gif" border="0" style="cursor: hand">&nbsp;&nbsp;
				<img src="static/images/i_del.gif" border="0" style="cursor: hand">
			</td>
		</tr>
		<tr>
			<td align="center">
				<input type="checkbox" name="chk" />
			</td>
			<td style="height: 22px" align="center">4</td>
			<td style="height: 22px" align="center">鞋包服饰</td>
			<td style="height: 22px" align="center">
				<img src="static/images/i_edit.gif" border="0" style="cursor: hand">&nbsp;&nbsp;
				<img src="static/images/i_del.gif" border="0" style="cursor: hand">
			</td>
		</tr>
		<tr>
			<td align="center">
				<input type="checkbox" name="chk" />
			</td>
			<td style="height: 22px" align="center">5</td>
			<td style="height: 22px" align="center">美妆个护</td>
			<td style="height: 22px" align="center">
				<img src="static/images/i_edit.gif" border="0" style="cursor: hand">&nbsp;&nbsp;
				<img src="static/images/i_del.gif" border="0" style="cursor: hand">
			</td>
		</tr>
	</table>
	<%@ include file="page.jsp" %>
</div>
</body>
</html>