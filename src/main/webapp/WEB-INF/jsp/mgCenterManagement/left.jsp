<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="viewport"
content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<meta name="renderer" content="webkit">

<title>左边部分网页</title>
<base href="<%=basePath%>">
<link href="css/admin.css" type="text/css" rel="stylesheet" />
<script src="js/jquery.js" type="text/javascript"></script>
<style type="text/css">

* {
    box-sizing: border-box;
}
html {
    font-family: "Lucida Sans", sans-serif;
}
.header {
    background-color: #9933cc;
    color: #ffffff;
    padding: 15px;
}
a:link{
text-decoration:none;
}
.tda {
	list-style-type: none;
	margin: 0;
    padding: 0;
}
a{
	color: white;
	display: inline-block;
	width: 100%;
	text-align: center;
	margin: 1px auto;
	font-size: 16px;
	font-family: 楷体;
/* 	font-weight: bold; */
}

a:hover {
/* 	background: wathet; */
	font-size: 17px;
	font-family:楷体;
	font-weight: bold;
	color:white;
}
.tda {
    padding: 20px;
    margin-bottom: 10px;
/*     background-color :#33b5e5; */
	background-color :#5CACEE;
/*     text-align:center; */
    color: #ffffff;
    border-radius:10px 10px 10px 10px;
    box-shadow: 0 1px 3px rgba(0,0,0,0.12), 0 1px 2px rgba(0,0,0,0.24);
}
  .tda:hover {  
      background-color: #0099cc;  
  } 
/* .menuParent { */
/* 	float: left; */
/* 	text-align: left; */
/* 	display: block; */
/* 	width: 120px; */
/* 	height: 20px; */
/* 	line-height: 20px; */
/* 	overflow: hidden; */
/* } */

/* .menuChild { */
/* 	clear: both; */
/* 	float: left; */
/* 	text-align: left; */
/* 	margin: 0px; */
/* 	padding-top: 5px 0px; */
/* } */
 div#show-leftbar{
  	    position: fixed;
  		background-color:grey;
  		top:50%;
  		left:0;
  		width:15px;
  	}
</style>
</head>
<body style="background-image: url(images/timg1.jpg); ">
	<form name="form1" method="post" action="./left.aspx" id="form1">
		<input type="hidden" name="__VIEWSTATE" id="__VIEWSTATE"
			value="/wEPDwULLTE0MjE4Nzc1MjIPZBYCAgMPZBYCAgEPFgIeB1Zpc2libGVnZGTEMpxSL2ckyCYs7d8ySK18nVvjXSZWNK+KmEWvIv5bUQ==" />
		<input type="hidden" name="__VIEWSTATEGENERATOR"
			id="__VIEWSTATEGENERATOR" value="07F317EC" /> <input type="hidden"
			name="__EVENTVALIDATION" id="__EVENTVALIDATION"
			value="/wEdAAKFaZY/0+Q3QO6TgtK6uoZlaUwjnuxCSOCY1z9BxyqD980Dtln98UOwHmy6uJo71wdu4KxGMwQZ7ufdrdvpEUSg" />
		<input name="menus" type="hidden" id="menus"
			value="01-02-03-04-05-06-08-09-10-11-12-13-14-16-15-17-18-19-20-" />
		<div>
			<table cellspacing="0" cellpadding="0" width="170"
				style="width: 100%;" border="0">
				<tr>
					<td valign="top" align="center">
						<table cellspacing="0" cellpadding="0" width="100%" border="0">
							<tr>
								<td height="10"></td>
							</tr>
						</table>
						 <c:choose>
							<c:when test="${mg.category==1 }">
							<div id="ceshi" style="float: left;">
								<table cellspacing="0" cellpadding="0" width="85" border="0">
									<tr id="tr_xtrz" height="22">
<!-- 									<td> -->
<!-- 										<img src="images/tubiao2.png" width="35">	 -->
<!-- 										</td> -->
										<td >
											<a class="menuParent" onclick="expand(1)"
											href="javascript:void(0);" target="left"> <img src="image/系统设置.png" width="35"><br>系统设置</a>
										</td>
									</tr>
									<tr height="4">
										<td></td>
									</tr>
								</table>
								</div>
								<div>
									<div style="float: left;">
										<div id="child1" style="display: none;">
										<div style="float: left;">
											<table cellspacing="0" cellpadding="0" width="85" border="0">
												<tr id="tr_xtrz" height="22">
<!-- 													<td align="center" width="30"></td> -->
													<td><a href="systemSettings/toAddManager.do"
													target="main"><img src="image/增加管理.png" width="35"><br>增加管理</a></td>
												</tr>
<!-- 												<tr height="4"> -->
<!-- 													<td></td> -->
<!-- 												</tr> -->
											</table>
											</div>
											<div style="float: left;">
											<table cellspacing="0" cellpadding="0" width="85" border="0">
												<tr id="tr_xtrz" height="22">
