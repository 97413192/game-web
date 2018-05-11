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
<style type="text/css">
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
			var newname = $("#newname").val().trim();
			var password = $("#AdminPwd").val().trim();
			var newpwd = $("#newpwd").val().trim();
			//获取多选的值
			var chk_value =[]; 
			$('input[name="chkListModels"]:checked').each(function(){ 
				chk_value.push($(this).val()); 
				});
			var power = chk_value.toString();
			//获取当前系统
			var system = detectOS();
			//清除上次提示内容
			//$("#oldname").html("");
			$("#name").html("");
			$("#password").html("");
			//检查格式
			var ok = true;
			if(newname == ""){
				$("#name").html("不能为空");
				ok = false;
			}
			if(password == ""){
				$("#password").html("不能为空");
				ok = false;
			}
			if(password != newpwd){
				$("#newpassword").html("两次输入的密码不一致");
				ok = false;
			}
			if(chk_value.length==0){
				alert("你还没有选择任何内容！");
			}
			if(ok && chk_value.length > 0){
				//发送ajax请求
				$.ajax({
					url:"systemSettings/alter.do",
					type:"post",
					data:{"name":name,"newname":newname,"password":password,"power":power,"system":system},
					dataType:"json",
					success:function(result){
						if(result.status==0){//修改成功
							alert(result.msg);
							$("#AdminName").val("");
							$("#newname").val("");
							$("#AdminPwd").val("");
							$("#newpwd").val("");
							$(":checkbox").attr("checked",false);
						}else if(result.status == 1){//修改失败
							alert(result.msg);
						}else if(result.status == 2){//没有这个管理员
							alert(result.msg);
						}else if(result.status == 3){//没有权限
							alert(result.msg);
						}else if(result.status == 4){//修改失败
							alert(result.msg);
						}else if(result.status == 5){//未知错误
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
				<table width="68%" border="1" align="center" cellpadding="3" cellspacing="0" bordercolorlight="#335078"
					bordercolordark="#FFFFFF" bgcolor="#C5D5E4" style="font-size: 12px; letter-spacing: 1px;">
					<tr bgcolor="#B6EBC4">
						<td bgcolor="#C5D5E4" height="25" style="width: 615px" colspan="2">
							<p align="center">
								<b>修改管理员</b>
						</td>
					</tr>
					<tr>
						<td height="21" align="right" bgcolor="#DFE8F0" style="border-bottom: medium none;
							 border-right: medium none ;width: 60%;">
								用户名：
								<input name="AdminName" type="text" maxlength="15" 
									id="AdminName" class="put" value="${name }"/>
						</td>
						<td height="25" align="left" bgcolor="#DFE8F0" 
							style="border-bottom: medium none; border-left: medium none;">
							<span id="olaname" class="color"></span>
						</td>
					</tr>
					<tr>
						<td height="25" align="right" bgcolor="#DFE8F0" style="border-top: medium none;
							border-right: medium none ;border-bottom: medium none;">
							<div align="right" >
								新名字：
								<input name="newname" type="text" id="newname" class="put"/>
							</div>
						</td>
						<td height="25" align="left" bgcolor="#DFE8F0" style="border-top: medium none;
							border-bottom: medium none; border-left: medium none;">
							<span id="name" class="color"></span>
						</td>
					</tr>
					<tr>
						<td height="25" align="right" bgcolor="#DFE8F0" style="border-top: medium none;
							border-right: medium none ;border-bottom: medium none; width: 300px;">
							<div align="right" >
								密&nbsp;&nbsp;码：
								<input name="AdminPwd" type="password" id="AdminPwd" class="put"/>
							</div>
						</td>
						<td height="25" align="left" bgcolor="#DFE8F0" style="border-top: medium none;
							border-bottom: medium none; border-left: medium none;">
							<span id="password" class="color"></span>
						</td>
					</tr>
					<tr>
						<td height="25" align="right" bgcolor="#DFE8F0" style="border-top: medium none;
							border-right: medium none ;border-bottom: medium none; width: 300px;">
							<div align="right" >
								确认密码：
								<input name="AdminPwd" type="password" id="newpwd" class="put"/>
							</div>
						</td>
						<td height="25" align="left" bgcolor="#DFE8F0" style="border-top: medium none;
							border-bottom: medium none; border-left: medium none;">
							<span id="newpassword" class="color"></span>
						</td>
					</tr>
					<tr id="trPopedom">
						<td height="25" align="center" bgcolor="#DFE8F0" style="border-top: medium none;
							border-bottom: medium none;" colspan="2">
							<div align="left">
								分配权限：<br /><div style="padding-left:20px;line-height:30px;">
									<span id="chkListModels">
                                    	<input id="chkListModels_1" type="checkbox" name="chkListModels" value="1"/><label for="chkListModels_1">信息发布</label>
                                    	<input id="chkListModels_2" type="checkbox" name="chkListModels" value="2"/><label for="chkListModels_2">设置系统(修改初始房卡)</label>
                                    	<input id="chkListModels_3" type="checkbox" name="chkListModels" value="3"/><label for="chkListModels_3">创建一级代理</label>
                                    	<input id="chkListModels_4" type="checkbox" name="chkListModels" value="4"/><label for="chkListModels_4">删除一级代理</label>
                                    	<input id="chkListModels_5" type="checkbox" name="chkListModels" value="5"/><label for="chkListModels_5">出售房卡</label>
                                    	<input id="chkListModels_6" type="checkbox" name="chkListModels" value="6"/><label for="chkListModels_6">修改登录密码</label>
                                    </span>
								</div>
							</div>
						</td>
					</tr>

					<tr>
						<td height="25" bgcolor="#DFE8F0" style="border-top: medium none; width: 615px;" colspan="2">
							<p align="center">
								<input type="button" name="Button1" value="确定修改" id="Button1" name="B1" class="put" />
								<input name="reback" type="button" value="返回上一页" onclick="javascript:history.back();"
									class="put" />
						</td>
					</tr>
				</table>
			</center>
		</form>
	</div>
</body>
</html>