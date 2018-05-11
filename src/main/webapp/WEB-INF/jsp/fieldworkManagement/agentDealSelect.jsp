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
<script type="text/javascript" src="MyDate/WdatePicker.js"></script>
<!-- 验证JS -->
<script src="js/operation.js"></script>
<script type="text/javascript">
		
</script>
</head>
<body>
	<div id="div1">
		<table class="gridtable" width="100%" border="1" align="center" cellpadding="3" cellspacing="0" bordercolor="#A4B6D7"
			bgcolor="F2F8FF" class="admin_table2">
			<tr>
				<td class="title_03" align="center">
					<strong>代理交易记录查询</strong>
				</td>
			</tr>
		</table>
<!-- 		<table width="100%" border="0" cellspacing="0" cellpadding="0"> -->
<!-- 			<tr> -->
<!-- 				<td style="height: 5px"> -->
<!-- 				</td> -->
<!-- 			</tr> -->
<!-- 		</table> -->
		<table class="gridtable"  width="100%" border="1" align="center" cellpadding="3" cellspacing="0" bordercolor="A4B6D7"
			bgcolor="F2F8FF" class="admin_table">
			<c:choose>
				<c:when test="${operatRC =='agent' }">
					<tr>
						<td align="right" width="40%" style="height:20px;">
							汇款人账户：
						</td>
						<td colspan="2" align="left"  style="height:20px;">
							<input type="text" id="raccountImpMsg" class="put" value = "${account }" readonly/>
						</td>
					</tr>
				</c:when>
				<c:otherwise>
					<tr>
						<td align="right" width="40%" style="height:20px;">
							汇款人账户：
						</td>
						<td colspan="2" align="left"  style="height:20px;">
							<input type="text" id="raccountImpMsg" class="put"/>
							<span id="nowAccount"></span>
						</td>
					</tr>
				</c:otherwise>
			</c:choose>
			<tr>
				<td align="right" width="40%" style="height:20px;">
					收款人账户：
				</td>
				<td colspan="2" align="left"  style="height:20px;">
					<input type="text" id="paccountImpMsg" class="put"/>
					<span id="mesPrompt2" class="red"></span>
				</td>
			</tr>
			<tr>
				<td align="right">
					起始日期：
				</td>
				<td colspan="2" align="left">
					<input id="startDate" type="text" name="date" class="tcal tcalInput" value="" readonly="true"> 
				</td>
			</tr>
			
			<tr>
				<td align="right">
					结束日期：
				</td>
				<td colspan="2" align="left">
				<input id="endDate" type="text" name="date" class="tcal tcalInput" value="" readonly="true">
					
				</td>
			</tr>
			<tr>
				<td align="right">
					快速查询
				</td>
				<td colspan="2" align="left"><!-- 
					&nbsp;<input type="button" value=" 今日 " id="today"/>&nbsp;
					<input type="button" value=" 昨日 " id="yesterday"/>&nbsp;
					<input type="button" value=" 本周" id="week"/>&nbsp; -->
					<input type="button" value=" 查询 " id="selectBegin" class="btn btn-info"/>&nbsp;&nbsp;&nbsp;
				</td>
			</tr>
			<!-- 
			<tr>
				<td align="center" colspan="3">
					<input type="button" value=" 查询 " id="selectBegin"/>&nbsp;&nbsp;&nbsp;
				</td>
			</tr>
			 -->
		</table>
	</div>

</body>
</html>