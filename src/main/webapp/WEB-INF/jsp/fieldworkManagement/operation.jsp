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
<!-- 验证JS -->
<%-- 
<script src="js/operation.js"></script>
--%>
<script type="text/javascript">
		//验证修改的内容
		$(function(){
			//修改绑定单击事件
			$("#alterMSG").click(function(){
				var alterDT= $("#alterDT").val();				//证件类型
				var account = $("#account").html();				//代理商账号
				var alterDN = $("#alterDN").val();				//证件编号
				var alterBN = $("#alterBN").val();				//银行名称
				var alterBCN =$("#alterBCN").val();				//银行卡号
				var alterRealName = $("#alterRealName").val();	//真是姓名
				var password = $("#password").val();			//真是姓名
				var alterCPN = $("#alterCPN").val();			//手机号码
				var alterEmail =$("#alterEmail").val();			//邮箱
				var alterState = $('#alterState').val();		//状态
				var userID = $('#userID').html();				//代理商id
				//var num = checkDT()+checkDN()+checkBN()+checkBCN()+checkRealName()+checkCPN()+checkEmail();
				//if(num != 7){
				//	var r=confirm("输入信息有误:请重新输入");
				//}
				
				var data = {"userID":userID,"alterDT":alterDT,"alterDN":alterDN,"alterBN":alterBN,"alterBCN":alterBCN,"alterRealName":alterRealName,"password":password,"alterCPN":alterCPN,"alterEmail":alterEmail,"alterState":alterState};
				//发送ajax请求
				$.ajax({
					url:"agent/operation.do",
					type:"post",
					data:data,
					dataType:"json",
					success:function(result){
						if(result.status == 0){
							alert(result.msg);
							$("#alterDN").val("");				//证件编号
							$("#alterBN").val("");				//银行名称
							$("#alterBCN").val("");				//银行卡号
							$("#alterRealName").val("");		//真是姓名
							$("#alterCPN").val("");				//手机号码
							$("#alterEmail").val("");			//邮箱
							$("#password").val("");				//密码
						}else if(result.status == 1){
							alert(result.msg);
						}else if(result.status == 2){
							alert(result.msg);
						}else if(result.status == -1){
							window.location.href="login/toLogin.do";
						}
					},
					error:function(){
						alert("修改失败！")
					}
				});
			});
			
			$("#deleteMSG").click(function(){
				var account = $("#account").html();
				
				//检查格式
				var OK= true;
				if(account == "" || account == null){
					alert("用户名为空!");
					OK = false;
				}
				
				//发送ajax请求
				$.ajax({
					url:"agent/delete.do",
					type:"post",
					data:{"account":account},
					dataType:"json",
					success:function(result){
						if(result.status == 0){
							alert(result.msg);
						}else if(result.status == 1){
							alert(result.msg);
						}else if(result.status == 2){
							alert(result.msg);
						}else if(result.status == -1){
							window.location.href="login/toLogin.do";
						}
					},
					error:function(){
						alert("删除失败！")
					}
				});
			});
		});
