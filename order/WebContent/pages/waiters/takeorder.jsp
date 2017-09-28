<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
<script src="jquery/syq-pagination1.0.js" type="text/javascript"></script>
<script type="text/javascript">

	function cancel(obj) {

		if (confirm("此订单将不结账而直接作废？")) {
			obj.parentNode.parentNode.parentNode
					.removeChild(obj.parentNode.parentNode);
		}
	}
	function detail(name, disc, txt, price, commend, img) {

		var dishesName = document.getElementById("dishesName");
		var dishesDiscript = document.getElementById("dishesDiscript");
		var dishesTxt = document.getElementById("dishesTxt");
		txt = txt.replace(/ordersysspace/g, "&nbsp;");
		txt = txt.replace(/ordersysbreak/g, "<br>");

		var dishesPrice = document.getElementById("dishesPrice");
		var recommend = document.getElementById("recommend");
		var dishesImg = document.getElementById("dishesImg");
		dishesImg.src =  img;
		dishesName.innerHTML = name;
		dishesDiscript.innerHTML = disc;
		dishesTxt.innerHTML = txt;
		dishesPrice.innerHTML = price;
		if (commend == 1) {
			recommend.innerHTML = "<i class='icon-heart icon-large'></i>&nbsp;推荐菜品";
		} else {
			recommend.innerHTML = "";
		}
		
	}

	function subtract(obj) {
		var form = obj.parentNode.parentNode.parentNode.parentNode;
		if (parseInt(form.nums.value) > 1) {
			form.nums.value = parseInt(form.nums.value) - 1;
		}
	}

	function add(obj) {
		var form = obj.parentNode.parentNode.parentNode.parentNode;
		form.nums.value = parseInt(form.nums.value) + 1;

		//var child=form.
		//txt[0].value+=1;
	}

	function setTableId() {
		var table = $("#tableId").val();
		$.ajax({
			  type: "POST",
		        url: "/order/addtableIdserlvet",
		        data: {
		        	tableId : table,
		    },
			dataType:"json",
			error:function (XMLHttpRequest, textStatus, errorThrown) {
				alert("sdfsdf");
	        },
	        success: function (json) {
	        	if(json.myData!=""){
	        		alert(json.msg);
	        		var result = document.getElementById("tableresult");
	        		result.innerHTML = json.myData;
	        		//document.getElementById('tablenum').value = json.myData;
	        	}else{
	        		alert(json.msg);
	        	}
	        }
		});
	}
	function commitOrder() {
        var id = $("#tableresult").html();
        var waiterid = document.getElementById('userid').value;
        alert(waiterid);
        if(id!="【】") {
            $.ajax({
                type: "POST",
                url: "/order/addorderserlvet",
                data: {
                    tablenum: id,
                    waiterId: waiterid,
                },
                dataType: "json",
                error: function (XMLHttpRequest, textStatus, errorThrown) {
                },
                success: function (json) {
                    if (json.myData == "1") {
                    	$("#tableresult").html("【】");
                    }
                    alert(json.msg);
                }
            });
        }
        else{
        	 alert("桌号未添加");
        }
    }
	function info(obj){
		var div = obj.nextSibling.nextSibling.nextSibling;
		var form = div.firstChild;
		var id = form.dishes.value;
		$.ajax({
	        type: "POST",
	        url: "/order/dishinfoserlvet",
	        data: {
	       	 id: id,
	        },
	        dataType: "json",
	        error: function (XMLHttpRequest, textStatus, errorThrown) {
	            //window.location.href = "/paging/PagingServlet";
	        },
	        success: function (json) {
	        	if(json.myDate != ""){
	        		if(json.myData != "1"){
	        			var recommend = json.myData.recommend > 0 ? "❤推荐菜品":"非推荐菜品";
	        			$("#dishesImg").attr("src",json.myData.dishesImg);
	          			$("#dishesName").html(json.myData.dishesName);
	          			$("#recommend").html(recommend);
	          			$("#dishesDiscript").html(json.myData.dishesDiscript);
	          			$("#dishesTxt").html(json.myData.dishesTxt);
	          			$("#dishesPrice").html(json.myData.dishesPrice);
	          			$("#myModal").modal("show");
	        		}else{
	        			alert("菜品搜索失败！");
	        		}
	        	}else{
	        		alert("系统错误！");
	        	}
	        }
		}
	    );
		
	}
</script>
<script type="text/javascript">

