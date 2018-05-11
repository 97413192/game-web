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
<title>查询桌子</title>
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
	//检查所有
	function check(){
		var inputs = document.getElementsByClassName("put");
		//alert("dsfsdf");
		var count = 0;
		for(var i=0; i<inputs.length; i++){
			var str = inputs[i].value;
			//alert(str);
			if(str == "" || str == null){
				count++;
			}
		}
		if(count>0){
			document.all.CreateDesk.disabled = "disabled";
		}else{
			document.all.CreateDesk.disabled = "";
		}
	}
	//当选择了游戏名，返回相应的房间名列表,测试时，加到游戏下拉列表，单击事件
	function rooms(){
		//获取下拉选的游戏id
		var gameId = $("#gameId").val();
		//判断gameId是否有内容
		var ok = true;
		if(gameId == "" || gameId == null){
			alert("请选择游戏！");
			ok = false;
		}
		//发送ajax请求
		if(ok){
			$.ajax({
				url:"gameDesk/getRoom.do",
				type:"post",
				data:{"gameId":gameId},
				dataType:"json",
				success:function(result){
					if(result.status == 0){
						//alert(result.msg);
						for(var i=0;i<result.getData.size();i++){
							var room = "";
							room += "<option value="+result.getData.id+">"+result.getData.name+"</option>";
							var $room = $(room);
							//下拉选项添加到选项最后
							$("#roomId").append($room);
						}
						
					}else if(result.status == 1){//登陆服务连接失败
						alert(result.msg);
					}else if(result.status == 2){//通过登录服务获取房间信息失败
						alert(result.msg);
					}else if(result.status == 3){//未知错误
						alert(result.msg);
					}else if(result.status == -1){
						window.location.href="login/toLogin.do";
					}
				},
				error:function(){
					alert("获取房间失败！")
				}
			});
		}
	}
	$(function(){
		//给创建按钮绑定单击事件
		$("#CreateDesk").click(function(){
			alert("asdfasd");
			//获取输入的参数
			var gameId = $("#gameId").val();
			var roomId = $("#roomId").val();
			var id = $("#id").val().trim();
			//将上次查询出来的内容清除
			$("#maxGoldStock").html("");
			$("#curGoldStock").html("");
			$("#extraBonusScore").html("");
			//发送ajax请求
			$.ajax({
				url:"gameDesk/selectDesk.do",
				type:"post",
				data:{"gameId":gameId,"roomId":roomId,"id":id},
				dataType:"json",
				success:function(result){
					if(result.status == 0){
						//alert(result.msg);
						$("#maxGoldStock").html(result.data.maxGoldStock);
						$("#curGoldStock").html(result.data.curGoldStock);
						$("#extraBonusScore").html(result.data.extraBonusScore);
					}else if(result.status == 1){//登陆服务连接失败
						alert(result.msg);
					}else if(result.status == 2){//通过登陆服务查询桌子错误
						alert(result.msg);
					}else if(result.status == 3){//未知错误
						alert(result.msg);
					}else if(result.status == -1){
						window.location.href="login/toLogin.do";
					}
				},
				error:function(){
					alert("增加房间失败！")
				}
			});
		});
	});
</script>
</head>
<body>
	<form name="form1" method="post" action="" id="form1">
		<table width="100%" border="1" align="center" cellpadding="3" cellspacing="0" bordercolor="#A4B6D7"
			bgcolor="F2F8FF" class="admin_table2">
			<tr>
				<td class="title_03">
					<strong>桌子管理</strong>
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
					<strong>查询桌子</strong>
				</td>
			</tr>
			<tr>
				<td align="right" width="40%" style="height:20px;">
					所属游戏：
				</td>
				<td colspan="2" align="left"  style="height:20px;">
					<select name="kindid" id="gameId" class="put" onclick="check();">
						<option value="">请选择所属游戏 </option>
						<option value="1">斗地主</option>
						<option value="2">四川麻将</option>
						<option value="3">广东麻将</option>
						<option value="4">斗牛牛</option>
					</select>
					<%-- 
					<select name="kindid" id="gameId" class="put" onclick="check();">
						<option value="">请选择所属游戏 </option>
						<c:forEach items="${games}" var="game" varStatus="s">
							<option value="${game.id }">${game.name }</option>
						</c:forEach>
					</select>
					--%>
					<!-- 
					<a href="GameTypesAdd.aspx">添加游戏类别</a>
					 -->
				</td>
			</tr>
			<tr>
				<td align="right">
					所属房间：
				</td>	
				<td colspan="2" align="left">
					<!-- 通过c标签foreach循环 -->
					<select name="kindid" id="roomId" class="put" onclick="check();">
						<option value="">请选择所属房间</option>
						<option value="1">1</option>
						<option value="2">2</option>
						<option value="3">3</option>
						<option value="4">4</option>
					</select>
				</td>
			</tr>
			<tr>
				<td align="right">
					id编号：
				</td>
				<td colspan="2" align="left">
					<input name="id" type="text" id="id" class="put" maxlength="12" size="14"
						onkeyup="filterInput();check();"/>
					<span id="userpwdmsg_02" class="red"></span>
				</td>
			</tr>
			<tr>
				<td align="right">
					桌子最大的金币库存：
				</td>
				<td colspan="2" align="left">
					<span id="maxGoldStock"></span>
				</td>
			</tr>
			<tr>
				<td align="right">
					桌子当前金币库存：
				</td>
				<td colspan="2" align="left">
					<span id="curGoldStock"></span>
				</td>
			</tr>
			<tr>
				<td align="right">
					额外皮子分：
				</td>
				<td colspan="2" align="left">
					<span id="extraBonusScore"></span>
				</td>
			</tr>

			<tr>
				<td align="right">
					&nbsp;
				</td>
				<td colspan="2" align="left">
					<input type="button" name="CreateDesk" value=" 查 询 " id="CreateDesk" disabled = "disabled"/>
					<span id="ErrorLabel" style="color:Red;"></span>
				</td>
			</tr>
		</table>
	</form>
</body>
</html>