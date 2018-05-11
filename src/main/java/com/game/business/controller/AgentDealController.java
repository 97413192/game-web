package com.game.business.controller;


import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.game.business.model.Agent;
import com.game.business.service.AgentDealService;
import com.game.business.service.AgentService;

@Controller
@RequestMapping("/agentDeal")
public class AgentDealController {
	@Resource
	private AgentDealService agentDealService;
	
	
	@RequestMapping("/findAll.do")
	public String findAllAgentDealMessage(HttpServletRequest req) throws ServletException, IOException{
		return agentDealService.findAllAgentDealMessage(req);
	}
	/**
	 * 获取数据修改数据库，保存到数据交易记录表
	 */
	@RequestMapping("/sellRoomCard.do")
	@ResponseBody
	public String sellRoomCard(HttpServletRequest req,String isAOA,String payeeAccount,String sellMoney,String sellNum,String sellReason) throws ServletException, IOException{
		
		return agentDealService.insertAgentDealRecord(req,isAOA,payeeAccount,sellMoney,sellNum,sellReason);
	}
	/**
	 * 
	 * @param req
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping("/toAgentDealSelect.do")
	public String toAgentDealSelect(HttpServletRequest req) throws ServletException, IOException{
		
		return agentDealService.toAgentDealSelect(req);
	}
	@RequestMapping("/toSelect.do")
	public String toSelect(HttpServletRequest req,HttpServletResponse res,String remitterAccount,String payeeAccount,String startDate,String endDate,String index)throws ServletException, IOException{
		
		return agentDealService.toSelect(req,res,remitterAccount,payeeAccount,startDate,endDate,index);
	}
}
