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
<title>添加代理商</title>
<base href="<%=basePath%>">
<link  href="css/systemSetttings/inc_style.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
<style type="text/css">
*{margin: 0;padding: 0;}
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
<!-- 验证JS -->
<%--
<script src="js/operation.js"></script>
 --%>
<script type="text/javascript">
		//验证修改的内容
		$(function(){
			//修改绑定单击事件
			//给确定添加加单击事件
			$("#addAgent").click(function(){
				//获取输入内容
				var account = $("#account").val().trim();
				var password = $("#password").val().trim();
				var documentType = $("#documentType").val().trim();
				var documentNumber = $("#documentNumber").val().trim();
				var bankName = $("#bankName").val().trim();
				var bankCardNumber = $("#bankCardNumber").val().trim();
				var realName = $("#realName").val().trim();
				var cellPhoneNumber = $("#cellPhoneNumber").val().trim();
				var email = $("#email").val().trim();
				
 				var reg1 = /^[A-Za-z]+[0-9]+$/;
 				var reg2 = /^[0-9]+[A-Za-z]+$/;
				//检查格式
				//清空以前提示消息
				var ok = true;
				if(!reg1.test(account) && !reg2.test(account)){
					alert("用户名必须是数字和字母组合！");
					ok = false;
				}
				if(password == null || password == ""){
					alert("密码不能为空！");
					ok = false;
				}
				//发送ajax请求
				if(ok){
					$.ajax({
						url:"agent/save.do",
						type:"post",
						data:{"account":account,"password":password,"documentType":documentType,"documentNumber":documentNumber,"bankName":bankName,
							"bankCardNumber":bankCardNumber,"realName":realName,"cellPhoneNumber":cellPhoneNumber,"email":email},
						dataType:"json",
						success:function(result){
							//result就是服务器返回的JSON结果
							if(result.status == 0){//创建成功
								alert(result.msg);
								//清空输入框中信息
								$("#account").val("");
								$("#documentNumber").val("");
								$("#bankName").val("");
								$("#bankCardNumber").val("");
								$("#realName").val("");
								$("#cellPhoneNumber").val("");
								$("#email").val("");
								$("#password").val("")
							}else if(result.status == 1){
								alert(result.msg);
							}else if(result.status == 2){
								alert(result.msg);
							}else if(result.status == 3){
								alert(result.msg);
							}else if(result.status == 4){
								alert(result.msg);
							}else if(result.status == 5){
								alert(result.msg);
							}else if(result.status == 6){
								alert(result.msg);
							}else if(result == -1){
								window.location.href="login/toLogin.do";
							}
						},
						error:function(){
							alert("增加代理商失败！");
						}
					});
				}
			});
		});
</script>
</head>
<body>
	<form name="form1" method="post" action="" >
		<table class="gridtable" width="100%" border="1" align="center" cellpadding="3" cellspacing="0" bordercolor="#A4B6D7"
			bgcolor="F2F8FF" class="admin_table2">
			<tr>
				<td class="title_03" align="center">
					<strong>新增代理界面</strong>
				</td>
			</tr>
		</table>
<!-- 		<table width="100%" border="0" cellspacing="0" cellpadding="0"> -->
<!-- 			<tr> -->
<!-- 				<td style="height: 5px"> -->
<!-- 				</td> -->
<!-- 			</tr> -->
<!-- 		</table> -->
		<table class="gridtable" width="100%" border="1" align="center" cellpadding="3" cellspacing="0" bordercolor="A4B6D7"
			bgcolor="F2F8FF" class="admin_table">
			<tr>
				<td align="right" width="40%" style="height:20px;">
					证件类型：
				</td>
				<td colspan="2" align="left"  style="height:20px;">
					<select id = "documentType">
						<option value = "身份证" >身份证</option>
						<option value = "护照" >护照</option>
						<option value = "驾驶证" >驾驶证</option>
					</select>
					<span id="alterDTMSG" class="red"></span>
				</td>
			</tr>
			<tr>
				<td align="right" width="40%" style="height:20px;">
					证件编号：
				</td>
				<td colspan="2" align="left"  style="height:20px;">
					<input name="documentNumber" type="text" id="documentNumber" class="put"/>
					<span id="alterDNMSG" class="red"></span>
				</td>
			</tr>
			<tr>
				<td align="right" width="40%" style="height:20px;">
					账户：
				</td>
				<td colspan="2" align="left"  style="height:20px;">
					<input type="text" name="account" id="account">
					<span id="accountMSG" class="red">${message }</span>
				</td>
			</tr>
			<tr>
				<td align="right" width="40%" style="height:20px;">
					密码：
				</td>
				<td colspan="2" align="left"  style="height:20px;">
					<input type="text" name="password" id="password">
					<span id="accountMSG" class="red">${password }</span>
				</td>
			</tr>
			<tr>
				<td align="right">
					银行名称：
				</td>
				<td colspan="2" align="left">
					<input name="bankName" type="text" id="bankName" class="put"/>
					<span id="alterBNMSG" class="red"></span>
				</td>
			</tr>
			<tr>
				<td align="right">
					银行卡号：
				</td>
				<td colspan="2" align="left">
					<input name="bankCardNumber" type="text" id="bankCardNumber" class="put"/>
					<span id="alterBCNMSG" class="red"></span>
				</td>
			</tr>
			<tr>
				<td align="right">
					真实姓名：
				</td>
				<td colspan="2" align="left">
					<input name="realName" type="text" id="realName" class="put" />
					<span id="alterRealNameMSG" class="red"></span>
				</td>
			</tr>
			<tr>
				<td align="right">
					手机号码：
				</td>
				<td colspan="2" align="left">
					<input name="cellPhoneNumber" type="text" id="cellPhoneNumber" />
					<span id="alterCPNMSG" class="red"></span>
				</td>
			</tr>
			<tr>
				<td align="right">
					邮箱：
				</td>
				<td colspan="2" align="left">
					<input name="email" type="text" id="email" class="put" />
					<span id="alterEmailMSG" class="red"></span>
				</td>
			</tr>
			<tr>
				<td align="right">
					<input type="button" value="增 加  " id="addAgent" class="btn btn-primary"/>
				</td>
				<td colspan="2" align="left">
				</td>
			</tr>
		</table>
	</form>
</body>
</html>