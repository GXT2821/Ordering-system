<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>${ORDER_SYS_NAME}-餐厅管理员</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="css/dashboard.css">


<link rel="stylesheet" href="css/font-awesome.min.css">
<link rel="stylesheet" href="css/rome.css">


<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script src="bootstrap/js/jquery-1.11.1.min.js"></script>
<script src="bootstrap/js/jquery.min.js"></script>
<script src="bootstrap/js/jquery-ui-1.8.20.js"></script>

<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript" src="js/ajax.js"></script>
<script type="text/javascript" src="js/rome.js"></script>
<script type="text/javascript">

function begin() {
	rome(document.getElementById("bt"));
	rome(document.getElementById("et"));

}

function reloadPage()
{
var t = setTimeout(function(){window.location.reload();},1000);
 
}
function search(){ //初始化数据
	var startDate=$("#bt").val();
	var endDate=$("#et").val();
	$.ajax({
        type: "POST",
        url: "/order/SelectDataServlet",
        data: {
        	startDate : startDate,
        	endDate : endDate,
        },
        dataType: "json",
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            //window.location.href = "/paging/PagingServlet";
        },
        success: function (json) {
        	if(json.msg==""){
        		/*var listHtml="<table border='1'><tr><td colspan = '4' align='center'><h1>会议室信息</h1></td></tr><tr><td>编号</td><td>会议室名称</td><td>预定日期</td><td>预定人</td></tr>"; */
        		var listHtml="";
      			for(var i=0;i<json.myData.length;i++){
      				listHtml += "<tr><td>"+json.myData[i].tableId 
      				+ "</td><td>"+json.myData[i].orderEndDate
      				+"</td><td>"+json.myData[i].userAccount
      				+"</td><td>"+json.myData[i].total
      				+"</td>";
      				var newLine = "<td>";
      				newLine += "<i style='cursor: pointer; font-size: 14;'";
      				newLine += "onmouseover='this.style.color=\"orange\"'";
      				newLine += "onmouseout='this.style.color=\"black\"'";
      				newLine += "class='icon-credit-card icon-large' title='确认结账' onclick='orderinfo("
      						+ json.myData[i].orderId + ")'></i>&nbsp;&nbsp;";

      				newLine += "<i style='cursor: pointer; font-size: 14;'";
      				newLine += "onmouseover='this.style.color=\"orange\"'";
      				newLine += "onmouseout='this.style.color=\"black\"'";
      				newLine += "class='icon-sitemap icon-large' onclick='orderinfo("
      						+ json.myData[i].orderId +")' title='查看订单详情'></i>&nbsp;&nbsp;";

      				newLine += "<i style='cursor: pointer; font-size: 14;'";
      				newLine += "onmouseover='this.style.color=\"orange\"'";
      				newLine += "onmouseout='this.style.color=\"black\"'";
      				newLine += "class='icon-remove-sign icon-large' title='订单作废' onclick='deleteinfo("
      						+ json.myData[i].orderId + ")'></i>";

      				newLine += "</td></tr>";
      				
      				listHtml += newLine;
      			}
      			
      			$("#orderTable").html(listHtml);
        	}else{
        		alert("信息错误！");
        	}
        }
    });
}

function orderinfo(orderId){//初始化数据
	$.ajax({
        type: "POST",
        url: "/order/DateOrderDetailedServlet",
        data: {
       	 orderId : orderId,
        },
        dataType: "json",
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            //window.location.href = "/paging/PagingServlet";
        },
        success: function (json) {
        	if(json.msg==""){
        		/*var listHtml="<table border='1'><tr><td colspan = '4' align='center'><h1>会议室信息</h1></td></tr><tr><td>编号</td><td>会议室名称</td><td>预定日期</td><td>预定人</td></tr>"; */
        		var listHtml="";
      			for(var i=0;i<json.myData.length;i++){
      				listHtml += "<tr><td>"+json.myData[i].dishesName 
      				+ "</td><td>"+json.myData[i].dishesPrice
      				+"</td><td>"+json.myData[i].num
      				
      				+"</td></tr>";
      			}
      			$("#tableId").html(json.data.tableId);
      			$("#orderBeginTime").html(json.data.orderBeginDate);
      			$("#orderEndTime").html(json.data.orderEndDate);
      			$("#userAccount").html(json.data.userAccount);
      			$("#sumPrice").html(json.data.total);
      			$("#detailTable").html(listHtml);
      			$('#myModal').modal('show');
        	}else{
        		alert("信息错误！");
        	}
        }
    });
}


