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
<title>玩家注册赠送房卡</title>
<base href="<%=basePath%>">
<link href="css/systemSetttings/inc_style.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
<style type="text/css">
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

table.imagetable {
    font-family: verdana,arial,sans-serif;
    font-size:11px;
    color:#333333;
    border-width: 1px;
    border-color: #999999;
    border-collapse: collapse;
}
table.imagetable th {
    background:#b5cfd2 url('images/cell-blue.jpg');
    border-width: 1px;
    padding: 8px;
    border-style: solid;
    border-color: #999999;
}
table.imagetable td {
    background:#dcddc0 url('images/cell-grey.jpg');
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
		$("#alterBtn").click(function(){
			//获取输入内容
			var roomCardNumber= $("#roomCardNumber").val().trim();  
			var ok = true;
			//检验格式
			if(roomCardNumber == ""){
				alert("输入不能为空！");
				ok = false;
			}
			if (confirm("确定修改玩家注册时赠送的房卡数？")) {
				ok = true;
			} else {
				ok = false;
			}
			//发送ajax请求
			if(ok){
				$.ajax({
					url:"gamePlayer/alertPlayerStartRoomCard.do",
					type:"post",
					data:{"roomCardNumber":roomCardNumber},
					dataType:"json",
					success:function(result){
						if(result.status == 0){
							alert(result.msg);
							 $("#roomCard").html(roomCardNumber);
// 							 $("#roomCardNumber").val("");
						}else if(result.status == 1){
							alert(result.msg);
						}else if(result.status == 2){
							alert(result.msg);
						}else if(result.status == 3){
							alert(result.msg);
						}else if(result.status == 4){
							alert(result.msg);
						}else if(result.status == -1){
							window.location.href="login/toLogin.do";
						}
					},
					error:function(){
						alert("修改失败！")
					}
				});
			}
		});
		//修改绑定单击事件
		$("#roomCardBin").click(function(){
			//获取输入内容
			var roomCardBing= $("#roomCardBing").val().trim();  
			//检验格式
			var ok = true;
			if(roomCardBing == ""){
				alert("输入不能为空！");
				ok = false;
			}
			if (confirm("确定修改玩家绑定赠送房卡数？")) {
				ok = true;
			} else {
				ok = false;
			}
				
	if (ok) {
				$.ajax({
					url : "gamePlayer/updateRoomCardBin.do",
					type : "post",
					data : {
						"roomCardBing" : roomCardBing
					},
					dataType : "json",
					success : function(result) {
						if (result.status == 0) {
							alert(result.msg);
							$("#roomCard2").html(roomCardBing);
// 							$("#roomCardBin").val("");
						} else if (result.status == -1) {
							window.location.href = "login/toLogin.do";
						} else if (result.status == 1) {
							alert("修改失败!");
						}

					},
					error : function() {
						alert("修改失败！")
					}
				})
			}
			;
		});
	});
</script>
</head>
<body>
	<form name="form1" method="post" action="./CreateUser.aspx" id="form1" onsubmit="return checkform(this)">
		<table class="gridtable" width="100%"  align="center" cellpadding="3" cellspacing="0" bordercolor="A4B6D7"
			bgcolor="F2F8FF" class="admin_table">
			<tr>
				<td class="title_03" align ="right" style="width: 40%">
					玩家注册时赠送的房卡数：
				</td>
				<td class="title_03" align ="left" >
					<span id="roomCard">${roomCard }</</span>
				</td>
			</tr>
			<tr>
				<td align ="right" style="height:20px;">
					输入修改玩家注册时赠送的房卡数：
				</td>
				<td align ="left" style="height:20px;">
					<!-- 没有从服务器获取到数据时,不显示修改初始房卡按钮 -->
					<c:if test="${roomCard != -1 }">
						<input name="alter" type="text" id="roomCardNumber" class="put" maxlength="15" size ="10" onkeyup="filterInput();"/>
						<input type="button" name="alterBtn" value=" 修 改 " id="alterBtn" class="btn btn-success"/>
					</c:if>
				</td>
			</tr>
			<tr>
				<td></td>
				<td></td>
			</tr>
			
			<tr>
				<td class="title_03" align ="right" style="width: 40%">
					玩家绑定赠送房卡数：
				</td>
				<td class="title_03" align ="left" >
					<span id="roomCard2">${roomCard2 }</</span>
				</td>
			</tr>
			<tr>
				<td align ="right" style="height:20px;">
					输入修改玩家绑定赠送房卡数：
				</td>
				<td align ="left" style="height:20px;">
					<!-- 没有从服务器获取到数据时,不显示修改初始房卡按钮 -->
					<c:if test="${roomCard2 != -1 }">
						<input name="alter" type="text" id="roomCardBing" class="put" maxlength="15" size ="10" onkeyup="filterInput();"/>
						<input type="button" name="alterBtn" value="修 改 " id="roomCardBin" class="btn btn-success"/>
					</c:if>
				</td>
			</tr>
		</table>
	</form>
</body>
</html>