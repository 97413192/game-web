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
<link  href="css/systemSetttings/inc_style.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="css/tcal.css" />
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
<script type="text/javascript" src="js/tcal.js"></script>
<script type="text/javascript">
	$(function(){
		
		//给跳转按钮添加单击事件
		$("#anpPageIndex_btn").click(function(){
			//alert("sdfs");
			var name=$.trim($('#operAccount').val());
			var startDate=$.trim($('#startDate').val());
			var endDate=$.trim($('#endDate').val());
			var ip=$.trim($('#operIP').val());
			var jump = $("#anpPageIndex_input").val().trim();
			var pagecount=$('#pagecount').html();
			console.log(jump);
			if(!isNaN(jump)){
				console.log(!isNaN(jump));
				if(jump>0 && jump<=pagecount){
					console.log("systemSettings/toSelectLogByCondition.do?name="+name+"&startDate="+
							startDate+"&endDate="+endDate+"&ip="+ip+"&index="+jump);
					window.location.href = "systemSettings/toSelectLogByCondition.do?name="+name+"&startDate="+
						startDate+"&endDate="+endDate+"&ip="+ip+"&index="+jump;
				}
			}
		});
		$('#selLog').click(function(){
			var regStartDate=$.trim($('#regStartDate').val());
			var regEndDate=$.trim($('#regEndDate').val());
			var logStartDate=$.trim($('#logStartDate').val());
			var logEndDate=$.trim($('#logEndDate').val());
			var regIP=$.trim($('#regIP').val());
			var logIP=$.trim($('#logIP').val());
			window.location.href="systemSettings/toSelectLogByCondition.do?name="+name+"&startDate="+
					startDate+"&endDate="+endDate+"&ip="+ip+"&index=-1";
		});
		$('#cancel').click(function(){
			
			window.location.href="systemSettings/toLogSelect.do";
		});
	});
</script>
</head>
<body>
	<div id="div1">
		<table width="100%" border="1" align="center" cellpadding="3" cellspacing="0" bordercolor="#A4B6D7"
			bgcolor="F2F8FF" class="admin_table2">
			<tr>
				<td class="title_03">
					<strong>日志高级查询界面</strong>
				</td>
			</tr>
		</table>
		<table width="100%" border="1" align="center" cellpadding="3" cellspacing="0" bordercolor="A4B6D7"
			bgcolor="F2F8FF" class="admin_table">
			<tr>
				<td class="title_03">
					时间查询
				</td>
			</tr>
			<tr>
				<td align="right">
					注册时间：
				</td>
				<td colspan="2" align="left">
					起始日期：<input id="regStartDate" type="text" name="date" class="tcal" value="${startDate }" readOnly/>
					结束日期：<input id="regEndDate" type="text" name="date" class="tcal" value="${endDate }" readOnly/>
				</td>
			</tr>
			<tr>
				<td align="right">
					登录时间：
				</td>
				<td colspan="2" align="left">
					起始日期：<input id="logStartDate" type="text" name="date" class="tcal" value="${startDate }" readOnly/>
					结束日期：<input id="logEndDate" type="text" name="date" class="tcal" value="${endDate }" readOnly/>
				</td>
			</tr>
			<tr>
				<td class="title_03">
					IP查询
				</td>
			</tr>
			<tr>
				<td align="right" width="40%" style="height:20px;">
					注册地址：
				</td>
				<td colspan="2" align="left"  style="height:20px;">
					<input type="text" class="put" id="regIP" value="${ip }"/>
				</td>
			</tr>
			<tr>
				<td align="right" width="40%" style="height:20px;">
					登录地址：
				</td>
				<td colspan="2" align="left"  style="height:20px;">
					<input type="text" class="put" id="logIP" value="${ip }"/>
				</td>
			</tr>
			<tr>
				<td align="right">
					<input type="button" value=" 查询" id="selLog"/>
				</td>
				<td colspan="2" align="left">
					<input type="button" value=" 重置 " id="cancel"/>
				</td>
			</tr>
		</table>
	</div>
	<c:if test="${list !=null }">
	<div id="div2">
		<table width="100%" border="1" align="center" cellpadding="3" cellspacing="0" bordercolor="A4B6D7"
            bgcolor="F2F8FF" class="admin_table">
            <tr>
                <td class="title_03" colspan="8" align="center">
                    <strong><font color="red">系统日志列表</font></strong></td>
            </tr>
             <tr style="font-weight:bold;font-style:italic;">
             	<td>序号</td>
             	<td>id号</td>
             	<td>管理员名</td>
             	<td>日志描述</td>
             	<td>登陆ip</td>
             	<td>登陆系统</td>
             	<td>日志产生时间</td>
             	<td>操作成功失败状态</td>
             </tr>
            <c:forEach items="${list}" var="log" varStatus="s">
            	<tr>
	            	<td>${s.index+1 }</td>
	            	<td>${log.id }</td>
	                <td>${log.name }</td>
	                <td>${log.dsc }</td>
	                <td>${log.ip }</td>
	                <td>${log.system }</td>
	                <td>${log.time }</td>
	                <td><font color="red">${log.status }</font></td>
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
    									<a href="systemSettings/toSelectLogByCondition.do?name=${name }&startDate=${startDate}&endDate=${endDate }&ip=${ip}&index=1" style="margin-right:5px;">首页</a>
										<a href="systemSettings/toSelectLogByCondition.do?name=${name }&startDate=${startDate}&endDate=${endDate }&ip=${ip}&index=${index-1 }" style="margin-right:5px;">上一页</a>
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
	    											<a href="systemSettings/toSelectLogByCondition.do?name=${name }&startDate=${startDate}&endDate=${endDate }&ip=${ip}&index=${s.index }" style="margin-right:5px;">${s.index }</a>
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
	    											<a href="systemSettings/toSelectLogByCondition.do?name=${name }&startDate=${startDate}&endDate=${endDate }&ip=${ip}&index=${s.index }" style="margin-right:5px;">${s.index }</a>
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
    									<a href="systemSettings/toSelectLogByCondition.do?name=${name }&startDate=${startDate}&endDate=${endDate }&ip=${ip}&index=${index+1 }" style="margin-right:5px;">下一页</a>
    									<a href="systemSettings/toSelectLogByCondition.do?name=${name }&startDate=${startDate}&endDate=${endDate }&ip=${ip}&index=${pagecount }" style="margin-right:5px;">尾页</a>
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
	</div>
	</c:if>
</body>
</html>