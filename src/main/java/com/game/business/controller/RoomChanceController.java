package com.game.business.controller;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.game.base.util.Result;
import com.game.business.service.RoomChanceService;

/**
 * <li>@ClassName: 房间概率管理控制层
 * <li>@author 周强
 * <li>@date 2016年11月21日
 */
@Controller()
@RequestMapping("/roomChance")
public class RoomChanceController {
//	Log log = LogFactory.getLog(PlayerController.class);
//	@Resource
//	private RoomChanceService roomChanceService;
//	
//	//查询所有房间概率
//	@RequestMapping("selectAllRoomChance.do")
//	public String selectAllRoomChance(HttpServletRequest req)throws ServletException, IOException{
//		//return deskChanceService.selectAllRoomChance(req);
//		return null;
//	}
//	//请求修改房间概率页面
//	@RequestMapping("toAlterRoomChance.do")
//	public String toAlterRoomChance(HttpServletRequest req)throws ServletException, IOException{
//		//roomChanceService.toAlterRoomChance(req);
//		return null;
//	}
//	//请求修改桌子概率
//	@RequestMapping("alterRoomChance.do")
//	@ResponseBody
//	public Result alterRoomChance(HttpServletRequest req,int id,int K5,int RS,int SF,int K4,int FH,int FL,int ST,int K3,int P2,int P1,int NP,int top)
//			throws ServletException, IOException{
//		//return roomChanceService.alterRoomChance(req, id, K5, RS, SF, K4, FH, FL, ST, K3, P2, P1, NP, top);
//		return null;
//	}
}
