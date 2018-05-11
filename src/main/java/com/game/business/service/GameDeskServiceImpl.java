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
import com.game.cache.CacheGameServer;
import com.game.cache.CacheLoginServer;
import com.game.constant.D;
import com.game.constant.LoginRMIConstant;
import com.game.constant.RMIConstant;
import com.game.game.model.Desk;
import com.game.game.model.Game;
import com.game.game.model.Room;
import com.ranger.remote.LoginServerRMI;
import com.ranger.remote.RMIResult;

import cocl.rmi.GameRMIServer;
/**
 * <li>@ClassName: 游戏桌子业务层
 * <li>@author 周强
 * <li>@date 2016年11月14日
 */
@Service
public class GameDeskServiceImpl implements GameDeskService{
	@Resource
	private LogMapper logMapper;
	//请求创建游戏桌子页面
	public String toAddDesk(HttpServletRequest req) throws ServletException, IOException {
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
			log.setDsc(Code.ADD_DESKPAGE_OUTTIME);
			logMapper.logSave(log);
			
			//返回结果
			return "login/login";
		}
		try{
			//获取登陆服务
			LoginServerRMI loginServerRMI = CacheLoginServer.getLoginServer();
			if (null == loginServerRMI) {
				//获取登陆服务失败
				log.setStatus(ManagerLog.error);
				log.setDsc(Code.ADD_DESKPAGE_LOGSERVER_ERROR);
				logMapper.logSave(log);
				
				//返回结果
				return "login/login";
			}else{
				//查询所有游戏名，,返回值为List<game>
				RMIResult result = (RMIResult) loginServerRMI.exce(LoginRMIConstant.GM_TOOL_USR_INSERT_BY_NAME_PWD);
				if(result.getErrorCode() == D.CODE_SUC && result.getResult() !=null){  //判断查询成功时
					//将房间的查询成功存入到日志表中
					log.setStatus(ManagerLog.success);
					log.setDsc(Code.ADD_DESKPAGE_SUC_CREROOM);
					logMapper.logSave(log);
					
					//将所有游戏信息存入req中
					req.setAttribute("games", (List<Game>)result.getResult());
					
					//返回结果
					return "gameDeskManagement/addDesk";
				}else{
					//打印查询失败状态吗
					System.out.println(result.getErrorCode());
					
					//将查询失败加入的日志中
					log.setStatus(ManagerLog.error);
					log.setDsc(Code.ADD_DESKPAGE_GETRESULT_ERROR);
					logMapper.logSave(log);
					
					//返回结果
					return "login/login";
				}
			}
		}catch(Exception e){
			e.printStackTrace();
			//将由于未知错误引起的操作失败存入日志
			log.setStatus(ManagerLog.error);
			log.setDsc(Code.ADD_DESKPAGE_UNKNOWN_ERROR);
			logMapper.logSave(log);
		}
		return "login/login";
	}
	//通过游戏id获取房间名
	public Result getRoom(HttpServletRequest req, String gameId) throws ServletException, IOException {
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
			log.setDsc(Code.GET_ROOMNAME_OUTTIME);
			logMapper.logSave(log);
			
			//返回结果
			result.setMsg(Code.GET_ROOMNAME_OUTTIME);
			result.setStatus(-1);
			return result;
		}
		try{
			//获取登陆服务
			LoginServerRMI loginServerRMI = CacheLoginServer.getLoginServer();
			if (null == loginServerRMI) {
				//获取登陆服务失败
				log.setStatus(ManagerLog.error);
				log.setDsc(Code.GET_ROOMNAME_LOGSERVER_ERROR);
				logMapper.logSave(log);
				
				//返回结果
				result.setMsg(Code.GET_ROOMNAME_LOGSERVER_ERROR);
				result.setStatus(1);
				return result;
			}else{
				//通过游戏id查询此游戏下所有房间,返回值为List<Room>
				RMIResult result1 = (RMIResult) loginServerRMI.exce(LoginRMIConstant.GM_TOOL_USR_INSERT_BY_NAME_PWD,gameId);
				if(result1.getErrorCode() == D.CODE_SUC && result1.getResult() !=null){  //判断查询成功时
					//将房间的查询成功存入到日志表中
					log.setStatus(ManagerLog.success);
					log.setDsc(Code.GET_ROOMNAME_SUC_CREROOM);
					logMapper.logSave(log);
					
					//返回结果,并将查出room集合信息返回给页面
					result.setMsg(Code.GET_ROOMNAME_SUC_CREROOM);
					result.setStatus(0);
					result.setData((List<Room>)result1.getResult());
					return result;
				}else{
					//打印查询失败状态码
					System.out.println(result1.getErrorCode());
					
					//将查询失败加入的日志中
					log.setStatus(ManagerLog.error);
					log.setDsc(Code.GET_ROOMNAME_GETRESULT_ERROR);
					logMapper.logSave(log);
					
					//返回结果
					result.setMsg(Code.GET_ROOMNAME_GETRESULT_ERROR);
					result.setStatus(2);
					return result;
				}
			}
		}catch(Exception e){
			e.printStackTrace();
			
			//将由于未知错误引起的操作失败存入日志
			log.setStatus(ManagerLog.error);
			log.setDsc(Code.GET_ROOMNAME_UNKNOWN_ERROR);
			logMapper.logSave(log);
			
			//返回结果
			result.setMsg(Code.GET_ROOMNAME_UNKNOWN_ERROR);
			result.setStatus(3);
		}
		return result;
	}
	
	//增加桌子
	public Result addRoom(HttpServletRequest req, String gameId, String roomId, String maxGoldStock,
			String curGoldStock) throws ServletException, IOException {
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
			log.setDsc(Code.ADD_DESK_OUTTIME);
			logMapper.logSave(log);
			
			//返回结果
			result.setMsg(Code.ADD_DESK_OUTTIME);
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
				Desk desk = new Desk();
				desk.setCurGoldStock(Integer.valueOf(curGoldStock));
				desk.setMaxGoldStock(Integer.valueOf(maxGoldStock));
				desk.setGameid(Integer.valueOf(gameId));
				desk.setRoomid(Integer.valueOf(roomId));
				//增加桌子，传入一个Desk实体类，desk的id在数据库中自增，额外皮子分按照数据库默认为0
				RMIResult result1 = (RMIResult) loginServerRMI.exce(LoginRMIConstant.GM_TOOL_USR_INSERT_BY_NAME_PWD,desk);
				if(result1.getErrorCode() == D.CODE_SUC){  //判断查询成功时
					//将桌子的添加成功存入到日志表中
					log.setStatus(ManagerLog.success);
					log.setDsc(Code.ADD_DESK_SUC_CREROOM);
					logMapper.logSave(log);
					
					//返回结果,并将查出room集合信息返回给页面
					result.setMsg(Code.ADD_DESK_SUC_CREROOM);
					result.setStatus(0);
					return result;
				}else{
					//打印增加桌子失败状态码
					System.out.println(result1.getErrorCode());
					
					//将增加桌子失败加入的日志中
					log.setStatus(ManagerLog.error);
					log.setDsc(Code.ADD_DESK_GETRESULT_ERROR);
					logMapper.logSave(log);
					
					//返回结果
					result.setMsg(Code.ADD_DESK_GETRESULT_ERROR);
					result.setStatus(2);
					return result;
				}
			}
		}catch(Exception e){
			e.printStackTrace();
			
			//将由于未知错误引起的操作失败存入日志
			log.setStatus(ManagerLog.error);
			log.setDsc(Code.ADD_DESK_UNKNOWN_ERROR);
			logMapper.logSave(log);
			
			//返回结果
			result.setMsg(Code.ADD_DESK_UNKNOWN_ERROR);
			result.setStatus(3);
		}
		return result;
	}
	
	//请求查询桌子参数页面
	public String toSelectDesk(HttpServletRequest req) throws ServletException, IOException {
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
			log.setDsc(Code.SELECT_DESKPAGE_OUTTIME);
			logMapper.logSave(log);
			
			//返回结果
			return "login/login";
		}
		try{
			//获取登陆服务
			GameRMIServer loginServerRMI = CacheGameServer.getGameServer();
			if (null == loginServerRMI) {
				System.out.println("拉开距离据了解来看了解了解了解了就                  失败");
				//获取登陆服务失败
				log.setStatus(ManagerLog.error);
				log.setDsc(Code.SELECT_DESKPAGE_LOGSERVER_ERROR);
				logMapper.logSave(log);
				
				//返回结果
				return "login/login";
			}else{
				System.out.println("拉开距离据了解来看了解了解了解了就                  成功");
				//查询所有游戏名，,返回值为List<game>
				RMIResult result = (RMIResult) loginServerRMI.exec(RMIConstant.GM_TOOL_GET_DESK_LIST,1);
				if(result.getErrorCode() == D.CODE_SUC && result.getResult() !=null){  //判断查询成功时
					//将房间的查询成功存入到日志表中
					log.setStatus(ManagerLog.success);
					log.setDsc(Code.SELECT_DESKPAGE_SUC_CREROOM);
					logMapper.logSave(log);
					
					//将所有游戏信息存入req中
					req.setAttribute("games", (List<Game>)result.getResult());
					//返回结果
					return "gameDeskManagement/selectDesk";
				}else{
					//打印查询失败状态吗
					System.out.println(result.getErrorCode());
					
					//将查询失败加入的日志中
					log.setStatus(ManagerLog.error);
					log.setDsc(Code.SELECT_DESKPAGE_GETRESULT_ERROR);
					logMapper.logSave(log);
					
					//返回结果
					return "login/login";
				}
			}
		}catch(Exception e){
			e.printStackTrace();
			//将由于未知错误引起的操作失败存入日志
			log.setStatus(ManagerLog.error);
			log.setDsc(Code.SELECT_DESKPAGE_UNKNOWN_ERROR);
			logMapper.logSave(log);
		}
		return "login/login";
	}
	
	//查询桌子参数
	public Result selectDesk(HttpServletRequest req, String gameId, String roomId, String id)
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
			log.setDsc(Code.SELECT_DESK_OUTTIME);
			logMapper.logSave(log);
			
			//返回结果
			result.setMsg(Code.SELECT_DESK_OUTTIME);
			result.setStatus(-1);
			return result;
		}
		try{
			//获取登陆服务
			LoginServerRMI loginServerRMI = CacheLoginServer.getLoginServer();
			if (null == loginServerRMI) {
				
				
				System.out.println("拉开距离据了解来看了解了解了解了就                  失败");
				//获取登陆服务失败
				log.setStatus(ManagerLog.error);
				log.setDsc(Code.SELECT_DESK_LOGSERVER_ERROR);
				logMapper.logSave(log);
				
				//返回结果
				result.setMsg(Code.SELECT_DESK_LOGSERVER_ERROR);
				result.setStatus(1);
				return result;
			}else{
				
				System.out.println("拉开距离据了解来看了解了解了解了就");
				
				//查询桌子参数，传入游戏id，房间id，桌子id，返回的RMIResult的result属性为一个Desk实体类
				RMIResult result1 = (RMIResult) loginServerRMI.exce(LoginRMIConstant.GM_TOOL_USR_INSERT_BY_NAME_PWD,gameId,roomId,id);
				if(result1.getErrorCode() == D.CODE_SUC && result1.getResult() != null){  //判断查询成功时
					//将桌子的添加成功存入到日志表中
					log.setStatus(ManagerLog.success);
					log.setDsc(Code.SELECT_DESK_SUC_CREROOM);
					logMapper.logSave(log);
					
					//返回结果,并将查出room集合信息返回给页面
					result.setMsg(Code.SELECT_DESK_SUC_CREROOM);
					result.setStatus(0);
					result.setData((Desk)result1.getResult());
					return result;
				}else{
					//打印增加桌子失败状态码
					System.out.println(result1.getErrorCode());
					
					//将增加桌子失败加入的日志中
					log.setStatus(ManagerLog.error);
					log.setDsc(Code.SELECT_DESK_GETRESULT_ERROR);
					logMapper.logSave(log);
					
					//返回结果
					result.setMsg(Code.SELECT_DESK_GETRESULT_ERROR);
					result.setStatus(2);
					return result;
				}
			}
		}catch(Exception e){
			e.printStackTrace();
			
			//将由于未知错误引起的操作失败存入日志
			log.setStatus(ManagerLog.error);
			log.setDsc(Code.SELECT_DESK_UNKNOWN_ERROR);
			logMapper.logSave(log);
			
			//返回结果
			result.setMsg(Code.SELECT_DESK_UNKNOWN_ERROR);
			result.setStatus(3);
		}
		return result;
	}
	
	//查询所有桌子参数
	public String selectAllDesk(HttpServletRequest req) throws ServletException, IOException {
		ManagerLog log = new ManagerLog(); //创建日志
		String admin = GetAdmin.getAdmin(req);  //获取当前管理员名
		String ip = GetData.getRemoteHost(req); //获取管理员ip
		String system = GetData.getSystem(req);  //获取管理员操作系统
		Timestamp time = GetData.getTime(); //获取当前系统时间
		String deskIndex = req.getParameter("deskIndex");  //获取传过来的页数请求
		
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
			log.setDsc(Code.SELECTALL_DESKPAGE_OUTTIME);
			logMapper.logSave(log);
			
			//返回结果
			return "login/login";
		}
		try{
			int indexpage = Integer.valueOf(deskIndex);
			//判断index是否为第一次请求
			if(indexpage < 0){
				indexpage = 1;
			}
			
			//获取登陆服务
			LoginServerRMI loginServerRMI = CacheLoginServer.getLoginServer();
			if (null == loginServerRMI) {
				//获取登陆服务失败
				log.setStatus(ManagerLog.error);
				log.setDsc(Code.SELECTALL_DESKPAGE_LOGSERVER_ERROR);
				logMapper.logSave(log);
				
				//返回结果
				return "gameDeskManagement/addDesk";
			}else{
				//查询所有桌子信息，传入一个查询所有桌子的状态码，,返回值为List<Desk>
				RMIResult result = (RMIResult) loginServerRMI.exce(LoginRMIConstant.GM_TOOL_USR_INSERT_BY_NAME_PWD);
				if(result.getErrorCode() == D.CODE_SUC && result.getResult() !=null){  //判断查询成功时
					@SuppressWarnings("unchecked")
					List<Desk> totalList = (List<Desk>)result.getResult();  //获取所有玩家
					int count = totalList.size();  //获取玩家总个数
					int pagesize = 20;  //定义每页显示的记录数
					int pagecount;  //定义总共有多少页面
					List<Desk> list = null; //此次请求页面的记录数
					
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
					req.setAttribute("deskCount", count);
					req.setAttribute("deskIndex", indexpage);  //将传进来的index页面数保存到session中
					req.setAttribute("deskPagesize", pagesize);  //将每页显示的记录数存入session中
					req.setAttribute("deskPagecount", pagecount);  //将pagecount用session保存
					req.setAttribute("deskList", list);  //将此次要显示的记录放入计划list中
					
					//将所有桌子的查询成功存入到日志表中
					log.setStatus(ManagerLog.success);
					log.setDsc(Code.SELECTALL_DESKPAGE_SUC_CREROOM);
					logMapper.logSave(log);
					
					//返回结果
					return "gameDeskManagement/addDesk";
				}else{
					//打印查询失败状态吗
					System.out.println(result.getErrorCode());
					
					//将查询失败加入的日志中
					log.setStatus(ManagerLog.error);
					log.setDsc(Code.SELECTALL_DESKPAGE_GETRESULT_ERROR);
					logMapper.logSave(log);
					
					//返回结果
					return "gameDeskManagement/selectAllDesk";
				}
			}
		}catch(Exception e){
			e.printStackTrace();
			//将由于未知错误引起的操作失败存入日志
			log.setStatus(ManagerLog.error);
			log.setDsc(Code.SELECTALL_DESKPAGE_UNKNOWN_ERROR);
			logMapper.logSave(log);
		}
		return "gameDeskManagement/selectAllDesk";
	}
	
	//获取修改桌子页面
	public String toAlterDesk(HttpServletRequest req) throws ServletException, IOException {
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
			log.setDsc(Code.ALTER_DESKPAGE_OUTTIME);
			logMapper.logSave(log);
			
			//返回结果
			return "login/login";
		}
		try{
			req.setAttribute("id", (String)req.getParameter("id"));  //将桌子id值传给修改页面
			req.setAttribute("gameid", (String)req.getParameter("gameid"));  //将游戏id值传给修改页面
			req.setAttribute("roomid", (String)req.getParameter("roomid"));  //将房间id值传给修改页面
			req.setAttribute("maxGoldStock", (String)req.getParameter("maxGoldStock"));  //将桌子最大金币库存传给修改页面
			req.setAttribute("curGoldStock", (String)req.getParameter("curGoldStock"));  //将桌子当前金币库存值传给修改页面
			
			//将获取修改桌子页面成功存入到日志表中
			log.setStatus(ManagerLog.success);
			log.setDsc(Code.ALTER_DESKPAGE_SUC_CREROOM);
			logMapper.logSave(log);
			
			return "gameDeskManagement/alterDesk";
		}catch(Exception e){
			e.printStackTrace();
			e.printStackTrace();
			//将由于未知错误引起的操作失败存入日志
			log.setStatus(ManagerLog.error);
			log.setDsc(Code.ALTER_DESKPAGE_UNKNOWN_ERROR);
			logMapper.logSave(log);
		}
		
		return "gameDeskManagement/alterDesk";
	}
	//修改桌子参数
	public Result alterDesk(HttpServletRequest req, int gameid, int roomid, int id, int maxGoldStock, int curGoldStock)
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
				log.setDsc(Code.ALTER_DESK_OUTTIME);
				logMapper.logSave(log);
				
				//返回结果
				result.setMsg(Code.ALTER_DESK_OUTTIME);
				result.setStatus(-1);
				return result;
			}
			try{
				//获取登陆服务
				LoginServerRMI loginServerRMI = CacheLoginServer.getLoginServer();
				if (null == loginServerRMI) {
					//获取登陆服务失败
					log.setStatus(ManagerLog.error);
					log.setDsc(Code.ALTER_DESK_LOGSERVER_ERROR);
					logMapper.logSave(log);
					
					//返回结果
					result.setMsg(Code.ALTER_DESK_LOGSERVER_ERROR);
					result.setStatus(1);
					return result;
				}else{
					Desk desk = new Desk();
					desk.setCurGoldStock(curGoldStock);
					desk.setGameid(gameid);
					desk.setId(id);
					desk.setMaxGoldStock(maxGoldStock);
					desk.setRoomid(roomid);
					//修改desk参数，传入一个修改的状态码和一个Desk实体类，实体类中包含id，gameid，roomid，maxGoldStock，curGoldStock
					RMIResult result1 = (RMIResult) loginServerRMI.exce(LoginRMIConstant.GM_TOOL_USR_INSERT_BY_NAME_PWD,desk);
					if(result1.getErrorCode() == D.CODE_SUC){  //判断查询成功时
						//将桌子修改成功存入到日志表中
						log.setStatus(ManagerLog.success);
						log.setDsc(Code.ALTER_DESK_SUC_CREROOM);
						logMapper.logSave(log);
						
						//返回结果,并将查出room集合信息返回给页面
						result.setMsg(Code.ALTER_DESK_SUC_CREROOM);
						result.setStatus(0);
						return result;
					}else{
						//打印增加桌子失败状态码
						System.out.println(result1.getErrorCode());
						
						//将增加桌子失败加入的日志中
						log.setStatus(ManagerLog.error);
						log.setDsc(Code.ALTER_DESK_GETRESULT_ERROR);
						logMapper.logSave(log);
						
						//返回结果
						result.setMsg(Code.ALTER_DESK_GETRESULT_ERROR);
						result.setStatus(2);
						return result;
					}
				}
			}catch(Exception e){
				e.printStackTrace();

				//将由于未知错误引起的操作失败存入日志
				log.setStatus(ManagerLog.error);
				log.setDsc(Code.ALTER_DESK_UNKNOWN_ERROR);
				logMapper.logSave(log);
				
				//返回结果
				result.setMsg(Code.ALTER_DESK_UNKNOWN_ERROR);
				result.setStatus(3);
			}
		return result;
	}

}






















