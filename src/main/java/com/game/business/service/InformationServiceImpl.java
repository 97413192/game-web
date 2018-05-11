package com.game.business.service;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.game.base.util.BeanFactory;
import com.game.base.util.Code;
import com.game.base.util.GetAdmin;
import com.game.base.util.GetData;
import com.game.base.util.PowerManagment;
import com.game.base.util.Result;
import com.game.business.mapper.AgentMapper;
import com.game.business.mapper.InformationCategoryMapper;
import com.game.business.mapper.InformationMapper;
import com.game.business.mapper.LogMapper;
import com.game.business.mapper.MgerMapper;
import com.game.business.mapper.SingleDataMapper;
import com.game.business.model.Agent;
import com.game.business.model.Information;
import com.game.business.model.manager.Manager;
import com.game.business.model.manager.ManagerLog;
import com.game.cache.CacheGameServer;
import com.game.cache.CacheLoginServer;
import com.game.constant.D;
import com.game.constant.LoginRMIConstant;
import com.game.constant.RMIConstant;
import com.ranger.module.po.Usr;
import com.ranger.remote.LoginServerRMI;
import com.ranger.remote.RMIResult;

import cocl.rmi.GameRMIServer;
/** 
 * <li>ClassName:ManagerService <br/> 
 * <li>@Description: 消息业务层
 * <li>@Date:     2017年02月7日<br/> 
 * <li>@author   周强      
 */

@Service
public class InformationServiceImpl implements InformationService {
	@Resource
	private MgerMapper mgMapper;
	@Resource
	private LogMapper logMapper;
	@Resource
	private AgentMapper agentMapper;
	@Resource
	private SingleDataMapper singleDataMapper;
	@Resource
	private InformationMapper informationMapper;
	@Resource
	private InformationCategoryMapper informationCategoryMapper;
	
	Log log = LogFactory.getLog(InformationServiceImpl.class);
	//请求增加管理员页面
	@Transactional
	public String toAddInformation(HttpServletRequest req) throws ServletException, IOException {
		String admin = GetAdmin.getAdmin(req);  			//获取当前管理员名
		GetData getData = (GetData) BeanFactory.getBean(GetData.class);
		
		//判断当前管理员或者代理商是否超时
		if(admin == null){
			//保存日志
			getData.saveCurrentLog(req, Code.ADD_INFORMATION_OUTTIME_PAGE, ManagerLog.error);
			
			//返回结果
			return "login/login";
		}
		//判断操作者时管理员还是代理商
		Manager mg = mgMapper.findByName(admin);
		Agent agent = agentMapper.findAgentByName(admin);
		
		//此时操作有此权限
		if(mg != null && agent == null){
			//成功
			getData.saveCurrentLog(req, Code.ADD_INFORMATION_SUC_PAGE, ManagerLog.success);
			return "informationManagment/addInformation";
		}else{
			//记录失败
			getData.saveCurrentLog(req, Code.ADD_INFORMATION_NOT_POWER_PAGE, ManagerLog.error);
			
			//返回结果
			return "systemSettingsManagement/selectAllManagerNoPower";
		}
	}
	
	//增加管理员
	@Transactional
	public Result addInformation(HttpServletRequest req, String text, Integer category)
			throws ServletException, IOException {
		Result result = new Result();  						//创建返回结果
		String admin = GetAdmin.getAdmin(req);  			//获取当前管理员名
		Information information = new Information();
		GetData getData = (GetData) BeanFactory.getBean(GetData.class);
		
		//判断当前管理员是否超时
		if(admin == null){
			//保存日志
			getData.saveCurrentLog(req, Code.ADD_INFORMATION_OUTTIME, ManagerLog.error);
			
			//返回结果
			result.setStatus(-1);
			return result;
		}
		
		try{
			//判断操作者时管理员还是代理商
			Manager mg = mgMapper.findByName(admin);
			Agent agent = agentMapper.findAgentByName(admin);
			
			//此时操作有此权限
			if(mg != null && agent == null){
				information.setCategory(category);
				information.setMessage(text);
				//执行增加消息sql
				informationMapper.save(information);
				
				//表中只能有两条数据
				if(informationCategoryMapper.selectByCategory(information.getCategory()).size() != 0){
					System.out.println(information.getCategory());
					System.out.println("1");
					informationCategoryMapper.updateByCategory(information);
				}else{
					System.out.println("2");
					informationCategoryMapper.save(information);
				}
				
				
				//记录成功日志
				getData.saveCurrentLog(req, Code.ADD_INFORMATION_SUC, ManagerLog.success);
				
				//返回结果
				result.setMsg(Code.ADD_INFORMATION_SUC);
				result.setStatus(0);
				return result;
			}else{
				//记录失败日志
				getData.saveCurrentLog(req, Code.ADD_INFORMATION_NOT_POWER, ManagerLog.error);
				
				//返回结果
				result.setMsg(Code.ADD_INFORMATION_NOT_POWER);
				result.setStatus(1);
				return result;
			}
			
		}catch(Exception e){
			e.printStackTrace();
			//记录失败日志
			getData.saveCurrentLog(req, Code.ADD_INFORMATION_UNKNOWN, ManagerLog.error);
			
			//返回结果
			result.setData(2);
			result.setMsg("未知错误");
		}
		return result;
	}

