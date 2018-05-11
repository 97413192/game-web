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
<title>查询玩家参数</title>
<base href="<%=basePath%>">
<link href="css/systemSetttings/inc_style.css" rel="stylesheet" type="text/css" />
<style type="text/css">
	body{
			margin-top: 0px;
		}
	.red{
			color: red;
		}
</style>
<script src="js/basevalue.js"></script>
<script src="js/jquery-1.11.1.js"></script>
<script type="text/javascript">
	$(function(){
		//查询绑定单击事件
		$("#check").click(function(){
			//获取参数
			var name = $("#checkname").val().trim();
			//清空上次的提示内容
			$("#checkmsg").html("");
			//检验格式是否正确
			var ok = true;
			if(name == ""){
				$("#checkmsg").html("名字不能为空");
				ok = false;
			}else if(!/^[0-9a-zA-Z\u4e00-\u9fa5\\.]{9,64}$/.test(name)){
				//alert("格式");
				$("#checkmsg").html("玩家名长度9～64个字符，由字母、数字组成");
				ok = false;
			}
			//发送ajax请求
			if(ok){
				$.ajax({
					url:"gamePlayer/selectPlayer.do",
					type:"post",
					data:{"name":name},
					dataType:"json",
					success:function(result){
						//result就是服务器返回的JSON结果
						if(result.status == 0){//查询成功
							//alert(result.msg);
							$("#namemsg").html(result.data.uname);
							$("#uuidmsg").html(result.data.uuid);
							$("#sexmsg").html(result.data.sex);
							$("#imeimsg").html(result.data.imei);
							$("#imsimsg").html(result.data.imsi);
							$("#platformmsg").html(result.data.platform);
							$("#systemmsg").html(result.data.system);
							$("#systypemsg").html(result.data.systype);
							$("#nettypemsg").html(result.data.nettype);
							$("#creDatemsg").html(result.data.creDate);
							$("#serverIDmsg").html(result.data.serverID);
							$("#modifyDatemsg").html(result.data.modifyDate);
							$("#isBindmsg").html(result.data.isBind);
							$("#isResetPassWordmsg").html(result.data.isResetPassWord);
							$("#channelmsg").html(result.data.channel);
							$("#channelNomsg").html(result.data.channelNo);
							$("#baiduIdmsg").html(result.data.baiduId);
						}else if(result.status == 1){//由于登陆连接没成功查询失败
							alert(result.msg);
						}else if(result.status == 2){//玩家名不存在
							alert(result.msg);
						}else if(result.status == 3){//由于未获得玩家参数查询失败
							alert(result.msg);
						}else if(result.status == 4){//未知错误
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
			var newpwd = $("#pwd").val().trim();
			var confirmpwd =$("#confirmpwd").val().trim();
			//清除上次提示内容
			$("#usernamemsg").html("");
			$("#newusernamemsg").html("");
			$("#userpwdmsg").html("");
			$("#confirmuserpwdmsg").html("");
			var ok = true;
			//检验格式
			if(olduser == ""){
				$("#usernamemsg").html("用户名不能为空！");
				ok = false;
			}else if(!/^[0-9a-zA-Z\u4e00-\u9fa5\\.]{9,64}$/.test(olduser)){
				//alert("名字");
				$("#usernamemsg").html("用户名长度9～64个字符，由字母、数字组成");
				ok = false;
			}
			if(newuser == ""){
				$("#newusernamemsg").html("用户名不能为空！");
				ok = false;
			}else if(!/^[0-9a-zA-Z\u4e00-\u9fa5\\.]{9,64}$/.test(newuser)){
				//alert("名字");
				$("#newusernamemsg").html("用户名长度9～64个字符，由字母、数字组成");
				ok = false;
			}
			if(newpwd == ""){
				$("#userpwdmsg").html("密码不能为空！");
				ok = false;
			}else if(!/^[0-9a-zA-Z\\.]{6,18}$/.test(newpwd)){
				$("#userpwdmsg").html("密码为6-18个数字、字母组成");
				ok = false;
			}else if(newpwd != confirmpwd){
				$("#confirmuserpwdmsg").html("两次输入的密码不一致");
				ok = false;
			}
			//发送ajax请求
			if(ok){
				$.ajax({
					url:"gamePlayer/alterPlayer.do",
					type:"post",
					data:{"olduser":olduser,"newuser":newuser,"newpwd":newpwd},
					dataType:"json",
					success:function(result){
						//result就是服务器返回的JSON结果
						if(result.status==0){//创建成功
							alert(result.msg);
						}else if(result.status == 1){//创建失败
							alert(result.msg);
						}else if(result.status == 2){//登录服务连接失败
							alert(result.msg);
						}else if(result.status == 3){//名字格式错误
							$("#usernamemsg").html(result.msg);
						}else if(result.status == 4){//密码格式错误
							$("#userpwdmsg").html(result.msg);
						}else if(result.status == 5){//未知错误
							alert(result.msg);
						}else if(result.status == -1){
							window.location.href="login/toLogin.do";
						}
						//清空输入框中信息
						$("#u").val("");
						$("#newu").val("");
						$("#pwd").val("");
						$("#confirmpwd").val("");
					},
					error:function(){
						alert("修改失败！")
					}
				});
			}
		});
	});
</script>
</head>
<body>
	<form name="form1" method="post" action="./CreateUser.aspx" id="form1" onsubmit="return checkform(this)">
		<table width="100%" border="1" align="center" cellpadding="3" cellspacing="0" bordercolor="#A4B6D7"
			bgcolor="F2F8FF" class="admin_table2">
			<tr>
				<td class="title_03">
					<strong>会员管理</strong>
				</td>
			</tr>
		</table>
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td style="height: 5px">
				</td>
			</tr>
		</table>
		<table width="100%" border="1" align="center" cellpadding="3" cellspacing="0" bordercolor="A4B6D7"
			bgcolor="F2F8FF" class="admin_table">
			<tr>
				<td class="title_03" colspan="19">
					<strong>查询玩家参数</strong>
				</td>
			</tr>
			<tr>
				<td align="right" width="40%" style="height:20px;">
					输入要查询的玩家名：
				</td>
				<td colspan="2" align="left"  style="height:20px;">
					<input name="checkname" type="text" id="checkname" class="put" maxlength="12" />
					<input type="button" name="CreateUser" value=" 查 询 " id="check" class="put" />
					<span id="checkmsg" class="red">${loginServiceError }${error }${exception }</span>
				</td>
			</tr>
			<tr>
				<td align="right" width="40%" style="height:20px;">
					玩家名：
				</td>
				<td colspan="2" align="left"  style="height:20px;">
					<span id="namemsg">${success.uname }</span>
				</td>
			</tr>
			<tr style="display:none">
				<td align="right" width="40%" style="height:20px;">
					uuid：
				</td>
				<td colspan="2" align="left"  style="height:20px;">
					<span id="uuidmsg">${success.uuid }</span>
				</td>
			</tr>
			<tr>
				<td align="right">
					性别(sex)：
				</td>
				<td colspan="2" align="left">
					<span id="sexmsg">${success.sex }</span>
				</td>
			</tr>
			<tr>
				<td align="right">
					国际移动台设备识别码(IMEI)：
				</td>
				<td colspan="2" align="left">
					<span id="imeimsg">${success.imei }</span>
				</td>
			</tr>
			<tr>
				<td align="right">
					国际移动客户识别码(IMSI)：
				</td>
				<td colspan="2" align="left">
					<span id="imsimsg">${success.imsi }</span>
				</td>
			</tr>
			<tr>
				<td align="right">
					平台(platform)：
				</td>
				<td colspan="2" align="left">
					<span id="platformmsg">${success.platform }</span>
				</td>
			</tr>
			<tr>
				<td align="right">
					系统类型(system)：
				</td>
				<td colspan="2" align="left">
					<span id="systemmsg">${success.system }</span>
				</td>
			</tr>
			<tr>
				<td align="right">
					系统版本(systype)：
				</td>
				<td colspan="2" align="left">
					<span id="systypemsg">${success.systype }</span>
				</td>
			</tr>
			<tr>
				<td align="right">
					网络型号(nettype)：
				</td>
				<td colspan="2" align="left">
					<span id="nettypemsg">${success.nettype }</span>
				</td>
			</tr>
			<tr>
				<td align="right">
					创建日期(creDate)：
				</td>
				<td colspan="2" align="left">
					<span id="creDatemsg">${success.creDate }</span>
				</td>
			</tr>
			<tr>
				<td align="right">
					服务器id(serverID)：
				</td>
				<td colspan="2" align="left">
					<span id="serverIDmsg">${success.serverID }</span>
				</td>
			</tr>
			<tr>
				<td align="right">
					最近一次修改时间(modifyDate)：
				</td>
				<td colspan="2" align="left">
					<span id="modifyDatemsg">${success.modifyDate }</span>
				</td>
			</tr>
			<tr>
				<td align="right">
					绑定状态(isBind)：
				</td>
				<td colspan="2" align="left">
					<span id="isBindmsg">${success.isBind }</span>
				</td>
			</tr>
			<tr>
				<td align="right">
					重置状态(isResetPassWord)：
				</td>
				<td colspan="2" align="left">
					<span id="isResetPassWordmsg">${success.isResetPassWord }</span>
				</td>
			</tr>
			<tr>
				<td align="right">
					用户渠道(channel)：
				</td>
				<td colspan="2" align="left">
					<span id="channelmsg">${success.channel }</span>
				</td>
			</tr>
			<tr>
				<td align="right">
					用户渠道号(channelNo)：
				</td>
				<td colspan="2" align="left">
					<span id="channelNomsg">${success.channelNo }</span>
				</td>
			</tr>
			<tr>
				<td align="right">
					百度id(baiduId)：
				</td>
				<td colspan="2" align="left">
					<span id="baiduIdmsg">${success.baiduId }</span>
				</td>
			</tr>
			<tr>
				<td class="title_03" colspan="19">
					<strong>修改玩家名和密码</strong>
				</td>
			</tr>
			<tr>
				<td align="right" width="40%" style="height:20px;">
					用户名：
				</td>
				<td colspan="2" align="left"  style="height:20px;">
					<input name="u" type="text" id="u" class="put" maxlength="12"/>
					<span id="usernamemsg" class="red"></span>
				</td>
			</tr>
			<tr>
				<td align="right" width="40%" style="height:20px;">
					新名字：
				</td>
				<td colspan="2" align="left"  style="height:20px;">
					<input name="newu" type="text" id="newu" class="put" maxlength="12"  />
					<span id="newusernamemsg" class="red"></span>
				</td>
			</tr>
			<tr>
				<td align="right">
					新密码：
				</td>
				<td colspan="2" align="left">
					<input name="pwd" type="password" id="pwd" class="put" maxlength="12"/>
					<span id="userpwdmsg" class="red"></span>
				</td>
			</tr>
			<tr>
				<td align="right">
					确认密码：
				</td>
				<td colspan="2" align="left">
					<input name="pwd" type="password" id="confirmpwd" class="put" maxlength="12" />
					<span id="confirmuserpwdmsg" class="red"></span>
				</td>
			</tr>
			<tr>
				<td align="right">
					&nbsp;
				</td>
				<td colspan="2" align="left">
					<input type="button" name="CreateUser" value=" 修 改 " id="updateuser" class="put" /><span id="ErrorLabel" style="color:Red;"></span>
				</td>
			</tr>
		</table>
	</form>
</body>
</html>