function usersAjax(pageIndex,everyPageDataCount){//初始化数据
	$.ajax({
        type: "POST",
        url: "/order/takeorderserlvet",
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
        		<%-- // var name=<%=request.getAttribute("user")%>; --%>
      			for(var i=0;i<json.myData.length;i++){
                         /*listHtml+="<span>"+json.myData[i].proposer+"</span><br>"	;*/
                         listHtml+="<div class='col-xs-6 col-sm-3 placeholder'><a href='javascript:void(0);' onclick='info(this)'><img class='img-thumbnail' style='border-radius:20px' name='hello' alt='Generic placeholder thumbnail' src='"+
                         json.myData[i].dishesImg+"'></a><h4>"+json.myData[i].dishesName+"</h4><span class='text-muted'>"+json.myData[i].dishesDiscript+"</span><div class='form-group'><form>"+
						 "<div style='width:120px;margin: 0px auto'><div class='input-group'><span class='input-group-btn'><button class='btn btn-default' type='button' onclick='subtract(this)'>-</button>"+
						"</span> <input type='text' class='form-control' value='1' name='nums' style='text-align: center;padding: 0px;cursor: text;'><input type='hidden' name='dishes' value='"+
						json.myData[i].dishesId+"' />"+
						"<span class='input-group-btn'> <button class='btn btn-default' type='button' onclick='add(this)'>+</button></span></div>"
						+"</div><p><input type='button' class='btn btn-danger' style='width:120px;margin-top: 5px' value='加入点餐车' onclick='addshop(this)'></input></p></form></div></div>";
      			}
      			$("#orderTable").html(listHtml);
      			pagingAjax(json.dataCount,everyPageDataCount,json.pageIndex);
        	}else{
        		alert("aaaa");
        	}
        }
    });
}
	
function addshop(obj){
	var form = obj.parentNode.parentNode;
	var dishesId = form.dishes.value;
	var nums = form.nums.value;
	$.ajax({
        type: "POST",
        url: "/order/submitshoppserlvet",
        data: {
       	 dishesId: dishesId,
       	 nums: nums,
        },
        dataType: "json",
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            //window.location.href = "/paging/PagingServlet";
        },
        success: function (json) {
        		alert(json.msg);
        }
	}
	);
	
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

<body style="font-family: 微软雅黑" >
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
				<li><span class="navbar-brand">点单界面：当前桌号<span id="tableresult" name="tableresult">【】</span></span></li>

				<li class="dropdown"><a href="#" class="dropdown-toggle"
					data-toggle="dropdown" role="button" aria-haspopup="true"
					aria-expanded="false"><img src="${user.faceimg }"
						width="24" height="24" class="img-circle"
						style="border:1px solid #FFF" />&nbsp;&nbsp;当前用户:【${user.userAccount}】,身份为服务员
						<span class="caret"></span></a>
						<input type="hidden" name="userid" id="userid" value="${user.userId}">
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


<form class="navbar-form navbar-right">
				<input type="text" class="form-control" placeholder="设定桌号" id="tableId" name="tableId">
				<input class="btn btn-default" type="button" value="确定" onclick="setTableId()"/>
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
						class="icon-user icon-large"></i>&nbsp;服务员点餐功能</li>
					<li  class="active"><a href="pages/waiters/takeorder.jsp"><i class="icon-glass icon-large"></i>&nbsp;
							服务点餐界面 <span class="sr-only">(current)</span></a></li>
					<li><a href="pages/waiters/paylist.jsp"><i
							class="icon-credit-card icon-large"></i>&nbsp; 餐桌结账 <span
							class="sr-only">(current)</span></a></li>


				</ul>

			</div>


			<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">

				<ol class="breadcrumb">
					<li><a href="/OrderSys/">首页</a></li>
					<li>服务员</li>
					<li class="active">服务员点餐界面</li>
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
						<h2 class="panel-title">菜品清单</h2>
					</div>
					<div class="panel-body" style="padding-bottom: 0px">
						<div class="row placeholders" id="dishesbord" >
					<div id="orderTable" align="center"></div>




						</div>
						<nav>
						<ul class="pager">
							<li><a id="pagingDivId"></a></li>
						</ul>
						</nav>

						<div style="text-align: center;">
							<a href="javascript:commitOrder()" class="btn btn-danger"
								style="width: 40%;margin-bottom: 15px;font-size: 18;font-weight: bold;">确认订单</a>
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
					<h4 class="modal-title" id="myModalLabel">详细信息</h4>
				</div>
				<div class="modal-body"
					style="background-image: url('img/body-bg.png')">










					<div class="panel panel-danger" style="margin-top: 10px">
						<div class="panel-heading">
							<h3 class="panel-title">菜品详情</h3>
						</div>
						<div class="panel-body">

							<div style="text-align: center;">
								<img src="" id="dishesImg" width="200px"
									height="200px" class="img-circle"
									style="border:1px solid #CCC;box-shadow:0 0 10px rgba(100, 100, 100, 1);" />
								</div>
									<p>
									<h2 style="text-align: center;">
										菜品名称： <span id="dishesName"></span>
									</h2>
									<h3 style="text-align: center;">
										<span style="color: red;font-weight: bold;" id="recommend"></span>
									</h3>
									
									<hr />

									<p>
									<h3>
										菜品简介：
									</h3>
									<p><span id="dishesDiscript"></span></p>

									<p>
									<h3>菜品描述：</h3>

									<p>
										<span id="dishesTxt"></span>
									</p>
									<h3>
										菜品价格：<span id="dishesPrice"> </span> (元)
									</h3>
									


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
