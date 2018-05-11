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
<title>绑定玩家列表</title>
<base href="<%=basePath%>">
<link href="css/systemSetttings/inc_style.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
<link href="css/tableCss.css" rel="stylesheet" type="text/css" />
<style>
	body {
		margin-top: 0px;
		font-family : 微软雅黑,宋体;
		font-size : 0.9em;
	}
	table{
		overflow:scroll
	 }
</style>
<script src="js/basevalue.js"></script>
<script src="js/jquery-1.11.1.js"></script>
<script>
	$(function(){
		var name=${log.account };
		//给确定添加加单击事件
		$("#anpPageIndex_btn").click(function(){
			//alert("sdfs");
			var jump = $("#anpPageIndex_input").val().trim();
			if(!isNaN(jump)){
				var pagecount=$("#pagecount").value();
				if(jump>0 && jump<=pagecount){
					window.location.href = "gamePlayer/toSelectBindedPlayers.do?index="+jump;
				}
			}
		});	
	});
</script>
</head>
<body>
    <form name="form1" method="post" action="" id="form1">
        <table class="imagetable" width="100%" border="1" align="center" cellpadding="3" cellspacing="0" bordercolor="A4B6D7"
            bgcolor="F2F8FF" class="admin_table">
            <tr >
                <td class="title_03" colspan="12" align="center">
                    <strong><font color="red">绑定玩家列表</font></strong></td>
            </tr>
             <tr style="font-weight:bold;font-style:italic;">
             	<th>序号</th>
             	<th>id</th>
             	<th>代理账号</th>
             	<th>代理房卡信息</th>
             	<th>玩家id</th>
             	<th>代理商id</th>
             	<th>绑定时间</th>
             	<th>修改时间</th>
             	<th>玩家昵称</th>
             	<th>玩家本月充值金额</th>
             	<th>玩家房卡信息</th>
             </tr>
            <c:forEach items="${list }" var="log" varStatus="s">
            	<tr>
	            	<td>${s.index+1 }</td>
	                <td>${log.id }</td>
	                <td>${log.account }</td>
	                <td>${log.roomCard }</td>
	                <td>${log.gameId }</td>
	                <td>${log.userID }</td>
	                <td>${log.bindingTime }</td>
	                <td>${log.modifyTime }</td>
	                <td>${log.pNickName }</td>
	                <td>${log.nowMonthPay}</td>
	                <td>${log.playerRoomCard }</td>
	                </tr>
            </c:forEach>
            <tr>
            	<td>所有玩家充值总金额：<font color="red">${paycount }</font></td>
            </tr>
            <tr>
                <td colspan='12' align='right'>
	                <div id="page_link" class="page_link">
		                <div id="anpPageIndex">
							<div style="width:40%;float:left;">
								第${index }页，共<span id="pagecount">${pagecount }</span>页；每页${pagesize }条，共${count }条
							</div>
							<div style="width:60%;float:left;">
							<!-- index==1 -->
								<c:choose>
									<c:when test="${index==1 }">
										<span style="margin-right:5px;font-weight:Bold;color:red;">首页</span>
										<span style="margin-right:5px;font-weight:Bold;color:red;">上一页</span>
									</c:when> 
    								<c:otherwise>
    									<a href="gamePlayer/toSelectBindedPlayers.do?index=1" style="margin-right:5px;">首页</a>
										<a href="gamePlayer/toSelectBindedPlayers.do?index=${index-1 }" style="margin-right:5px;">上一页</a>
    								</c:otherwise> 
								</c:choose> 
								<!-- pagecount<=10 -->
								<c:choose>
									<c:when test="${pagecount<=10 }">
										<c:forEach begin="1" end="${pagecount }" varStatus="s">
											<c:choose>
												<c:when test="${index == s.index }">
													<span style="margin-right:5px;font-weight:Bold;color:red;">${s.index }</span>
												</c:when> 
	    										<c:otherwise>
	    											<a href="gamePlayer/toSelectBindedPlayers.do?index=${s.index }" style="margin-right:5px;">${s.index }</a>
	    										</c:otherwise> 
											</c:choose>
										</c:forEach>
									</c:when>
									<c:otherwise>
										<!-- 当总页数减去当前页数不足十页时怎样显示 -->
										<c:forEach begin="${index>pagecount-10?pagecount-9:index }" end="${index>pagecount-10?pagecount:index+9 }" varStatus="s">
											<c:choose>
												<c:when test="${index == s.index }">
													<span style="margin-right:5px;font-weight:Bold;color:red;">${s.index }</span>
												</c:when> 
	    										<c:otherwise>
	    											<a href="gamePlayer/toSelectBindedPlayers.do?index=${s.index }" style="margin-right:5px;">${s.index }</a>
	    										</c:otherwise> 
											</c:choose>
										</c:forEach>
									</c:otherwise>
								</c:choose>
								
								<c:choose>
									<c:when test="${pagecount == index }">
										<span style="margin-right:5px;font-weight:Bold;color:red;">下一页</span>
										<span style="margin-right:5px;font-weight:Bold;color:red;">尾页</span>
									</c:when> 
    								<c:otherwise>
    									<a href="gamePlayer/toSelectBindedPlayers.do?index=${index+1 }" style="margin-right:5px;">下一页</a>
    									<a href="gamePlayer/toSelectBindedPlayers.do?index=${pagecount }" style="margin-right:5px;">尾页</a>
    								</c:otherwise> 
								</c:choose>
								
								&nbsp;&nbsp;转到<input type="text"  name="anpPageIndex_input" id="anpPageIndex_input" style="width:30px;" />
								页<input type="button" value="Go" name="anpPageIndex" id="anpPageIndex_btn"  class="btn btn-success"/>
							</div>
						</div>
	                </div>
                </td>
            </tr>
        </table>
    </form>
</body>

</html>