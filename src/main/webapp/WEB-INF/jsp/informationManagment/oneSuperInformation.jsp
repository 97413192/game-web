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
<title>玩家信息</title>
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
<script>
	function changeTrColor(obj){
		 var _table=obj.parentNode;
		 for (var i=0;i<_table.rows.length;i++){
		 	_table.rows[i].style.backgroundColor="";
		 }    
		 obj.style.backgroundColor="#F5DEB3";
	}
</script>
</head>
<body>
    <form name="form1" method="post" action="" id="form1">
        <table class="gridtable" width="100%" border="1" align="center" cellpadding="3" cellspacing="0" bordercolor="A4B6D7"
            bgcolor="F2F8FF" class="admin_table">
            <tr bgcolor="#C5D5E4">
                <td class="title_03" colspan="14" align="center">
                    <strong><font color="red">玩家信息</font></strong></td>
            </tr>
             <tr style="font-weight:bold;font-style:italic;">
             	<td>序号</td>
             	<td>玩家gameId</td>
             	<td>玩家昵称</td>
             	<td>超端</td>
             </tr>
           
            	<tr style="cursor:hand " onmousemove="changeTrColor(this)">
	            	<td>1</td>
	                <td>${gameIdOne}</td>
	                <td>${pNickName }</td>
	                <c:choose>
						<c:when test="${isSuperClient == 1 }">
                 			<td>
                 				<a class="btn btn-success" href="information/updateSuperPortPage.do?gameId=${gameIdOne }">超端</a>
                 			</td>
                 		</c:when> 
 						<c:otherwise>
 							<td>
 								<a class="btn btn-success" href="information/updateSuperPortPage.do?gameId=${gameIdOne }">不是超端</a>
 							</td>
 						</c:otherwise> 
					</c:choose>
 								
	             </tr>
        </table>
    </form>
</body>

</html>