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
<base href="<%=basePath%>">
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
    <meta name="renderer" content="webkit">
    <title>登录</title>
	
    <link rel="stylesheet" href="css/base.css">
    <link rel="stylesheet" href="css/style.css">
<script src="js/basevalue.js"></script>
<script src="js/jquery-1.11.1.js"></script>
<script language="javascript" type="text/javascript">
	$(function(){//页面载入完毕
		//给登录按钮绑定单击处理
		$("#load").click(function(){
			//获取请求参数
			var name = $("#count").val().trim();
			var password = $("#password").val().trim();
// 			var imgCode = $("#imgCode").val().trim();
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
			}
// 			else if(imgCode == null || imgCode == ""){
// 				ok = false;
// 				alert("验证码不能为空!");
// 				var src = "login/createImg.do?x="+Math.random();
// 				$("#img").attr("src",src)
// 			}
			//发送Ajax请求
			if(ok){
				$.ajax({
					url:"login/login.do",
					type:"post",
					data:{"name":name,"password":password,"system":system},
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

</script>
</head>
<body>
<div class="bg"></div>
    <div class="container">
        <div class="line bouncein">
            <div class="xs6 xm4 xs3-move xm4-move">
                <div style="height:150px;"></div>
                <div class="media media-y margin-big-bottom">
                </div>
                <form name="login" method="post" action="" id="login">
                    <div class="panel loginbox">
                        <div class="text-center margin-big padding-big-top">
                            <h1>后台管理中心</h1>
                        </div>
                        <div class="panel-body" style="padding:30px; padding-bottom:10px; padding-top:10px;">
                            <div class="form-group">
                                <div class="field field-icon-right">
                                    <input type="text" class="input input-big" name="txtUserName" id="count" placeholder="登录账号" />
                                    <span class="icon icon-user margin-small"></span>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="field field-icon-right">
                                    <input type="password" class="input input-big" name="password" id="password"  placeholder="登录密码" />
                                    <span class="icon icon-key margin-small"></span>
                                </div>
                            </div>
<!--                             <div class="form-group"> -->
<!--                                 <div class="field"> -->
<!--                                     <input type="text" class="input input-big" name="txtCode" id="imgCode" placeholder="填写右侧的验证码" /> -->
<!--                                    <a href="javascript:reloadcode()"  > -->
<!--                                     <img id="img" src="login/createImg.do" alt="验证码" onclick="this.setAttribute('src','login/createImg.do?x='+Math.random());" width="100" height="32" class="passcode" style="height:43px;cursor:pointer;"></a> -->
<!--                                 </div> -->
<!--                             </div> -->
                        </div>
                        <div style="padding:30px;">
                            <input type="button" id="load" class="button button-block bg-main text-big input-big" value="登录">
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</body>
</html>
