package com.game.business.service;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.javassist.bytecode.stackmap.MapMaker;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.game.base.util.BeanFactory;
import com.game.base.util.Code;
import com.game.base.util.GameUtil;
import com.game.base.util.GetAdmin;
import com.game.base.util.GetData;
import com.game.base.util.PowerManagment;
import com.game.base.util.Result;
import com.game.base.util.StringUtil;
import com.game.business.mapper.AgentMapper;
import com.game.business.mapper.LogMapper;
import com.game.business.mapper.MgerMapper;
import com.game.business.mapper.PlayerRealNameApplyMapper;
import com.game.business.mapper.PlayerShareInfoMapper;
import com.game.business.mapper.PlayerTradeRecordMapper;
import com.game.business.mapper.PromoCodeMapper;
import com.game.business.mapper.SingleDataMapper;
import com.game.business.model.Agent;
import com.game.business.model.PlayerTradeRecode;
import com.game.business.model.PromoCode;
import com.game.business.model.Share;
import com.game.business.model.manager.Manager;
import com.game.business.model.manager.ManagerLog;
import com.game.business.model.player.RealNameInfo;
import com.game.cache.CacheGameServer;
import com.game.cache.CacheLoginServer;
import com.game.constant.D;
import com.game.constant.LoginRMIConstant;
import com.game.constant.RMIConstant;
import com.game.game.hallMapper.PromoCode1Mapper;
import com.game.game.mapper.ShareMapper;
import com.game.game.model.Player;
import com.ranger.module.po.Usr;
import com.ranger.remote.LoginServerRMI;
import com.ranger.remote.RMIResult;

import cocl.rmi.GameRMIServer;
/**
 * 
 * <li>@ClassName: 游戏玩家业务层接口实现类
 * <li>@author 周强
 * <li>@date 2016年11月8日
 *
 */
@Service
public class GamePlayerServiceImpl implements GamePlayerService{
	@Resource
	private LogMapper logMapper;
	@Resource
	private PlayerTradeRecordMapper playerTradeRecordMapper;
	@Resource
	private AgentMapper agentMapper;
	@Resource
	private MgerMapper mgMapper;
	@Resource
	PromoCodeMapper promoCodeMapper;
	@Resource
	SingleDataMapper singleDataMapper;
	@Resource
	PromoCode1Mapper promoCode1Mapper;
	@Resource
	PlayerRealNameApplyMapper playerRealNameApplyMapper;
	@Resource
	PlayerShareInfoMapper playerShareInfoMapper;
	
	Log log = LogFactory.getLog(GamePlayerServiceImpl.class);
	/** 请求通过游戏id查询玩家信息页面*/
	@Transactional
	public String toSelectPlayer(HttpServletRequest req) throws ServletException, IOException {
		String admin = GetAdmin.getAdmin(req);  			//获取当前管理员名
		GetData getData = (GetData) BeanFactory.getBean(GetData.class);
		
		//判断当前管理员或者代理商是否超时
		if(admin == null){
			//保存日志
			getData.saveCurrentLog(req, Code.SELECT_PLAYERPAGE_OUTTIME_PAGE, ManagerLog.error);
			
			//返回结果
			return "login/login";
		}
		
		//成功
		getData.saveCurrentLog(req, Code.SELECT_PLAYERPAGE_SUC_PAGE, ManagerLog.success);
		return "playerManagement/selectPlayerByGameId";
	}
	
	/** 通过游戏id查询玩家信息*/
	public Result selectPlayerById(HttpServletRequest req, Integer gameId) throws ServletException, IOException {
		Result result = new Result();  						//创建返回结果
		String admin = GetAdmin.getAdmin(req);  			//获取当前管理员名
		GetData getData = (GetData) BeanFactory.getBean(GetData.class);
		//判断当前管理员是否超时
		if(admin == null){
			//保存日志
			getData.saveCurrentLog(req, Code.SELETE_PLAYERPAGE_OUT_TIME, ManagerLog.error);
			//返回结果
			result.setStatus(-1);
			return result;
		}
		
		if(gameId == null){
			//保存日志
			getData.saveCurrentLog(req, Code.SELETE_PLAYERPAGE_PARAMETERS_ERROR, ManagerLog.error);
			//返回结果
			result.setStatus(1);
			result.setMsg(Code.SELETE_PLAYERPAGE_PARAMETERS_ERROR);
			return result;
		}
		
		try{
			//获取游戏服连接
			GameRMIServer gameRMIServer = CacheGameServer.getGameServer();
			System.out.println(gameRMIServer);
			if(gameRMIServer == null){
				//保存日志
				getData.saveCurrentLog(req, Code.SELETE_PLAYERPAGE_NOT_SERVER, ManagerLog.error);
				//返回结果
				result.setStatus(2);
				result.setMsg(Code.SELETE_PLAYERPAGE_NOT_SERVER);
				return result;
			}
			@SuppressWarnings("unchecked")
			Map<String,Object> map = (Map<String, Object>) gameRMIServer.exec(RMIConstant.GM_TOOL_FIND_PLAYER, gameId);
			//判断游戏服务器是否传过来数据
			if(map == null){
				//保存日志
				getData.saveCurrentLog(req, Code.SELETE_PLAYERPAGE_NOT_SERVER_DATA, ManagerLog.error);
				//返回结果
				result.setStatus(3);
				result.setMsg(Code.SELETE_PLAYERPAGE_NOT_SERVER_DATA);
				return result;
			}

			map.put("pNiceName",map.get("pNickName"));
			
			//保存日志
			getData.saveCurrentLog(req, Code.SELETE_PLAYERPAGE_SUC, ManagerLog.success);
			//返回结果
			result.setStatus(0);
			result.setData(map);
			result.setMsg(Code.SELETE_PLAYERPAGE_SUC);
			return result;
			
		}catch(Exception e){
			e.printStackTrace();
			//记录失败日志
			getData.saveCurrentLog(req, Code.SELETE_PLAYERPAGE_UNKNOWN, ManagerLog.error);
			//返回结果
			result.setStatus(4);
			result.setMsg(Code.SELETE_PLAYERPAGE_UNKNOWN);
		}
		return result;
	}
	/** 给玩家添加房卡页面*/
	@Transactional
	public String toAddRoomCard(HttpServletRequest req,String pNiceName,Integer gameId,Integer roomCard)throws ServletException, IOException {
		String admin = GetAdmin.getAdmin(req);  			//获取当前管理员名
		GetData getData = (GetData) BeanFactory.getBean(GetData.class);
		
		//判断当前管理员或者代理商是否超时
		if(admin == null){
			//保存日志
			getData.saveCurrentLog(req, Code.SELECT_PLAYERPAGE_OUTTIME_PAGE, ManagerLog.error);
			
			//返回结果
			return "login/login";
		}
		
		//将房卡数,玩家id,玩家昵称存入到req中
		req.setAttribute("pNiceName",pNiceName);
		req.setAttribute("gameId",gameId);
		req.setAttribute("roomCard",roomCard);
		PromoCode promoCode = promoCodeMapper.selectByGameId(gameId);
		if(promoCode==null){
			String guanliyuan="管理员";
			req.setAttribute("account",guanliyuan);
		}else{
			req.setAttribute("account",promoCode.getAccount());
		}
		
		//判断操作者时管理员还是代理商
		Manager mg = mgMapper.findByName(admin);
		Agent agent = agentMapper.findAgentByName(admin);
		
		if(mg != null && agent == null){  //管理员
			req.setAttribute("mark", -1);
			//成功
			getData.saveCurrentLog(req, Code.SELECT_PLAYERPAGE_SUC_PAGE, ManagerLog.success);
		}else if(agent != null && mg == null){
			req.setAttribute("agentRoomCard", agent.getRoomCard());
			req.setAttribute("account", agent.getAccount());
			req.setAttribute("mark", -2);
			//成功,记录日志
			getData.saveCurrentLog(req, Code.SELECT_PLAYERPAGE_SUC_PAGE, ManagerLog.success);
		}else{
			//记录失败
			getData.saveCurrentLog(req, Code.SELECT_PLAYERPAGE_NOT_POWER_PAGE, ManagerLog.error);
			return "systemSettingsManagement/selectAllManagerNoPower";
		}
		return "playerManagement/addRoomCard";
	}
	