<!-- 													<td align="center" width="30"><img height="9" -->
<!-- 													src="images/menu_icon.gif" width="9"></td> -->
													<td> <a
													href="systemSettings/selectAllManager.do?index=-1"
													target="main"><img src="image/查询管理.png" width="35"><br>查询管理</a></td>
												</tr>
<!-- 												<tr height="4"> -->
<!-- 													<td></td> -->
<!-- 												</tr> -->
											</table>
											</div>
											
											<div style="float: left;">
											<table cellspacing="0" cellpadding="0" width="85" border="0">
												<tr id="tr_xtrz" height="22">
<!-- 													<td align="center" width="30"><img height="9" -->
<!-- 													src="images/menu_icon.gif" width="9"></td> -->
													<td> <a class="menuChild"
													href="systemSettings/toSystemLog.do?index=-1" target="main"><img src="image/管理日志.png" width="35"><br>管理日志</a></td>
												</tr>
<!-- 												<tr height="4"> -->
<!-- 													<td></td> -->
<!-- 												</tr> -->
											</table>
											</div>
											
												<div style="float: left;">
											<table cellspacing="0" cellpadding="0" width="85" border="0">
												<tr id="tr_xtrz" height="22">
<!-- 													<td align="center" width="30"><img height="9" -->
<!-- 													src="images/menu_icon.gif" width="9"></td> -->
													<td> <a class="menuChild"
													href="systemSettings/toLogSelect.do" target="main"><img src="image/日志查询.png" width="35"><br>日志查询</a></td>
												</tr>
												<tr height="4">
													<td></td>
												</tr>
											</table>
											</div>
											
											<div style="float: left;">
											<table cellspacing="0" cellpadding="0" width="85" border="0">
												<tr id="tr_xtrz" height="22">
<!-- 													<td align="center" width="30"><img height="9" -->
<!-- 													src="images/menu_icon.gif" width="9"></td> -->
													<td> <a class="menuChild"
													href="systemSettings/toClearLog.do" target="main"><img src="image/清理日志.png" width="35"><br>清理日志</a></td>
												</tr>
												<tr height="4">
													<td></td>
												</tr>
											</table>
											</div>
											
											<div style="float: left;">
											<table cellspacing="0" cellpadding="0" width="85" border="0">
												<tr id="tr_xtrz" height="22">
<!-- 													<td align="center" width="30"><img height="9" -->
<!-- 													src="images/menu_icon.gif" width="9"></td> -->
													<td> <a class="menuChild"
													href="login/managementCenter.do" target="_parent"><img src="image/清理日志.png" width="35"><br>返回首页</a></td>
												</tr>
												<tr height="4">
													<td></td>
												</tr>
											</table>
											</div>
											
										</div>
												<!-- 
		                        <tr>
		                            <td align="center" width="30">
		                                <img height="9" src="images/menu_icon.gif" width="9">
		                            </td>
		                            <td>
		                                <div style="color: Blue; width: 140; height: 20px; z-index: 1000; display: none;
		                                    position: absolute; top: 14%; left: 9%; background-color: #e3effb; border: solid 1px yellow"
		                                    id="div1">
		                                </div>
		                                <a class="menuChild" href="systemSettings/toAdvancedLogSelect.do"
		                                    target="main">日志高级查询</a>
		                            </td>
		                        </tr>
		                         -->
												<!-- 									<tr> -->
									</div>
								</div>
							</c:when>
							<c:otherwise>
							</c:otherwise>
						</c:choose> 
						<c:choose>
							<c:when test="${mg.category==1 }">
							<div id="ceshi1" style="float: left;">
								<table cellspacing="0" cellpadding="0" width="85" border="0">
									<tr id="tr_xtrz" height="22">
<!-- 									<td> -->
<!-- 										<img src="images/tubiao1.png" width="35"> -->
<!-- 										</td> -->
										<td>
											<a class="menuParent" onclick="expand(9)"
											href="javascript:void(0);"><img src="image/移动联盟.png" width="35"><br>联盟超端</a>
										</td>
									</tr>
									<tr height="4">
										<td></td>
									</tr>
								</table>
								</div>
								<div style="float: left;">
									<div id="child9" style="display: none;">
										<div style="float: left;">
											<table cellspacing="0" cellpadding="0" width="85" border="0">
												<tr id="menu_a1">
<!-- 													<td align="center" width="30"><img height="9" -->
<!-- 														src="images/menu_icon.gif" width="9"></td> -->
													<td><a class="menuChild"
														href="information/queryPlayerPage.do" target="main"><img src="image/ID查询.png" width="35"><br>ID查询</a>
													</td>
												</tr>
												<tr height="4">
													<td colspan="2"></td>
												</tr>
											</table>
										</div>
										
										<div style="float: left;">
											<table cellspacing="0" cellpadding="0" width="85" border="0">
												<tr id="menu_a1">
