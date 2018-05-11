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
<title>游戏后台代理系统</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=0.5, maximum-scale=2.0, user-scalable=yes" />
<base href="<%=basePath%>">
<style type="text/css">
	   html, body { 
    		height: 100%; margin: 0px; padding: 0px;
    		 background-color:#152753;
    		/* white-space:nowrap; text-overflow:ellipsis; -o-text-overflow:ellipsis; -moz-binding:url('ellipsis.xml#ellipsis'); overflow:hidden;  */
    		 
       }
       #login{
       	height:100%;
       }
       #txt_UserName {
           width:70px; 
		   height:17px;
	   	   color:Black;
	       background-color:#87adbf;
	       font-size:12px;
	       border:solid 1px #153966;
	       font-family:Arial, Helvetica, sans-serif;
	       font-weight:900;
       }
      #txt_PassWord {
      	  width:70px; 
		  height:17px;
		  color:Black;
		  background-color:#87adbf;
		  font-size:12px;
		  border:solid 1px #153966;
		  font-family:Arial, Helvetica, sans-serif;
	 	  font-weight:900;
	  }
      #txt_no {
      	  width:70px; 
		  height:17px;
		  color:Black;
		  background-color:#87adbf;
		  font-size:12px;
		  border:solid 1px #153966;
		  font-family:Arial, Helvetica, sans-serif;
	 	  font-weight:900;
	  }
	  html { overflow-x: hidden; overflow-y: hidden; }
	  .btn{
		background-color:#87adbf;
		border-radius:2px;
	  }
</style>
<script src="js/basevalue.js"></script>
<script src="js/jquery-1.11.1.js"></script>
<script language="javascript" type="text/javascript">
	$(function(){//页面载入完毕
		//给登录按钮绑定单击处理
		$("#load").click(function(){
			//获取请求参数
			var name = $("#count").val().trim();
			var password = $("#password").val().trim();
			var imgCode = $("#imgCode").val().trim();
			var system = detectOS();
			//alert(system);
			//检测参数格式
			var ok = true;//是否通过检测
			if(name == ""){
				ok = false;
				alert("用户名不能为空!");
				var src = "login/createImg.do?x="+Math.random();
				$("#img").attr("src",src)
			}else if(password == null || password == ""){
				ok = false;
				alert("密码不能为空!");
				var src = "login/createImg.do?x="+Math.random();
				$("#img").attr("src",src)
			}else if(imgCode == null || imgCode == ""){
				ok = false;
				alert("验证码不能为空!");
				var src = "login/createImg.do?x="+Math.random();
				$("#img").attr("src",src)
			}
			//发送Ajax请求
			if(ok){
				$.ajax({
					url:"login/login.do",
					type:"post",
					data:{"name":name,"password":password,"imgCode":imgCode,"system":system},
					dataType:"json",
					success:function(result){
						//result就是服务器返回的JSON结果
						if(result.status==0){//成功
							window.location.href="login/managementCenter.do";
						}else if(result.status == 1){//用户名错误
							alert(result.msg);
							var src = "login/createImg.do?x="+Math.random();
							$("#img").attr("src",src);
						}else if(result.status == 2){//密码错误
							alert(result.msg);
							var src = "login/createImg.do?x="+Math.random();
							$("#img").attr("src",src);
						}else if(result.status == 3){
							alert(result.msg);
							var src = "login/createImg.do?x="+Math.random();
							$("#img").attr("src",src);
						}else if(result.status == 4){
							alert(result.msg);
							var src = "login/createImg.do?x="+Math.random();
							$("#img").attr("src",src);
						}else if(result.status == 5){
							alert(result.msg);
							var src = "login/createImg.do?x="+Math.random();
							$("#img").attr("src",src);
						}else if(result.status == -1){
							window.location.href="login/toLogin.do";
						}
					},
					error:function(){
						alert("登录异常");
					}
				});
			}
		});
		//给enter键加事件
		$(document).keydown(function(event){ 
			if(event.keyCode==13){ 
				$("#load").click(); 
			} 
		}); 
		
		//重置按钮
		$("#resert").click(function(){
			//清空输入框中的信息
			$("#count").val("");
			$("#password").val("");
			$("#imgCode").val("");
			var src = "login/createImg.do?x="+Math.random();
			$("#img").attr("src",src);
		});
	});

	/*
	function EnterKey() {
          var t = document.getElementById("txt_no");
          if (t != null || t.value != "") {
              t.value = t.value.toUpperCase();
          }

      }
      function checkrest() {
          document.getElementById("txt_UserName").value = "";
          document.getElementById("txt_PassWord").value = "";
          document.getElementById("txt_no").value = "";
          return false;
      }
      
      function check() {
          var s = document.getElementById("txt_UserName").value;
          var spwd = document.getElementById("txt_PassWord").value;
          var sno = document.getElementById("txt_no").value;

          var fig = true;
          if (s == null || s.length == 0) {
              fig = false;
              alert("请输入用户名！");
              return false;
          }
          if (spwd == null || spwd.length == 0) {
              fig = false;
              alert("请输入密码！");
              return false;
          }
          if (sno == null || sno.length == 0) {
              fig = false;
              alert("请输入验证码！");
              return false;
          }
          return true;
      }
      function reloadcode() {
          var verify = document.getElementById('safecode');
          verify.setAttribute('src', '/Public/Getcode.aspx?' + Math.random());

      }
      function GetCookie(sName) {
          var aCookie = document.cookie.split("; ");
          for (var i = 0; i < aCookie.length; i++) {
              var aCrumb = aCookie[i].split("=");

              if (sName == aCrumb[0])
                  return unescape(aCrumb[1]);
          }
          return null;
      }
      function Mycookic() {
          var s = GetCookie("v1") + "__" + GetCookie("user_lanjm11");
          alert(s);
      }
      */
