package com.game.business.service;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.game.base.util.Result;
import com.game.business.model.Agent;


public interface AgentService {
	/** 代理商列表*/
	String findAllAgentMessage(HttpServletRequest req) throws ServletException, IOException;
	/** 单个代理商信息*/
	String findAgentByAccount(HttpServletRequest req, String account) throws ServletException, IOException;
	/** 修改代理基本信息*/
	public Result operationAgentMSG(HttpServletRequest req, HttpServletResponse res,String userID, String alterDT, String alterDN, String alterBN, String alterBCN,
			String alterRealName, String alterCPN, String alterEmail, String alterState,String password) throws ServletException, IOException;
	/** 根据账户删除代理*/
	Result deleteAgent(HttpServletRequest req, HttpServletResponse res, String account) throws ServletException, IOException;
	/** 到新增代理页面*/
	String addAgent(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException;
	/** 保存新增代理*/
	Result saveAgent(HttpServletRequest req) throws ServletException, IOException;
	//验证信息是否存在
	String checkMSG(String message);
	//查看所有代理日志
	String findAllAgentLog(HttpServletRequest req, String startDate, String endDate,String index) throws ServletException, IOException;
	//根据日期删除代理日志
	String deleteAgentLogByDate(HttpServletRequest req,HttpServletResponse res , String startDate, String endDate) throws ServletException, IOException;
	/** 游戏客户端绑定推广码*/
	public Result promoCode(HttpServletRequest req) throws ServletException, IOException;
	/** 游戏客户端判断是否绑定推广码*/
	public Result judgePromoCode(HttpServletRequest req) throws ServletException, IOException;
	/** 当给代理商充值房卡,请求查询代理商信息页面*/
	public String selectAengtPage(HttpServletRequest req) throws ServletException, IOException;
	/** 当给代理商充值房卡,查询代理商信息*/
	public Result selectAengt(HttpServletRequest req) throws ServletException, IOException;
	/** 代理商增加页面*/
	public String toAddRoomCard(HttpServletRequest req,String account,
			Integer userID,Integer roomCard) throws ServletException, IOException;
	public Result updateRebate(HttpServletRequest req) throws ServletException, IOException;
	public String toUpdateRebate(HttpServletRequest req) throws ServletException, IOException;
	/** 给代理商充值房卡*/
	public Result addRoomCard(HttpServletRequest req, Integer userID, Integer addRoomCard,
			String sell,String reason) throws ServletException, IOException;
	/**请返水解散页面*/
	public String toAgentDis(HttpServletRequest req) throws ServletException, IOException;

	/**查询某个代理下特定时间段下玩家的充值记录*/
	public String toSelectAgentDis(HttpServletRequest req,String name,String startDate,String endDate, Integer index) throws ServletException, IOException;
	/**查询代理赚水*/
	public String toAgentEarn(HttpServletRequest req) throws ServletException, IOException;
	/**管理查询代理赚水*/
	public String toAgentEarnData(HttpServletRequest req,String start,String end,Integer userId) throws ServletException, IOException;
	//绑定推广码
	public Result bindAgent(HttpServletRequest req, Integer gameId, Integer agentId)throws ServletException, IOException;
}
