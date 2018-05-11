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
<title>所有玩家</title>
<base href="<%=basePath%>">
<link href="css/systemSetttings/inc_style.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
<link href="css/tableCss.css" rel="stylesheet" type="text/css" />
<style>
	body {
		margin-top: 0px;
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
			var pagecount = $("#pagecount");
			if(!isNaN(jump)){
				if(jump>0 && jump<=pagecount){
					window.location.href = "agent/toAgentEarnData.do?start=${start}&end=${end}&userId=${userId}&index="+jump;
				}
			}
		});	
		
		$("#sub").click(function(){
			var start = $("#start").val().trim();
			var end = $("#end").val().trim();
			var name = $("#name").val().trim();
			var ok = true;
			if(name=="" || name == null){
				if((start=="" || start==null)||(end=="" || end==null)){
				ok = false;
				alert("没有选择时间");
				}
			}
			if(ok){
			window.location.href = "agent/toAgentEarnData.do?index=1&start="+start+"&end="+end+"&userId="+name+"";
			}
		})
		
	});
</script>
</head>
<body>
    <form name="form1" method="post" action="" id="form1">
        <table width="100%" border="1" align="center" cellpadding="3" cellspacing="0" bordercolor="A4B6D7"
            bgcolor="F2F8FF" class="imagetable">
              <tr>
                <td class="title_03" colspan="7" align="center" width="100%"><input type="date" id="start"><input type="date" id="end"><input type="text" id="name" placeholder="输入代理商id查询"><input type="button" id="sub" value="查询" class="btn btn-primary"></td>
            </tr>
            <tr>
            <td class="title_03" colspan="2">
<%--                     <strong><font color="red">总流水:${totalCount }</font></strong></td> --%>
                <td class="title_03" colspan="5" align="center">
                    <strong><font color="red">收益详细列表</font></strong></td>
            </tr>
             <tr style="font-weight:bold;font-style:italic;">
             	<th align="center">代理ID</th>
             	<th align="center">代理等级</th>
             	<th align="center">代理名称</th>
             	<th align="center">玩家总充值</th>
             	<th align="center">代理收益</th>
             </tr>
            <c:forEach items="${list }" var="log" varStatus="s">
            	<tr>
	            	<td align="center">${log.userId }</td>
	                <td align="center">${log.grade }</td>
	                <td align="center">${log.name }</td>
	                <td align="center">${log.player }</td>
	                <td align="center">${log.earn }</td>
	            </tr>
            </c:forEach>
            <tr>
                <td colspan='7' align='right'>
	                <div id="page_link" class="page_link">
		                <div id="anpPageIndex">
							<div style="width:40%;float:left;">
								第${index }页，共<span id ="pagecount">${pagecount }</span>页；每页${pagesize }条，共${count }条
							</div>
							<div style="width:60%;float:left;">
								<c:choose>
									<c:when test="${index==1 }">
										<span style="margin-right:5px;font-weight:Bold;color:red;">首页</span>
										<span style="margin-right:5px;font-weight:Bold;color:red;">上一页</span>
									</c:when> 
    								<c:otherwise>
    									<a href="agent/toAgentEarn.do?index=1&start=${start}&end=${end}&userId=${userId}" style="margin-right:5px;">首页</a>
										<a href="agent/toAgentEarn.do?index=${index-1 }&start=${start}&end=${end}&userId=${userId}" style="margin-right:5px;">上一页</a>
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
	    											<a href="agent/toAgentEarn.do?index=${s.index }&start=${start}&end=${end}&userId=${userId}" style="margin-right:5px;">${s.index }</a>
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
	    											<a href="agent/toAgentEarn.do?index=${s.index }&start=${start}&end=${end}&userId=${userId}" style="margin-right:5px;">${s.index }</a>
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
    									<a href="agent/toAgentEarn.do?index=${index+1 }&start=${start}&end=${end}&userId=${userId}" style="margin-right:5px;">下一页</a>
    									<a href="agent/toAgentEarn.do?index=${pagecount }&start=${start}&end=${end}&userId=${userId}" style="margin-right:5px;">尾页</a>
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