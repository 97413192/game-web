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
<title>修改超端</title>
<base href="<%=basePath%>">
<!-- <link href="css/systemSetttings/inc_style.css" rel="stylesheet" type="text/css" /> -->
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
	body{
		margin-top: 0px;
		font-size: 0.9em;
		background-color: #F2F8FF;
		}
	.red{
			color: red;
		}
</style>
<script src="js/basevalue.js"></script>
<script src="js/jquery-1.11.1.js"></script>
<script type="text/javascript">

	$(function(){
		
		//修改绑定单击事件
		$("#updateSuper").click(function(){
			//获取输入内容
			var gameId = $("#gameId").html().trim();
			var isSuperClient = $("#isSuperClient").val().trim();
			//清除上次提示内容
			$("#usernamemsg").html("");
			var ok = true;
			//检验格式
			if(isSuperClient == "" ){
				alert("内容不能为空！");
				ok = false;
			}
			if (confirm('确定要修改此玩家的信息吗？')) {
				ok = true;
			} else {
				ok = false;
			}
			//发送ajax请求
			if(ok){
				$.ajax({
					url:"information/updateSuperPortGL.do",
					type:"post",
					data:{"gameId":gameId,"isSuperClient":isSuperClient},
					dataType:"json",
					success:function(result){
						//result就是服务器返回的JSON结果
						if(result.status==0){		 //修改成功
							alert(result.msg);
							window.location.href="information/selectOneInformation.do?index=-1&gameId="+result.data;
						}else if(result.status == 1){
							alert(result.msg);
						}else if(result.status == 2){
							alert(result.msg);
						}else if(result.status == 3){
							alert(result.msg);
						}else if(result.status == 4){
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
<!-- 		<table class="gridtable" border="1" align="center" cellpadding="3" cellspacing="0" bordercolor="#A4B6D7" -->
<!-- 			bgcolor="F2F8FF" class="admin_table2"> -->
<!-- 			<tr bgcolor="#C5D5E4"> -->
<!-- 				<td class="title_03" align="center"> -->
<!-- 					<strong>修改玩家超端</strong> -->
<!-- 				</td> -->
<!-- 			</tr> -->
<!-- 		</table> -->
		<table class="gridtable" border="1"  align="center" cellpadding="3" cellspacing="0" bordercolor="A4B6D7"
			bgcolor="F2F8FF" width="100%">
			<tr>
				<td class="title_03" colspan="19" align="center">
					<strong>玩家信息</strong>
				</td>
			</tr>
			<%-- <tr>
				<td align="right" width="40%" style="height:20px;">
					id：
				</td>
				<td colspan="2" align="left"  style="height:20px;">
					<span id="id">${id }</span>
				</td>
			</tr> --%>
			
			<tr>
				<td align="right" width="40%" style="height:20px;">
					玩家ID：
				</td>
				<td colspan="2" align="left"  style="height:20px;">
					<span id="gameId">${gameIdOne }</span>
				</td>
			</tr>
			
			<tr>
				<td align="right" width="40%" style="height:20px;">
					玩家昵称：
				</td>
				<td colspan="2" align="left"  style="height:20px;">
					<span >${pNickName}</span>
				</td>
			</tr>
						
			
			<tr>
				<td align="right">
					状态：
				</td>
				<td colspan="2" align="left">
					<span>
						<c:choose>
							<c:when test="${isSuperClient==1 }">
	                 			超端
	                 		</c:when> 
	 						<c:otherwise>
	 							不是超端
	 						</c:otherwise> 
						</c:choose>
					</span>
				</td>
			</tr>
			
			<tr>
				<td class="title_03" colspan="19" align="center">
					<strong>修改消息内容</strong>
				</td>
			</tr>
			
				
			<tr>
				<td align="right" width="40%" style="height:20px;">
					状态：
				</td>
				<td colspan="2" align="left"  style="height:20px;">
					<select id="isSuperClient">
					  <c:choose>
							<c:when test="${isSuperClient==1 }">
	                 			<option value ="1">超端</option>  
					  			<option value ="0">不是超端</option>  
	                 		</c:when> 
	 						<c:otherwise>
	 							<option value ="0">不是超端</option>
	 							<option value ="1">超端</option>  
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
					<input type="button" class="btn btn-success" name="CreateUser" value=" 修 改 " id="updateSuper" class="put" />
					
			</tr>
		</table>
	</form>
</body>
</html>