<!-- 													<td align="center" width="30"><img height="9" -->
<!-- 														src="images/menu_icon.gif" width="9"></td> -->
													<td><a class="menuChild"
														href="login/managementCenter.do" target="_parent"><img src="image/ID查询.png" width="35"><br>返回首页</a>
													</td>
												</tr>
												<tr height="4">
													<td colspan="2"></td>
												</tr>
											</table>
										</div>
									</div>
								</div>
							</c:when>
							<c:otherwise>
							</c:otherwise>
						</c:choose>
						
						
						<div id="ceshi2" style="float: left;">
						<table cellspacing="0" cellpadding="0" width="85" border="0">
							<tr height="22">
<!-- 							<td> -->
<!-- 									<img src="images/tubiao3.png" width="35"> -->
<!-- 								</td> -->
								<td>
									<a class="menuParent" onclick="expand(2)"
									href="javascript:void(0);"><img src="image/用户管理.png" width="35"><br>用户管理 </a>
								</td>
							</tr>
							<tr height="4">
								<td></td>
							</tr>
						</table>
						</div>
						<div style="float: left;">
							<div id="child2" style="display: none;">
								<div style="float: left;">
									<table cellspacing="0" cellpadding="0" width="85" border="0">
										<tr id="menu_a1">
<!-- 											<td align="center" width="30"><img height="9" -->
<!-- 												src="images/menu_icon.gif" width="9"></td> -->
											<td><a class="menuChild"
												href="gamePlayer/toSelectPlayer.do" target="main"><img src="image/单个玩家.png" width="35"><br>单个玩家</a>
											</td>
										</tr>
										<tr height="4">
											<td colspan="2"></td>
										</tr>
									</table>
								</div>
								
								<c:if test="${power == 1 }">
								<div style="float: left;">
									<table cellspacing="0" cellpadding="0" width="85" border="0">
										<tr id="menu_a1">
<!-- 											<td align="center" width="30"><img height="9" -->
<!-- 												src="images/menu_icon.gif" width="9"></td> -->
											<td><a class="menuChild"
										href="gamePlayer/selectAllPlayer.do?index=1" target="main"><img src="image/所有玩家.png" width="35"><br>所有玩家</a>
											</td>
										</tr>
										<tr height="4">
											<td colspan="2"></td>
										</tr>
									</table>
								</div>
								
								<div style="float: left;">
									<table cellspacing="0" cellpadding="0" width="85" border="0">
										<tr id="menu_a1">
<!-- 											<td align="center" width="30"><img height="9" -->
<!-- 												src="images/menu_icon.gif" width="9"></td> -->
											<td><a class="menuChild"
										href="gamePlayer/toSelectPlayerStartRoomCard.do" target="main"><img src="image/修改房卡.png" width="35"><br>修改房卡</a>
											</td>
										</tr>
										<tr height="4">
											<td colspan="2"></td>
										</tr>
									</table>
								</div>
								</c:if>
								
								<div style="float: left;">
									<table cellspacing="0" cellpadding="0" width="85" border="0">
										<tr id="menu_a1">
<!-- 											<td align="center" width="30"><img height="9" -->
<!-- 												src="images/menu_icon.gif" width="9"></td> -->
											<td><a class="menuChild"
												href="gamePlayer/toSelectBindedPlayers.do?index=1"
												target="main"><img src="image/绑定玩家.png" width="35"><br>绑定玩家</a></td>
										</tr>
										<tr height="4">
											<td colspan="2"></td>
										</tr>
									</table>
								</div>
								
								<div style="float: left;">
									<table cellspacing="0" cellpadding="0" width="85" border="0">
										<tr id="menu_a1">
<!-- 											<td align="center" width="30"><img height="9" -->
<!-- 												src="images/menu_icon.gif" width="9"></td> -->
											<td><a class="menuChild"
												href="gamePlayer/toSelectRecodByCondition.do?index=-1"
												target="main"><img src="image/玩家记录.png" width="35"><br>玩家记录</a>
											</td>
										</tr>
										<tr height="4">
											<td colspan="2"></td>
										</tr>
									</table>
								</div>
								
								<div style="float: left;">
									<table cellspacing="0" cellpadding="0" width="85" border="0">
										<tr id="menu_a1">