	/** 给玩家添加房卡*/
	@Transactional
	public Result addRoomCard(HttpServletRequest req, Integer gameId, Integer addRoomCard)
			throws ServletException, IOException {
		Result result = new Result();  						//创建返回结果
		String admin = GetAdmin.getAdmin(req);  			//获取当前管理员名
		PlayerTradeRecode playerTradeRecode = new PlayerTradeRecode();
		GetData getData = (GetData) BeanFactory.getBean(GetData.class);
		String ip = GetData.getRemoteHost(req); 			//获取管理员ip
		
		//判断当前管理员是否超时
		if(admin == null){
			//保存日志
			getData.saveCurrentLog(req, Code.ADD_PLAYER_ROOMCARD_OUT_TIME, ManagerLog.error);
			//返回结果
			result.setStatus(-1);
			return result;
		}
		
		if(gameId == null){
			//保存日志
			getData.saveCurrentLog(req, Code.ADD_PLAYER_ROOMCARD_PARAMETERS_ERROR, ManagerLog.error);
			//返回结果
			result.setStatus(1);
			result.setMsg(Code.ADD_PLAYER_ROOMCARD_PARAMETERS_ERROR);
			return result;
		}
		
		try{
			//获取游戏服连接
			GameRMIServer gameRMIServer = CacheGameServer.getGameServer();
			if(gameRMIServer == null){
				//保存日志
				getData.saveCurrentLog(req, Code.ADD_PLAYER_ROOMCARD_NOT_SERVER, ManagerLog.error);
				//返回结果
				result.setStatus(2);
				result.setMsg(Code.ADD_PLAYER_ROOMCARD_NOT_SERVER);
				return result;
			}
			
			//判断操作者时管理员还是代理商
			Manager mg = mgMapper.findByName(admin);
			Agent agent = agentMapper.findAgentByName(admin);
			
			if(mg != null && agent == null){  //管理员
				@SuppressWarnings("unchecked")
				Integer m = (Integer) gameRMIServer.exec(RMIConstant.GM_TOOL_CHARGE_TREASURE,gameId, addRoomCard);
				@SuppressWarnings("unchecked")
				Map<String,Object> map = (Map<String, Object>) gameRMIServer.exec(RMIConstant.GM_TOOL_FIND_PLAYER, gameId);
				String playerRoomCard = ((Long) map.get("roomCard")).toString();
				//判断减去的房卡数超出玩家的房卡没有
				if(Integer.valueOf(playerRoomCard)+addRoomCard <0){
					//记录失败
					getData.saveCurrentLog(req, Code.ADD_PLAYERP_ROOMCARD_BEYOND, ManagerLog.error);
					result.setStatus(4);
					result.setMsg(Code.ADD_PLAYERP_ROOMCARD_BEYOND);
					return result;
				}
				
				playerTradeRecode.setRemitAccount(mg.getName());
				playerTradeRecode.setRemitCategory(1);
				playerTradeRecode.setGameId(gameId);
				playerTradeRecode.setTradeCategory(1);
				playerTradeRecode.setRemitRoomCard(-1);
				playerTradeRecode.setPlayerRoomCard(Integer.valueOf(playerRoomCard)+addRoomCard);
				playerTradeRecode.setOperateId(mg.getId());
				playerTradeRecordMapper.save(playerTradeRecode);
				
				//成功
				getData.saveCurrentLog(req, Code.ADD_PLAYER_ROOMCARD_SUC, ManagerLog.success);
				//返回结果
				result.setStatus(0);
				result.setMsg(Code.ADD_PLAYER_ROOMCARD_SUC);
				return result;
			}else if(agent != null && mg == null){  //代理商
				//判断传入进来的房卡是否为正整数或者大于代理商自身的房卡数
				if(addRoomCard<0 || (agent.getRoomCard() - addRoomCard) <0){
					getData.saveCurrentLog(req, Code.ADD_PLAYERP_ROOMCARD_NUMBER, ManagerLog.error);
					//返回结果
					result.setStatus(3);
					result.setMsg(Code.ADD_PLAYERP_ROOMCARD_NUMBER);
					return result;
				}
				@SuppressWarnings("unchecked")
				Integer m = (Integer) gameRMIServer.exec(RMIConstant.GM_TOOL_CHARGE_TREASURE,gameId, addRoomCard);
				@SuppressWarnings("unchecked")
				Map<String,Object> map = (Map<String, Object>) gameRMIServer.exec(RMIConstant.GM_TOOL_FIND_PLAYER, gameId);
				String playerRoomCard = ((Long) map.get("roomCard")).toString();
				
				//修改代理商的参数数据
				agent.setLastLoginIP(ip);
				agent.setLastLoginTime(agent.getLastLoginTime()+1);
				agent.setRoomCard(agent.getRoomCard()-addRoomCard);
				agentMapper.updateByName(agent);
				
				playerTradeRecode.setRemitAccount(agent.getAccount());
				playerTradeRecode.setRemitCategory(2);
				playerTradeRecode.setGameId(gameId);
				playerTradeRecode.setTradeCategory(2);
				playerTradeRecode.setRemitRoomCard(agent.getRoomCard());
				playerTradeRecode.setPlayerRoomCard(Integer.valueOf(playerRoomCard)+addRoomCard);
				playerTradeRecode.setOperateId(agent.getUserID());
				playerTradeRecordMapper.save(playerTradeRecode);
				
				//成功,记录日志
				getData.saveCurrentLog(req, Code.ADD_PLAYER_ROOMCARD_SUC, ManagerLog.success);
				//返回结果
				result.setStatus(0);
				result.setMsg(Code.ADD_PLAYER_ROOMCARD_SUC);
				return result;
			}else{
				//记录失败
				getData.saveCurrentLog(req, Code.ADD_PLAYERP_ROOMCARD_NOT_POWER, ManagerLog.error);
				//返回结果
				result.setStatus(5);
				result.setMsg(Code.ADD_PLAYERP_ROOMCARD_NOT_POWER);
				return result;
			}
		}catch(Exception e){
			e.printStackTrace();
			//记录失败日志
			getData.saveCurrentLog(req, Code.ADD_PLAYER_ROOMCARD_UNKNOWN, ManagerLog.error);
			//返回结果
			result.setStatus(6);
			result.setMsg(Code.ADD_PLAYER_ROOMCARD_UNKNOWN);
		}
		return result;
	}
	
	
	/** 请求查询玩家多条交易记录页面*/
	@Transactional
	public String toSelectRecodByCondition(HttpServletRequest req,Integer index) throws ServletException, IOException {
		String admin = GetAdmin.getAdmin(req);   					//获取当前管理员名
//		String category = (String)req.getParameter("category");     //查询类别
		Object condition = req.getParameter("condition");     		//查询条件
		
		
		int category = 0;
		//System.out.println("playerIndex:"+playerIndex);
		GetData getData = (GetData) BeanFactory.getBean(GetData.class);
		
		//判断当前管理员或者代理商是否超时
		if(admin == null){
			//保存日志
			getData.saveCurrentLog(req, Code.SELECT_MANY_PLAYER_ROOMCARD_OUT_TIME, ManagerLog.error);
			
			//返回结果
			return "login/login";
		}
		try{
			List<PlayerTradeRecode> list = null;
			
			//获取当前操作管理员
			Manager mg = mgMapper.findByName(admin);
			Agent agent = agentMapper.findAgentByName(admin);
			if(mg != null && agent == null){  //管理员可以查询所有的玩家交易日志
				switch(category){
					case 0:
					//获取所有消息记录
					list = playerTradeRecordMapper.selectAllRecord();	
						
				}
			}else if(agent != null && mg == null){  //代理商只能查看属于自己的玩家的日志
				
			}else{
				//记录失败日志
				getData.saveCurrentLog(req, Code.SELECT_MANY_PLAYERP_ROOMCARD_NOT_POWER, ManagerLog.error);
				
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
				
				//将管理员查询成功存入到日志表中
				getData.saveCurrentLog(req, Code.SELECT_INFORMATION_ALL_SUC, ManagerLog.success);
				
				return "playerManagement/selectManyPlayerTradeRecord";
			}else{
				//将管理员查询失败存入到日志表中
				getData.saveCurrentLog(req, Code.SELECT_MANY_PLAYER_ROOMCARD_ERROR, ManagerLog.error);
				return "playerManagement/selectManyPlayerTradeRecord";
			}
		}catch(Exception e){
			e.printStackTrace();
			//将管理员查询失败存入到日志表中
			getData.saveCurrentLog(req, Code.SELECT_MANY_PLAYER_ROOMCARD_UNKNOWN, ManagerLog.error);
		}
		return "playerManagement/selectManyPlayerTradeRecord";
	}
	
	
	/** 查询所有玩家*/
	@Transactional
	public String selectAllPlayer(HttpServletRequest req,Integer index) throws ServletException, IOException {
		String admin = GetAdmin.getAdmin(req);   					//获取当前管理员名
		GetData getData = (GetData) BeanFactory.getBean(GetData.class);
		
		//判断当前管理员或者代理商是否超时
		if(admin == null){
			//保存日志
			getData.saveCurrentLog(req, "查询所有玩家:当前操作者未操作超时!", ManagerLog.error);
			
			//返回结果
			return "login/login";
		}
		try{
			//判断当前操作者是否是管理员,非管理员不能访问
			if(PowerManagment.checkOperCategory(admin) != 1){
				//保存日志
				getData.saveCurrentLog(req, "查询所有玩家:没有权限!", ManagerLog.error);
				
				//返回结果
				return "systemSettingsManagement/selectAllManagerNoPower";
			}
			
			//获取游戏服连接
			GameRMIServer gameRMIServer = CacheGameServer.getGameServer();
			if(gameRMIServer == null){
				//保存日志
				getData.saveCurrentLog(req, "查询所有玩家:未获得服务器连接!", ManagerLog.error);
				//返回结果
				return "playerManagement/selectAllPlayer";
			}
			
			@SuppressWarnings("unchecked")
			Map<String , Object> map = (Map<String , Object>) gameRMIServer.exec(RMIConstant.GM_TOOL_FIND_PLAYERS,index,20);
			//判断通过服务器拿到的数据
			if(map == null){
				//保存日志
				getData.saveCurrentLog(req, "查询所有玩家:未从服务器获得数据!", ManagerLog.error);
				//返回结果
				return "playerManagement/selectAllPlayer";
			}
			
			Integer count = (Integer) map.get("count");
			Integer pagecount = 0;
			@SuppressWarnings("unchecked")
			List<Map<String, Object>> list = (List<Map<String, Object>>)map.get("player");
			
			//获取玩家的上级代理
			for(int i = 0;i<list.size();i++){
				Integer gameId = (Integer)list.get(i).get("gameId");
				PromoCode promoCode = promoCodeMapper.selectByGameId(gameId);
				if(promoCode == null){
					list.get(i).put("higher", "管理员");
				}else{
					list.get(i).put("higher", promoCode.getAccount());
				}
			}
			
			if(count < 20){
				pagecount = 1;
			}else{
				if(count % 20 == 0){
					pagecount = count/20;
				}else{
					pagecount = count/20 + 1;
				}
			}
			
			req.setAttribute("count", count);
			req.setAttribute("index", index);  			//将传进来的index页面数保存到req中
			req.setAttribute("pagesize", 20);  			//将每页显示的记录数存入req中
			req.setAttribute("pagecount", pagecount);  	//将pagecount用req保存
			req.setAttribute("list", list);  			//将此次要显示的记录放入计划list中
			
			getData.saveCurrentLog(req, "查询所有玩家:操作成功!", ManagerLog.success);
			return "playerManagement/selectAllPlayer";
			
		}catch(Exception e){
			e.printStackTrace();
			//将管理员查询失败存入到日志表中
			getData.saveCurrentLog(req, "查询所有玩家:未知异常!", ManagerLog.error);
		}
		return "playerManagement/selectAllPlayer";
	}
	
	
	/** 请求查询玩家注册时房卡数的页面*/
	public String toSelectPlayerStartRoomCard(HttpServletRequest req) throws ServletException, IOException {
		String admin = GetAdmin.getAdmin(req);  			//获取当前管理员名
		GetData getData = (GetData) BeanFactory.getBean(GetData.class);
		
		//判断当前管理员是否超时
		if(admin == null){
			//保存日志
			getData.saveCurrentLog(req, Code.SELECT_PLAYER_STRAT_ROOMCARD_OUTTIME_PAGE, ManagerLog.error);
			//返回结果
			return "login/login";
		}
		
		try{
			//获取游戏服连接
			GameRMIServer gameRMIServer = CacheGameServer.getGameServer();
			if(gameRMIServer == null){
				//保存日志
				getData.saveCurrentLog(req, Code.SELECT_PLAYER_STRAT_ROOMCARD_NOT_SERVER, ManagerLog.error);
				//返回结果
				return "playerManagement/selectPlayerStartRoomcard";
			}
			
			@SuppressWarnings("unchecked")
			Long roomCard= (Long) gameRMIServer.exec(RMIConstant.GM_TOOL_FIND_ROOMCARD_START);
			
			//判断时候获取到注册时的房卡数没
			if(roomCard != null){
				req.setAttribute("roomCard", roomCard);
				//保存日志
				getData.saveCurrentLog(req, Code.SELECT_PLAYER_STRAT_ROOMCARD_SUC_PAGE, ManagerLog.success);
			}else{
				req.setAttribute("roomCard", -1);
				
				//保存日志
				getData.saveCurrentLog(req, Code.SELECT_PLAYER_STRAT_ROOMCARD_NOT_SERVER_DATA, ManagerLog.success);
			}
			//玩家绑定 赠送房卡数
			req.setAttribute("roomCard2",singleDataMapper.selectBy(1));
			//返回结果
			return "playerManagement/selectPlayerStartRoomcard";
			
		}catch(Exception e){
			e.printStackTrace();
			//记录失败日志
			getData.saveCurrentLog(req, Code.SELECT_PLAYER_STRAT_ROOMCARD_UNKNOWN_PAGE, ManagerLog.error);
		}
		return "playerManagement/selectPlayerStartRoomcard";
	}
	
