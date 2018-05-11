package com.game.business.controller;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.game.base.util.Result;
import com.game.business.service.GameDeskService;

/**
 * 
 * <li>@ClassName: 游戏桌子控制层
 * <li>@author 周强
 * <li>@date 2016年11月7日
 *
 */
@Controller
@RequestMapping("/gameDesk")
public class GameDeskController {
//	Log log = LogFactory.getLog(GameDeskController.class);
//	@Resource
//	private GameDeskService gameDeskService;
//	
//	//请求增加游戏桌子页面
//	@RequestMapping("/toAddDesk.do")
//	public String toAddDesk(HttpServletRequest req)throws ServletException, IOException{
//		HttpSession session = req.getSession(); //获取session
//		if(session.getAttribute("admin") == null){  //验证session是否超时和需要登陆之后才能进入管理中
//			return "login/login";
//		}
//		return gameDeskService.toAddDesk(req);
//	}
//	//通过游戏id获取房间名
//	@RequestMapping("/getRoom.do")
//	@ResponseBody
//	public Result getRoom(HttpServletRequest req,String gameId)throws ServletException, IOException{
//		return gameDeskService.getRoom(req, gameId);
//	}
//	//添加桌子
//	@RequestMapping("/addGameDesk.do")
//	@ResponseBody
//	public Result addRoom(HttpServletRequest req,String gameId,String roomId,String maxGoldStock,String curGoldStock)
//			throws ServletException, IOException{
////		System.out.println("chenggong");
////		System.out.println(gameId);
////		System.out.println(roomId);
////		System.out.println(maxGoldStock);
////		System.out.println(gameId);
//		return gameDeskService.addRoom(req, gameId, roomId, maxGoldStock, curGoldStock);
//	}
//	//请求修改游戏桌子参数页面
//	@RequestMapping("/toAlterDesk.do")
//	public String toAlterDesk(HttpServletRequest req)throws ServletException, IOException{
//		return gameDeskService.toAlterDesk(req);
//	}
//	//修改桌子参数
//	@RequestMapping("/alterDesk.do")
//	@ResponseBody
//	public Result alterDesk(HttpServletRequest req,int gameid,int roomid,int id,int maxGoldStock,int curGoldStock)
//			throws ServletException, IOException{
//		System.out.println("chengong");
//		return gameDeskService.alterDesk(req, gameid, roomid, id, maxGoldStock, curGoldStock);
//		//return null;
//	}
//	//请求查询游戏桌子参数页面
//	@RequestMapping("/toSelectDesk.do")
//	public String toSelectDesk(HttpServletRequest req)throws ServletException, IOException{
//		//System.out.println("进来");
//		return gameDeskService.toSelectDesk(req);
//	}
//	//请求查询游戏桌子参数
//	@RequestMapping("/selectDesk.do")
//	@ResponseBody
//	public Result selectDesk(HttpServletRequest req,String gameId,String roomId,String id)
//			throws ServletException, IOException{
//		System.out.println("chenggong");
//		System.out.println(gameId);
//		System.out.println(roomId);
//		System.out.println(id);
//		return null;
//		//return gameDeskService.selectDesk(req, gameId, roomId, id);
//	}
//	//请求查询所有桌子参数页面
//	@RequestMapping("/selectAllDesk.do")
//	public String selectAllDesk(HttpServletRequest req)throws ServletException, IOException{
//		return gameDeskService.selectAllDesk(req);
//	}
}