function deleteinfo(orderId){
	like=window.confirm("是否删除此订单？");
	if(like==true){
		$.ajax({
	        type: "POST",
	        url: "/order/DeleteOrderServlet",
	        data: {
	       	 orderId : orderId,
	        },
	        dataType: "json",
	        error: function (XMLHttpRequest, textStatus, errorThrown) {
	            //window.location.href = "/paging/PagingServlet";
	        },
	        success: function (json) {
	        	if(json.msg==""){
	        		alert("操作成功！");
	        		search();
	        	}else{
	        		alert("操作失败！");
	        	}
	        }
	    });
	}
	
}
</script>
</head>

<body style="font-family: 微软雅黑" onload="begin()">
	<nav class="navbar navbar-inverse navbar-fixed-top">
	<div class="container-fluid">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed"
				data-toggle="collapse" data-target="#navbar" aria-expanded="false"
				aria-controls="navbar">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<img src="img/logo.png" class="navbar-brand" /> <span
				class="navbar-brand">${ORDER_SYS_NAME}</span>
		</div>
		<div id="navbar" class="navbar-collapse collapse">
			<ul class="nav navbar-nav navbar-right">
				<li><span class="navbar-brand">餐厅管理界面</span></li>

				<li class="dropdown"><a href="#" class="dropdown-toggle"
					data-toggle="dropdown" role="button" aria-haspopup="true"
					aria-expanded="false"><img
						src="${user.faceimg }" width="24" height="24"
						class="img-circle" style="border:1px solid #FFF" />&nbsp;&nbsp;当前用户:【${user.userAccount
						}】,身份为管理员 <span class="caret"></span></a>
					<ul class="dropdown-menu">
						<li style="text-align: center;padding-top: 15px"><img
							src="${user.faceimg }" width="128" height="128"
							class="img-circle"
							style="border:1px solid #CCC;box-shadow:0 0 10px rgba(100, 100, 100, 1);margin-bottom: 20px" /></li>
						<li><a href="pages/users/modifyuser.jsp">修改我的密码</a></li>
						<li role="separator" class="divider"></li>
						<li><a href="SelectKitchenServlet">查看当前在线的厨师 </a></li>
						<li><a href="SelectWaiterServlet">查看当前在线的点餐员</a></li>


					</ul></li>
				<li><a href="pages/login.jsp">退出登录</a></li>
			</ul>



			<form class="navbar-form navbar-right" method="post"
				action="sendbord.order" target="formtarget">
				<input type="text" class="form-control" placeholder="公告"
					style="width:300px" name="bord"><input
					class="btn btn-default" type="submit" value="发送" />
				<iframe name="formtarget" width="0" height="0" style="display: none"></iframe>
			</form>
		</div>
	</div>
	</nav>


	<div class="container-fluid">
		<div class="row">
			<div class="col-sm-3 col-md-2 sidebar">
				<ul class="nav nav-sidebar">
					<li class="nav-header"
						style="font-size: 18;margin-bottom: 10px;margin-left: 10px"><i
						class="icon-credit-card icon-large"></i>&nbsp;运营功能</li>
					<li><a href="OrderInfoServlet"><i
							class="icon-money icon-large"></i>&nbsp; 顾客结账界面 <span
							class="sr-only">(current)</span></a></li>


				</ul>
				<ul class="nav nav-sidebar">
					<li class="nav-header"
						style="font-size: 18;margin-bottom: 10px;margin-left: 10px"><i
						class="icon-cog icon-large"></i>&nbsp;餐厅管理</li>
					<li><a href="pages/admin/useradmin.jsp"><i
							class="icon-group icon-large"></i>&nbsp;员工管理</a></li>
					<li><a href="pages/admin/dishesadmin.jsp"><i
							class="icon-coffee icon-large"></i>&nbsp;菜品管理</a></li>
					<li  class="active"><a href="pages/admin/operatedata.jsp"><i
							class="icon-bar-chart icon-large"></i>&nbsp;查看经营数据 </a></li>

				</ul>
			</div>




			<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">

				<ol class="breadcrumb">
					<li><a href="OrderInfoServlet">首页</a></li>
					<li>管理员</li>
					<li class="active">管理管理界面</li>
				</ol>





				<div class="panel panel-danger">
					<div class="panel-heading">
						<h2 class="panel-title">搜索特定时间订单</h2>
					</div>
					<div class="panel-body" style="padding-bottom: 0px">



						<form class="form-inline">
							<div class="form-group">
								<label for="exampleInputName2">开始时间：</label> <input type="text"
									class="form-control" id="bt" name="bt">
							</div>
							&nbsp;&nbsp;
							<div class="form-group">
								<label for="exampleInputEmail2">结束时间：</label> <input type="text"
									class="form-control" id="et" name="et">
							</div>
							<input type="button" class="btn btn-danger" onclick="search()"
								value="开始搜索" />
						</form>




					</div>
				</div>






				<div class="panel panel-danger">
					<div class="panel-heading">
						<h3 class="panel-title">搜索结果</h3>
					</div>
					<div class="panel-body">

						<div class="table-responsive">
							<table class="table table-striped">
								<thead>
									<tr>
										<th>桌号</th>
										<th>结账时间</th>
										<th>服务员</th>
										<th>总价</th>
										<th>详情</th>
									</tr>
								</thead>
								<tbody id="orderTable">
									<tr>
										<td>1,001</td>
										<td>Lorem</td>
										<td>ipsum</td>
										<td>dolor</td>
										<td><i style="cursor: pointer; font-size: 14;"
											onmouseover="this.style.color='orange'"
											onmouseout="this.style.color='black'"
											class="icon-credit-card icon-large" title="确认结账"></i>&nbsp;&nbsp;<i
											style="cursor: pointer; font-size: 14;"
											onmouseover="this.style.color='orange'"
											onmouseout="this.style.color='black'"
											class="icon-sitemap icon-large" title="查看订单详情"
											onclick="$('#myModal').modal('show')"></i> &nbsp;&nbsp;<i
											style="cursor: pointer; font-size: 14;"
											onmouseover="this.style.color='orange'"
											onmouseout="this.style.color='black'"
											class=" icon-remove-sign icon-large" title="订单作废"
											onclick="cancel(this)"></i></td>
									</tr>

								</tbody>
							</table>
							<!-- 
							<nav> 
							<ul class="pager">
								<li><a href="#">上一页</a></li>
								<li><a href="#">下一页</a></li>
							</ul>
							</nav>
							 -->
						</div>

					</div>
				</div>

				<div
					style="height:1px;width: 100%;background: #CCC;margin-bottom: 10px"></div>
				<footer>
				<p>&copy; ${ORDER_SYS_NAME}-中软国际ETC 2017</p>
				</footer>

			</div>
		</div>
	</div>

	<br>






	<!-- Modal -->
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">订单详情</h4>
				</div>
				<div class="modal-body"
					style="background-image: url('img/body-bg.png')">








					<div class="container-fluid" style="padding: 0px 0px;">
						<div class="row-fluid">
							<div class="span6" style="width:30%;float:left">
								<div
									style="background-color: #66A;padding: 15px;margin: 1px;height:202px;color:white;">
									<h2 style="color: white;font-weight: bold;font-family: 微软雅黑">桌号</h2>
									<p id="tableId"></p>

									<p style="text-align: center;font-size: 24;margin-top: 11px ">
										<i class="icon-warning-sign icon-large"></i>
									</p>
								</div>
							</div>
							<div class="span6" style="width:70%;float:left">
								<div class="row-fluid">
									<div class="span6" style="width:50%;float:left">
										<div
											style="background-color: #CCC;padding: 15px;margin: 1px;height:100px;">
											<h3>开始时间</h3>
											<p id="orderBeginTime"></p>

										</div>

									</div>
									<div class="span6" style="width:50%;float:left">

										<div
											style="background-color: #CF0;padding: 15px;margin: 1px;height:100px;">
											<h3>结餐时间</h3>
											<p id="orderEndTime"></p>

										</div>
									</div>
								</div>
								<div class="row-fluid">
									<div class="span6" style="width:50%;float:left">

										<div
											style="background-color: #FA0;padding: 15px;margin: 1px;height:100px;">
											<h3>点餐服务员</h3>
											<p id="userAccount"></p>

										</div>
									</div>
									<div class="span6" style="width:50%;float:left">

										<div
											style="background-color: #DAD;padding: 15px;margin: 1px;height:100px;">
											<h3>总价（元）</h3>
											<p>
												<span style="color:red;font-weight: bold" id="sumPrice"></span>
											</p>

										</div>
									</div>
								</div>
							</div>
						</div>
					</div>



					<div class="panel panel-danger" style="margin-top: 10px">
						<div class="panel-heading">
							<h3 class="panel-title">订单详情</h3>
						</div>
						<div class="panel-body">
							<table class="table table-striped">

								<caption>该桌的订单详情如下</caption>
								<thead>
									<tr>
										<th>菜品</th>
										<th>单价</th>
										<th>数量</th>
									</tr>
								</thead>
								<tbody id="detailTable">


								</tbody>

							</table>
							<!-- 
							<nav>
							<ul class="pager">
								<li><a href="#">上一页</a></li>
								<li><a href="#">下一页</a></li>
							</ul>
							</nav>
 -->
						</div>
					</div>











				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-danger" data-dismiss="modal">关闭</button>

				</div>
			</div>
		</div>
	</div>







</body>
</html>
