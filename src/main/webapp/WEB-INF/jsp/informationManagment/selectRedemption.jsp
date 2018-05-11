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
<title>所有消息内容</title>
<base href="<%=basePath%>">
<link href="css/systemSetttings/inc_style.css" rel="stylesheet" type="text/css" />
<style>
	body {
		margin-top: 0px;
	}
</style>
<script src="js/basevalue.js"></script>
<script src="js/jquery-1.11.1.js"></script>
<script>
</script>
</head>
<body>
    <form name="form1" method="post" action="./AdminModifyLog.aspx?page=10" id="form1">
        <table width="100%" border="1" align="center" cellpadding="3" cellspacing="0" bordercolor="A4B6D7"
            bgcolor="F2F8FF" class="admin_table">
            <tr>
                <td class="title_03" colspan="7" align="center">
                    <strong><font color="red">商品兑换列表</font></strong></td>
            </tr>
             <tr style="font-weight:bold;font-style:italic;">
             	<td>序号</td>
             	<td>商品id</td>
             	<td>商品标题</td>
             	<td>消耗的积分</td>
             	<td>兑换的房卡</td>
             	<td>修改</td>
             	<td>删除商品</td>
             </tr>
            <c:forEach items="${list }" var="log" varStatus="s">
            	<tr>
	            	<td>${s.index+1 }</td>
	                <td>${log.id }</td>
	                <td>${log.title }</td>
	                <td>${log.integral }</td>
	                <td>${log.fk }</td>
	                <td>
	                	<a href="information/toUpdateRedemption.do?id=${log.id }&title=${log.title }&integral=${log.integral }&fk=${log.fk }">修改</a>
	                </td>
	                 <td>
	                	<a href="information/delRedemption.do?id=${log.id }">删除</a>
	                </td>
	                </tr>
            </c:forEach>
            <tr><td><a href="information/toAddRedemption.do">增加商品配置</a></td></tr>
        </table>
    </form>
</body>

</html>