</script>
</head>
<body>
	<form name="login" method="post" action="" id="login">
		<table width="100%" height="100%" border="0" cellspacing="0" cellpadding="0">
		  <tr bgcolor="#1075b1" height="20%">
		    <td bgcolor="#1075b1">&nbsp;</td>
		  </tr>
		  <tr>
		    <td background="images/login_03.gif">
			    <table width="847" border="0" align="center" cellpadding="0" cellspacing="0">
			      <tr>
			        <td height="318" background="images/login_04.jpg">&nbsp;</td>
			      </tr>
			      <tr>
			        <td height="84">
				        <table width="100%" border="0" cellspacing="0" cellpadding="0">
				          <tr>
				            <td width="381" height="84" background="images/login_06.gif">&nbsp;</td>
				            <td width="162" valign="middle" background="images/login_07.gif">
				            	<table width="100%" border="0" cellspacing="0" cellpadding="0">
								  <tr>
								    <td width="50" style="width:50px; font-size:12px;">用户名:</td>
								    <td width="72"><input name="txtUserName" type="text" id="count" maxlength="20" style="height:18px;width:70px;" /></td>
								    <td width="40">&nbsp;</td>
								  </tr>
								  <tr>
								    <td style="font-size:12px;">密&nbsp;&nbsp;码:</td>
								    <td><input name="txtPassword" type="password" id="password" maxlength="20" style="height:18px;width:70px;" /></td>
								    <td>&nbsp;</td>
								  </tr>
				  
								  <tr>
								    <td style="font-size:12px;">验证码:</td>
								    <td><input name="txtCode" type="text" id="imgCode" maxlength="4" style="height:18px;width:40px;" /></td>
								    <td align="right">  
								    	<a href="javascript:reloadcode()"  >
								        	<img src="login/createImg.do" onclick="this.setAttribute('src','login/createImg.do?x='+Math.random());" alt="验证码" title="点击更换" id="img"/>
								        </a>
								    </td>
								  </tr>
								</table>
				           </td>
			               <td width="26"><img src="images/login_08.gif" width="26" height="84">
			               </td>
				           <td width="67" background="images/login_09.gif">
					            <table width="100%" border="0" cellspacing="0" cellpadding="0">
					              <tr>
					                <td height="25"><div align="center">
					                <input type="button" value="登陆" class="btn" id="load">
					                </div></td>
					              </tr>
					              <tr>
					                <td height="25"><div align="center">
					                  <input type="button" value="重置" class="btn" id="resert">
					                </div></td>
					              </tr>
					            </table>
				           </td>
				           <td width="211" background="images/login_10.gif">&nbsp;&nbsp;</td>
				          </tr>
				        </table>
			        </td>
			      </tr>
			      
			      <tr>
			        <td height="206" background="images/login_11.gif">&nbsp;</td>
			      </tr>
			      <tr bgcolor="#152753"></tr>
			    </table>
		    </td>
		  </tr>
		  <tr bgcolor="#152753" id="id2" height="30%">
		    <td bgcolor="#152753">&nbsp;</td>
		  </tr>
		</table>
	</form>
</body>
</html>
