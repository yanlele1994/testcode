<%@ page contentType="text/html; charset=UTF-8"%>
<style type="text/css">
	.page {text-align:right;margin:10px 0;padding:0;font-size:12px;}
   	.page button{margin:0;cursor:pointer;height:23px;vertical-align: middle;}
   	.page button.curr{color:black;border-color:red;background-color:transparent; font-weight:bold;cursor:auto;}
   	.page button.go {padding:1px;height:20px;vertical-align: middle;}
   	.page select {margin:0;padding:0;width:39px;vertical-align: middle;}
   	.page input {margin:0;padding:0;padding-left:2px;width:27px;vertical-align: middle;}
</style>
<!-- 分页开始 -->
<div class="page">
	<button>首页</button>
	<button>上一页</button>
	<span>
		<button>3</button>
		<button>4</button>
		<button>5</button>
		<button>6</button>
		<button>7</button>
		<button class="curr">8</button>
		<button>9</button>
		<button>10</button>
		<button>11</button>
		<button>12</button>
	</span>
	<button>下一页</button>
	<button>尾页</button>
	共 91 条记录，
	每页 <select>
		<option value="5">5</option>
		<option value="10">10</option>
		<option value="20">20</option>
	</select> 条，
	当前页 8 / 19，
	跳转到第 <input style="width:35px;" /> 页
	<button class="go">GO</button>
</div>
<!-- 分页结束 -->