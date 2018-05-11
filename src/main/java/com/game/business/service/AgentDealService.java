package com.game.business.service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface AgentDealService {
	//代理交易列表
	String findAllAgentDealMessage(HttpServletRequest req) throws ServletException, IOException;
	//插入交易数据，并修改数据库
	String insertAgentDealRecord(HttpServletRequest req, String isAOA, String payeeAccount, String sellMoney,
			String sellNum, String sellReason) throws ServletException, IOException;
	//到代理交易查询页面
	String toAgentDealSelect(HttpServletRequest req) throws ServletException, IOException;
	//验证参数，并根据参数查询
	String toSelect(HttpServletRequest req,HttpServletResponse res, String remitterAccount, String payeeAccount, String startDate,
			String endDate, String index) throws ServletException, IOException;
}
