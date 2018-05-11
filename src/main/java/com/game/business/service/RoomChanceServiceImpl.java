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
import com.game.game.model.DeskChance;
import com.game.game.model.RoomChance;
import com.ranger.remote.LoginServerRMI;
import com.ranger.remote.RMIResult;
/**
 * <li>@ClassName: 房间概率管理业务层实现类
 * <li>@author 周强
 * <li>@date 2016年11月21日
 */
@Service
public class RoomChanceServiceImpl implements RoomChanceService{
	@Resource
	private LogMapper logMapper;
	
	//查询所有房间概率
	@SuppressWarnings("unchecked")
	public String selectAllRoomChance(HttpServletRequest req) throws ServletException, IOException {
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
			log.setDsc(Code.SELECT_ALLROOMCHANCE_OUTTIME);
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
				log.setDsc(Code.SELECT_ALLROOMCHANCE_LOGSERVER_ERROR);
				logMapper.logSave(log);
				
				return "roomChanceManagement/selectAllRoomChance";
			}else{
				//传入一个状态码，返回一个RMIResult实体类，RMIResult的result属性为List<RoomChance>
				RMIResult result = (RMIResult) loginServerRMI.exce(LoginRMIConstant.GM_TOOL_USR_SELECT);
				if(result.getErrorCode() == D.CODE_SUC && result.getResult() != null ){ //判断查询是否成功
					req.setAttribute("levelList", (List<RoomChance>)result.getResult());
					
					//将查询成功存入到日志表中
					log.setStatus(ManagerLog.success);
					log.setDsc(Code.SELECT_ALLROOMCHANCE_SUC_CREROOM);
					logMapper.logSave(log);
					return "roomChanceManagement/selectAllRoomChance";
				}else{
					//将查询失败存入到日志表中
					log.setStatus(ManagerLog.error);
					log.setDsc(Code.SELECT_ALLROOMCHANCE_GETRESULT_ERROR);
					logMapper.logSave(log);
					return "roomChanceManagement/selectAllRoomChance";
				}
			}
		}catch(Exception e){
			e.printStackTrace();
			//将由于未知错误引起的操作失败存入日志
			log.setStatus(ManagerLog.error);
			log.setDsc(Code.SELECT_ALLROOMCHANCE_UNKNOWN_ERROR);
			logMapper.logSave(log);
		}
		return "roomChanceManagement/selectAllRoomChance";
	}
	
	//请求修改房间概率页面
	public String toAlterRoomChance(HttpServletRequest req) throws ServletException, IOException {
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
			log.setDsc(Code.ALTER_ROOMCHANCEPAGE_OUTTIME);
			logMapper.logSave(log);
			
			//返回结果
			return "login/login";
		}
		try{
			req.setAttribute("id", (String)req.getParameter("id"));
			req.setAttribute("K5", (String)req.getParameter("K5"));
			req.setAttribute("RS", (String)req.getParameter("RS"));
			req.setAttribute("SF", (String)req.getParameter("SF"));
			req.setAttribute("K4", (String)req.getParameter("K4"));
			req.setAttribute("FH", (String)req.getParameter("FH"));
			req.setAttribute("FL", (String)req.getParameter("FL"));
			req.setAttribute("ST", (String)req.getParameter("ST"));
			req.setAttribute("K3", (String)req.getParameter("P2"));
			req.setAttribute("P2", (String)req.getParameter("P2"));
			req.setAttribute("P1", (String)req.getParameter("P1"));
			req.setAttribute("NP", (String)req.getParameter("NP"));
			req.setAttribute("top", (String)req.getParameter("top"));
			
			//将查询成功存入到日志表中
			log.setStatus(ManagerLog.success);
			log.setDsc(Code.ALTER_ROOMCHANCEPAGE_SUC_CREROOM);
			logMapper.logSave(log);
			return "roomChanceManagement/alterRoomChance";
		}catch(Exception e){
			e.printStackTrace();
			
			//将由于未知错误引起的操作失败存入日志
			log.setStatus(ManagerLog.error);
			log.setDsc(Code.ALTER_ROOMCHANCEPAGE_UNKNOWN_ERROR);
			logMapper.logSave(log);
		}
		
		return "roomChanceManagement/alterRoomChance";
	}
	
	//修改桌子概率
	public Result alterRoomChance(HttpServletRequest req, int id, int K5, int RS, int SF, int K4, int FH, int FL,
			int ST, int K3, int P2, int P1, int NP, int top) throws ServletException, IOException {
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
			log.setDsc(Code.ALTER_ROOMCHANCE_OUTTIME);
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
				log.setDsc(Code.ALTER_ROOMCHANCE_LOGSERVER_ERROR);
				logMapper.logSave(log);
				
				//返回结果
				result.setMsg(Code.ALTER_ROOMCHANCE_LOGSERVER_ERROR);
				result.setStatus(1);
				return result;
			}else{
				RoomChance rc = new RoomChance();
				rc.setFH(FH);
				rc.setFL(FL);
				rc.setId(id);
				rc.setK3(K3);
				rc.setK4(K4);
				rc.setK5(K5);
				rc.setNP(NP);
				rc.setP1(P1);
				rc.setP2(P2);
				rc.setRS(RS);
				rc.setSF(SF);
				rc.setST(ST);
				rc.setTop(top);
				
				//传入一个状态码和实体类RoomChance，通过实体类RoomChance的id值定位修改那条记录.返回成功和失败两种状态，不需要判断是否重名的问题
				RMIResult result1 = (RMIResult) loginServerRMI.exce(LoginRMIConstant.GM_TOOL_USR_INSERT_BY_NAME_PWD,rc);
				if(result1.getErrorCode() == D.CODE_SUC){
					//将修改成功存入日志
					log.setStatus(ManagerLog.success);
					log.setDsc(Code.ALTER_ROOMCHANCE_SUC_CREROOM);
					logMapper.logSave(log);
					
					//返回结果
					result.setMsg(Code.ALTER_ROOMCHANCE_SUC_CREROOM);
					result.setStatus(0);
					return result;
				}else{
					//打印失败状态码
					System.out.println(result1.getErrorCode());
					
					//将修改失败加入的日志中
					log.setStatus(ManagerLog.error);
					log.setDsc(Code.ALTER_ROOMCHANCE_GETRESULT_ERROR);
					logMapper.logSave(log);
					
					//返回结果
					result.setMsg(Code.ALTER_ROOMCHANCE_GETRESULT_ERROR);
					result.setStatus(2);
					return result;
				}
			}
		}catch(Exception e){
			e.printStackTrace();
			//将由于未知错误引起的操作失败存入日志
			log.setStatus(ManagerLog.error);
			log.setDsc(Code.ALTER_ROOMCHANCE_UNKNOWN_ERROR);
			logMapper.logSave(log);
			
			//返回结果
			result.setMsg(Code.ALTER_ROOMCHANCE_UNKNOWN_ERROR);
			result.setStatus(3);
		}
		return result;
	}

}
