<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>查询消息具体信息</title>
<base href="<%=basePath%>">
<link href="css/systemSetttings/inc_style.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
<style type="text/css">
table.gridtable {
	font-family: verdana, arial, sans-serif;
	font-size: 11px;
	color: #333333;
	border-width: 1px;
	border-color: #666666;
	border-collapse: collapse;
}

table.gridtable th {
	border-width: 1px;
	padding: 8px;
	border-style: solid;
	border-color: #666666;
	background-color: #dedede;
}

table.gridtable td {
	border-width: 1px;
	padding: 8px;
	border-style: solid;
	border-color: #666666;
	background-color: #ffffff;
}

table.imagetable {
	font-family: verdana, arial, sans-serif;
	font-size: 11px;
	color: #333333;
	border-width: 1px;
	border-color: #999999;
	border-collapse: collapse;
}

table.imagetable th {
	background: #b5cfd2 url('images/cell-blue.jpg');
	border-width: 1px;
	padding: 8px;
	border-style: solid;
	border-color: #999999;
}

table.imagetable td {
	background: #dcddc0 url('images/cell-grey.jpg');
	border-width: 1px;
	padding: 8px;
	border-style: solid;
	border-color: #999999;
}
	body{
			margin-top: 0px;
		}
	.red{
			color: red;
		}
</style>
<script src="js/basevalue.js"></script>
<script src="js/jquery-1.11.1.js"></script>
<script type="text/javascript">
	$(function(){
		//查询绑定单击事件
		$("#deleteInformation").click(function(){
			//获取参数
			var id = $("#id").html().trim();
// 			alert(id);
			//清空上次的提示内容
			var ok = confirm("你确认要删除这条信息吗?");
			//发送ajax请求
			if(ok){
				$.ajax({
					url:"information/deleteInformation.do",
					type:"post",
					data:{"id":id},
					dataType:"json",
					success:function(result){
						//result就是服务器返回的JSON结果
						if(result.status==0){		 //删除成功
							alert(result.msg); 
						}else if(result.status == 1){//没有权限
							alert(result.msg);
						}else if(result.status == 2){//数据库中没有此条数据
							alert(result.msg);
						}else if(result.status == 3){//删除操作失败
							alert(result.msg);
						}else if(result.status == 4){//未知错误
							alert(result.msg);
						}else if(result.status == -1){
							window.location.href="login/toLogin.do";
						}
					},
					error:function(){
						alert("删除失败！");
					}
					
				});
			}
		});
		//修改绑定单击事件
		$("#updateInformation").click(function(){
			//获取输入内容
			var id = $("#id").html().trim();
			var message= $("#message").val().trim();
			var category = $("#category").val().trim();
			//alert(message);
			//alert(category);
			//清除上次提示内容
			$("#usernamemsg").html("");
			var ok = true;
			//检验格式
			if(message == ""){
				$("#usernamemsg").html("内容不能为空！");
				ok = false;
			}
			
			//发送ajax请求
			if(ok){
				$.ajax({
					url:"information/alertInformation.do",
					type:"post",
					data:{"id":id,"message":message,"category":category},
					dataType:"json",
					success:function(result){
						//result就是服务器返回的JSON结果
						if(result.status==0){		 //修改成功
							alert(result.msg); 
						}else if(result.status == 1){//没有权限
							alert(result.msg);
						}else if(result.status == 2){//数据库中没有此条数据
							alert(result.msg);
						}else if(result.status == 3){//修改操作失败
							alert(result.msg);
						}else if(result.status == 4){//未知错误
							alert(result.msg);
						}else if(result.status == -1){
							window.location.href="login/toLogin.do";
						}
						//清空输入框中信息
						$("#message").val("");
					},
					error:function(){
						alert("修改失败！")
					}
				});
			}
		});
	});
</script>
</head>
<body>
	<form name="form1" method="post" action="systemSettings/selectManager.do" id="form1" onsubmit="return checkform(this)">