<!-- 											<td align="center" width="30"><img height="9" -->
<!-- 												src="images/menu_icon.gif" width="9"></td> -->
											<td><a class="menuChild"
												href="javascript:void(0)"
												onclick="delMenu()"><img src="image/实名认证.png" width="35"><br>实名认证</a>
											</td>
										</tr>
										<tr height="4">
											<td colspan="2"></td>
										</tr>
									</table>
								</div>
								
								<!--  ---------------------------------------------------------------------------------- -->
								
								<c:choose>
									<c:when test="${showUnbind.size() > 0 }">
									<div id="ceshi1" style="float: left;">
										<table cellspacing="0" cellpadding="0" width="85" border="0">
											<tr id="tr_xtrz" height="22">
												<td>
													<%-- <a class="menuChild" id="unbind" account="${agent.account}" onclick="showUnbind(this)" href="javascript:void(0)"> --%>
													<a class="menuChild" id="unbind" account="${agent.account}" href="gamePlayer/getAllBindPlayers.do?index=1&account=${agent.account}" target="main">
													<img src="image/解除绑定.png" width="35"><br>解除绑定</a>
												</td>
											</tr>
											<tr height="4">
												<td></td>
											</tr>
										</table>
										</div>
										
									</c:when>
									<c:otherwise>
									</c:otherwise>
								</c:choose>
								
								
								<div style="float: left;">
									<table cellspacing="0" cellpadding="0" width="85" border="0">
										<tr id="menu_a1">
<!-- 											<td align="center" width="30"><img height="9" -->
<!-- 												src="images/menu_icon.gif" width="9"></td> -->
											<td><a class="menuChild"
												href="login/managementCenter.do" target="_parent"><img src="image/玩家记录.png" width="35"><br>返回首页</a>
											</td>
										</tr>
										<tr height="4">
											<td colspan="2"></td>
										</tr>
									</table>
								</div>
								
							</div>
							
						</div>
						 <c:choose>
							<c:when test="${mg.mark == -1 }">
							<div id="ceshi3" style="float: left;">
								<table id="menu_04" cellspacing="0" cellpadding="0" width="85"
									border="0">
									<tr height="22">
<!-- 									<td> -->
<!-- 											<img src="images/tubiao4.png" width="35"> -->
<!-- 										</td> -->
										<td>
											<a class="menuParent" onclick="expand(3)"
											href="javascript:void(0);"><img src="image/消息房卡.png" width="35"><br>消息房卡 </a>
										</td>
									</tr>
									<tr height="4">
										<td></td>
									</tr>
								</table>
								</div>

								<div style="float: left;">
									<div id="child3" style="display: none;">
										<div style="float: left;">
											<table cellspacing="0" cellpadding="0" width="85" border="0">
												<tr id="menu_a1">
<!-- 													<td align="center" width="30"><img height="9" -->
<!-- 														src="images/menu_icon.gif" width="9"></td> -->
													<td><a class="menuChild"
														href="information/toAddInformation.do" target="main"><img src="image/新增消息.png" width="35"><br>新增消息</a>
													</td>
												</tr>
												<tr height="4">
													<td colspan="2"></td>
												</tr>
											</table>
										</div>

										<div style="float: left;">
											<table cellspacing="0" cellpadding="0" width="85" border="0">
												<tr id="menu_a1">
<!-- 													<td align="center" width="30"><img height="9" -->
<!-- 														src="images/menu_icon.gif" width="9"></td> -->
													<td><a class="menuChild"
														href="information/toSelectAllCategoryInformation.do?index=-1"
														target="main"><img src="image/跑马灯公告.png" width="35"><br>跑马灯公告</a></td>
												</tr>
												<tr height="4">
													<td colspan="2"></td>
												</tr>
											</table>
										</div>
										
										<div style="float: left;">
											<table cellspacing="0" cellpadding="0" width="85" border="0">
												<tr id="menu_a1">
<!-- 													<td align="center" width="30"><img height="9" -->
<!-- 														src="images/menu_icon.gif" width="9"></td> -->
													<td><a class="menuChild"
														href="information/toSelectAllInformation.do?index=-1"
														target="main"><img src="image/所有消息.png" width="35"><br>所有消息</a></td>
												</tr>
												<tr height="4">
													<td colspan="2"></td>
												</tr>
											</table>
										</div>

										<div style="float: left;">
											<table cellspacing="0" cellpadding="0" width="85" border="0">
												<tr>
<!-- 													<td align="center" width="30"><img height="9" -->
<!-- 														src="images/menu_icon.gif" width="9"></td> -->
													<td><a class="menuChild"
														href="information/toSelectShareConfig.do" target="main"><img src="image/分享配置.png" width="35"><br>分享配置</a></td>
												</tr>
												<tr height="4">
													<td colspan="2"></td>
												</tr>
											</table>
										</div>

										<div style="float: left;">
											<table cellspacing="0" cellpadding="0" width="85" border="0">
												<tr>
