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
<title>修改桌子参数</title>
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
			document.all.alterDesk.disabled = "disabled";
		}else{
			document.all.alterDesk.disabled = "";
		}
	}
	$(function(){
		$("#alterDesk").click(function(){
			//获取参数
			var gameid = $("#gameid").html();
			var roomid = $("#roomid").html();
			var id = $("#id").html();
			var maxGoldStock = $("#maxGoldStock").val().trim();
			var curGoldStock = $("#curGoldStock").val().trim();
			//检验格式
			var ok = true;
			if(gameid == "" || gameid == null){
				alert("游戏id不能为空！");
				ok = false;
			}
			if(roomid == "" || roomid == null){
				alert("房间id不能为空！");
				ok = false;
			}
			if(id == "" || id == null){
				alert("id号没能为空！");
				ok = false;
			}
			//发送ajax请求
			if(ok){
				$.ajax({
					url:"gameDesk/alterDesk.do",
					type:"post",
					data:{"gameid":gameid,"roomid":roomid,"id":id,"maxGoldStock":maxGoldStock,"curGoldStock":curGoldStock},
					dataType:"json",
					success:function(result){
						if(result.status == 0){
							alert(result.msg);
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
						alert("修改房间参数失败！");
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
					<strong>修改桌子参数</strong>
				</td>
			</tr>
			<tr>
				<td align="right" width="40%" style="height:20px;">
					游戏id：
				</td>
				<td colspan="2" align="left"  style="height:20px;">
					<span id="gameid">${gameid }</span>
				</td>
			</tr>
			<tr>
				<td align="right" width="40%" style="height:20px;">
					房间id：
				</td>
				<td colspan="2" align="left"  style="height:20px;">
					<span id="roomid">${roomid }</span>
				</td>
			</tr>
			<tr>
				<td align="right" width="40%" style="height:20px;">
					桌子id：
				</td>
				<td colspan="2" align="left"  style="height:20px;">
					<span id="id">${id }</span>
				</td>
			</tr>
			<tr>
				<td align="right">
					桌子的最大库存：
				</td>	
				<td colspan="2" align="left">
					<input name="pwd" type="text" id="maxGoldStock" class="put" maxlength="12" value="${maxGoldStock }" onkeyup="filterInput();check();"/>
					<span>建议最大金币数不超过25000</span>
				</td>
			</tr>
			<tr>
				<td align="right">
					桌子的当前库存：
				</td>
				<td colspan="2" align="left">
					<!-- 不能超出最大库存 -->
					<input name="pwd" type="text" id="curGoldStock" class="put" maxlength="12" value="${curGoldStock }" onkeyup="filterInput();check();"/>
					<span>建议不要超出最大金币库存</span>
				</td>
			</tr>

			<tr>
				<td align="right">
					&nbsp;
				</td>
				<td colspan="2" align="left">
					<input type="button" name="alterDesk" value=" 修 改 " id="alterDesk" class="put" disabled = "disabled"/>
					<span id="ErrorLabel" style="color:Red;"></span>
				</td>
			</tr>
		</table>
	</form>
</body>
</html>