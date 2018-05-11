<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<meta name="renderer" content="webkit">

<title>左边部分网页</title>
<base href="<%=basePath%>">
<link href="css/admin.css" type="text/css" rel="stylesheet" />
<script src="js/jquery.js" type="text/javascript"></script>
<style type="text/css">
* {
	box-sizing: border-box;
}

html {
	font-family: "Lucida Sans", sans-serif;
}

.header {
	background-color: #9933cc;
	color: #ffffff;
	padding: 15px;
}

a:link {
	text-decoration: none;
}

.tda {
	list-style-type: none;
	margin: 0;
	padding: 0;
}

a {
	color: white;
	display: inline-block;
	width: 100%;
	text-align: center;
	margin: 1px auto;
	font-size: 16px;
	font-family: 楷体;
	/* 	font-weight: bold; */
}

a:hover {
	/* 	background: wathet; */
	font-size: 17px;
	font-family: 楷体;
	font-weight: bold;
	color: white;
}

.tda {
	padding: 20px;
	margin-bottom: 10px;
	/*     background-color :#33b5e5; */
	background-color: #5CACEE;
	/*     text-align:center; */
	color: #ffffff;
	border-radius: 10px 10px 10px 10px;
	box-shadow: 0 1px 3px rgba(0, 0, 0, 0.12), 0 1px 2px rgba(0, 0, 0, 0.24);
}

.tda:hover {
	background-color: #0099cc;
}

div#show-leftbar {
	position: fixed;
	background-color: grey;
	top: 50%;
	left: 0;
	width: 15px;
}
</style>
</head>
<body style="background-image: url(images/timg1.jpg);">
				<div style="float: left;">
						<div style="float: left;">
						<table cellspacing="0" cellpadding="0" width="85" border="0">
							<tr id="menu_a1">
								<td><a class="menuChild"
									href="gamePlayer/getAllRealNamePlayer.do?index=1" target="main" >
									<img src="image/所有代理.png" width="35">
										<br>所有实名玩家</a>
								</td>
							</tr>
							<tr height="4">
								<td colspan="2"></td>
							</tr>
						</table>
					</div>
				</div>
				
				<div style="float: left;">
						<div style="float: left;">
						<table cellspacing="0" cellpadding="0" width="85" border="0">
							<tr id="menu_a1">
								<td><a class="menuChild"
									href="gamePlayer/realNameApply.do?index=1" target="main"><img
										src="image/玩家记录.png" width="35">
										<br>实名申请</a>
								</td>
							</tr>
							<tr height="4">
								<td colspan="2"></td>
							</tr>
						</table>
					</div>
				</div>
				
				<div style="float: left;">
						<div style="float: left;">
						<table cellspacing="0" cellpadding="0" width="85" border="0">
							<tr id="menu_a1">
								<td><a class="menuChild"
									href="gamePlayer/applyRecord.do?index=1" target="main"><img
										src="image/玩家记录.png" width="35">
										<br>申请记录</a>
								</td>
							</tr>
							<tr height="4">
								<td colspan="2"></td>
							</tr>
						</table>
					</div>
				</div>
		
		
				<div style="float: left;">
						<div style="float: left;">
						<table cellspacing="0" cellpadding="0" width="85" border="0">
							<tr id="menu_a1">
								<td><a class="menuChild"
									href="login/managementCenter.do" target="_parent"><img
										src="image/推广管理.png" width="35">
										<br>返回首页</a>
								</td>
							</tr>
							<tr height="4">
								<td colspan="2"></td>
							</tr>
						</table>
					</div>
				</div>
	</form>
</body>
</html>