<!-- 		<table class="gridtable" width="100%" border="1" align="center" cellpadding="3" cellspacing="0" bordercolor="#A4B6D7" -->
<!-- 			bgcolor="F2F8FF" class="admin_table2"> -->
<!-- 			<tr> -->
<!-- 				<td class="title_03"> -->
<!-- 					<strong>消息管理</strong> -->
<!-- 				</td> -->
<!-- 			</tr> -->
<!-- 		</table> -->
<!-- 		<table class="gridtable" width="100%" border="0" cellspacing="0" cellpadding="0"> -->
<!-- 			<tr> -->
<!-- 				<td style="height: 5px"> -->
<!-- 				</td> -->
<!-- 			</tr> -->
<!-- 		</table> -->
		<table class="gridtable" width="100%" border="1" align="center" cellpadding="3" cellspacing="0" bordercolor="A4B6D7"
			bgcolor="F2F8FF" class="admin_table">
			<tr>
				<td class="title_03" colspan="19" align="center">
					<strong>消息详细信息</strong>
				</td>
			</tr>
			<%-- 
			<tr>
				<td align="right" width="40%" style="height:20px;">
					输入要查询的消息id：
				</td>
				<td colspan="2" align="left"  style="height:20px;">
					<input name="name" type="text" id="name" class="put" maxlength="12" />
					<input type="button" name="CreateUser" value=" 查 询 " id="select" class="put" />
					<span id="checkmsg" class="red">${loginServiceError }${error }${exception }</span>
				</td>
			</tr>
			--%>
			<tr>
				<td align="right" width="40%" style="height:20px;">
					消息id：
				</td>
				<td colspan="2" align="left"  style="height:20px;">
					<span id="id">${information.id }</span>
				</td>
			</tr>
			<tr>
				<td align="right" width="40%" style="height:20px;">
					消息内容：
				</td>
				<td colspan="2" align="left"  style="height:20px;">
					<span id="uuidmsg">${information.message }</span>
				</td>
			</tr>
			<tr>
				<td align="right">
					消息类别：
				</td>
				<td colspan="2" align="left">
					<span id="sexmsg">
						<c:choose>
							<c:when test="${information.category==2 }">
	                 			跑马灯
	                 		</c:when> 
	 						<c:otherwise>
	 							公告
	 						</c:otherwise> 
						</c:choose>
					</span>
				</td>
			</tr>
			<tr>
				<td align="right">
					创建时间：
				</td>
				<td colspan="2" align="left">
					<span id="imeimsg">${information.createTime }</span>
				</td>
			</tr>
			<tr>
				<td align="right">
					上次修改的时间：
				</td>
				<td colspan="2" align="left">
					<span id="imsimsg">${information.modifyTime }</span>
				</td>
			</tr>
			
			<tr>
				<td class="title_03" colspan="19" align="center">
					<strong>修改消息内容</strong>
				</td>
			</tr>
			<tr>
				<td align="right" width="40%" style="height:20px;">
					消息内容：<br>
				</td>
				<td colspan="2" align="left"  style="height:20px;">
					<textarea name="u" id="message" class="put"  cols="50" rows="5" maxlength="1000" required></textarea>
					<span id="usernamemsg" class="red"></span>
				</td>
			</tr>
			
			<tr>
				<td align="right" width="40%" style="height:20px;">
					管理员状态：
				</td>
				<td colspan="2" align="left"  style="height:20px;">
					<select id="category">
					  <c:choose>
							<c:when test="${information.category==2 }">
	                 			<option value ="2">跑马灯</option>  
					  			<option value ="1">公告</option>  
	                 		</c:when> 
	 						<c:otherwise>
	 							<option value ="1">公告</option>
	 							<option value ="2">跑马灯</option>  
	 						</c:otherwise> 
						</c:choose>
					</select> 
					<span id="newusernamemsg" class="red"></span>
				</td>
			</tr>
			<tr>
				<td align="right">
					&nbsp;
				</td>
				<td colspan="2" align="left">
					<input type="button" name="CreateUser" value="修 改 " id="updateInformation" class="btn btn-warning"/>&nbsp;&nbsp;
					<input type="button" name="CreateUser" value="删 除 " id="deleteInformation" class="btn btn-danger" />
				</td>
			</tr>
		</table>
	</form>
</body>
</html>