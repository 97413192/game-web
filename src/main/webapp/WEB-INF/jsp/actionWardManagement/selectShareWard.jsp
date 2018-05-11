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
<title>分享奖励</title>
<base href="<%=basePath%>">
<link href="css/systemSetttings/inc_style.css" rel="stylesheet" type="text/css" />
<style>
	body {
		margin-top: 0px;
	}
</style>
<script src="js/basevalue.js"></script>
<script src="js/jquery-1.11.1.js"></script>
</head>
<body>
    <form name="form1" method="post" action="./AdminModifyLog.aspx?page=10" id="form1">
        <table width="100%" border="1" align="center" cellpadding="3" cellspacing="0" bordercolor="A4B6D7"
            bgcolor="F2F8FF" class="admin_table">
            <tr>
                <td class="title_03" colspan="7" align="center">
                    <strong><font color="red">奖励列表</font></strong></td>
            </tr>
             <tr style="font-weight:bold;font-style:italic;">
             	<td>序号</td>
             	<td>消息id</td>
             	<td>奖励内容</td>
             	<td>奖励数量</td>
             	<td>操作</td>
             </tr>
            	<tr>
	            	<td>1</td>
	                <td>${share.id }</td>
	                <td>元宝</td>
	                <td>${share.prize }</td>
	                <td><a href="actionWard/updatePrize.do?id=1">修改</a></td>
	                </tr>
        </table>
    </form>
</body>

</html>