	/**请求所有消息记录的页面*/
	@Transactional
	public String toSelectAllInformation(HttpServletRequest req) throws ServletException, IOException {
		String admin = GetAdmin.getAdmin(req);   					//获取当前管理员名
		String playerIndex = (String)req.getParameter("index");     //获取传入的请求页数
		//System.out.println("playerIndex:"+playerIndex);
		GetData getData = (GetData) BeanFactory.getBean(GetData.class);
		
		//判断当前管理员或者代理商是否超时
		if(admin == null){
			//保存日志
			getData.saveCurrentLog(req, Code.SELECT_INFORMATION_ALL_OUTTIME, ManagerLog.error);
			
			//返回结果
			return "login/login";
		}
		
		try{
			//获取当前操作管理员
			Manager mg = mgMapper.findByName(admin);
			Agent agent = agentMapper.findAgentByName(admin);
			
			//此时操作有此权限
			if(mg != null && agent == null){
				int indexpage = Integer.valueOf(playerIndex);
				//判断index是否为第一次请求
				if(indexpage <0){
					indexpage = 1;
				}
				
				//获取所有消息记录
				List<Information> list = informationMapper.selectAllInformation();
				//System.out.println(list);
				
				if(list != null){
					int pagesize = 20;  //定义每页显示的记录数
					//加入查询所有记录方法中
					GetData.selectAll(req, list, pagesize, indexpage,
							"count", "index", "pagesize", "pagecount", "list");
					
					//将管理员查询成功存入到日志表中
					getData.saveCurrentLog(req, Code.SELECT_INFORMATION_ALL_SUC, ManagerLog.success);
					
					return "informationManagment/selectAllInformation";
				}else{
					//将管理员查询失败存入到日志表中
					getData.saveCurrentLog(req, Code.SELECT_INFORMATION_ALL_ERROR, ManagerLog.error);
				}
			}else{
				//记录失败日志
				getData.saveCurrentLog(req, Code.SELECT_INFORMATION_ALL_NOT_POWER, ManagerLog.error);
				
				//返回结果
				return "systemSettingsManagement/selectAllManagerNoPower";
			}
		}catch(Exception e){
			e.printStackTrace();
			//将管理员查询失败存入到日志表中
			getData.saveCurrentLog(req, Code.SELECT_INFORMATION_ALL_UNKNOWN, ManagerLog.error);
		}
		return "informationManagment/selectAllInformation";
	}

	/**请求单个消息记录的页面*/
	@Transactional
	public String toSelectInformation(HttpServletRequest req) throws ServletException, IOException {
		String admin = GetAdmin.getAdmin(req);   						  //获取当前管理员名
		Integer id = Integer.valueOf((String)req.getParameter("id"));     //获取传入的请求id
		System.out.println("id:"+id);
		GetData getData = (GetData) BeanFactory.getBean(GetData.class);
		
		//判断当前管理员或者代理商是否超时
		if(admin == null){
			//保存日志
			getData.saveCurrentLog(req, Code.SELECT_INFORMATION_OUTTIME, ManagerLog.error);
			
			//返回结果
			return "login/login";
		}
		try{
			//获取当前操作管理员
			Manager mg = mgMapper.findByName(admin);
			Agent agent = agentMapper.findAgentByName(admin);
			
			//此时操作有此权限
			if(mg != null && agent == null){
				//获取消息记录
				Information information = informationMapper.selectById(id);
				if(information != null){
					//将information存入req中
					req.setAttribute("information", information);
					
					//操作成功存入日志
					getData.saveCurrentLog(req, Code.SELECT_INFORMATION_SUC, ManagerLog.success);
					
					return "informationManagment/selectInformation";
				}else{
					//将管理员查询失败存入到日志表中
					getData.saveCurrentLog(req, Code.SELECT_INFORMATION_NO_INFORMATION, ManagerLog.error);
				}
			}else{
				//记录失败日志
				getData.saveCurrentLog(req, Code.SELECT_INFORMATION_NOT_POWER, ManagerLog.error);
				
				//返回结果
				return "systemSettingsManagement/selectAllManagerNoPower";
			}
			
		}catch(Exception e){
			e.printStackTrace();
			//将管理员查询失败存入到日志表中
			getData.saveCurrentLog(req, Code.SELECT_INFORMATION_UNKNOWN, ManagerLog.error);
		}
		return "informationManagment/selectInformation";
	}

