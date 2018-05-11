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
<title>删除玩家</title>
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
					<strong>删除玩家</strong>
				</td>
			</tr>
			<tr>
				<td align="right" width="40%" style="height:20px;">
					用户名：
				</td>
				<td colspan="2" align="left"  style="height:20px;">
					<input name="u" type="text" id="u" class="put" maxlength="12" onblur="checkusername()" /><div id="usernamemsg" class="red">
					</div>
				</td>
			</tr>
			
			<tr>
				<td align="right">
					&nbsp;
				</td>
				<td colspan="2" align="left">
					<input type="submit" name="CreateUser" value="确定删除" id="CreateUser" class="put" onclick="confirm('确认删除？')"/>
					<span id="ErrorLabel" style="color:Red;"></span>
				</td>
			</tr>
		</table>
	</form>
</body>
</html>