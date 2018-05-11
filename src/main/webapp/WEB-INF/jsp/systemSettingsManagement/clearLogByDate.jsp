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
<title>日志查询</title>
<base href="<%=basePath%>">
<link  href="css/systemSetttings/inc_style.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
<link rel="stylesheet" type="text/css" href="css/tcal.css" />
<style type="text/css">
table.gridtable {
	font-family: verdana,arial,sans-serif;
	font-size:11px;
	color:#333333;
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
	body{
			margin-top: 0px;
		}
	.red{
			color: red;
		}
</style>
<script src="js/basevalue.js"></script>
<script src="js/jquery-1.11.1.js"></script>
<script type="text/javascript" src="js/tcal.js"></script>
<script type="text/javascript">
	$(function(){
		
		$('#selLog').click(function(){
		
		var startDate = $.trim($('#startDate').val());
			var endDate = $.trim($('#endDate').val());
			var url = "systemSettings/clearLogByDate.do";
			var data = {
				startDate : startDate,
				endDate : endDate
			};
			ok = true;
			if (confirm('确定要删除数据吗？删除后不可恢复')) {
				ok = true;
			} else {
				ok = false;
			}

			if (ok) {
				$.getJSON(url, data, function(result) {
					if (result.status == -1) {
						window.location.href = "toLogin.do";
					}
					if (result.status == 0) {
						confirm("删除成功！");
					}
					if (result.status == 1) {
						confirm("删除失败！");
					}
				});
			}

		});
		$('#cancel').click(function() {
			$('#startDate').val('');
			$('#endDate').val('');
		});
	});
</script>
</head>
<body>
	<div id="div1">
		<table class="gridtable" width="100%" border="1" align="center" cellpadding="3" cellspacing="0" bordercolor="#A4B6D7"
			bgcolor="F2F8FF" class="admin_table2">
			<tr>
				<td class="title_03" align="center">
					<strong>删除日志界面</strong>
				</td>
			</tr>
		</table>
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td style="height: 5px">
				</td>
			</tr>
		</table>
		<table class="gridtable" width="100%" border="1" align="center" cellpadding="3" cellspacing="0" bordercolor="A4B6D7"
			bgcolor="F2F8FF" class="admin_table">
			<tr>
				<td align="right" width="40%">
					起始日期：
				</td>
				<td colspan="2" align="left">
					<input id="startDate" type="text" name="date" class="tcal" value="${startDate }" readOnly/>
				</td>
			</tr>
			
			<tr>
				<td align="right" width="40%">
					结束日期：
				</td>
				<td colspan="2" align="left">
					<input id="endDate" type="text" name="date" class="tcal" value="${endDate }" readOnly/>
				</td>
			</tr>
			<tr>
				<td align="right">
					<input type="button" class="btn btn-success" value=" 删除" id="selLog"/>
				</td>
				<td colspan="2" align="left">
					<input type="button" class="btn btn-success" value="重置 " id="cancel"/>
				</td>
			</tr>
		</table>
	</div>
</body>
</html>