	/** 修改信息*/
	@Transactional
	public Result alertInformation(HttpServletRequest req, Integer id,String message, Integer category)
			throws ServletException, IOException {
		Result result = new Result();  						//创建返回结果
		String admin = GetAdmin.getAdmin(req);  			//获取当前管理员名
		Information information = new Information();
		GetData getData = (GetData) BeanFactory.getBean(GetData.class);
		
		//判断当前管理员是否超时
		if(admin == null){
			//保存日志
			getData.saveCurrentLog(req, Code.ALERT_INFORMATION_OUTTIME, ManagerLog.error);
			
			//返回结果
			result.setStatus(-1);
			return result;
		}
		
		try{
			//获取当前操作管理员
			Manager mg = mgMapper.findByName(admin);
			Agent agent = agentMapper.findAgentByName(admin);
			
			//此时操作有此权限
			if(mg != null && agent == null){
				//通过id查询消息记录
				information = informationMapper.selectById(id);
				if(information != null){
					if(message == null || message == ""){
						information.setMessage("空");
					}
					information.setMessage(message);
					information.setCategory(category);
					//执行修改sql语句
					if(informationMapper.updateById(information) > 0){
						//表中只能有两条数据
						if(informationCategoryMapper.selectByCategory(information.getCategory()).size() != 0){
							System.out.println(information.getCategory());
							System.out.println("1");
							informationCategoryMapper.updateByCategory(information);
						}else{
							System.out.println("2");
							informationCategoryMapper.save(information);
						}
						
						getData.saveCurrentLog(req, Code.ALERT_INFORMATION_SUC, ManagerLog.success);
						
						//返回结果
						result.setMsg(Code.ALERT_INFORMATION_SUC);
						result.setStatus(0);
						return result;
					}else{
						//保存日志
						getData.saveCurrentLog(req, Code.ALERT_INFORMATION_ERROR, ManagerLog.error);
						
						//返回结果
						result.setMsg(Code.ALERT_INFORMATION_ERROR);
						result.setStatus(3);
						return result;
					}
				}else{
					//保存日志
					getData.saveCurrentLog(req, Code.ALERT_INFORMATION_NO_INFORMATION, ManagerLog.error);
					
					//返回结果
					result.setMsg(Code.ALERT_INFORMATION_NO_INFORMATION);
					result.setStatus(2);
					return result;
				}
			}else{
				//记录失败日志
				getData.saveCurrentLog(req, Code.ALERT_INFORMATION_NOT_POWER, ManagerLog.error);
				
				//返回结果
				result.setMsg(Code.ALERT_INFORMATION_NOT_POWER);
				result.setStatus(1);
				return result;
			}
		}catch(Exception e){
			e.printStackTrace();
			//记录失败日志
			getData.saveCurrentLog(req, Code.ALERT_INFORMATION_UNKNOWN, ManagerLog.error);
			
			//返回结果
			result.setMsg(Code.ALERT_INFORMATION_UNKNOWN);
			result.setStatus(4);
		}
		return result;
	}

	/** 通过id删除信息*/
	@Transactional
	public Result deleteInformation(HttpServletRequest req, Integer id) throws ServletException, IOException {
		Result result = new Result();  						//创建返回结果
		String admin = GetAdmin.getAdmin(req);  			//获取当前管理员名
		Information information = new Information();
		GetData getData = (GetData) BeanFactory.getBean(GetData.class);
		
		//判断当前管理员是否超时
		if(admin == null){
			//保存日志
			getData.saveCurrentLog(req, Code.DELETE_INFORMATION_OUTTIME, ManagerLog.error);
			
			//返回结果
			result.setStatus(-1);
			return result;
		}
		
		try{
			//获取当前操作管理员
			Manager mg = mgMapper.findByName(admin);
			Agent agent = agentMapper.findAgentByName(admin);
			
			//此时操作有此权限
			if(mg != null && agent == null){
				//通过id查询消息记录
				information = informationMapper.selectById(id);
				//System.out.println("id:"+id);
				if(information != null){
					if(informationMapper.deleteById(id) > 0){
						getData.saveCurrentLog(req, Code.DELETE_INFORMATION_SUC, ManagerLog.success);
						
						//返回结果
						result.setMsg(Code.DELETE_INFORMATION_SUC);
						result.setStatus(0);
						return result;
					}else{
						//保存日志
						getData.saveCurrentLog(req, Code.DELETE_INFORMATION_ERROR, ManagerLog.error);
						
						//返回结果
						result.setMsg(Code.DELETE_INFORMATION_ERROR);
						result.setStatus(3);
						return result;
					}
				}else{  //没有此条记录
					//保存日志
					getData.saveCurrentLog(req, Code.DELETE_INFORMATION_NO_INFORMATION, ManagerLog.error);
					
					//返回结果
					result.setMsg(Code.DELETE_INFORMATION_NO_INFORMATION);
					result.setStatus(2);
					return result;
				}
			}else{  //操作者没有权限
				//记录失败日志
				getData.saveCurrentLog(req, Code.DELETE_INFORMATION_NOT_POWER, ManagerLog.error);
				
				//返回结果
				result.setMsg(Code.DELETE_INFORMATION_NOT_POWER);
				result.setStatus(1);
				return result;
			}
		}catch(Exception e){
			e.printStackTrace();
			//记录失败日志
			getData.saveCurrentLog(req, Code.DELETE_INFORMATION_UNKNOWN, ManagerLog.error);
			
			//返回结果
			result.setMsg(Code.DELETE_INFORMATION_UNKNOWN);
			result.setStatus(4);
		}
		return result;
	}

