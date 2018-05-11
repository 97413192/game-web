<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page isELIgnored="false" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>修改管理员</title>
<base href="<%=basePath%>">
<link href="css/systemSettings/Inc_style.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
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
body {
	
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}
.color{
	color:red;
}
</style>
<script src="js/basevalue.js"></script>
<script src="js/jquery-1.11.1.js"></script>
<script type="text/ecmascript" language="javascript">
	$(function(){
		//给确定添加加单击事件
		$("#Button1").click(function(){
			//获取输入内容
			var name = $("#AdminName").val().trim();
			//获取当前系统
			var system = detectOS();
			//清除上次提示内容
			$("#name").html("");
			//检查格式
			var ok = true;
			if(name == ""){
				$("#name").html("用户名不能为空");
				ok = false;
			}
			if(ok && confirm("你确定要删除此管理员"+name)){
				//发送ajax请求
				$.ajax({
					url:"systemSettings/delete.do",
					type:"post",
					data:{"name":name,"system":system},
					dataType:"json",
					success:function(result){
						if(result.status==0){//修改成功
							alert(result.msg);
							//清空输入框中内容
							$("#AdminName").val("");
						}else if(result.status == 1){//修改失败
							alert(result.msg);
						}else if(result.status == 2){//用户名不存在
							alert(result.msg);
						}else if(result.status == 3){//权限不够
							alert(result.msg);
						}else if(result.status == 4){//未知错误
							alert(result.msg);
						}else if(result.status == -1){
							window.location.href="login/toLogin.do";
						}
					},
					error:function(){
						alert("修改管理员失败");
					}
				});
			}
			
		});	
	});
