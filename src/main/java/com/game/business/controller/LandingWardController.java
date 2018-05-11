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
import com.game.business.service.LandingWardService;


/**
 * <li>@ClassName: 登陆奖励管理控制层
 * <li>@author 周强
 * <li>@date 2016年11月17日
 */
@Controller
@RequestMapping("/landingWard")
public class LandingWardController {
//	Log log = LogFactory.getLog(PlayerController.class);
//	@Resource
//	private LandingWardService landingWardService;
//	
//	//查询所有登陆奖励
//	@RequestMapping("/selectAllLandingWard.do")
//	public String selectAllLandingWard(HttpServletRequest req)throws ServletException, IOException{
//		System.out.println("成功！");
//		//return landingWardService.selectAllLandingWard(req);
//		return null;
//	}
//	//修改登陆奖励金币和概率
//	@RequestMapping("/alterLandingWard.do")
//	@ResponseBody
//	public Result alterLandingWard(HttpServletRequest req,int id,int gold,int chance) throws ServletException, IOException {
//		System.out.println("成功！");
//		//return landingWardService.alterLandingWard(req, id, gold, chance);
//		return null;
//	}
}
