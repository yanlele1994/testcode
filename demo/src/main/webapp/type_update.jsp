<%@ page contentType="text/html; charset=UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="static/css/style.css" type="text/css" rel="stylesheet" />
</head>
<body>
	<form action="#" method="post" style="margin: 2px;">
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
				<td class="ta_01" style="WIDTH: 100%" align="center" bgColor="#f5fafe" colSpan="4">
					<input type="submit" value="确定" />&nbsp;&nbsp;
					<input type="reset" value="重置" />&nbsp;&nbsp;
					<input type="button" value="返回" onclick="history.go(-1)" />
				</td>
			</tr>
		</table>
	</form>
</body>
</HTML>