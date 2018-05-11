package com.game.business.service;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.game.base.util.BeanFactory;
import com.game.base.util.Code;
import com.game.base.util.GetAdmin;
import com.game.base.util.GetData;
import com.game.base.util.JudgeAuthority;
import com.game.business.mapper.AgentDealMapper;
import com.game.business.mapper.AgentMapper;
import com.game.business.mapper.LogMapper;
import com.game.business.mapper.MgerMapper;
import com.game.business.model.Agent;
import com.game.business.model.AgentDeal;
import com.game.business.model.manager.ManagerLog;
@Service
@Transactional
public class AgentDealServiceImpl implements AgentDealService {
	@Resource
	private AgentDealMapper agentDealMapper;
	@Resource
	private AgentMapper agentMapper;
	@Resource
	private LogMapper logMapper;
	@Resource
	private MgerMapper mgerMapper;
	
	Map<String,Object> map = new HashMap<String,Object>();
	/**
	 * 根据账户查询代理交易记录信息
	 */
	public String findAllAgentDealMessage(HttpServletRequest req) throws ServletException, IOException {
		String admin = GetAdmin.getAdmin(req);  			//获取当前管理员名
		GetData getData = (GetData) BeanFactory.getBean(GetData.class);
		String playerIndex = (String)req.getParameter("index");
		
		//判断当前管理员是否超时
		if(admin == null){
			//保存日志
			getData.saveCurrentLog(req,admin+Code.SELECT_ALL_OUT_TIME,ManagerLog.error);
			
			//返回结果
			return "login/login";
		}
		try{
			Map<String,Object> mapJudge = JudgeAuthority.judgeAuthority(mgerMapper, agentMapper, admin);
			String condition = null;
			if(mapJudge == null){
				return "login/login";
			}
			if(mapJudge.get("judge").equals("agent")){
				condition = admin;
			}
			List<AgentDeal> list = agentDealMapper.findAllAgentDeal(condition);
			int indexpage = Integer.valueOf(playerIndex);
			//判断index是否为第一次请求
			if(indexpage <0){
				indexpage = 1;
			}
			if(list != null){
				int pagesize = 20;  //定义每页显示的记录数
				//加入查询所有记录方法中
				GetData.selectAll(req, list, pagesize, indexpage,
						"count", "index", "pagesize", "pagecount", "list");
				
				//将管理员查询成功存入到日志表中
				getData.saveCurrentLog(req,admin+"查询所有代理交易记录"+"："+ManagerLog.success,ManagerLog.success);
				return "fieldworkManagement/selectAllAgentDeal";
			}else{
				//将管理员查询失败存入到日志表中
				getData.saveCurrentLog(req,admin+"查询所有代理交易记录"+"：失败!",ManagerLog.error);
			}
		}catch(Exception e){
			e.printStackTrace();
			//将管理员查询失败存入到日志表中
			getData.saveCurrentLog(req,admin+"查询所有代理交易记录"+"：由于未知异常查询失败!",ManagerLog.error);
			
		}
		return "login/login";
	}
	/**
	 * 该房卡数据数据，插入记录数据
	 * @throws IOException 
	 * @throws ServletException 
	 */
	public String insertAgentDealRecord(HttpServletRequest req, String isAOA, String payeeAccount, String sellMoney,
			String sellNum, String sellReason) throws ServletException, IOException {
		String admin = GetAdmin.getAdmin(req);  			//获取当前管理员名
		GetData getData = (GetData) BeanFactory.getBean(GetData.class);
		String ip = GetData.getRemoteHost(req);  //获取管理员ip
		
		//判断当前管理员是否超时
		if(admin == null){
			//保存日志
			getData.saveCurrentLog(req, admin+Code.SELECT_ALL_OUT_TIME, ManagerLog.error);
			
			//返回结果
			return "login/login";
		}
		try{
			if(isAOA.equals("agent")){
				Agent sellAgent = agentMapper.findAgentByName(admin);
				sellAgent.setRoomCard(sellAgent.getRoomCard()-Integer.parseInt(sellNum));
				agentMapper.updateRoomCardByAgent(sellAgent);
				map.put("remitterRoomCard", sellAgent.getRoomCard());
			}else{
				map.put("remitterRoomCard", 0);
			}
			Agent payeeAgent = agentMapper.findAgentByName(payeeAccount);
			payeeAgent.setRoomCard(payeeAgent.getRoomCard()+Integer.parseInt(sellNum));
			agentMapper.updateRoomCardByAgent(payeeAgent);
			map.put("dealHour", new Date());
			map.put("remitterAccount", admin);
			map.put("payeeAccount", payeeAccount);
			map.put("dealType", JudgeAuthority.sell);
			map.put("payeeRoomCard", payeeAgent.getRoomCard());
			map.put("operateIP", ip);
			map.put("sellNumber", Integer.parseInt(sellNum));
			map.put("sellMoney", Double.parseDouble(sellMoney));
			map.put("sellReason", sellReason);
			Integer num = agentDealMapper.insertAgentDealRecord(map);
			if(num == 1){
				//将管理员查询成功存入到日志表中
				getData.saveCurrentLog(req, admin+"插入一条代理交易记录"+"："+ManagerLog.success, ManagerLog.success);
				return "SUCCESS";
			}else{
				//将管理员查询失败存入到日志表中
				getData.saveCurrentLog(req, admin+"查询所有代理交易记录"+"：失败!", ManagerLog.error);
			}
		}catch(Exception e){
			e.printStackTrace();
			//将管理员查询失败存入到日志表中
			getData.saveCurrentLog(req, admin+"查询所有代理交易记录"+"：由于未知异常查询失败!", ManagerLog.error);
		}
		return "ERROR";
	}
	
