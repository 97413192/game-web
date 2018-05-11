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
<title>查询管理员参数</title>
<base href="<%=basePath%>">
<link href="css/systemSetttings/inc_style.css" rel="stylesheet"
	type="text/css" />
<link rel="stylesheet"
	href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css"
	integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
	crossorigin="anonymous">
<style type="text/css">
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
	$(function(){
		//查询绑定单击事件
		$("#select").click(function(){
			//获取参数
			var name = $("#name").val().trim();
			//清空上次的提示内容
			$("#checkmsg").html("");
			//检验格式是否正确
			var ok = true;
			if(name == ""){
				$("#checkmsg").html("名字不能为空");
				ok = false;
			}else if(!/^[0-9a-zA-Z\\.]{4,16}$/.test(name)){
				//alert("格式");
				$("#checkmsg").html("玩家名长度4～16个字符，由字母、数字组成");
				ok = false;
			}
			//发送ajax请求
			if(ok){
				$.ajax({
					url:"systemSettings/selectManagerByName.do",
					type:"post",
					data:{"name":name},
					dataType:"json",
					success:function(result){
						//result就是服务器返回的JSON结果
						if(result.status == 0){//查询成功
							window.location.href="systemSettings/selectManager.do?name="+result.data;
						}else if(result.status == 1){
							alert(result.msg);
						}else if(result.status == -1){
							window.location.href="login/toLogin.do";
						}
						//清空输入框中信息
						$("#u").val("");
						$("#pwd").val("");
					},
					error:function(){
						alert("查询失败！");
					}
					
				});
			}
		});
		//修改绑定单击事件
		$("#updateuser").click(function(){
			//获取输入内容
			var olduser= $("#u").val().trim();
			var newuser = $("#newu").val().trim();
			var status = $("#status").val().trim();
			var newpwd = $("#pwd").val().trim();
			var confirmpwd =$("#confirmpwd").val().trim();
			//清除上次提示内容
			$("#usernamemsg").html("");
			$("#newusernamemsg").html("");
			$("#userpwdmsg").html("");
			$("#confirmuserpwdmsg").html("");
			var ok = true;
			
			if (confirm('确定要修改此管理员吗？')) {
				ok = true;
			} else {
				ok = false;
			}
			//检验格式
			if (olduser == "") {
				$("#usernamemsg").html("用户名不能为空！");
				ok = false;
			} else if (!/^[0-9a-zA-Z\\.]{4,16}$/.test(olduser)) {
				//alert("名字");
				$("#usernamemsg").html("用户名长度4～16个字符，由字母、数字组成");
				ok = false;
			}
			if (newuser == "") {
				$("#newusernamemsg").html("用户名不能为空！");
				ok = false;
			} else if (!/^[0-9a-zA-Z\\.]{4,16}$/.test(newuser)) {
				//alert("名字");
				$("#newusernamemsg").html("用户名长度4～16个字符，由字母、数字组成");
				ok = false;
			}
			if (newpwd == "") {
				$("#userpwdmsg").html("密码不能为空！");
				ok = false;
			} else if (!/^[0-9a-zA-Z\\.]{6,12}$/.test(newpwd)) {
				$("#userpwdmsg").html("密码为6-12个数字、字母组成");
				ok = false;
			} else if (newpwd != confirmpwd) {
				$("#confirmuserpwdmsg").html("两次输入的密码不一致");
				ok = false;
			}
			//发送ajax请求
			if (ok) {
				$.ajax({
					url : "systemSettings/alterAadStatus.do",
					type : "post",
					data : {
						"olduser" : olduser,
						"status" : status,
						"newuser" : newuser,
						"newpwd" : newpwd
					},
					dataType : "json",
					success : function(result) {
						//result就是服务器返回的JSON结果
						if (result.status == 0) {//创建成功
							alert(result.msg);
						} else if (result.status == 1) {//创建失败
							alert(result.msg);
						} else if (result.status == 2) {//登录服务连接失败
							alert(result.msg);
						} else if (result.status == 3) {//名字格式错误
							alert(result.msg);
						} else if (result.status == 4) {//密码格式错误
							alert(result.msg);
						} else if (result.status == 5) {//未知错误
							alert(result.msg);
						} else if (result.status == -1) {
							window.location.href = "login/toLogin.do";
						}
						//清空输入框中信息
						$("#u").val("");
						$("#newu").val("");
						$("#pwd").val("");
						$("#confirmpwd").val("");
					},
					error : function() {
						alert("修改失败！")
					}
				});
			}
		});
	});
