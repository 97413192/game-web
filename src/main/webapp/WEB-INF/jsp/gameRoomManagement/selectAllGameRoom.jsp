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
<title>查询所有房间信息</title>
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
				if(jump>0 && jump<=${roomcount }){
					window.location.href = "gameRoom/toSelectAllGameRoom.do?roomIndex="+jump;
				}
			}
		});	
	});
</script>
</head>
<body>
    <form name="form1" method="post" action="" id="form1">
        <table width="100%" border="1" align="center" cellpadding="3" cellspacing="0" bordercolor="A4B6D7"
            bgcolor="F2F8FF" class="admin_table">
            <tr>
                <td class="title_03" colspan="13" align="center">
                    <strong><font color="red">查询所有房间信息</font></strong></td>
            </tr>
             <tr style="font-weight:bold;font-style:italic;">
             	<td></td>
             	<td>游戏名称</td>
             	<td>房间名称</td>
             	<td>VIP等级</td>
             	<td>进入房间最低金币限制</td>
             	<td>人数上限</td>
             	<td>金币兑换积分比例</td>
             	<td>基础押注分</td>
             	<td>最小押注分</td>
             	<td>最大押注分</td>
             	<td>当前桌子数</td>
             	<td>当前库存</td>
             	<td>修改</td>
             </tr>
            <c:forEach items="${roomList}" var="list" varStatus="s">
            	<tr>
            		<td>${s.index+1 }</td>
	                <td>${list.get(0) }</td>
	                <td>${list.get(1).name }</td>
	                <td>${list.get(1).vipLimit }</td>
	                <td>${list.get(1).goldLimit }</td>
	                <td>${list.get(1).peopleNumLimit }</td>
	                <td>${list.get(1).creditRate }</td>
	                <td>${list.get(1).betBaseScore }</td>
	                <td>${list.get(1).betMinScore }</td>
	                <td>${list.get(1).betMaxScore }</td>
	                <td></td>
	                <td></td>
	                <td><a href="gameRoom/toAlterGameRoom.do?gameId=${list.get(0) }&name=${list.get(1).name }vipLimit=${list.get(1).vipLimit }&goldLimit=${list.get(1).goldLimit }&peopleNumLimit=${list.get(1).peopleNumLimit }&creditRate=${list.get(1).creditRate }&betBaseScore=${list.get(1).betBaseScore }&betMinScore=${list.get(1).betMinScore }&betMaxScore=${list.get(1).betMaxScore }">修改</a></td>
	            </tr>
            </c:forEach>
             
            <tr>
                <td colspan='13' align='right'>
	                <div id="page_link" class="page_link">
		                <div id="anpPageIndex">
							<div style="width:40%;float:left;">
								第${roomIndex }页，共${roomPagecount }页；每页${roomPagesize }条，共${roomCount }条
								&nbsp; &nbsp;<span style="color:red">${unknow }${logServiceError }${ungetLoginService }</span>
							</div>
							<div style="width:60%;float:left;">
								<c:choose>
									<c:when test="${roomIndex==1 }">
										<span style="margin-right:5px;font-weight:Bold;color:red;">首页</span>
										<span style="margin-right:5px;font-weight:Bold;color:red;">上一页</span>
									</c:when> 
    								<c:otherwise>
    									<a href="gameRoom/toSelectAllGameRoom.do?roomIndex=1" style="margin-right:5px;">首页</a>
										<a href="gameRoom/toSelectAllGameRoom.do?roomIndex=${roomIndex-1 }" style="margin-right:5px;">上一页</a>
    								</c:otherwise> 
								</c:choose> 
								<c:choose>
									<c:when test="${roomPagecount<=10 }">
										<c:forEach begin="1" end="${roomPagecount }" varStatus="s">
											<c:choose>
												<c:when test="${roomIndex == s.index }">
													<span style="margin-right:5px;font-weight:Bold;color:red;">${s.index }</span>
												</c:when> 
	    										<c:otherwise>
	    											<a href="gameRoom/toSelectAllGameRoom.do?roomIndex=${s.index }" style="margin-right:5px;">${s.index }</a>
	    										</c:otherwise> 
											</c:choose>
										</c:forEach>
									</c:when>
									<c:otherwise>
										<!-- 当总页数减去当前页数不足十页时怎样显示 -->
										<c:forEach begin="${roomIndex>roomPagecount-10?roomPagecount-9:roomIndex }" end="${roomIndex>roomPagecount-10?roomPagecount:roomIndex+9 }" varStatus="s">
											<c:choose>
												<c:when test="${roomIndex == s.index }">
													<span style="margin-right:5px;font-weight:Bold;color:red;">${s.index }</span>
												</c:when> 
	    										<c:otherwise>
	    											<a href="gameRoom/toSelectAllGameRoom.do?roomIndex=${s.index }" style="margin-right:5px;">${s.index }</a>
	    										</c:otherwise> 
											</c:choose>
										</c:forEach>
									</c:otherwise>
								</c:choose>
								<c:choose>
									<c:when test="${roomPagecount == roomIndex }">
										<span style="margin-right:5px;font-weight:Bold;color:red;">下一页</span>
										<span style="margin-right:5px;font-weight:Bold;color:red;">尾页</span>
									</c:when> 
    								<c:otherwise>
    									<a href="gameRoom/toSelectAllGameRoom.do?roomIndex=${roomIndex+1 }" style="margin-right:5px;">下一页</a>
    									<a href="gameRoom/toSelectAllGameRoom.do?roomIndex=${roomPagecount }" style="margin-right:5px;">尾页</a>
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