<!-- 													<td align="center" width="30"><img height="9" -->
<!-- 														src="images/menu_icon.gif" width="9"></td> -->
													<td><a class="menuChild"
														href="information/toSelectBindConfig.do" target="main"><img src="image/绑定配置.png" width="35"><br>绑定配置</a></td>
												</tr>
												<tr height="4">
													<td colspan="2"></td>
												</tr>
											</table>
										</div>
										
										<div style="float: left;">
											<table cellspacing="0" cellpadding="0" width="85" border="0">
												<tr>
													<td><a
														href="login/managementCenter.do" target="_parent"><img src="image/绑定配置.png" width="35"><br>返回首页</a></td>
												</tr>
												<tr height="4">
													<td colspan="2"></td>
												</tr>
											</table>
										</div>

									</div>
								</div>

							</c:when>
							<c:otherwise>
							</c:otherwise>
						</c:choose> <!-- 
                    <table id="menu_05" cellspacing="0" cellpadding="0" width="150" border="0" >
						<tr height="22">
							<td style="padding-left: 30px" background="images/menu_bt.jpg">
					        	<a class="menuParent" onclick="expand(4)" href="javascript:void(0);">游戏房间管理中心 </a>
					        </td>
						</tr>
						<tr height="4">
							<td></td>
						</tr>
					</table>
                    <table id="child4" style="display: none" cellspacing="0" cellpadding="0" width="150" border="0">
                        <tr height="20">
                            <td align="center" width="30">
                                <img height="9" src="images/menu_icon.gif" width="9">
                            </td>
                            <td>
                                <a class="menuChild" href="gameRoom/toAddGameRoom.do"
                                    target="main">新增游戏房间</a>
                            </td>
                        </tr>
                        <tr height="20">
                            <td align="center" width="30">
                                <img height="9" src="images/menu_icon.gif" width="9">
                            </td>
                            <td>
                                <a class="menuChild" href="gameRoom/toAlterGameRoom.do"
                                    target="main">修改游戏房间参数</a>
                            </td>
                        </tr>
                        <tr height="20">
                            <td align="center" width="30">
                                <img height="9" src="images/menu_icon.gif" width="9">
                            </td>
                            <td>
                                <a class="menuChild" href="gameRoom/toSelectGameRoom.do"
                                    target="main">查询游戏房间信息</a>
                            </td>
                        </tr>
                        <tr height="20">
                            <td align="center" width="30">
                                <img height="9" src="images/menu_icon.gif" width="9">
                            </td>
                            <td>
                                <a class="menuChild" href="gameRoom/toSelectAllGameRoom.do?roomIndex=-1"
                                    target="main">查询所有游戏房间信息</a>
                            </td>
                        </tr>
                        <tr height="4">
                            <td colspan="2">
                            </td>
                        </tr>
                    </table>
                    
                    <table id="menu_06" cellspacing="0" cellpadding="0" width="150" border="0" >
						<tr height="22">
							<td style="padding-left: 30px" background="images/menu_bt.jpg">
					        	<a class="menuParent" onclick="expand(5)" href="javascript:void(0);">游戏桌子管理中心 </a>
					        </td>
						</tr>
						<tr height="4">
							<td></td>
						</tr>
					</table>

                    <table id="child5" style="display: none" cellspacing="0" cellpadding="0" width="150" border="0">
                        <tr height="20">
                            <td align="center" width="30">
                                <img height="9" src="images/menu_icon.gif" width="9">
                            </td>
                            <td>
                                <a class="menuChild" href="gameDesk/toAddDesk.do"
                                    target="main">新增游戏桌子</a>
                            </td>
                        </tr>
                        <tr height="20">
                            <td align="center" width="30">
                                <img height="9" src="images/menu_icon.gif" width="9">
                            </td>
                            <td>
                                <a class="menuChild" href="gameDesk/selectAllDesk.do"
                                    target="main">查询所有桌子</a>
                            </td>
                        </tr>
                        <tr height="20">
                            <td align="center" width="30">
                                <img height="9" src="images/menu_icon.gif" width="9">
                            </td>
                            <td>
                                <a class="menuChild" href="gameDesk/toSelectDesk.do"
                                    target="main">查询游戏桌子参数</a>
                            </td>
                        </tr>
                        <tr height="4">
                            <td colspan="2">
                            </td>
                        </tr>
                    </table>
                    <table id="menu_07" cellspacing="0" cellpadding="0" width="150" border="0" >
						<tr height="22">
							<td style="padding-left: 30px" background="images/menu_bt.jpg">
					        	<a class="menuParent" onclick="expand(6)" href="javascript:void(0);">房间留桌设置中心 </a>
					        </td>
						</tr>
						<tr height="4">
							<td></td>
						</tr>
					</table>

                    <table id="child6" style="display: none" cellspacing="0" cellpadding="0" width="150" border="0">
                        <tr height="20">
                            <td align="center" width="30">
                                <img height="9" src="images/menu_icon.gif" width="9">
                            </td>
                            <td>
                                <a class="menuChild" href="waitDesk/toAddWaitDesk.do"
                                    target="main">新增留桌配置</a>
                            </td>
                        </tr>
                        <tr height="20">
                            <td align="center" width="30">
                                <img height="9" src="images/menu_icon.gif" width="9">
                            </td>
                            <td>
                                <a class="menuChild" href="waitDesk/selectAllWaitDesk.do"
                                    target="main">查询所有留桌配置</a>
                            </td>
                        </tr>
                        <tr height="4">
                            <td colspan="2">
                            </td>
                        </tr>
                    </table>
                    <table id="menu_08" cellspacing="0" cellpadding="0" width="150" border="0" >
						<tr height="22">
							<td style="padding-left: 30px" background="images/menu_bt.jpg">
					        	<a class="menuParent" onclick="expand(7)" href="javascript:void(0);">比倍管理 </a>
					        </td>
						</tr>
						<tr height="4">
							<td></td>
						</tr>
					</table>

                    <table id="child7" style="display: none" cellspacing="0" cellpadding="0" width="150" border="0">
                        <tr height="20">
                            <td align="center" width="30">
                                <img height="9" src="images/menu_icon.gif" width="9">
                            </td>
                            <td>
                                <a class="menuChild" href="double/selectAllDouble.do"
                                    target="main">查询所有比倍</a>
                            </td>
                        </tr>
                        <tr height="4">
                            <td colspan="2">
                            </td>
                        </tr>
                    </table>
                    <table id="menu_09" cellspacing="0" cellpadding="0" width="150" border="0" >
						<tr height="22">
							<td style="padding-left: 30px" background="images/menu_bt.jpg">
					        	<a class="menuParent" onclick="expand(8)" href="javascript:void(0);">签到管理 </a>
					        </td>
						</tr>
						<tr height="4">
							<td></td>
						</tr>
					</table>

                    <table id="child8" style="display: none" cellspacing="0" cellpadding="0" width="150" border="0">
                        <tr height="20">
                            <td align="center" width="30">
                                <img height="9" src="images/menu_icon.gif" width="9">
                            </td>
                            <td>
                                <a class="menuChild" href="double/selectAllDouble.do"
                                    target="main">查询所有签到</a>
                            </td>
                        </tr>
                        <tr height="4">
                            <td colspan="2">
                            </td>
                        </tr>
                    </table>
                    <table id="menu_10" cellspacing="0" cellpadding="0" width="150" border="0" >
						<tr height="22">
							<td style="padding-left: 30px" background="images/menu_bt.jpg">
					        	<a class="menuParent" onclick="expand(9)" href="javascript:void(0);">登陆奖励管理 </a>
					        </td>
						</tr>
						<tr height="4">
							<td></td>
						</tr>
					</table>

                    <table id="child9" style="display: none" cellspacing="0" cellpadding="0" width="150" border="0">
                        <tr height="20">
                            <td align="center" width="30">
                                <img height="9" src="images/menu_icon.gif" width="9">
                            </td>
                            <td>
                                <a class="menuChild" href="landingWard/selectAllLandingWard.do"
                                    target="main">查询所有登陆奖励表</a>
                            </td>
                        </tr>
                        <tr height="4">
                            <td colspan="2">
                            </td>
                        </tr>
                    </table>
                    <table id="menu_11" cellspacing="0" cellpadding="0" width="150" border="0" >
						<tr height="22">
							<td style="padding-left: 30px" background="images/menu_bt.jpg">
					        	<a class="menuParent" onclick="expand(10)" href="javascript:void(0);">活动奖励管理 </a>
					        </td>
						</tr>
						<tr height="4">
							<td></td>
						</tr>
					</table>

                    <table id="child10" style="display: none" cellspacing="0" cellpadding="0" width="150" border="0">
                        <tr height="20">
                            <td align="center" width="30">
                                <img height="9" src="images/menu_icon.gif" width="9">
                            </td>
                            <td>
                                <a class="menuChild" href="actionWard/selectAllActionWard.do"
                                    target="main">查询所有活动奖励表</a>
                            </td>
                        </tr>
                        <tr height="4">
                            <td colspan="2">
                            </td>
                        </tr>
                    </table>
                     --> <!-- 
                    <table id="menu_12" cellspacing="0" cellpadding="0" width="150" border="0" >
						<tr height="22">
							<td style="padding-left: 30px" background="images/menu_bt.jpg">
					        	<a class="menuParent" onclick="expand(11)" href="javascript:void(0);">充值管理 </a>
					        </td>
						</tr>
						<tr height="4">
							<td></td>
						</tr>
					</table>
 					-->
