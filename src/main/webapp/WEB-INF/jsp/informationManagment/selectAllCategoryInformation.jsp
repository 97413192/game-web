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
<title>跑马灯和公告</title>
<base href="<%=basePath%>">
<link href="css/systemSetttings/inc_style.css" rel="stylesheet" type="text/css" />
<link href="css/tableCss.css" rel="stylesheet" type="text/css" />
<style>
	body {
		margin-top: 0px;
	}
</style>
<script src="js/basevalue.js"></script>
<script src="js/jquery-1.11.1.js"></script>
</head>
<body>
    <form name="form1" method="post" action="./AdminModifyLog.aspx?page=10" id="form1">
        <table class="imagetable" width="100%" border="1" align="center" cellpadding="3" cellspacing="0" bordercolor="A4B6D7"
            bgcolor="F2F8FF" class="admin_table">
            <tr>
                <td class="title_03" colspan="7" align="center">
                    <strong><font color="red">消息列表</font></strong></td>
            </tr>
             <tr style="font-weight:bold;font-style:italic;">
             	<th>序号</th>
             	<th>消息id</th>
             	<th>消息内容</th>
             	<th>消息类别</th>
             	<th>创建时间</th>
             	<th>修改时间</th>
             </tr>
            <c:forEach items="${list }" var="log" varStatus="s">
            	<tr>
	            	<td>${s.index+1 }</td>
	                <td>${log.id }</td>
	                <td>${log.message }</td>
	                <c:choose>
						<c:when test="${log.category==2 }">
                 			<td>跑马灯</td>
                 		</c:when> 
 						<c:otherwise>
 							<td>公告</td>
 						</c:otherwise> 
					</c:choose>
	                <td>${log.createTime }</td>
	                <td>${log.modifyTime }</td>
	                </tr>
            </c:forEach>
        </table>
    </form>
</body>

</html>