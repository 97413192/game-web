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
<title>添加商品兑换信息失败</title>
<base href="<%=basePath%>">
<link href="css/systemSetttings/inc_style.css" rel="stylesheet" type="text/css" />
<style>
	body {
		margin-top: 0px;
		background-color: #F2F8FF;
		font-family : 微软雅黑,宋体;
		font-size : 0.9em;
	}
	table{
		overflow:scroll
	 }
</style>
<script src="js/basevalue.js"></script>
<script src="js/jquery-1.11.1.js"></script>
<script type="text/javascript">

</script>
</head>
<body>
    <form name="form1" method="post" action="<%=basePath%>information/addRedemption.do" id="form1">
        
             <tr>
             	<td>查询失败：未获得服务器连接！</td>
             </tr>
       
    </form>
</body>

</html>