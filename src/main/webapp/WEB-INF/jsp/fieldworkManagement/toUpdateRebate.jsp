<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title></title>
<base href="<%=basePath%>">
<link href="css/systemSetttings/inc_style.css" rel="stylesheet"
	type="text/css" />
	<link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
<style type="text/css">
* {
	margin: 0;
	padding: 0;
}

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
}

.red {
	color: red;
}
</style>
<script src="js/basevalue.js"></script>
<script src="js/jquery-1.11.1.js"></script>
<script type="text/javascript">
	//输入框只能输入数字
	function filterInput(){
		if (event.type.indexOf("key") != -1){
			var re = /37|38|39|40/g
			if (event.keyCode.toString().match(re)){
				return false
			}
		}
		event.srcElement.value = event.srcElement.value.replace(/[^\-?\d.]/g,'')
	}
	
	function update(data){
		var fd=$("#fd"+data).val().trim();
		var ok = true;
		if(fd==''){
			alert("不能为空!");
			ok = false;
		}
		if (confirm('确定要修改代理的返点比例吗？')) {
			ok = true;
		} else {
			location.reload();
			ok = false;
		}
		//alert(fd);
		
	if (ok) {
			$.ajax({
				url : "agent/updateRebate.do",
				type : "post",
				data : {
					"rebate" : fd,
					"grade" : data
				},
				dataType : "json",
				success : function(result) {
					if (result.status == 0) {
						alert(result.msg);
					} else if (result.status == 1) {
						alert(result.msg);
					} else if (result.status == -1) {
						window.location.href = "login/toLogin.do";
					}
				},
				error : function() {
					alert("修改失败！")
				}
			});
		}
	}
</script>
</head>
<body>
	<form name="form1" method="post" action="./CreateUser.aspx" id="form1"
		onsubmit="return checkform(this)">
		<table width="100%" border="1" align="center" cellpadding="3"
			cellspacing="0" bordercolor="A4B6D7" bgcolor="F2F8FF"
			class="gridtable">
			<tr>
				<td class="title_03" colspan="20" align="center"><strong><font
						color="red">修改代理商返点比例</font></strong></td>
			</tr>
			<tr>
				<td class="title_03" align="right" style="width: 40%">
					一级代理返点比例：</td>
				<td class="title_03" align="left"><input name="alter"
					type="text" id="fd1" value="${rebate1}" class="put" maxlength="15"
					size="10" onkeyup="filterInput();" /> <input type="button"
					name="alterBtn" value=" 修 改 " onclick="update(1);" class="btn btn-danger" /></td>
			</tr>
			<tr>
				<td class="title_03" align="right" style="width: 40%">
					二级代理返点比例：</td>
				<td class="title_03" align="left"><input name="alter"
					type="text" id="fd2" value="${rebate2}" class="put" maxlength="15"
					size="10" onkeyup="filterInput();" /> <input type="button"
					name="alterBtn" value=" 修 改 " onclick="update(2);" class="btn btn-danger" /></td>
			</tr>
			<tr>
				<td class="title_03" align="right" style="width: 40%">
					三级代理返点比例：</td>
				<td class="title_03" align="left"><input name="alter"
					type="text" id="fd3" value="${rebate3}" class="put" maxlength="15"
					size="10" onkeyup="filterInput();" /> <input type="button"
					name="alterBtn" value=" 修 改 " onclick="update(3);" class="btn btn-danger" /></td>
			</tr>

		</table>
	</form>
</body>
</html>