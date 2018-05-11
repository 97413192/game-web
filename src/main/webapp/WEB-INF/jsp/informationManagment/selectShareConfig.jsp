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
<title>分享配置信息</title>
<base href="<%=basePath%>">
<link href="css/systemSetttings/inc_style.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
<style>
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
	/* $(function(){
		$("#select").val("${map.category}");
	}); */

</script>
</head>
<body>
    <form name="form1" method="post" action="<%=basePath%>information/updateShareConfig.do" id="form1">
        <table class="gridtable" width="100%" border="1" align="center" cellpadding="3" cellspacing="0" bordercolor="A4B6D7"
            bgcolor="F2F8FF" class="admin_table">
            <tr bgcolor="#C5D5E4">
                <td class="title_03" colspan="14" align="center">
                    <strong><font color="red">分享配置信息</font></strong></td>
            </tr>
             <tr>
             	<td width="200px">ID:</td><td>${map.id }<input type="hidden" name="id" value="${map.id }" ></td>
             </tr>
              <tr>
             	<!-- <td>奖品种类:</td><td>
	             	<select id="select" name="category" >
	             		<option value="1">房卡</option>
	             		<option value="2">金币</option>
	             	</select>
             	</td> -->
             	<td>奖品种类:</td><td>房卡<input type="hidden" name="category" value="1" ></td>
             </tr>
             <tr >
             	<td>奖品数量:</td><td><input type="number" name="prize" value="${map.prize }" ></td>
             </tr>
              <tr>
             	<td>每天可分享次数:</td><td><input type="number" name="shareNumber" value="${map.shareNumber }" ></td>
             </tr>
             <tr >
             	<td>分享时间间隔（分钟）:</td><td><input type="number" name="intervalTime" value="${map.intervalTime }" ></td>
             	
             </tr>
             <tr>
             	<td><input type="submit" value="修改" class="btn btn-success"></td>
             </tr>
            	
        </table>
    </form>
</body>

</html>