package com.game.business.service;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
import com.game.game.model.Game;
import com.game.game.model.Room;
import com.ranger.remote.LoginServerRMI;
import com.ranger.remote.RMIResult;

import cocl.rmi.GameRMIServer;
/**
 * <li>@ClassName: 游戏房间业务层接口实现类
 * <li>@author 周强
 * <li>@date 2016年11月10日
 */
@Service
public class GameRoomServiceImpl implements GameRoomService {
	@Resource
	private LogMapper logMapper;
	//增加房间
	public Result addGameRoom(HttpServletRequest req, Room room) throws ServletException, IOException {
		Result result = new Result();  //创建返回结果
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
			log.setDsc(Code.ROOM_OUTTIME);
			logMapper.logSave(log);
			
			//返回结果
			result.setStatus(-1);
			return result;
		}
		//判断传入的room是否有值
		if(room == null){
			//保存日志
			log.setStatus(ManagerLog.error);
			log.setDsc(Code.ROOM_IN_NEWS_ERROR);
			logMapper.logSave(log);
			
			//返回结果
			result.setStatus(2);
			result.setMsg(Code.ROOM_IN_NEWS_ERROR);
			return result;
		}
		try{
			//获取游戏服务
			GameRMIServer gameServerRMI = CacheGameServer.getGameServer();
			if (null == gameServerRMI) {
				//获取登陆服务失败
				log.setStatus(ManagerLog.error);
				log.setDsc(Code.ROOM_LOGSERVER_ERROR);
				logMapper.logSave(log);
				
				//返回结果
				result.setMsg(Code.ROOM_LOGSERVER_ERROR);
				result.setStatus(1);
				return result;
			}else{
				//获取结果实体类
				RMIResult result1 = (RMIResult) gameServerRMI.exec(LoginRMIConstant.GM_TOOL_ADD_ROOM,room.toBytes());
				if(result1.getErrorCode() == D.CODE_SUC){
					//将房间的创建成功存入到日志表中
					log.setStatus(ManagerLog.success);
					log.setDsc(Code.ROOM_SUC_CREROOM);
					logMapper.logSave(log);
					
					//返回结果
					result.setStatus(0);
					result.setMsg(Code.ROOM_SUC_CREROOM);
					return result;
				}else{
					//失败的状态码
					System.out.println(result1.getErrorCode());
					
					//将房间创建失败存入到日志表中
					log.setStatus(ManagerLog.error);
					log.setDsc(Code.ROOM_GETRESULT_ERROR);
					logMapper.logSave(log);
					
					//返回结果
					result.setStatus(4);
					result.setMsg(Code.ROOM_GETRESULT_ERROR);
					return result;
				}
			}
		}catch(Exception e){
			e.printStackTrace();
			//将房间创建失败存入到日志表中
			log.setStatus(ManagerLog.error);
			log.setDsc(Code.ROOM_UNKNOWN_ERROR);
			logMapper.logSave(log);
			
			result.setMsg(Code.ROOM_UNKNOWN_ERROR);
			result.setStatus(5);
		}
		return result;
	}
	
	//请求所有房间信息
	public String toSelectAllRoom(HttpServletRequest req) throws ServletException, IOException {
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
			log.setDsc(Code.SELECT_ALLROOM_OUTTIME);
			logMapper.logSave(log);
			
			//返回到登陆页
			return "login/login";
		}
		try{
			int indexpage = Integer.valueOf((String)req.getParameter("roomIndex"));
			//判断index是否为第一次请求
			if(indexpage <0){
				indexpage = 1;
			}
			
			//连接游戏服务
			GameRMIServer gameServerRMI = CacheGameServer.getGameServer();
			if (null == gameServerRMI) {
				//保存日志,获取登陆服务失败
				log.setStatus(ManagerLog.error);
				log.setDsc(Code.SELECT_ALLROOM_LOGSERVER_ERROR);
				logMapper.logSave(log);
				
				//错误信息保存到req中
				req.setAttribute("ungetLoginService", Code.SELECT_ALLROOM_LOGSERVER_ERROR);
				return "gameRoomManagement/selectAllGameRoom";
			}else{
				//返回所有房间
				RMIResult result = (RMIResult) gameServerRMI.exec(LoginRMIConstant.GM_TOOL_GET_ROOM_LIST);
				if(result.getErrorCode() == D.CODE_SUC && result.getResult() != null ){  //判断查询是否成功
					@SuppressWarnings("unchecked")
					List<Map<String,byte[]>> list1 = (List<Map<String,byte[]>>)result.getResult();
					List<List> totalList = new ArrayList<List>();
					for(int i=0;i<list1.size();i++){
						//遍历map
						 for (Map.Entry<String, byte[]> entry : list1.get(i).entrySet()) {  
					            //System.out.println("key = " + entry.getKey() + " and value = " + entry.getValue()); 
							 	Room room = new Room();
								room.fromBytes(entry.getValue());
								List list2 = new ArrayList();
							 	list2.add(entry.getKey());
							 	list2.add(room);
								totalList.add(list2);
					        } 
					}
					
					int count = totalList.size();  //获取玩家总个数
					int pagesize = 20;  //定义每页显示的记录数
					int pagecount;  //定义总共有多少页面
					List<List> list = null; //此次请求页面的记录数
					
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
					 * 将日志总的条数用session保存
					 * 方便转发到JSP页面取值，和后面请求页面分页显示取值
					 * 注意：count是动态变化的
					 */
					req.setAttribute("roomCount", count);
					req.setAttribute("roomIndex", indexpage);  //将传进来的index页面数保存到session中
					req.setAttribute("roomPagesize", pagesize);  //将每页显示的记录数存入session中
					req.setAttribute("roomPagecount", pagecount);  //将pagecount用session保存
					req.setAttribute("roomList", list);  //将此次要显示的记录放入计划list中
					
					//将所有房间查询成功存入到日志表中
					log.setStatus(ManagerLog.success);
					log.setDsc(Code.SELECT_ALLROOM_SUC_CREROOM);
					logMapper.logSave(log);
					return "gameRoomManagement/selectAllGameRoom";
				}else{
					//将错误码打印出来
					System.out.println(result.getErrorCode());
					
					//记录错误日志
					log.setStatus(ManagerLog.error);
					log.setDsc(Code.SELECT_ALLROOM_GETRESULT_ERROR);
					logMapper.logSave(log);
					
					//将错误信息保存到req中传到页面中
					req.setAttribute("logServiceError", Code.SELECT_ALLROOM_GETRESULT_ERROR);
					return "gameRoomManagement/selectAllGameRoom";
				}
			}
		}catch(Exception e){
			e.printStackTrace();
			//将查找所有房间的未知错误保存到日志中
			log.setStatus(ManagerLog.error);
			log.setDsc(Code.SELECT_ALLROOM_UNKNOWN_ERROR);
			logMapper.logSave(log);
			
			//将查找所有房间的未知错误保存到req中
			req.setAttribute("unknow", Code.SELECT_ALLROOM_UNKNOWN_ERROR);
		}
		return "gameRoomManagement/selectAllGameRoom";
	}
	
	//获取修改页面
	public String toAlterGameRoom(HttpServletRequest req) throws ServletException, IOException {
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
			log.setDsc(Code.ALTERPAGE_ROOM_OUTTIME);
			logMapper.logSave(log);
			
			//返回到登陆页
			return "login/login";
		}
		try{
			//获取传过来的参数，并将保持到req
			req.setAttribute("gameId", Integer.valueOf((String)req.getParameter("gameId")));
			req.setAttribute("name", (String)req.getParameter("name"));
			req.setAttribute("vipLimit", Integer.valueOf((String)req.getParameter("vipLimit")));
			req.setAttribute("goldLimit", Integer.valueOf((String)req.getParameter("goldLimit")));
			req.setAttribute("level", Integer.valueOf((String)req.getParameter("level")));
			req.setAttribute("peopleNumLimit", Integer.valueOf((String)req.getParameter("peopleNumLimit")));
			req.setAttribute("creditRate", Integer.valueOf((String)req.getParameter("creditRate")));
			req.setAttribute("betBaseScore", Integer.valueOf((String)req.getParameter("betBaseScore")));
			req.setAttribute("betMinScore", Integer.valueOf((String)req.getParameter("betMinScore")));
			req.setAttribute("betMaxScore", Integer.valueOf((String)req.getParameter("betMaxScore")));
		}catch(Exception e){
			e.printStackTrace();
			//将获取修改房间的未知错误保存到日志中
			log.setStatus(ManagerLog.error);
			log.setDsc(Code.ALTERPAGE_ROOM_UNKNOWN_ERROR);
			logMapper.logSave(log);
			
			//将查找所有房间的未知错误保存到req中
			req.setAttribute("unknow", Code.ALTERPAGE_ROOM_UNKNOWN_ERROR);
		}
		return "gameRoomManagement/alterGameRoom";
	}
	
	//修改房间参数
	public Result alterGameRoom(HttpServletRequest req,Room room,String oldname) throws ServletException, IOException {
		Result result = new Result();  //创建返回结果
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
			log.setStatus(ManagerLog.error);
			log.setDsc(Code.ALTER_ROOM_OUTTIME);
			logMapper.logSave(log);
			
			//返回结果
			result.setStatus(-1);
			return result;
		}
		//判断传入的room是否有值
		if(room == null){
			//保存日志
			log.setStatus(ManagerLog.error);
			log.setDsc(Code.ALTER_ROOM_IN_NEWS_ERROR);
			logMapper.logSave(log);
			
			//返回结果
			result.setStatus(2);
			result.setMsg(Code.ALTER_ROOM_IN_NEWS_ERROR);
			return result;
		}
		try{
			//检验传入的房间名是否合法
			String numReg = "^[0-9a-zA-Z\u4e00-\u9fa5]{6,20}$";
			if(room.getName().matches(numReg)){
				//保存日志
				log.setStatus(ManagerLog.error);
				log.setDsc(Code.ALTER_ROOM_IN_ROOM_ERROR);
				logMapper.logSave(log);
				
				//返回结果
				result.setStatus(3);
				result.setMsg(Code.ALTER_ROOM_IN_ROOM_ERROR);
				return result;
			}
			//获取登陆服务
			LoginServerRMI loginServerRMI = CacheLoginServer.getLoginServer();
			if (null == loginServerRMI) {
				//获取登陆服务失败
				log.setStatus(ManagerLog.error);
				log.setDsc(Code.ALTER_ROOM_LOGSERVER_ERROR);
				logMapper.logSave(log);
				
				//返回结果
				result.setMsg(Code.ALTER_ROOM_LOGSERVER_ERROR);
				result.setStatus(1);
				return result;
			}else{
				//通过原来的名查出room的id
				RMIResult result1 = (RMIResult) loginServerRMI.exce(LoginRMIConstant.GM_TOOL_USR_INSERT_BY_NAME_PWD,oldname);
				//判断传入name，查到room没有
				if(result1.getErrorCode() == D.CODE_SUC && result1.getResult() != null){
					//传入room实体类，修改房间参数
					RMIResult result2 = (RMIResult) loginServerRMI.exce(LoginRMIConstant.GM_TOOL_USR_INSERT_BY_NAME_PWD,room);
					if(result2.getErrorCode() == D.CODE_SUC){
						//将房间的创建成功存入到日志表中
						log.setStatus(ManagerLog.success);
						log.setDsc(Code.ALTER_ROOM_SUC_CREROOM);
						logMapper.logSave(log);
						
						//返回结果
						result.setStatus(0);
						result.setMsg(Code.ALTER_ROOM_SUC_CREROOM);
						return result;
					}else{
						//失败的状态码
						System.out.println(result2.getErrorCode());
						
						//将房间创建失败存入到日志表中
						log.setStatus(ManagerLog.error);
						log.setDsc(Code.ALTER_ROOM_GETRESULT_ERROR);
						logMapper.logSave(log);
						
						//返回结果
						result.setStatus(4);
						result.setMsg(Code.ALTER_ROOM_GETRESULT_ERROR);
						return result;
					}
				}else{
					//失败的状态码
					System.out.println(result1.getErrorCode());
					
					//将房间创建失败存入到日志表中
					log.setStatus(ManagerLog.error);
					log.setDsc(Code.ALTER_ROOM_SELECT_ERROR);
					logMapper.logSave(log);
					
					result.setMsg(Code.ALTER_ROOM_SELECT_ERROR);
					result.setStatus(5);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
			//将房间创建失败存入到日志表中
			log.setStatus(ManagerLog.error);
			log.setDsc(Code.ALTER_ROOM_UNKNOWN_ERROR);
			logMapper.logSave(log);
			
			result.setMsg(Code.ALTER_ROOM_UNKNOWN_ERROR);
			result.setStatus(6);
		}
		return result;
	}
	
	//查询房间参数
	public Result selectRoom(HttpServletRequest req,int gameId,String oldName) throws ServletException, IOException {
		Result result = new Result();  //创建返回结果
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
			log.setDsc(Code.SELECT_ROOM_OUTTIME);
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
				log.setDsc(Code.SELECT_ROOM_LOGSERVER_ERROR);
				logMapper.logSave(log);
				
				//返回结果
				result.setMsg(Code.SELECT_ROOM_LOGSERVER_ERROR);
				result.setStatus(1);
				return result;
			}else{
				//通过传入游戏id和游戏房间名查询房间信息
				RMIResult result1 = (RMIResult) loginServerRMI.exce(LoginRMIConstant.GM_TOOL_USR_INSERT_BY_NAME_PWD,gameId,oldName);
				if(result1.getErrorCode() == D.CODE_SUC && result1.getResult() !=null){  //判断查询成功时
					//将房间的查询成功存入到日志表中
					log.setStatus(ManagerLog.success);
					log.setDsc(Code.SELECT_ROOM_SUC_CREROOM);
					logMapper.logSave(log);
					
					//返回结果,并将查出room信息返回给页面
					result.setStatus(0);
					result.setMsg(Code.SELECT_ROOM_SUC_CREROOM);
					result.setData((Room)result1.getResult());  //返回room
					return result;
				}else{
					//打印查询失败状态吗
					System.out.println(result1.getErrorCode());
					
					//将查询失败加入的日志中
					log.setStatus(ManagerLog.error);
					log.setDsc(Code.SELECT_ROOM_SELECT_ERROR);
					logMapper.logSave(log);
					
					//返回结果
					result.setMsg(Code.SELECT_ROOM_SELECT_ERROR);
					result.setStatus(2);
					return result;
				}
			}
		}catch(Exception e){
			e.printStackTrace();
			//将查找房间信息的未知错误保存到日志中
			log.setStatus(ManagerLog.error);
			log.setDsc(Code.SELECT_ROOM_UNKNOWN_ERROR);
			logMapper.logSave(log);
			
			//返回结果
			result.setMsg(Code.SELECT_ROOM_UNKNOWN_ERROR);
			result.setStatus(3);
		}
		return result;
	}
	
	
	//请求增加游戏房间页面
	public String toAddGameRoom(HttpServletRequest req) throws ServletException, IOException {
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
			log.setDsc(admin+Code.ADD_ROOMPAGE_OUTTIME);
			logMapper.logSave(log);
			
			//返回结果
			return "login/login";
		}
		try{
			//获取游戏服务
			GameRMIServer gameServerRMI = CacheGameServer.getGameServer();
			//System.out.println(gameServerRMI);
			if (null == gameServerRMI) {
				//获取登陆服务失败
				log.setStatus(ManagerLog.error);
				log.setDsc(Code.SELECT_GAMES_ALL_NOSERVER);
				logMapper.logSave(log);
				
				//返回结果
				return "gameRoomManagement/addGameRoom";
			}else{
				//获取所有游戏
				RMIResult result1 = (RMIResult) gameServerRMI.exec(LoginRMIConstant.GM_TOOL_GET_GAME_LIST);
				if(result1.getErrorCode() == D.CODE_SUC && result1.getResult() != null){
					@SuppressWarnings("unchecked")
					List<byte[]> list = (List<byte[]>)result1.getResult();
					List<Game> list1 = new ArrayList<Game>();
					for(int i=0;i<list.size();i++){
						Game game = new Game();
						game.fromBytes(list.get(i));
						list1.add(game);
					}
					req.setAttribute("list", list1);
					
					//将所有游戏查询成功加入日志
					log.setStatus(ManagerLog.success);
					log.setDsc(Code.ADD_SUC_ROOMPAGE);
					logMapper.logSave(log);
					
					//返回结果
					return "gameRoomManagement/addGameRoom";
				}else{
					//失败的状态码
					System.out.println(result1.getErrorCode());
					
					//将请求所有游戏失败存入日志
					log.setStatus(ManagerLog.error);
					log.setDsc(Code.SELECT_GAMES_ALL_NOGETGAMES);
					logMapper.logSave(log);
					
					//返回结果
					return "gameRoomManagement/addGameRoom";
				}
			}
		}catch(Exception e){
			e.printStackTrace();
			//将玩家查询失败存入到日志表中
			log.setStatus(ManagerLog.error);
			log.setDsc(Code.SELECT_EXCEPTION_GAMES);
			logMapper.logSave(log);
			
		}
		return "gameRoomManagement/addGameRoom";
	}

}
