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
<title>修改游戏房间</title>
<base href="<%=basePath%>">
<link href="css/gameRoomManagement/inc_style.css" rel="stylesheet" type="text/css" />
<style type="text/css">
		body{
			margin-top: 0px;
		}
		select{
		    z-index:0;
		}
</style>
<script src="js/basevalue.js"></script>
<script src="js/jquery-1.11.1.js"></script>
<script src="js/gameRoomManagement/public.js" type="text/javascript"></script>
<script src="js/gameRoomManagement/jquery.js" type="text/javascript"></script>
<script src="js/gameRoomManagement/admin.js" type="text/javascript"></script>
<script src="js/gameRoomManagement/Global.js" type="text/javascript"></script>
<script>
	//要求输入的为数字
	function filterInput(){
		if (event.type.indexOf("key") != -1){
			var re = /37|38|39|40/g
			if (event.keyCode.toString().match(re)){
				return false
			}
		}
		event.srcElement.value = event.srcElement.value.replace(/[^0-9]/g, "")
	}
	//非空检查
	function checkA(obj) {
		var str = obj.value; 
		//alert(str);
		if(str != null && str != "") {
		 document.all.Button1.disabled = "";
		} else {
		 document.all.Button1.disabled = "disabled";
		}
	}
	
	//检查所有
	function check(){
		var inputs = document.getElementsByClassName("put");
		var count = 0;
		for(var i=0; i<inputs.length; i++){
			var str = inputs[i].value;
			if(str == "" || str == null){
				count++;
			}
		}
		if(count>0){
			document.all.Button1.disabled = "disabled";
		}else{
			document.all.Button1.disabled = "";
		}
	}

	$(function(){
		$("#Button1").click(function(){
			//获取填写数据
			var gameName = $("#gameName").html().trim();
			var oldroomName = $("#hidden").val().trim();
			var roomName = $("#roomName").val().trim();
			var VIP = $("#VIP").val().trim();
			var goldLimit = $("#goldLimit").val().trim();
			var level= $("#level").val().trim();
			var maxPeople = $("#maxPeople").val().trim();
			var rate = $("#rate").val().trim();
			var basicBet = $("#basicBet").val().trim();
			var minBet = $("#lessBet").val().trim();
			var maxBet = $("#maxBet").val().trim();
			var ok = true;
			//判断房间名是否符合规范
			if(!/^[0-9a-zA-Z\u4e00-\u9fa5]{6,20}$/.test(roomName)){
				alert("房间名长度6～20个中文，由字母、数字组成");
				ok = false;
			}
			//判断gameName和oldName是否有值
			if(gameName == null || gameName =="" || oldroomName == null || oldroomName ==""){
				window.location.href="gameRoom/toSelectAllGameRoom.do?roomIndex=-1";
				ok = false;
			}
			//发送ajax请求
			if(ok){
				$.ajax({
					url:"gameRoom/alterGameRoom.do",
					type:"post",
					data:{"gameId":gameName,"oldName":oldroomName,"name":roomName,"vipLimit":VIP,"goldLimit":goldLimit,"level":level,
						"peopleNumLimit":maxPeople,"creditRate":rate,"betBaseScore":basicBet,"betMinScore":minBet,"betMaxScore":maxBet},
					dataType:"json",
					success:function(result){
						if(result.status == 0){
							alert(result.msg);
						}else if(result.status == 1){//登陆服务连接失败
							alert(result.msg);
						}else if(result.status == 2){//没有传入room值
							alert(result.msg);
						}else if(result.status == 3){//房间名不合法
							alert(result.msg);
						}else if(result.status == 4){//创建房间失败
							alert(result.msg);
						}else if(result.status == 5){//传入oldname没有从登陆服务获得room
							alert(result.msg);
						}else if(result.status == 6){//创建房间未知错误
							alert(result.msg);
						}else if(result.status == -1){
							window.location.href="login/toLogin.do";
						}
					},
					error:function(){
						alert("增加房间失败！")
					}
					
				});
			}
		});
	});