</script>
</head>
<body>
	<div id="div1">
		<table width="100%" border="1" align="center" cellpadding="3" cellspacing="0" bordercolor="#A4B6D7"
			bgcolor="F2F8FF" class="admin_table2">
			<tr>
				<td class="title_03">
					<strong>代理商管理</strong>
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
					<strong>查询代理商参数</strong>
				</td>
			</tr>
			<tr>
				<td align="right" width="40%" style="height:20px;">
					用户ID：
				</td>
				<td colspan="2" align="left"  style="height:20px;">
					<span id="userID">${agent.userID }</span>
				</td>
			</tr>
			<tr>
				<td align="right" width="40%" style="height:20px;">
					账户：
				</td>
				<td colspan="2" align="left"  style="height:20px;">
					<span id="account">${agent.account }</span>
				</td>
			</tr>
			<tr>
				<td align="right">
					房卡：
				</td>
				<td colspan="2" align="left">
					<span id="roomCard">${agent.roomCard }</span>
				</td>
			</tr>
			<tr>
				<td align="right">
					上级代理：
				</td>
				<td colspan="2" align="left">
					<span id="higherAgent">${agent.higherAgent }</span>
				</td>
			</tr>
			<tr>
				<td align="right">
					下级代理数：
				</td>
				<td colspan="2" align="left">
					<span id="lowerAgentNum">${agent.lowerAgentNum }</span>
				</td>
			</tr>
			<tr>
				<td align="right">
					下级玩家数：
				</td>
				<td colspan="2" align="left">
					<span id="lowerPlayerNum">${agent.lowerPlayerNum}</span>
				</td>
			</tr>
			
			
			<tr>
				<td class="title_03" colspan="19">
					<strong>可修改项</strong>
				</td>
			</tr>
			<tr>
				<td align="right" width="40%" style="height:20px;">
					证件类型：
				</td>
				<td colspan="2" align="left"  style="height:20px;">
					<select id = "alterDT">
						<option value = "身份证">身份证</option>
						<option value = "护照">护照</option>
						<option value = "驾驶证">驾驶证</option>
					</select>
					<span id="alterDTMSG" class="red"></span>
				</td>
			</tr>
			<tr>
				<td align="right" width="40%" style="height:20px;">
					证件编号：
				</td>
				<td colspan="2" align="left"  style="height:20px;">
					<input name="documentNumber" type="text" id="alterDN" class="put" maxlength="12" value="${agent.documentNumber}" />
					<span id="alterDNMSG" class="red"></span>
				</td>
			</tr>
			<tr>
				<td align="right">
					银行名称：
				</td>
				<td colspan="2" align="left">
					<input name="bankName" type="text" id="alterBN" class="put" value="${agent.bankName }"/>
					<span id="alterBNMSG" class="red"></span>
				</td>
			</tr>
			<tr>
				<td align="right">
					银行卡号：
				</td>
				<td colspan="2" align="left">
					<input name="bankCardNumber" type="text" id="alterBCN" class="put" value="${agent.bankCardNumber }"/>
					<span id="alterBCNMSG" class="red"></span>
				</td>
			</tr>
			<tr>
				<td align="right">
					真实姓名：
				</td>
				<td colspan="2" align="left">
					<input name="realName" type="text" id="alterRealName" class="put" value="${agent.realName }"/>
					<span id="alterRealNameMSG" class="red"></span>
				</td>
			</tr>
			<tr>
				<td align="right">
					密码：
				</td>
				<td colspan="2" align="left">
					<input name="password" type="text" id="password" class="put"/>
				</td>
			</tr>
			<tr>
				<td align="right">
					手机号码：
				</td>
				<td colspan="2" align="left">
					<input name="cellPN" type="text" id="alterCPN" value="${agent.cellPhoneNumber }"/>
					<span id="alterCPNMSG" class="red"></span>
				</td>
			</tr>
			<tr>
				<td align="right">
					邮箱：
				</td>
				<td colspan="2" align="left">
					<input name="email" type="text" id="alterEmail" class="put" value="${agent.email }"/>
					<span id="alterEmailMSG" class="red"></span>
				</td>
			</tr>
			<tr>
				<td align="right">
					状态：
				</td>
				<td colspan="2" align="left">
					<select id = "alterState">
					<%-- 
						<option value = "3" ${agent.state=="离线"?'selected':'' }>离线</option>
						<option value = "2" ${agent.state=="冻结"?'selected':'' }>冻结</option>
					--%>
						<option value = "3">离线</option>
						<option value = "2">冻结</option>
					</select>
					
				</td>
			</tr>
			<tr>
				<td align="right">
					<input type="button" value=" 修 改 " id="alterMSG"/>
				</td>
				<td colspan="2" align="left">
					<input type="button" value=" 删除 " id="deleteMSG"/>
				</td>
			</tr>
			
			<!-- 
			<tr>
				<td class="title_03" colspan="19">
					<strong>房卡信息</strong>
				</td>
			</tr>
			<tr>
				<td align="right" width="40%" style="height:20px;">
					房卡数：
				</td>
				<td colspan="2" align="left"  style="height:20px;">
					<span id="roomCardNum">${agent.roomCard}</span>
				</td>
			</tr>
			
			<tr>
				<td class="title_03" colspan="19">
					<input type="button" id="sellCard" value="出售房卡"/>
				</td>
			</tr>
			 -->
		</table>
	</div>
	
<!-- ----------点击出售房卡时的界面---------------------- -->
	
	<div id="div2" style="display: none">	
		<table width="100%" border="1" align="center" cellpadding="3" cellspacing="0" bordercolor="#A4B6D7"
			bgcolor="F2F8FF" class="admin_table2">
			<tr>
				<td class="title_03">
					<strong>出售房卡</strong>
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
			<c:choose>
				<c:when test="${!empty operatRC }">
					<tr>
						<td align="right" width="40%" style="height:20px;">
							我当前房卡数：
						</td>
						<td colspan="2" align="left"  style="height:20px;">
							<span id="myRoomCardNum">${operatRC }</span>
						</td>
					</tr>
					<tr>
						<td align="right" width="40%" style="height:20px;">
							出售数量：
						</td>
						<td colspan="2" align="left"  style="height:20px;">
							<input name="" type="text" id="sellNumOne" class="put" maxlength="12" />
							<span id="sellNumOneMSG" class="red"></span>
						</td>
					</tr>
				</c:when>
				<c:otherwise>
					<tr>
						<td align="right" width="40%" style="height:20px;">
							出售数量：
						</td>
						<td colspan="2" align="left"  style="height:20px;">
							<input type="text" id="sellNumTwo" class="put" maxlength="12" />
							<span id="sellNumTwoMSG" class="red"></span>
						</td>
					</tr>
				</c:otherwise>
			</c:choose>
			<tr>
				<td align="right">
					出售金额：
				</td>
				<td colspan="2" align="left">
					<input type="text" id="sellMoney" class="put"/>
					<span id="sellMoneyMSG" class="red"></span>
				</td>
			</tr>
			<tr>
				<td align="right">
					出售原因：
				</td>
				<td colspan="2" align="left">
					<input name="bankCardNumber" type="text" id="sellReason" class="put"/>
					<span id="sellReasonMSG" class="red"></span>
				</td>
			</tr>
			<tr>
				<td align="right">
					<input type="button" value="确认出售" id="affirmSell"/>
				</td>
				<td colspan="2" align="left">
					<input type="button" value=" 取消" id="cancelSell"/>
				</td>
			</tr>
		</table>
	</div>
</body>
</html>