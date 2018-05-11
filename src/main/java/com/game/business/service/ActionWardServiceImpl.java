package com.game.business.service;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.game.base.util.BeanFactory;
import com.game.base.util.Code;
import com.game.base.util.GetAdmin;
import com.game.base.util.GetData;
import com.game.base.util.PowerManagment;
import com.game.base.util.Result;
import com.game.business.mapper.LogMapper;
import com.game.business.model.Share;
import com.game.business.model.manager.ManagerLog;
import com.game.cache.CacheGameServer;
import com.game.cache.CacheLoginServer;
import com.game.constant.D;
import com.game.constant.LoginRMIConstant;
import com.game.constant.RMIConstant;
import com.game.game.mapper.ShareMapper;
import com.game.game.model.ActionWard;
import com.ranger.remote.LoginServerRMI;
import com.ranger.remote.RMIResult;

import cocl.rmi.GameRMIServer;

/**
 * <li>@ClassName: 活动奖励管理业务层接口实现类
 * <li>@author 周强
 * <li>@date 2016年11月17日
 */

@Service
public class ActionWardServiceImpl implements ActionWardService{
	/**分享奖励*/
	@Resource
	private ShareMapper shareMapper;
	@Resource
	private LogMapper logMapper;
	
	//查询所有活动奖励
	@SuppressWarnings("unchecked")
	public String selectAllActionWard(HttpServletRequest req) throws ServletException, IOException {
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
			log.setDsc(Code.SELECT_ACTIONWARD_OUTTIME);
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
				log.setDsc(Code.SELECT_ACTIONWARD_LOGSERVER_ERROR);
				logMapper.logSave(log);
				
				return "landingWardManagement/selectAllLandingWard";
			}else{
				//传入一个状态码，返回一个RMIResult实体类，RMIResult的result属性为List<ActionWard>
				RMIResult result = (RMIResult) loginServerRMI.exce(LoginRMIConstant.GM_TOOL_USR_SELECT);
				if(result.getErrorCode() == D.CODE_SUC && result.getResult() != null ){ //判断查询是否成功
					req.setAttribute("actionWardList", (List<ActionWard>)result.getResult());
					
					//将所有活动奖励查询成功存入到日志表中
					log.setStatus(ManagerLog.success);
					log.setDsc(Code.SELECT_ACTIONWARD_SUC_CREROOM);
					logMapper.logSave(log);
					return "landingWardManagement/selectAllLandingWard";
				}else{
					//将所有签到查询失败存入到日志表中
					log.setStatus(ManagerLog.error);
					log.setDsc(Code.SELECT_ACTIONWARD_GETRESULT_ERROR);
					logMapper.logSave(log);
					return "landingWardManagement/selectAllLandingWard";
				}
			}
		}catch(Exception e){
			e.printStackTrace();
			//将由于未知错误引起的操作失败存入日志
			log.setStatus(ManagerLog.error);
			log.setDsc(Code.SELECT_ACTIONWARD_UNKNOWN_ERROR);
			logMapper.logSave(log);
		}
		return "actionWardManagement/selectAllActionWard";
	}
	
	//查询分享奖励
	@SuppressWarnings("unchecked")
	@Transactional
	public String selectShareWard(HttpServletRequest req) throws ServletException, IOException {
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
			log.setDsc(Code.SELECT_ACTIONWARD_OUTTIME);
			logMapper.logSave(log);
			
			//返回结果
			return "login/login";
		}
		//获取游戏服连接
		GameRMIServer gameRMIServer = CacheGameServer.getGameServer();
		if(gameRMIServer == null){
			System.out.println("111111111111111111111111111111111111111111111111");
			//保存日志
			log.setStatus(ManagerLog.error);
			log.setDsc("查询分享奖励:未获得服务器连接!");
			//返回结果
			return "actionWardManagement/selectShareWard";
			
		}

		
		
		
		/*Map<String,Object> map=new HashMap<String,Object>();
		map.put("id", 1);
		map.put("prize", 55);*/
		Map<String,Map<String , Object>> map;
		try{
			map= (Map<String,Map<String , Object>>) gameRMIServer.exec(RMIConstant.GM_TOOL_FIND_SHARE_PRIZE);
				System.out.println("---------------------------11map.share:"+map.get("share"));
			if(map.get("share").size()==0){
				System.out.println("---------------------------map=空");
				int m= (Integer) gameRMIServer.exec(RMIConstant.GM_TOOL_INSERT_SHARE_PRIZE,1,1);
				System.out.println("---------------------------返回插入:"+m);
				if(m==0){
				System.out.println("---------------------------插入数据成功");
					map= (Map<String,Map<String , Object>>) gameRMIServer.exec(RMIConstant.GM_TOOL_FIND_SHARE_PRIZE);
				}
			}
				System.out.println("---------------------------map.size="+map.size());
			
			if(map.size()!=0){
				System.out.println("---------------------------map!=null");
				Share share=new Share();
				System.out.println("---------------------------map="+map);
				share.setGameId((Integer) map.get("share").get("id"));
				share.setNumber((Integer) map.get("share").get("prize"));
				req.setAttribute("share", share);
				log.setStatus(ManagerLog.success);
				log.setDsc("查询分享奖励:成功");
				logMapper.logSave(log);
				return "actionWardManagement/selectShareWard";
			}else{
				log.setStatus(ManagerLog.error);
				log.setDsc("查询分享奖励:失败未从服务器获得数据");
				logMapper.logSave(log);
				return "actionWardManagement/selectShareWard";
			}
		}catch(Exception e){
			e.printStackTrace();
			log.setStatus(ManagerLog.error);
			log.setDsc("查询分享奖励:未知异常");
			logMapper.logSave(log);
		}
		return "actionWardManagement/selectShareWard";
	}
	
	
	//修改活动奖励
	public Result alterActionWard(HttpServletRequest req, int id,String wardName, int gold)
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
			log.setDsc(Code.ALTER_ACTIONWARD_OUTTIME);
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
				log.setDsc(Code.ALTER_ACTIONWARD_LOGSERVER_ERROR);
				logMapper.logSave(log);
				
				//返回结果
				result.setMsg(Code.ALTER_ACTIONWARD_LOGSERVER_ERROR);
				result.setStatus(1);
				return result;
			}else{
				//传入一个修改获胜概率的状态码及比倍id,wardName,gold，通过id的值修改wardName,gold。返回成功失败两种状态。
				RMIResult result1 = (RMIResult) loginServerRMI.exce(LoginRMIConstant.GM_TOOL_USR_INSERT_BY_NAME_PWD,id,wardName,gold);
				if(result1.getErrorCode() == D.CODE_SUC){
					//将修改成功存入日志
					log.setStatus(ManagerLog.success);
					log.setDsc(Code.ALTER_ACTIONWARD_SUC_CREROOM);
					logMapper.logSave(log);
					
					//返回结果
					result.setMsg(Code.ALTER_ACTIONWARD_SUC_CREROOM);
					result.setStatus(0);
					return result;
				}else{
					//打印失败状态码
					System.out.println(result1.getErrorCode());
					
					//将修改失败加入的日志中
					log.setStatus(ManagerLog.error);
					log.setDsc(Code.ALTER_ACTIONWARD_GETRESULT_ERROR);
					logMapper.logSave(log);
					
					//返回结果
					result.setMsg(Code.ALTER_ACTIONWARD_GETRESULT_ERROR);
					result.setStatus(2);
					return result;
				}
			}
		}catch(Exception e){
			e.printStackTrace();
			//将由于未知错误引起的操作失败存入日志
			log.setStatus(ManagerLog.error);
			log.setDsc(Code.ALTER_ACTIONWARD_UNKNOWN_ERROR);
			logMapper.logSave(log);
			
			//返回结果
			result.setMsg(Code.ALTER_ACTIONWARD_UNKNOWN_ERROR);
			result.setStatus(3);
		}
		return result;
	}
	//请求分享元宝奖励页面
	public String updatePrize(HttpServletRequest req) throws ServletException, IOException {
		String admin = GetAdmin.getAdmin(req);   	
		GetData getData = (GetData) BeanFactory.getBean(GetData.class);
		
		//判断当前管理员或者代理商是否超时
		if(admin == null){
			//保存日志
			getData.saveCurrentLog(req, Code.SELECT_INFORMATION_OUTTIME, ManagerLog.error);
			//返回结果
			return "login/login";
		}
		//获取游戏服连接
				GameRMIServer gameRMIServer = CacheGameServer.getGameServer();
				if(gameRMIServer == null){
					//保存日志
					getData.saveCurrentLog(req,"查询分享奖励:未获得服务器连接!",ManagerLog.error);
					//返回结果
					return "actionWardManagement/updatePrize";
					
				}
				@SuppressWarnings("unchecked")
				Map<String,Map<String , Object>> map= (Map<String,Map<String , Object>>) gameRMIServer.exec(RMIConstant.GM_TOOL_FIND_SHARE_PRIZE);
		try{
				
			if(map!=null){
				Share share = new Share();
				share.setGameId((Integer)map.get("share").get("id"));
				share.setNumber((Integer)map.get("share").get("prize"));
				req.setAttribute("share", share);
				getData.saveCurrentLog(req, "请求修改分享奖励页面:成功", ManagerLog.success);
				return "actionWardManagement/updatePrize";
			}else{
				getData.saveCurrentLog(req, "请求修改分享奖励页面:未从服务器获得数据", ManagerLog.error);
				return "actionWardManagement/updatePrize";
			}
		}catch(Exception e){
			e.printStackTrace();
			getData.saveCurrentLog(req,"请求修改分享奖励页面:失败", ManagerLog.error);
		}
		return "actionWardManagement/updatePrize";
	}
