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
<title>查询玩家房卡数</title>
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
*{margin: 0;padding:0}
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
		$("#selectBtn").click(function(){
			//获取输入内容
			var agentId= $("#agentId").val().trim();  
			var ok = true;
			//检验格式
			if(agentId == ""){
				alert("输入不能为空！");
				ok = false;
			}
			//发送ajax请求
			if(ok){
				$.ajax({
					url:"agent/selectAengt.do",
					type:"post",
					data:{"agentId":agentId},
					dataType:"json",
					success:function(result){
						if(result.status == 0){
							var account = result.data.account;		//代理商账号
							var userID = result.data.userID;		//代理商id
							var roomCard = result.data.roomCard;	//代理商房卡
							//alert(gameId);
							window.location.href="agent/toAddRoomCard.do?account="+account+"&userID="+userID+"&roomCard="+roomCard;
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
						alert("查询失败！")
					}
				});
			}
		});
	});
</script>
</head>
<body>
	<form name="form1" method="post" action="" id="form1" onsubmit="return checkform(this)">
		<table class="gridtable" width="100%" border="1" align="center" cellpadding="3" cellspacing="0" bordercolor="A4B6D7"
			bgcolor="F2F8FF" class="admin_table">
			<tr>
				<td class="title_03" colspan="19" align ="center">
					<strong>查询代理商信息</strong>
				</td>
			</tr>
			<tr>
				<td align ="center" style="height:20px;">
					输入查询的代理商Id：<input name="alter" type="text" id="agentId" class="put" maxlength="15" size ="10" onkeyup="filterInput();"/>
					<input type="button" name="alterBtn" value="查 询" id="selectBtn" class="btn btn-info"/>
				</td>
			</tr>
		</table>
	</form>
</body>
</html>