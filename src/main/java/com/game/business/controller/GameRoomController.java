package com.game.business.controller;


import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.game.base.util.Result;
import com.game.business.service.GameRoomService;
import com.game.game.model.Room;

/**
 * 
 * <li>@ClassName: 游戏房间控制层
 * <li>@author 周强
 * <li>@date 2016年11月7日
 *
 */
@Controller
@RequestMapping("/gameRoom")
public class GameRoomController {
//	Log log = LogFactory.getLog(GameRoomController.class);
//	@Resource
//	private GameRoomService gameRoomService;
//	
//	//请求增加游戏房间页面
//	@RequestMapping("/toAddGameRoom.do")
//	public String toAddGameRoom(HttpServletRequest req)throws ServletException, IOException{
//		return gameRoomService.toAddGameRoom(req);
//	}
//	//增加游戏房间
//	@RequestMapping("/addGameRoom.do")
//	@ResponseBody
//	public Result addGameRoom(HttpServletRequest req,int gameId,int vipLimit,int goldLimit,int level,String name,
//			int peopleNumLimit,int creditRate,int betBaseScore,int betMinScore,int betMaxScore )
//			throws ServletException, IOException{
//		//将参数传入到room中
//		Room room = new Room();
//		room.setBetBaseScore(betBaseScore);
//		room.setBetMaxScore(betMaxScore);
//		room.setBetMinScore(betMinScore);
//		room.setCreditRate(creditRate);
//		room.setGameId(gameId);
//		room.setGoldLimit(goldLimit);
//		room.setName(name);
//		room.setPeopleNumLimit(peopleNumLimit);
//		room.setVipLimit(vipLimit);
//		//System.out.println("chenggong");
//		//System.out.println(room);
//		
//		return gameRoomService.addGameRoom(req, room);
//	}
//	//通过查询所有房间链接请求修改游戏房间页面
//	@RequestMapping("/toAlterGameRoom.do")
//	public String toAlterGameRoom(HttpServletRequest req)throws ServletException, IOException{
//		return gameRoomService.toAlterGameRoom(req);
//	}
//	//修改游戏房间
//	@RequestMapping("/alterGameRoom.do")
//	@ResponseBody
//	public Result alterGameRoom(HttpServletRequest req,int vipLimit,int goldLimit,int level,String name,
//			int peopleNumLimit,int creditRate,int betBaseScore,int betMinScore,int betMaxScore)
//			throws ServletException, IOException{
//		System.out.println("chenggong");
//		//将参数传入到room中
//		Room room = new Room();
//		room.setBetBaseScore(betBaseScore);
//		room.setBetMaxScore(betMaxScore);
//		room.setBetMinScore(betMinScore);
//		room.setCreditRate(creditRate);
//		//room.setGameId(gameId);
//		room.setGoldLimit(goldLimit);
//		room.setName(name);
//		room.setPeopleNumLimit(peopleNumLimit);
//		room.setVipLimit(vipLimit);
//		System.out.println("chenggong");
//		System.out.println(room);
//		return null;
//		//return gameRoomService.alterGameRoom(req,room,oldName);
//	}
//	
//	
//	
//	
//	
//	//请求查询房间信息网页
//	@RequestMapping("/toSelectGameRoom.do")
//	public String toSelectRoom(HttpServletRequest req)throws ServletException, IOException{
//		HttpSession session = req.getSession(); //获取session
//		if(session.getAttribute("admin") == null){  //验证session是否超时和需要登陆之后才能进入管理中
//			return "login/login";
//		}
//		return "gameRoomManagement/selectGameRoom";
//	}
//	//请求查询房间信息
//	@RequestMapping("/selectGameRoom.do")
//	@ResponseBody
//	public Result selectRoom(HttpServletRequest req,int gameId,String oldName)throws ServletException, IOException{
//		System.out.println("asdjljojj");
//		System.out.println(gameId);
//		System.out.println(oldName);
//		return null;
//		//return gameRoomService.selectRoom(req, gameId, oldName);
//	}
//	
//	//请求显示所有房间信息
//	@RequestMapping("/toSelectAllGameRoom.do")
//	public String toSelectAllRoom(HttpServletRequest req)throws ServletException, IOException{
//		return gameRoomService.toSelectAllRoom(req);
//	}
	
}