<!-- 						<table id="child11" style="display: none" cellspacing="0" -->
<!-- 							cellpadding="0" width="150" border="0"> -->
<!-- 							<tr height="20"> -->
<!-- 								<td align="center" width="30"><img height="9" -->
<!-- 									src="images/menu_icon.gif" width="9"></td> -->
<!-- 								<td><a class="menuChild" -->
<!-- 									href="recharge/toSelectManyRechargeRecord.do?index=-1&category=0" -->
<!-- 									target="main">查询玩家交易记录</a></td> -->
<!-- 							</tr> -->


							<!-- 
                        <tr height="20">
                            <td align="center" width="30">
                                <img height="9" src="images/menu_icon.gif" width="9">
                            </td>
                            <td>
                                <a class="menuChild" href="recharge/selectConverScale.do"
                                    target="main">查询钻石兑换金币比例</a>
                            </td>
                        </tr>
                        <tr height="20">
                            <td align="center" width="30">
                                <img height="9" src="images/menu_icon.gif" width="9">
                            </td>
                            <td>
                                <a class="menuChild" href="recharge/selectAllLevel.do"
                                    target="main">查看所有充值档次</a>
                            </td>
                        </tr>
                        <tr height="20">
                            <td align="center" width="30">
                                <img height="9" src="images/menu_icon.gif" width="9">
                            </td>
                            <td>
                                <a class="menuChild" href="recharge/toAddLevel.do"
                                    target="main">增加充值档次</a>
                            </td>
                        </tr>
                         -->
							<tr height="4">
								<td colspan="2"></td>
							</tr>
						</table> <!--  <table id="menu_13" cellspacing="0" cellpadding="0" width="150" border="0" >
						<tr height="22">
							<td style="padding-left: 30px" background="images/menu_bt.jpg">
					        	<a class="menuParent" onclick="expand(12)" href="javascript:void(0);">奖励管理</a>
					        </td>
						</tr>
						<tr height="4">
							<td></td>
						</tr>
					</table>

                    <table id="child12" style="display: none" cellspacing="0" cellpadding="0" width="150" border="0">
                        <tr height="20">
                            <td align="center" width="30">
                                <img height="9" src="images/menu_icon.gif" width="9">
                            </td>
                            <td>
                                <a class="menuChild" href="actionWard/selectShareWard.do?index=-1" target="main">分享奖励管理</a>
                            </td>
                        </tr>
                        <tr height="4">
                            <td colspan="2">
                            </td>
                        </tr>
                    </table> -->

						<div id="ceshi4" style="float: left">
						<table id="menu_14" cellspacing="0" cellpadding="0" width="85"
							border="0">
							<tr height="22">
