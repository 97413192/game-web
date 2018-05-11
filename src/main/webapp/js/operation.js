/*
 * 代理商修改或者增加验证
 */
function addAccount(){
	var account= $.trim($("#account").val());
	if(account == null || account == ''){
		$("#accountMSG").html("新增用户不能为空");
		return false;
	}
	var url = "agent/checkMSG.do";
	var data = {message:account};
	$.getJSON(url,data,function(result){
		if(result == "ERROR"){
			$("#accountMSG").html("账户名已存在！！");
			return false;
		}
	});
	$("#accountMSG").html("");
	return true;
}
function addRoomCard(){
	var roomCard= $.trim($("#roomCard").val());
	if(roomCard == null || roomCard == ''){
		$("#roomCardMSG").html("新增房卡不能为空");
		return false;
	}
	var reg = /^[1-9]\d*$/;
	if(! reg.test(roomCard)){
		$("#roomCardMSG").html("房卡只能是数字");
		return false;
	}
	$("#roomCardMSG").html("");
	return true;
}
function checkDT(){
	var alterDT= $.trim($("#alterDT").val());
	if(alterDT == null || alterDT == ''){
		$("#alterDTMSG").html("证件类型不能为空");
		return false;
	}
	$("#alterDTMSG").html("");
	return true;
}
function checkDN(){
	var alterDN = $.trim($("#alterDN").val());
	if(alterDN == null || alterDN == ''){
		$("#alterDNMSG").html("证件号码不能为空");
		return false;
	}
	$("#alterDNMSG").html("");
	return true;
}
function checkBN(){
	var alterBN = $.trim($("#alterBN").val());
	if(alterBN == null || alterBN == ''){
		$("#alterBNMSG").html("银行名称不能为空");
		return false;
	}
	$("#alterBNMSG").html("");
	return true;
}
function checkBCN(){
	var alterBCN =$.trim($("#alterBCN").val());
	if(alterBCN == null || alterBCN == ''){
		$("#alterBCNMSG").html("银行卡号不能为空");
		return false;
	}
	var reg = /^[1-9]\d+$/;
	if(! reg.test(alterCPN)){
		$("#alterBCNMSG").html("银行卡号格式不对");
		return false;
	}
	$("#alterBCNMSG").html("");
	return true;
}
function checkRealName(){
	var alterRealName = $.trim($("#alterRealName").val());
	if(alterRealName == null || alterRealName == ''){
		$("#alterRealNameMSG").html("真实姓名不能为空");
		return false;
	}
	$("#alterRealNameMSG").html("");
	return true;
}
function checkCPN(){
	var alterCPN = $.trim($("#alterCPN").val());
	if(alterCPN == null || alterCPN == ''){
		$("#alterCPNMSG").html("电话号码不能为空");
		return false;
	}
	var reg = /^[1-9]\d+$/;
	if(! reg.test(alterCPN)){
		$("#alterCPNMSG").html("电话号码格式不对");
		return false;
	}
	$("#alterCPNMSG").html("");
	return true;
}
function checkEmail(){
	var alterEmail =$.trim($("#alterEmail").val());
	if(alterEmail == null || alterEmail == ''){
		$("#alterEmailMSG").html("邮箱不能为空");
		return false;
	}
	$("#alterEmailMSG").html("");
	return true;
}
/*
 * 删除
 */
$(function(){
	$('#deleteMSG').click(deleteMSG);
});
function deleteMSG(){
	var userID = $('#userID').html();
	var account = $("#account").html();
	var url = "agent/delete.do";
	var data = {account:account};
	$.getJSON(url,data,function(result){
		if(result == "SUCCESS"){
			window.location.href="agent/findAll.do?index=-1";
			return;
		}
		if(result == "ERROR"){
			var r=confirm("删除失败");
			if(r){
				window.location.href="agent/findAll.do?index=-1";
			}else{
				window.location.href="agent/look.do?account="+account;
			}
			return;
		}else{
			window.location.href="login/toLogin.do";
		}
	});
}
/*
 * 新增代理
 */
$(function(){
	//点击取消单击事件
	$('#cancel').click(clickCancel);
});
function clickCancel(){
	window.location.href="agent/findAll.do?index=-1";
}
function checkForm(){
	var num = addAccount()+addRoomCard()/*+checkDT()+checkDN()+checkBN()+checkBCN()+checkRealName()+checkCPN()+checkEmail()*/;
	/*if(num != 9){
		var conf = confirm("输入信息有误");
		return false;
	}*/
	if(num != 2){
		var conf = confirm("输入信息有误");
		return false;
	}
	return true;
}
/*
 * 出售房卡
 */
