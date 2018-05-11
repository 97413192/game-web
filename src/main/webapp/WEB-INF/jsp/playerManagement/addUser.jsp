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
<title>添加玩家</title>
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
<script>
	$(function(){
		$("#CreateUser").click(function(){
			//获取输入内容
			var user= $("#u").val().trim();
			var pwd = $("#pwd").val().trim();
			var newpwd =$("#newpwd").val().trim();
			//获取操作系统
			var system = detectOS();
			//清除上次提示内容
			$("#usernamemsg").html("");
			$("#userpwdmsg").html("");
			$("#usernewpwdmsg").html("");
			var ok = true;
			//检验格式
			if(user == ""){
				$("#usernamemsg").html("用户名不能为空！");
				ok = false;
			}else if(!/^[0-9a-zA-Z\\.]{9,64}$/.test(user)){
				//alert("名字");
				$("#usernamemsg").html("用户名长度9～64个字符，由字母、数字组成");
				ok = false;
			}
			if(pwd == ""){
				$("#userpwdmsg").html("密码不能为空！");
				ok = false;
			}else if(!/^[0-9a-zA-Z\\.]{6,18}$/.test(pwd)){
				$("#userpwdmsg").html("密码为6-18个数字、字母组成");
				ok = false;
			}else if(pwd != newpwd){
				$("#usernewpwdmsg").html("两次输入的密码不一致");
				ok = false;
			}
			//发送ajax请求
			if(ok){
				$.ajax({
					url:"gamePlayer/addPlayer.do",
					type:"post",
					data:{"name":user,"pwd":pwd,"system":system},
					dataType:"json",
					contentType:'application/json;charset=UTF-8',
					success:function(result){
						//result就是服务器返回的JSON结果
						if(result.status==0){//创建成功
							alert(result.msg);
						}else if(result.status == 1){//创建失败
							alert(result.msg);
						}else if(result.status == 2){//登录服务连接失败
							alert(result.msg);
						}else if(result.status == 3){//名字格式错误
							$("#usernamemsg").html(result.msg);
						}else if(result.status == 4){//密码格式错误
							$("#userpwdmsg").html(result.msg);
						}else if(result.status == 5){//未知错误
							alert(result.msg);
						}else if(result.status == -1){
							window.location.href="login/toLogin.do";
						}
						//清空输入框中信息
						$("#u").val("");
						$("#pwd").val("");
						$("#newpwd").val("");
					},
					error:function(){
						alert("增加玩家失败！")
					}
					
				});
			}
		});
	});
</script>
</head>
<body>
	<form name="form1" method="post" action="./CreateUser.aspx" id="form1" onsubmit="return checkform(this)">
		<table width="100%" border="1" align="center" cellpadding="3" cellspacing="0" bordercolor="#A4B6D7"
			bgcolor="F2F8FF" class="admin_table2">
			<tr>
				<td class="title_03">
					<strong>会员管理</strong>
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
					<strong>新增玩家</strong>
				</td>
			</tr>
			<tr>
				<td align="right" width="40%" style="height:20px;">
					用户名：
				</td>
				<td colspan="2" align="left"  style="height:20px;">
					<input name="u" type="text" id="u" class="put" maxlength="12" onblur="checkusername()" />
					<span id="usernamemsg" class="red"></span>
				</td>
			</tr>
			<tr>
				<td align="right">
					密码：
				</td>
				<td colspan="2" align="left">
					<input name="pwd" type="password" id="pwd" class="put" maxlength="12" onblur="checkpwd()" />
					<span id="userpwdmsg" class="red"></span>
				</td>
			</tr>
			<tr>
				<td align="right">
					确认密码：
				</td>
				<td colspan="2" align="left">
					<input name="pwd" type="password" id="newpwd" class="put" maxlength="12" />
					<span id="usernewpwdmsg" class="red"></span>
				</td>
			</tr>
			<tr>
				<td align="right">
					&nbsp;
				</td>
				<td colspan="2" align="left">
					<input type="button" name="CreateUser" value=" 创 建 " id="CreateUser" class="put" /><span id="ErrorLabel" style="color:Red;"></span>
				</td>
			</tr>
	
		</table>
	</form>
	<script type="text/javascript">
		function checkusername() {
			var txtUserName = document.getElementById('u');
			var txtmsg = document.getElementById('usernamemsg');
			if (txtUserName.value.length == 0) {
				txtmsg.innerHTML = "用户名不能为空！";
				return false;
			}
			if (!/^[0-9a-zA-Z\\.]{9,64}$/.test(txtUserName.value)) {
				txtmsg.innerHTML = "用户名长度9～64个字符，中文，由字母、数字组成";
				return false;
			}

			txtmsg.innerHTML = "";
			return true;
		}
		function checkpwd() {

			var txtPwd = document.getElementById('pwd');
			var txtmsg = document.getElementById('userpwdmsg');
			if (txtPwd.value.length == 0) {
				txtmsg.innerHTML = "密码不能为空！";

				return false;
			}
			if (!/^[0-9a-zA-Z\\.]{6,18}$/.test(txtPwd.value)) {
				txtmsg.innerHTML = "密码为6-18个数字、字母组成！";

				return false;
			}
			txtmsg.innerHTML = "";
			return true;
		}
		/*
		function checkform(f) {

			if (!checkusername()) {
				document.getElementById('u').focus();
				return false;
			}
			if (!checknickname()) {
				document.getElementById('n').focus();
				return false;
			}
			if (!checkpwd()) {
				document.getElementById('pwd').focus();
				return false;
			}

			return true;
		}
		*/
	</script>
</body>
</html>