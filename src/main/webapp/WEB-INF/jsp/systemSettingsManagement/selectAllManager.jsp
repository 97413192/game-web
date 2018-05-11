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
<title>所有管理员信息</title>
<base href="<%=basePath%>">
<link href="css/systemSetttings/inc_style.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
<link href="css/tableCss.css" rel="stylesheet" type="text/css" />
<style>
	body {
		margin-top: 0px;
	}
	table{
		overflow:scroll
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
				var pagecount=$("#pagecount").value();
				if(jump>0 && jump<=pagecount){
					window.location.href = "systemSettings/selectAllManager.do?index="+jump;
				}
			}
		});	
	});
</script>
</head>
<body>
    <form name="form1" method="post" action="./AdminModifyLog.aspx?page=10" id="form1">
    <div style="overflow-x : scroll;">
        <table class="imagetable" width="100%" border="1" align="center" cellpadding="3" cellspacing="0" bordercolor="A4B6D7"
            bgcolor="F2F8FF" class="admin_table">
            <tr >
                <td class="title_03" colspan="17" align="center">
                    <strong><font color="red">管理员列表</font></strong></td>
            </tr>
             <tr style="font-weight:bold;font-style:italic;">
<!--              	<th>序号</th> -->
             	<th>管理员id</th>
             	<th>名字</th>
<!--              	<td>密码</th> -->
<!--              	<th>权限</th> -->
<!--              	<th>创建时ip</th> -->
<!--              	<th>创建时的系统</th> -->
<!--              	<th>创建时间</th> -->
<!--              	<th>上次登录时间</th> -->
<!--              	<th>上次登录ip</th> -->
<!--              	<th>上次修改时间</th> -->
<!--              	<td>本次在线时间</td> -->
<!--              	<td>累积在线时间</td> -->
             	<th>类别</th>
<!--              	<th>状态</th> -->
             	<th>登录次数</th>
             	<th>修改</th>
             </tr>
            <c:forEach items="${list }" var="log" varStatus="s">
            	<tr>
<%-- 	            	<td>${s.index+1 }</td> --%>
	                <td>${log.id }</td>
	                <td>${log.name }</td>
<%-- 	            <td>${log.password }</td> --%>
<%-- 	                <td>${log.power }</td> --%>
<%-- 	                <td>${log.ip }</td> --%>
<%-- 	                <td>${log.system }</td> --%>
<%-- 	                <td>${log.time }</td> --%>
<%-- 	                <td>${log.lastLoginTime }</td> --%>
<%-- 	                <td>${log.lastLoginIp }</td> --%>
<%-- 	                <td>${log.modifyTime }</td> --%>
<%-- 	                <td>${log.onlineTime }</td> --%>
<%-- 	                <td>${log.heapOnlineTime }</td> --%>
	                <c:choose>
						<c:when test="${log.category==1 }">
                 			<td><font color="red">超级管理员</font></td>
                 		</c:when> 
 						<c:otherwise>
 							<td>普通管理员</td>
 						</c:otherwise> 
					</c:choose>
<%-- 	                <c:choose> --%>
<%-- 						<c:when test="${log.status==1 }"> --%>
<!--                  			<td>在线</td> -->
<%--                  		</c:when>  --%>
<%--                  		<c:when test="${log.status==2 }"> --%>
<!--                  			<td>冻结</td> -->
<%--                  		</c:when>  --%>
<%--  						<c:otherwise> --%>
<!--  							<td><font color="red">离线</font></td> -->
<%--  						</c:otherwise>  --%>
<%-- 					</c:choose> --%>
	                <td>${log.loginTimes }</td>
	                <td>
	                	<a class="btn btn-success" onclick="javascript:if(confirm('是否前往管理员修改页面？')){return true;}return false;" href="systemSettings/selectManager.do?id=${log.id }&name=${log.name }&password=${log.password }&power=${log.power }&ip=${log.ip }&system=${log.system }&time=${log.time }&category=${log.category }&status=${log.status }">修改</a>
	                </td>
	                </tr>
            </c:forEach>
             <tr>
             	<td colspan='17' align='left'>
             		<font color="red">备注</font>:权限段中,1表示允许信息发布,2表示允许系统设置,3表示允许创建一级代理,4表示允许删除一级代理,5表示允许出售房卡,6表示允许修改登录密码
             	</td>
             </tr>
            <tr>
                <td colspan='17' align='right'>
	                <div id="page_link" class="page_link">
		                <div id="anpPageIndex">
							<div style="width:40%;float:left;">
								第${index }页，共<spen id="pagecount">${pagecount }</spen>页；每页${pagesize }条，共${count }条
							</div>
							<div style="width:60%;float:left;">
								<c:choose>
									<c:when test="${index==1 }">
										<span style="margin-right:5px;font-weight:Bold;color:red;">首页</span>
										<span style="margin-right:5px;font-weight:Bold;color:red;">上一页</span>
									</c:when> 
    								<c:otherwise>
    									<a href="systemSettings/selectAllManager.do?index=1" style="margin-right:5px;">首页</a>
										<a href="systemSettings/selectAllManager.do?index=${index-1 }" style="margin-right:5px;">上一页</a>
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
	    											<a href="systemSettings/selectAllManager.do?index=${s.index }" style="margin-right:5px;">${s.index }</a>
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
	    											<a href="systemSettings/selectAllManager.do?index=${s.index }" style="margin-right:5px;">${s.index }</a>
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
    									<a href="systemSettings/selectAllManager.do?index=${index+1 }" style="margin-right:5px;">下一页</a>
    									<a href="systemSettings/selectAllManager.do?index=${pagecount }" style="margin-right:5px;">尾页</a>
    								</c:otherwise> 
								</c:choose>
								
								&nbsp;&nbsp;转到<input type="text"  name="anpPageIndex_input" id="anpPageIndex_input" style="width:30px;" />
								页<input type="button" class="btn btn-success" value="Go" name="anpPageIndex" id="anpPageIndex_btn" />
							</div>
						</div>
	                </div>
                </td>
            </tr>
        </table>
        </div>
    </form>
</body>

</html>