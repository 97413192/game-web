<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page isELIgnored="false" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>管理员修改密码</title>
<base href="<%=basePath%>">
<!-- <link href="css/Inc_style.css" rel="stylesheet" type="text/css" /> -->
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
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}
</style>
<script src="js/jquery-1.11.1.js"></script>
<script type="text/ecmascript" language="javascript">
	$(function(){
		//给修改按钮绑定单击事件
		$("#Button2").click(function(){
			//获取请求参数
			var AdminPwd = $("#AdminPwd").val().trim();
			var AdminPwd1 = $("#AdminPwd1").val().trim();
			var AdminPwd2 = $("#AdminPwd2").val().trim();
			//检测参数格式
			//清空以前提示消息
			$("#text").html("");
			$("#text1").html("");
			$("#text2").html("");
			var ok = true;//是否通过检测
			if(AdminPwd == ""){
				$("#text").html("密码不能为空！");
				ok = false;
			}
			if(AdminPwd1 == ""){
				$("#text1").html("密码不能为空！");
				ok = false;
			}
			if(AdminPwd2 == ""){
				$("#text2").html("密码不能为空！");
				ok = false;
			}
			
			if (confirm('请再次确认是否修改密码？')) {
				ok = true;
			} else {
				ok = false;
			}
			//发送ajax请求
			if (ok && AdminPwd1 != AdminPwd2) {
				$("#text2").html("两次输入的密码不一样！！");
			} else if (ok) {
				$.ajax({
					url : "mgmentConter/aditPwd.do",
					type : "post",
					data : {
						"AdminPwd" : AdminPwd,
						"AdminPwd1" : AdminPwd1
					},
					dataType : "json",
					success : function(result) {
						//result就是服务器返回的JSON结果
						if (result.status == 0) {//修改成功
							alert(result.msg);
							$("#AdminPwd").val("");
							$("#AdminPwd1").val("");
							$("#AdminPwd2").val("");
						} else if (result.status == 1) {//修改失败
							alert(result.msg);
						} else if (result.status == 2) {
							alert(result.msg);
						} else if (result.status == 3) {
							alert(result.msg);
						} else if (result.status == 4) {
							alert(result.msg);
						} else if (result.status == 5) {
							alert(result.msg);
						} else if (result.status == -1) {
							window.location.href = "login/toLogin.do";
						}
					},
					error : function() {
						alert("修改密码失败！");
					}
				});
			}
		});
	});
</script>
</head>
<body>
	<div align="center">
		<form name="Login" method="POST" action="./Edit_AdminUsers.aspx" id="Login" onsubmit="return CheckForm();">
			<table class="gridtable" width="100%" border="1" align="center" cellpadding="3" cellspacing="0" bordercolor="A4B6D7" bgcolor="F2F8FF" class="admin_table">
		        <tr>
		            <td colspan="2" class="title_03" align="center">
		                <strong>修改个人密码</strong></td>
		        </tr>
		        <tr>
		            <td colspan="2" align="center">
		                <span id="lblMsg" style="color:Red;font-weight:bold;"></span></td>
		        </tr>
		        <tr>
		            <td align="right" width="25%">用户名：</td>
		            <td align="left">${admin }</td>
		        </tr>
		        <tr>
		            <td align="right" width="25%">旧密码：</td>
		            <td align="left">
		                <input name="AdminPwd" type="password" maxlength="20" id="AdminPwd" class="put" />&nbsp;&nbsp;
		                <span id="text" style="color:red"></span>
		            </td>
		        </tr>
		        <tr>
		            <td align="right" width="25%">新密码：</td>
		            <td align="left">
		                <input name="AdminPwd1" type="password" maxlength="20" id="AdminPwd1" class="put" />&nbsp;&nbsp;
		                <span id="text1" style="color:red"></span>
		            </td>
		        </tr>
		        <tr>
		            <td align="right" width="25%"> 确认密码：</td>
		            <td align="left">
		                <input name="AdminPwd2" type="password" maxlength="20" id="AdminPwd2" class="put" />&nbsp;&nbsp;
		                <span id="text2" style="color:red"></span>
		            </td>
		        </tr>
		        <tr>
		            <td>
		                &nbsp;
		            </td>
		            <td align="left">
		                <input type="button" name="Button2" class="btn btn-success" value="确定修改" id="Button2" class="put" />
		            </td>
	        	</tr>
	    	</table>
		</form>
	</div>
</body>
</html>