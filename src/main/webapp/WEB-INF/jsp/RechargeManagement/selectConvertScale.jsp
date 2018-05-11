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
<title>查询钻石兑换基本比例</title>
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
		$("#alterBtn").click(function(){
			//获取输入内容
			var convertScale= $("#alter").val().trim();  //钻石兑换金币比例
			var ok = true;
			//检验格式
			if(convertScale == ""){
				alert("输入不能为空！");
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
						alert("修改失败！")
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
					<strong>查询钻石兑换金币比例</strong>
				</td>
			</tr>
			<tr>
				<td align="right" width="40%" style="height:20px;">
					钻石兑换金币比例：
				</td>
				<td colspan="2" align="left"  style="height:20px;">
					<span id="convert">${convertScale.convertScale }</span>					
				</td>
			</tr>
			<tr>
				<td class="title_03" colspan="19">
					<strong>修改钻石兑换金币比例</strong>
				</td>
			</tr>
			<tr>
				<td align="right" width="40%" style="height:20px;">
					输入新的钻石兑换金币比例：
				</td>
				<td colspan="2" align="left"  style="height:20px;">
					<input name="alter" type="text" id="alter" class="put" maxlength="7" size ="10" onkeyup="filterInput();"/>
					<input type="button" name="alterBtn" value=" 修 改 " id="alterBtn" class="put"/>
				</td>
			</tr>
		</table>
	</form>
</body>
</html>