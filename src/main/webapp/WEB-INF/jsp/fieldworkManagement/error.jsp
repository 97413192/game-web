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
<title>代理商个人信息</title>
<base href="<%=basePath%>">
<link  href="css/systemSetttings/inc_style.css" rel="stylesheet" type="text/css" />
<style type="text/css">
	body{
			margin-top: 0px;
			font-size: 12px;
		    font-family: Tahoma,Geneva,sans-serif;
		    background-position: top;
		    background-repeat: repeat-x;
    		background-color: #0063a0;
		}
	
</style>
<script src="js/basevalue.js"></script>
<script src="js/jquery-1.11.1.js"></script>
<script type="text/javascript">
	var timer;
	//启动跳转的定时器
	function startTimes() {
	    timer = window.setInterval(showSecondes,1000);
	}
	
	var i = 5;
	function showSecondes() {
	    if (i > 0) {
	        i--;
	        document.getElementById("secondes").innerHTML = i;
	    }
	    else {
	        window.clearInterval(timer);
	        location.href = "agent/findAll.do?index=-1";
	    }
	}
	
	//取消跳转
	function resetTimer() {
	    if (timer != null && timer != undefined) {
	        window.clearInterval(timer);
	        location.href = "agent/findAll.do?index=-1";
	    }
	}
</script>
</head>
<body onload="startTimes();">
	<h1 id="error">
	        遇到错误，&nbsp;<span id="secondes">5</span>&nbsp;秒后将自动跳转，立即跳转请点击&nbsp;
            <a  href="javascript:resetTimer();">返回</a>
    </h1>
</body>
</html>