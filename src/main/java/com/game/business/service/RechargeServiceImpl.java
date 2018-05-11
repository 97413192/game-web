package com.game.business.service;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.game.base.util.BeanFactory;
import com.game.base.util.Code;
import com.game.base.util.GetAdmin;
import com.game.base.util.GetData;
import com.game.base.util.Result;
import com.game.base.util.StringUtil;
import com.game.business.mapper.AgentMapper;
import com.game.business.mapper.LogMapper;
import com.game.business.mapper.MgerMapper;
import com.game.business.mapper.PlayerTradeRecordMapper;
import com.game.business.model.Agent;
import com.game.business.model.PlayerTradeRecode;
import com.game.business.model.manager.Manager;
import com.game.business.model.manager.ManagerLog;
import com.game.cache.CacheLoginServer;
import com.game.constant.D;
import com.game.constant.LoginRMIConstant;
import com.game.game.mapper.RecoreMapper;
import com.game.game.model.ConvertScale;
import com.game.game.model.Level;
import com.game.game.model.OrderRecord;
import com.ranger.remote.LoginServerRMI;
import com.ranger.remote.RMIResult;

import sun.swing.StringUIClientPropertyKey;

/**
 * <li>@ClassName: 充值管理业务层接口实现类
 * <li>@author 周强
 * <li>@date 2016年11月18日
 */
