package com.game.business.service;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.Resource;
import javax.management.remote.rmi.RMIServer;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.game.base.util.BeanFactory;
import com.game.base.util.Code;
import com.game.base.util.GetAdmin;
import com.game.base.util.GetData;
import com.game.base.util.Md5Util;
import com.game.base.util.PowerManagment;
import com.game.base.util.Result;
import com.game.business.mapper.AgentDealMapper;
import com.game.business.mapper.AgentMapper;
import com.game.business.mapper.AgentRebateMapper;
import com.game.business.mapper.LogMapper;
import com.game.business.mapper.MgerMapper;
import com.game.business.mapper.PromoCodeMapper;
import com.game.business.mapper.SingleDataMapper;
import com.game.business.model.Agent;
import com.game.business.model.AgentDeal;
import com.game.business.model.AgentLog;
import com.game.business.model.AgentRebate;
import com.game.business.model.EarnPage;
import com.game.business.model.PromoCode;
import com.game.business.model.manager.Manager;
import com.game.business.model.manager.ManagerLog;
import com.game.cache.CacheGameServer;
import com.game.constant.RMIConstant;
import com.game.game.hallMapper.PromoCode1Mapper;

import cocl.rmi.GameRMIServer;
import sun.util.logging.resources.logging;
@Service
public class AgentServiceImpl implements AgentService {
	Log log = LogFactory.getLog(AgentServiceImpl.class);
	
	@Resource
	private AgentMapper agentMapper;
	@Resource
	private LogMapper logMapper;
	@Resource
	private MgerMapper mgerMapper;
	@Resource
	private PromoCodeMapper promoCodeMapper;
	@Resource
	private AgentDealMapper agentDealMapper;
	@Resource
	private SingleDataMapper singleDataMapper;
	@Resource
	private AgentRebateMapper agentRebateMapper;
	@Resource
	PromoCode1Mapper promoCode1Mapper;
	@Resource
	AgentRebateMapper agentRebate;
	
