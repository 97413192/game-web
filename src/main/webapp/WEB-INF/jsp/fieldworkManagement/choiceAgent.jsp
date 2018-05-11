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
	padding: 0;
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
	function changeTrColor(obj){
		 var _table=obj.parentNode;
		 for (var i=0;i<_table.rows.length;i++){
		 	_table.rows[i].style.backgroundColor="";
		 }    
		 obj.style.backgroundColor="#F5DEB3";
	}
	function lookMessage(obj){
		var mes = $(obj).parent().prev().text();
		alert("出售原因:"+mes);
		/* $('#table1').hide();
		$('#seeReason')[0].style.display = "";
		$('#seeReason div').html(mes); */
	}
	function returnTable(){
		$('#table1').show();
		$('#seeReason')[0].style.display = "none";
		$('#seeReason div').html("");
	}
</script>
</head>
<body>
  
    <table id="table1" class="imagetable" width="100%" border="1" align="center" cellpadding="3" cellspacing="0" bordercolor="A4B6D7"
        bgcolor="F2F8FF" class="admin_table">
        <tr>
            <td class="title_03" colspan="19" align="center">
                <strong><font color="red" size="4px">代理商列表</font></strong></td>
        </tr>
         <tr style="font-weight:bold;font-style:italic;">
         	<th>序号</th>
         	<th>交易ID</th>
         	<th>交易时间</th>
         	<th>汇款人账户</th>
         	<th>收款人账户</th>
         	<th>交易类型</th>
         	<th>汇款人房卡</th>
         	<th>收款人房卡</th>
         	<th>操作IP</th>
         	<th>出售数量</th>
         	<th>出售金额</th>
         	<th>出售原因</th>
         </tr>
        <c:forEach items="${list }" var="log" varStatus="s">
        	<tr style="cursor:hand " onmousemove="changeTrColor(this)">
	         	<td>${s.index+1 }</td>
	             <td>${log.agentDealID }</td>
	             <td>${log.dealHour }</td>
	             <td>${log.remitterAccount }</td>
	             <td>${log.payeeAccount }</td>
	             <td>${log.dealType }</td>
	             <td>${log.remitterRoomCard }</td>
	             <td>${log.payeeRoomCard }</td>
	             <td>${log.operateIP }</td>
	             <td>${log.sellNumber }</td>
	             <td>${log.sellMoney }</td>
	             <td style="display:none">${log.sellReason }</td>
	             <td ><input type="button" class="btn btn-info" value="查看" onclick="lookMessage(this)"></td>
        	</tr>
        </c:forEach>
         
        <tr>
            <td colspan='12' align='right'>
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
				页<input type="button" value="Go" name="anpPageIndex" id="anpPageIndex_btn" />
			</div>
		</div>
             </div>
            </td>
        </tr>
    </table>
   
    <div id="seeReason" style="display: none;border:1px solid #000">
    	<div style="border:1px solid #000;font-size:18px">
    		
    	</div>
    	<input type="button" value="确定" onclick="returnTable()"/>
    </div>
</body>

</html>