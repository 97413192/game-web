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
<title>修改桌子概率</title>
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
	
	$(function(){
		//修改绑定单击事件
		$("#alterBtn").click(function(){
			//获取输入内容
			var id = $("#id").html();  //桌子概率
			var K5 = $("#K5").val().trim();  
			var RS = $("#RS").val().trim();  
			var SF = $("#SF").val().trim();  
			var K4 = $("#K4").val().trim();  
			var FH = $("#FH").val().trim();  
			var FL = $("#FL").val().trim();  
			var ST = $("#ST").val().trim();  
			var P2 = $("#P2").val().trim();  
			var P1 = $("#P1").val().trim();  
			var NP = $("#NP").val().trim();  
			var top = $("#top").val().trim();  
			var ok = true;
			//检验格式
			if(id == ""){
				alert("id不能为空！");
				ok = false;
			}
			if(K5 == ""){
				alert("牌型K5输入不能为空！");
				ok = false;
			}
			if(RS == ""){
				alert("牌型RS输入不能为空！");
				ok = false;
			}
			if(SF == ""){
				alert("牌型SF输入不能为空！");
				ok = false;
			}
			if(K4 == ""){
				alert("牌型K4输入不能为空！");
				ok = false;
			}
			if(FH == ""){
				alert("牌型FH输入不能为空！");
				ok = false;
			}
			if(FL == ""){
				alert("牌型FL输入不能为空！");
				ok = false;
			}
			if(ST == ""){
				alert("牌型ST输入不能为空！");
				ok = false;
			}
			if(P2 == ""){
				alert("牌型P2输入不能为空！");
				ok = false;
			}
			if(P1 == ""){
				alert("牌型P1输入不能为空！");
				ok = false;
			}
			if(NP == ""){
				alert("牌型NP输入不能为空！");
				ok = false;
			}
			if(RS == ""){
				alert("牌型RS输入不能为空！");
				ok = false;
			}
			if(top == ""){
				alert("最大上限top输入不能为空！");
				ok = false;
			}
			//发送ajax请求
			if(ok){
				$.ajax({
					url:"deskChance/alterDeskChance.do",
					type:"post",
					data:{"id":id,"K5":K5,"RS":RS,"SF":SF,"K4":K4,"FH":FH,"FL":FL,"ST":ST,"K3":K3,"P2":P2,"P1":P1,"NP":NP,"top":top},
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
						alert("修改失败！")
					}
				});
			}
		});
	});
