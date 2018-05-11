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
<title>查询所有充值档次</title>
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
	
	alert(i){
		var a = i+10;
		var b = i+20;
		var c = i+30;
		var d = i+40;
		var id = $("#"+a).html();  //获取id号
		var levelId = $("#"+b).val().trim();  //档次
		var money = $("#"+c).val().trim();  //金额
		var jewel = $("#"+d).val().trim();  //钻石
		var 
		//判断格式
		var ok = true;
		if(id == ""){
			alter("id不能为空！");
			ok = false;
		}
		if(levelId == ""){
			alter("档次不能为空！");
			ok = false;
		}
		if(money == ""){
			alter("金额不能为空！");
			ok = false;
		}
		if(jewel == ""){
			alter("钻石不能为空！");
			ok = false;
		}
		if(ok){
			发送请求ajax请求
			$.ajax({
				url:"landingWard/alterLandingWard.do",
				type:"post",
				data:{"gold":gold,"id":id,"chance":chance},
				dataType:"json",
				success:function(result){
					if(result.status == 0){
						alert(result.msg);
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
					alert("查询所有充值档次失败！");
				}
			});
		}
	};
</script>
</head>
<body>
    <form name="form1" method="post" action="" id="form1">
        <table width="100%" border="1" align="center" cellpadding="3" cellspacing="0" bordercolor="A4B6D7"
            bgcolor="F2F8FF" class="admin_table">
            <tr>
                <td class="title_03" colspan="6" align="center">
                    <strong><font color="red">查询所有充值档次</font></strong></td>
            </tr>
             <tr style="font-weight:bold;font-style:italic;">
             	<td></td>
             	<td>档次id</td>
             	<td>档次编号</td>
             	<td>充值金额</td>
             	<td>获得钻石</td>
             	<td>修改</td>
             </tr>
            <c:forEach items="${doubleList}" var="log" varStatus="s">
            	<tr>
            		<td>${s.index+1 }</td>
	                <td id="${log.id+10 }">${log.id }</td>
	                <td>
	                	${log.levelId }
	                	<input type="text" id="${log.id+20 }" onkeyup = "filterInput()">
	                </td>
	                <td>
	                	${log.money}
	                	<input type="text" id="${log.id+30 }" onkeyup = "filterInput()">
	                </td>
	                <td>
	               		${log.jewel}
	               		<input type="text" id="${log.id+40 }" onkeyup = "filterInput()">
	                </td>
	                <td>
	                	<input type="button" value="修改" onclick="alter(${log.id })">
	                </td>
	            </tr>
            </c:forEach>
        </table>
    </form>
</body>
</html>