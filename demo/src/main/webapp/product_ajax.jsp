<%@ page contentType="text/html; charset=UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="static/css/style.css" />
<script type="text/javascript" src="static/js/jquery-1.8.3.min.js"></script>
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
	jQuery(function($) {
		$("#add").click(function() {
			$("#updateDialog").show();
			$("#dataForm")[0].reset();
		});

		$("#closeDialog").click(function () {
			$("#updateDialog").hide();
		});

        function list() {
            $.ajax({
                url: "/product/list.json",
                success: function (data) {
                    var arr = [];
                    $(data).each(function (index) {
                        arr.push(`<tr>
                                <td align="center">
                                    <input value="`+this.id+`" type="checkbox" name="chk" />
                                </td>
                                <td style="height: 22px" align="center">`+(index+1)+`</td>
                                <td style="height: 22px" align="left">`+this.name+`</td>
                                <td style="height: 22px" align="center">`+this.price+`</td>
                                <td style="height: 22px" align="center">`+this.num+`</td>
                                <td style="height: 22px" align="left"><div>`+this.description+`</div></td>
                                <td align="center">
                                    <img src="static/images/i_edit.gif" border="0" style="cursor: hand">&nbsp;&nbsp;
                                    <img del="`+this.id+`" src="static/images/i_del.gif" border="0" style="cursor: hand">
                                </td>
                            </tr>`)
                    });
                    // [1,2,3].join("") ===> 123
                    $("#dataBody").html( arr.join("") );


                }
            })
        }

        list();


        $.ajax({
            url: "/type/types.json", // 请求路径
            success: function (data) {
                var html = "";
                $(data).each(function () {
                    //此处this是数组中的每个元素
                    html += '<option value="'+this.id+'">'+this.name+'</option>'
                });
                $("select[name=tid]").html(html);
            }
        });

        $("#saveBtn").click(function () {
            $.ajax({
                url: "/product/save.do",
                type: "post",
                data: {
                    // :input   input、select、textarea、button
                    name: $(":input[name=name]").val(),
                    tid: $(":input[name=tid]").val(),
                    price: $(":input[name=price]").val(),
                    num: $(":input[name=num]").val(),
                    description: $(":input[name=description]").val()
                },
                success: function (data) {
                    // 对于增删改操作，后台返回的json：{success: true/false, msg: ""}
                    if (data.success) {
                        $("#updateDialog").hide();
                        list(); // 重新加载列表
                    }
                    // 如果结果中有信息返回，则弹出该信息
                    /*
                        在if中：
                        boolean    true         false
                        字符串      非""           ""
                        数字        非0            0
                        对象        非null         null
                                   非undefined    undefined
                     */
                    if (data.msg) {
                        alert(data.msg);
                    }
                }
            })
        });
        /*
            如果需要绑定事件的元素不存在，可以使用事件委派的方式进行绑定
            其原理是：将事件绑定到目标元素的父元素上（必须存在），当点击父元素中的任意元素时，
            判断是否为目标元素，如果是，则触发指定的事件，否则不触发

            事件函数中的e: 事件句柄对象，通过该对象，可以获取到事件触发时和事件相关的属性，
            例如坐标(距离窗口左上角(0,0)点的x、y坐标)，点击的是哪个元素，左键？右键？点击的元素？
         */
        /*$("#dataBody").click(function (e) {
            // e.target是点击的元素，是否是包含del属性的img标签
            if ( $( e.target ).is("img[del]") ) {
                var id = $(e.target).attr("del");
                alert(id)
            }
        });*/

        $("#dataBody").on("click", "img[del]", function () {

            var id = $(this).attr("del");

            if ( !confirm("确定删除码？") ) return;

            $.ajax({
                url: "/product/delete.do?id="+id,
                success: function (data) {
                    if (data.success) {
                        list(); // 重新加载列表
                    }
                    if (data.msg) {
                        alert(data.msg);
                    }
                }
            });


        });


        $("#selectAll").click(function () {
            $(":checkbox[name=chk]").prop("checked", this.checked);
        });

        // 改变复选框时，同步全选按钮的状态
        $("#dataBody").on("click", ":checkbox[name=chk]", function () {
            // 是否有未选择的
            var checked = $(":checkbox[name=chk]:not(:checked)").size() == 0;
            $("#selectAll").prop("checked", checked);
        });

        $("#del").click(function () {
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
            $.ajax({
                url: "/product/delete.do?id=" + ids,
                success: function (data) {
                    if (data.success) {
                        list(); // 重新加载列表
                    }
                    if (data.msg) {
                        alert(data.msg);
                    }
                }
            })
        });
	});
</script>
</head>
<body>
<%--添加的DIV窗口--%>
<div id="updateDialog" class="dialog">
	<form id="dataForm">
		<table cellSpacing="30" cellPadding="5" width="100%" align="center" bgColor="#eeeeee" style="border: 1px solid #8ba7e3" border="0">
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
					<select id="tid" name="tid" class="bg">
					</select>
				</td>
			</tr>
			<tr>
				<td align="center" bgColor="#f5fafe" class="ta_01">
					商品价格：
				</td>
				<td class="ta_01" bgColor="#ffffff">
					<input type="text" name="price"  class="bg"/>
				</td>
				<td align="center" bgColor="#f5fafe" class="ta_01">
					库存数量：
				</td>
				<td class="ta_01" bgColor="#ffffff">
					<input type="text" name="num" class="bg" />
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
					<textarea name="description" rows="5" style="width:99%;"></textarea>
				</td>
			</tr>
			<tr>
				<td class="ta_01" style="padding: 10px 0;" align="center" bgColor="#f5fafe" colSpan="4">
					<input id="saveBtn" type="button" value="确定" />&nbsp;&nbsp;
					<input type="reset" value="重置" />&nbsp;&nbsp;
					<input id="closeDialog" type="button" value="关闭" />
				</td>
			</tr>
		</table>
	</form>
</div>

<%--修改的DIV窗口--%>
<div id="updateDialog" class="dialog">
    <form>
        <table cellSpacing="30" cellPadding="5" width="100%" align="center" bgColor="#eeeeee" style="border: 1px solid #8ba7e3" border="0">
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
                    <select name="tid" class="bg">
                    </select>
                </td>
            </tr>
            <tr>
                <td align="center" bgColor="#f5fafe" class="ta_01">
                    商品价格：
                </td>
                <td class="ta_01" bgColor="#ffffff">
                    <input type="text" name="price"  class="bg"/>
                </td>
                <td align="center" bgColor="#f5fafe" class="ta_01">
                    库存数量：
                </td>
                <td class="ta_01" bgColor="#ffffff">
                    <input type="text" name="num" class="bg" />
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
                    <textarea name="description" rows="5" style="width:99%;"></textarea>
                </td>
            </tr>
            <tr>
                <td class="ta_01" style="padding: 10px 0;" align="center" bgColor="#f5fafe" colSpan="4">
                    <input id="saveBtn" type="button" value="确定" />&nbsp;&nbsp;
                    <input type="reset" value="重置" />&nbsp;&nbsp;
                    <input id="closeDialog" type="button" value="关闭" />
                </td>
            </tr>
        </table>
    </form>
</div>

<div style="padding: 3px;">
	<div class="option">
		<input id="add" type="button" value="添加" />
		<input id="del" type="button" value="删除选中(支持跨页！)" />
	</div>
	<table id="dataTable" cellspacing="0" cellpadding="0" bordercolor="gray" border="1" width="100%">
		<thead>
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
		</thead>
		<tbody id="dataBody"></tbody>
	</table>
	<%@ include file="page.jsp" %>
</div>
</body>
</html>