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
<title>增加消息</title>
<base href="<%=basePath%>">
<!-- <link href="css/systemSettings/Inc_style.css" rel="stylesheet" type="text/css" /> -->
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
		margin-left: 0px;
		margin-top: 0px;
		margin-right: 0px;
		margin-bottom: 0px;
	}
</style>
<script src="js/jquery-1.11.1.js"></script>
<script type="text/ecmascript" language="javascript">
	$(function(){
		//给确定添加加单击事件
		$("#Button1").click(function(){
			//获取输入内容
			var text = $("#text").val().trim();
			var category = $("#category").val();
			
			//发送ajax请求
			$.ajax({
				url:"information/addInformation.do",
				type:"post",
				data:{"text":text,"category":category},
				dataType:"json",
				success:function(result){
					//result就是服务器返回的JSON结果
					if(result.status==0){			//创建成功
						alert(result.msg);
					}else if(result.status == 1){	//用户名已经存在
						alert(result.msg);
					}else if(result.status == 2){	//未知错误
						alert(result.msg);
					}else if(result == -1){
						window.location.href="login/toLogin.do";
					}
				},
				error:function(){
					alert("增加管理员失败！");
				}
			});
			
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
                        <p ><b>增加消息</b></p>
                    </td>
                </tr>
                <tr>
                    <td align="center" bgcolor="#DFE8F0" style="border-top: medium none;  border-bottom: medium none;padding-left:30px; "><br>
                                                                        内容：&nbsp;&nbsp;<br>
                          	<textarea name="AdminName" cols="50" rows="5" id="text" class="put" ></textarea>
                        &nbsp;&nbsp;<span id="name" style="color:red"></span>
                    </td>
                </tr>
                <tr>
                    <td height="25" align="center" bgcolor="#DFE8F0" style="border-top: medium none;align:center;  border-bottom: medium none;padding-left:30px; ">
                                                                     类&nbsp;&nbsp;别：
                                                     <select name="AdminPwd" type="password" id="category" class="put">  
													 	<option value ="2">跑马灯</option>  
													 	<option value ="1">公告</option>  
													 </select>                 
                       &nbsp;&nbsp;<span id="pwd" style="color:red"></span>
                    </td>
                </tr>
                
                <tr>
                    <td height="25" bgcolor="#DFE8F0" style="border-top: medium none;align:center;  border-bottom: medium none;padding-left:28px; ">
                    	<input type="button" class="btn btn-success" name="Button1" value="确定添加" id="Button1" name="B1" />
                    </td>
                </tr>
                <!-- 
                <tr>
                	<td height="25" bgcolor="#DFE8F0" style="border-top: medium none;text-align:left;  border-bottom: medium none;padding-left:28px; ">
                    	<a class="" href="systemSettings/toAlter.do">修改</a>&nbsp;&nbsp;
                    	<a class="" href="systemSettings/toDelete.do">删除</a>
                    </td>
                </tr>
                 -->
            </table>
        </div>
        </form>
    </div>
</body>
</html>