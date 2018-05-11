package com.game.business.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.game.base.util.Code;
import com.game.base.util.Result;
import com.game.base.util.StringUtil;
import com.game.business.model.player.RealNameInfo;
import com.game.business.service.GamePlayerService;
import com.game.game.model.Player;

/**
 * <li>@ClassName: 游戏玩家控制层
 * <li>@author 周强
 * <li>@date 2016年11月7日
 */
@Controller
@RequestMapping("/gamePlayer")
public class PlayerController {
	//Log log = LogFactory.getLog(PlayerController.class);
	@Resource
	private GamePlayerService gamePlayerService;

	/** 请求通过游戏id查询玩家信息页面 */
	@RequestMapping("/toSelectPlayer.do")
	public String toSelectPlayer(HttpServletRequest req) throws ServletException, IOException {
		return gamePlayerService.toSelectPlayer(req);
	}

	/** 通过游戏id查询玩家信息 */
	@RequestMapping("/selectPlayerById.do")
	@ResponseBody
	public Result selectPlayerById(HttpServletRequest req, Integer gameId) throws ServletException, IOException {
		// System.out.println("gameId:"+gameId);
		return gamePlayerService.selectPlayerById(req, gameId);
	}

	/** 给玩家添加房卡页面 */
	@RequestMapping("/toAddRoomCard.do")
	public String addRoomCard(HttpServletRequest req, String pNiceName, Integer gameId, Integer roomCard) throws ServletException, IOException {
		return gamePlayerService.toAddRoomCard(req, pNiceName, gameId, roomCard);
	}

	/** 给玩家添加房卡 */
	@RequestMapping("/addRoomCard.do")
	@ResponseBody
	public Result addRoomCard(HttpServletRequest req, Integer gameId, Integer addRoomCard) throws ServletException, IOException {
		return gamePlayerService.addRoomCard(req, gameId, addRoomCard);
	}

	/** 请求查询玩家多个交易记录页面 */
	@RequestMapping("/toSelectRecodByCondition.do")
	public String toSelectRecodByCondition(HttpServletRequest req, Integer index) throws ServletException, IOException {
		return gamePlayerService.toSelectRecodByCondition(req, index);
	}

	/** 请求查询玩家注册时房卡数的页面 */
	@RequestMapping("/toSelectPlayerStartRoomCard.do")
	public String toSelectPlayerStartRoomCard(HttpServletRequest req) throws ServletException, IOException {
		return gamePlayerService.toSelectPlayerStartRoomCard(req);
	}

	/** 修改玩家注册时房卡数 */
	@RequestMapping("/alertPlayerStartRoomCard.do")
	@ResponseBody
	public Result alertPlayerStartRoomCard(HttpServletRequest req, String roomCardNumber) throws ServletException, IOException {
		return gamePlayerService.alertPlayerStartRoomCard(req, roomCardNumber);
	}
	
	/** 修改玩家绑定时赠送的房卡 */
	@RequestMapping("/updateRoomCardBin.do")
	@ResponseBody
	public Result updateRoomCardBin(HttpServletRequest req, Integer roomCardBing) throws ServletException, IOException {
		return gamePlayerService.updateRoomCardBin(req, roomCardBing);
	}

	/** 查询所有玩家 */
	@RequestMapping("/selectAllPlayer.do")
	public String selectAllPlayer(HttpServletRequest req, Integer index) throws ServletException, IOException {
		return gamePlayerService.selectAllPlayer(req, index);
	}

	// 请求增加玩家页面
	@RequestMapping("/toAddPlayer.do")
	public String toAddPlayer(HttpServletRequest req) throws ServletException, IOException {
		return gamePlayerService.toAddPlayer(req);
	}

	// 增加玩家
	@RequestMapping("/addPlayer.do")
	@ResponseBody
	public Result addPlayer(HttpServletRequest req, String name, String pwd, String system) throws ServletException, IOException {
		return gamePlayerService.addPlayer(req, name, pwd, system);
	}

	// 请求修改玩家页面
	@RequestMapping("/toAlterPlayer.do")
	public String toAlterPlayer(HttpServletRequest req) throws ServletException, IOException {
		return gamePlayerService.toAlterPlayer(req);
	}

	// 修改玩家
	@RequestMapping("/alterPlayer.do")
	@ResponseBody
	public Result alterPlayer(HttpServletRequest req, String olduser, String newuser, String newpwd) throws ServletException, IOException {
		return gamePlayerService.alterPlayer(req, olduser, newuser, newpwd);
	}

	// 请求删除玩家页面
	@RequestMapping("/toDeletePlayer.do")
	public String toDeletePlayer(HttpServletRequest req) throws ServletException, IOException {
		return gamePlayerService.toDeletePlayer(req);
	}

	// //请求查询玩家页面
	// @RequestMapping("/toSelectPlayer.do")
	// public String toSelectPlayer(HttpServletRequest req)throws
	// ServletException, IOException{
	// return gamePlayerService.showPlayer(req);
	// }
	// 请求查询玩家
	@RequestMapping("/selectPlayer.do")
	@ResponseBody
	public Result selectPlayer(HttpServletRequest req, String name) throws ServletException, IOException {
		return gamePlayerService.selectPlayer(req, name);
	}

	// 请求所有玩家
	@RequestMapping("/toAllPlayer.do")
	public String toAllPlayer(HttpServletRequest req) throws ServletException, IOException {
		HttpSession session = req.getSession(); // 获取session
		if (session.getAttribute("admin") == null) { // 验证session是否超时和需要登陆之后才能进入管理中
			return "login/login";
		}
		String playerIndex = (String) req.getParameter("playerIndex");
		return gamePlayerService.toAllPlayer(req, playerIndex);
	}