</script>
</head>
<body>
	<form name="myform" method="post" action="" id="myform">
		<table width="100%" border="1" align="center" cellpadding="5" cellspacing="0" bordercolor="A4B6D7"
			bgcolor="F2F8FF" class="admin_table">
			<tr>
				<td colspan="2" class="title_03">
					<strong>修改游戏房间</strong>
				</td>
			</tr>
			<tr>
				<td align="right">
					游戏名称：
				</td>
				<td align="left">
					<span id="gameName">${room.gameId }</span>
					<span id="cue1"></span>
				</td>
			</tr>
			<tr>
				<td align="right">
					游戏房间名称：
				</td>
				<td align="left">
					<input name="roomName" type="text" id="roomName" class="put" maxlength="30" size="10" 
					onkeyup="check();" value="${room.name }"/>
					<input type="hidden" id="hidden" value="${room.name }">
					<span id="cue2">例如：斗地主金币场</span>
				</td>
			</tr>
			<tr>
				<td align="right">
					VIP等级：
				</td>
				<td align="left">
					<input name="VIP" type="text" id="VIP" class="put" size="10"
						maxlength="15" onkeyup="filterInput();check();" value="${room.vipLimit }"/>
					<span id="cue3">例如：0，表示最低进入VIP等级</span>
				</td>
			</tr>
			<tr>
				<td align="right">
					进入房间最低金币限制：
				</td>
				<td align="left">
					<input name="goldLimit" type="text" id="goldLimit" class="put" size="10"
						maxlength="15" onkeyup="filterInput();check();" value="${room.goldLimit }"/>
					<span id="cue4">例如：100</span>
				</td>
			</tr>
			<tr>
				<td align="right">
					level：
				</td>
				<td align="left">
					<input name="level" type="text" id="level" class="put" size="10"
						maxlength="15" onkeyup="filterInput();check();" value="${room.level }"/>
					<span id="cue5">例如：100</span>
				</td>
			</tr>
			<tr>
				<td align="right">
					人数上限：
				</td>
				<td align="left">
					<input name="maxPeople" type="text" id="maxPeople" class="put" size="10"
						maxlength="3" onkeyup="filterInput();check();" value="${room.peopleNumLimit }"/>
					<span id="cue6">房间最多容纳人数，建议200人</span>
				</td>
			</tr>
			<tr>
				<td align="right">
					金币兑换积分比例：
				</td>
				<td align="left">
					<input name="rate" type="text" id="rate" class="put" size="10"
						maxlength="3" onkeyup="filterInput();check();" value="${room.creditRate }"/>
					<span id="cue7">如：10，表示10金币兑换一积分</span>
				</td>
			</tr>
			<tr>
				<td align="right">
					基础押注分：
				</td>
				<td align="left">
					<input name="basicBet" type="text" id="basicBet" class="put" size="10"
						maxlength="9" onkeyup="filterInput();check();" value="${room.betBaseScore }"/>
					<span id="cue8">押一次押的积分</span>
				</td>
			</tr>
			<tr>
				<td align="right">
					最小押注分：
				</td>
				<td align="left">
					<input name="lessBet" type="text" id="lessBet" class="put" size="10"
						maxlength="9" onkeyup="filterInput();check();" value="${room.betMinScore }"/>
					<span id="cue8">开牌需要最低的积分数</span>
				</td>
			</tr>
			<tr>
				<td align="right">
					最大押注分：
				</td>
				<td align="left">
					<input type="text" size="10" name="maxBet" class="put" id="maxBet"
						onkeyup="filterInput();check();" value="${room.betMaxScore }"/>
						<span id="cue10">押注达到此值，自动开牌</span>
				</td>
			</tr>
			<tr>
				<td>
					<input type="hidden" name="action" value="add" />&nbsp;<input type="hidden" name="action2"
						value="add" />
				</td>
				<td align="left">
					<input type="button" name="Button1" value=" 修 改 " id="Button1" Class="put" 
						disabled="disabled"/>
				</td>
			</tr>
		</table>
	</form>
</body>
</html>