<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>所有消息内容</title>
<base href="<%=basePath%>">
<!-- <link href="css/systemSetttings/inc_style.css" rel="stylesheet" type="text/css" /> -->
<link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
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
    <form name="form1" method="post" action=" id="form1">
        <table class="imagetable" width="100%" border="1" align="center" cellpadding="3" cellspacing="0" bordercolor="A4B6D7"
            bgcolor="F2F8FF" class="admin_table">
            <tr>
                <td class="title_03" colspan="9" align="center">
                    <strong><font color="red">消息列表</font></strong></td>
            </tr>
             <tr style="font-weight:bold;font-style:italic;">
             	<th>序号</th>
             	<th>游戏昵称</th>
             	<th>游戏id</th>
             	<th>姓名</th>
             	<th>身份证</th>
             	<th>手机号</th>
             	<th>申请时间</th>
             	<th>批准时间</th>
             </tr>
            <c:set var="realNames" value="${list}"/>
            <c:if test="${realNames != null}">
	            <c:forEach items="${realNames}" var="realName" varStatus="s">
	            	<tr>
		            	<td>${s.index+1 }</td>
		                <td>${realName.PName}</td>
		                <td>${realName.gameId}</td>
		                <td>${realName.realNameInfo.realName}</td>
		                <td>${realName.realNameInfo.idCard}</td>
		                <td>${realName.realNameInfo.phoneNumber}</td>
		                <td>
		                	<fmt:formatDate value="${realName.realNameInfo.applyTime}" pattern="yyyy-MM-dd HH:mm:ss" type="time" />
		                </td>
		                 <td>
		                 	<c:if test="${realName.realNameInfo.applyState == 1}">
		                 		<fmt:formatDate value="${realName.realNameInfo.approveTime}" pattern="yyyy-MM-dd HH:mm:ss" type="time" />(待批准)
		                 	</c:if>
		                 	<c:if test="${realName.realNameInfo.applyState == 2}">
		                 		<fmt:formatDate value="${realName.realNameInfo.approveTime}" pattern="yyyy-MM-dd HH:mm:ss" type="time" />(已批准)
		                 	</c:if>
		                 	<c:if test="${realName.realNameInfo.applyState == 3}">
		                 		<fmt:formatDate value="${realName.realNameInfo.approveTime}" pattern="yyyy-MM-dd HH:mm:ss" type="time" />(已拒绝)
		                 	</c:if>
		                 </td>
		                </tr>
	            </c:forEach>
            </c:if>
            <c:if test="${realNames == null}">
	            		<tr>
		            		<td colspan="8" style="text-align:center">暂无多余信息</td>
		                </tr>
            </c:if>
            <tr>
                <td colspan='8' align='right'>
	                <div id="page_link" class="page_link">
		                <div id="anpPageIndex">
							<div style="width:40%;float:left;">
								第${index }页，共${pagecount }页；每页${pagesize }条，共${count }条
							</div>
							<div style="width:60%;float:left;">
								<c:choose>
									<c:when test="${index==1 }">
										<span style="margin-right:5px;font-weight:Bold;color:red;">首页</span>
										<span style="margin-right:5px;font-weight:Bold;color:red;">上一页</span>
									</c:when> 
    								<c:otherwise>
    									<a href="information/toSelectAllInformation.do?index=1" style="margin-right:5px;">首页</a>
										<a href="information/toSelectAllInformation.do?index=${index-1 }" style="margin-right:5px;">上一页</a>
    								</c:otherwise> 
								</c:choose> 
								
								<c:choose>
									<c:when test="${pagecount<=10 }">
										<c:forEach begin="1" end="${pagecount }" varStatus="s">
											<c:choose>
												<c:when test="${index == s.index }">
													<span style="margin-right:5px;font-weight:Bold;color:red;">${s.index }</span>
												</c:when> 
	    										<c:otherwise>
	    											<a href="information/toSelectAllInformation.do?index=${s.index }" style="margin-right:5px;">${s.index }</a>
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
	    											<a href="information/toSelectAllInformation.do?index=${s.index }" style="margin-right:5px;">${s.index }</a>
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
    									<a href="information/toSelectAllInformation.do?index=${index+1 }" style="margin-right:5px;">下一页</a>
    									<a href="information/toSelectAllInformation.do?index=${pagecount }" style="margin-right:5px;">尾页</a>
    								</c:otherwise> 
								</c:choose>
								
								&nbsp;&nbsp;转到<input type="text" name="anpPageIndex_input" id="anpPageIndex_input" style="width:30px;" />
								页<input type="button" value="Go" class="btn btn-success" name="anpPageIndex" id="anpPageIndex_btn" />
							</div>
						</div>
	                </div>
                </td>
            </tr>
        </table>
    </form>
    
    <script>
	$(function(){
		//href="information/toSelectInformation.do?
		var UPDATE_APPLYINFO_SUCCESS = "更新信息成功";
		var UPDATE_APPLYINFO_ERROR = "更新信息失败";
		var RMISERVER_ERROR_INFO = "游戏服链接异常，请稍后再试";
		//给确定添加加单击事件
		$(".agree").click(function(){
			var gameId = $(".agree").attr("id")
			var state  = 2
			if(confirm("是否同意该玩家的实名认证申请?")){
					//同意，更新该玩家的认证申请，更新批准时间，向服务器发送钻石奖励
					$.ajax({
						type:'post',
						url :'gamePlayer/updateApplyState.do',
						data:{gameId:gameId,state:state},
						success:function(data){
							if(data.ok = UPDATE_APPLYINFO_SUCCESS){
								alert(UPDATE_APPLYINFO_SUCCESS);
								location.href = "gamePlayer/realNameApply.do?index=1";
							}
							if(data.error == UPDATE_APPLYINFO_ERROR){
								alert(UPDATE_APPLYINFO_ERROR)
							}
							if(data.rmiServer_error == RMISERVER_ERROR_INFO){
								alert(UPDATE_APPLYINFO_ERROR)
							}
						}
						
					})
			}
		});	
		$(".refuse").click(function(){
			var gameId = $(".refuse").attr("id")
			var state  = 3
			if(confirm("是否拒绝该玩家的实名认证申请?")){
				$.ajax({
					type:'post',
					url :'gamePlayer/updateApplyState.do',
					data:[{gameId:gameId},{state:state}],
					success:function(data){
						if(data.ok = UPDATE_APPLYINFO_SUCCESS){
							alert(UPDATE_APPLYINFO_SUCCESS);
							location.href = "gamePlayer/realNameApply.do?index=1";
						}
						if(data.error == UPDATE_APPLYINFO_ERROR){
							alert(UPDATE_APPLYINFO_ERROR)
						}
					}
					
				})
			}
		});	
	});
</script>
</body>

</html>