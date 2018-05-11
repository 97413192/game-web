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
<title>增加管理员</title>
<base href="<%=basePath%>">
<link href="css/systemSettings/Inc_style.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
<style type="text/css">
	body{
		margin-left: 0px;
		margin-top: 0px;
		margin-right: 0px;
		margin-bottom: 0px;
	}
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
</style>
<script src="js/jquery-1.11.1.js"></script>
<script type="text/ecmascript" language="javascript">
	$(function(){
		//给确定添加加单击事件
		$("#Button1").click(function(){
			//获取输入内容
			var name = $("#AdminName").val().trim();
			var password = $("#AdminPwd").val().trim();
			var newpwd = $("#newPwd").val().trim();
			//获取操作系统
			var system = detectOS();
			//获取多选的值
			//var text = $("input:checkbox[name='chkListModels']:checked");
			var chk_value =[]; 
			$('input[name="chkListModels"]:checked').each(function(){ 
				chk_value.push($(this).val()); 
				});
			var power = chk_value.toString();
			
			
			var reg1 = /^[A-Za-z]+[0-9]+$/;
			var reg2 = /^[0-9]+[A-Za-z]+$/;
			//检查格式
			//清空以前提示消息
			$("#name").html("");
			$("#pwd").html("");
			var ok = true;
			if(name == ""){
				$("#name").html("用户名不能为空！");
				ok = false;
			}
			if(!reg1.test(name) && !reg2.test(name)){
				alert("用户名必须是数字和字母组合！");
				alert("密码!");
				ok = false;
			}
			if(password == ""){
				$("#pwd").html("密码为空！");
				ok = false;
			}
			if(password != newpwd){
				$("#newpassword").html("两次输入的密码不相同！");
				ok = false;
			}
			if(chk_value.length==0){
				alert("你还没有选择任何内容！");
			}
			//发送ajax请求
			if(ok && chk_value.length != 0){
				$.ajax({
					url:"systemSettings/addManager.do",
					type:"post",
					data:{"name":name,"password":password,"system":system,"power":power},
					dataType:"json",
					success:function(result){
						//result就是服务器返回的JSON结果
						if(result.status==0){//创建成功
							alert(result.msg);
							//清空输入框中信息
							$("#AdminName").val("");
							$("#AdminPwd").val("");
							$("#newPwd").val("");
							//清空多选
							$(":checkbox").attr("checked",false);
						}else if(result.status == 1){
							alert(result.msg);
						}else if(result.status == 2){
							alert(result.msg);
							$("#AdminName").val("");
							$("#AdminPwd").val("");
							$("#newPwd").val("");
							//清空多选
							$(":checkbox").attr("checked",false);
						}else if(result.status == 3){
							alert(result.msg);
						}else if(result.status == 4){
							alert(result.msg);
						}else if(result.status == 5){
							alert(result.msg);
						}else if(result.status == 6){
							alert(result.msg);
						}else if(result == -1){
							window.location.href="login/toLogin.do";
						}
					},
					error:function(){
						alert("增加管理员失败！");
					}
				});
			}
		});
	});
	
	//获取操作系统
	function detectOS() {
	    var sUserAgent = navigator.userAgent;
	    var isWin = (navigator.platform == "Win32") || (navigator.platform == "Windows");
	    var isMac = (navigator.platform == "Mac68K") || (navigator.platform == "MacPPC") || (navigator.platform == "Macintosh") || (navigator.platform == "MacIntel");
	    if (isMac) return "Mac";
	    var isUnix = (navigator.platform == "X11") && !isWin && !isMac;
	    if (isUnix) return "Unix";
	    var isLinux = (String(navigator.platform).indexOf("Linux") > -1);
	    if (isLinux) return "Linux";
	    if (isWin) {
	        var isWin2K = sUserAgent.indexOf("Windows NT 5.0") > -1 || sUserAgent.indexOf("Windows 2000") > -1;
	        if (isWin2K) return "Win2000";
	        var isWinXP = sUserAgent.indexOf("Windows NT 5.1") > -1 || sUserAgent.indexOf("Windows XP") > -1;
	        if (isWinXP) return "WinXP";
	        var isWin2003 = sUserAgent.indexOf("Windows NT 5.2") > -1 || sUserAgent.indexOf("Windows 2003") > -1;
	        if (isWin2003) return "Win2003";
	        var isWinVista= sUserAgent.indexOf("Windows NT 6.0") > -1 || sUserAgent.indexOf("Windows Vista") > -1;
	        if (isWinVista) return "WinVista";
	        var isWin7 = sUserAgent.indexOf("Windows NT 6.1") > -1 || sUserAgent.indexOf("Windows 7") > -1;
	        if (isWin7) return "Win7";
	    }
	    return "other";
	}
