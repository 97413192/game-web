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
<title>增加玩家房卡</title>
<base href="<%=basePath%>">
<link href="css/systemSetttings/inc_style.css" rel="stylesheet" type="text/css" />
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
<script type="text/javascript">
	//输入框只能输入数字
	function filterInput(){
		if (event.type.indexOf("key") != -1){
			var re = /37|38|39|40/g
			if (event.keyCode.toString().match(re)){
				return false
			}
		}
		event.srcElement.value = event.srcElement.value.replace(/[^0-9]/g, "")
	}
	
	$(function(){
		//修改绑定单击事件
		$("#addBtn").click(function(){
			//获取输入内容
			var userID= $("#userID").html().trim();  			//游戏玩家id
			var addRoomCard= $("#addRoomCard").val().trim();    //充值房卡
			var mark = $("#mark").html().trim();
			var sell = $("#sell").val().trim();
			var reason = $("#reason").val().trim();
			var add = parseInt(agentRoomCard)+parseInt(addRoomCard);
			var ok = true;
			
			//检验格式
			if(addRoomCard == ""){
				alert("增加的房卡数不能为空！");
				ok = false;
			}
			if(sell == ""){
				alert("出售进来不能为空！");
				ok = false;
			}
			if(reason == ""){
				alert("出售原因不能为空！");
				ok = false;
			}
			if(!(/^(\+|-)?\d+($|\.\d+$)/.test(addRoomCard))){
				alert("输入的房卡数必须为数字!");
				ok = false;
			}
			if(mark == -2){
				var agentRoomCard = $("#agentRoomCard").html().trim();
				if((agentRoomCard -addRoomCard)<0){
					alert("充值数超过了代理商拥有的房卡数！");
					ok = false;
				}
			}
			//发送ajax请求
			if(ok){
				$.ajax({
					url:"agent/addRoomCard.do",
					type:"post",
					data:{"userID":userID,"addRoomCard":addRoomCard,"sell":sell,"reason":reason},
					dataType:"json",
					success:function(result){
						if(result.status == 0){
							alert(result.msg);
							$("#addRoomCard").val("");
							$("#sell").val("");
							$("#reason").val("");
							if(mark == -2){
								$("#agentRoomCard").html(result.data.operRoomCard);
							}
							$("#addAgentRoomCard").html(result.data.roomCard);
							//$("#addAgentRoomCard").html(add);
						}else if(result.status == 1){
							alert(result.msg);
						}else if(result.status == 2){
							window.location.href="systemSettings/noPower.do";
						}else if(result.status == 3){
							alert(result.msg);
						}else if(result.status == 4){
							alert(result.msg);
						}else if(result.status == 5){
							alert(result.msg);
						}else if(result.status == 6){
							alert(result.msg);
						}else if(result.status == 7){
							alert(result.msg);
						}else if(result.status == 8){
							alert(result.msg);
						}else if(result.status == -1){
							window.location.href="login/toLogin.do";
						}
					},
					error:function(){
						alert("增加房卡失败！")
					}
				});
			}
		});
	});
</script>
</head>
<body>
	<form name="form1" method="post" action="./CreateUser.aspx" id="form1" onsubmit="return checkform(this)">
		<table class="gridtable" width="100%" border="1" align="center" cellpadding="3" cellspacing="0" bordercolor="A4B6D7"
			bgcolor="F2F8FF" class="admin_table">
			<tr>
				<td class="title_03" colspan="19" align="center">
					<strong>增加玩家房卡</strong>
				</td>
			</tr>
			<!-- 代理商 -->
			<c:if test="${mark==-2 }">
				<tr>
					<td align="right" width="40%" style="height:20px;">
						代理商名称：
					</td>
					<td colspan="2" align="left"  style="height:20px;">
						<span id="operAccount">${account }</span>
					</td>
				</tr>
				<tr>
					<td align="right" width="40%" style="height:20px;">
						代理商房卡数：
					</td>
					<td colspan="2" align="left"  style="height:20px;">
						<span id="agentRoomCard">${agentRoomCard }</span>					
					</td>
				</tr>
			</c:if>
			
			<tr>
				<td align="right" width="40%" style="height:20px;">
					需要充值代理商账号：
				</td>
				<td colspan="2" align="left"  style="height:20px;">
					<span id="account">${ByAccount }</span>
				</td>
			</tr>
			<tr>
				<td align="right" width="40%" style="height:20px;">
					需要充值代理商id：
				</td>
				<td colspan="2" align="left"  style="height:20px;">
					<span id="userID">${userID }</span>
				</td>
			</tr>
			<tr>
				<td align="right" width="40%" style="height:20px;">
					需要充值代理商的房卡数：
				</td>
				<td colspan="2" align="left"  style="height:20px;">
					<span id="addAgentRoomCard">${roomCard }</span>
				</td>
			</tr>
			<tr>
				<td align="right" width="40%" style="height:20px;">
					出售金额：
				</td>
				<td colspan="2" align="left"  style="height:20px;">
					<input name="sell" type="text" id="sell" class="put" maxlength="20" size ="10" onkeyup="filterInput();"/>
				</td>
			</tr>
			<tr>
				<td align="right" width="40%" style="height:20px;">
					添加房卡(管理可输入负数)：
				</td>
				<td colspan="2" align="left"  style="height:20px;">
					<span id="mark" style="display: none">${mark }</span>
					<input name="alter" type="text" id="addRoomCard" class="put" maxlength="20" size ="10"/>
				</td>
			</tr>
			<tr>
				<td align="right" width="40%" style="height:20px;">
					出售原因：
				</td>
				<td colspan="2" align="left"  style="height:20px;">
					<textarea name="reason" cols="50" rows="5" id="reason" class="put" ></textarea>
				</td>
            </tr>
            <tr>
				<td align="right" width="40%" style="height:20px;">
					<input type="button" name="alterBtn" value="增 加 " id="addBtn" class="btn btn-warning"/>
				</td>
				<td colspan="2" align="left"  style="height:20px;">
				</td>
            </tr>
		</table>
	</form>
</body>
</html>