	/** 修改玩家注册时房卡数*/
	public Result alertPlayerStartRoomCard(HttpServletRequest req, String roomCardNumber)
			throws ServletException, IOException {
		Result result = new Result();  						//创建返回结果
		String admin = GetAdmin.getAdmin(req);  			//获取当前管理员名
		GetData getData = (GetData) BeanFactory.getBean(GetData.class);
		
		//判断当前管理员是否超时
		if(admin == null){
			//保存日志
			getData.saveCurrentLog(req, Code.ALERT_PLAYER_ROOMCARD_START_OUT_TIME, ManagerLog.error);
			//返回结果
			result.setStatus(-1);
			return result;
		}
		
		//验证页面输入的参数格式
		Long roomCard = null;
		try{
			roomCard = Long.valueOf(roomCardNumber);
		}catch(Exception e){
			//保存日志
			getData.saveCurrentLog(req, Code.ALERT_PLAYER_ROOMCARD_START_PARAMETERS_ERROR, ManagerLog.error);
			//返回结果
			result.setStatus(1);
			result.setMsg(Code.ALERT_PLAYER_ROOMCARD_START_PARAMETERS_ERROR);
			return result;
		}
		
		try{
			//获取游戏服连接
			GameRMIServer gameRMIServer = CacheGameServer.getGameServer();
			if(gameRMIServer == null){
				//保存日志
				getData.saveCurrentLog(req, Code.ALERT_PLAYER_ROOMCARD_START_NOT_SERVER, ManagerLog.error);
				//返回结果
				result.setStatus(2);
				result.setMsg(Code.ALERT_PLAYER_ROOMCARD_START_NOT_SERVER);
				return result;
			}
			
			//判断操作者时管理员还是代理商
			Manager mg = mgMapper.findByName(admin);
			Agent agent = agentMapper.findAgentByName(admin);
			
			if(mg != null && agent == null){  //管理员
				System.out.println("roomCardNumber:"+roomCardNumber);
				@SuppressWarnings("unchecked")
				Integer m = (Integer) gameRMIServer.exec(RMIConstant.GM_TOOL_ALERT_ROOMCARD_START,roomCardNumber);
				
				if(m == 0){
					//成功
					getData.saveCurrentLog(req, Code.ALERT_PLAYER_ROOMCARD_START_SUC, ManagerLog.success);
					//返回结果
					result.setStatus(0);
					result.setMsg(Code.ALERT_PLAYER_ROOMCARD_START_SUC);
					return result;
				}
				
				//失败
				getData.saveCurrentLog(req, Code.ALERT_PLAYERP_ROOMCARD_START_NOT_SERVER_DATA, ManagerLog.success);
				//返回结果
				result.setStatus(0);
				result.setMsg(Code.ALERT_PLAYERP_ROOMCARD_START_NOT_SERVER_DATA);
				return result;
			}else{
				//记录失败
				getData.saveCurrentLog(req, Code.ALERT_PLAYERP_ROOMCARD_START_NOT_POWER, ManagerLog.error);
				//返回结果
				result.setStatus(3);
				result.setMsg(Code.ALERT_PLAYERP_ROOMCARD_START_NOT_POWER);
				return result;
			}
		}catch(Exception e){
			e.printStackTrace();
			//记录失败日志
			getData.saveCurrentLog(req, Code.ALERT_PLAYER_ROOMCARD_START_UNKNOWN, ManagerLog.error);
			//返回结果
			result.setStatus(4);
			result.setMsg(Code.ALERT_PLAYER_ROOMCARD_START_UNKNOWN);
		}
		return result;
	}
	/**修改绑定时赠送的房卡数*/
	public Result updateRoomCardBin(HttpServletRequest req, Integer roomCardNumber) throws ServletException, IOException {
		Result result = new Result();  						//创建返回结果
		String admin = GetAdmin.getAdmin(req);  			//获取当前管理员名
		GetData getData = (GetData) BeanFactory.getBean(GetData.class);
		
		//判断当前管理员是否超时
		if(admin == null){
			//保存日志
			getData.saveCurrentLog(req,"修改玩家绑定 赠送房卡数:登录超时!", ManagerLog.error);
			//返回结果
			result.setStatus(-1);
			return result;
		}
		
		//验证页面输入的参数格式
		Long roomCard = null;
		try{
			roomCard = Long.valueOf(roomCardNumber);
		}catch(Exception e){
			//保存日志
			getData.saveCurrentLog(req,"修改玩家绑定 赠送房卡数:参数传递错误!", ManagerLog.error);
			//返回结果
			result.setStatus(1);
			result.setMsg("修改玩家绑定 赠送房卡数:参数传递错误!");
			return result;
		}
		
		try{
			
			if(PowerManagment.checkOperCategory(admin)==1 && roomCardNumber>=0) {
				Map map=new HashMap();
				map.put("type", 1);
				map.put("num",roomCardNumber);
				singleDataMapper.update(map);
				//失败
				getData.saveCurrentLog(req,"修改绑定玩家赠送房卡:修改成功", ManagerLog.success);
				//返回结果
				result.setStatus(0);
				result.setMsg("修改成功");
				return result;
			}else{
				//记录失败
				getData.saveCurrentLog(req,"修改绑定玩家赠送房卡:修改失败", ManagerLog.error);
				//返回结果
				result.setStatus(1);
				result.setMsg("修改失败");
				return result;
			}
		}catch(Exception e){
			e.printStackTrace();
			//记录失败日志
			getData.saveCurrentLog(req,"修改绑定玩家赠送房卡:未知异常", ManagerLog.error);
			//返回结果
			result.setStatus(1);
			result.setMsg("未知异常");
			return result;
		}
	}
	//添加玩家
	public Result addPlayer(HttpServletRequest req, String name, String pwd, String sys)
			throws ServletException, IOException {
		Result result = new Result();  //创建返回结果
		ManagerLog log = new ManagerLog(); //创建日志
		String ip = GetData.getRemoteHost(req); //获取管理员ip
		String system = GetData.getSystem(req);  //获取管理员操作系统
		String admin = GetAdmin.getAdmin(req);  //获取当前管理员名
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
			log.setDsc(admin+Code.ADD_PLAYER_OUTTIME);
			logMapper.logSave(log);
			
			//返回结果
			result.setMsg(Code.ADD_PLAYER_OUTTIME);
			result.setStatus(-1);
			return result;
		}
		try{
			//正则验证
			String namereg = "^[a-zA-Z0-9\\.]{9,64}$";
			String pwdReg = "^[0-9a-zA-Z\\.]{6,18}$";
			if(!name.matches(namereg)){
				//将玩家的添加失败存入到日志表中
				log.setStatus(ManagerLog.error);
				log.setDsc(admin+"添加玩家"+name+"：由于正则验证"+ManagerLog.error);
				logMapper.logSave(log);
				
				//返回结果
				result.setStatus(3);
				result.setMsg("用户名长度9～64个字符，由字母、数字组成");
				return result;
			}else if(!pwd.matches(pwdReg)){
				//将玩家的添加失败存入到日志表中
				log.setStatus(ManagerLog.error);
				log.setDsc(admin+"添加玩家"+name+"：由于正则验证"+ManagerLog.error);
				logMapper.logSave(log);
				
				//返回结果
				result.setStatus(4);
				result.setMsg("密码为6-18个数字、字母组成");
				return result;
			}
			
			//连接登陆服务
			LoginServerRMI loginServerRMI = CacheLoginServer.getLoginServer();
			if (null == loginServerRMI) {
				//将玩家的添加失败存入到日志表中
				log.setStatus(ManagerLog.error);
				log.setDsc(admin+"添加玩家"+name+"由于登陆服务连接"+ManagerLog.error);
				logMapper.logSave(log);
				
				//返回结果
				result.setMsg("登录服务连接失败");
				result.setStatus(2);
				return result;
				//throw new LogicException(D.CODE_SERVER_EXCEPTION, "登录服务连接失败");
			}else{
				//登陆服务返回的结果集
				RMIResult result1 = (RMIResult) loginServerRMI.exce(LoginRMIConstant.GM_TOOL_USR_INSERT_BY_NAME_PWD,name,pwd);
				if(result1.getErrorCode() == D.CODE_SUC){
					//System.out.println(result1.getRowCount());
					//将玩家的添加成功存入到日志表中
					log.setStatus(ManagerLog.success);
					log.setDsc(admin+"添加玩家"+name+":"+ManagerLog.success);
					logMapper.logSave(log);
					
					//返回结果
					result.setStatus(0);
					result.setMsg("添加玩家成功");
					return result;
				}else{
					//失败的状态码
					System.out.println(result1.getErrorCode());
					
					//将玩家的添加失败存入到日志表中
					log.setStatus(ManagerLog.error);
					log.setDsc(admin+"添加玩家"+name+":"+ManagerLog.error);
					logMapper.logSave(log);
					
					//返回结果
					result.setStatus(1);
					result.setMsg("添加玩家失败");
					return result;
				}
			}
			
		}catch(Exception e){
			//将玩家的添加失败存入到日志表中
			log.setStatus(ManagerLog.error);
			log.setDsc(admin+"添加玩家"+name+":由于未知错误添加失败");
			logMapper.logSave(log);
			
			e.printStackTrace();
			result.setStatus(5);
			result.setMsg("未知错误");
		}
		return result;
	}
	
	//修改玩家名字和密码
	public Result alterPlayer(HttpServletRequest req, String olduser, String newuser, String newpwd)
			throws ServletException, IOException {
		Result result = new Result();  //创建返回结果
		ManagerLog log = new ManagerLog(); //创建日志
		String ip = GetData.getRemoteHost(req); //获取管理员ip
		String system = GetData.getSystem(req);  //获取管理员操作系统
		String admin = GetAdmin.getAdmin(req);  //获取当前管理员名
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
			log.setDsc(admin+Code.ALTER_PLAYER_OUTTIME);
			logMapper.logSave(log);
			
			//返回结果
			result.setMsg(Code.ALTER_PLAYER_OUTTIME);
			result.setStatus(-1);
			return result;
		}
		try{
			//连接登陆服务
			LoginServerRMI loginServerRMI = CacheLoginServer.getLoginServer();
			if (null == loginServerRMI) {
				//将玩家修改失败存入到日志表中
				log.setStatus(ManagerLog.error);
				log.setDsc(admin+"修改玩家"+olduser+"为"+newuser+":由于登陆服务连接"+ManagerLog.error);
				logMapper.logSave(log);
				
				//返回结果
				result.setMsg("登录服务连接失败");
				result.setStatus(1);
				return result;
				
			}else{
				//通过名字查询
				RMIResult result1 = (RMIResult) loginServerRMI.exce(LoginRMIConstant.GM_TOOL_USR_SELECT_BY_NAME,olduser);
				if(result1.getErrorCode() == D.CODE_SUC && result1.getResult() != null ){
					Usr user = (Usr)result1.getResult();  //获取玩家的uuid
					
					//将新名字和新密码加入到实体类中
					user.setPsd(newpwd);
					user.setUname(newuser);
					
					//传入玩家新名字新密码并保存
					RMIResult result2 = (RMIResult) loginServerRMI.exce(LoginRMIConstant.GM_TOOL_USR_UPDATE,user);
					if(result2.getErrorCode() == D.CODE_SUC){
						//将玩家的修改成功存入到日志表中
						log.setStatus(ManagerLog.success);
						log.setDsc(admin+"修改玩家"+olduser+newuser+":"+ManagerLog.success);
						logMapper.logSave(log);
						
						//返回结果
						result.setStatus(0);
						result.setMsg("修改玩家成功");
						return result;
					}else{
						//失败的状态码
						System.out.println(result1.getErrorCode());
						
						//将玩家的修改失败存入到日志表中
						log.setStatus(ManagerLog.error);
						log.setDsc(admin+"修改玩家"+olduser+":"+newuser+ManagerLog.error);
						logMapper.logSave(log);
						
						//返回结果
						result.setStatus(4);
						result.setMsg("修改玩家失败");
						return result;
					}
				}else if(result1.getErrorCode() == D.CODE_UPDATE_USR_NO_USR){  //由于玩家不存在修改失败
					//将玩家的修改失败存入到日志表中
					log.setStatus(ManagerLog.error);
					log.setDsc(admin+"修改玩家"+olduser+":由于玩家名不存在或传人的玩家名错误修改失败!");
					logMapper.logSave(log);
					
					//返回结果
					result.setMsg("玩家名不存在");
					result.setStatus(2);
					return result;
				}else{
					//将玩家的修改失败存入到日志表中
					log.setStatus(ManagerLog.error);
					log.setDsc(admin+"修改玩家"+olduser+":失败!");
					logMapper.logSave(log);
					
					result.setMsg("查询失败!");
					result.setStatus(3);
					return result;
				}
			}
			
		}catch(Exception e){
			e.printStackTrace();
			//将玩家的修改失败存入到日志表中
			log.setStatus(ManagerLog.error);
			log.setDsc(admin+"修改玩家"+olduser+":由于未知错误修改失败!");
			logMapper.logSave(log);
			
			//返回结果
			result.setMsg("未知错误");
			result.setStatus(5);
		}
		return result;
	}
	
	//查询玩家
	public Result selectPlayer(HttpServletRequest req, String name) throws ServletException, IOException {
		Result result = new Result();  //创建返回结果
		ManagerLog log = new ManagerLog();  //创建管理员日志
		String ip = GetData.getRemoteHost(req); //获取管理员ip
		String system = GetData.getSystem(req);  //获取管理员操作系统
		String admin = GetAdmin.getAdmin(req);  //获取当前管理员名
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
			log.setDsc(admin+Code.SELETE_PLAYER_OUTTIME);
			logMapper.logSave(log);
			
			//返回结果
			result.setMsg(Code.SELETE_PLAYER_OUTTIME);
			result.setStatus(-1);
			return result;
		}
		try{
			//连接登陆服务
			LoginServerRMI loginServerRMI = CacheLoginServer.getLoginServer();
			if (null == loginServerRMI) {
				//将玩家查询失败存入到日志表中
				log.setStatus(ManagerLog.error);
				log.setDsc(admin+"查询玩家"+name+"：由于登陆服务连接"+ManagerLog.error);
				logMapper.logSave(log);
				
				//返回结果
				result.setMsg("登录服务连接失败");
				result.setStatus(1);
				return result;
			}else{
				//通过名字查询,返回一个结果对象，对象中的属性result为uer对象
				RMIResult result1 = (RMIResult) loginServerRMI.exce(LoginRMIConstant.GM_TOOL_USR_SELECT_BY_NAME,name);
				if(result1.getErrorCode() == D.CODE_SUC && result1.getResult() != null ){ //判断查询是否成功
					Usr user = (Usr)result1.getResult();  //获得玩家信息
					
					//将玩家查询成功存入到日志表中
					log.setStatus(ManagerLog.success);
					log.setDsc(admin+"查询玩家"+name+"："+ManagerLog.success);
					logMapper.logSave(log);
					
					result.setStatus(0);
					result.setMsg("查询成功");
					result.setData(user);
					return result;
				}else if(result1.getErrorCode() == D.CODE_LOGIN_NO_USR){
					//将玩家查询失败存入到日志表中
					log.setStatus(ManagerLog.error);
					log.setDsc(admin+"查询玩家"+name+"：由于玩家不存在玩家信息查询"+ManagerLog.error);
					logMapper.logSave(log);
					
					//返回结果
					result.setMsg("没有此用玩家");
					result.setStatus(2);
					return result;
				}else{
					//将玩家查询失败存入到日志表中
					log.setStatus(ManagerLog.error);
					log.setDsc(admin+"查询玩家"+name+"：由于未获得玩家信息查询"+ManagerLog.error);
					logMapper.logSave(log);
					
					//返回结果
					result.setMsg("登陆服务未知错误查询失败");
					result.setStatus(3);
					return result;
				}
			}
		}catch(Exception e){
			//将玩家查询失败存入到日志表中
			log.setStatus(ManagerLog.error);
			log.setDsc(admin+"查询玩家"+name+"：由于未知错误查询失败!");
			logMapper.logSave(log);
			
			e.printStackTrace();
			result.setMsg("未知错误");
			result.setStatus(4);
		}
		return result;
	}
	
	//请求查询玩家参数页面
	public String showPlayer(HttpServletRequest req) throws ServletException, IOException {
		ManagerLog log = new ManagerLog();  //创建管理员日志
		String ip = GetData.getRemoteHost(req); //获取管理员ip
		String system = GetData.getSystem(req);  //获取管理员操作系统
		String admin = GetAdmin.getAdmin(req);  //获取当前管理员名
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
			log.setDsc(admin+Code.SELETE_PLAYERPAGE_OUTTIME);
			logMapper.logSave(log);
			
			//返回结果
			return "login/login";
		}
		//判断进入查询玩家查询页面的途径
		String name = req.getParameter("name");
		if(name == null){
			return "playerManagement/selectUser";
		}
		try{
			//连接登陆服务
			LoginServerRMI loginServerRMI = CacheLoginServer.getLoginServer();
			if (null == loginServerRMI) {
				//将玩家查询失败存入到日志表中
				log.setStatus(ManagerLog.error);
				log.setDsc(admin+"通过查询所有玩家连接查询单个玩家"+name+"：由于登陆服务连接"+ManagerLog.error);
				logMapper.logSave(log);
				
				//将失败信息保存到req中，返回给页面
				req.setAttribute("loginServiceError", "登陆服务连接失败");
				return "playerManagement/selectUser";
			}else{
				//通过名字查询,返回一个结果对象，对象中的属性result为uer对象
				RMIResult result1 = (RMIResult) loginServerRMI.exce(LoginRMIConstant.GM_TOOL_USR_SELECT_BY_NAME,name);
				
				if(result1.getErrorCode() == D.CODE_SUC && result1.getResult() != null ){ //判断查询是否成功
					Usr user = (Usr)result1.getResult();  //获得玩家信息
					
					//将玩家查询成功存入到日志表中
					log.setStatus(ManagerLog.success);
					log.setDsc(admin+"通过查询所有玩家连接查询单个玩家"+name+"："+ManagerLog.success);
					logMapper.logSave(log);
					
					//将查询出的玩家信息保存到req中
					req.setAttribute("success", user);
					return "playerManagement/selectUser";
				}else{
					//将玩家查询失败存入到日志表中
					log.setStatus(ManagerLog.error);
					log.setDsc(admin+"通过查询所有玩家连接查询单个玩家"+name+"：由于未获得玩家信息查询"+ManagerLog.error);
					logMapper.logSave(log);
					
					//将失败信息保存到req中，返回给页面
					req.setAttribute("error", "未从登陆服务获得数据");
					return "playerManagement/selectUser";
				}
			}
		}catch(Exception e){
			e.printStackTrace();
			//将玩家查询失败存入到日志表中
			log.setStatus(ManagerLog.error);
			log.setDsc(admin+"通过查询所有玩家连接查询单个玩家"+name+"：由于由于未知错误查询"+ManagerLog.error);
			logMapper.logSave(log);
			//将失败信息保存到req中，返回给页面
			req.setAttribute("exception", "未知错误");
		}
		return "playerManagement/selectUser";
	}
	
	//查询所有玩家
	public String toAllPlayer(HttpServletRequest req,String playerIndex) throws ServletException, IOException {
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
			log.setDsc(admin+Code.SELETEALL_PLAYER_OUTTIME);
			logMapper.logSave(log);
			
			//返回结果
			return "login/login";
		}
		try{
			int indexpage = Integer.valueOf(playerIndex);
			//判断index是否为第一次请求
			if(indexpage <0){
				indexpage = 1;
			}
			//连接登陆服务
			LoginServerRMI loginServerRMI = CacheLoginServer.getLoginServer();
			if (null == loginServerRMI) {
				//将玩家查询失败存入到日志表中
				log.setStatus(ManagerLog.error);
				log.setDsc(admin+"查询所有玩家"+"：由于登陆服务连接"+ManagerLog.error);
				logMapper.logSave(log);
				
				return "playerManagement/selectAllUser";
				//throw new LogicException(D.CODE_SERVER_EXCEPTION, "登录服务连接失败");
			}else{
				//返回所有玩家
				RMIResult result1 = (RMIResult) loginServerRMI.exce(LoginRMIConstant.GM_TOOL_USR_SELECT);
				
				//判断返回的数据是否成功
				if(result1.getErrorCode() == D.CODE_SUC && result1.getResult() != null ){ //判断查询是否成功
					@SuppressWarnings("unchecked")
					List<Usr> totalList = (List<Usr>)result1.getResult();  //获取所有玩家
					int pagesize = 20;  //定义每页显示的记录数
					//加入查询所有记录插件中
					GetData.selectAll(req, totalList, pagesize, indexpage,
							"playerCount", "playerIndex", "playerPagesize", "playerPagecount", "playerList");
					
					//将玩家查询成功存入到日志表中
					log.setStatus(ManagerLog.success);
					log.setDsc(admin+"查询所有玩家"+"："+ManagerLog.success);
					logMapper.logSave(log);
					return "playerManagement/selectAllUser";
				}else{
					//将玩家查询失败存入到日志表中
					log.setStatus(ManagerLog.error);
					log.setDsc(admin+"查询所有玩家"+"：由于未获得玩家信息查询失败!");
					logMapper.logSave(log);
					return "playerManagement/selectAllUser";
				}
			}
		}catch(Exception e){
			e.printStackTrace();
			//将玩家查询失败存入到日志表中
			log.setStatus(ManagerLog.error);
			log.setDsc(admin+"查询所有玩家"+"：由于未知异常查询失败!");
			logMapper.logSave(log);
		}
		return "playerManagement/selectAllUser";
	}
	
	
	
	//请求增加玩家页面
	public String toAddPlayer(HttpServletRequest req) throws ServletException, IOException {
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
			log.setDsc(Code.ADD_PLAYERPAGE_OUTTIME);
			logMapper.logSave(log);
			
			//返回结果
			return "login/login";
		}
		
		//成功
		log.setStatus(ManagerLog.error);
		log.setDsc(Code.ADD_SUC_PLAYERPAGE);
		logMapper.logSave(log);
		return "playerManagement/addUser";
	}
	
	//请求修改玩家名和密码页面
	public String toAlterPlayer(HttpServletRequest req) throws ServletException, IOException {
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
			log.setDsc(admin+Code.ALTER_PLAYERPAGE_OUTTIME);
			logMapper.logSave(log);
			
			//返回结果
			return "login/login";
		}
		
		//成功
		log.setStatus(ManagerLog.error);
		log.setDsc(admin+Code.ALTER_SUC_PLAYERPAGE);
		logMapper.logSave(log);
		return "playerManagement/alterUser";
	}
	
	//请求删除玩家页面
	public String toDeletePlayer(HttpServletRequest req) throws ServletException, IOException {
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
			log.setDsc(admin+Code.DELETE_PLAYERPAGE_OUTTIME);
			logMapper.logSave(log);
			
			//返回结果
			return "login/login";
		}
		
		//成功
		log.setStatus(ManagerLog.error);
		log.setDsc(admin+Code.DELETE_SUC_PLAYERPAGE);
		logMapper.logSave(log);
		return "playerManagement/deleteUser";
	}

	/**查询绑定玩家信息*/
	@SuppressWarnings("unchecked")
	public String toSelectBindedPlayer(HttpServletRequest req,String name) throws ServletException, IOException {
		String admin = GetAdmin.getAdmin(req);  			//获取当前管理员名
		GetData getData = (GetData) BeanFactory.getBean(GetData.class);
		List<PromoCode> list=null;
		
		//判断当前管理员是否超时
		if(admin == null){
			//保存日志
			getData.saveCurrentLog(req,"查询绑定玩家列表:当前操作者未操作超时!",ManagerLog.error);
			return "login/login";
		}
		
		try{
			String index = (String)req.getParameter("index");
			int indexpage = Integer.valueOf(index);
			if(indexpage < 0){  //判断index是否为第一次请求
				indexpage = 1;
			}
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
			for (int i = 0; i <list.size(); i++) {
				PromoCode pc=new PromoCode();
				Agent a1=agentMapper.findAgentByName(name);
				Map<String,Object> map = (Map<String, Object>) gameRMIServer.exec(RMIConstant.GM_TOOL_FIND_PLAYER,list.get(i).getGameId());
				log.warn(map);
				if(map == null){
					//保存日志
					getData.saveCurrentLog(req,"玩家不存在!", ManagerLog.error);
					//返回结果
					req.setAttribute("error","未获得服务器数据!");
					return "error/error";
				}
				pc.setpNickName((String) map.get("pNickName"));
				pc.setRoomCard(a1.getRoomCard());
				pc.setGameId(list.get(i).getGameId());
				pc.setPlayerRoomCard( (Long) map.get("roomCard"));
				promoCodeMapper.updateALL(pc);
			}
			list = promoCodeMapper.selectByAccount(name);
			
			int paycount=0;//所有玩家本月充值的总金额
			for (PromoCode promoCode : list) {
				Integer gameId = promoCode.getGameId();//获取玩家ID
				int payValue =promoCode1Mapper.selectNowMonthPay(gameId);//查找玩家当月总充值
				paycount=payValue+paycount;
				promoCode.setNowMonthPay(payValue);
			}
			req.setAttribute("paycount", paycount);
			System.out.println(list);
			if(list.size() != 0){
				int pagesize = 20;  //定义每页显示的记录数
				//加入查询所有记录方法中
				GetData.selectAll(req, list, pagesize, indexpage,
						"count", "index", "pagesize", "pagecount", "list");
				
				//将管理员查询成功存入到日志表中
				req.setAttribute("account", name);
				getData.saveCurrentLog(req, "查询绑定玩家列表:操作成功!", ManagerLog.success);
				return "playerManagement/selectBindedPlayers";
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
/**查询所有绑定玩家*//*
	@SuppressWarnings("unchecked")
	public String toSelectBindedPlayers(HttpServletRequest req) throws ServletException, IOException {
		String admin = GetAdmin.getAdmin(req);  			//获取当前管理员名
		GetData getData = (GetData) BeanFactory.getBean(GetData.class);
		List<PromoCode> list=null;
		
		//判断当前管理员是否超时
		if(admin == null){
			//保存日志
			getData.saveCurrentLog(req,"查询绑定玩家列表:当前操作者未操作超时!",ManagerLog.error);
			
			//返回结果
			
			return "login/login";
		}
		
		try{
			String index = (String)req.getParameter("index");
			int indexpage = Integer.valueOf(index);
			if(indexpage < 0){  //判断index是否为第一次请求
				indexpage = 1;
			}
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
			
			//判断请求是管理员还是代理商
			if(PowerManagment.checkOperCategory(admin) == 1){					//管理员
				list = promoCodeMapper.selectAllBindedPlayers();
				for (int i = 0; i <list.size(); i++) {
					PromoCode pc=new PromoCode();
					Agent a1=agentMapper.findAgentByName(list.get(i).getAccount());
					Map<String,Object> map = (Map<String, Object>) gameRMIServer.exec(RMIConstant.GM_TOOL_FIND_PLAYER,list.get(i).getGameId());
					log.warn(map);
					if(map == null){
						//保存日志
						getData.saveCurrentLog(req,"玩家不存在!", ManagerLog.error);
						//返回结果
						req.setAttribute("error","为获得服务器数据!");
						return "error/error";
					}
					pc.setpNickName((String) map.get("pNickName"));
					pc.setRoomCard(a1.getRoomCard());
					pc.setGameId(list.get(i).getGameId());
					pc.setPlayerRoomCard((Long) map.get("roomCard"));
					System.out.println(pc);
					promoCodeMapper.updateALL(pc);
				}
				list = promoCodeMapper.selectAllBindedPlayers();
			}else if(PowerManagment.checkOperCategory(admin) == 2){				//代理商
				list = promoCodeMapper.selectByAccount(admin);
				for (int i = 0; i <list.size(); i++) {
					PromoCode pc=new PromoCode();
					Agent a1=agentMapper.findAgentByName(admin);
					Map<String,Object> map = (Map<String, Object>) gameRMIServer.exec(RMIConstant.GM_TOOL_FIND_PLAYER,list.get(i).getGameId());
					
					if(map == null){
						//保存日志
						getData.saveCurrentLog(req,"玩家不存在!", ManagerLog.error);
						//返回结果
						req.setAttribute("error","为获得服务器数据!");
						return "error/error";
					}
					pc.setpNickName((String) map.get("pNickName"));
					pc.setRoomCard(a1.getRoomCard());
					pc.setGameId(list.get(i).getGameId());
					pc.setPlayerRoomCard((Long) map.get("roomCard"));
					System.out.println(pc);
					promoCodeMapper.updateALL(pc);
				}
				list = promoCodeMapper.selectByAccount(admin);
				int paycount=0;//所有玩家本月充值的总金额
				for (PromoCode promoCode : list) {
					Integer gameId = promoCode.getGameId();//获取玩家ID
					int payValue =promoCode1Mapper.selectNowMonthPay(gameId);//查找玩家当月总充值
					paycount=payValue+paycount;
					promoCode.setNowMonthPay(payValue);
				}
				req.setAttribute("paycount", paycount);
				
			}else{																//操作者不存在
				getData.saveCurrentLog(req,"查询绑定玩家列表:操作者不存在!",ManagerLog.error);
				
				return "login/login";
			}
			
			if(list.size() != 0){
				int pagesize = 20;  //定义每页显示的记录数
				//加入查询所有记录方法中
				GetData.selectAll(req, list, pagesize, indexpage,
						"count", "index", "pagesize", "pagecount", "list");
				
				//将管理员查询成功存入到日志表中
				getData.saveCurrentLog(req, "查询绑定玩家列表:操作成功!", ManagerLog.success);
				
				return "playerManagement/selectBindedAllPlayers";
			}else{
				//将管理员查询失败存入到日志表中
				getData.saveCurrentLog(req, "查询绑定玩家列表:操作者没有绑定的玩家!", ManagerLog.error);
				return "playerManagement/noData";
			}
			
		}catch(Exception e){
			e.printStackTrace();
			//记录异常日志
			getData.saveCurrentLog(req,"查询绑定玩家列表:未知异常!",ManagerLog.error);
		}
		return "playerManagement/noData";
	}*/

	/**查询所有绑定玩家*/
	@SuppressWarnings("unchecked")
	public String toSelectBindedPlayers(HttpServletRequest req) throws ServletException, IOException {
		String admin = GetAdmin.getAdmin(req);  			//获取当前管理员名
		GetData getData = (GetData) BeanFactory.getBean(GetData.class);
		List<PromoCode> list=null;
		
		//判断当前管理员是否超时
		if(admin == null){
			//保存日志
			getData.saveCurrentLog(req,"查询绑定玩家列表:当前操作者未操作超时!",ManagerLog.error);
			
			//返回结果
			
			return "login/login";
		}
		
		try{
			String index = (String)req.getParameter("index");
			int indexpage = Integer.valueOf(index);
			if(indexpage < 0){  //判断index是否为第一次请求
				indexpage = 1;
			}
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
			
			//判断请求是管理员还是代理商
			if(PowerManagment.checkOperCategory(admin) == 1){					//管理员
				list = promoCodeMapper.selectAllBindedPlayers();
				for (int i = 0; i <list.size(); i++) {
					PromoCode pc=new PromoCode();
					Agent a1=agentMapper.findAgentByName(list.get(i).getAccount());
					Map<String,Object> map = (Map<String, Object>) gameRMIServer.exec(RMIConstant.GM_TOOL_FIND_PLAYER,list.get(i).getGameId());
					
					if(map == null){
						log.warn(map);
						continue;
						//保存日志
						//getData.saveCurrentLog(req,"玩家不存在!", ManagerLog.error);
						//返回结果
						//req.setAttribute("error","为获得服务器数据!");
						// "error/error";
					}
					pc.setpNickName((String) map.get("pNickName"));
					pc.setRoomCard(a1.getRoomCard());
					pc.setGameId(list.get(i).getGameId());
					pc.setPlayerRoomCard((Long) map.get("roomCard"));
					System.out.println(pc);
					promoCodeMapper.updateALL(pc);
				}
				list = promoCodeMapper.selectAllBindedPlayers();
			}else if(PowerManagment.checkOperCategory(admin) == 2){				//代理商
				list = promoCodeMapper.selectByAccount(admin);
				for (int i = 0; i <list.size(); i++) {
					PromoCode pc=new PromoCode();
					Agent a1=agentMapper.findAgentByName(admin);
					Map<String,Object> map = (Map<String, Object>) gameRMIServer.exec(RMIConstant.GM_TOOL_FIND_PLAYER,list.get(i).getGameId());
					
					if(map == null){
						continue;
						//保存日志
						//getData.saveCurrentLog(req,"玩家不存在!", ManagerLog.error);
						//返回结果
						//req.setAttribute("error","为获得服务器数据!");
						//return "error/error";
					}
					pc.setpNickName((String) map.get("pNickName"));
					pc.setRoomCard(a1.getRoomCard());
					pc.setGameId(list.get(i).getGameId());
					pc.setPlayerRoomCard((Long) map.get("roomCard"));
					System.out.println(pc);
					promoCodeMapper.updateALL(pc);
				}
				list = promoCodeMapper.selectByAccount(admin);
				int paycount=0;//所有玩家本月充值的总金额
				for (PromoCode promoCode : list) {
					Integer gameId = promoCode.getGameId();//获取玩家ID
					int payValue =promoCode1Mapper.selectNowMonthPay(gameId);//查找玩家当月总充值
					paycount=payValue+paycount;
					promoCode.setNowMonthPay(payValue);
				}
				req.setAttribute("paycount", paycount);
				
			}else{																//操作者不存在
				getData.saveCurrentLog(req,"查询绑定玩家列表:操作者不存在!",ManagerLog.error);
				
				return "login/login";
			}
			
			if(list.size() != 0){
				int pagesize = 20;  //定义每页显示的记录数
				//加入查询所有记录方法中
				GetData.selectAll(req, list, pagesize, indexpage,
						"count", "index", "pagesize", "pagecount", "list");
				
				//将管理员查询成功存入到日志表中
				getData.saveCurrentLog(req, "查询绑定玩家列表:操作成功!", ManagerLog.success);
				
				return "playerManagement/selectBindedAllPlayers";
			}else{
				//将管理员查询失败存入到日志表中
				getData.saveCurrentLog(req, "查询绑定玩家列表:操作者没有绑定的玩家!", ManagerLog.error);
				return "playerManagement/noData";
			}
			
		}catch(Exception e){
			e.printStackTrace();
			//记录异常日志
			getData.saveCurrentLog(req,"查询绑定玩家列表:未知异常!",ManagerLog.error);
		}
		return "playerManagement/noData";
	}	
	
	
		/**
		 * 查询所有已认证的玩家信息
		 */
		@Override
		public String getAllRealNamePlayer(HttpServletRequest req,String playerIndex) throws ServletException, IOException {
			ManagerLog log = new ManagerLog(); //创建日志
			String admin = GetAdmin.getAdmin(req);  //获取当前管理员名
			String ip = GetData.getRemoteHost(req); //获取管理员ip
			String system = GetData.getSystem(req);  //获取管理员操作系统   目前乱码、、
			Timestamp time = GetData.getTime(); //获取当前系统时间
			GetData getData = (GetData) BeanFactory.getBean(GetData.class);
			
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
				log.setDsc(admin+Code.SELETEALL_PLAYER_OUTTIME);
				logMapper.logSave(log);
				//返回结果
				return "login/login";
			}
			try{
				int indexpage = Integer.valueOf(playerIndex);
				int pagesize  = 20;
				//查询所有的实名玩家  当状态为不同时，则相应查询出不同的状态 2 ==》 已批准
				List<Player> players = playerRealNameApplyMapper.getAllRealNameApply(2);
				//分页
				GetData.selectAll(req, players, pagesize, indexpage,
						"count", "index", "pagesize", "pagecount", "list");
					
				getData.saveCurrentLog(req, "查询所有认证玩家:操作成功!", ManagerLog.success);
				
				return "playerManagement/selectAllRealNamePlayers";
					
			}catch(Exception e){
				e.printStackTrace();
				//将玩家查询失败存入到日志表中
				log.setStatus(ManagerLog.error);
				log.setDsc(admin+"查询所有认证玩家"+"：由于未知异常查询失败!");
				logMapper.logSave(log);
			}
			logMapper.logSave(log);
			return "playerManagement/selectAllRealNamePlayers";
			
		}

		@Override
		public String getRealNameApply(HttpServletRequest req, String index) throws ServletException, IOException {
			return null;
		}

		/**
		 * 查询申请，批准记录
		 */
		@Override
		public String getApplyRecord(HttpServletRequest req, String index) throws ServletException, IOException {
			
			ManagerLog log = new ManagerLog();           //创建日志
			String admin = GetAdmin.getAdmin(req);      //获取当前管理员名
			String ip = GetData.getRemoteHost(req);    //获取管理员ip
			String system = GetData.getSystem(req);   //获取管理员操作系统   目前乱码
			Timestamp time = GetData.getTime();      //获取当前系统时间
			GetData getData = (GetData) BeanFactory.getBean(GetData.class);
			
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
				log.setDsc(admin+Code.SELETEALL_PLAYER_OUTTIME);
				logMapper.logSave(log);
				
				//返回结果
				return "login/login";
			}
			try{
				int indexpage = Integer.valueOf(index);
				Integer pagesize  = 20;
				
				List<Player> players = playerRealNameApplyMapper.getAllApplyRecords();
				
				//加入查询所有记录方法中
				GetData.selectAll(req, players, pagesize, indexpage,
						"count", "index", "pagesize", "pagecount", "list");
					
				getData.saveCurrentLog(req, "查询所有申请,批准记录:操作成功!", ManagerLog.success);
				
				return "playerManagement/selectAllApplyRecords";
				
			}catch(Exception e){
				//将失败查询实名记录存入到日志表中
				log.setStatus(ManagerLog.error);
				log.setDsc(admin+"查询所有实名申请记录"+"：由于未知异常查询失败!");
				logMapper.logSave(log);
			}
			logMapper.logSave(log);
			return "playerManagement/selectAllApplyRecords";
			
		}

		/**
		 *  存储实名申请
		 */
		@Override
		public Result insertRealNameApply(HttpServletRequest req,Player player) throws ServletException, IOException {
			
			ManagerLog log = new ManagerLog(); //创建日志
			String admin = GetAdmin.getAdmin(req);  //获取当前管理员名
			
			//校验信息   以下所有信息待定
			Result infos = null;
			
			//校验
			if(!GameUtil.isPhoneLegal(player.getRealNameInfo().getPhoneNumber())) {
				infos = new Result();
				infos.setMsg("电话号码有误，请重新输入");
				infos.setStatus(3);
				return infos;
			}
			if(!GameUtil.is18IdCard(player.getRealNameInfo().getIdCard())) {
				infos = new Result();
				infos.setMsg("身份证号码有误,请输入输入");
				infos.setStatus(4);
				return infos;
			}
			//检查名称中是否含有特殊字符
			if (GameUtil.isHaveSpecialChar(player.getRealNameInfo().getRealName())) {
				infos = new Result();
				infos.setMsg("名称中不能带有特殊字符");
				infos.setStatus(5);
				return infos;
			}
			if(!GameUtil.isName(player.getRealNameInfo().getRealName())) {
				infos = new Result();
				infos.setMsg("请输入1-6位数字、英文、汉字");
				infos.setStatus(6);
				return infos;
			};
			
			//如果验证通过，校验申请状态
			if (infos == null ) {
				//查询该玩家的申请状态
				Player playerNew = getSinglePlayer(Integer.valueOf(player.getGameId()));
				if(playerNew != null && playerNew.getRealNameInfo().getApplyState() != null) {
					//查询到该玩家的信息，只需更新
					switch (playerNew.getRealNameInfo().getApplyState()) {
					case 3: //已经拒绝过至少一次该玩家的实名申请  玩家可能改变昵称和手机号，
						    //更新申请时间,申请状态到数据库 
						
							Player players = new Player();
							
							RealNameInfo realNameInfo = new RealNameInfo();
							realNameInfo.setApplyTime(new Date());
							realNameInfo.setApplyState(1);
							realNameInfo.setPhoneNumber(player.getRealNameInfo().getPhoneNumber());
							realNameInfo.setRealName(player.getRealNameInfo().getRealName());
							player.setRealNameInfo(realNameInfo);
							playerRealNameApplyMapper.updateApplyState(player);
							
							infos = new Result();
							infos.setMsg(Code.STATE_OK_INFO);
							infos.setStatus(0);
							return infos;
					case 2: //已经批准该玩家的实名申请
						
							infos = new Result();
							infos.setMsg(Code.STATE_AGREE_INFO);
							infos.setStatus(1);
							return  infos;
					case 1: //该玩家的实名申请正在批准中
						
							infos = new Result();
							infos.setMsg(Code.STATE_REFUSE_INFO);
							infos.setStatus(2);
							return  infos;
					}
					
				}else {
							//没有查询到该玩家实名认证的信息 ，插入数据库
						try {
							Player insertPlayer = new Player();
							insertPlayer.setPName(player.getPName());
							insertPlayer.setGameId(player.getGameId());
							RealNameInfo realNameInfo = new RealNameInfo();
							realNameInfo.setRealName(player.getRealNameInfo().getRealName());
							realNameInfo.setPhoneNumber(player.getRealNameInfo().getPhoneNumber());
							realNameInfo.setIdCard(player.getRealNameInfo().getIdCard());
							realNameInfo.setApplyState(1);//没有查询到信息，说明此次为第一次实名申请，此次设置为正在申请中
							realNameInfo.setApplyTime(new Date());
							realNameInfo.setApproveTime(new Date());//批准时间默认和申请时间一致
							insertPlayer.setRealNameInfo(realNameInfo);
							
							playerRealNameApplyMapper.insertRealNameApply(insertPlayer);
							infos = new Result();
							infos.setMsg(Code.STATE_OK_INFO);
							infos.setStatus(0);
							return infos;
						} catch (Exception e) {
							//将玩家实名申请失败存入到日志表中
							log.setStatus(ManagerLog.error);
							log.setDsc(admin+"实名申请持久化异常"+"：未知异常查询失败!");
							logMapper.logSave(log);
							infos = new Result();
							infos.setMsg(Code.STATE_ERROR_INFO);
							infos.setStatus(4);
							
						}
				}
				
			}
			
			return infos;
		}
		/**
		 * 获得所有的实名申请记录
		 */
		@Override
		public String getAllRealNameApply(HttpServletRequest req, String index) throws ServletException, IOException {
		
		    ManagerLog log = new ManagerLog(); //创建日志
			String admin = GetAdmin.getAdmin(req);  //获取当前管理员名
			String ip = GetData.getRemoteHost(req); //获取管理员ip
			String system = GetData.getSystem(req);  //获取管理员操作系统   目前乱码、、
			Timestamp time = GetData.getTime(); //获取当前系统时间
			GetData getData = (GetData) BeanFactory.getBean(GetData.class);
			
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
				log.setDsc(admin+Code.SELETEALL_PLAYER_OUTTIME);
				logMapper.logSave(log);
				
				//返回结果
				return "login/login";
			}
			try{
				int indexpage = Integer.valueOf(index);
				Integer pagesize  = 20;
				
				List<Player> list = playerRealNameApplyMapper.getAllRealNameApply(1);
				
				//加入查询所有记录方法中
				GetData.selectAll(req, list, pagesize, indexpage,
						"count", "index", "pagesize", "pagecount", "list");
					
				getData.saveCurrentLog(req, "查询所有实名申请记录:操作成功!", ManagerLog.success);
				return "playerManagement/selectApplyRecords";
			
				
			}catch(Exception e){
				//将失败查询实名记录存入到日志表中
				log.setStatus(ManagerLog.error);
				log.setDsc(admin+"查询所有实名申请记录"+"：由于未知异常查询失败!");
				logMapper.logSave(log);
			}
			logMapper.logSave(log);
			return "playerManagement/selectApplyRecords";
		}

		@Override
		public Player getSinglePlayer(Integer gameId) {
			return playerRealNameApplyMapper.getSinglePlayer(gameId);
		}

		@Override
		public Result updateApplyState(HttpServletRequest req, Integer gameId,String state) throws ServletException, IOException {
			
			String admin = GetAdmin.getAdmin(req);  //获取当前管理员名
			GetData getData = (GetData) BeanFactory.getBean(GetData.class);
			
			//认证信息
			Result applyInfo = null;
			
			if (gameId != null) {
				//查询该玩家认证状态
				Player player = playerRealNameApplyMapper.getSinglePlayer(gameId);
					if (player != null && player.getRealNameInfo().getApplyState() != Integer.valueOf(state)) {
						RealNameInfo realNameInfo = new RealNameInfo();
						realNameInfo.setApplyState(Integer.valueOf(state));
						realNameInfo.setApproveTime(new Date());
						realNameInfo.setApplyTime(player.getRealNameInfo().getApplyTime());
						realNameInfo.setPhoneNumber(player.getRealNameInfo().getPhoneNumber());
						realNameInfo.setRealName(player.getRealNameInfo().getRealName());
						
						player.setRealNameInfo(realNameInfo);
						//更新状态
						playerRealNameApplyMapper.updateApplyState(player);
						applyInfo = new Result();
						applyInfo.setMsg(Code.UPDATE_APPLYINFO_SUCCESS);
						applyInfo.setStatus(2);
					}
				
					Player playernew = playerRealNameApplyMapper.getSinglePlayer(gameId);
					if (playernew.getRealNameInfo().getApplyState() == 2) { //批准实名申请，奖励钻石
						
						/*****************************************************？*/
						//向服务器发送钻石奖励信息
							
							try{
								//获取游戏服连接
								GameRMIServer gameRMIServer = CacheGameServer.getGameServer();
								if(gameRMIServer == null){
									//保存日志
									getData.saveCurrentLog(req, Code.ALERT_PLAYER_ROOMCARD_START_NOT_SERVER, ManagerLog.error);
									applyInfo = new Result();
									applyInfo.setMsg(Code.RMISERVER_ERROR_INFO);
									applyInfo.setStatus(0);
									return applyInfo;
								}
								
								//判断操作者时管理员还是代理商
								Manager mg = mgMapper.findByName(admin);
								Agent agent = agentMapper.findAgentByName(admin);
								
								if(mg != null && agent == null){  //管理员
									@SuppressWarnings("unchecked") //奖励3个钻石
									Integer m = (Integer)gameRMIServer.exec(RMIConstant.GM_TOOL_CHARGE_TREASURE,player.getGameId(),3);
									
									if(m == 0){
										//成功
										getData.saveCurrentLog(req, Code.ALERT_PLAYER_ROOMCARD_START_SUC, ManagerLog.success);
										getData.saveCurrentLog(req, "更新玩家分享次数成功", ManagerLog.success);
										applyInfo = new Result();
										applyInfo.setMsg(Code.UPDATE_SHARENUMBER_SUCCESS);
										applyInfo.setStatus(0);
										return applyInfo;
									}else {
										//失败
										getData.saveCurrentLog(req, Code.ALERT_PLAYERP_ROOMCARD_START_NOT_SERVER_DATA, ManagerLog.success);
										applyInfo = new Result();
										applyInfo.setMsg(Code.RMISERVER_ERROR_INFO);
										applyInfo.setStatus(3);
										return applyInfo;
									}
								}else{
									//记录失败
									getData.saveCurrentLog(req, Code.ALERT_PLAYERP_ROOMCARD_START_NOT_POWER, ManagerLog.error);
									
								}
							}catch(Exception e){
								//记录失败日志
								getData.saveCurrentLog(req, Code.ALERT_PLAYER_ROOMCARD_START_UNKNOWN, ManagerLog.error);
								//返回结果
								applyInfo = new Result();
								applyInfo.setMsg(Code.RMISERVER_ERROR_INFO);
								applyInfo.setStatus(4);
								return applyInfo;
							}
					}
				
				    return applyInfo;
			}
			
			applyInfo = new Result();
			applyInfo.setMsg(Code.UPDATE_APPLYINFO_ERROR);
			applyInfo.setStatus(5);
			return applyInfo;
		}

		@Override
		public Result updateTreasure(HttpServletRequest req, Integer gameId) throws ServletException, IOException {
			
			String admin = GetAdmin.getAdmin(req);  //获取当前管理员名
			GetData getData = (GetData) BeanFactory.getBean(GetData.class);
			
			//认证信息
			Result changeInfo = null;
			
			if (gameId != null) {
				
				try{
					//获取游戏服连接
					GameRMIServer gameRMIServer = CacheGameServer.getGameServer();
					if(gameRMIServer == null){
						//保存日志
						getData.saveCurrentLog(req, Code.ALERT_PLAYER_ROOMCARD_START_NOT_SERVER, ManagerLog.error);
						changeInfo = new Result();
						changeInfo.setMsg(Code.RMISERVER_ERROR_INFO);
						changeInfo.setStatus(2);
						return changeInfo;
					}
					
						//查询该玩家分享的次数
						Share share=  playerShareInfoMapper.selectShare(gameId);
						
						if (share == null) { //玩家第一次分享 
								Share shareNew = new Share();
								shareNew.setGameId(gameId);
								shareNew.setRewardNumber(1);
								shareNew.setNumber(3);
								shareNew.setShareType(1);
								shareNew.setNumberType(2);
								shareNew.setReceiveCD(10L);
								shareNew.setShareNumber(0);
								shareNew.setShareTime(new Date());
								Integer id = playerShareInfoMapper.insertShare(shareNew);
								if (id == null) {
									log.warn("插入失败");
									changeInfo = new Result();
									changeInfo.setMsg(Code.UPDATE_APPLYINFO_ERROR);
									changeInfo.setStatus(3);
									return changeInfo;
								}else {
									
									//查询该玩家分享的次数
									Share shareNum=  playerShareInfoMapper.selectShare(gameId);
									if (shareNum != null) {
										@SuppressWarnings("unchecked") //奖励3个钻石
										Integer m = (Integer)gameRMIServer.exec(RMIConstant.GM_TOOL_CHARGE_TREASURE,gameId,shareNum.getNumber());
										if(m == 0){
											//成功
											getData.saveCurrentLog(req, "更新玩家分享次数成功", ManagerLog.success);
											changeInfo = new Result();
											changeInfo.setMsg(Code.UPDATE_SHARENUMBER_SUCCESS);
											changeInfo.setStatus(0);
											return changeInfo;
										}else {
											//失败
											getData.saveCurrentLog(req, Code.ALERT_PLAYERP_ROOMCARD_START_NOT_SERVER_DATA, ManagerLog.success);
											changeInfo = new Result();
											changeInfo.setMsg(Code.RMISERVER_ERROR_INFO);
											changeInfo.setStatus(3);
											return changeInfo;
										}
									}
									
								}
						}
						if (share != null) {
							//更新该用户的分享次数 不奖励
							Share shareNum = new Share();
							shareNum.setGameId(gameId);
							shareNum.setShareNumber(share.getShareNumber() + 1);
							shareNum.setShareTime(new Date());
							playerShareInfoMapper.updateNumber(shareNum);
							getData.saveCurrentLog(req, "更新玩家分享次数成功", ManagerLog.success);
							changeInfo = new Result();
							changeInfo.setMsg(Code.UPDATE_SHARENUMBER_SUCCESS);
							changeInfo.setStatus(0);
							return changeInfo;
						}
						
				}catch(Exception e){
					//记录失败日志
					getData.saveCurrentLog(req, Code.ALERT_PLAYER_ROOMCARD_START_UNKNOWN, ManagerLog.error);
					//返回结果
					changeInfo = new Result();
					changeInfo.setMsg(Code.RMISERVER_ERROR_INFO);
					changeInfo.setStatus(4);
					return changeInfo;
				}
				
			}
			//失败
			getData.saveCurrentLog(req, Code.UPDATE_ERROR, ManagerLog.error);
			changeInfo = new Result();
			changeInfo.setMsg(Code.RMISERVER_ERROR_INFO);
			changeInfo.setStatus(2);
			return changeInfo;
			
		}

		@Override
		public String getAllBindPlayers(HttpServletRequest req,String account,String index) throws ServletException, IOException {
			GetData getData = (GetData) BeanFactory.getBean(GetData.class);
			List<PromoCode> list=null;
			
			try{
				int indexpage = Integer.valueOf(index);
				if(indexpage < 0){  //判断index是否为第一次请求
					indexpage = 1;
				}
				//查询所有绑定状态
				list = promoCodeMapper.selectByAccountAndBindState(account);
				
				if(list.size() != 0){
					int pagesize = 20;  //定义每页显示的记录数
					//加入查询所有记录方法中
					GetData.selectAll(req, list, pagesize, indexpage,
							"count", "index", "pagesize", "pagecount", "list");
					
					//将管理员查询成功存入到日志表中
					req.setAttribute("account", account);
					getData.saveCurrentLog(req, "查询绑定玩家列表:操作成功!", ManagerLog.success);
					return "playerManagement/selectUnBindPlayers";
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

		/**
		 * 执行解绑操作
		 */
		@Override
		public Result unbindPlayer(HttpServletRequest req, String account, String gameId)
				throws ServletException, IOException {
			
			GetData getData = (GetData) BeanFactory.getBean(GetData.class);
			
			Result result = null;
			
			if (gameId != null && account != null) {
				
				try {
					PromoCode promoCode = new PromoCode();
					promoCode.setAccount(account);
					promoCode.setGameId(Integer.valueOf(gameId));
					promoCode.setYb(1);
					promoCodeMapper.unbindPlayer(promoCode);//更新用户绑定状态
					
					result = new Result();
					result.setStatus(1);
					result.setMsg("解绑成功");
					return result;
					
				}catch(Exception e){
					//记录失败日志
					getData.saveCurrentLog(req, Code.STATE_ERROR_INFO, ManagerLog.error);
					//返回结果
					result = new Result();
					result.setStatus(2);
					result.setMsg("解绑失败");
					return result;
				}
				
			}
			//失败
			getData.saveCurrentLog(req, Code.STATE_ERROR_INFO, ManagerLog.error);
			result = new Result();
			result.setStatus(2);
			result.setMsg("解绑失败");
			return result;
		}
			
}