</script>
</head>
<body>
    <div align="center">
        <form name="Login" method="POST" action="" id="Login" onsubmit="return CheckForm();">
        <div style="width: 100%; text-align: center; margin: 0px auto;">
            <table class="gridtable" border="1" align="center" cellpadding="3" cellspacing="0" bordercolorlight="#335078"  style="width: 99%; font-size: 12px; letter-spacing: 1px; ">
                <tr bgcolor="#B6EBC4">
                    <td bgcolor="#C5D5E4" height="25" style="width: 615px" align="center">
                        <p ><b>增加管理员</b></p>
                    </td>
                </tr>
                <tr>
                    <td height="25" align="center" bgcolor="#DFE8F0" style="border-top: medium none;text-align:left;  border-bottom: medium none;padding-left:30px; "><br>
                                                                        用户名：&nbsp;&nbsp;<input name="AdminName" type="text" id="AdminName" class="put" />
                        &nbsp;&nbsp;<span id="name" style="color:red"></span>
                    </td>
                </tr>
                <tr>
                    <td height="25" align="center" bgcolor="#DFE8F0" style="border-top: medium none;text-align:left;  border-bottom: medium none;padding-left:30px; ">
                                                                     密&nbsp;&nbsp;码：&nbsp;&nbsp;<input name="AdminPwd" type="password" id="AdminPwd" class="put" />
                       &nbsp;&nbsp;<span id="pwd" style="color:red"></span>
                    </td>
                </tr>
                <tr>
                    <td height="25" align="center" bgcolor="#DFE8F0" style="border-top: medium none;text-align:left;  border-bottom: medium none;padding-left:30px; ">
                                                                      确认密码：<input name="newPwd" type="password" id="newPwd" class="put" />
                       &nbsp;&nbsp;<span id="newpassword" style="color:red"></span>
                    </td>
                </tr>
                <tr>
                   <td height="25" bgcolor="#DFE8F0" style="border-top: medium none;text-align:left;  border-bottom: medium none;padding-left:30px; ">
                        <div>
                            <div style="width:100%; text-align:left;">
                                                                                                分配权限：<br/>
                                <div style="padding-left: 18px; line-height: 30px;">
                                    <span id="chkListModels">
                                    	<input id="chkListModels_1" type="checkbox" name="chkListModels" value="1"/><label for="chkListModels_1">信息发布</label><br>
                                    	<input id="chkListModels_2" type="checkbox" name="chkListModels" value="2"/><label for="chkListModels_2">设置系统</label><br>
                                    	<input id="chkListModels_3" type="checkbox" name="chkListModels" value="3"/><label for="chkListModels_3">创建一级代理</label><br>
                                    	<input id="chkListModels_4" type="checkbox" name="chkListModels" value="4"/><label for="chkListModels_4">删除一级代理</label><br>
                                    	<input id="chkListModels_5" type="checkbox" name="chkListModels" value="5"/><label for="chkListModels_5">出售房卡</label><br>
                                    	<input id="chkListModels_6" type="checkbox" name="chkListModels" value="6"/><label for="chkListModels_6">修改登录密码</label><br>
                                </div>
                            </div>
                        </div>
                    </td>
                </tr>
                <tr>
                    <td height="25" bgcolor="#DFE8F0" style="border-top: medium none;text-align:left;  border-bottom: medium none;padding-left:50px; ">
                    	<input type="button" class="btn btn-info" name="Button1" value="确定添加" id="Button1" name="B1" />
                    </td>
                </tr>
                <tr>
                	<td height="25" bgcolor="#DFE8F0" style="border-top: medium none;text-align:left;  border-bottom: medium none;padding-left:50px; ">
                    	<a class="btn btn-warning" href="systemSettings/toAlter.do">修改</a>&nbsp;&nbsp;&nbsp;&nbsp;
                    	<a class="btn btn-danger" href="systemSettings/toDelete.do">删除</a>
                    </td>
                </tr>
            </table>
        </div>
        </form>
    </div>
</body>
</html>