	/**
	 * 查询代理商充值记录
	 */
	public String toAgentDealSelect(HttpServletRequest req) throws ServletException, IOException {
		String admin = GetAdmin.getAdmin(req);  			//获取当前管理员名
		GetData getData = (GetData) BeanFactory.getBean(GetData.class);
		
		//判断当前管理员是否超时
		if(admin == null){
			//保存日志
			getData.saveCurrentLog(req, admin+Code.SELECT_ALL_OUT_TIME, ManagerLog.error);
			
			//返回结果
			return "login/login";
		}
		Map<String,Object> judgeMap = JudgeAuthority.judgeAuthority(mgerMapper, agentMapper, admin);
		req.setAttribute("operatRC", judgeMap.get("judge"));
		req.setAttribute("account", admin);
		return "fieldworkManagement/agentDealSelect";
	}
	/**
	 * 代理查询页面，根据各种条件查询代理充值记录
	 */
	public String toSelect(HttpServletRequest req,HttpServletResponse res, String remitterAccount, String payeeAccount, String startDate,
			String endDate, String index) throws ServletException, IOException {
		String admin = GetAdmin.getAdmin(req);  			//获取当前管理员名
		GetData getData = (GetData) BeanFactory.getBean(GetData.class);
		
		//判断当前管理员是否超时
		if(admin == null){
			//保存日志
			getData.saveCurrentLog(req, admin+Code.SELECT_ALL_OUT_TIME, ManagerLog.error);
			
			//返回结果
			return "login/login";
		}
		try{
			map.put("startDate", startDate);
			map.put("endDate", endDate);
			map.put("remitterAccount", remitterAccount);
			map.put("payeeAccount", payeeAccount);
			List<AgentDeal> list = agentDealMapper.findAllAgentDealByCondition(map);
			int indexpage = Integer.valueOf(index);
			//判断index是否为第一次请求
			if(indexpage <0){
				indexpage = 1;
			}
			int pagesize = 20;  //定义每页显示的记录数
			//加入查询所有记录方法中
			GetData.selectAll(req, list, pagesize, indexpage,
					"count", "index", "pagesize", "pagecount", "list");
			//将管理员查询成功存入到日志表中
			getData.saveCurrentLog(req, admin+"查询所有代理交易记录"+"："+ManagerLog.success, ManagerLog.success);
			
			//查询结果转发到下面这个JSP
			return "fieldworkManagement/choiceAgent";
		}catch(Exception e){
			e.printStackTrace();
			//将管理员查询失败存入到日志表中
			getData.saveCurrentLog(req, admin+"查询所有代理交易记录"+"：由于未知异常查询失败!", ManagerLog.error);
		}
		return "login/login";
	}

}