	/**请求现在使用的跑马灯和消息公告*/
	@Transactional
	public String toSelectAllCategoryInformation(HttpServletRequest req) throws ServletException, IOException {
		System.out.println("进来");
		String admin = GetAdmin.getAdmin(req);   					//获取当前管理员名
		String playerIndex = (String)req.getParameter("index");     //获取传入的请求页数
		GetData getData = (GetData) BeanFactory.getBean(GetData.class);
		
		//判断当前管理员或者代理商是否超时
		if(admin == null){
			//保存日志
			getData.saveCurrentLog(req, Code.SELECT_INFORMATION_NOW_OUTTIME, ManagerLog.error);
			
			//返回结果
			return "login/login";
		}
		
		try{
			//获取当前操作管理员
			Manager mg = mgMapper.findByName(admin);
			Agent agent = agentMapper.findAgentByName(admin);
			
			//此时操作有此权限
			if(mg != null && agent == null){
				int indexpage = Integer.valueOf(playerIndex);
				//判断index是否为第一次请求
				if(indexpage <0){
					indexpage = 1;
				}
				
				//获取所有消息记录
				List<Information> list = informationCategoryMapper.selectAllInformation();
				System.out.println(list);
				
				if(list != null){
					int pagesize = 20;  //定义每页显示的记录数
					//加入查询所有记录方法中
					GetData.selectAll(req, list, pagesize, indexpage,
							"count", "index", "pagesize", "pagecount", "list");
					
					//将管理员查询成功存入到日志表中
					getData.saveCurrentLog(req, Code.SELECT_INFORMATION_NOW_SUC, ManagerLog.success);
					
					return "informationManagment/selectAllCategoryInformation";
				}else{
					//将管理员查询失败存入到日志表中
					getData.saveCurrentLog(req, Code.SELECT_INFORMATION_NOW_ERROR, ManagerLog.error);
				}
			}else{
				//记录失败日志
				getData.saveCurrentLog(req, Code.SELECT_INFORMATION_NOW_NOT_POWER, ManagerLog.error);
				
				//返回结果
				return "systemSettingsManagement/selectAllManagerNoPower";
			}
		}catch(Exception e){
			e.printStackTrace();
			//将管理员查询失败存入到日志表中
			getData.saveCurrentLog(req, Code.SELECT_INFORMATION_NOW_UNKNOWN, ManagerLog.error);
		}
		return "informationManagment/selectAllCategoryInformation";
	}

	/**请求现在使用的跑马灯和消息公告*/
	@Transactional
	public Result toSelectCategoryInformation(HttpServletRequest req) throws ServletException, IOException {
		//System.out.println("进来");
		Result result = new Result();
		GetData getData = (GetData) BeanFactory.getBean(GetData.class);
		
		
		try{
			//获取所有消息记录
			List<Information> list = informationCategoryMapper.selectAllInformation();
			System.out.println(list);
			
			if(list != null){
				//将管理员查询成功存入到日志表中
				getData.saveCurrentLog(req, "查询跑马灯公告消息接口:成功!", ManagerLog.success);
				result.setData(list);
				result.setStatus(0);
				return result;
			}else{
				//将管理员查询失败存入到日志表中
				getData.saveCurrentLog(req,"查询跑马灯公告消息接口:失败!", ManagerLog.error);
				result.setStatus(1);
				return result;
			}
		}catch(Exception e){
			e.printStackTrace();
			//将管理员查询失败存入到日志表中
			getData.saveCurrentLog(req, "查询跑马灯公告消息接口:未知错误失败!", ManagerLog.error);
			result.setStatus(-1);
			return result;
		}
		
	}
	

