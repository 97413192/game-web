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
import com.game.business.service.ActionWardService;
import com.game.business.service.LandingWardService;

/**
 * <li>@ClassName: 活动奖励管理控制层
 * <li>@author 周强
 * <li>@date 2016年11月17日
 */
@Controller
@RequestMapping("/actionWard")
public class ActionWardController {
	Log log = LogFactory.getLog(PlayerController.class);
	@Resource
	private ActionWardService actionWardService;
	
//	//查询所有活动奖励
//	@RequestMapping("/selectAllActionWard.do")
//	public String selectAllActionWard(HttpServletRequest req)throws ServletException, IOException{
//		System.out.println("成功！");
//		//return actionWardService.selectAllActionWard(req);
//		return null;
//	}
	//查询分享奖励
	@RequestMapping("/selectShareWard.do")
	public String selectShareWard(HttpServletRequest req)throws ServletException, IOException{
		return actionWardService.selectShareWard(req);
	}
	//修改活动奖励
	@RequestMapping("/alterActionWard.do")
	@ResponseBody
	public Result alterActionWard(HttpServletRequest req,int id,String wardName,int gold) 
			throws ServletException, IOException {
		//return actionWardService.alterActionWard(req, id, wardName, gold);
		return null;
	}
	//请求修改分享奖励页面
	@RequestMapping("/updatePrize.do")
	public String updatePrize(HttpServletRequest req) 
			throws ServletException, IOException {
		return actionWardService.updatePrize(req);
	}
	//修改分享奖励
	@RequestMapping("/toUpdatePrize.do")
	@ResponseBody
	public Result toUpdatePrize(HttpServletRequest req,String id,String prize) 
			throws ServletException, IOException {
		
		return actionWardService.toUpdatePrize(req,Integer.parseInt(id),Integer.parseInt(prize));
	}
}