var model = {};//用来装出售房卡的信息
$(function(){
	//出售数量
	//操作人是代理
	$('#sellNumOne').blur(checkSellNumOne);
	//操作人是管理员
	$('#sellNumTwo').blur(checkSellNumTwo);
	//出售金额
	$('#sellMoney').blur(checkSellMoney);
	//出售原因
	$('#sellReason').blur(checkSellReason);
	
	$('#affirmSell').click(affirmSell);
	//取消按钮
	$('#cancelSell').click(cancelSell);
	
	$('#sellCard').click(jumpPage);
	
});
function jumpPage(){
	$('#div1').hide();
	$('#div2')[0].style.display = "";
}
function checkSellNumOne(){
	var myRoomCardNum = $('#myRoomCardNum').html();
	var sellNum = $.trim($('#sellNumOne').val());
	if(sellNum == null || sellNum == ""){
		$('#sellNumOneMSG').html('不能为空');
		return false;
	}
	var reg = /^[1-9]\d*$/;
	if(isNaN(sellNum)){
		$('#sellNumOneMSG').html('必须为数字');
		return false;
	}
	if(! reg.test(sellNum)){
		$('#sellNumOneMSG').html('必须为正数');
		return false;
	}
	if(sellNum-myRoomCardNum>0){
		$('#sellNumOneMSG').html('你的房卡数不足');
		return false;
	}
	$('#sellNumOneMSG').html('');
	model.isAOA = "agent";
	model.sellNum = sellNum;
	return true;
}
function checkSellNumTwo(){
	var myRoomCardNum = $('#myRoomCardNum').html();
	var sellNum = $.trim($('#sellNumTwo').val());
	if(sellNum == null || sellNum == ""){
		$('#sellNumTwoMSG').html('不能为空');
		return false;
	}
	if(isNaN(sellNum)){
		$('#sellNumTwoMSG').html('必须为数字');
		return false;
	}
	if(sellNum == 0){
		$('#sellNumTwoMSG').html('不能为0');
		return false;
	}
	if(sellNum+myRoomCardNum<0){
		$('#sellNumTwoMSG').html('对方余额不足');
		return false;
	}
	$('#sellNumOneMSG').html('');
	model.isAOA = "admin";
	model.sellNum = sellNum;
	$('#sellNumTwoMSG').html('');
	return true;
}
function checkSellMoney(){
	var sellMoney = $.trim($('#sellMoney').val());
	if(sellMoney == null || sellMoney == ""){
		$('#sellMoneyMSG').html('不能为空');
		return false;
	}
	if(isNaN(sellMoney) || sellMoney<0){
		$('#sellMoneyMSG').html('必须为数字且要大于等于0');
		return false;
	}
	$('#sellMoneyMSG').html('');
	model.sellMoney = sellMoney;
	return true;
}
function checkSellReason(){
	var sellReason = $.trim($('#sellReason').val());
	if(sellReason == null || sellReason == ""){
		$('#sellReasonMSG').html('不能为空');
		return false;
	}
	$('#sellReasonMSG').html('');
	model.sellReason = sellReason;
	return true;
}
function affirmSell(){
	var num = checkSellNumOne()+checkSellNumTwo()+checkSellMoney()+checkSellReason();
	if(num != 3){
		confirm('输入信息有误');
		return;
	}
	model.payeeAccount = $('#account').html();
	var url = "agentDeal/sellRoomCard.do";
	var data = model;
	$.getJSON(url,data,function(result){
		if(result == "SUCCESS"){
			$("#alterMSG").click();
		}else{
			confirm("修改失败");
		}
	});
}
function cancelSell(){
	$('#div1').show();
	$('#div2')[0].style.display = "none";
}
/*
 * 代理交易查询
 */
$(function(){
	$('#selectBegin').click(toSelectBegin);
});
function toSelectBegin(){
	var remitterAccount = $.trim($('#raccountImpMsg').val());
	var payeeAccount = $.trim($('#paccountImpMsg').val());
	var startDate = $.trim($('#startDate').val());
	var endDate = $.trim($('#endDate').val());
	var url = "agentDeal/toSelect.do";
	window.location.href="agentDeal/toSelect.do?remitterAccount="+
		remitterAccount+"&payeeAccount="+payeeAccount+"&startDate="+
		startDate+"&endDate="+endDate+"&index=-1";
}