<!-- 							<td> -->
<!-- 									<img src="images/tubiao5.png" width="35"> -->
<!-- 								</td> -->
								<td>
									<a class="menuParent" onclick="expand(13)"
									href="javascript:void(0);"><img src="image/推广管理.png" width="35"><br>推广管理 </a>
								</td>
							</tr>
							<tr height="4">
								<td></td>
							</tr>
						</table>
						</div>
						
						<div style="float: left;">
									<div id="child13" style="display: none;">
									<c:if test="${mg.category==1}">
										<div style="float: left;">
										<table cellspacing="0" cellpadding="0" width="85" border="0">
											<tr id="menu_a1">
												<td><a class="menuChild"
													href="agent/toAgentEarn.do?index=1" target="main"><img src="image/代理充值.png" width="35"><br>返水查询</a></td>
											</tr>
											<tr height="4">
												<td colspan="2"></td>
											</tr>
										</table>
									</div>
									</c:if>	
								<c:if test="${agentPower != 2 }">
									<div style="float: left;">
										<table cellspacing="0" cellpadding="0" width="85" border="0">
											<tr id="menu_a1">
<!-- 												<td align="center" width="30"><img height="9" -->
<!-- 													src="images/menu_icon.gif" width="9"></td> -->
												<td><a class="menuChild"
													href="agent/selectAengtPage.do" target="main"><img src="image/代理充值.png" width="35"><br>代理充值</a></td>
											</tr>
											<tr height="4">
												<td colspan="2"></td>
											</tr>
										</table>
									</div>

									<div style="float: left;">
										<table cellspacing="0" cellpadding="0" width="85" border="0">
											<tr id="menu_a1">
<!-- 												<td align="center" width="30"><img height="9" -->
<!-- 													src="images/menu_icon.gif" width="9"></td> -->
												<td><a class="menuChild"
													href="agent//toAddAgent.do" target="main"><img src="image/增加管理.png" width="35"><br>增加代理</a></td>
											</tr>
											<tr height="4">
												<td colspan="2"></td>
											</tr>
										</table>
									</div>
								</c:if>

								<div style="float: left;">
									<table cellspacing="0" cellpadding="0" width="85" border="0">
										<tr id="menu_a1">
<!-- 											<td align="center" width="30"><img height="9" -->
<!-- 												src="images/menu_icon.gif" width="9"></td> -->
											<td><a class="menuChild"
												href="agent/findAll.do?index=-1" target="main"><img src="image/所有代理.png" width="35"><br>所有代理</a></td>
										</tr>
										<tr height="4">
											<td colspan="2"></td>
										</tr>
									</table>
								</div>

								<div style="float: left;">
									<table cellspacing="0" cellpadding="0" width="85" border="0">
										<tr id="menu_a1">
