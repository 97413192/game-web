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
<title>代理商管理</title>
<base href="<%=basePath%>">
<link href="css/systemSetttings/inc_style.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
<link href="css/tableCss.css" rel="stylesheet" type="text/css" />
<style>
* {
	margin: 0;
	padding: 0
}

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
			if(!isNaN(jump)){
				if(jump>0 && jump<=${pagecount }){
					window.location.href = "agent/findAll.do?index="+jump;
				}
			}
		});
		//给新增添加单击事件
		$('#toAddAgent').click(function(){
			window.location.href = "agent/toAddAgent.do";
		});
	});
	//查询代理商下级绑定玩家
	function findPlayer(name,num){
		if(num<=0){
			alert("该代理商下级玩家数为0!");
		}else{
			
				window.location.href="gamePlayer/toSelectBindedPlayer.do?name="+name+"&index=-1";
		}
	}
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
    <form name="form1" method="post" action="./AdminModifyLog.aspx?page=10" id="form1">
    <div style="overflow-x : scroll;">
        <table class="imagetable" width="100%" border="1" align="center" cellpadding="3" cellspacing="0" bordercolor="A4B6D7"
            bgcolor="F2F8FF" class="admin_table">
            <tr>
                <td class="title_03" colspan="20" align="center">
                    <strong><font color="red" size="4px">代理商列表</font></strong></td>
            </tr>
             <tr style="font-weight:bold;font-style:italic;">
<!--              	<th>序号</th> -->
             	<th>用户ID</th>
             	<th>账户</th>
             	<th>房卡</th>
             	<th>级别</th>
             	<th>上级代理</th>
             	<th>下级代理数</th>
             	<th>下级玩家数</th>
             	<th>返点比例</td>
<!--              	<td>证件类型</td> -->
<!--              	<td>证件编号</td> -->
<!--              	<td>银行名称</td> -->
<!--              	<td>银行卡号</td> -->
<!--              	<td>真实姓名</td> -->
<!--              	<td>手机号码</td> -->
<!--              	<td>邮箱</td> -->
<!--              	<th>注册时间</th> -->
<!--              	<th>登录次数</th> -->
<!--              	<th>最后登录IP</th> -->
<!--              	<th>最后登录时间</th> -->
<!--              	<th>状态</th>            	 -->
             </tr>
            <c:forEach items="${list }" var="log" varStatus="s">
            	<tr style="cursor:hand " onmousemove="changeTrColor(this)">
<%-- 	            	<td>${s.index+1 }</td> --%>
	                <td>${log.userID }</td>
	                <td><a href="agent/look.do?account=${log.account }">${log.account }</a></td>
	                <td>${log.roomCard }</td>
	                <c:choose>
	                	<c:when test="${log.category==1 }">
	                		<td>一级代理商</td>
	                	</c:when>
	                	<c:when test="${log.category==2 }">
	                		<td>二级代理商</td>
	                	</c:when>
	                	<c:otherwise>
	                		<td>三级代理商</td>
	                	</c:otherwise>
	                </c:choose>
	                <td>${log.higherAgent }</td>
	                <td>${log.lowerAgentNum }</td>
	                <c:choose>
	                	<c:when test="${log.lowerPlayerNum>0 }">
	                		<td><a href="javascript:findPlayer('${log.account }',${log.lowerPlayerNum });">${log.lowerPlayerNum }</a></td>
	                	</c:when>
	                	<c:otherwise>
	                		<td>无</td>
	                	</c:otherwise>
	                </c:choose>
	                
	                <td>${log.rebate }</td>
<%-- 	                <td>${log.documentType }</td> --%>
<%-- 	                <td>${log.documentNumber }</td> --%>
<%-- 	                <td>${log.bankName }</td> --%>
<%-- 	                <td>${log.bankCardNumber }</td> --%>
<%-- 	                <td>${log.realName }</td> --%>
<%-- 	                <td>${log.cellPhoneNumber }</td> --%>
<%-- 	                <td>${log.email }</td> --%>
<%-- 	                <td>${log.regTime }</td> --%>
<%-- 	                <td>${log.loginNumber }</td> --%>
<%-- 	                <td>${log.lastLoginIP }</td> --%>
<%-- 	                <td>${log.lastLoginTime }</td> --%>
<%-- 	                <td>${log.state }</td> --%>
	           </tr>
            </c:forEach>
             
            <tr>
                <td colspan='19' align='right'>
	                <div id="page_link" class="page_link">
		                <div id="anpPageIndex">
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
    									<a href="agent/findAll.do?index=1" style="margin-right:5px;">首页</a>
										<a href="agent/findAll.do?index=${index-1 }" style="margin-right:5px;">上一页</a>
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
	    											<a href="agent/findAll.do?index=${s.index }" style="margin-right:5px;">${s.index }</a>
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
	    											<a href="agent/findAll.do?index=${s.index }" style="margin-right:5px;">${s.index }</a>
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
    									<a href="agent/findAll.do?index=${index+1 }" style="margin-right:5px;">下一页</a>
    									<a href="agent/findAll.do?index=${pagecount }" style="margin-right:5px;">尾页</a>
    								</c:otherwise> 
								</c:choose>
								&nbsp;&nbsp;转到<input type="text"  name="anpPageIndex_input" id="anpPageIndex_input" style="width:30px;" />
								页<input type="button" value="Go" class="btn btn-info" name="anpPageIndex" id="anpPageIndex_btn" />
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