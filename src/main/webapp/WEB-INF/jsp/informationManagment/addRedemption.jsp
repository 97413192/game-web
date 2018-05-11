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
<title>添加商品兑换信息</title>
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
	$(function(){
		$("#select").val("${map.category}");
	});

</script>
</head>
<body>
    <form name="form1" method="post" action="<%=basePath%>information/addRedemption.do" id="form1">
        <table width="100%" border="1" align="center" cellpadding="3" cellspacing="0" bordercolor="A4B6D7"
            bgcolor="F2F8FF" class="admin_table">
            <tr bgcolor="#C5D5E4">
                <td class="title_03" colspan="14" align="center">
                    <strong><font color="red">添加商品兑换信息</font></strong></td>
            </tr>
             <tr>
             	<td width="200px">商品ID:</td><td><input type="number" name="id" ></td>
             </tr>
              <tr>
             	<td>商品标题:</td><td><input type="text" name="title"  ></td>
             </tr>
             <tr >
             	<td>消耗的积分数量:</td><td><input type="number" name="integral" ></td>
             </tr>
              <tr>
             	<td>消耗积分可兑换的房卡数量:</td><td><input type="number" name="fk" ></td>
             </tr>
             <tr>
             	<td><input type="submit" value="添加" ></td>
             </tr>
            	
        </table>
    </form>
</body>

</html>