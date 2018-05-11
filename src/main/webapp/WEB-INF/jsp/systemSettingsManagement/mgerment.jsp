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
<title>管理员管理</title>
<base href="<%=basePath%>">
<link href="css/systemSettings/Inc_style.css" rel="stylesheet" type="text/css" />
<style type="text/css">
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
		//$("#none").hide(); 
		//给确定添加加单击事件
		$("#Button1").click(function(){
			//$("#none").hide(); 
			//alert("sadfasdf");
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
			//alert(chk_value);
			//var power = chk_value.join(",");
			var power = chk_value.toString();
			//alert(power);
			//检查格式
			//清空以前提示消息
			$("#name").html("");
			$("#pwd").html("");
			var ok = true;
			if(name == ""){
				$("#name").html("用户名为空！");
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
					url:"systemSettings/add.do",
					type:"post",
					data:{"name":name,"password":password,"system":system,"power":power},
					dataType:"json",
					success:function(result){
						//result就是服务器返回的JSON结果
						if(result.status==0){//创建成功
							alert(result.msg);
							//window.location.href="systemSettings/toAddManager.do"
							//清空输入框中信息
							$("#AdminName").val("");
							$("#AdminPwd").val("");
							//清空多选
							$(":checkbox").attr("checked",false);
						}else if(result.status == 1){//创建失败
							alert(result.msg);
						}else if(result.status == 2){//用户名已经存在
							alert(result.msg);
							$("#AdminName").val("");
							$("#AdminPwd").val("");
						}else if(result.status == 2){//未知错误
							alert(result.msg);
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
<!--
    function SetFocus() {
        if (document.Login.AdminName.value == '')
            document.Login.AdminName.focus();
        else
            document.Login.AdminName.select();
    }

    function CheckForm() {
        if (document.Login.AdminName.value == '') {
            alert('请输入用户名！');
            document.Login.AdminName.focus();
            return false;
        }
        if (document.Login.AdminPwd.value == '') {
            alert('请输入密码！');
            document.Login.AdminPwd.focus();
            return false;
        }
    }

    function CheckBrowser() {
        var app = navigator.appName;
        var verStr = navigator.appVersion;
        if (app.indexOf('Netscape') != -1) {
            alert('友情提示：\n    您使用的是Netscape浏览器，可能会导致无法使用后台的部分功能。建议您使用 IE6.0 或以上版本。');
        } else if (app.indexOf('Microsoft') != -1) {
            if (verStr.indexOf('MSIE 3.0') != -1 || verStr.indexOf('MSIE 4.0') != -1 || verStr.indexOf('MSIE 5.0') != -1 || verStr.indexOf('MSIE 5.1') != -1)
                alert('友情提示：\n    您的浏览器版本太低，可能会导致无法使用后台的部分功能。建议您使用 IE6.0 或以上版本。');
        }
    }
//-->
</script>
</head>
<body>
    <div align="center">
        <form name="Login" method="POST" action="" id="Login" onsubmit="return CheckForm();">
			<input type="hidden" name="__VIEWSTATE" id="__VIEWSTATE" value="/wEPDwULLTE3NzAwNDA5OTcPZBYEAgMPZBYCAgUPEGQPFhNmAgECAgIDAgQCBQIGAgcCCAIJAgoCCwIMAg0CDgIPAhACEQISFhMQBRHpppbpobVGTEFTSOeuoeeQhgUCMDFnEAUS546p5a6255WZ6KiA566h55CGBQIwMmcQBRLnvZHnq5nlj4LmlbDorr7nva4FAjAzZxAFEueUqOaIt+euoeeQhuS4reW/gwUCMDRnEAUS5YWF5YC8566h55CG5Lit5b+DBQIwNWcQBRLmuLjmiI/nrqHnkIbkuK3lv4MFAjA2ZxAFEui9rOi0pueuoeeQhuS4reW/gwUCMDhnEAUS6K6w5b2V5p+l6K+i5Lit5b+DBQIwOWcQBRLmlrDpl7vnrqHnkIbkuK3lv4MFAjEwZxAFEuWlluWTgeeuoeeQhuS4reW/gwUCMTFnEAUS57O757uf6YWN572u5Lit5b+DBQIxMmcQBRLmjqjlub/ns7vnu5/nrqHnkIYFAjEzZxAFEumdk+WPt+euoeeQhuS4reW/gwUCMTRnEAUV6a2F5Yqb5YC85YWR5o2i566h55CGBQIxNmcQBRLpgZPlhbfnrqHnkIbkuK3lv4MFAjE1ZxAFFeS7o+eQhuWVhuW4kOWPt+euoeeQhgUCMTdnEAUY5Y+R6YCB5L+h5oGv566h55CG5Lit5b+DBQIxOGcQBRLmr5TotZvnrqHnkIbkuK3lv4MFAjE5ZxAFEuacjeijheeuoeeQhuS4reW/gwUCMjBnZGQCBQ8WAh4LXyFJdGVtQ291bnQCARYCZg9kFgJmDxUEBzIzNTYzMzIBM3Y8YSBocmVmPSJEZWxfQWRtaW5Vc2VyLmFzcHg/aWQ9MyIgb25jbGljaz0ne2lmKGNvbmZpcm0oIuehruWumuimgeWIoOmZpOWQlz8iKSl7cmV0dXJuIHRydWU7fXJldHVybiBmYWxzZTt9Jz7liKDpmaQ8L2E+IjIwMTYvNy8yOSDmmJ/mnJ/kupQg5LiL5Y2IIDI6NDE6MDBkGAEFHl9fQ29udHJvbHNSZXF1aXJlUG9zdEJhY2tLZXlfXxYUBQ9jaGtMaXN0TW9kZWxzOjAFD2Noa0xpc3RNb2RlbHM6MQUPY2hrTGlzdE1vZGVsczoyBQ9jaGtMaXN0TW9kZWxzOjMFD2Noa0xpc3RNb2RlbHM6NAUPY2hrTGlzdE1vZGVsczo1BQ9jaGtMaXN0TW9kZWxzOjYFD2Noa0xpc3RNb2RlbHM6NwUPY2hrTGlzdE1vZGVsczo4BQ9jaGtMaXN0TW9kZWxzOjkFEGNoa0xpc3RNb2RlbHM6MTAFEGNoa0xpc3RNb2RlbHM6MTEFEGNoa0xpc3RNb2RlbHM6MTIFEGNoa0xpc3RNb2RlbHM6MTMFEGNoa0xpc3RNb2RlbHM6MTQFEGNoa0xpc3RNb2RlbHM6MTUFEGNoa0xpc3RNb2RlbHM6MTYFEGNoa0xpc3RNb2RlbHM6MTcFEGNoa0xpc3RNb2RlbHM6MTgFEGNoa0xpc3RNb2RlbHM6MTiv1HbcfG5t7bZrR0bDbZNjcuRqreOEOt+67shp3f4JMw==" />
			<input type="hidden" name="__VIEWSTATEGENERATOR" id="__VIEWSTATEGENERATOR" value="581C180D" />
			<input type="hidden" name="__EVENTVALIDATION" id="__EVENTVALIDATION" value="/wEdABdA/2XoT5GPJi12y2WZG8jXXQ0lSqDtIWz/xIoI1EWqL9dRgMatglN60AjQSQIHb7bWth+YD0dahuhzF78PJm2E4we9YAtIreMw7q6aFOVOvCu+r0RCTjRcCFil3a8XWib0rQBLAOlxyZOePrnpdkTdLo2/ID+dHX362VuJS2cVLIr+JHsjaRiQiwXAHsYVPmHWsuBvJBfndZT6QKzHDUuTwPywoYNPPlW9T8srkEemWLZgj4LuIr/wmu+SzDpio5L9XqI0hZziTbsyV7bt9MnjdeHW2Ov9VORHUKXyAZyjMGPVBDdSb0Ti02apDTD3o/RzhmbeyB6CU1wHzlv8qFkMj5ra3xh5NZeuhIz0rvE6tIimYx9h8OU5lT0VPJQmxAgHarSUb/Ka2FPvgDaXLWbmM28JXxXWapA3CG8X29NfbClWH1IyLvjCC8SaTMtxkOsspFb6K2Hr9DdgLQyRE0hqzfg78Z8BXhXifTCAVkevdww+agXvpdvBbzOlFPZS+ES5Zln02FXRaDx70LS7v54F" />
        <div style="width: 100%; text-align: center; margin: 0px auto;">
            <table border="1" align="center" cellpadding="3" cellspacing="0" bordercolorlight="#335078"  style="width: 99%; font-size: 12px; letter-spacing: 1px; ">
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
                                                                      密&nbsp;&nbsp;&nbsp;&nbsp;码：<input name="AdminPwd" type="password" id="AdminPwd" class="put" />
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
                                    	<input id="chkListModels_0" type="checkbox" name="chkListModels" value="0"/><label for="chkListModels_0">首页FLASH管理</label><input id="chkListModels_1" type="checkbox" name="chkListModels" value="1"/><label for="chkListModels_1">玩家留言管理</label><input id="chkListModels_2" type="checkbox" name="chkListModels" value="2"/><label for="chkListModels_2">网站参数设置</label><input id="chkListModels_3" type="checkbox" name="chkListModels" value="3"/><label for="chkListModels_3">用户管理中心</label><input id="chkListModels_4" type="checkbox" name="chkListModels" value="4"/><label for="chkListModels_4">充值管理中心</label><br>
                                    	<input id="chkListModels_5" type="checkbox" name="chkListModels" value="5"/><label for="chkListModels_5">游戏管理中心</label><input id="chkListModels_6" type="checkbox" name="chkListModels" value="6"/><label for="chkListModels_6">转账管理中心</label><input id="chkListModels_7" type="checkbox" name="chkListModels" value="7"/><label for="chkListModels_7">记录查询中心</label><input id="chkListModels_8" type="checkbox" name="chkListModels" value="8"/><label for="chkListModels_8">新闻管理中心</label><input id="chkListModels_9" type="checkbox" name="chkListModels" value="9"/><label for="chkListModels_9">奖品管理中心</label><br>
                                    	<input id="chkListModels_10" type="checkbox" name="chkListModels" value="10"/><label for="chkListModels_10">系统配置中心</label><input id="chkListModels_11" type="checkbox" name="chkListModels" value="11"/><label for="chkListModels_11">推广系统管理</label><input id="chkListModels_12" type="checkbox" name="chkListModels" value="12"/><label for="chkListModels_12">靓号管理中心</label><input id="chkListModels_13" type="checkbox" name="chkListModels" value="13"/><label for="chkListModels_13">魅力值兑换管理</label><input id="chkListModels_14" type="checkbox" name="chkListModels" value="14"/><label for="chkListModels_14">道具管理中心</label><br>
                                    	<input id="chkListModels_15" type="checkbox" name="chkListModels" value="15"/><label for="chkListModels_15">代理商帐号管理</label><input id="chkListModels_16" type="checkbox" name="chkListModels" value="16"/><label for="chkListModels_16">发送信息管理中心</label><input id="chkListModels_17" type="checkbox" name="chkListModels" value="17"/><label for="chkListModels_17">比赛管理中心</label><input id="chkListModels_18" type="checkbox" name="chkListModels" value="18"/><label for="chkListModels_18">服装管理中心</label><br></span>
                                </div>
                            </div>
                        </div>
                    </td>
                </tr>
                <tr>
                    <td height="25" bgcolor="#DFE8F0" style="border-top: medium none;text-align:left;  border-bottom: medium none;padding-left:50px; ">
                    	<input type="button" name="Button1" value="确定添加" id="Button1" name="B1" class="put" />
                    </td>
                </tr>
                <tr>
                	<td height="25" bgcolor="#DFE8F0" style="border-top: medium none;text-align:left;  border-bottom: medium none;padding-left:50px; ">
                    	<a class="" href="systemSettings/toAlter.do">修改</a>&nbsp;&nbsp;&nbsp;&nbsp;
                    	<a class="" href="systemSettings/toDelete.do">删除</a>
                    </td>
                </tr>
            </table>
        </div>
        </form>
    </div>
</body>
</html>