</script>
</head>
<body>
	<form name="form1" method="post" action="./CreateUser.aspx" id="form1" onsubmit="return checkform(this)">
		<table width="100%" border="1" align="center" cellpadding="3" cellspacing="0" bordercolor="A4B6D7"
			bgcolor="F2F8FF" class="admin_table">
			<tr>
				<td class="title_03" colspan="19">
					<strong>修改桌子概率</strong>
				</td>
			</tr>
			<tr>
				<td align="right" width="40%" style="height:20px;">
					桌子概率id：
				</td>
				<td colspan="2" align="left"  style="height:20px;">
					<span id="id">${id }</span>
				</td>
			</tr>
			<tr>
				<td align="right" width="40%" style="height:20px;">
					5.K：
				</td>
				<td colspan="2" align="left"  style="height:20px;">
					<input name="K5" type="text" id="K5" class="put" 
						maxlength="10" size ="10" onkeyup="filterInput();" value="${K5 }"/>
					&nbsp;&nbsp;牌型5.K概率为：5.K/(R.S+S.F+4.F+F.H+F.L+S.T+3.K+2.P+1.P+N.P)						
				</td>
			</tr>
			<tr>
				<td align="right" width="40%" style="height:20px;">
					R.S：
				</td>
				<td colspan="2" align="left"  style="height:20px;">
					<input name="RS" type="text" id="RS" class="put" 
						maxlength="10" size ="10" onkeyup="filterInput();" value="${RS }"/>
					&nbsp;&nbsp;牌型R.S概率为：R.S/(R.S+S.F+4.F+F.H+F.L+S.T+3.K+2.P+1.P+N.P)					
				</td>
			</tr>
			<tr>
				<td align="right" width="40%" style="height:20px;">
					S.F：
				</td>
				<td colspan="2" align="left"  style="height:20px;">
					<input name="SF" type="text" id="SF" class="put" 
						maxlength="10" size ="10" onkeyup="filterInput();" value="${SF }"/>
					&nbsp;&nbsp;牌型S.F概率为：S.F/(R.S+S.F+4.F+F.H+F.L+S.T+3.K+2.P+1.P+N.P)					
				</td>
			</tr>
			<tr>
				<td align="right" width="40%" style="height:20px;">
					4.K：
				</td>
				<td colspan="2" align="left"  style="height:20px;">
					<input name="K4" type="text" id="K4" class="put" 
						maxlength="10" size ="10" onkeyup="filterInput();" value="${K4 }"/>
					&nbsp;&nbsp;牌型4.K概率为：4.K/(R.S+S.F+4.F+F.H+F.L+S.T+3.K+2.P+1.P+N.P)					
				</td>
			</tr>
			<tr>
				<td align="right" width="40%" style="height:20px;">
					F.H：
				</td>
				<td colspan="2" align="left"  style="height:20px;">
					<input name="FH" type="text" id="FH" class="put" 
						maxlength="10" size ="10" onkeyup="filterInput();" value="${FH }"/>
					&nbsp;&nbsp;牌型F.H概率为：F.H/(R.S+S.F+4.F+F.H+F.L+S.T+3.K+2.P+1.P+N.P)					
				</td>
			</tr>
			<tr>
				<td align="right" width="40%" style="height:20px;">
					F.L：
				</td>
				<td colspan="2" align="left"  style="height:20px;">
					<input name="FL" type="text" id="FL" class="put" 
						maxlength="10" size ="10" onkeyup="filterInput();" value="${FL }"/>
					&nbsp;&nbsp;牌型F.L概率为：F.L/(R.S+S.F+4.F+F.H+F.L+S.T+3.K+2.P+1.P+N.P)					
				</td>
			</tr>
			<tr>
				<td align="right" width="40%" style="height:20px;">
					S.T：
				</td>
				<td colspan="2" align="left"  style="height:20px;">
					<input name="ST" type="text" id="ST" class="put" 
						maxlength="10" size ="10" onkeyup="filterInput();" value="${ST }"/>
					&nbsp;&nbsp;牌型S.T概率为：S.T/(R.S+S.F+4.F+F.H+F.L+S.T+3.K+2.P+1.P+N.P)					
				</td>
			</tr>
			<tr>
				<td align="right" width="40%" style="height:20px;">
					3.K：
				</td>
				<td colspan="2" align="left"  style="height:20px;">
					<input name="K3" type="text" id="K3" class="put" 
						maxlength="10" size ="10" onkeyup="filterInput();" value="${K3 }"/>
					&nbsp;&nbsp;牌型3.K概率为：3.K/(R.S+S.F+4.F+F.H+F.L+S.T+3.K+2.P+1.P+N.P)					
				</td>
			</tr>
			<tr>
				<td align="right" width="40%" style="height:20px;">
					2.P：
				</td>
				<td colspan="2" align="left"  style="height:20px;">
					<input name="P2" type="text" id="P2" class="put" 
						maxlength="10" size ="10" onkeyup="filterInput();" value="${P2 }"/>
					&nbsp;&nbsp;牌型2.P概率为：2.P/(R.S+S.F+4.F+F.H+F.L+S.T+3.K+2.P+1.P+N.P)					
				</td>
			</tr>
			<tr>
				<td align="right" width="40%" style="height:20px;">
					1.P：
				</td>
				<td colspan="2" align="left"  style="height:20px;">
					<input name="P1" type="text" id="P1" class="put" 
						maxlength="10" size ="10" onkeyup="filterInput();" value="${P1 }"/>
					&nbsp;&nbsp;牌型1.P概率为：1.P/(R.S+S.F+4.F+F.H+F.L+S.T+3.K+2.P+1.P+N.P)					
				</td>
			</tr>
			<tr>
				<td align="right" width="40%" style="height:20px;">
					N.P：
				</td>
				<td colspan="2" align="left"  style="height:20px;">
					<input name="NP" type="text" id="NP" class="put" 
						maxlength="10" size ="10" onkeyup="filterInput();" value="${NP }"/>
					&nbsp;&nbsp;牌型N.P概率为：N.P/(R.S+S.F+4.F+F.H+F.L+S.T+3.K+2.P+1.P+N.P)					
				</td>
			</tr>
			<tr>
				<td align="right" width="40%" style="height:20px;">
					top：
				</td>
				<td colspan="2" align="left"  style="height:20px;">
					<input name="top" type="text" id="top" class="put" 
						maxlength="10" size ="10" onkeyup="filterInput();"value="${top }"/>
					&nbsp;&nbsp;触发条件上限（为百分比）					
				</td>
			</tr>
			<tr>
				<td align="right" width="40%" style="height:20px;">
				</td>
				<td colspan="2" align="left"  style="height:20px;">
					<input type="button" name="alterBtn" value=" 修 改 " id="alterBtn" class="put"/>
				</td>
			</tr>
		</table>
	</form>
</body>
</html>