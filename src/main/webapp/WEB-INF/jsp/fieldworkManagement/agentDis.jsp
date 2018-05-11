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
<title>日志查询</title>
<base href="<%=basePath%>">
<!-- 最新版本的 Bootstrap 核心 CSS 文件 -->
<link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
<link  href="css/systemSetttings/inc_style.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="css/tcal.css" />

<style type="text/css">
table.gridtable {
	font-family: verdana,arial,sans-serif;
	font-size:11px;
	color:#333333;
	border-width: 1px;
	border-color: #666666;
	border-collapse: collapse;
}
table.gridtable th {
	border-width: 1px;
	padding: 8px;
	border-style: solid;
	border-color: #666666;
	background-color: #dedede;
}
table.gridtable td {
	border-width: 1px;
	padding: 8px;
	border-style: solid;
	border-color: #666666;
	background-color: #ffffff;
}

table.imagetable {
    font-family: verdana,arial,sans-serif;
    font-size:11px;
    color:#333333;
    border-width: 1px;
    border-color: #999999;
    border-collapse: collapse;
}
table.imagetable th {
    background:#b5cfd2 url('images/cell-blue.jpg');
    border-width: 1px;
    padding: 8px;
    border-style: solid;
    border-color: #999999;
}
table.imagetable td {
    background:#dcddc0 url('images/cell-grey.jpg');
    border-width: 1px;
    padding: 8px;
    border-style: solid;
    border-color: #999999;
}

	body{
			margin-top: 0px;
		}
	.red{
			color: red;
		}
</style>
<script src="js/basevalue.js"></script>
<script src="js/jquery-1.11.1.js"></script>
<script type="text/javascript" src="js/tcal.js"></script>
<script type="text/javascript">
	$(function(){
		
		//给跳转按钮添加单击事件
		$("#anpPageIndex_btn").click(function(){
			//alert("sdfs");
			var name=$.trim($('#operAccount').val());
			var startDate=$.trim($('#startDate').val());
			var endDate=$.trim($('#endDate').val());
			var jump = $("#anpPageIndex_input").val().trim();
			var pagecount=$('#pagecount').html();
			console.log(jump);
			if(!isNaN(jump)){
				console.log(!isNaN(jump));
				if(jump>0 && jump<=pagecount){
					window.location.href = "agent/toSelectAgentDis.do?name="+name+"&startDate="+
						startDate+"&endDate="+endDate+"&index="+jump;
				}
			}
		});
		$('#selLog').click(function(){
			var name=$.trim($('#operAccount').val());
			var startDate=$.trim($('#startDate').val());
			var endDate=$.trim($('#endDate').val());
			window.location.href="agent/toSelectAgentDis.do?name="+name+"&startDate="+
					startDate+"&endDate="+endDate+"&index=1";
		});
	$('#cancel').click(function() {
			window.location.href = "agent/toAgentDis.do";
		});
	});
</script>
</head>
<body><c:if test="${list ==null }">
	<div id="div1">
		<table class="gridtable" width="100%" border="1" align="center" cellpadding="3" cellspacing="0" bordercolor="#A4B6D7"
			bgcolor="F2F8FF" >
			<tr>
				<td class="title_03" align="center">
					<strong><font color="red" size="3px">返水解散界面</font></strong>
				</td>
			</tr>
		</table>
		<table class="gridtable" width="100%" border="1" align="center" cellpadding="3" cellspacing="0" bordercolor="A4B6D7"
			bgcolor="F2F8FF" class="admin_table">
			<tr>
				<td align="right" width="40%" style="height:20px;">
					代理账号：
				</td>
				<td colspan="2" align="left"  style="height:20px;">
					<input type="text" id="operAccount">
				</td>
			</tr>
			<tr>
				<td align="right">
					起始日期：
				</td>
				<td colspan="2" align="left">
					<input id="startDate" type="text" name="date" class="tcal"r/>
				</td>
			</tr>
			<tr>
				<td align="right">
					结束日期：
				</td>
				<td colspan="2" align="left">
					<input id="endDate" type="text" name="date" class="tcal"/>
				</td>
			</tr>
			<tr>
				<td align="right">
					<input type="button"  class="btn btn-success" value="查询" id="selLog"/>
				</td>
				<td align="left">
					<input type="button"  class="btn btn-success" value=" 重置 " id="cancel"/>
				</td>
			</tr>
		</table>
	</div>
	</c:if>
	<c:if test="${list !=null }">
	<div id="div2">
		<table class="imagetable" width="100%" border="1" align="center" cellpadding="3" cellspacing="0" bordercolor="A4B6D7"
            bgcolor="F2F8FF" class="admin_table">
				<tr style="font-weight: bold; font-style: italic;">
					<td>代理账号</td>
					<td>代理房卡信息</td>
					<td>玩家id</td>
					<td>代理商id</td>
					<td>玩家昵称</td>
					<td>玩家本月充值金额</td>
					<td>玩家房卡信息</td>
				</tr>
				<c:forEach items="${list}" var="log" varStatus="s">
					<tr>
						<td>${log.account }</td>
						<td>${log.roomCard }</td>
						<td>${log.gameId }</td>
						<td>${log.userID }</td>
						<td>${log.pNickName }</td>
						<td>${log.nowMonthPay}</td>
						<td>${log.playerRoomCard }</td>
					</tr>
				</c:forEach>
				<tr>
                <td colspan='8' align='right'>
	                <div id="page_link" class="page_link">
		                <div id="anpPageIndex">
							<div style="width:30%;float:left;">
								第${index }页，共<span id="pagecount">${pagecount }</span>页；每页${pagesize }条，共${count }条
							</div>
							<div style="width:60%;float:left;">
								<c:choose>
									<c:when test="${index==1 }">
										<span style="margin-right:5px;font-weight:Bold;color:red;">首页</span>
										<span style="margin-right:5px;font-weight:Bold;color:red;">上一页</span>
									</c:when> 
    								<c:otherwise>
    									<a href="agent/toSelectAgentDis.do?name=${name }&startDate=${startDate}&endDate=${endDate }&index=1" style="margin-right:5px;">首页</a>
										<a href="agent/toSelectAgentDis.do?name=${name }&startDate=${startDate}&endDate=${endDate }&index=${index-1 }" style="margin-right:5px;">上一页</a>
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
	    											<a href="agent/toSelectAgentDis.do?name=${name }&startDate=${startDate}&endDate=${endDate }&index=${s.index }" style="margin-right:5px;">${s.index }</a>
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
	    											<a href="agent/toSelectAgentDis.do?name=${name }&startDate=${startDate}&endDate=${endDate }&index=${s.index }" style="margin-right:5px;">${s.index }</a>
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
    									<a href="agent/toSelectAgentDis.do?name=${name }&startDate=${startDate}&endDate=${endDate }&index=${index+1 }" style="margin-right:5px;">下一页</a>
    									<a href="agent/toSelectAgentDis.do?name=${name }&startDate=${startDate}&endDate=${endDate }&index=${pagecount }" style="margin-right:5px;">尾页</a>
    								</c:otherwise> 
								</c:choose>
								
								&nbsp;&nbsp;转到<input type="text"  name="anpPageIndex_input" id="anpPageIndex_input" style="width:30px;" />
								页<input type="button" value="Go" class="btn btn-success" name="anpPageIndex" id="anpPageIndex_btn" />
							</div>
						</div>
	                </div>
                </td>
            </tr>
        </table>
	</div>
	</c:if>
</body>
</html>