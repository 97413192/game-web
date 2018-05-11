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

table.imagetable {
	font-family: verdana, arial, sans-serif;
	font-size: 11px;
	color: #333333;
	border-width: 1px;
	border-color: #999999;
	border-collapse: collapse;
}

table.imagetable th {
	background: #b5cfd2 url('images/cell-blue.jpg');
	border-width: 1px;
	padding: 8px;
	border-style: solid;
	border-color: #999999;
}

table.imagetable td {
	background: #dcddc0 url('images/cell-grey.jpg');
	border-width: 1px;
	padding: 8px;
	border-style: solid;
	border-color: #999999;
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
			var gameId= $("#gameId").html().trim();  			//游戏玩家id
			var addRoomCard= $("#addRoomCard").val().trim();    //充值房卡
			var mark = $("#mark").html().trim();
			var playerRoomCard = $("#playerRoomCard").html().trim();
			var add = parseInt(playerRoomCard)+parseInt(addRoomCard);
			var ok = true;
			
			if (confirm("确定要给ID为"+"  "+gameId+"  "+"的玩家增加"+"  "+addRoomCard+"  "+"房卡吗？")) {
				ok = true;
			} else {
				ok = false;
			}
			//检验格式
			if(addRoomCard == ""){
				alert("输入不能为空！");
				ok = false;
			}
			if(!(/^(\+|-)?\d+($|\.\d+$)/.test(addRoomCard))){
				alert("输入的房卡数必须为数字!");
				ok = false;
			}
			if(add<0){
				alert("减去玩家的房卡数超出了玩家的最大房卡!");
				ok = false;
			}
			if(mark == -2){
				var agentRoomCard = $("#agentRoomCard").html().trim();
				if((agentRoomCard -addRoomCard)<0){
					alert("充值数超过了代理商拥有的房卡数！");
					ok = false;
				}
				if(addRoomCard<0){
					alert("房卡数只能输入正数！");
					ok = false;
				}
			}
			//发送ajax请求
			if(ok){
				$.ajax({
					url:"gamePlayer/addRoomCard.do",
					type:"post",
					data:{"gameId":gameId,"addRoomCard":addRoomCard},
					dataType:"json",
					success:function(result){
						if(result.status == 0){
							alert(result.msg);
							$("#addRoomCard").val("");
							$("#playerRoomCard").html(add);
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
			
			<c:if test="${mark==-2 }">
				<tr>
					<td align="right" width="40%" style="height:20px;">
						代理商名称：
					</td>
					<td colspan="2" align="left"  style="height:20px;">
						${account }					
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
					玩家昵称：
				</td>
				<td colspan="2" align="left"  style="height:20px;">
					${pNiceName }					
				</td>
			</tr>
			<tr>
				<td align="right" width="40%" style="height:20px;">
					玩家id：
				</td>
				<td colspan="2" align="left"  style="height:20px;">
					<span id="gameId">${gameId }</span>
				</td>
			</tr>
			<tr>
				<td align="right" width="40%" style="height:20px;">
					玩家房卡数：
				</td>
				<td colspan="2" align="left"  style="height:20px;">
					<span id="playerRoomCard">${roomCard }</span>
				</td>
			</tr>
			<tr>
				<td align="right" width="40%" style="height:20px;">
					上级代理：
				</td>
				<td colspan="2" align="left"  style="height:20px;">
					<span id="account">${account}</span>
				</td>
			</tr>
			<tr>
				<td align="right" width="40%" style="height:20px;">
					输入给玩家增加的房卡数：
				</td>
				<td colspan="2" align="left"  style="height:20px;">
					<span id="mark" style="display: none">${mark }</span>
					<input name="alter" type="text" id="addRoomCard" class="put" maxlength="20" size ="10"/>
					<input type="button" name="alterBtn" value="增 加 " id="addBtn" class="btn btn-success"/>
				</td>
			</tr>
		</table>
	</form>
</body>
</html>