<!-- 											<td align="center" width="30"><img height="9" -->
<!-- 												src="images/menu_icon.gif" width="9"></td> -->
											<td><a class="menuChild"
												href="agentDeal/findAll.do?index=-1" target="main"><img src="image/交易记录.png" width="35"><br>交易记录</a></td>
										</tr>
										<tr height="4">
											<td colspan="2"></td>
										</tr>
									</table>
								</div>

								<div style="float: left;">
									<table cellspacing="0" cellpadding="0" width="85" border="0">
										<tr id="menu_a1">
<!-- 											<td align="center" width="30"><img height="9" -->
<!-- 												src="images/menu_icon.gif" width="9"></td> -->
											<td><a class="menuChild"
												href="agentDeal/toAgentDealSelect.do" target="main"><img src="image/交易查询.png" width="35"><br>交易查询</a></td>
										</tr>
										<tr height="4">
											<td colspan="2"></td>
										</tr>
									</table>
								</div>
								
								<c:if test="${power == 1 }">
								<div style="float: left;">
									<table cellspacing="0" cellpadding="0" width="85" border="0">
										<tr id="menu_a1">
<!-- 											<td align="center" width="30"><img height="9" -->
<!-- 												src="images/menu_icon.gif" width="9"></td> -->
											<td><a class="menuChild" href="agent/toUpdateRebate.do"
										target="main"><img src="image/代理返点.png" width="35"><br>代理返点</a></td>
										</tr>
										<tr height="4">
											<td colspan="2"></td>
										</tr>
									</table>
								</div>
								
<!-- 								<div style="float: left;"> -->
<!-- 									<table cellspacing="0" cellpadding="0" width="85" border="0"> -->
<!-- 										<tr id="menu_a1"> -->
<!-- 											<td align="center" width="30"><img height="9" -->
<!-- 												src="images/menu_icon.gif" width="9"></td> --> 
<!-- 											<td><a class="menuChild" href="agent/toAgentDis.do" -->
<!-- 										target="main"><img src="image/代理返点.png" width="35"><br>返点解散</a></td> -->
<!-- 										</tr> -->
<!-- 										<tr height="4"> -->
<!-- 											<td colspan="2"></td> -->
<!-- 										</tr> -->
<!-- 									</table> -->
<!-- 								</div> -->
								</c:if>
								
								<div style="float: left;">
									<table cellspacing="0" cellpadding="0" width="85" border="0">
										<tr id="menu_a1">
<!-- 											<td align="center" width="30"><img height="9" -->
<!-- 												src="images/menu_icon.gif" width="9"></td> -->
											<td><a class="menuChild" href="login/managementCenter.do"
										target="_parent"><img src="image/代理返点.png" width="35"><br>返回首页</a></td>
										</tr>
										<tr height="4">
											<td colspan="2"></td>
										</tr>
									</table>
								</div>

							</div>
								</div>
					</td>
				</tr>
			</table>
		</div>
		<script type="text/javascript" language="javascript">
			$().ready(function() {
				var menus = $('#menus').val();
				if (menus != null && menus.length > 0) {

					var array = menus.split("-");
					var obj = null;
					if (array != null && array.length > 0) {
						for (var i = 0; i < array.length; i++) {
							if ($("#menu_" + array[i]) != null) {
								$("#menu_" + array[i]).show();
							}
						}
					}
				}
			});
			 function expand(el){
			      childObj = document.getElementById("child" + el);
			      childObj1 = document.getElementById("ceshi");
			      childObj2 = document.getElementById("ceshi1");
			      childObj3 = document.getElementById("ceshi2");
			      childObj4 = document.getElementById("ceshi3");
			      childObj5 = document.getElementById("ceshi4");

			      if (childObj.style.display == 'none') {
			          childObj.style.display = 'block';
			          childObj1.style.display='none';
			          childObj2.style.display='none';
			          childObj3.style.display='none';
			          childObj4.style.display='none';
			          childObj5.style.display='none';
			      }
			      else {
			          childObj.style.display = 'none';
			      }
			      return;
			  }
			 //点击实名认证后删除本页
			 function delMenu(){
				 $("#child2").parent().remove();
				 //判断是否删除成功
				 if($("#child2").parent().length == 0){
					 //请求实名认证相关菜单列表
					 location.href = "gamePlayer/geteRealNameMenuList.do"
				 }
				 
			 }
			 
			 function showUnbind(obj){
				 //$("#child2").parent().remove();
				 //判断是否删除成功
				 //if($("#child2").parent().length == 0){
					 var account =  $(obj).attr('account');
					
					 if(account != "" && account != 'undefined'){
						//请求实名认证相关菜单列表
						 location.href = "gamePlayer/getAllBindPlayers.do?index=1&account="+account;
					 }
					 
				// }
			 }
			 

			$(function() {
				$(".menuChild").click(function() {
					$(".menuChild").each(function() {
						$(this).css('color', "#292929");
					});
					$(this).css('color', "#4876FF");
				});
			});

			function changeMenu(ID) {

			}
		</script>
	</form>
</body>
</html>