</script>
</head>
<body>
	<form name="form1" method="post"
		action="systemSettings/selectManager.do" id="form1"
		onsubmit="return checkform(this)">
		<!-- 		<table class="gridtable" width="100%" border="1" align="center" cellpadding="3" cellspacing="0" bordercolor="#A4B6D7" -->
		<!-- 			bgcolor="F2F8FF" class="admin_table2"> -->
		<!-- 			<tr> -->
		<!-- 				<td class="title_03"> -->
		<!-- 					<strong>管理员管理</strong> -->
		<!-- 				</td> -->
		<!-- 			</tr> -->
		<!-- 		</table> -->
		<!-- 		<table width="100%" border="0" cellspacing="0" cellpadding="0"> -->
		<!-- 			<tr> -->
		<!-- 				<td style="height: 5px"> -->
		<!-- 				</td> -->
		<!-- 			</tr> -->
		<!-- 		</table> -->
		<table class="gridtable" width="100%" border="1" align="center"
			cellpadding="3" cellspacing="0" bordercolor="A4B6D7" bgcolor="F2F8FF"
			class="admin_table">
			<tr>
				<td class="title_03" colspan="19" align="center"><strong>查询管理员参数</strong>
				</td>
			</tr>
			<tr>
				<td align="right" width="40%" style="height: 20px;">
					输入要查询的管理员名：</td>
				<td colspan="2" align="left" style="height: 20px;"><input
					name="name" type="text" id="name" class="btn btn-danger" maxlength="12" /> <input
					type="button" name="CreateUser" value=" 查 询 " id="select"
					class="btn btn-info" /> <span id="checkmsg" class="red">${loginServiceError }${error }${exception }</span>
				</td>
			</tr>
			<tr>
				<td align="right" width="40%" style="height: 20px;">管理员id：</td>
				<td colspan="2" align="left" style="height: 20px;"><span
					id="namemsg">${mgr.id }</span></td>
			</tr>
			<tr>
				<td align="right" width="40%" style="height: 20px;">管理员名字：</td>
				<td colspan="2" align="left" style="height: 20px;"><span
					id="uuidmsg">${mgr.name }</span></td>
			</tr>
			<tr>
				<td align="right">管理员密码：</td>
				<td colspan="2" align="left"><span id="sexmsg">${mgr.password }</span>
				</td>
			</tr>
			<tr>
				<td align="right">管理员权限：</td>
				<td colspan="2" align="left"><span id="imeimsg">${mgr.power }</span>
				</td>
			</tr>
			<tr>
				<td align="right">管理员创建ip：</td>
				<td colspan="2" align="left"><span id="imsimsg">${mgr.ip }</span>
				</td>
			</tr>
			<tr>
				<td align="right">管理员创建时系统：</td>
				<td colspan="2" align="left"><span id="platformmsg">${mgr.system}</span>
				</td>
			</tr>
			<tr>
				<td align="right">管理员创建时间：</td>
				<td colspan="2" align="left"><span id="systemmsg">${mgr.time }</span>
				</td>
			</tr>
			<tr>
				<td align="right">管理员上次登录时间：</td>
				<td colspan="2" align="left"><span id="systemmsg">${mgr.lastLoginTime }</span>
				</td>
			</tr>
			<tr>
				<td align="right">管理员上次登录ip：</td>
				<td colspan="2" align="left"><span id="systemmsg">${mgr.lastLoginIp }</span>
				</td>
			</tr>
			<tr>
				<td align="right">管理员修改时间：</td>
				<td colspan="2" align="left"><span id="systemmsg">${mgr.modifyTime }</span>
				</td>
			</tr>
			<tr>
				<td align="right">本次在线时间：</td>
				<td colspan="2" align="left"><span id="systemmsg">${mgr.onlineTime }</span>
				</td>
			</tr>
			<tr>
				<td align="right">累积在线时间：</td>
				<td colspan="2" align="left"><span id="systemmsg">${mgr.heapOnlineTime }</span>
				</td>
			</tr>
			<tr>
				<td align="right">管理员类别：</td>
				<td colspan="2" align="left"><span id="systypemsg"> <c:choose>
							<c:when test="${mgr.category==1 }">
                 			超级管理员
                 		</c:when>
							<c:otherwise>
 							普通管理员
 						</c:otherwise>
						</c:choose>
				</span></td>
			</tr>
			<tr>
				<td align="right">管理员冻结状态：</td>
				<td colspan="2" align="left"><span id="nettypemsg"> <c:choose>
							<c:when test="${mgr.status==1 }">
                 			正常
                 		</c:when>
							<c:otherwise>
 							冻结
 						</c:otherwise>
						</c:choose>
				</span></td>
			</tr>
			<tr>
				<td align="right">登录次数：</td>
				<td colspan="2" align="left"><span id="nettypemsg">${mgr.loginTimes }</span>
				</td>
			</tr>


			<tr>
				<td class="title_03" colspan="19" align="center"><strong>修改管理员名和密码</strong>
				</td>
			</tr>
			<tr>
				<td align="right" width="40%" style="height: 20px;">管理员名：</td>
				<td colspan="2" align="left" style="height: 20px;"><input
					name="u" type="text" id="u" class="put" value="${mgr.name }"
					maxlength="12" /> <span id="usernamemsg" class="red"></span></td>
			</tr>
			<tr>
				<td align="right" width="40%" style="height: 20px;">新名字：</td>
				<td colspan="2" align="left" style="height: 20px;"><input
					name="newu" type="text" id="newu" class="put" maxlength="12" /> <span
					id="newusernamemsg" class="red"></span></td>
			</tr>
			<tr>
				<td align="right" width="40%" style="height: 20px;">管理员状态：</td>
				<td colspan="2" align="left" style="height: 20px;"><select
					id="status">
						<option value="1">正常</option>
						<option value="2">冻结</option>
				</select> <span id="newusernamemsg" class="red"></span></td>
			</tr>
			<tr>
				<td align="right">新密码：</td>
				<td colspan="2" align="left"><input name="pwd" type="password"
					id="pwd" class="put" maxlength="12" /> <span id="userpwdmsg"
					class="red"></span></td>
			</tr>
			<tr>
				<td align="right">确认密码：</td>
				<td colspan="2" align="left"><input name="pwd" type="password"
					id="confirmpwd" class="put" maxlength="12" /> <span
					id="confirmuserpwdmsg" class="red"></span></td>
			</tr>
			<tr>
				<td align="right">&nbsp;</td>
				<td colspan="2" align="left"><input type="button"
					name="CreateUser" value="修 改 " id="updateuser"
					class="btn btn-danger" /><span id="ErrorLabel" style="color: Red;"></span>
				</td>
			</tr>
		</table>
	</form>
</body>
</html>