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
<title>增加充值档次</title>
<base href="<%=basePath%>">
<link href="css/systemSetttings/inc_style.css" rel="stylesheet" type="text/css" />
<style type="text/css">
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
	//输入框只能输入数字
	function filterInput(){
		if (event.type.indexOf("key") != -1){
			var re = /37|38|39|40/g
			if (event.keyCode.toString().match(re)){
				return false
			}
		}
		event.srcElement.value = event.srcElement.value.replace(/[^0-9]/g, "")
	}
	
	$(function(){
		//修改绑定单击事件
		$("#addBtn").click(function(){
			//获取输入内容
			var levelId = $("#levelId").val().trim();  //档次编号
			var money = $("#money").val().trim();  //充值金额
			var jewel = $("#jewel").val().trim();  //钻石数量
			var ok = true;
			//检验格式
			if(levelId == ""){
				alert("档次编号输入不能为空！");
				ok = false;
			}
			if(money == ""){
				alert("充值金额输入不能为空！");
				ok = false;
			}
			if(jewel == ""){
				alert("钻石数量输入不能为空！");
				ok = false;
			}
			//发送ajax请求
			if(ok){
				$.ajax({
					url:"recharge/alterConvertScale.do",
					type:"post",
					data:{"convertScale":convertScale},
					dataType:"json",
					success:function(result){
						if(result.status == 0){
							alert(result.msg);
						}else if(result.status == 1){//登陆服务连接失败
							alert(result.msg);
						}else if(result.status == 2){//通过登录服务获取房间信息失败
							alert(result.msg);
						}else if(result.status == 3){//未知错误
							alert(result.msg);
						}else if(result.status == -1){
							window.location.href="login/toLogin.do";
						}
					},
					error:function(){
						alert("增加失败！")
					}
				});
			}
		});
	});
</script>
</head>
<body>
	<form name="form1" method="post" action="./CreateUser.aspx" id="form1" onsubmit="return checkform(this)">
		<table width="100%" border="1" align="center" cellpadding="3" cellspacing="0" bordercolor="A4B6D7"
			bgcolor="F2F8FF" class="admin_table">
			<tr>
				<td class="title_03" colspan="19">
					<strong>增加充值档次</strong>
				</td>
			</tr>
			<tr>
				<td align="right" width="40%" style="height:20px;">
					档次编号：
				</td>
				<td colspan="2" align="left"  style="height:20px;">
					<input name="levelId" type="text" id="levelId" class="put" 
						maxlength="10" size ="10" onkeyup="filterInput();"/>					
				</td>
			</tr>
			<tr>
				<td align="right" width="40%" style="height:20px;">
					充值金额：
				</td>
				<td colspan="2" align="left"  style="height:20px;">
					<input name="money" type="text" id="money" class="put" 
						maxlength="10" size ="10" onkeyup="filterInput();"/>					
				</td>
			</tr>
			<tr>
				<td align="right" width="40%" style="height:20px;">
					钻石数量：
				</td>
				<td colspan="2" align="left"  style="height:20px;">
					<input name="jewel" type="text" id="jewel" class="put" 
						maxlength="10" size ="10" onkeyup="filterInput();"/>					
				</td>
			</tr>
			<tr>
				<td align="right" width="40%" style="height:20px;">
				</td>
				<td colspan="2" align="left"  style="height:20px;">
					<input type="button" name="addBtn" value=" 增 加 " id="addBtn" class="put"/>
				</td>
			</tr>
		</table>
	</form>
</body>
</html>