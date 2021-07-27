<%@ page contentType="text/html; charset=UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link rel="stylesheet" type="text/css" href="static/css/left.css" />
<link rel="stylesheet" type="text/css" href="static/css/ztree/ztree.css" />
<script type="text/javascript" src="static/js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="static/js/jquery.ztree.all.min.js"></script>
<script type="text/javascript">
// 设置
var setting = {
	data: {
		// 简单JSON模式
		simpleData: {
			enable: true, 	// 启用
			idKey: "id", 	// 节点的id属性名
			pIdKey: "pid"	// 节点的pid属性名
		}
	}
};

// 节点数据
var zNodes = [
	{ id:1, pid:0, name:"系统菜单树", open:true },
	{ id:2, pid:1, name:"欢迎页", url:"welcome.jsp", target:"mainFrame", icon:"static/css/ztree/img/mydiy/home.png" },
	{ id:3, pid:1, name:"商品管理", open:true },
	{ id:4, pid:3, name:"商品管理", url:"/product/list.html", target:"mainFrame"},
	{ id:5, pid:3, name:"商品管理-ajax", url:"product_ajax.jsp", target:"mainFrame"},
	{ id:6, pid:3, name:"商品分类管理", url:"type.jsp", target:"mainFrame" },
	{ id:7, pid:3, name:"商品分类管理-ajax", url:"type_ajax.jsp", target:"mainFrame" },

	{ id:8, pid:0, name:"zTree", open:true },
	{ id:9, pid:8, name:"文档", url:"static/ztree/api/API_cn.html", target:"mainFrame" },
	{ id:10, pid:8, name:"示例", url:"static/ztree/demo/cn/index.html", target:"mainFrame" }
];

// 界面加载完成后，进行初始化
jQuery(function($) {
	$.fn.zTree.init($("#ztree"), setting, zNodes);
});
</script>
</head>
<body style="background-image: url('static/images/top.png');">
<table cellSpacing="0" cellPadding="0">
	<tr>
		<td style="vertical-align:top;">
			<ul id="ztree" class="ztree"></ul>
		</td>
		<td style="width:3px;background:#5998CD;"></td>
	</tr>
</table>
</body>
</html>
