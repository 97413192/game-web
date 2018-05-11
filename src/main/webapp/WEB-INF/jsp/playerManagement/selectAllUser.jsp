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
<title>查询所有玩家</title>
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
<script>
	$(function(){
		//给确定添加加单击事件
		$("#anpPageIndex_btn").click(function(){
			//alert("sdfs");
			var jump = $("#anpPageIndex_input").val().trim();
			if(!isNaN(jump)){
				if(jump>0 && jump<=${pagecount }){
					window.location.href = "gamePlayer/toAllPlayer.do?playerIndex="+jump;
				}
			}
		});	
	});
</script>
</head>
<body>
    <form name="form1" method="post" action="./AdminModifyLog.aspx?page=10" id="form1">
        <table width="100%" border="1" align="center" cellpadding="3" cellspacing="0" bordercolor="A4B6D7"
            bgcolor="F2F8FF" class="admin_table">
            <tr>
                <td class="title_03" colspan="5" align="center">
                    <strong><font color="red">查询所有玩家</font></strong></td>
            </tr>
             <tr style="font-weight:bold;font-style:italic;">
             	<td></td>
             	<td>玩家名</td>
             	<td>玩家创建时间</td>
             	<td>玩家最后一次修改时间</td>
             	<td>详细信息</td>
             </tr>
            <c:forEach items="${playerList}" var="log" varStatus="s">
            	<tr>
            		<td>${s.index+1 }</td>
	                <td>${log.uname }</td>
	                <td>${log.creDate }</td>
	                <td>${log.modifyDate }</td>
	                <td><a href="gamePlayer/toSelectPlayer.do?name=${log.uname }">详细信息</a></td>
	            </tr>
            </c:forEach>
             
            <tr>
                <td colspan='5' align='right'>
	                <div id="page_link" class="page_link">
		                <div id="anpPageIndex">
							<div style="width:40%;float:left;">
								第${playerIndex }页，共${playerPagecount }页；每页${playerPagesize }条，共${playerCount }条
							</div>
							<div style="width:60%;float:left;">
								<c:choose>
									<c:when test="${playerIndex==1 }">
										<span style="margin-right:5px;font-weight:Bold;color:red;">首页</span>
										<span style="margin-right:5px;font-weight:Bold;color:red;">上一页</span>
									</c:when> 
    								<c:otherwise>
    									<a href="gamePlayer/toAllPlayer.do?playerIndex=1" style="margin-right:5px;">首页</a>
										<a href="gamePlayer/toAllPlayer.do?playerIndex=${playerIndex-1 }" style="margin-right:5px;">上一页</a>
    								</c:otherwise> 
								</c:choose> 
								<c:choose>
									<c:when test="${playerPagecount<=10 }">
										<c:forEach begin="1" end="${playerPagecount }" varStatus="s">
											<c:choose>
												<c:when test="${playerIndex == s.index }">
													<span style="margin-right:5px;font-weight:Bold;color:red;">${s.index }</span>
												</c:when> 
	    										<c:otherwise>
	    											<a href="gamePlayer/toAllPlayer.do?playerIndex=${s.index }" style="margin-right:5px;">${s.index }</a>
	    										</c:otherwise> 
											</c:choose>
										</c:forEach>
									</c:when>
									<c:otherwise>
										<!-- 当总页数减去当前页数不足十页时怎样显示 -->
										<c:forEach begin="${playerIndex>playerPagecount-10?playerPagecount-9:playerIndex }" end="${playerIndex>playerPagecount-10?playerPagecount:playerIndex+9 }" varStatus="s">
											<c:choose>
												<c:when test="${playerIndex == s.index }">
													<span style="margin-right:5px;font-weight:Bold;color:red;">${s.index }</span>
												</c:when> 
	    										<c:otherwise>
	    											<a href="gamePlayer/toAllPlayer.do?playerIndex=${s.index }" style="margin-right:5px;">${s.index }</a>
	    										</c:otherwise> 
											</c:choose>
										</c:forEach>
									</c:otherwise>
								</c:choose>
								<c:choose>
									<c:when test="${playerPagecount == playerIndex }">
										<span style="margin-right:5px;font-weight:Bold;color:red;">下一页</span>
										<span style="margin-right:5px;font-weight:Bold;color:red;">尾页</span>
									</c:when> 
    								<c:otherwise>
    									<a href="gamePlayer/toAllPlayer.do?playerIndex=${playerIndex+1 }" style="margin-right:5px;">下一页</a>
    									<a href="gamePlayer/toAllPlayer.do?playerIndex=${playerPagecount }" style="margin-right:5px;">尾页</a>
    								</c:otherwise> 
								</c:choose>
								
								&nbsp;&nbsp;转到<input type="text"  name="anpPageIndex_input" id="anpPageIndex_input" style="width:30px;" />
								页<input type="button" value="Go" name="anpPageIndex" id="anpPageIndex_btn" />
							</div>
						</div>
	                </div>
                </td>
            </tr>
        </table>
    </form>
</body>
</html>