@Service
public class RechargeServiceImpl implements RechargeService{
	@Resource
	private LogMapper logMapper;
	
	
	@Resource
	private PlayerTradeRecordMapper playerTradeRecordMapper;
	@Resource
	private AgentMapper agentMapper;
	@Resource
	private MgerMapper mgMapper;
	@Resource
	private RecoreMapper recoreMapper;
	
	
	/** 根据条件查询多条充值记录*/
	public String toSelectManyRechargeRecord(HttpServletRequest req) throws ServletException, IOException {
		String admin = GetAdmin.getAdmin(req);   					//获取当前管理员名
		Integer index = null;
		String index1 = (String)req.getParameter("index");     		//获取请求页数
		Integer category = null; 
		String category1 = (String)req.getParameter("category");    //查询类别
		String condition = (String)req.getParameter("condition");   //查询条件
		String beginTime = (String)req.getParameter("beginTime");   //通过时间查询的开始时间
		String endTime = (String)req.getParameter("endTime");       //通过时间查询的结束时间
		Map<String ,Object> timeMap = null;
		//int category = 0;
		//System.out.println("playerIndex:"+playerIndex);
		GetData getData = (GetData) BeanFactory.getBean(GetData.class);
		
		//判断当前管理员或者代理商是否超时
		if(admin == null){
			//保存日志
			getData.saveCurrentLog(req, Code.SELECT_MANY_ORDER_OUTTIME, ManagerLog.error);
			
			//返回结果
			return "login/login";
		}
		
		//检查传入的参数格式
		try{
			//检查传入的时间格式
			timeMap = new HashMap<String ,Object>();
			if(beginTime==null || beginTime == "" || endTime==null || endTime == ""){
				throw new NullPointerException();
			}
			if(category1 == null || category1=="" || index1==null || index1==""){
				throw new NullPointerException();
			}
			category = Integer.valueOf(category1);
			index = Integer.valueOf(index1);
			
			timeMap.put("beginTime", new Timestamp((StringUtil.strToDate(beginTime)).getTime()));
			timeMap.put("endTime", new Timestamp((StringUtil.strToDate(endTime)).getTime()));
			
		}catch(Exception e){
			e.printStackTrace();
			
			//记录日志
			getData.saveCurrentLog(req, Code.SELECT_MANY_ORDER_PARAMETER_ERROR, ManagerLog.error);
			return "RechargeManagement/selectManyOrderRecord";
		}
		
		
		try{
			List<OrderRecord> list = null;
			
			//获取当前操作管理员
			Manager mg = mgMapper.findByName(admin);
			Agent agent = agentMapper.findAgentByName(admin);
			
			if(mg != null && agent == null){  //管理员可以查询所有的玩家交易日志
				switch(category){
					case 0:
					//获取所有消息记录
					list = recoreMapper.findAllOrder();
					break;
					case 1:
					list = recoreMapper.findOrderByTime(timeMap);
					break;
						
				}
				
			}else if(agent != null && mg == null){  //代理商只能查看属于自己的玩家的日志
				
			}else{
				//记录失败日志
				getData.saveCurrentLog(req, Code.SELECT_MANY_ORDER_NOT_POWER, ManagerLog.error);
				
				//返回结果
				return "systemSettingsManagement/selectAllManagerNoPower";
			}
			
			
			int indexpage = Integer.valueOf(index);
			//判断index是否为第一次请求
			if(indexpage <0){
				indexpage = 1;
			}
			
			
			//System.out.println(list);
			
			if(list != null){
				int pagesize = 20;  //定义每页显示的记录数
				//加入查询所有记录方法中
				GetData.selectAll(req, list, pagesize, indexpage,
						"count", "index", "pagesize", "pagecount", "list");
				
				req.setAttribute("category", category1);
				req.setAttribute("condition", condition);
				req.setAttribute("beginTime", beginTime);
				req.setAttribute("endTime", endTime);
				
				//将管理员查询成功存入到日志表中
				getData.saveCurrentLog(req, Code.SELECT_MANY_ORDER_SUC, ManagerLog.success);
				
				return "RechargeManagement/selectManyOrderRecord";
			}else{
				//将管理员查询失败存入到日志表中
				getData.saveCurrentLog(req, Code.SELECT_MANY_ORDER_ERROR, ManagerLog.error);
				return "RechargeManagement/selectManyOrderRecord";
			}
		}catch(Exception e){
			e.printStackTrace();
			//将管理员查询失败存入到日志表中
			getData.saveCurrentLog(req, Code.SELECT_MANY_ORDER_UNKNOWN_ERROR, ManagerLog.error);
		}
		return "RechargeManagement/selectManyOrderRecord";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//查询钻石兑换金币比例
	public String selectConverScale(HttpServletRequest req) throws ServletException, IOException {
		ManagerLog log = new ManagerLog(); //创建日志
		String admin = GetAdmin.getAdmin(req);  //获取当前管理员名
		String ip = GetData.getRemoteHost(req); //获取管理员ip
		String system = GetData.getSystem(req);  //获取管理员操作系统
		Timestamp time = GetData.getTime(); //获取当前系统时间
		
		//将ip地址，系统，管理员名，当前系统时间存入log中
		log.setIp(ip);
		log.setName(admin);
		log.setSystem(system);
		log.setTime(time);
		
		//判断当前管理员是否超时
		if(admin == null){
			//保存日志
			log.setIp(ip);
			log.setName("未知");
			log.setSystem("未知");
			log.setTime(time);
			log.setStatus(ManagerLog.error);
			log.setDsc(Code.SELECT_CONVERTSCALE_OUTTIME);
			logMapper.logSave(log);
			
			//返回结果
			return "login/login";
		}
		try{
			//连接登陆服务
			LoginServerRMI loginServerRMI = CacheLoginServer.getLoginServer();
			if (null == loginServerRMI) {
				//将获取登陆服务失败
				log.setStatus(ManagerLog.error);
				log.setDsc(Code.SELECT_CONVERTSCALE_LOGSERVER_ERROR);
				logMapper.logSave(log);
				
				return "RechargeManagement/selectConvertScale";
			}else{
				//传入一个状态码，返回一个RMIResult实体类，RMIResult的result属性为ActionWard
				RMIResult result = (RMIResult) loginServerRMI.exce(LoginRMIConstant.GM_TOOL_USR_SELECT);
				if(result.getErrorCode() == D.CODE_SUC && result.getResult() != null ){ //判断查询是否成功
					req.setAttribute("convertScale", (ConvertScale)result.getResult());
					
					//将钻石兑换金币查询成功存入到日志表中
					log.setStatus(ManagerLog.success);
					log.setDsc(Code.SELECT_ACTIONWARD_SUC_CREROOM);
					logMapper.logSave(log);
					return "RechargeManagement/selectConvertScale";
				}else{
					//将查询失败存入到日志表中
					log.setStatus(ManagerLog.error);
					log.setDsc(Code.SELECT_CONVERTSCALE_GETRESULT_ERROR);
					logMapper.logSave(log);
					return "RechargeManagement/selectConvertScale";
				}
			}
		}catch(Exception e){
			e.printStackTrace();
			//将由于未知错误引起的操作失败存入日志
			log.setStatus(ManagerLog.error);
			log.setDsc(Code.SELECT_CONVERTSCALE_UNKNOWN_ERROR);
			logMapper.logSave(log);
		}
		return "RechargeManagement/selectConvertScale";
	}
	
	//修改钻石兑换金币比例
	public Result alterConvertScale(HttpServletRequest req, int convertScale) throws ServletException, IOException {
		ManagerLog log = new ManagerLog(); //创建日志
		Result result = new Result();  //创建返回结果
		String admin = GetAdmin.getAdmin(req);  //获取当前管理员名
		String ip = GetData.getRemoteHost(req); //获取管理员ip
		String system = GetData.getSystem(req);  //获取管理员操作系统
		Timestamp time = GetData.getTime(); //获取当前系统时间
		
		//将ip地址，系统，管理员名，当前系统时间存入log中
		log.setIp(ip);
		log.setName(admin);
		log.setSystem(system);
		log.setTime(time);
		
		//判断当前管理员是否超时
		if(admin == null){
			//保存日志
			log.setIp(ip);
			log.setName("未知");
			log.setSystem("未知");
			log.setTime(time);
			log.setStatus(ManagerLog.error);
			log.setDsc(Code.ALTER_CONVERTSCALE_OUTTIME);
			logMapper.logSave(log);
			
			//返回结果
			result.setStatus(-1);
			return result;
		}
		try{
			//获取登陆服务
			LoginServerRMI loginServerRMI = CacheLoginServer.getLoginServer();
			if (null == loginServerRMI) {
				//获取登陆服务失败
				log.setStatus(ManagerLog.error);
				log.setDsc(Code.ALTER_CONVERTSCALE_LOGSERVER_ERROR);
				logMapper.logSave(log);
				
				//返回结果
				result.setMsg(Code.ALTER_CONVERTSCALE_LOGSERVER_ERROR);
				result.setStatus(1);
				return result;
			}else{
				//传入一个修改钻石兑换金币的状态码及convertScale，修改convertScale的值，在数据库中修改钻石兑换金币的记录只有一条。返回成功失败两种状态。
				RMIResult result1 = (RMIResult) loginServerRMI.exce(LoginRMIConstant.GM_TOOL_USR_INSERT_BY_NAME_PWD,convertScale);
				if(result1.getErrorCode() == D.CODE_SUC){
					//将修改成功存入日志
					log.setStatus(ManagerLog.success);
					log.setDsc(Code.ALTER_CONVERTSCALE_SUC_CREROOM);
					logMapper.logSave(log);
					
					//返回结果
					result.setMsg(Code.ALTER_CONVERTSCALE_SUC_CREROOM);
					result.setStatus(0);
					return result;
				}else{
					//打印失败状态码
					System.out.println(result1.getErrorCode());
					
					//将修改失败加入的日志中
					log.setStatus(ManagerLog.error);
					log.setDsc(Code.ALTER_CONVERTSCALE_GETRESULT_ERROR);
					logMapper.logSave(log);
					
					//返回结果
					result.setMsg(Code.ALTER_CONVERTSCALE_GETRESULT_ERROR);
					result.setStatus(2);
					return result;
				}
			}
		}catch(Exception e){
			e.printStackTrace();
			//将由于未知错误引起的操作失败存入日志
			log.setStatus(ManagerLog.error);
			log.setDsc(Code.ALTER_CONVERTSCALE_UNKNOWN_ERROR);
			logMapper.logSave(log);
			
			//返回结果
			result.setMsg(Code.ALTER_CONVERTSCALE_UNKNOWN_ERROR);
			result.setStatus(3);
		}
		return result;
	}
	
	//查询所有充值档次
	@SuppressWarnings("unchecked")
	public String selectAllLevel(HttpServletRequest req) throws ServletException, IOException {
		ManagerLog log = new ManagerLog(); //创建日志
		String admin = GetAdmin.getAdmin(req);  //获取当前管理员名
		String ip = GetData.getRemoteHost(req); //获取管理员ip
		String system = GetData.getSystem(req);  //获取管理员操作系统
		Timestamp time = GetData.getTime(); //获取当前系统时间
		
		//将ip地址，系统，管理员名，当前系统时间存入log中
		log.setIp(ip);
		log.setName(admin);
		log.setSystem(system);
		log.setTime(time);
		
		//判断当前管理员是否超时
		if(admin == null){
			//保存日志
			log.setIp(ip);
			log.setName("未知");
			log.setSystem("未知");
			log.setTime(time);
			log.setStatus(ManagerLog.error);
			log.setDsc(Code.SELECT_AllLEVEL_OUTTIME);
			logMapper.logSave(log);
			
			//返回结果
			return "login/login";
		}
		try{
			//连接登陆服务
			LoginServerRMI loginServerRMI = CacheLoginServer.getLoginServer();
			if (null == loginServerRMI) {
				//将获取登陆服务失败
				log.setStatus(ManagerLog.error);
				log.setDsc(Code.SELECT_AllLEVEL_LOGSERVER_ERROR);
				logMapper.logSave(log);
				
				return "RechargeManagement/selectAllLevel";
			}else{
				//传入一个状态码，返回一个RMIResult实体类，RMIResult的result属性为List<Level>
				RMIResult result = (RMIResult) loginServerRMI.exce(LoginRMIConstant.GM_TOOL_USR_SELECT);
				if(result.getErrorCode() == D.CODE_SUC && result.getResult() != null ){ //判断查询是否成功
					req.setAttribute("DeskChanceList", (List<Level>)result.getResult());
					
					//将查询成功存入到日志表中
					log.setStatus(ManagerLog.success);
					log.setDsc(Code.SELECT_AllLEVEL_SUC_CREROOM);
					logMapper.logSave(log);
					return "RechargeManagement/selectAllLevel";
				}else{
					//将查询失败存入到日志表中
					log.setStatus(ManagerLog.error);
					log.setDsc(Code.SELECT_AllLEVEL_GETRESULT_ERROR);
					logMapper.logSave(log);
					return "RechargeManagement/selectAllLevel";
				}
			}
		}catch(Exception e){
			e.printStackTrace();
			//将由于未知错误引起的操作失败存入日志
			log.setStatus(ManagerLog.error);
			log.setDsc(Code.SELECT_AllLEVEL_UNKNOWN_ERROR);
			logMapper.logSave(log);
		}
		return "RechargeManagement/selectAllLevel";
	}
	
	//修改充值档次
	public Result alterLevel(HttpServletRequest req,int id, int levelId, int money, int jewel)
			throws ServletException, IOException {
		ManagerLog log = new ManagerLog(); //创建日志
		Result result = new Result();  //创建返回结果
		String admin = GetAdmin.getAdmin(req);  //获取当前管理员名
		String ip = GetData.getRemoteHost(req); //获取管理员ip
		String system = GetData.getSystem(req);  //获取管理员操作系统
		Timestamp time = GetData.getTime(); //获取当前系统时间
		
		//将ip地址，系统，管理员名，当前系统时间存入log中
		log.setIp(ip);
		log.setName(admin);
		log.setSystem(system);
		log.setTime(time);
		
		//判断当前管理员是否超时
		if(admin == null){
			//保存日志
			log.setIp(ip);
			log.setName("未知");
			log.setSystem("未知");
			log.setTime(time);
			log.setStatus(ManagerLog.error);
			log.setDsc(Code.ALTER_AllLEVEL_OUTTIME);
			logMapper.logSave(log);
			
			//返回结果
			result.setStatus(-1);
			return result;
		}
		try{
			//获取登陆服务
			LoginServerRMI loginServerRMI = CacheLoginServer.getLoginServer();
			if (null == loginServerRMI) {
				//获取登陆服务失败
				log.setStatus(ManagerLog.error);
				log.setDsc(Code.ALTER_CONVERTSCALE_LOGSERVER_ERROR);
				logMapper.logSave(log);
				
				//返回结果
				result.setMsg(Code.ALTER_AllLEVEL_LOGSERVER_ERROR);
				result.setStatus(1);
				return result;
			}else{
				/*
				 * 传入一个修改钻石兑换金币的状态码及id、levelId、money、jewel，
				 * 修改通过id的值修改对应的levelId、money、jewel，
				 * 当转入的levelId已经存在，修改失败，返回修改失败的状态码。
				 */
				RMIResult result1 = (RMIResult) loginServerRMI.exce(LoginRMIConstant.GM_TOOL_USR_INSERT_BY_NAME_PWD,id,levelId,money,jewel);
				if(result1.getErrorCode() == D.CODE_SUC){
					//将修改成功存入日志
					log.setStatus(ManagerLog.success);
					log.setDsc(Code.ALTER_AllLEVEL_SUC_CREROOM);
					logMapper.logSave(log);
					
					//返回结果
					result.setMsg(Code.ALTER_AllLEVEL_SUC_CREROOM);
					result.setStatus(0);
					return result;
				}else{
					//打印失败状态码
					System.out.println(result1.getErrorCode());
					
					//将修改失败加入的日志中
					log.setStatus(ManagerLog.error);
					log.setDsc(Code.ALTER_AllLEVEL_GETRESULT_ERROR);
					logMapper.logSave(log);
					
					//返回结果
					result.setMsg(Code.ALTER_AllLEVEL_GETRESULT_ERROR);
					result.setStatus(2);
					return result;
				}
			}
		}catch(Exception e){
			e.printStackTrace();
			//将由于未知错误引起的操作失败存入日志
			log.setStatus(ManagerLog.error);
			log.setDsc(Code.ALTER_AllLEVEL_UNKNOWN_ERROR);
			logMapper.logSave(log);
			
			//返回结果
			result.setMsg(Code.ALTER_AllLEVEL_UNKNOWN_ERROR);
			result.setStatus(3);
		}
		return result;
	}
	
	//增加充值档次
	public Result addLevel(HttpServletRequest req, int levelId, int money, int jewel)
			throws ServletException, IOException {
		ManagerLog log = new ManagerLog(); //创建日志
		Result result = new Result();  //创建返回结果
		String admin = GetAdmin.getAdmin(req);  //获取当前管理员名
		String ip = GetData.getRemoteHost(req); //获取管理员ip
		String system = GetData.getSystem(req);  //获取管理员操作系统
		Timestamp time = GetData.getTime(); //获取当前系统时间
		
		//将ip地址，系统，管理员名，当前系统时间存入log中
		log.setIp(ip);
		log.setName(admin);
		log.setSystem(system);
		log.setTime(time);
		
		//判断当前管理员是否超时
		if(admin == null){
			//保存日志
			log.setIp(ip);
			log.setName("未知");
			log.setSystem("未知");
			log.setTime(time);
			log.setStatus(ManagerLog.error);
			log.setDsc(Code.ADD_AllLEVEL_OUTTIME);
			logMapper.logSave(log);
			
			//返回结果
			result.setStatus(-1);
			return result;
		}
		try{
			//获取登陆服务
			LoginServerRMI loginServerRMI = CacheLoginServer.getLoginServer();
			if (null == loginServerRMI) {
				//获取登陆服务失败
				log.setStatus(ManagerLog.error);
				log.setDsc(Code.ADD_AllLEVEL_LOGSERVER_ERROR);
				logMapper.logSave(log);
				
				//返回结果
				result.setMsg(Code.ADD_AllLEVEL_LOGSERVER_ERROR);
				result.setStatus(1);
				return result;
			}else{
				/*
				 * 传入一个修改钻石兑换金币的状态码及levelId、money、jewel，
				 * 当转入的levelId已经存在，增加失败，返回增加失败的状态码。
				 */
				RMIResult result1 = (RMIResult) loginServerRMI.exce(LoginRMIConstant.GM_TOOL_USR_INSERT_BY_NAME_PWD,levelId,money,jewel);
				if(result1.getErrorCode() == D.CODE_SUC){
					//将修改成功存入日志
					log.setStatus(ManagerLog.success);
					log.setDsc(Code.ADD_AllLEVEL_SUC_CREROOM);
					logMapper.logSave(log);
					
					//返回结果
					result.setMsg(Code.ADD_AllLEVEL_SUC_CREROOM);
					result.setStatus(0);
					return result;
				}else{
					//打印失败状态码
					System.out.println(result1.getErrorCode());
					
					//将修改失败加入的日志中
					log.setStatus(ManagerLog.error);
					log.setDsc(Code.ADD_AllLEVEL_GETRESULT_ERROR);
					logMapper.logSave(log);
					
					//返回结果
					result.setMsg(Code.ADD_AllLEVEL_GETRESULT_ERROR);
					result.setStatus(2);
					return result;
				}
			}
		}catch(Exception e){
			e.printStackTrace();
			//将由于未知错误引起的操作失败存入日志
			log.setStatus(ManagerLog.error);
			log.setDsc(Code.ADD_AllLEVEL_UNKNOWN_ERROR);
			logMapper.logSave(log);
			
			//返回结果
			result.setMsg(Code.ADD_AllLEVEL_UNKNOWN_ERROR);
			result.setStatus(3);
		}
		return result;
	}
	
	//请求增加充值档次页面
	public String toAddLevel(HttpServletRequest req) throws ServletException, IOException {
		ManagerLog log = new ManagerLog(); //创建日志
		String admin = GetAdmin.getAdmin(req);  //获取当前管理员名
		String ip = GetData.getRemoteHost(req); //获取管理员ip
		String system = GetData.getSystem(req);  //获取管理员操作系统
		Timestamp time = GetData.getTime(); //获取当前系统时间
		
		//将ip地址，系统，管理员名，当前系统时间存入log中
		log.setIp(ip);
		log.setName(admin);
		log.setSystem(system);
		log.setTime(time);
		
		//判断当前管理员是否超时
		if(admin == null){
			//保存日志
			log.setIp(ip);
			log.setName("未知");
			log.setSystem("未知");
			log.setTime(time);
			log.setStatus(ManagerLog.error);
			log.setDsc(Code.ADD_AllLEVELPAGE_OUTTIME);
			logMapper.logSave(log);
			
			//返回结果
			return "login/login";
		}
		
		//将请求成功存入到日志表中
		log.setStatus(ManagerLog.success);
		log.setDsc(Code.ADD_AllLEVELPAGE_SUC_CREROOM);
		logMapper.logSave(log);
		return "RechargeManagement/addLevel";
	}

	
	
}
