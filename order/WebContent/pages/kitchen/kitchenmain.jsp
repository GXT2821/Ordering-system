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

<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script src="bootstrap/js/jquery-1.11.1.min.js"></script>

<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->











<script src="bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript" src="js/ajax.js"></script>
	<!-- <script src="jquery/jquery-1.7.1.min.js" type="text/javascript"></script> -->
<script src="jquery/jquery-ui-1.8.20.min.js" type="text/javascript"></script>
<script src="jquery/syq-pagination1.0.js" type="text/javascript"></script>
<script type="text/javascript">
function usersAjax(pageIndex,everyPageDataCount){//初始化数据
		$.ajax({
	        type: "POST",
	        url: "/order/kitchenserlvet",
	        data: {
	       	 pageIndex: pageIndex,//当前第几页（0序列）
	       	 everyPageDataCount:everyPageDataCount,//每一页多少条数据
	        },
	        dataType: "json",
	        error: function (XMLHttpRequest, textStatus, errorThrown) {
	            //window.location.href = "/paging/PagingServlet";
	        },
	        success: function (json) {
	        	if(json.myData!=""){
	        		var listHtml="";
	      			for(var i=0;i<json.myData.length;i++){
	                         /*listHtml+="<span>"+json.myData[i].proposer+"</span><br>"	;*/
	                         listHtml+="<tr>"+
									"<td>"+json.tableId[i]+"</td>"+
									"<td>"+json.dishname[i]+"</td>"+
									"<td>"+json.myData[i].num+"</td>"+
									"<td><input type='hidden' name='dstate' value='"+json.myData[i].dstate+
									"' /><a href='javascript:void(0);' onclick='alertq(this)' class='btn btn-danger' style='width:350px' id='dstate'>"+json.alert[i]+"</a><input type='hidden' name='odId' value='"+json.myData[i].odId+
									"' /></td>"+             
	                                "</tr>";
	      			}
	      			$("#orderTable").html(listHtml);
	      			pagingAjax(json.dataCount,everyPageDataCount,json.pageIndex);
	        	}else{
	        		alert("系统出错！");
	        	}
	        }
	    });
	}
	function alertq(obj){
		var td = obj.parentNode;
		var input = td.firstChild;
		var dstate = input.value;
		var oinput=td.lastChild;
		var odId=oinput.value;
		var data = 0;
		if(dstate==0){
			if (confirm("开始烹饪菜品？"))
			 {
			       alert("菜品正在烹饪！");
			       data=1;
			 }
		}else if(dstate == 1){
			if(confirm("菜品已烹制完成？")){
				alert("正在通知服务员传菜！");
				data=1;
			}
		}else if(dstate == 2){
			if(confirm("服务员已取餐？")){
				alert("该菜品已上桌！");
				data=1;
			}
		}else{
			alert("系统错误！");
		}
		if(data==1){
			$.ajax({
		        type: "POST",
		        url: "/order/dstateserlvet",
		        data:{
		       	 dstate: dstate,
		       	 odId: odId,
		        },
		        dataType: "json",
		        error: function (XMLHttpRequest, textStatus, errorThrown) {
		            //window.location.href = "/paging/PagingServlet";
		            alert("sdfsdfsdfsdf");
		        },
		        success: function (json) {
		        	if(json.myData!=""){
		        		window.location.reload(); 
		        	}else{
		        		alert("系统错误！");
		        	}
		        	}
		        });
		}
		        
	
	}
    function pagingAjax(dataCount,everyPageDataCount,nowPage){
			$("#pagingDivId").zcPage({
			     allDataCount: dataCount,//一共有多少条数据
			     everyPageDataCount: everyPageDataCount,//每一页显示多少条数据
			     nowPageCataCount:nowPage,//当前是第几页
			     success: function (nowPageCataCount){
			    	 usersAjax(nowPageCataCount,everyPageDataCount);
			     },
			 });
    }

		 $(document).ready(function () {
			var id=<%=request.getAttribute("id")%>;
			usersAjax(0,4);//初始化页面数据（当前是第1页，每一页显示5条数据）
		});
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
					aria-expanded="false"><img src="${user.faceimg }"
						width="24" height="24" class="img-circle"
						style="border:1px solid #FFF" />&nbsp;&nbsp;当前用户:【${user.userAccount}】,身份为后厨人员
						<span class="caret"></span></a>
					<ul class="dropdown-menu">
						<li style="text-align: center;padding-top: 15px"><img
							src="${user.faceimg }" width="128" height="128"
							class="img-circle"
							style="border:1px solid #CCC;box-shadow:0 0 10px rgba(100, 100, 100, 1);margin-bottom: 20px" /></li>
						<li><a href="pages/users/modifyuser.jsp">修改我的密码</a></li>
						<li role="separator" class="divider"></li>
						


					</ul></li>
				<li><a href="pages/login.jsp">退出登录</a></li>
			</ul>




		</div>
	</div>
	</nav>

	<div class="container-fluid">
		<div class="row">
			<div class="col-sm-3 col-md-2 sidebar">
				<ul class="nav nav-sidebar">
					<li class="nav-header"
						style="font-size: 18;margin-bottom: 10px;margin-left: 10px"><i
						class="icon-coffee icon-large"></i>&nbsp;后厨</li>
					<li class="active"><a><i
							class="icon-food icon-large"></i>&nbsp; 备菜界面 <span
							class="sr-only">(current)</span></a></li>


				</ul>

			</div>





			<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">

				<ol class="breadcrumb">
					<li><a href="/OrderSys/">首页</a></li>
					<li>厨房</li>
					<li class="active">接受点餐信息界面</li>
				</ol>



				<div class="panel panel-danger">
					<div class="panel-heading">
						<h2 class="panel-title">最新公告消息</h2>
					</div>
					<div class="panel-body" style="padding-bottom: 10px">

						<h4>
							<i class=" icon-envelope icon-large" style="color:orange;"></i>&nbsp;<span
								id="msg"></span>
						</h4>

					</div>
				</div>


				<div class="panel panel-danger">
					<div class="panel-heading">
						<h3 class="panel-title">顾客点餐列表</h3>
					</div>
					<div class="panel-body">

						<div class="table-responsive">
							<table class="table table-striped">
								<thead>
									<tr>
										<th>桌号</th>
										<th>菜品</th>
										<th>数量</th>
										<th>操作</th>
									</tr>
								</thead>
								<tbody id="orderTable">

								</tbody>
							</table>
                          <nav>
							<ul class="pager">
							<li><a id="pagingDivId"></a></li>
							</ul>
							</nav>


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















</body>
</html>