	Map<String,Object> map = new HashMap<String,Object>();
	SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-DD HH:mm:ss");
	/**
	 * 查询所有代理商信息
	 */
	public String findAllAgentMessage(HttpServletRequest req) throws ServletException, IOException {
		String admin = GetAdmin.getAdmin(req);  			//获取当前操作者名
		GetData getData = (GetData) BeanFactory.getBean(GetData.class);
		String playerIndex = (String)req.getParameter("index");  //获取传入的请求页数
		//判断当前管理员是否超时
		if(admin == null){
			//保存日志
			getData.saveCurrentLog(req,"查询所有代理商:操作者未操作超时!",ManagerLog.error);
			//返回结果
			return "login/login";
		}
		
		try{
			List<Agent> list = null;
			List<Agent> list2 = agentMapper.findAllAgent();
			for(int i=0;i<list2.size();i++){
				List<PromoCode> list3=  promoCodeMapper.selectByAccount(list2.get(i).getAccount());
				Agent a1=new Agent();
				a1.setLowerPlayerNum(list3.size());
				a1.setUserID(list2.get(i).getUserID());
				agentMapper.updateLowerPlayerNum(a1);
			}
			//二级代理商没有权限(系统代理商的最低级数为二级)
			if(PowerManagment.checkOperCategory(admin) == 1){					//管理员
				list = agentMapper.findAllAgent();
			}else if(PowerManagment.checkOperCategory(admin) == 2
					&& PowerManagment.checkAgentCategory(admin) == 1){			//一级代理商
				list = agentMapper.findManyAgentByName(admin);
			}else if(PowerManagment.checkOperCategory(admin) == 2
					&& PowerManagment.checkAgentCategory(admin) == 2){			//二级代理商
				list = agentMapper.findManyAgentByName(admin);
			}else{																//三级级代理商没有权限
				getData.saveCurrentLog(req,"查询所有代理商:操作者没有权限!",ManagerLog.error);
				return "systemSettingsManagement/selectAllManagerNoPower";
			}
			List<AgentRebate> ars=agentRebateMapper.selectAll();
			double rebate1=0;
			double rebate2=0;
			double rebate3=0;
			for(AgentRebate a:ars) {
				if(a.getGrade()==1)
					rebate1=a.getRebate();
				if(a.getGrade()==2)
					rebate2=a.getRebate();
				if(a.getGrade()==3)
					rebate3=a.getRebate();
			}
			for(Agent a:list) {
				if(a.getCategory()==1)
					a.setRebate(rebate1);
				if(a.getCategory()==2)
					a.setRebate(rebate2);
				if(a.getCategory()==3)
					a.setRebate(rebate3);
			}
			
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
				
				//将代理商查询成功存入到日志表中
				getData.saveCurrentLog(req,"查询所有代理商:操作成功!",ManagerLog.success);
				
				
				//req.setAttribute("judge", mapJudge.get("judge"));
				return "fieldworkManagement/selectAllAgentMessage";
			}else{ 
				//将管理员查询失败存入到日志表中
				getData.saveCurrentLog(req,admin+"查询所有代理商"+"：失败!",ManagerLog.error);
			}
		}catch(Exception e){
			e.printStackTrace();
			//将管理员查询失败存入到日志表中
			getData.saveCurrentLog(req,admin+"查询所有代理商"+"：由于未知异常查询失败!",ManagerLog.error);
		}
		return "fieldworkManagement/selectAllAgentMessage";
	}
	
	/**
	 * 根据账户查询代理商信息
	 */
	public String findAgentByAccount(HttpServletRequest req,String account) throws ServletException, IOException {
		String admin = GetAdmin.getAdmin(req);  			//获取当前管理员名
		GetData getData = (GetData) BeanFactory.getBean(GetData.class);
		
		//判断当前管理员是否超时
		if(admin == null){
			//保存日志
			getData.saveCurrentLog(req,"请求代理商修改页面:操作者未操作超时!",ManagerLog.error);
		
			//返回结果
			return "login/login";
		}
		
		try{
			Agent agent = agentMapper.findAgentByName(account);
			
			//判断代理商是否存在
			if(agent == null){
				//保存日志
				getData.saveCurrentLog(req,"请求代理商修改页面:查询代理商不存在!",ManagerLog.success);
			
				//返回结果
				return "login/login";
			}
			
			//操作者是代理商,被操作者必须为一级代理商
			if(PowerManagment.checkOperCategory(admin) == 2){
				//判断操作者是否是查询代理商的上一级,或者操作者是管理员
				if(agent.getHigherAgent().equals(admin)){
					req.setAttribute("agent", agent);
					
					//记录日志
					getData.saveCurrentLog(req,"请求代理商修改页面:操作成功!",ManagerLog.success);
					return "fieldworkManagement/operation";
				}
			}
			
			//如果操作是管理员,被操作者是一件代理商
			if(PowerManagment.checkOperCategory(admin) == 1
					&& PowerManagment.checkAgentCategory(account) == 1){
				req.setAttribute("agent", agent);
				
				//记录日志
				getData.saveCurrentLog(req,"请求代理商修改页面:操作成功!",ManagerLog.success);
				return "fieldworkManagement/operation";
			}
			
			//记录日志
			getData.saveCurrentLog(req,"请求代理商修改页面:操作者没有权限!",ManagerLog.error);
			
			return "systemSettingsManagement/selectAllManagerNoPower";
		}catch(Exception e){
			e.printStackTrace();
			//将管理员查询失败存入到日志表中
			getData.saveCurrentLog(req,"请求代理商修改页面:未知错误!",ManagerLog.error);
		}
		
		//此处需要一单独写一个错误页面!
		return "systemSettingsManagement/selectAllManagerNoPower";
	}
	
	/**
	 * 修改代理商信息
	 * @throws IOException 
	 * @throws ServletException 
	 */
	public Result operationAgentMSG(HttpServletRequest req, HttpServletResponse res, String userID, String alterDT, String alterDN, String alterBN,
			String alterBCN, String alterRealName, String alterCPN, String alterEmail,String alterState,String password) throws ServletException, IOException {
		Result result = new Result();  						//创建返回结果
		String admin = GetAdmin.getAdmin(req);  			//获取当前操作者名
		GetData getData = (GetData) BeanFactory.getBean(GetData.class);
		
		//判断当前管理员是否超时
		if(admin == null){
			//保存日志
			getData.saveCurrentLog(req, "修改代理商信息:操作者未操作超时!", ManagerLog.error);
			
			//返回结果
			result.setStatus(-1);
			return result;
		}
		try{
			//通过id查找代理商
			Agent agent = agentMapper.findAgentById(Integer.valueOf(userID));
			
			//判断权限
			//判断代理商是否存在
			if(agent == null){
				//保存日志
				getData.saveCurrentLog(req,"修改代理商信息:修改的代理商不存在!",ManagerLog.success);
			
				//返回结果
				result.setStatus(1);
				result.setMsg("修改代理商信息:修改的代理商不存在!");
				return result;
			}
			
			//将修改项保存到agent中
			agent.setBankCardNumber(alterBCN);								//银行卡号
			agent.setBankName(alterBN);										//银行名称
			agent.setCellPhoneNumber(alterCPN);;							//手机号码
			agent.setDocumentNumber(alterDN);								//证件号码
			agent.setDocumentType(alterDT);									//证件类型
			agent.setEmail(alterEmail);										//电子邮件
			agent.setState(alterState);										//状态
			agent.setRealName(alterRealName);								//真实姓名
			if(password != null && password != ""){
				//System.out.println("password:"+password);
				agent.setPassword(Md5Util.getMD5(password.getBytes(), true));//密码
			}
			//操作者是代理商,并且被操作者的上级代理商是操作者
			if(PowerManagment.checkOperCategory(admin) == 2){
				//判断操作者是否是查询代理商的上一级,或者操作者是管理员
				if(agent.getHigherAgent().equals(admin)){
					int m = agentMapper.alertAgentById(agent);
					if(m > 0){
						//记录日志
						getData.saveCurrentLog(req,"修改代理商信息:操作成功!",ManagerLog.success);
						
						//返回结果
						result.setStatus(0);
						result.setMsg("修改代理商信息:操作成功!");
						return result;
					}
				}
			}
			
			//如果操作是管理员,被操作者是一件代理商
			if(PowerManagment.checkOperCategory(admin) == 1
					&& PowerManagment.checkAgentCategory(agent.getAccount()) == 1){
				int m = agentMapper.alertAgentById(agent);
				if(m > 0){
					//记录日志
					getData.saveCurrentLog(req,"修改代理商信息:操作成功!",ManagerLog.success);
					
					//返回结果
					result.setStatus(0);
					result.setMsg("修改代理商信息:操作成功!");
					return result;
				}
			}
			
			//将管理员查询失败存入到日志表中
			getData.saveCurrentLog(req,"修改代理商信息:操作失败!",ManagerLog.error);
			
		}catch(Exception e){
			e.printStackTrace();
			//将管理员查询失败存入到日志表中
			getData.saveCurrentLog(req,"修改代理商信息:未知异常!",ManagerLog.error);
			
			//返回结果
			result.setMsg("修改代理商信息:未知异常!");
			result.setStatus(2);
		}
		return result;
	}
	
	/**
	 * 根据账户删除代理
	 */
	public Result deleteAgent(HttpServletRequest req, HttpServletResponse res, String account) throws ServletException, IOException {
		String admin = GetAdmin.getAdmin(req);  			//获取当前管理员名
		GetData getData = (GetData) BeanFactory.getBean(GetData.class);
		Result result = new Result();  						//创建返回结果
		
		//判断当前管理员是否超时
		if(admin == null){
			//保存日志
			getData.saveCurrentLog(req,"删除代理商:操作者未操作超时!",ManagerLog.error);
			
			//返回结果
			result.setStatus(-1);
			return result;
		}
		try{
			Agent agent = agentMapper.findAgentByName(account);
			if(agent != null){
				//操作者是代理商,并且被操作者的上级代理商是操作者
				if(PowerManagment.checkOperCategory(admin) == 2){
					//判断操作者是否是查询代理商的上一级,或者操作者是管理员
					if(agent.getHigherAgent().equals(admin)){
						int m = agentMapper.deleteAgent(agent);
						if(m > 0){
							//记录日志
							getData.saveCurrentLog(req,"删除代理商:操作成功!",ManagerLog.success);
							
							//返回结果
							result.setStatus(0);
							result.setMsg("删除代理商:操作成功!");
							return result;
						}
					}
				}
				
				//如果操作是管理员,被操作者是一件代理商
				if(PowerManagment.checkOperCategory(admin) == 1
						&& PowerManagment.checkAgentCategory(agent.getAccount()) == 1){
					int m = agentMapper.deleteAgent(agent);
					if(m > 0){
						//记录日志
						getData.saveCurrentLog(req,"删除代理商:操作成功!",ManagerLog.success);
						
						//返回结果
						result.setStatus(0);
						result.setMsg("删除代理商:操作成功!");
						return result;
					}
				}
			}else{
				//将管理员删除失败存入到日志表中
				getData.saveCurrentLog(req,"删除代理商:代理商不存在!",ManagerLog.error);
				
				//返回结果
				result.setStatus(1);
				result.setMsg("删除代理商:代理商不存在!");
				return result;
			}
		}catch(Exception e){
			e.printStackTrace();
			
			//将管理员查询失败存入到日志表中
			getData.saveCurrentLog(req,"删除代理商:未知异常!",ManagerLog.error);
			
			//返回结果
			result.setStatus(2);
			result.setMsg("删除代理商:未知异常!");
		}
		return result;
	}
	/**
	 * 到新增代理页面
	 */
	public String addAgent(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String admin = GetAdmin.getAdmin(req);  			//获取当前管理员名
		GetData getData = (GetData) BeanFactory.getBean(GetData.class);
		
		//判断当前管理员是否超时
		if(admin == null){
			//保存日志
			getData.saveCurrentLog(req,"请求增加代理商页面:操作者未操作超时!",ManagerLog.error);
			
			//返回结果
			return "login/login";
		}
		
		
		System.out.println("管理员还是代理商:"+PowerManagment.checkOperCategory(admin));
		System.out.println("代理商层级:"+PowerManagment.checkAgentCategory(admin));
		
		if(PowerManagment.checkOperCategory(admin) == 2
				&& PowerManagment.checkAgentCategory(admin) == 3){
			
			//记录失败
			getData.saveCurrentLog(req,"请求增加代理商页面:没有权限操作失败!", ManagerLog.success);
			
			return "systemSettingsManagement/selectAllManagerNoPower";
		}
		return "fieldworkManagement/addAgent";
	}
	
	
	/**
	 * 新增代理商
	 */
	public Result saveAgent(HttpServletRequest req)
			throws ServletException, IOException {
		Result result = new Result();
		String admin=GetAdmin.getAdmin(req); 
		GetData getData = (GetData) BeanFactory.getBean(GetData.class);
		Agent agent = new Agent();
		String ip = GetData.getRemoteHost(req);
		
		//给代理商添加属性
		agent.setAccount((String)req.getParameter("account"));
		agent.setBankCardNumber((String)req.getParameter("bankCardNumber"));
		agent.setBankName((String)req.getParameter("bankName"));
		agent.setCellPhoneNumber((String)req.getParameter("cellPhoneNumber"));
		agent.setDocumentNumber((String)req.getParameter("documentNumber"));
		agent.setDocumentType((String)req.getParameter("documentType"));
		agent.setEmail((String)req.getParameter("email"));
		agent.setRealName((String)req.getParameter("realName"));
		agent.setRoomCard(0);																//初始房卡为0
		agent.setPassword(Md5Util.getMD5(((String)req.getParameter("password")).getBytes(), true));		
		agent.setState("3");
		agent.setLowerAgentNum(0);															//下级代理初始为0
		agent.setLowerPlayerNum(0);															//下级玩家数为0
		agent.setLoginNumber(0);															//登录次数为0
		agent.setLastLoginIP(ip);
		agent.setHigherAgent(admin);														//上级代理,一级代理商的上级代理是管理员
		
		//判断当前管理员或者代理商是否超时
		if(admin == null){
			//保存日志
			getData.saveCurrentLog(req,"增加代理商:操作者为操作超时!",ManagerLog.error);
			
			//返回结果
			//返回结果
			result.setStatus(-1);
			return result;
		}
		try{
			//验证新添加的代理商名在代理商表和管理员表中存在
			Manager manager = mgerMapper.findByName(agent.getAccount());
			Agent agentTest =  agentMapper.findAgentByName(agent.getAccount());
			if(manager != null || agentTest != null){
				getData.saveCurrentLog(req,"增加代理商:名字已经存在!",ManagerLog.error);
				
				//返回结果
				result.setMsg("增加代理商:名字已经存在!");
				result.setStatus(1);
				return result;
			}
			//当前操作者为管理员
			if(PowerManagment.checkOperCategory(admin) == 1){
				agent.setCategory(1);					//一级代理商
				
				int m = agentMapper.saveAgent(agent);
				
				
				if(m > 0){
					getData.saveCurrentLog(req,"增加代理商:操作成功!",ManagerLog.success);
					
					//返回结果
					result.setMsg("增加代理商:操作成功!");
					result.setStatus(0);
					return result;
				}else{
					getData.saveCurrentLog(req,"增加代理商:增加代理商失败!",ManagerLog.error);
					
					//返回结果
					result.setMsg("增加代理商:增加代理商失败!");
					result.setStatus(2);
					return result;
				}
			}
			
			//当前操作者为代理商(当前操作不能为三级代理商)
			if(PowerManagment.checkOperCategory(admin) == 2
					&& PowerManagment.checkAgentCategory(admin) != 3){
				Agent operAgent = agentMapper.findAgentByName(admin);
				operAgent.setLowerAgentNum(operAgent.getLowerAgentNum()+1);			//修改操作代理商下级代理商的个数
				//执行修改
				int updateCount = agentMapper.alertAgentById(operAgent);
				
				//修改成功
				if(updateCount > 0){
					agent.setCategory(operAgent.getCategory()+1);					//操作者是下级代理商
					int m = agentMapper.saveAgent(agent);
					
					if(m > 0){
						getData.saveCurrentLog(req,"增加代理商:操作成功!",ManagerLog.success);
						
						//返回结果
						result.setMsg("增加代理商:操作成功!");
						result.setStatus(0);
						return result;
					}else{
						getData.saveCurrentLog(req,"增加代理商:增加代理商失败!",ManagerLog.error);
						
						//返回结果
						result.setMsg("增加代理商:增加代理商失败!");
						result.setStatus(2);
						return result;
					}
				}else{
					getData.saveCurrentLog(req,"增加代理商:修改操作者的下级代理商个数失败!",ManagerLog.error);
					
					//返回结果
					result.setMsg("增加代理商:修改操作者的下级代理商个数失败!");
					result.setStatus(3);
					return result;
				}
			}
			getData.saveCurrentLog(req,"增加代理商:当前操作者没有权限!",ManagerLog.error);
			
			//返回结果
			result.setMsg("增加代理商:当前操作者没有权限!");
			result.setStatus(4);
			return result;
		}catch(Exception e){
			e.printStackTrace();
			//将管理员查询失败存入到日志表中
			getData.saveCurrentLog(req,"增加代理商:未知错误!",ManagerLog.error);
			
			result.setMsg("增加代理商:未知错误!");
			result.setStatus(5);
		}
		return result;
	}
	
	/**
	 * 验证信息是否存在
	 */
	public String checkMSG(String message) {
		Manager manager = mgerMapper.findByName(message);
		Agent agent = agentMapper.findAgentByName(message);
		if(agent == null && manager == null){
			return "SUCCESS";
		}
		return "ERROR";
	}
	
	/**
	 * 查看所有代理日志
	 */
	public String findAllAgentLog(HttpServletRequest req,String startDate,String endDate,String index) throws ServletException, IOException {
		String admin=GetAdmin.getAdmin(req); 
		GetData getData = (GetData) BeanFactory.getBean(GetData.class);
		
		//判断当前管理员是否超时
		if(admin == null){
			//保存日志
			getData.saveCurrentLog(req,admin+Code.SELECT_ALL_OUT_TIME,ManagerLog.error);
			
			//返回结果
			return "login/login";
		}
		try{
			map.put("startDate", startDate);
			map.put("endDate", endDate);
			List<AgentLog> list = agentMapper.findAllAgentLog(map);
			int indexpage = Integer.valueOf(index);
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
				getData.saveCurrentLog(req,admin+"查询所有代理日志"+"："+ManagerLog.success,ManagerLog.success);
				req.setAttribute("startDate", startDate);
				req.setAttribute("endDate", endDate);
				return "fieldworkManagement/selectAllAgentLog";
			}else{
				//将管理员查询失败存入到日志表中
				getData.saveCurrentLog(req,admin+"查询所有代理日志"+"：失败!",ManagerLog.error);
			}
		}catch(Exception e){
			e.printStackTrace();
			//将管理员查询失败存入到日志表中
			getData.saveCurrentLog(req,admin+"查询所有代理日志"+"：由于未知异常查询失败!",ManagerLog.error);
		}
		return "fieldworkManagement/selectAllAgentLog";
	}
	
	/**
	 * 根据日期删除代理日志
	 * @throws IOException 
	 * @throws ServletException 
	 */
	public String deleteAgentLogByDate(HttpServletRequest req,HttpServletResponse res, String startDate, String endDate) throws ServletException, IOException {

		String admin=GetAdmin.getAdmin(req); 
		GetData getData = (GetData) BeanFactory.getBean(GetData.class);
		
		String playerIndex = req.getParameter("index");
		//判断当前管理员是否超时
		if(admin == null){
			//保存日志
			getData.saveCurrentLog(req,admin+Code.SELECT_ALL_OUT_TIME,ManagerLog.error);
			//返回结果
			return "login/login";
		}
		try{
			if((startDate!=null&&startDate!="")&&(endDate!=null&&endDate!="")){
				map.put("startDate", startDate);
				map.put("endDate", endDate);
				agentMapper.deleteAgentLogByDate(map);
				res.sendRedirect("findAllAgentLog.do?startDate=&endDate=&index=-1");
			}else{
				//将管理员查询失败存入到日志表中
				getData.saveCurrentLog(req,admin+"查询所有代理日志"+"：失败!",ManagerLog.error);
			}
			
		}catch(Exception e){
			e.printStackTrace();
			//将管理员查询失败存入到日志表中
			getData.saveCurrentLog(req,admin+"查询所有代理日志"+"：由于未知异常查询失败!",ManagerLog.error);
		}
		return "login/login";
	}

	/** 游戏客户端绑定推广码*/
	public Result promoCode(HttpServletRequest req) throws ServletException, IOException {
		Result result = new Result();  						//创建返回结果
		GetData getData = (GetData) BeanFactory.getBean(GetData.class);
		
		try{
			//获取游戏gameId,并验证gameId
			Integer gameId = Integer.valueOf((String)req.getParameter("gameId"));
			Integer agentId = Integer.valueOf((String)req.getParameter("agentId"));
			log.warn("gameId:"+gameId);
			log.warn("agentId:"+agentId);
			
			
			//获取代理商id,并验证代理商id
			if(gameId == null || agentId == null){
				throw new NullPointerException();
			}
			
			//获取游戏服连接
			GameRMIServer gameRMIServer = CacheGameServer.getGameServer();
			if(gameRMIServer == null){
				//保存日志
				getData.saveCurrentLog(req, "玩家绑定到代理商时:获取游戏服务器连接失败!", ManagerLog.error);
				log.warn("未获得服务器连接");
				//返回结果
				result.setMsg("与服务器连接失败!");
				result.setStatus(1);
				return result;
			}
			//通过agentId查询代理商,并验证代理是否存在
			Agent agent = agentMapper.findAgentById(agentId);
			log.warn("agent:"+agent);
			if(agent == null){
				getData.saveCurrentLog(req,"代理商不存在!", ManagerLog.error);
				log.warn("----------------------代理商不存在!");
				result.setMsg("代理商不存在!");
				//玩家已经绑定了
				result.setStatus(4);
				return result;
			}
			//查询玩家是否存在
			@SuppressWarnings("unchecked")
			Map<String,Object> map = (Map<String, Object>) gameRMIServer.exec(RMIConstant.GM_TOOL_FIND_PLAYER, gameId);
			log.warn("map:"+map);
			//判断游戏服务器是否传过来数据
			if(map == null){
				//保存日志
				getData.saveCurrentLog(req,"玩家不存在!", ManagerLog.error);
				log.warn("----------------------玩家不存在!");
				//返回结果
				result.setMsg("没有查询到玩家!");
				result.setStatus(2);
				return result;
			}
			
			//通过gameId查询ProCode,并判断玩家是否已经绑定
			PromoCode promoCode1 = promoCodeMapper.selectByGameId(gameId);
			if(promoCode1 != null){
				getData.saveCurrentLog(req,"玩家已经绑定!", ManagerLog.error);
				log.warn("----------------------玩家已经绑定!");
				//玩家已经绑定了
				result.setMsg("玩家已经绑定!");
				result.setStatus(3);
				return result;
			}
			
			
			PromoCode promoCode = new PromoCode();
			promoCode.setAccount(agent.getAccount());
			promoCode.setGameId(gameId);
			promoCode.setRoomCard(agent.getRoomCard());
			promoCode.setUserID(agent.getUserID());
			promoCode.setpNickName((String) map.get("pNickName"));
			promoCode.setPlayerRoomCard( (Long) map.get("roomCard"));
			log.warn("------------------------------410055");
			gameRMIServer.exec(RMIConstant.GM_TOOL_PLAYER_Binding, gameId);
			Integer m = (Integer) gameRMIServer.exec(RMIConstant.GM_TOOL_CHARGE_TREASURE,gameId,singleDataMapper.selectBy(1));
			int update = promoCodeMapper.save(promoCode);
			if(update > 0){
				getData.saveCurrentLog(req,"玩家绑定代理商:绑定成功", ManagerLog.success);
				result.setMsg("绑定成功");
				result.setStatus(0);
				return result;
			}
			getData.saveCurrentLog(req,"玩家绑定代理商:绑定失败!", ManagerLog.error);
			result.setMsg("绑定失败");
			result.setStatus(6);
			return result;
			
		}catch(Exception e){
			e.printStackTrace();
			getData.saveCurrentLog(req,"绑定失败!", ManagerLog.success);
			result.setMsg("未知异常!");
			result.setStatus(5);
			return result;
		}
	}
	
	/**
	 * 判断客户端是否绑定代理商
	 */
	public Result judgePromoCode(HttpServletRequest req) throws ServletException, IOException {
		Result result = new Result();  						//创建返回结果
		GetData getData = (GetData) BeanFactory.getBean(GetData.class);
		
		try{
			//获取游戏gameId,并验证gameId
			Integer gameId = Integer.valueOf((String)req.getParameter("gameId"));
			log.warn("判断是否绑定代理商gameId:"+gameId);
			//获取代理商id,并验证代理商id
			if(gameId == null){
				throw new NullPointerException();
			}
			
			//获取游戏服连接
			GameRMIServer gameRMIServer = CacheGameServer.getGameServer();
			if(gameRMIServer == null){
				
				System.out.println("服务器没连接没成功!");
				
				//保存日志
				getData.saveCurrentLog(req, "判断玩家绑定到代理商时:获取游戏服务器连接失败!", ManagerLog.error);
				//返回结果
				result.setMsg("与服务器连接失败!");
				result.setStatus(1);
				return result;
			}
			
			@SuppressWarnings("unchecked")
			Map<String,Object> map = (Map<String, Object>) gameRMIServer.exec(RMIConstant.GM_TOOL_FIND_PLAYER, gameId);
			log.warn("----------map:"+map);
			//判断游戏服务器是否传过来数据
			if(map == null){
				//保存日志
				getData.saveCurrentLog(req,"判断玩家绑定到代理商时:玩家不存在!", ManagerLog.error);
				
				System.out.println("玩家不存在!");
				
				//返回结果
				result.setMsg("没有查询到玩家!");
				result.setStatus(2);
				return result;
			}
			
			//通过gameId查询ProCode,并判断玩家已经十分绑定失败
			PromoCode promoCode1 = promoCodeMapper.selectByGameId(gameId);
			if(promoCode1 != null){
				getData.saveCurrentLog(req,"判断玩家绑定到代理商时:玩家已经绑定!", ManagerLog.error);
				
				System.out.println("玩家已经绑定!");
				
				//玩家已经绑定了
				result.setMsg("玩家已经绑定");
				result.setStatus(3);
				System.out.println(promoCode1.getUserID());
				result.setData(promoCode1.getUserID());
				return result;
			}else{
				getData.saveCurrentLog(req,"判断玩家绑定到代理商时:玩家未绑定!", ManagerLog.error);
				
				System.out.println("玩家没有绑定!");
				result.setMsg("玩家没有绑定");
				//玩家没有绑定了
				result.setStatus(0);
				return result;
			}
		}catch(Exception e){
			e.printStackTrace();
			getData.saveCurrentLog(req,"判断玩家绑定到代理商时:未知异常!", ManagerLog.error);
			result.setMsg("未知异常!");
			result.setStatus(4);
		}
		return result;
	}
	
	/**
	 * 当给代理商充值房卡,请求查询代理商信息页面
	 */
	@Transactional
	public String selectAengtPage(HttpServletRequest req) throws ServletException, IOException {
		String admin = GetAdmin.getAdmin(req);  			//获取当前管理员名
		GetData getData = (GetData) BeanFactory.getBean(GetData.class);
		
		//判断当前管理员或者代理商是否超时
		if(admin == null){
			//保存日志
			getData.saveCurrentLog(req, "给代理商充值时:操作者未操作超时!", ManagerLog.error);
			
			//返回结果
			return "login/login";
		}
		
		//三级级代理商没有权限(系统代理商的最低级数为二级)
		if(PowerManagment.checkOperCategory(admin) == 2 
				&& PowerManagment.checkAgentCategory(admin) == 3){
			//记录日志
			getData.saveCurrentLog(req, "给代理商充值时:没有权限操作失败!", ManagerLog.success);
			
			return "systemSettingsManagement/selectAllManagerNoPower";
		}
		
		//成功
		getData.saveCurrentLog(req, "给代理商充值时:请求查询页面成功!", ManagerLog.success);
		return "fieldworkManagement/selectAgentByGameId";
	}
	
	/**
	 * 当给代理商充值房卡,查询代理商信息
	 * 需要验证充值的代理商是否是充值者的下级代理商
	 */
	@Transactional
	public Result selectAengt(HttpServletRequest req) throws ServletException, IOException {
		Result result = new Result();  						//创建返回结果
		String admin = GetAdmin.getAdmin(req);  			//获取当前管理员名
		GetData getData = (GetData) BeanFactory.getBean(GetData.class);
		String agentId = (String)req.getParameter("agentId");
		
		//判断当前管理员是否超时
		if(admin == null){
			//保存日志
			getData.saveCurrentLog(req, "查询代理商信息:操作者未操作超时!", ManagerLog.error);
			//返回结果
			result.setStatus(-1);
			return result;
		}
		
		if(agentId == null){
			//保存日志
			getData.saveCurrentLog(req, "查询代理商信息:代理商id为空!", ManagerLog.error);
			//返回结果
			result.setStatus(1);
			result.setMsg("查询代理商信息:代理商id为空!");
			return result;
		}
		
		try{
			Agent agent = agentMapper.findAgentById(Integer.valueOf(agentId));
			//判断是否查出了代理商信息
			if(agent == null){
				//保存日志
				getData.saveCurrentLog(req,"查询代理商信息:代理商不存在!", ManagerLog.error);
				//返回结果
				result.setStatus(2);
				result.setMsg("查询代理商信息:代理商不存在!");
				return result;
			}
			
			if(PowerManagment.checkOperCategory(admin) == 1){
				//保存日志
				getData.saveCurrentLog(req, "查询代理商信息:查询成功!", ManagerLog.success);
				//req.setAttribute("agent", agent);	
				
				//返回结果
				result.setStatus(0);
				result.setData(agent);
				result.setMsg("查询代理商信息:查询成功!");
				return result;
			}
			
			//被充值代理商必须必须是操作者的下级代理商
			if(PowerManagment.checkOperCategory(admin) == 2){
				if(agent.getHigherAgent().equals(admin)){
					
					//保存日志
					getData.saveCurrentLog(req, "查询代理商信息:查询成功!", ManagerLog.success);
					//req.setAttribute("agent", agent);	
					
					//返回结果
					result.setStatus(0);
					result.setData(agent);
					result.setMsg("查询代理商信息:查询成功!");
					return result;
				}
			}
			
			getData.saveCurrentLog(req, "查询代理商信息:没有权限!", ManagerLog.error);
			
			//返回结果
			result.setStatus(3);
			result.setData(agent);
			result.setMsg("查询代理商信息:没有权限!");
			return result;
		}catch(Exception e){
			e.printStackTrace();
			//记录失败日志
			getData.saveCurrentLog(req,"查询代理商信息:未知异常!", ManagerLog.error);
			//返回结果
			result.setStatus(4);
			result.setMsg("查询代理商信息:未知异常!");
		}
		return result;
	}
	
	/** 代理商增加房卡页面*/
	@Transactional
	public String toAddRoomCard(HttpServletRequest req,String account,Integer userID,Integer roomCard) throws ServletException, IOException {
		String admin = GetAdmin.getAdmin(req);  			//获取当前管理员名
		GetData getData = (GetData) BeanFactory.getBean(GetData.class);
		
		//判断当前操作者是否超时
		if(admin == null){
			//保存日志
			getData.saveCurrentLog(req, "请求代理商充值充值页面:操作者为操作超时!", ManagerLog.error);
			
			//返回结果
			return "login/login";
		}
		
		//三级代理商没有权限(系统代理商的最低级数为二级)
		if(PowerManagment.checkOperCategory(admin) == 2 
				&& PowerManagment.checkAgentCategory(admin) == 3){
			//记录日志
			getData.saveCurrentLog(req,"请求代理商充值充值页面:没有权限操作失败!", ManagerLog.success);
			
			return "systemSettingsManagement/selectAllManagerNoPower";
		}
		
		//将房卡数,玩家id,玩家昵称存入到req中
		req.setAttribute("ByAccount",account);
		req.setAttribute("userID",userID);
		req.setAttribute("roomCard",roomCard);
		
		//判断操作者时管理员还是代理商
		Manager mg = mgerMapper.findByName(admin);
		Agent agent = agentMapper.findAgentByName(admin);
		
		if(mg != null && agent == null){  			//管理员
			req.setAttribute("mark", -1);
			//成功
			getData.saveCurrentLog(req,"请求代理商充值充值页面:操作成功!", ManagerLog.success);
		}else if(agent != null && mg == null){		//代理商
			req.setAttribute("agentRoomCard", agent.getRoomCard());
			req.setAttribute("account", agent.getAccount());
			req.setAttribute("mark", -2);
			//成功,记录日志
			getData.saveCurrentLog(req,"请求代理商充值充值页面:操作成功!", ManagerLog.success);
		}else{
			//记录失败
			getData.saveCurrentLog(req,"请求代理商充值充值页面:没有权限操作失败!", ManagerLog.success);
			
			return "systemSettingsManagement/selectAllManagerNoPower";
		}
		return "fieldworkManagement/addRoomCard";
	}
	
	/**
	 * 给代理商充值房卡
	 */
	public Result addRoomCard(HttpServletRequest req, Integer userID, Integer addRoomCard,
			String sell,String reason) throws ServletException, IOException {
		Result result = new Result();  						//创建返回结果
		String admin = GetAdmin.getAdmin(req);  			//获取当前操作者名
		AgentDeal agentDeal = new AgentDeal();
		GetData getData = (GetData) BeanFactory.getBean(GetData.class);
		String ip = GetData.getRemoteHost(req); 			//获取操作者ip
		
		//判断当前管理员是否超时
		if(admin == null){
			//保存日志
			getData.saveCurrentLog(req, "给代理充值房卡:操作者未操作超时!", ManagerLog.error);
			//返回结果
			result.setStatus(-1);
			return result;
		}
		
		if(userID == null){
			//保存日志
			getData.saveCurrentLog(req,"给代理充值房卡:传入参数错误!", ManagerLog.error);
			//返回结果
			result.setStatus(1);
			result.setMsg("给代理充值房卡:传入参数错误!");
			return result;
		}
		
		try{
			//三级代理商没有权限(系统代理商的最低级数为二级)
			if(PowerManagment.checkOperCategory(admin) == 2 
					&& PowerManagment.checkAgentCategory(admin) == 3){
				//记录日志
				getData.saveCurrentLog(req,"给代理充值房卡:没有权限!", ManagerLog.success);
				
				result.setStatus(2);
				result.setMsg("给代理充值房卡:没有权限!");
				return result;
			}
			
			Agent operAgent = agentMapper.findAgentByName(admin);
			//通过id查询代理商信息,管理员给代理减房卡不能减到小于0
			Agent agent = agentMapper.findAgentById(userID);
			
			if(agent == null){
				getData.saveCurrentLog(req,"给代理充值房卡:充值房卡的代理商不存在!", ManagerLog.success);
				
				result.setStatus(3);
				result.setMsg("给代理充值房卡:充值房卡的代理商不存在!");
				return result;
			}
			
			log.warn("收款人房卡:"+agent.getRoomCard());
			
			if(PowerManagment.checkOperCategory(admin) == 1){  //管理员
				//判断充值的代理商是不是一级代理商
				if(agent.getCategory() != 1
						&& !PowerManagment.checkManagmentCategory(admin)){
					getData.saveCurrentLog(req,"给代理充值房卡:被充值代理商不是一级代理商!", ManagerLog.error);
					
					result.setStatus(4);
					result.setMsg("给代理充值房卡:被充值代理商不是一级代理商!");
					return result;
				}
				
				if(agent.getRoomCard()+addRoomCard < 0){
					getData.saveCurrentLog(req,"给代理充值房卡:减去代理商的房卡超出代理的最大房卡数!", ManagerLog.error);
					
					result.setStatus(5);
					result.setMsg("给代理充值房卡:减去代理商的房卡超出代理的最大房卡数!");
					return result;
				}
				
				//交易记录
				agentDeal.setRemitterAccount(admin);
				agentDeal.setPayeeAccount(agent.getAccount());
				agentDeal.setDealType("管理员充值");
				agentDeal.setRemitterRoomCard(0);
				agentDeal.setPayeeRoomCard(agent.getRoomCard()+addRoomCard);							//交易后的房卡
				agentDeal.setOperateIP(ip);
				agentDeal.setSellNumber(addRoomCard);
				agentDeal.setSellMoney(new Double(sell));
				agentDeal.setSellReason(reason);
				//将交易记录持久化
				agentDealMapper.insertAgentDeal(agentDeal);
				
				//修改代理商的房卡
				agent.setRoomCard(agent.getRoomCard()+addRoomCard);
				log.warn("房卡:"+agent.getRoomCard());
				agentMapper.updateByName(agent);
				
				//成功
				getData.saveCurrentLog(req,"给代理充值房卡:操作成功!", ManagerLog.success);
				
				Map<String,Object> map = new HashMap<String , Object>();
				map.put("roomCard", agent.getRoomCard());
				
				//返回结果
				result.setStatus(0);
				result.setData(map);
				result.setMsg("给代理充值房卡:操作成功!");
				return result;
			}else if(PowerManagment.checkOperCategory(admin) == 2){  //代理商
				if(!agent.getHigherAgent().equals(admin)){
					//保存日志
					getData.saveCurrentLog(req, "给代理充值房卡:非操作者的下级代理!", ManagerLog.success);
					
					//返回结果
					result.setStatus(4);
					result.setData(agent);
					result.setMsg("给代理充值房卡:非操作者的下级代理!");
					return result;
				}
				
				//判断传入进来的房卡是否为正整数或者大于代理商自身的房卡数
				if(operAgent.getRoomCard() - addRoomCard < 0){
					getData.saveCurrentLog(req,"给代理充值房卡:充值房卡超出操作者的房卡数!", ManagerLog.error);
					//返回结果
					result.setStatus(7);
					result.setMsg("给代理充值房卡:充值房卡超出操作者的房卡数!");
					return result;
				}
				
				//修改代理商的参数数据
				operAgent.setLastLoginIP(ip);
				operAgent.setLastLoginTime(operAgent.getLastLoginTime()+1);
				operAgent.setRoomCard(operAgent.getRoomCard()-addRoomCard);
				agentMapper.updateByName(operAgent);
				
				//修改代理商的房卡
				agent.setRoomCard(agent.getRoomCard()+addRoomCard);
				System.out.println("房卡:"+agent.getRoomCard());
				agentMapper.updateByName(agent);
				
				//交易记录
				agentDeal.setRemitterAccount(admin);
				agentDeal.setPayeeAccount(agent.getAccount());
				agentDeal.setDealType("代理商充值");
				agentDeal.setRemitterRoomCard(operAgent.getRoomCard());
				agentDeal.setPayeeRoomCard(agent.getRoomCard());							//交易后的房卡
				agentDeal.setOperateIP(ip);
				agentDeal.setSellNumber(addRoomCard);
				agentDeal.setSellMoney(new Double(sell));
				agentDeal.setSellReason(reason);
				//将交易记录持久化
				agentDealMapper.insertAgentDeal(agentDeal);
				
				//成功,记录日志
				getData.saveCurrentLog(req,"给代理充值房卡:操作成功!", ManagerLog.success);
				
				Map<String,Object> map = new HashMap<String , Object>();
				map.put("operRoomCard", operAgent.getRoomCard());
				map.put("roomCard", agent.getRoomCard());
				
				//返回结果
				result.setStatus(0);
				result.setData(map);
				result.setMsg("给代理充值房卡:操作成功!");
				return result;
			}else{
				//记录日志
				getData.saveCurrentLog(req,"给代理充值房卡:没有权限!", ManagerLog.success);
				
				result.setStatus(2);
				result.setMsg("给代理充值房卡:没有权限!");
				return result;
			}
		}catch(Exception e){
			e.printStackTrace();
			//记录失败日志
			getData.saveCurrentLog(req,"给代理充值房卡:未知异常!", ManagerLog.error);
			//返回结果
			result.setStatus(8);
			result.setMsg("给代理充值房卡:未知异常!");
		}
		return result;
	}

	public Result updateRebate(HttpServletRequest req) throws ServletException, IOException {
		String admin = GetAdmin.getAdmin(req);  			//获取当前管理员名
		GetData getData = (GetData) BeanFactory.getBean(GetData.class);
		Result result=new Result();
		//判断当前操作者是否超时
		if(admin == null){
			//保存日志
			getData.saveCurrentLog(req, "修改代理返点比例页面:操作者为操作超时!", ManagerLog.error);
			result.setStatus(-1);
			return result;
		}
		
		try {
			Integer grade=Integer.valueOf(req.getParameter("grade"));
			Double rebate=Double.valueOf(req.getParameter("rebate"));
			if(grade==null || rebate==null) {
				getData.saveCurrentLog(req,"修改代理返点比例页面:参数出错!", ManagerLog.error);
				result.setStatus(1);
				result.setMsg("参数出错!");
				return result;
			}
			//超管才可以
			if(PowerManagment.checkOperCategory(admin) == 1 
					&& PowerManagment.checkManagmentCategory(admin)){
				AgentRebate ar=new AgentRebate();
				ar.setGrade(grade);
				ar.setRebate(rebate);
				agentRebateMapper.update(ar);
				getData.saveCurrentLog(req,"修改代理返点比例页面:修改成功!", ManagerLog.error);
				result.setStatus(0);
				result.setMsg("修改成功!");
				return result;
			}else {
				getData.saveCurrentLog(req,"修改代理返点比例页面:没有权限操作失败!", ManagerLog.error);
				result.setStatus(1);
				result.setMsg("权限不足!");
				return result;
			}
		
		}catch(Exception e) {
			e.printStackTrace();
			getData.saveCurrentLog(req,"请求修改代理返点比例页面:未知异常!", ManagerLog.error);
			result.setStatus(1);
			result.setMsg("未知异常!");
			return result;
		}
		
	}

	public String toUpdateRebate(HttpServletRequest req) throws ServletException, IOException {
		String admin = GetAdmin.getAdmin(req);  			//获取当前管理员名
		GetData getData = (GetData) BeanFactory.getBean(GetData.class);

		//判断当前操作者是否超时
		if(admin == null){
			//保存日志
			getData.saveCurrentLog(req, "请求修改代理返点比例页面:操作者为操作超时!", ManagerLog.error);
			
			//返回结果
			return "login/login";
		}
		
		try {
		//超管才可以
		if(PowerManagment.checkOperCategory(admin) == 1 
				&& PowerManagment.checkManagmentCategory(admin)){
			List<AgentRebate> ars=agentRebateMapper.selectAll();
			double rebate1=0;
			double rebate2=0;
			double rebate3=0;
			for(AgentRebate a:ars) {
				if(a.getGrade()==1)
					rebate1=a.getRebate();
				if(a.getGrade()==2)
					rebate2=a.getRebate();
				if(a.getGrade()==3)
					rebate3=a.getRebate();
			}
			req.setAttribute("rebate1",rebate1);
			req.setAttribute("rebate2",rebate2);
			req.setAttribute("rebate3",rebate3);
			getData.saveCurrentLog(req,"请求修改代理返点比例页面:查询成功!", ManagerLog.success);
			return "fieldworkManagement/toUpdateRebate";
		}else {
			//记录日志
			getData.saveCurrentLog(req,"请求修改代理返点比例页面:没有权限操作失败!", ManagerLog.error);
			req.setAttribute("error","没有权限!");
			return "error/error";
		}
		
		}catch(Exception e) {
			e.printStackTrace();
			getData.saveCurrentLog(req,"请求修改代理返点比例页面:未知异常!", ManagerLog.error);
			req.setAttribute("error","未知异常!");
			return "error/error";
		}
	}

	@Override
	public String toAgentDis(HttpServletRequest req) throws ServletException, IOException {
		String admin = GetAdmin.getAdmin(req);  			//获取当前管理员名
		GetData getData = (GetData) BeanFactory.getBean(GetData.class);

		//判断当前操作者是否超时
		if(admin == null){
			//保存日志
			getData.saveCurrentLog(req, "请求修改代理返点比例页面:操作者为操作超时!", ManagerLog.error);
			
			//返回结果
			return "login/login";
		}
		return "fieldworkManagement/agentDis";
	}

	@Override
	public String toSelectAgentDis(HttpServletRequest req, String name, String startDate, String endDate, Integer index)
			throws ServletException, IOException {
		String admin = GetAdmin.getAdmin(req);  			//获取当前管理员名
		GetData getData = (GetData) BeanFactory.getBean(GetData.class);
		List<PromoCode> list= new ArrayList<>();
		
		//判断当前管理员是否超时
		if(admin == null){
			//保存日志
			getData.saveCurrentLog(req,"查询绑定玩家列表:当前操作者未操作超时!",ManagerLog.error);
			return "login/login";
		}
		
		try{
			//获取游戏服连接
			GameRMIServer gameRMIServer = CacheGameServer.getGameServer();
			if(gameRMIServer == null){
				//保存日志
				getData.saveCurrentLog(req, "玩家绑定到代理商时:获取游戏服务器连接失败!", ManagerLog.error);
				log.warn("未获得服务器连接");
				//返回结果
				req.setAttribute("error","与服务器连接失败!");
				return "error/error";
			}
			
			
			list = promoCodeMapper.selectByAccount(name);//通过代理商名称查询代理商下绑定的玩家
			List<Map<String,Object>> listMap = new ArrayList<Map<String,Object>>();
			for (int i = 0; i <list.size(); i++) {
				@SuppressWarnings("unchecked")
				List<Map<String,Object>> map = (List<Map<String, Object>>) gameRMIServer.exec(RMIConstant.GM_TOOL_FIND_PLAYER,list.get(i).getGameId(),startDate,endDate);
				log.warn(map);
				if(map == null){
					//保存日志
					getData.saveCurrentLog(req,"玩家不存在!", ManagerLog.error);
					//返回结果
					req.setAttribute("error","未获得服务器数据!");
					return "error/error";
				}
				for(int j=0; j<map.size(); j++){
					Map<String,Object> ma = new HashMap<>();
					ma.put("pNiceName", map.get(j).get("pNiceName"));
					listMap.add(ma);
				}
			}
			
			
			System.out.println(listMap);
			if(listMap.size() != 0){
				int pagesize = 20;  //定义每页显示的记录数
				//加入查询所有记录方法中
				GetData.selectAll(req, list, pagesize, index,
						"count", "index", "pagesize", "pagecount", "list");
				
				//将管理员查询成功存入到日志表中
				req.setAttribute("account", name);
				getData.saveCurrentLog(req, "查询绑定玩家列表:操作成功!", ManagerLog.success);
				return "fieldworkManagement/agentDis";
			}else{
				//将管理员查询失败存入到日志表中
				getData.saveCurrentLog(req, "查询绑定玩家列表:操作者没有绑定的玩家!", ManagerLog.error);
				req.setAttribute("error","没有绑定玩家!");
				return "error/error";
			}
			
		}catch(Exception e){
			e.printStackTrace();
			//记录异常日志
			getData.saveCurrentLog(req,"查询绑定玩家列表:未知异常!",ManagerLog.error);
			req.setAttribute("error","未知异常!");
			return "error/error";
		}
	}

	@Override
	public String toAgentEarn(HttpServletRequest req) throws ServletException, IOException {
		String admin = GetAdmin.getAdmin(req);  			//获取当前操作者名
		GetData getData = (GetData) BeanFactory.getBean(GetData.class);

		//判断当前管理员是否超时
		if(admin == null){
			//保存日志
			getData.saveCurrentLog(req,"查询所有代理商:操作者未操作超时!",ManagerLog.error);
			//返回结果
			return "login/login";
		}
		return "fieldworkManagement/selectEarn";
	}

	@Override
	public String toAgentEarnData(HttpServletRequest req, String start, String end, Integer userId)
			throws ServletException, IOException {
		String admin = GetAdmin.getAdmin(req);  			//获取当前操作者名
		GetData getData = (GetData) BeanFactory.getBean(GetData.class);
		String playerIndex = (String)req.getParameter("index");  //获取传入的请求页数
		
		//判断当前管理员是否超时
		if(admin == null){
			//保存日志
			getData.saveCurrentLog(req,"查询所有代理商:操作者未操作超时!",ManagerLog.error);
			//返回结果
			return "login/login";
		}

		try{
			GameRMIServer gameRMIServer = CacheGameServer.getGameServer();
			if(gameRMIServer == null){
				//返回结果
				req.setAttribute("error", "与服务器连接失败");
				return "login/login";
			}else{
				if(start==""||start==null){
					start = null;
					end =null;
				}if(end==""||end==null){
					start = null;
					end =null;
				}

				List<EarnPage> earnPage = new ArrayList<>();
				if(userId == null){
					start += " 00:00:01";
					end += " 00:00:00";
					List<Agent> list = agentMapper.findAllAgent();
					if(list != null){
						for(int i=0;i<list.size();i++){
							List<PromoCode> list1=  promoCodeMapper.selectByAccount1(list.get(i).getUserID());//通过代理id去查询旗下玩家
							if(list1 != null){
								Long count = 0l;
								Map<String, Object> maplist = new HashMap<>();
								maplist.put("start", start);
								maplist.put("end", end);
								List<Integer> map = new ArrayList<>();
								for(int j=0; j<list1.size(); j++){
									map.add(list1.get(j).getGameId());
								}
								maplist.put("gameid", map);
								System.out.println("我传给服务器的："+maplist);
								count =  (Long) gameRMIServer.exec(RMIConstant.SELECT_PLAYER_MONERY,maplist);
								System.out.println("服务器返回的是："+count);
								EarnPage earn = new EarnPage();
								AgentRebate rebate = agentRebate.selectOne(list.get(i).getCategory());
								System.out.println(rebate);
								earn.setEarn(count*rebate.getRebate());
								earn.setGrade(list.get(i).getCategory());
								earn.setName(list.get(i).getAccount());
								earn.setPlayer(count);
								earn.setUserId(list.get(i).getUserID());
								earnPage.add(earn);
							}
						}
					}
					int indexpage = Integer.valueOf(playerIndex);
					//判断index是否为第一次请求
					if(indexpage <0){
						indexpage = 1;
					}
					System.out.println("结果："+earnPage);
					if(earnPage != null){
						int pagesize = 20;  //定义每页显示的记录数
						//加入查询所有记录方法中
						GetData.selectAll(req, earnPage, pagesize, indexpage,
								"count", "index", "pagesize", "pagecount", "list");

						//将代理商查询成功存入到日志表中
						getData.saveCurrentLog(req,"查询所有代理商:操作成功!",ManagerLog.success);
						req.setAttribute("start", start);
						req.setAttribute("end", end);
						req.setAttribute("userId", userId);
						return "fieldworkManagement/selectEarn";
					}
				}

				if(start != null && end != null && userId != null){
					start += " 00:00:01";
					end += " 00:00:00";
					Agent ag = agentMapper.findAgentById(userId);
					List<PromoCode> list1=  promoCodeMapper.selectByAccount1(userId);//通过代理id去查询旗下玩家
					EarnPage earn = new EarnPage();
					if(list1 != null){
						Long count = 0l;
						Map<String, Object> maplist = new HashMap<String, Object>();
						maplist.put("start", start);
						maplist.put("end", end);
						List<Integer> map = new ArrayList<>();
						for(int j=0; j<list1.size(); j++){
							map.add(list1.get(j).getGameId());
						}
						maplist.put("gameid", map);
						count =  (Long) gameRMIServer.exec(RMIConstant.SELECT_PLAYER_MONERY,maplist);
						AgentRebate rebate = agentRebate.selectOne(ag.getCategory());
						earn.setEarn(count*rebate.getRebate());
						earn.setGrade(ag.getCategory());
						earn.setName(ag.getAccount());
						earn.setPlayer(count);
						earn.setUserId(ag.getUserID());
					}
					int indexpage = Integer.valueOf(playerIndex);
					//判断index是否为第一次请求
					if(indexpage <0){
						indexpage = 1;
					}
					List<EarnPage> earnP = new ArrayList<>();
					earnP.add(earn);
					if(earnP != null){
						int pagesize = 20;  //定义每页显示的记录数
						//加入查询所有记录方法中
						GetData.selectAll(req, earnP, pagesize, indexpage,
								"count", "index", "pagesize", "pagecount", "list");

						//将代理商查询成功存入到日志表中
						getData.saveCurrentLog(req,"查询所有代理商:操作成功!",ManagerLog.success);
						req.setAttribute("start", start);
						req.setAttribute("end", end);
						req.setAttribute("userId", userId);
						return "fieldworkManagement/selectEarn";
					}
				}
			}
		}catch(Exception e){
			e.printStackTrace();
			//将管理员查询失败存入到日志表中
			getData.saveCurrentLog(req,admin+"查询所有代理商"+"：由于未知异常查询失败!",ManagerLog.error);
		}
		return "fieldworkManagement/selectEarn";
	}

	@Override
	public Result bindAgent(HttpServletRequest req, Integer gameId, Integer agentId) throws ServletException, IOException {
		
		GetData getData = (GetData) BeanFactory.getBean(GetData.class);
		
		Result result = new Result();
		
		//校验邀请码的有效性
		if (agentId != null && gameId != null) {
			
			//获取游戏服连接
			GameRMIServer gameRMIServer = CacheGameServer.getGameServer();
			
			if(gameRMIServer == null){
				//保存日志
				getData.saveCurrentLog(req, "玩家绑定到代理商时:获取游戏服务器连接失败!", ManagerLog.error);
				log.warn("未获得服务器连接");
				//返回结果
				result.setMsg("与服务器连接失败!");
				result.setStatus(1);
				return result;
			}
			
			
			//通过agentId查询代理商,并验证代理是否存在
			Agent agent = agentMapper.findAgentById(agentId);
			log.warn("agent:"+agent);
			if(agent == null){
				getData.saveCurrentLog(req,"代理商不存在!", ManagerLog.error);
				log.warn("----------------------代理商不存在!");
				result.setMsg("请输入有效邀请码");
				//玩家已经绑定了
				result.setStatus(3);
				return result;
			}
			//通过gameId查询ProCode,并判断玩家是否已经绑定
			PromoCode promoCode1 = promoCodeMapper.selectByGameId(gameId);
			if(promoCode1 != null && promoCode1.getYb() == 0){
				getData.saveCurrentLog(req,"玩家已经绑定!", ManagerLog.error);
				log.warn("----------------------玩家已经绑定!");
				//玩家已经绑定了
				result.setMsg("玩家已经绑定!");
				result.setStatus(2);
				return result;
			}else if(promoCode1 != null && promoCode1.getYb() == 1){
				getData.saveCurrentLog(req,"玩家重新绑定!", ManagerLog.error);
				log.warn("----------------------玩家重新绑定!");
				
				//重新绑定的时候，更新当前玩家的信息
				//根据gameId 查询出该游戏玩家的信息     昵称之类 的   
				
				@SuppressWarnings("unchecked")
				Map<String , Object> maps = (Map<String , Object>) gameRMIServer.exec(RMIConstant.GM_TOOL_FIND_PLAYER,gameId);
				
				PromoCode promoCode = new PromoCode();
				promoCode.setAccount(agent.getAccount());
				promoCode.setGameId(gameId);
				promoCode.setRoomCard(agent.getRoomCard());
				promoCode.setUserID(agent.getUserID());
				promoCode.setpNickName((String) maps.get("pNiceName"));
				promoCode.setPlayerRoomCard( (Long) maps.get("roomCard"));
				promoCode.setBindingTime(new Date());
				promoCode.setModifyTime(new Date());
				promoCode.setYb(0);  // 0被绑定 1 未被绑定
				//告诉服务器被绑定了   //这里服务器好像没有编写
				gameRMIServer.exec(RMIConstant.GM_TOOL_PLAYER_Binding, gameId);
				promoCodeMapper.unbindPlayer(promoCode1);//更新玩家绑定状态
				result.setMsg("绑定成功");
				result.setStatus(0);
				return result;
			}else {
				
				
				//根据gameId 查询出该游戏玩家的信息     昵称之类 的   
				
				@SuppressWarnings("unchecked")
				Map<String , Object> maps = (Map<String , Object>) gameRMIServer.exec(RMIConstant.GM_TOOL_FIND_PLAYER,gameId);
				
				PromoCode promoCode = new PromoCode();
				promoCode.setAccount(agent.getAccount());
				promoCode.setGameId(gameId);
				promoCode.setRoomCard(agent.getRoomCard());
				promoCode.setUserID(agent.getUserID());
				promoCode.setpNickName((String) maps.get("pNiceName"));
				promoCode.setPlayerRoomCard( (Long) maps.get("roomCard"));
				promoCode.setBindingTime(new Date());
				promoCode.setModifyTime(new Date());
				promoCode.setYb(0);  // 0被绑定 1 未被绑定
				//告诉服务器被绑定了   //这里服务器好像没有编写
				gameRMIServer.exec(RMIConstant.GM_TOOL_PLAYER_Binding, gameId);
				
				//绑定邀请码
				int update = promoCodeMapper.save(promoCode);
				if(update > 0){
					//同一个邀请码可解绑 绑定多次，但是奖励只有一次
					//增加钻石
					Integer num = singleDataMapper.selectBy(1);
					
					Integer m = (Integer) gameRMIServer.exec(RMIConstant.GM_TOOL_CHARGE_TREASURE,gameId,num);
					getData.saveCurrentLog(req,"玩家绑定代理商:绑定成功", ManagerLog.success);
					result.setMsg("绑定成功");
					result.setStatus(0);
					return result;
				}
			}
		}
		result.setMsg("请输入正确的邀请码");
		result.setStatus(3);
		return result;
	}
}