//修改分享元宝奖励
	public Result toUpdatePrize(HttpServletRequest req, int id, int prize) throws ServletException, IOException {
		
		Result result = new Result();  //创建返回结果
		String admin = GetAdmin.getAdmin(req);   						  //获取当前管理员名
		GetData getData = (GetData) BeanFactory.getBean(GetData.class);
		//判断当前管理员或者代理商是否超时
				if(admin == null){
					//保存日志
					getData.saveCurrentLog(req, Code.ALERT_INFORMATION_OUTTIME, ManagerLog.error);
					
					//返回结果
					result.setStatus(-1);
					return result;
				}
				//获取游戏服连接
				GameRMIServer gameRMIServer = CacheGameServer.getGameServer();
				if(gameRMIServer == null){
					//保存日志
					result.setMsg("修改失败:未获得服务器连接");
					result.setStatus(3);
					getData.saveCurrentLog(req, "修改分享奖励:未获得服务器连接!",ManagerLog.error );
					//返回结果
					return result;
					
				}
				
				try{
				int n=PowerManagment.checkOperCategory(admin);
				if(n==1){
					Integer m= (Integer) gameRMIServer.exec(RMIConstant.GM_TOOL_UPDATE_SHARE_PRIZE,id,prize);
					System.out.println("--------------------------修改分享奖励 服务器返回:"+m);
					if(m!=0){
						//保存日志
						result.setMsg("修改失败:未知错误");
						result.setStatus(5);
						getData.saveCurrentLog(req, "修改分享奖励:服务器修改失败未知错误!",ManagerLog.error );
						//返回结果
						return result;
					}
					result.setMsg("修改成功");
					result.setStatus(0);
					//保存日志
					getData.saveCurrentLog(req, "修改分享奖励:修改成功", ManagerLog.success);
					return result;
				}else{
					//保存日志
					getData.saveCurrentLog(req, "修改分享奖励:不是管理员权限不足失败", ManagerLog.error);
					//返回结果
					result.setMsg("修改分享奖励:不是管理员权限不足失败");
					result.setStatus(3);
					return result;
				}
				}catch(Exception e){
					e.printStackTrace();
					//保存日志
					getData.saveCurrentLog(req, "修改分享奖励:未知错误失败", ManagerLog.error);
					result.setMsg("修改分享奖励:未知错误失败");
					result.setStatus(4);
				}
		return result;
	}

	

}
