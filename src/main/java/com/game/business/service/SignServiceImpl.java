package com.game.business.service;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.game.base.util.Code;
import com.game.base.util.GetAdmin;
import com.game.base.util.GetData;
import com.game.base.util.Result;
import com.game.business.mapper.LogMapper;
import com.game.business.model.manager.ManagerLog;
import com.game.cache.CacheLoginServer;
import com.game.constant.D;
import com.game.constant.LoginRMIConstant;
import com.game.game.model.Sign;
import com.ranger.remote.LoginServerRMI;
import com.ranger.remote.RMIResult;

/**
 * <li>@ClassName: 签到管理业务层接口实现类
 * <li>@author 周强
 * <li>@date 2016年11月17日
 */

@Service
public class SignServiceImpl implements SignService{
	@Resource
	private LogMapper logMapper;
	
	//查询所有签到
	@SuppressWarnings("unchecked")
	public String selectAllSign(HttpServletRequest req) throws ServletException, IOException {
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
			log.setDsc(Code.SELECT_ALLSIGN_OUTTIME);
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
				log.setDsc(Code.SELECT_ALLSIGN_LOGSERVER_ERROR);
				logMapper.logSave(log);
				
				return "signManagement/selectAllSign";
			}else{
				//传入一个状态码，返回一个RMIResult实体类，RMIResult的result属性为List<Sign>
				RMIResult result = (RMIResult) loginServerRMI.exce(LoginRMIConstant.GM_TOOL_USR_SELECT);
				if(result.getErrorCode() == D.CODE_SUC && result.getResult() != null ){ //判断查询是否成功
					req.setAttribute("signList", (List<Sign>)result.getResult());
					
					//将所有签到查询成功存入到日志表中
					log.setStatus(ManagerLog.success);
					log.setDsc(Code.SELECT_ALLSIGN_SUC_CREROOM);
					logMapper.logSave(log);
					return "signManagement/selectAllSign";
				}else{
					//将所有签到查询失败存入到日志表中
					log.setStatus(ManagerLog.error);
					log.setDsc(Code.SELECT_ALLDOUBLE_GETRESULT_ERROR);
					logMapper.logSave(log);
					return "signManagement/selectAllSign";
				}
			}
		}catch(Exception e){
			e.printStackTrace();
			//将由于未知错误引起的操作失败存入日志
			log.setStatus(ManagerLog.error);
			log.setDsc(Code.SELECT_ALLSIGN_GETRESULT_ERROR);
			logMapper.logSave(log);
		}
		return "signManagement/selectAllSign";
	}

	//修改签到金币
	public Result alterSign(HttpServletRequest req, int gold, int id) throws ServletException, IOException {
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
			log.setDsc(Code.ALTER_ALLSIGN_OUTTIME);
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
				log.setDsc(Code.ALTER_ALLSIGN_LOGSERVER_ERROR);
				logMapper.logSave(log);
				
				//返回结果
				result.setMsg(Code.ALTER_ALLSIGN_LOGSERVER_ERROR);
				result.setStatus(1);
				return result;
			}else{
				//传入一个修改获胜概率的状态码及比倍id，gold，通过id的值修改gold。返回成功失败两种状态。
				RMIResult result1 = (RMIResult) loginServerRMI.exce(LoginRMIConstant.GM_TOOL_USR_INSERT_BY_NAME_PWD,id,gold);
				if(result1.getErrorCode() == D.CODE_SUC){
					//将修改成功存入日志
					log.setStatus(ManagerLog.success);
					log.setDsc(Code.ALTER_ALLSIGN_SUC_CREROOM);
					logMapper.logSave(log);
					
					//返回结果
					result.setMsg(Code.ALTER_ALLSIGN_SUC_CREROOM);
					result.setStatus(0);
					return result;
				}else{
					//打印失败状态码
					System.out.println(result1.getErrorCode());
					
					//将修改失败加入的日志中
					log.setStatus(ManagerLog.error);
					log.setDsc(Code.ALTER_ALLSIGN_GETRESULT_ERROR);
					logMapper.logSave(log);
					
					//返回结果
					result.setMsg(Code.ALTER_ALLSIGN_GETRESULT_ERROR);
					result.setStatus(2);
					return result;
				}
			}
		}catch(Exception e){
			e.printStackTrace();
			//将由于未知错误引起的操作失败存入日志
			log.setStatus(ManagerLog.error);
			log.setDsc(Code.ALTER_ALLSIGN_UNKNOWN_ERROR);
			logMapper.logSave(log);
			
			//返回结果
			result.setMsg(Code.ALTER_ALLSIGN_UNKNOWN_ERROR);
			result.setStatus(3);
		}
		return result;
	}

}
