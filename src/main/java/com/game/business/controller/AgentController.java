package com.game.business.controller;


import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.game.base.util.Result;
import com.game.business.model.Agent;
import com.game.business.service.AgentService;

@Controller
@RequestMapping("/agent")
public class AgentController {
	@Resource
	private AgentService agentService;
	
	/**
	 * 查询所有代理商
	 * @param req
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping("/findAll.do")
	public String findAllAgentMessage(HttpServletRequest req) throws ServletException, IOException{
		return agentService.findAllAgentMessage(req);
	}
	
	/**
	 * 通过账号查询代理商
	 * @param req
	 * @param account
	 * @param judge
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping("/look.do")
	public String findAgentByAccount(HttpServletRequest req,String account) throws ServletException, IOException{
		return agentService.findAgentByAccount(req,account);
	}
	
	/**
	 * 修改代理商
	 * @param req
	 * @param res
	 * @param userID
	 * @param alterDT
	 * @param alterDN
	 * @param alterBN
	 * @param alterBCN
	 * @param alterRealName
	 * @param alterCPN
	 * @param alterEmail
	 * @param alterState
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping("/operation.do")
	@ResponseBody
	public Result operationAgentMSG(HttpServletRequest req,HttpServletResponse res,String userID,String alterDT,String alterDN,String alterBN,String alterBCN,
			String alterRealName,String alterCPN,String alterEmail,String alterState,String password) throws ServletException, IOException{
		return agentService.operationAgentMSG(req,res,userID,alterDT,alterDN,alterBN,alterBCN,alterRealName,alterCPN,alterEmail,alterState,password);
	}
	/**
	 * 删除代理商
	 * @param req
	 * @param res
	 * @param account
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping("/delete.do")
	@ResponseBody
	public Result deleteAgent(HttpServletRequest req,HttpServletResponse res,String account) throws ServletException, IOException{
		System.out.println("删除代理商!");
		return agentService.deleteAgent(req,res,account);
	}
	/**
	 * 增加代理商页面
	 * @param req
	 * @param res
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping("/toAddAgent.do")
	public String addAgent(HttpServletRequest req,HttpServletResponse res) throws ServletException, IOException{
		return agentService.addAgent(req,res);
	}
	
	/**
	 * 增加代理商
	 * @param req
	 * @param res
	 * @param agent
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping("/save.do")
	@ResponseBody
	public Result saveAgent(HttpServletRequest req) throws ServletException, IOException{
		return agentService.saveAgent(req);
	}
	/**
	 * 
	 * @param message
	 * @return
	 */
	@RequestMapping("/checkMSG.do")
	@ResponseBody
	public String checkMSG(String message){
		
		return agentService.checkMSG(message);
	}
	
	/**
	 * 
	 * @param req
	 * @param startDate
	 * @param endDate
	 * @param index
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping("/findAllAgentLog.do")
	public String findAllAgentLog(HttpServletRequest req,String startDate,String endDate,String index) throws ServletException, IOException{
		System.out.println(startDate+","+endDate+","+index);
		return agentService.findAllAgentLog(req,startDate,endDate,index);
	}
	/**
	 * 
	 * @param req
	 * @param res
	 * @param startDate
	 * @param endDate
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping("/deleteAgentLogByDate.do")
	public String deleteAgentLogByDate(HttpServletRequest req,HttpServletResponse res,String startDate,String endDate) throws ServletException, IOException{
		
		return agentService.deleteAgentLogByDate(req,res,startDate,endDate);
	}
	
	/**
	 * 游戏客户端绑定推广码
	 */
	@RequestMapping("/promoCode.do")
	@ResponseBody
	public Result promoCode(HttpServletRequest req) throws ServletException, IOException{
		System.out.println("gameId"+req.getParameter("gameId"));
		return agentService.promoCode(req);
	}

	/**
	 * 判断游戏客户端是否绑定推广码
	 */
	@RequestMapping("/judgePromoCode.do")
	@ResponseBody
	public Result judgePromoCode(HttpServletRequest req) throws ServletException, IOException{
		return agentService.judgePromoCode(req);
	}
	/**
	 * 给代理商充值房卡
	 * 查询代理商信息页面
	 * @param req
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping("/selectAengtPage.do")
	public String selectAengtPage(HttpServletRequest req) throws ServletException, IOException{
		return agentService.selectAengtPage(req);
	}
	
	/**
	 * 给代理商充值房卡
	 * 查询代理商信息
	 * @param req
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping("/selectAengt.do")
	@ResponseBody
	public Result selectAengt(HttpServletRequest req) throws ServletException, IOException{
		
		return agentService.selectAengt(req);
	}
	
	/**
	 * 代理商增加页面
	 * @param req
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping("/toAddRoomCard.do")
	public String toAddRoomCard(HttpServletRequest req,String account,Integer userID,Integer roomCard) throws ServletException, IOException{
		//System.out.println("进来!");
		return agentService.toAddRoomCard(req,account,userID,roomCard);
	}
	
	@RequestMapping("/updateRebate.do")
	@ResponseBody
	public Result updateRebate(HttpServletRequest req) throws ServletException, IOException{
		return agentService.updateRebate(req);
	}
	
	@RequestMapping("/toUpdateRebate.do")
	public String toUpdateRebate(HttpServletRequest req) throws ServletException, IOException{
		return agentService.toUpdateRebate(req);
	}
	
	/**
	 * 给代理商充值房卡
	 * @param req
	 * @param userID
	 * @param addRoomCard
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping("/addRoomCard.do")
	@ResponseBody
	public Result addRoomCard(HttpServletRequest req, Integer userID, Integer addRoomCard,
			String sell,String reason) throws ServletException, IOException{
		return agentService.addRoomCard(req,userID,addRoomCard,sell,reason);
	}
	
	 /**请返水解散页面*/
	@RequestMapping("/toAgentDis.do")
	public String toAgentDis(HttpServletRequest req) throws ServletException, IOException{
		return agentService.toAgentDis(req);
	}
	
	/**查询某个代理下特定时间段下玩家的充值记录*/
	@RequestMapping("/toSelectAgentDis.do")
	public String toSelectAgentDis(HttpServletRequest req,String name,String startDate,String endDate, Integer index) throws ServletException, IOException{
		return agentService.toSelectAgentDis(req,name,startDate,endDate,index);
	}

	/**管理查询代理赚水*/
	@RequestMapping("/toAgentEarn.do")
	public String toAgentEarn(HttpServletRequest req) throws ServletException, IOException{
		return agentService.toAgentEarn(req);
	}

	/**管理查询代理赚水*/
	@RequestMapping("/toAgentEarnData.do")
	public String toAgentEarnData(HttpServletRequest req,String start,String end,Integer userId) throws ServletException, IOException{
		return agentService.toAgentEarnData(req,start,end,userId);
	}
	
	/**
	 *  绑定邀请码
	 * @param   req
	 * @param   gameId  游戏id
	 * @param   agentId 代理商id
	 * @return      
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping("tobindAgent.do")
	@ResponseBody
	public Result bindAgent(HttpServletRequest req ,Integer gameId,Integer agentId) throws ServletException, IOException {
		
		System.out.println("绑定请求到达后台-----------------------------------------------------------------------------------------");
		return agentService.bindAgent(req,gameId,agentId);
	}
	
}
