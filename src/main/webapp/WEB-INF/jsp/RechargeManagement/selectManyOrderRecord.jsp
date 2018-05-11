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
<title>所有消息内容</title>
<base href="<%=basePath%>">
<link href="css/systemSetttings/inc_style.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="css/tcal.css" />
<style>
	body {
		margin-top: 0px;
	}
</style>
<script src="js/basevalue.js"></script>
<script src="js/jquery-1.11.1.js"></script>
<script type="text/javascript" src="js/tcal.js"></script>
<script>
	$(function(){
		//通过玩家id,玩家名等查询
		$("#check1").click(function(){
			//alert("sdfs");
			var jump = $("#anpPageIndex_input").val().trim();
			
		});
		
		//通过直接查询
		$("#check2").click(function(){
			var beginTime = $("#beginTime").val().trim();
			var endTime = $("#endTime").val().trim();
			//alert(beginTime);
			//alert(endTime);
			//验证数据
			var ok = true;
			if(beginTime == "" || beginTime==null){
				alert("起始时间没有选择!");
				ok = false;
			}
			if(endTime == "" || endTime==null){
				alert("结束时间没有选择!");
				ok = false;
			}
			if(ok){
				window.location.href = "recharge/toSelectManyRechargeRecord.do?index=1&category=1&beginTime="+beginTime+"&endTime="+endTime;
				//window.location.href = "recharge/toSelectManyRechargeRecord.do?index=1&category=1&beginTime="+beginTime+"&endTime="+endTime;
			}
		});
		
		//给确定添加加单击事件
		$("#anpPageIndex_btn").click(function(){
			//alert("sdfs");
			var jump = $("#anpPageIndex_input").val().trim();
			var pagecount = $("#pagecount").html().trim();
			if(!isNaN(jump)){
				if(jump>0 && jump<=pagecount){
					window.location.href = "recharge/toSelectManyRechargeRecord.do?index=1&category=1&beginTime="+beginTime+"&endTime="+endTime;
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
                <td class="title_03" colspan="6" align="right">
                    	<input type="button" name="CreateUser" value=" 查 询  " id="check1" class="put" />
                <td class="title_03" colspan="7" align="left">
                	<select name="AdminPwd"  id="category" class="put">  
					 	<option value ="21">玩家id</option>  
					 	<option value ="22">玩家名称</option>  
					 	<option value ="23">订单编号</option>  
					</select>
					<input type="text" name="CreateUser" id="check1" class="put" size="10"/>    
                </td>
            </tr>
            <tr>
                <td class="title_03" colspan="6" align="right">
                   <input type="button" name="CreateUser" value=" 查 询  " id="check2" class="put" />
                <td class="title_03" colspan="7" align="left">
                   <input type="text" name="date" class="tcal" readOnly size="10" id="beginTime"/>
                   <input type="text" name="date" class="tcal" readOnly size="10" id="endTime"/>
            </tr>
            
            <tr>
                <td class="title_03" colspan="13" align="center">
                    <strong><font color="red">消息列表</font></strong></td>
            </tr>
             <tr style="font-weight:bold;font-style:italic;">
             	<td>序号</td>
             	<td>id</td>
             	<td>订单时间</td>
             	<td>订单编号</td>
             	<td>用户昵称</td>
             	<td>玩家id</td>
             	<td>订单金额</td>
             	<td>实付金额</td>
             	<td>充值途径</td>
             	<td>充值前房卡</td>
             	<td>充值房卡数</td>
             	<td>订单状态</td>
             	<td>操作ip</td>
             </tr>
            <c:forEach items="${list }" var="log" varStatus="s">
            	<tr>
	            	<td>${s.index+1 }</td>
	                <td>${log.id }</td>
	                <td>${log.message }</td>
	                <td>${log.message }</td>
	                <td>${log.message }</td>
	                <td>${log.message }</td>
	                <td>${log.message }</td>
	                <td>${log.message }</td>
	                <td>${log.message }</td>
	                <td>${log.message }</td>
	                <td>${log.message }</td>
	                <td>${log.createTime }</td>
	                <td>${log.modifyTime }</td>
	                </tr>
            </c:forEach>
            <tr>
                <td colspan='13' align='right'>
	                <div id="page_link" class="page_link">
		                <div id="anpPageIndex">
							<div style="width:40%;float:left;">
								第${index }页，共<span id="pagecount">${pagecount }</span>页；每页${pagesize }条，共${count }条
							</div>
							<div style="width:60%;float:left;">
								<c:choose>
									<c:when test="${index==1 }">
										<span style="margin-right:5px;font-weight:Bold;color:red;">首页</span>
										<span style="margin-right:5px;font-weight:Bold;color:red;">上一页</span>
									</c:when> 
    								<c:otherwise>
    									<a href="recharge/toSelectAllInformation.do?index=1&beginTime=${beginTime }&endTime=${endTime }&condition=${condition }&category=${category }" 
    										style="margin-right:5px;">首页</a>
										<a href="recharge/toSelectAllInformation.do?index=${index-1 }&beginTime=${beginTime }&endTime=${endTime }&condition=${condition }&category=${category }" style="margin-right:5px;">上一页</a>
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
	    											<a href="recharge/toSelectAllInformation.do?index=${s.index }&beginTime=${beginTime }&endTime=${endTime }&condition=${condition }&category=${category }" style="margin-right:5px;">${s.index }</a>
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
	    											<a href="recharge/toSelectAllInformation.do?index=${s.index }&beginTime=${beginTime }&endTime=${endTime }&condition=${condition }&category=${category }" style="margin-right:5px;">${s.index }</a>
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
    									<a href="recharge/toSelectAllInformation.do?index=${index+1 }&beginTime=${beginTime }&endTime=${endTime }&condition=${condition }&category=${category }" style="margin-right:5px;">下一页</a>
    									<a href="recharge/toSelectAllInformation.do?index=${pagecount }&beginTime=${beginTime }&endTime=${endTime }&condition=${condition }&category=${category }" style="margin-right:5px;">尾页</a>
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