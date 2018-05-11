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
 .btn{
 	display: inline-block;
    padding: 6px 12px;
    margin-bottom: 0;
    font-size: 14px;
    font-weight: 400;
    line-height: 1.42857143;
    text-align: center;
    white-space: nowrap;
    vertical-align: middle;
    -ms-touch-action: manipulation;
    touch-action: manipulation;
    cursor: pointer;
    -webkit-user-select: none;
    -moz-user-select: none;
    -ms-user-select: none;
    user-select: none;
    background-image: none;
    border: 1px solid transparent;
    border-radius: 4px;
  }
	.btn-info{
	    color: #fff;
	    background-color: #5bc0de;
	    border-color: #46b8da;
	}
	a{
		text-decoration: none;
	}	
</style>
<script src="js/basevalue.js"></script>
<script src="js/jquery-1.11.1.js"></script>
<script>
	$(function(){
		//给确定添加加单击事件
		$("#anpPageIndex_btn").click(function(){
			var name=$('#anpPageIndex_btn').attr('account');
			var jump = $("#anpPageIndex_input").val().trim();
			var pageCount = $("#pagecount").text();
			if(jump != "" && jump != "undefined" && jump <= pageCount){
					window.location.href = "gamePlayer/getAllBindPlayers.do?index=" + jump+ "&account=" + name; 
			}else if(jump > pageCount) {
				alert("跳转页数不能大于总页数哦！")
			}else {
				alert("页数不能为空！")
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
                <td class="title_03" colspan="8" align="center">
                    <strong><font color="red">绑定玩家列表</font></strong></td>
            </tr>
             <tr style="font-weight:bold;font-style:italic;">
<!--              	<td>序号</td> -->
<!--              	<td>id</td> -->
             	<td>代理账号</td>
             	<td>代理房卡信息</td>
             	<td>玩家id</td>
             	<td>代理商id</td>
<!--              	<td>绑定时间</td> -->
<!--              	<td>修改时间</td> -->
             	<td>玩家昵称</td>
             	<td>玩家房卡信息</td>
             	<td>修改</td>
             </tr>
            <c:forEach items="${list }" var="log" varStatus="s">
            	<tr>
<%-- 	            	<td>${s.index+1 }</td> --%>
<%-- 	                <td>${log.id }</td> --%>
	                <td>${log.account }</td>
	                <td>${log.roomCard }</td>
	                <td>${log.gameId }</td>
	                <td>${log.userID }</td>
<%-- 	                <td>${log.bindingTime }</td> --%>
<%-- 	                <td>${log.modifyTime }</td> --%>
	                <td>${log.pNickName }</td>
	                <td>${log.playerRoomCard }</td>
	                <td>
	                	<a class="btn btn-info" href="javascript:void(0)" account="${log.account}" gameId="${log.gameId}" onclick="doUnbind(this)">解除绑定</a>
	                </td>
	                </tr>
            </c:forEach>
            <tr>
                <td colspan='8' align='right'>
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
    									<a href="gamePlayer/getAllBindPlayers.do?index=1&account=${account}" style="margin-right:5px;">首页</a>
										<a href="gamePlayer/getAllBindPlayers.do?index=${index-1}&account=${account }" style="margin-right:5px;">上一页</a>
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
	    											<a href="gamePlayer/getAllBindPlayers.do?index=${s.index }&account=${account }" style="margin-right:5px;">${s.index }</a>
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
	    											<a href="gamePlayer/getAllBindPlayers.do?index=${s.index }&name=${account }" style="margin-right:5px;">${s.index }</a>
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
    									<a href="gamePlayer/getAllBindPlayers.do?index=${index+1 }&account=${account }" style="margin-right:5px;">下一页</a>
    									<a href="gamePlayer/getAllBindPlayers.do?index=${pagecount }&account=${account }" style="margin-right:5px;">尾页</a>
    								</c:otherwise> 
								</c:choose>
								
								&nbsp;&nbsp;转到<input type="text"  name="anpPageIndex_input" id="anpPageIndex_input" style="width:30px;" />
								页<input type="button" value="Go" account="${account}" name="anpPageIndex" id="anpPageIndex_btn" />
							</div>
						</div>
	                </div>
                </td>
            </tr>
        </table>
    </form>
</body>
<script>

	
	function doUnbind(obj){
		var account = $(obj).attr('account');
		var gameId  = $(obj).attr('gameId');
		var promocode = {
				account : account,
				gameId  : gameId
		}
		console.log(promocode)
		if(account != "" && account != "undefined" && gameId != "" && gameId != "undefined"){
			$.ajax({
				type:"post",
				url :"gamePlayer/unbindPlayer.do",
				data:promocode,
				success:function(data){
					console.log(data);
					if(data.status == 1){
						alert(data.msg);
						location.href="gamePlayer/getAllBindPlayers.do?account=" + account + "&index=1";
					}
				}
			})
		}
	}

</script>

</html>