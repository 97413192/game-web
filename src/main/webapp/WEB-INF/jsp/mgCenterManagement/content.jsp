<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<meta name="renderer" content="webkit">
<title>无标题网页</title>
<base href="<%=basePath%>">
<link rel="stylesheet" href="css/base.css">
<link rel="stylesheet" href="css/style.css">
<!-- <link href="css/logContent_style.css" rel="stylesheet" type="text/css" /> -->
<script src="js/jquery.js" type="text/javascript"></script>
</head>
<style>
/* div#hide-leftbar { */
/* 	position: fixed; */
/* 	background-color: grey; */
/* 	top: 50%; */
/* 	left: 0; */
/* 	width: 15px; */
/* } */
</style>
<body>
<!-- 	<div id="hide-leftbar"> -->
<!-- 		<span style="cursor: pointer;">隐藏左面板</span> -->
<!-- 	</div> -->
	<form name="form1" method="post" action="./SystemStatEveryDay.aspx"
		id="form1">
		<%-- 
		<table width="100%" border="1" align="center" cellpadding="3" cellspacing="0" bordercolor="#A4B6D7" bgcolor="F2F8FF" class="admin_table2">
			<tr>
				<td class="title_03" align="left">
					&nbsp;&nbsp;&nbsp;&nbsp;今天统计&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<strong> 统计日期：</strong><input name="txtDate" type="text" id="txtDate"/>
					<input type="submit" name="btnStat" value="统计" id="btnStat" class="put" />
				</td>
			</tr>
		</table>
		<table width="100%" border="0" cellspacing="0" cellpadding="0" style="background-color: White">
			<tr>
				<td style="background-color: White; height: 5px">
				</td>
			</tr>
		</table>
		
				<table width="100%" border="1" align="center" cellpadding="3" cellspacing="0" bordercolor="A4B6D7" bgcolor="F2F8FF" class="admin_table">
					<tr>
						<td colspan="2" class="title_03" align="center">
							<strong>游戏记录统计［2016-10-28 12:05:26］</strong></td>
					</tr>
					<tr>
						<td align="right" width="40%">
							当天所玩局数&nbsp;&nbsp;&nbsp;
							<td width="60%" align="left">
								&nbsp;&nbsp;0局
							</td>
					</tr>
					<tr>
						<td align="right" width="34%">
							当天游戏时间总数&nbsp;&nbsp;&nbsp;
							<td align="left">
								&nbsp;&nbsp;0秒
							</td>
					</tr>
					<tr>
						<td align="right" width="34%">
							当天平均每局时间&nbsp;&nbsp;&nbsp;
							<td align="left">
								&nbsp;&nbsp;0秒
							</td>
					</tr>
					<tr>
						<td align="right" width="34%">
							当天所交税总数&nbsp;&nbsp;&nbsp;
							<td align="left">
								&nbsp;&nbsp;0金币
							</td>
					</tr>
					<tr>
						<td align="right" width="34%">
							当天平均每局交税数&nbsp;&nbsp;&nbsp;</td>
						<td align="left">
							&nbsp;&nbsp;0金币
						</td>
					</tr>
					<tr>
						<td colspan="2" class="title_03" align="center">
							<strong>划账记录每日统计［2016-10-28 12:05:26］</strong></td>
					</tr>
					<tr>
						<td align="right" width="34%">
							划账总数&nbsp;&nbsp;&nbsp;</td>
						<td align="left">
							&nbsp;&nbsp;0笔
						</td>
					</tr>
					<tr>
						<td align="right" width="34%">
							手续费总数&nbsp;&nbsp;&nbsp;</td>
						<td align="left">
							&nbsp;&nbsp;0金币
						</td>
					</tr>
					<tr>
						<td align="right" width="34%">
							最高划账手续费&nbsp;&nbsp;&nbsp;</td>
						<td align="left">
							&nbsp;&nbsp;0金币
						</td>
					</tr>
					<tr>
						<td align="right" width="34%">
							最低划账手续费&nbsp;&nbsp;&nbsp;</td>
						<td align="left">
							&nbsp;&nbsp;0金币
						</td>
					</tr>
					<tr>
						<td colspan="2" class="title_03" align="center">
							<strong>今日用户注册统计［2016-10-28 12:05:26］</strong></td>
					</tr>
					<tr>
						<td align="right" width="34%">
							当天注册人数&nbsp;&nbsp;&nbsp;</td>
						<td align="left">
							&nbsp;&nbsp;0人
						</td>
					</tr>
					<tr>
						<td align="right" width="34%">
							当天被推广人数&nbsp;&nbsp;&nbsp;</td>
						<td align="left">
							&nbsp;&nbsp;0人
						</td>
					</tr>
					<tr>
						<td align="right" width="34%">
							当天总赠送金币数&nbsp;&nbsp;&nbsp;</td>
						<td align="left">
							&nbsp;&nbsp;0金币
						</td>
					</tr>
					<tr>
						<td colspan="2" class="title_03" align="center">
							<strong>用户充值每日统计［2016-10-28 12:05:26］</strong></td>
					</tr>
					<tr>
						<td align="right" width="34%">
							当天点卡充值数量&nbsp;&nbsp;&nbsp;</td>
						<td align="left">
							&nbsp;&nbsp;0张
						</td>
					</tr>
					<tr>
						<td align="right" width="34%">
							当天点卡充值总金币数&nbsp;&nbsp;&nbsp;</td>
						<td align="left">
							&nbsp;&nbsp;0金币
						</td>
					</tr>
					<tr>
						<td align="right" width="34%">
							当天银行充值次数&nbsp;&nbsp;&nbsp;</td>
						<td align="left">
							&nbsp;&nbsp;0次
						</td>
					</tr>
					<tr>
						<td align="right" width="34%">
							当天银行充值总金币数&nbsp;&nbsp;&nbsp;</td>
						<td align="left">
							&nbsp;&nbsp;0金币
						</td>
					</tr>
					<tr>
						<td colspan="2" class="title_03" align="center">
							<strong>后台急需处理［2016-10-28 12:05:26］</strong></td>
					</tr>
					<tr><td align="right" width="34%">
					未审核的留言数&nbsp;&nbsp;&nbsp;
					</td>
					<td align="left">&nbsp;&nbsp;0个&nbsp;&nbsp;查看</td>
					</tr>
					<tr><td align="right" width="34%">
					未审核的奖品兑换记录数&nbsp;&nbsp;&nbsp;
					</td>
					<td align="left">&nbsp;&nbsp;6条&nbsp;&nbsp;查看</td>
					</tr>
				</table>
			--%>

	</form>
</body>
</html>