	/** 查询玩家绑定信息页面 */
	@RequestMapping("/toSelectBindedPlayer.do")
	public String toSelectBindedPlayer(HttpServletRequest req,String name) throws ServletException, IOException {
		return gamePlayerService.toSelectBindedPlayer(req,name);
	}
	@RequestMapping("/toSelectBindedPlayers.do")
	public String toSelectBindedPlayers(HttpServletRequest req) throws ServletException, IOException {
		return gamePlayerService.toSelectBindedPlayers(req);
	}
	
	/**
	 * 获得实名认证的相关菜单列表
	 * @return
	 */
	@RequestMapping(value = "geteRealNameMenuList.do" ,method = RequestMethod.GET)
	public String getRealNameMenuList() {
		return "mgCenterManagement/realNameMenu";
	}
	/**
	 * 获得所有的实名认证过的玩家
	 * @return
	 * @throws IOException 
	 * @throws ServletException 
	 */
	@RequestMapping(value = "getAllRealNamePlayer.do" ,method = RequestMethod.GET)
	public String getAllRealNamePlayer(HttpServletRequest req ,String index) throws ServletException, IOException{
		
		String pageIndex = "1";
		if (index != null) {
			pageIndex = index;
		}
		return gamePlayerService.getAllRealNamePlayer(req, pageIndex);
	}
	
	/**
	 * 获得所有的实名认证申请
	 * @param req
	 * @param index
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping(value = "realNameApply.do" ,method = RequestMethod.GET)
	public String getRealNameApply(HttpServletRequest req, String index) throws ServletException,IOException {
		
		String pageIndex = "1";
		if (index != null) {
			pageIndex = index;
		}
		return gamePlayerService.getAllRealNameApply(req,pageIndex);
	}
	/**
	 * 发送实名申请
	 * @param req
	 * @param realName      真实姓名
	 * @param idCard		身份证号码
	 * @param phoneNumber	电话号码	
	 * @param pname			昵称
	 * @param gameId		游戏id
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping("insertRealNameApplyInfo.do")
	@ResponseBody
	public Result insertRealNameApplyInfo(HttpServletRequest req,String realName,String idCard, String phoneNumber,String pname,String gameId) throws ServletException, IOException {
		    
			Player player = null;
		   Result infos = null;
			if (StringUtil.isEmpty(realName) && StringUtil.isEmpty(idCard) && StringUtil.isEmpty(phoneNumber) && StringUtil.isEmpty(pname) && StringUtil.isEmpty(gameId)) {
				player = new Player();
				player.setPName(pname);
				player.setGameId(Integer.valueOf(gameId));
				RealNameInfo realNameInfo = new RealNameInfo();
				realNameInfo.setIdCard(idCard);
				realNameInfo.setPhoneNumber(phoneNumber);
				realNameInfo.setRealName(realName);
				player.setRealNameInfo(realNameInfo);
				return gamePlayerService.insertRealNameApply(req, player);
			}
		
			infos = new Result();
			infos.setMsg(Code.STATE_ERROR_INFO);
			infos.setStatus(3);
			return infos;
	}
	
	/**
	 *  更新实名申请状态
	 * @param req
	 * @param gameId 游戏id
	 * @param state  需要更新为该状态
	 * @return
	 * @throws IOException 
	 * @throws ServletException 
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value ="updateApplyState.do" , method = RequestMethod.POST)
	@ResponseBody
	public Result updateApplyState(HttpServletRequest req,Integer gameId,String state) throws ServletException, IOException {
		
		if (gameId != null && StringUtil.isEmpty(state)) {
			return gamePlayerService.updateApplyState(req,gameId,state);
		}
		
		Result result = new Result();
		result.setMsg(Code.STATE_ERROR_INFO);
		result.setStatus(3);
		
		return result;
		
	}
	
	
	/**
	 *  申请记录：玩家申请和批准的记录
	 * @param req
	 * @param index
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping(value = "applyRecord.do" ,method = RequestMethod.GET)
	public String getApplyRecord(HttpServletRequest req, String index) throws ServletException,IOException {
			
		String pageIndex = "1";
			if (index != null) {
				pageIndex = index;
			}
		
		return gamePlayerService.getApplyRecord(req,pageIndex) ;
	}
	
	/**
	 * 分享获得奖励
	 * @param req
	 * @param gameId 游戏id
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping("updateTreasure.do")
	@ResponseBody
	public Result updateTreasure(HttpServletRequest req,String gameId) throws ServletException, IOException{
		return gamePlayerService.updateTreasure(req,Integer.valueOf(gameId));
	}
	/**
	 * 获得所有代理商所绑定的玩家
	 * @param req
	 * @param account 代理商账户
	 * @return
	 * @throws IOException 
	 * @throws ServletException 
	 */
	@RequestMapping(value = "getAllBindPlayers.do")
	public String getAllBindPlayers(HttpServletRequest req,String account,String index) throws ServletException, IOException {
		
		return gamePlayerService.getAllBindPlayers(req,account,index);
	}

	/**
	 * 代理商进行解绑操作
	 * @param req
	 * @param account
	 * @param gameId
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping(value = "unbindPlayer.do")
	@ResponseBody
	public Result unbindPlayer(HttpServletRequest req,String account,String gameId) throws ServletException, IOException {
		
		return gamePlayerService.unbindPlayer(req,account,gameId);
	}
	
}
