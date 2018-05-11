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
<title>绑定奖励配置信息</title>
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
	*{
	margin: 0;
	padding: 0;
	}
</style>
<script src="js/basevalue.js"></script>
<script src="js/jquery-1.11.1.js"></script>
<script type="text/javascript">
</script>
</head>
<body>
    <form name="form1" method="post" action="<%=basePath%>information/updateBindConfig.do" id="form1" onsubmit="return  prevent();">
        <table class="gridtable" width="100%" border="1" align="center" cellpadding="3" cellspacing="0" bordercolor="A4B6D7"
            bgcolor="F2F8FF" class="admin_table">
            <tr bgcolor="#C5D5E4">
                <td class="title_03" colspan="14" align="center">
                    <strong><font color="red" size="5px">绑定奖励配置信息</font></strong></td>
            </tr>
			<tr><td align="right">房卡数量设置：</td><td><input type="number" name="num"  value="${num }"></td></tr>             
             <tr>
             <td align="right">操作：</td>
             	<td align="left"><input type="submit" class="btn btn-success" value="修改" ></td>
             </tr>
        </table>
    </form>
</body>
<script type="text/javascript">
	function prevent() {
		if (confirm('确定要修改房卡数量吗？')) {
			return true;
		}
		window.location.href = "information/toSelectBindConfig.do";
		return false;
	}
</script>
</html>