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
<title>查询所有房间概率</title>
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
</head>
<body>
    <form name="form1" method="post" action="" id="form1">
        <table width="100%" border="1" align="center" cellpadding="3" cellspacing="0" bordercolor="A4B6D7"
            bgcolor="F2F8FF" class="admin_table">
            <tr>
                <td class="title_03" colspan="15" align="center">
                    <strong><font color="red">查询所有房间概率</font></strong></td>
            </tr>
             <tr style="font-weight:bold;font-style:italic;">
             	<td></td>
             	<td>房间概率id</td>
             	<td>5.K</td>
             	<td>R.S</td>
             	<td>S.F</td>
             	<td>4.K</td>
             	<td>F.H</td>
             	<td>F.L</td>
             	<td>S.T</td>
             	<td>3.K</td>
             	<td>2.P</td>
             	<td>1.P</td>
             	<td>N.P</td>
             	<td>触发条件上限</td>
             	<td>修改</td>
             </tr>
            <c:forEach items="${DeskChanceList}" var="log" varStatus="s">
            	<tr>
            		<td>${s.index+1 }</td>
	                <td>${log.id }</td>
	                <td>
	                	${log.K5 }
	                </td>
	                <td>
	                	${log.RS}
	                </td>
	                <td>
	               		${log.SF}
	                </td>
	                <td>
	               		${log.K4}
	                </td>
	                <td>
	               		${log.FH}
	                </td>
	                <td>
	               		${log.FL}
	                </td>
	                <td>
	               		${log.ST}
	                </td>
	                <td>
	               		${log.K3}
	                </td>
	                <td>
	               		${log.P2}
	                </td>
	                <td>
	               		${log.P1}
	                </td>
	                <td>
	               		${log.NP}
	                </td>
	                <td>
	               		${log.top}
	                </td>
	                <td>
	                	<a href="roomChance/selectAllRoomChance.do?id=${log.id }&K5=${log.K5 }&RS=${log.RS}&SF=${log.SF}&K4=${log.K4}&FH=${log.FH}&FL=${log.FL}&ST=${log.ST}&K3=${log.K3}&P2=${log.P2}&P1=${log.P1}&NP=${log.NP}&top=${log.top}">修 改</a>
	                </td>
	            </tr>
            </c:forEach>
        </table>
    </form>
</body>
</html>