</script>
</head>
<body>
	<div align="center">
		<form name="Login" method="post" action="./Edit_AdminUser.aspx?id=3" id="Login" onsubmit="return CheckForm();">
			<input type="hidden" name="__VIEWSTATE" id="__VIEWSTATE" value="/wEPDwUKLTk2MTg4MDU4Mw9kFgICAw9kFgICBQ9kFgJmD2QWAgIBDxBkDxYTZgIBAgICAwIEAgUCBgIHAggCCQIKAgsCDAINAg4CDwIQAhECEhYTEAUR6aaW6aG1RkxBU0jnrqHnkIYFAjAxZxAFEueOqeWutueVmeiogOeuoeeQhgUCMDJnEAUS572R56uZ5Y+C5pWw6K6+572uBQIwM2cQBRLnlKjmiLfnrqHnkIbkuK3lv4MFAjA0ZxAFEuWFheWAvOeuoeeQhuS4reW/gwUCMDVnEAUS5ri45oiP566h55CG5Lit5b+DBQIwNmcQBRLovazotKbnrqHnkIbkuK3lv4MFAjA4ZxAFEuiusOW9leafpeivouS4reW/gwUCMDlnEAUS5paw6Ze7566h55CG5Lit5b+DBQIxMGcQBRLlpZblk4HnrqHnkIbkuK3lv4MFAjExZxAFEuezu+e7n+mFjee9ruS4reW/gwUCMTJnEAUS5o6o5bm/57O757uf566h55CGBQIxM2cQBRLpnZPlj7fnrqHnkIbkuK3lv4MFAjE0ZxAFFemtheWKm+WAvOWFkeaNoueuoeeQhgUCMTZnEAUS6YGT5YW3566h55CG5Lit5b+DBQIxNWcQBRXku6PnkIbllYbluJDlj7fnrqHnkIYFAjE3ZxAFGOWPkemAgeS/oeaBr+euoeeQhuS4reW/gwUCMThnEAUS5q+U6LWb566h55CG5Lit5b+DBQIxOWcQBRLmnI3oo4XnrqHnkIbkuK3lv4MFAjIwZ2RkGAEFHl9fQ29udHJvbHNSZXF1aXJlUG9zdEJhY2tLZXlfXxYUBQ9jaGtMaXN0TW9kZWxzOjAFD2Noa0xpc3RNb2RlbHM6MQUPY2hrTGlzdE1vZGVsczoyBQ9jaGtMaXN0TW9kZWxzOjMFD2Noa0xpc3RNb2RlbHM6NAUPY2hrTGlzdE1vZGVsczo1BQ9jaGtMaXN0TW9kZWxzOjYFD2Noa0xpc3RNb2RlbHM6NwUPY2hrTGlzdE1vZGVsczo4BQ9jaGtMaXN0TW9kZWxzOjkFEGNoa0xpc3RNb2RlbHM6MTAFEGNoa0xpc3RNb2RlbHM6MTEFEGNoa0xpc3RNb2RlbHM6MTIFEGNoa0xpc3RNb2RlbHM6MTMFEGNoa0xpc3RNb2RlbHM6MTQFEGNoa0xpc3RNb2RlbHM6MTUFEGNoa0xpc3RNb2RlbHM6MTYFEGNoa0xpc3RNb2RlbHM6MTcFEGNoa0xpc3RNb2RlbHM6MTgFEGNoa0xpc3RNb2RlbHM6MTh04u0nEcr8KLTJLoVrbK60BHOQ/S+/Obd20tjzsVmsXA==" />
			<input type="hidden" name="__VIEWSTATEGENERATOR" id="__VIEWSTATEGENERATOR" value="19F85299" />
			<input type="hidden" name="__EVENTVALIDATION" id="__EVENTVALIDATION" value="/wEdABdP4PK/Mogn1H5jov1bdrU4XQ0lSqDtIWz/xIoI1EWqL9dRgMatglN60AjQSQIHb7bWth+YD0dahuhzF78PJm2E4we9YAtIreMw7q6aFOVOvCu+r0RCTjRcCFil3a8XWib0rQBLAOlxyZOePrnpdkTdLo2/ID+dHX362VuJS2cVLIr+JHsjaRiQiwXAHsYVPmHWsuBvJBfndZT6QKzHDUuTwPywoYNPPlW9T8srkEemWLZgj4LuIr/wmu+SzDpio5L9XqI0hZziTbsyV7bt9MnjdeHW2Ov9VORHUKXyAZyjMGPVBDdSb0Ti02apDTD3o/RzhmbeyB6CU1wHzlv8qFkMj5ra3xh5NZeuhIz0rvE6tIimYx9h8OU5lT0VPJQmxAgHarSUb/Ka2FPvgDaXLWbmM28JXxXWapA3CG8X29NfbClWH1IyLvjCC8SaTMtxkOsspFb6K2Hr9DdgLQyRE0hqzfg78Z8BXhXifTCAVkevdyzLv3838c6Dc5aMmTNswALIG3czwOK/YAPf+ISQtiFW" />
			<center>
				<table class="gridtable" width="100%" border="1" align="center" cellpadding="3" cellspacing="0" bordercolorlight="#335078"
					bordercolordark="#FFFFFF" bgcolor="#C5D5E4" style="font-size: 12px; letter-spacing: 1px;">
					<tr bgcolor="#B6EBC4">
						<td bgcolor="#C5D5E4" height="25" style="width: 615px" colspan="2">
							<p align="center">
								<b>删除管理员</b>
						</td>
					</tr>
					<tr>
						<td height="21" bgcolor="#DFE8F0" style="border-bottom: medium none;
							 border-right: medium none ;padding-left:45%;">
								用户名：
								<input name="AdminName" type="text" maxlength="15" 
									id="AdminName" class="put" value="${name }"/>
						</td>
						
					</tr>

					<tr>
						<td height="25" bgcolor="#DFE8F0" style="border-top: medium none; padding-left:45%;" colspan="2" >
								<input type="button" name="Button1" value="确定删除" id="Button1" name="B1" class="btn btn-danger" />
								&nbsp;&nbsp;&nbsp;&nbsp;<input name="reback" type="button" value="返回上一页" onclick="javascript:history.back();"
									class="btn btn-info" />
						</td>
					</tr>
				</table>
			</center>
		</form>
	</div>
</body>
</html>