	/**判断gameId是否存在*/
	public Result selectOnePage(HttpServletRequest req,Integer gameId) throws ServletException, IOException {
		Result result = new Result();  						//创建返回结果
		String admin = GetAdmin.getAdmin(req);  			//获取当前管理员名
		GetData getData = (GetData) BeanFactory.getBean(GetData.class);
		//判断当前管理员是否超时
		if(admin == null){
			//保存日志
			getData.saveCurrentLog(req, "通过gameId查询兑换商品记录信息:操作者未操作超时!", ManagerLog.error);
			//返回结果
			result.setStatus(-1);
			return result;
		}
		try {
			
			if(gameId!=null){
				//连接登陆服务
				GameRMIServer gameRMIUrl  = CacheGameServer.getGameServer();
				
				if (null == gameRMIUrl) {
					//判断是否连接成功
					getData.saveCurrentLog(req,"连接服务器失败",ManagerLog.error);
					result.setMsg("链接服务器失败");
					result.setStatus(1);
					
				}else{
					//获取去map值并进行判断
					@SuppressWarnings("unchecked")
					Map<String,Object> map = (Map<String, Object>) gameRMIUrl.exec(RMIConstant.GM_TOOL_FIND_PLAYER,gameId);
					if(map==null){
						getData.saveCurrentLog(req, "gameId不存在:查询失败!", ManagerLog.error);
						result.setStatus(2);
						result.setMsg("gameId不存在:查询失败!");
						return result;
						
					}else{
						getData.saveCurrentLog(req, "查询成功", ManagerLog.success);
						result.setStatus(0);
						result.setMsg("查询成功");
						result.setData(gameId);
						return result;
					}
				}
				
			}else{
				//gameId为空
				getData.saveCurrentLog(req, "gameId为空:查询失败!", ManagerLog.error);
				result.setStatus(3);
				result.setMsg("gameId为空:查询失败!");
				return result;
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.setStatus(4);
			result.setMsg("未知异常:查询失败!");
			getData.saveCurrentLog(req, "未知异常:查询失败!", ManagerLog.error);
		}
		return result;
	}
	

	/**查询单个玩家信息*/
	public String selectOneInformation(HttpServletRequest req, Integer gameId,Integer index)
			throws ServletException, IOException {
		String admin = GetAdmin.getAdmin(req);  				//获取当前管理员名
		GetData getData = (GetData) BeanFactory.getBean(GetData.class);
		//判断当前管理员是否超时
		if(admin == null){
			//保存日志
			getData.saveCurrentLog(req,"查询单个玩家信息:操作者未操作超时!",ManagerLog.error);
			//返回结果
			return "login/login";
		}
		try{
			
			//判断这个管理员是否是超级管理员
			if(PowerManagment.checkOperCategory(admin) != 1){
				
				//记录失败日志
				getData.saveCurrentLog(req,"查询单个玩家信息:没有权限!",ManagerLog.error);
				//返回结果
				return "systemSettingsManagement/selectAllManagerNoPower";
			}
			
			//连接登陆服务
			GameRMIServer gameRMIUrl  = CacheGameServer.getGameServer();
			
			if (null == gameRMIUrl) {
				//将玩家查询失败存入到日志表中
				getData.saveCurrentLog(req,"连接服务器失败",ManagerLog.error);
				
			}else{
				
				@SuppressWarnings("unchecked")
				Map<String,Object> map = (Map<String, Object>) gameRMIUrl.exec(RMIConstant.GM_TOOL_FIND_PLAYER,gameId);
				log.warn("map----------------"+map);
				if(map==null){
					
					getData.saveCurrentLog(req,"查询失败",ManagerLog.error);
					
				}else{
					
					getData.saveCurrentLog(req,"查询成功",ManagerLog.success);
					req.setAttribute("id", map.get("id"));
					req.setAttribute("pNickName", map.get("pNickName"));
					req.setAttribute("gameIdOne", map.get("gameId"));
					req.setAttribute("isSuperClient", map.get("isSuperClient"));
				}
			}
			 
		}catch(Exception e){
			e.printStackTrace();
			//存入日志
			getData.saveCurrentLog(req,"查询单个玩家信息:未知异常!",ManagerLog.error);
		}
		return "informationManagment/oneSuperInformation";
	}

	
	
	
	
	/**修改玩家信息页面*/
	public String updateSuperPortPage(HttpServletRequest req, Integer gameId) throws ServletException, IOException {
		String admin = GetAdmin.getAdmin(req);  			//获取当前管理员名
		GetData getData = (GetData) BeanFactory.getBean(GetData.class);		
		//判断当前管理员或者代理商是否超时
		if(admin == null){
			//保存日志
			getData.saveCurrentLog(req, Code.ADD_INFORMATION_OUTTIME_PAGE, ManagerLog.error);
			
			//返回结果
			return "login/login";
		}
		
		try {
			//连接登陆服务
			GameRMIServer gameRMIUrl  = CacheGameServer.getGameServer();
			
			if (null == gameRMIUrl) {
				//将玩家查询失败存入到日志表中
				getData.saveCurrentLog(req,"连接服务器失败",ManagerLog.error);
				
			}else{
				
				@SuppressWarnings("unchecked")
				Map<String,Object> map = (Map<String, Object>) gameRMIUrl.exec(RMIConstant.GM_TOOL_FIND_PLAYER,gameId);
				if(map==null){
					
					getData.saveCurrentLog(req,"查询失败",ManagerLog.error);
					
				}else{
					getData.saveCurrentLog(req,"查询成功",ManagerLog.success);
					req.setAttribute("id", map.get("id"));
					req.setAttribute("pNickName", map.get("pNiceName"));
					req.setAttribute("gameIdOne", map.get("gameId"));
					req.setAttribute("isSuperClient", map.get("isSuperClient"));
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			getData.saveCurrentLog(req,"未知异常失败:请求修改页面失败", ManagerLog.error);
		}
		return "informationManagment/updateSuperPortPage";
	}

	
	/**修改超端功能*/
	public Result updateSuperPortGL(HttpServletRequest req, Integer gameId, Integer isSuperClient)
			throws ServletException, IOException {
		Result result = new Result();  						//创建返回结果
		String admin = GetAdmin.getAdmin(req);  			//获取当前管理员名
		GetData getData = (GetData) BeanFactory.getBean(GetData.class);
		//判断当前管理员是否超时
		if(admin == null){
			//保存日志
			getData.saveCurrentLog(req, "增加签到奖励:操作者未操作超时!", ManagerLog.error);
			//返回结果
			result.setStatus(-1);
			return result;
		}
		
		try {
			
			//管理员权限才能修改
			if(PowerManagment.checkOperCategory(admin) == 1){
				
				//连接登陆服务
				GameRMIServer gameRMIUrl  = CacheGameServer.getGameServer();
				
				//判断是否连接
				if (null == gameRMIUrl) {
					//将玩家查询失败存入到日志表中
					getData.saveCurrentLog(req,"连接服务器失败",ManagerLog.error);
					result.setMsg("连接服务器失败");
					result.setStatus(4);
				}else{
					
					Integer resultRevise = (Integer)gameRMIUrl.exec(RMIConstant.GM_TOOL_CHANGE_PLAYER_SUPER,gameId,isSuperClient);
					//判断是否修改成功
					if(resultRevise==0){
						getData.saveCurrentLog(req,"修改成功",ManagerLog.success);
						result.setMsg("修改成功");
						result.setStatus(0);
						result.setData(gameId);
						
					}else{
						getData.saveCurrentLog(req,"修改超端失败",ManagerLog.error);
						result.setMsg("修改超端失败");
						result.setStatus(1);
					}
				}
				
			}else{
				getData.saveCurrentLog(req,"没有权限修改失败",ManagerLog.error);
				result.setMsg("没有权限修改失败");
				result.setStatus(2);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			getData.saveCurrentLog(req,"未知异常修改失败",ManagerLog.error);
			result.setMsg("未知异常修改失败");
			result.setStatus(3);
			
		}
		return result;
	}

	public String toSelectShareConfig(HttpServletRequest req) throws ServletException, IOException {
		String admin = GetAdmin.getAdmin(req);  			//获取当前管理员名
		GetData getData = (GetData) BeanFactory.getBean(GetData.class);		
		//判断当前管理员或者代理商是否超时
		if(admin == null){
			//保存日志
			getData.saveCurrentLog(req, Code.ADD_INFORMATION_OUTTIME_PAGE, ManagerLog.error);
			
			//返回结果
			return "login/login";
		}
		
		try{
			
			//判断这个管理员是否是超级管理员
			if(PowerManagment.checkOperCategory(admin) != 1){
				
				//记录失败日志
				getData.saveCurrentLog(req,"查询单个玩家信息:没有权限!",ManagerLog.error);
				//返回结果
				return "systemSettingsManagement/selectAllManagerNoPower";
			}
			
			//连接登陆服务
			GameRMIServer gameRMIUrl  = CacheGameServer.getGameServer();
			
			if (null == gameRMIUrl) {
				//将玩家查询失败存入到日志表中
				getData.saveCurrentLog(req,"连接服务器失败",ManagerLog.error);
				return "informationManagment/noConnect";
			}else{
				
				@SuppressWarnings("unchecked")
				Map<String,Object> map = (Map<String, Object>) gameRMIUrl.exec(RMIConstant.GM_TOOL_FIND_SHARECONFIG);
				log.warn("map----------------"+map);
				if(map==null){
					
					getData.saveCurrentLog(req,"查询失败",ManagerLog.error);
					
				}else{
					
					req.setAttribute("map", map);
				}
			}
			 
		}catch(Exception e){
			e.printStackTrace();
			//存入日志
			getData.saveCurrentLog(req,"查询单个玩家信息:未知异常!",ManagerLog.error);
			return "informationManagment/noConnect";
		}
		return "informationManagment/selectShareConfig";
	}

	public String updateShareConfig(HttpServletRequest req) throws ServletException, IOException {
		String admin = GetAdmin.getAdmin(req);  			//获取当前管理员名
		GetData getData = (GetData) BeanFactory.getBean(GetData.class);		
		//判断当前管理员或者代理商是否超时
		if(admin == null){
			//保存日志
			getData.saveCurrentLog(req, Code.ADD_INFORMATION_OUTTIME_PAGE, ManagerLog.error);
			
			//返回结果
			return "login/login";
		}
		
		try{
			
			//判断这个管理员是否是超级管理员
			if(PowerManagment.checkOperCategory(admin) != 1){
				
				//记录失败日志
				getData.saveCurrentLog(req,"查询单个玩家信息:没有权限!",ManagerLog.error);
				//返回结果
				return "systemSettingsManagement/selectAllManagerNoPower";
			}
			
			int id = Integer.parseInt(req.getParameter("id"));
			int shareNumber = Integer.parseInt(req.getParameter("shareNumber"));
			int category = Integer.parseInt(req.getParameter("category"));
			int prize = Integer.parseInt(req.getParameter("prize"));
			int intervalTime = Integer.parseInt(req.getParameter("intervalTime"));
			
			//连接登陆服务
			GameRMIServer gameRMIUrl  = CacheGameServer.getGameServer();
			
			if (null == gameRMIUrl) {
				//将玩家查询失败存入到日志表中
				getData.saveCurrentLog(req,"连接服务器失败",ManagerLog.error);
				
			}else{
				
				 Map<String,Object> remap =(Map<String, Object>) gameRMIUrl.exec(RMIConstant.GM_TOOL_UPDATE_SHARECONFIG,id,category,prize,intervalTime,shareNumber);
				 int flag =(Integer) remap.get("state");
				 
				 if(flag == 0){//修改成功，查询数据
					 Map<String,Object> map = (Map<String, Object>) gameRMIUrl.exec(RMIConstant.GM_TOOL_FIND_SHARECONFIG);
						log.warn("map----------------"+map);
						if(map==null){
							
							getData.saveCurrentLog(req,"查询失败",ManagerLog.error);
							
						}else{
							
							req.setAttribute("map", map);
						}
				 }
			}
			 
		}catch(Exception e){
			e.printStackTrace();
			//存入日志
			getData.saveCurrentLog(req,"查询单个玩家信息:未知异常!",ManagerLog.error);
		}
		return "informationManagment/selectShareConfig";
	}

	public String updateBindConfig(HttpServletRequest req) throws ServletException, IOException {
		String admin = GetAdmin.getAdmin(req);  			//获取当前管理员名
		GetData getData = (GetData) BeanFactory.getBean(GetData.class);		
		//判断当前管理员或者代理商是否超时
		if(admin == null){
			//保存日志
			getData.saveCurrentLog(req, Code.ADD_INFORMATION_OUTTIME_PAGE, ManagerLog.error);
			
			//返回结果
			return "login/login";
		}
		
		try{
			
			//判断这个管理员是否是超级管理员
			if(PowerManagment.checkOperCategory(admin) != 1){
				
				//记录失败日志
				getData.saveCurrentLog(req,"查询单个玩家信息:没有权限!",ManagerLog.error);
				//返回结果
				return "systemSettingsManagement/selectAllManagerNoPower";
			}
			
			int num = Integer.parseInt(req.getParameter("num"));
			
			singleDataMapper.updateNum(num);
			
			int num1 = singleDataMapper.selectById(1);
			req.setAttribute("num", num1);
			
			 
		}catch(Exception e){
			e.printStackTrace();
			//存入日志
			getData.saveCurrentLog(req,"查询配置信息:未知异常!",ManagerLog.error);
		}
		return "informationManagment/selectBindConfig";
	}

	public String toSelectBindConfig(HttpServletRequest req) throws ServletException, IOException {
		String admin = GetAdmin.getAdmin(req);  			//获取当前管理员名
		GetData getData = (GetData) BeanFactory.getBean(GetData.class);		
		//判断当前管理员或者代理商是否超时
		if(admin == null){
			//保存日志
			getData.saveCurrentLog(req, Code.ADD_INFORMATION_OUTTIME_PAGE, ManagerLog.error);
			
			//返回结果
			return "login/login";
		}
		
		try{
			
			//判断这个管理员是否是超级管理员
			if(PowerManagment.checkOperCategory(admin) != 1){
				
				//记录失败日志
				getData.saveCurrentLog(req,"查询单个玩家信息:没有权限!",ManagerLog.error);
				//返回结果
				return "systemSettingsManagement/selectAllManagerNoPower";
			}
			
			int num1 = singleDataMapper.selectById(1);
			req.setAttribute("num", num1);
			
			 
		}catch(Exception e){
			e.printStackTrace();
			//存入日志
			getData.saveCurrentLog(req,"查询配置信息:未知异常!",ManagerLog.error);
		}
		return "informationManagment/selectBindConfig";
	}

	public String toSelectRedemption(HttpServletRequest req) throws ServletException, IOException {
		String admin = GetAdmin.getAdmin(req);  			//获取当前管理员名
		GetData getData = (GetData) BeanFactory.getBean(GetData.class);		
		//判断当前管理员或者代理商是否超时
		if(admin == null){
			//保存日志
			getData.saveCurrentLog(req, Code.ADD_INFORMATION_OUTTIME_PAGE, ManagerLog.error);
			
			//返回结果
			return "login/login";
		}
		
		try{
			
			//判断这个管理员是否是超级管理员
			if(PowerManagment.checkOperCategory(admin) != 1){
				
				//记录失败日志
				getData.saveCurrentLog(req,"查询单个玩家信息:没有权限!",ManagerLog.error);
				//返回结果
				return "systemSettingsManagement/selectAllManagerNoPower";
			}
			
			//连接登陆服务
			GameRMIServer gameRMIUrl  = CacheGameServer.getGameServer();
			
			if (null == gameRMIUrl) {
				//将玩家查询失败存入到日志表中
				getData.saveCurrentLog(req,"连接服务器失败",ManagerLog.error);
				
			}else{
				
				@SuppressWarnings("unchecked")
				List<Map<String,Object>> list =(List<Map<String, Object>>) gameRMIUrl.exec(RMIConstant.GM_TOOL_FIND_REDEMPTION);
				log.warn("list----------------"+list);
				if(list==null){
					
					getData.saveCurrentLog(req,"查询失败",ManagerLog.error);
					
				}else{
					
					req.setAttribute("list", list);
				}
			}
			 
		}catch(Exception e){
			e.printStackTrace();
			//存入日志
			getData.saveCurrentLog(req,"查询单个玩家信息:未知异常!",ManagerLog.error);
		}
		
		return "informationManagment/selectRedemption";
	}

	public String toUpdateRedemption(HttpServletRequest req,Integer id,String title, Integer integral, Integer fk) throws ServletException, IOException {
		String admin = GetAdmin.getAdmin(req);  			//获取当前管理员名
		GetData getData = (GetData) BeanFactory.getBean(GetData.class);		
		//判断当前管理员或者代理商是否超时
		if(admin == null){
			//保存日志
			getData.saveCurrentLog(req, Code.ADD_INFORMATION_OUTTIME_PAGE, ManagerLog.error);
			
			//返回结果
			return "login/login";
		}
		
		try{
			
			//判断这个管理员是否是超级管理员
			if(PowerManagment.checkOperCategory(admin) != 1){
				
				//记录失败日志
				getData.saveCurrentLog(req,"查询单个玩家信息:没有权限!",ManagerLog.error);
				//返回结果
				return "systemSettingsManagement/selectAllManagerNoPower";
			}
			
			req.setAttribute("id", id);
			req.setAttribute("title", title);
			req.setAttribute("integral", integral);
			req.setAttribute("fk", fk);
			 
		}catch(Exception e){
			e.printStackTrace();
			//存入日志
			getData.saveCurrentLog(req,"查询单个玩家信息:未知异常!",ManagerLog.error);
		}
		return "informationManagment/updateRedemption";
	}

	public String updateRedemption(HttpServletRequest req) throws ServletException, IOException {
		String admin = GetAdmin.getAdmin(req);  			//获取当前管理员名
		GetData getData = (GetData) BeanFactory.getBean(GetData.class);		
		//判断当前管理员或者代理商是否超时
		if(admin == null){
			//保存日志
			getData.saveCurrentLog(req, Code.ADD_INFORMATION_OUTTIME_PAGE, ManagerLog.error);
			
			//返回结果
			return "login/login";
		}
		
		try{
			
			//判断这个管理员是否是超级管理员
			if(PowerManagment.checkOperCategory(admin) != 1){
				
				//记录失败日志
				getData.saveCurrentLog(req,"查询单个玩家信息:没有权限!",ManagerLog.error);
				//返回结果
				return "systemSettingsManagement/selectAllManagerNoPower";
			}
			
			//连接登陆服务
			GameRMIServer gameRMIUrl  = CacheGameServer.getGameServer();
			
			if (null == gameRMIUrl) {
				//将玩家查询失败存入到日志表中
				getData.saveCurrentLog(req,"连接服务器失败",ManagerLog.error);
				
			}else{
				
				String parameter = req.getParameter("id");
				int id = Integer.parseInt(parameter);
				
				String title = req.getParameter("title");
				String parameter2 = req.getParameter("integral");
				int integral = Integer.parseInt(parameter2);
				String parameter3 = req.getParameter("fk");
				int fk = Integer.parseInt(parameter3);
				System.out.println(id+"-"+title+"-"+integral+"-"+fk);
				@SuppressWarnings("unchecked")
				boolean falg = (Boolean) gameRMIUrl.exec(RMIConstant.GM_TOOL_UPDATE_REDEMPTION,id,title,integral,fk);
				List<Map<String,Object>> list =(List<Map<String, Object>>) gameRMIUrl.exec(RMIConstant.GM_TOOL_FIND_REDEMPTION);
				log.warn("list----------------"+list);
				if(list==null){
					
					getData.saveCurrentLog(req,"查询失败",ManagerLog.error);
					
				}else{
					
					req.setAttribute("list", list);
				}
			}
			 
		}catch(Exception e){
			e.printStackTrace();
			//存入日志
			getData.saveCurrentLog(req,"查询单个玩家信息:未知异常!",ManagerLog.error);
		}
		
		return "informationManagment/selectRedemption";
	}

	public String delRedemption(HttpServletRequest req, Integer id) throws ServletException, IOException {
		String admin = GetAdmin.getAdmin(req);  			//获取当前管理员名
		GetData getData = (GetData) BeanFactory.getBean(GetData.class);		
		//判断当前管理员或者代理商是否超时
		if(admin == null){
			//保存日志
			getData.saveCurrentLog(req, Code.ADD_INFORMATION_OUTTIME_PAGE, ManagerLog.error);
			
			//返回结果
			return "login/login";
		}
		
		try{
			
			//判断这个管理员是否是超级管理员
			if(PowerManagment.checkOperCategory(admin) != 1){
				
				//记录失败日志
				getData.saveCurrentLog(req,"查询单个玩家信息:没有权限!",ManagerLog.error);
				//返回结果
				return "systemSettingsManagement/selectAllManagerNoPower";
			}
			
			//连接登陆服务
			GameRMIServer gameRMIUrl  = CacheGameServer.getGameServer();
			
			if (null == gameRMIUrl) {
				//将玩家查询失败存入到日志表中
				getData.saveCurrentLog(req,"连接服务器失败",ManagerLog.error);
				
			}else{
				
		
				@SuppressWarnings("unchecked")
				boolean flag=  (Boolean) gameRMIUrl.exec(RMIConstant.GM_TOOL_DEL_REDEMPTION,id);
				List<Map<String,Object>> list =(List<Map<String, Object>>) gameRMIUrl.exec(RMIConstant.GM_TOOL_FIND_REDEMPTION);
				log.warn("list----------------"+list);
				if(list==null){
					
					getData.saveCurrentLog(req,"查询失败",ManagerLog.error);
					
				}else{
					
					req.setAttribute("list", list);
				}
			}
			 
		}catch(Exception e){
			e.printStackTrace();
			//存入日志
			getData.saveCurrentLog(req,"查询单个玩家信息:未知异常!",ManagerLog.error);
		}
		
		return "informationManagment/selectRedemption";
	}

	public String addRedemption(HttpServletRequest req) throws ServletException, IOException {
		String admin = GetAdmin.getAdmin(req);  			//获取当前管理员名
		GetData getData = (GetData) BeanFactory.getBean(GetData.class);		
		//判断当前管理员或者代理商是否超时
		if(admin == null){
			//保存日志
			getData.saveCurrentLog(req, Code.ADD_INFORMATION_OUTTIME_PAGE, ManagerLog.error);
			
			//返回结果
			return "login/login";
		}
		
		try{
			
			//判断这个管理员是否是超级管理员
			if(PowerManagment.checkOperCategory(admin) != 1){
				
				//记录失败日志
				getData.saveCurrentLog(req,"查询单个玩家信息:没有权限!",ManagerLog.error);
				//返回结果
				return "systemSettingsManagement/selectAllManagerNoPower";
			}
			
			//连接登陆服务
			GameRMIServer gameRMIUrl  = CacheGameServer.getGameServer();
			
			if (null == gameRMIUrl) {
				//将玩家查询失败存入到日志表中
				getData.saveCurrentLog(req,"连接服务器失败",ManagerLog.error);
				
			}else{
				
				String parameter = req.getParameter("id");
				int id = Integer.parseInt(parameter);
				
				String title = req.getParameter("title");
				String parameter2 = req.getParameter("integral");
				int integral = Integer.parseInt(parameter2);
				String parameter3 = req.getParameter("fk");
				int fk = Integer.parseInt(parameter3);
				System.out.println(id+"-"+title+"-"+integral+"-"+fk);
				@SuppressWarnings("unchecked")
				boolean falg=  (Boolean) gameRMIUrl.exec(RMIConstant.GM_TOOL_ADD_REDEMPTION,id,title,integral,fk);
				if(falg){
					List<Map<String,Object>> list =(List<Map<String, Object>>) gameRMIUrl.exec(RMIConstant.GM_TOOL_FIND_REDEMPTION);
					log.warn("list----------------"+list);
					if(list==null){
						
						getData.saveCurrentLog(req,"查询失败",ManagerLog.error);
						
					}else{
						
						req.setAttribute("list", list);
					}
				}else{
					return "informationManagment/addFail";
				}
				
			}
			 
		}catch(Exception e){
			e.printStackTrace();
			//存入日志
			getData.saveCurrentLog(req,"查询单个玩家信息:未知异常!",ManagerLog.error);
		}
		
		return "informationManagment/selectRedemption";
	}



	
	
}


















