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
<title>修改留桌查看配置</title>
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
	//检查所有
	function check(){
		var inputs = document.getElementsByClassName("put");
		//alert("dsfsdf");
		var count = 0;
		for(var i=0; i<inputs.length; i++){
			var str = inputs[i].value;
			//alert(str);
			if(str == "" || str == null){
				count++;
			}
		}
		if(count>0){
			document.all.AlterWaitDesk.disabled = "disabled";
		}else{
			document.all.AlterWaitDesk.disabled = "";
		}
	}
	$(function(){
		//给创建按钮绑定单击事件
		$("#AlterWaitDesk").click(function(){
			alert("asdfasd");
			//获取输入的参数
			var id = $("#id").html();
			var time = $("#time").val().trim();
			var gold = $("#gold").val().trim();
			var typeName = $("#typeName").val().trim();
			var typeId = $("#typeId").val().trim();
			//判断格式
			var ok = true;
			if(id == ""){
				alert("id不能为空！");
				ok = false;
			}
			if(ok){
				//发送ajax请求
				$.ajax({
					url:"waitDesk/alterWaitDesk.do",
					type:"post",
					data:{"id":id,"time":time,"gold":gold,"typeName":typeName,"typeId":typeId},
					dataType:"json",
					success:function(result){
						if(result.status == 0){
							alert(result.msg);
						}else if(result.status == 1){//登陆服务连接失败
							alert(result.msg);
						}else if(result.status == 2){//判断传入的typeName和typeId不存在
							alert(result.msg);
						}else if(result.status == 3){//通过登陆服务增加桌子错误
							alert(result.msg);
						}else if(result.status == 4){//未知错误
							alert(result.msg);
						}else if(result.status == -1){
							window.location.href="login/toLogin.do";
						}
					},
					error:function(){
						alert("增加房间失败！")
					}
				});
			}
		});
	});
</script>
</head>
<body>
	<form name="form1" method="post" action="" id="form1">
		<table width="100%" border="1" align="center" cellpadding="3" cellspacing="0" bordercolor="#A4B6D7"
			bgcolor="F2F8FF" class="admin_table2">
			<tr>
				<td class="title_03">
					<strong>查看配置管理</strong>
				</td>
			</tr>
		</table>
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td style="height: 5px">
				</td>
			</tr>
		</table>
		<table width="100%" border="1" align="center" cellpadding="3" cellspacing="0" bordercolor="A4B6D7"
			bgcolor="F2F8FF" class="admin_table">
			<tr>
				<td class="title_03" colspan="19">
					<strong>修改留桌配置</strong>
				</td>
			</tr>
			<tr>
				<td align="right" width="40%" style="height:20px;">
					id：
				</td>
				<td colspan="2" align="left"  style="height:20px;">
					<spn id="id">${id }</spn>
					<span id="timemsg">(留桌设置的编号id)</span>
				</td>
			</tr>
			<tr>
				<td align="right" width="40%" style="height:20px;">
					时间：
				</td>
				<td colspan="2" align="left"  style="height:20px;">
					<input name="time" type="text" id="time" class="put" maxlength="12" 
						onkeyup="filterInput();check();"/>
					<span id="timemsg">留桌的时间，时间使用单位秒</span>
				</td>
			</tr>
			<tr>
				<td align="right">
					金币：
				</td>	
				<td colspan="2" align="left">
					<input name="gold" type="text" id="gold" class="put" maxlength="12" 
						onkeyup="filterInput();check();"/>
					<span id="goldmsg">留桌消耗的金币</span>
				</td>
			</tr>
			<tr>
				<td align="right">
					类型名称：
				</td>
				<td colspan="2" align="left">
					<input name="typeName" type="text" id="typeName" class="put" maxlength="12" 
						onkeyup="check();"/>
					<span id="typeNamemsg">留桌的类型名称，用于调用确认对应房间使用的是否是对应的留桌配置</span>
				</td>
			</tr>
			<tr>
				<td align="right">
					类型编号：
				</td>
				<td colspan="2" align="left">
					<input name="typeId" type="text" id="typeId" class="put" maxlength="12" 
						onkeyup="filterInput();check();"/>
					<span id="typeIdmsg">留桌的类型编号，用于调用确认对应房间使用的是否是对应的留桌配置</span>
				</td>
			</tr>

			<tr>
				<td align="right">
					&nbsp;
				</td>
				<td colspan="2" align="left">
					<input type="button" name="AlterWaitDesk" value=" 创 建 " id="AlterWaitDesk" disabled = "disabled"/>
					<span id="ErrorLabel" style="color:Red;"></span>
				</td>
			</tr>
		</table>
	</form>
</body>
</html>