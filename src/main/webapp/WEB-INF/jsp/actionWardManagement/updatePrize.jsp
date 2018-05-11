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
<title>修改奖励</title>
<base href="<%=basePath%>">
<!-- <link href="css/systemSetttings/inc_style.css" rel="stylesheet" type="text/css" /> -->
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
		$("#back").click(function(){
			window.location.href="actionWard/selectShareWard.do?index=-1";
		});
		$("#updatePrize").click(function(){
			var prize=$("#prizeNum").val();
			var id=$("#id").html();
			var OK=true;
			if(id==null || id==""){
				alert("id为空");
				OK=false;
			}
			if(prize==null || prize ==""){
				alert("元宝数量不能为空");
				OK=false;
			}
			if(OK){
				$.ajax({
					url:"actionWard/toUpdatePrize.do",
					type:"post",
					data:{"id":id,"prize":prize},
					dataType:"json",
					success:function(result){
						if(result.status==0){
							alert(result.msg);
						
						}else if(result.status==-1){
							window.location.href="login/toLogin.do";
						}else{
							alert(result.msg);
						}
					},
					error:function(){
						alert("修改异常！");
					}
					
				});
			}
		});
});
	</script>
</head>
<body>
	<form name="form1" method="post" action="systemSettings/selectManager.do" id="form1" onsubmit="return checkform(this)">
		<table width="100%" border="1" align="center" cellpadding="3" cellspacing="0" bordercolor="#A4B6D7"
			bgcolor="F2F8FF" class="admin_table2">
			<tr>
				<td class="title_03">
					<strong>奖励修改</strong>
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
					<strong>奖励详细信息</strong>
				</td>
			</tr>
			<tr>
				<td align="right" width="40%" style="height:20px;">
					奖励id：
				</td>
				<td colspan="2" align="left"  style="height:20px;">
					<span id="id">${share.id }</span>
				</td>
			</tr>
			<tr>
				<td align="right" width="40%" style="height:20px;">
					奖励类型:
				</td>
				<td colspan="2" align="left"  style="height:20px;">
					<span id="uuidmsg">元宝</span>
				</td>
			</tr>
			<tr>
			<td align="right" width="40%" style="height:20px;">
					元宝数量:
				</td>
				<td colspan="2" align="left"  style="height:20px;">
					<input type="text" value="${share.prize }"id="prizeNum" onkeyup="value=value.replace(/[^\d]/g,'') " onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))"/>
				</td>
			</tr>
			<tr>
				<td align="right">
					&nbsp;
				</td>
				<td colspan="2" align="left">
					<input type="button" name="CreateUser" value=" 修 改 " id="updatePrize" class="put" />&nbsp;&nbsp;
					<input type="button" name="CreateUser" value=" 返回 " id="back" class="put" />
				</td>
			</tr>
		</table>
	</form>
</body>
</html>