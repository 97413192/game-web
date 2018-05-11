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
import com.game.game.model.Desk;
import com.game.game.model.WaitDesk;
import com.ranger.module.po.Usr;
import com.ranger.remote.LoginServerRMI;
import com.ranger.remote.RMIResult;
/**
 * <li>@ClassName: 留桌管理业务层实现类
 * <li>@author 周强
 * <li>@date 2016年11月15日
 */
@Service
public class WaitDeskServiceImpl implements WaitDeskService{
	@Resource
	private LogMapper logMapper;
	//请求创建留桌设置页面
	public String toAddWaitDesk(HttpServletRequest req) throws ServletException, IOException {
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
			log.setDsc(Code.ADD_WAITDESKPAGE_OUTTIME);
			logMapper.logSave(log);
			
			//返回结果
			return "login/login";
		}
		//成功
		log.setStatus(ManagerLog.error);
		log.setDsc(Code.ADD_WAITDESKPAGE_SUC_CREROOM);
		logMapper.logSave(log);
		
		return "waitDeskManagement/addWaitDesk";
	}
	
	//增加留桌查看配置
	public Result addWaitDesk(HttpServletRequest req, int time1, int gold, String typeName, int typeId)
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
			log.setDsc(Code.ADD_WAITDESK_OUTTIME);
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
				log.setDsc(Code.ADD_DESK_LOGSERVER_ERROR);
				logMapper.logSave(log);
				
				//返回结果
				result.setMsg(Code.ADD_DESK_LOGSERVER_ERROR);
				result.setStatus(1);
				return result;
			}else{
				WaitDesk wd = new WaitDesk();
				wd.setGold(gold);
				wd.setTime(time1);
				wd.setTypeId(typeId);
				wd.setTypeName(typeName);
				/*
				 * 增加留桌设置，传入一个WaitDesk实体类，WaitDesk的id在数据库中自增，
				 * 返回的RMIResult的状态码要分辨typeName和typeId如果存在，
				 * 存在不能再次添加，并返回给页面提示
				 */
				RMIResult result1 = (RMIResult) loginServerRMI.exce(LoginRMIConstant.GM_TOOL_USR_INSERT_BY_NAME_PWD,wd);
				if(result1.getErrorCode() == D.CODE_SUC && result1.getResult() != null){  //判断查询成功时
					//将留桌设置的添加成功存入到日志表中
					log.setStatus(ManagerLog.success);
					log.setDsc(Code.ADD_WAITDESK_SUC_CREROOM);
					logMapper.logSave(log);
					
					//返回结果,并将查出room集合信息返回给页面
					result.setMsg(Code.ADD_WAITDESK_SUC_CREROOM);
					result.setStatus(0);
					return result;
				}else{
					//打印增加桌子失败状态码
					System.out.println(result1.getErrorCode());
					
					//将增加桌子失败加入的日志中
					log.setStatus(ManagerLog.error);
					log.setDsc(Code.ADD_WAITDESK_GETRESULT_ERROR);
					logMapper.logSave(log);
					
					//返回结果
					result.setMsg(Code.ADD_WAITDESK_GETRESULT_ERROR);
					result.setStatus(2);
					return result;
				}
			}
		}catch(Exception e){
			e.printStackTrace();
			
			//将由于未知错误引起的操作失败存入日志
			log.setStatus(ManagerLog.error);
			log.setDsc(Code.ADD_WAITDESK_UNKNOWN_ERROR);
			logMapper.logSave(log);
			
			//返回结果
			result.setMsg(Code.ADD_WAITDESK_UNKNOWN_ERROR);
			result.setStatus(3);
		}
		return result;
	}
	
	//请求查看所有留桌查看设置
	public String selectAllWaitDesk(HttpServletRequest req,String WaitDeskIndex) throws ServletException, IOException {
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
			log.setDsc(Code.SELECT_ALLWAITDESK_OUTTIME);
			logMapper.logSave(log);
			
			//返回结果
			return "login/login";
		}
		try{
			int indexpage = Integer.valueOf(WaitDeskIndex);
			//判断index是否为第一次请求
			if(indexpage <0){
				indexpage = 1;
			}
			
			//连接登陆服务
			LoginServerRMI loginServerRMI = CacheLoginServer.getLoginServer();
			if (null == loginServerRMI) {
				//将玩家查询失败存入到日志表中
				log.setStatus(ManagerLog.error);
				log.setDsc(Code.SELECT_ALLWAITDESK_LOGSERVER_ERROR);
				logMapper.logSave(log);
				
				return "waitDeskManagement/selectAllWaitDesk";
			}else{
				//传入一个状态码，返回一个RMIResult实体类，RMIResult的result属性为List<WaitDesk>
				RMIResult result1 = (RMIResult) loginServerRMI.exce(LoginRMIConstant.GM_TOOL_USR_SELECT);
				if(result1.getErrorCode() == D.CODE_SUC && result1.getResult() != null ){ //判断查询是否成功
					@SuppressWarnings("unchecked")
					List<WaitDesk> totalList = (List<WaitDesk>)result1.getResult();  //获取所有玩家
					int count = totalList.size();  //获取玩家总个数
					int pagesize = 20;  //定义每页显示的记录数
					int pagecount;  //定义总共有多少页面
					List<WaitDesk> list = null; //此次请求页面的记录数
					
					//判断count的条数是够显示一个页面
					if(count<pagesize){ //总记录数不足显示一个页面，第一页的显示的记录为count条
						pagesize = count;  //显示的记录数
						pagecount = 1;  //总页面数为1
						list = totalList.subList(0, count);
					}else{
						if(count%pagesize == 0){  //判断总记录数是否能被每页显示的条数整除
							pagecount = count/pagesize;  //总页面数
							//每页显示记录数
							list = totalList.subList(pagesize*indexpage-pagesize, pagesize*indexpage);
						}else{
							pagecount = count/pagesize+1; //当总页面数不能被pagesize整除时的：总页面数
							/*
							 * 当总页面数不能被pagesize整除时的数目，
							 * 并且请求显示最后一页的数目时
							 */
							if(pagecount == indexpage){
								//最后一页显示的记录（不足pagesize条个记录）
								list = totalList.subList(pagesize*indexpage-pagesize, (pagesize*indexpage-pagesize)+(count-pagesize*indexpage+pagesize));
							}else{
								//非最后一下显示的记录数
								list = totalList.subList(pagesize*indexpage-pagesize, pagesize*indexpage);
							}
						}
					}
					
					/*
					 * 将日志总的条数用req保存
					 * 方便转发到JSP页面取值，和后面请求页面分页显示取值
					 * 注意：count是动态变化的
					 */
					req.setAttribute("waitDeskCount", count);
					req.setAttribute("waitDeskIndex", indexpage);  //将传进来的index页面数保存到session中
					req.setAttribute("waitDeskPagesize", pagesize);  //将每页显示的记录数存入session中
					req.setAttribute("waitDeskPagecount", pagecount);  //将pagecount用session保存
					req.setAttribute("waitDeskList", list);  //将此次要显示的记录放入计划list中
					
					//将所有留桌查询成功存入到日志表中
					log.setStatus(ManagerLog.success);
					log.setDsc(Code.SELECT_ALLWAITDESK_UNKNOWN_ERROR);
					logMapper.logSave(log);
					return "waitDeskManagement/selectAllWaitDesk";
				}else{
					//将玩家查询失败存入到日志表中
					log.setStatus(ManagerLog.error);
					log.setDsc(Code.SELECT_ALLWAITDESK_SUC_CREROOM);
					logMapper.logSave(log);
					return "waitDeskManagement/selectAllWaitDesk";
				}
			}
		}catch(Exception e){
			e.printStackTrace();
			//将由于未知错误引起的操作失败存入日志
			log.setStatus(ManagerLog.error);
			log.setDsc(Code.SELECT_ALLWAITDESK_GETRESULT_ERROR);
			logMapper.logSave(log);
		}
		return "waitDeskManagement/selectAllWaitDesk";
	}
	
	//修改留桌查看配置
	public Result alterWaitDesk(HttpServletRequest req, int id, int time1, int gold, String typeName, int typeId)
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
			log.setDsc(Code.ALTER_WAITDESK_OUTTIME);
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
				log.setDsc(Code.ALTER_WAITDESK_LOGSERVER_ERROR);
				logMapper.logSave(log);
				
				//返回结果
				result.setMsg(Code.ALTER_WAITDESK_LOGSERVER_ERROR);
				result.setStatus(1);
				return result;
			}else{
				//传入一个状态码和typeName，返回一个RMIResult的result属性为一个WaitDesk实体类，确定名字是否已存在
				RMIResult result1 = (RMIResult) loginServerRMI.exce(LoginRMIConstant.GM_TOOL_USR_INSERT_BY_NAME_PWD,typeName);
				//传入一个状态码和typeId，返回一个RMIResult的result属性为一个WaitDesk实体类，确定typeId是否已存在
				RMIResult result2 = (RMIResult) loginServerRMI.exce(LoginRMIConstant.GM_TOOL_USR_INSERT_BY_NAME_PWD,typeId);
				if(result1.getErrorCode() == D.CODE_SUC && result1.getResult() != null && result2.getErrorCode() == D.CODE_SUC && result2.getResult() != null){
					//typeName或者typeID已经存在，保存到日志中
					log.setStatus(ManagerLog.error);
					log.setDsc(Code.ALTER_WAITDESK_NAMEANDID_ERROR);
					logMapper.logSave(log);
					
					//返回结果
					result.setMsg(Code.ALTER_WAITDESK_NAMEANDID_ERROR);
					result.setStatus(2);
					return result;
				//必须判断传入的typeName和typeId不存在，需要传回两了状态码
				}else if(result1.getErrorCode() == D.CMD_PAY && result2.getErrorCode() == D.CMD_GET_DISTRICT){
					WaitDesk wd = new WaitDesk();
					wd.setId(id);
					wd.setGold(gold);
					wd.setTime(time1);
					wd.setTypeId(typeId);
					wd.setTypeName(typeName);
					//传入一个WaitDesk实体类，返回成功状态码，通过WaitDesk的id来确认修改哪个WaitDesk
					RMIResult result3 = (RMIResult) loginServerRMI.exce(LoginRMIConstant.GM_TOOL_USR_INSERT_BY_NAME_PWD,wd);
					if(result3.getErrorCode() == D.CODE_SUC){  //判断查询成功时
						//将留桌设置的修改成功存入到日志表中
						log.setStatus(ManagerLog.success);
						log.setDsc(Code.ALTER_WAITDESK_SUC_CREROOM);
						logMapper.logSave(log);
						
						//返回结果,并将查出room集合信息返回给页面
						result.setMsg(Code.ALTER_WAITDESK_SUC_CREROOM);
						result.setStatus(0);
						return result;
					}else{
						//打印增加桌子失败状态码
						System.out.println(result3.getErrorCode());
						
						//将增加桌子失败加入的日志中
						log.setStatus(ManagerLog.error);
						log.setDsc(Code.ALTER_WAITDESK_GETRESULT_ERROR);
						logMapper.logSave(log);
						
						//返回结果
						result.setMsg(Code.ALTER_WAITDESK_GETRESULT_ERROR);
						result.setStatus(3);
						return result;
					}
				}else{
					//传入typeName或者typeID由于登陆服务原因，查询失败。保存日志
					log.setStatus(ManagerLog.error);
					log.setDsc(Code.ALTER_WAITDESK_FINDNAMEANDID_ERROR);
					logMapper.logSave(log);
					
					//返回结果
					result.setMsg(Code.ALTER_WAITDESK_FINDNAMEANDID_ERROR);
					result.setStatus(2);
					return result;
				}
			}
		}catch(Exception e){
			e.printStackTrace();
			//将由于未知错误引起的操作失败存入日志
			log.setStatus(ManagerLog.error);
			log.setDsc(Code.ALTER_WAITDESK_UNKNOWN_ERROR);
			logMapper.logSave(log);
			
			//返回结果
			result.setMsg(Code.ALTER_WAITDESK_UNKNOWN_ERROR);
			result.setStatus(4);
		}
		return result;
	}
	
	//通过查询所有链接到修改页面
	public String toAlterWaitDesk(HttpServletRequest req, int id)throws ServletException, IOException {
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
			log.setDsc(Code.ALTER_WAITDESKPAGE_OUTTIME);
			logMapper.logSave(log);
			
			//返回结果
			return "login/login";
		}
		//成功
		req.setAttribute("id", id);  //将id的值保存到req中
		
		log.setStatus(ManagerLog.error);
		log.setDsc(Code.ALTER_WAITDESKPAGE_SUC_CREROOM);
		logMapper.logSave(log);
		
		return "waitDeskManagement/alterWaitDesk";
	}
	
	//通过查询所有的增加到已有的typeName或者typeId的留桌查看设置的增加页面
	public String toAllWaitDesk_selectAll(HttpServletRequest req, String typeName, int typeId)
			throws ServletException, IOException {
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
			log.setDsc(Code.ADD_WAITDESKPAGE_OUTTIME);
			logMapper.logSave(log);
			
			//返回结果
			return "login/login";
		}
		//成功
		req.setAttribute("typeName", typeName);  //将typeName的值保存到req中
		req.setAttribute("typeId", typeId);  //将typeId的值保存到req中
		
		log.setStatus(ManagerLog.error);
		log.setDsc(Code.ADD_WAITDESKPAGE_SUC_CREROOM);
		logMapper.logSave(log);
		
		return "waitDeskManagement/addWaitDesk_selectAll";
	}

}

























