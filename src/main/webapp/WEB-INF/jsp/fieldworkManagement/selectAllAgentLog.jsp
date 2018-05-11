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
<title>代理日志</title>
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
		//给确定添加加单击事件
		$("#anpPageIndex_btn").click(function(){
			//alert("sdfs");
			var jump = $("#anpPageIndex_input").val().trim();
			var pagecount=$("#pagecount").html();
			if(!isNaN(jump)){
				if(jump>0 && jump<=pagecount){
					window.location.href = "agent/findAllAgentLog.do?index="+jump;
				}
			}
		});
		//给查询/删除添加点击事件
		$('#toOperateAgentLog').click(function(){
			$('#agentLogTable1')[0].style.display = 'none';
			$('#agentLogTable2')[0].style.display = '';
		});
		//给查询添加点击事件
		$('#selectAgentLog').click(function(){
			var startDate = $.trim($('#startDate').val());
			var endDate = $.trim($('#endDate').val());
			window.location.href = "agent/findAllAgentLog.do?startDate="+startDate+"&endDate="+endDate+"&index=-1";
		});
		//给删除添加点击事件
		$('#deleteAgentLog').click(function(){
			var startDate = $.trim($('#startDate').val());
			var endDate = $.trim($('#endDate').val());
			if((startDate!=null&&startDate!="")&&(endDate!=null&&startDate!="")){
				window.location.href = "agent/deleteAgentLogByDate.do?startDate="+startDate+"&endDate="+endDate+"&index=-1";
			}
			window.location.href = "agent/findAllAgentLog.do?startDate="+startDate+"&endDate="+endDate+"&index=-1";
		});
	});
	function changeTrColor(obj){
		 var _table=obj.parentNode;
		 for (var i=0;i<_table.rows.length;i++){
		 	_table.rows[i].style.backgroundColor="";
		 }    
		 obj.style.backgroundColor="#F5DEB3";
	}
</script>
</head>
<body>
     <table id="agentLogTable1" width="100%" border="1" align="center" cellpadding="3" cellspacing="0" bordercolor="A4B6D7"
         bgcolor="F2F8FF" class="admin_table">
         <tr>
             <td class="title_03" colspan="19" align="center">
                 <strong><font color="red">代理商列表</font></strong></td>
         </tr>
          <tr style="font-weight:bold;font-style:italic;">
          	<td>序号</td>
          	<td>日志ID</td>
          	<td>代理商名字</td>
          	<td>操作IP</td>
          	<td>系统</td>
          	<td>操作时间</td>
          	<td>描述</td>
          	<td>状态</td>
          </tr>
         <c:forEach items="${list }" var="log" varStatus="s">
         	<tr style="cursor:hand " onmousemove="changeTrColor(this)">
          	<td>${s.index+1 }</td>
              <td>${log.id }</td>
              <td>${log.name }</td>
              <td>${log.ip }</td>
              <td>${log.system }</td>
              <td>${log.time }</td>
              <td>${log.dsc }</td>
              <td>${log.status }</td>
         </tr>
         </c:forEach>
          
         <tr>
             <td colspan='19' align='right'>
              <div id="page_link" class="page_link">
               <div id="anpPageIndex">
               	&nbsp;<input id="toOperateAgentLog" type="button" value="查询/删除" style="float:left;">
				<div style="width:30%;float:left;">
					第${index }页，共${pagecount }页；每页${pagesize }条，共${count }条
				</div>
				<div style="width:60%;float:left;">
					<c:choose>
						<c:when test="${index==1 }">
							<span style="margin-right:5px;font-weight:Bold;color:red;">首页</span>
							<span style="margin-right:5px;font-weight:Bold;color:red;">上一页</span>
						</c:when> 
 								<c:otherwise>
 									<a href="agent/findAllAgentLog.do?startDate=${startDate }&endDate=${endDate }&index=1" style="margin-right:5px;">首页</a>
							<a href="agent/findAllAgentLog.do?startDate=${startDate }&endDate=${endDate }&index=${index-1 }" style="margin-right:5px;">上一页</a>
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
  											<a href="agent/findAllAgentLog.do?startDate=${startDate }&endDate=${endDate }&index=${s.index }" style="margin-right:5px;">${s.index }</a>
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
  											<a href="agent/findAllAgentLog.do?startDate=${startDate }&endDate=${endDate }&index=${s.index }" style="margin-right:5px;">${s.index }</a>
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
 									<a href="agent/findAllAgentLog.do?startDate=${startDate }&endDate=${endDate }&index=${index+1 }" style="margin-right:5px;">下一页</a>
 									<a href="agent/findAllAgentLog.do?startDate=${startDate }&endDate=${endDate }&index=${pagecount }" style="margin-right:5px;">尾页</a>
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
     <div id="agentLogTable2" style="display:none">
		<table width="100%" border="1" align="center" cellpadding="3" cellspacing="0" bordercolor="#A4B6D7"
			bgcolor="F2F8FF" class="admin_table2">
			<tr>
				<td class="title_03">
					<strong>操作代理日志界面</strong>
				</td>
			</tr>
		</table>
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td style="height: 5px">
				</td>
			</tr>
		</table>
		<table width="100%" border="1" align="center" cellpadding="4" cellspacing="0" bordercolor="A4B6D7"
			bgcolor="F2F8FF" class="admin_table">
			<tr>
				<td align="right">
					起始日期：
				</td>
				<td colspan="2" align="left">
					<input id="startDate" type="text" name="date" class="tcal" readOnly/>
					<span id="alterBCNMSG" class="red"></span>
				</td>
			</tr>
			
			<tr>
				<td align="right">
					结束日期：
				</td>
				<td colspan="2" align="left">
					<input id="endDate" type="text" name="date" class="tcal" readOnly/>
				</td>
			</tr>
			<tr>
				<td align="right">
					<input type="button" value=" 查询 " id="selectAgentLog"/>
				</td>
				<td colspan="2" align="left">
					<input type="button" value=" 删除" id="deleteAgentLog"/>
				</td>
			</tr>
		</table>
    </div>
</body>

</html>