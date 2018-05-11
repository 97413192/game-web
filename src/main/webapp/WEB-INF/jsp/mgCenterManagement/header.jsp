<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page isELIgnored="false" %>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title></title>
 <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
    <meta name="renderer" content="webkit">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
 <style type="text/css">
 a{ text-decoration:none; }
div#hide-leftbar {
/* 	position: fixed; */
/* 	background-color: grey; */
/* 	left: 15%; */
/* 	width: 5px; */
}
*{
margin: 0;
padding: 0;
}

    </style>
<base href="<%=basePath%>">
<link href="" type="text/css" rel="stylesheet" /></head>
<script src="js/jquery.js" type="text/javascript"></script>
<body>
<!-- <div id="hide-leftbar"> -->
<!-- 		<span style="cursor: pointer;">隐藏</span> -->
<!-- 	</div> -->
 <table cellspacing="0" cellpadding="0" width="100%" background="images/bg.jpg" 
        border="0">
        <tr height="56">
<!--             <td width="260" style="padding:0px; margin:0px; vertical-align:top;"> -->
<!--             <div id="hide-leftbar"> -->
<!--             <h2 id="hide-leftbar" style="color: white; margin-top:12px;margin-left: 10px;cursor: pointer;">网站管理中心<span style="font-size: 1px">ver.3.0</span></h2> -->
<!--                 <img height="56" src="images/header_left.jpg" width="250"> -->
<!--                 </div> -->
<!--             </td> -->
            <td style="font-weight: bold; color: #fff; padding-top: 20px;font-size:12px; width:268px" align="left">
                                              <span id="span_name">${admin }</span>
                 <c:if test="${power == 2}">
       				<c:choose>
						<c:when test="${agent.category == 1}"><span style="color: #fff">一级代理商</span></c:when>
       					<c:when test="${agent.category == 2}"><span style="color: #fff">二级代理商</span></c:when>
       					<c:when test="${agent.category == 3}"><span style="color: #fff">三级代理商</span></c:when>
       					<c:otherwise></c:otherwise>
       				</c:choose>
       				<span style="color: #fff">房卡数:${agent.roomCard} </span>
                 </c:if>
                 &nbsp;
<!--                 <a style="color: #fff" href="login/managementCenter.do" target="_parent">回首页</a>  -->
<%--                 <c:if test="${power != 3}"> --%>
<!--                  	<a style="color: #fff" href="mgmentConter/adit.do" target="main">修改登录密码</a> -->
<%--                  </c:if> --%>
<!--                 <a style="color: #fff" onclick="if (confirm('确定要退出吗？')) return true; else return false;" -->
<!--                     href="mgmentConter/dropOutSystem.do" target="_parent">退出系统</a> -->
            </td>
			<td style="text-align: left; padding-right: 20px; font-size: 13px;">
				<c:if test="${power != 3}">
					<a style="color: #fff" href="mgmentConter/adit.do" target="main">修改密码</a>
				</c:if>
			</td>
			<td style="text-align: right; padding-right: 20px; font-size: 13px;">
				<a style="color: #fff"
				onclick="if (confirm('确定要退出吗？')) return true; else return false;"
				href="mgmentConter/dropOutSystem.do" target="_parent">退出系统</a>
			</td>
		</tr>
    </table>
    <table cellspacing="0" cellpadding="0" width="100%" border="0">
        <tr background="images/bg.jpg" height="4">
            <td>
            </td>
        </tr>
    </table>
    <script type="text/javascript">
	 //隐藏/显示左边菜单栏
	$("#show-leftbar").hide();//默认进入首页时隐藏显示链接
	//点击隐藏链接事件
	$("#hide-leftbar").click(function(){
		if(window.parent.document.getElementById("full").cols == "180,*")
		{window.parent.document.getElementById("full").cols = "0,*";$("#show-leftbar").show();$("#hide-leftbar").show();}
		else{
			window.parent.document.getElementById("full").cols = "180,*";
			$("#show-leftbar").hide();
			$("#hide-leftbar").show();
		}

	});
	//点击显示链接事件
	$("#show-leftbar").click(function(){
		if(window.parent.document.getElementById("full").cols == "180,*")
		{window.parent.document.getElementById("full").cols = "0,*";$("#show-leftbar").hide();$("#hide-leftbar").show();}
		else{
			window.parent.document.getElementById("full").cols = "180,*";
			$("#show-leftbar").show();
			$("#hide-leftbar").hide();
		}
		
	});
	</script>
</body>
</html>
  