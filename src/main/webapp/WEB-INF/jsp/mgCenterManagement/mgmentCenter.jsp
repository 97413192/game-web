<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";	
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title></title>
<base href="<%=basePath%>">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<meta name="renderer" content="webkit">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta http-equiv=pragma content=no-cache>
<meta http-equiv=cache-control content=no-cache>
<meta http-equiv=expires content=-1000>
<!-- <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=0.1, maximum-scale=2.0, user-scalable=yes" /> -->
<!-- <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<meta name="msapplication-TileImage" content="/favicon/win-8-icon-152x152.png"> -->
<script>
/* $(window).bind('resize load', function(){

	   $("body").css("zoom", $(window).width() / 640);

	   $("body").css("display" , "block");

	   });  */
</script>
</head>
<frameset border=0 framespacing=0 rows="60,*" frameborder=0>
<frame name=header src="mgmentConter/header.do" frameborder=0 noresize scrolling=no>
<frameset id="full" rows="130,*" framespacing=0 frameborder=0>

<frame name=left src="mgmentConter/left.do" frameborder=0 noresize>
<frame name=main src="mgmentConter/right.do" frameborder=0 noresize scrolling=yes>
<frame name=show src="gamePlayer/getAllRealNamePlayer.do" frameborder=0 noresize scrolling=yes>
</frameset>
<noframes>
</noframes>
</frameset>
