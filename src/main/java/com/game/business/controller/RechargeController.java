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
import com.game.business.service.RechargeService;


/**
 * <li>@ClassName: 充值管理控制层
 * <li>@author 周强
 * <li>@date 2016年11月18日
 */
@Controller
@RequestMapping("/recharge")
public class RechargeController {
	Log log = LogFactory.getLog(PlayerController.class);
	@Resource
	private RechargeService rechargeService;
	
	/** 根据条件查询多条充值记录*/
	@RequestMapping("/toSelectManyRechargeRecord.do")
	public String toSelectManyRechargeRecord(HttpServletRequest req)throws ServletException, IOException{
		System.out.println("jinlai");
		return null;
		//return rechargeService.toSelectManyRechargeRecord(req);
	}
	
	
	
	
//	
//	//查询钻石兑换金币比例
//	@RequestMapping("/selectConverScale.do")
//	public String selectConverScale(HttpServletRequest req)throws ServletException, IOException{
//		System.out.println("成功钻石金币兑换");
//		//return rechargeService.selectConverScale(req);
//		return null;
//	}
//	//修改钻石兑换金币比例
//	@RequestMapping("/alterConvertScale.do")
//	@ResponseBody
//	public Result alterConvertScale(HttpServletRequest req,int convertScale)throws ServletException, IOException{
//		System.out.println("成功修改钻石金币兑换");
//		//return rechargeService.alterConvertScale(req, convertScale);
//		return null;
//	}
//	//查询所有充值档次
//	@RequestMapping("/selectAllLevel.do")
//	public String selectAllLevel(HttpServletRequest req)throws ServletException, IOException{
//		System.out.println("查询所有档次成功！");
//		//return rechargeService.selectAllLevel(req);
//		return null;
//	}
//	//修改充值档次
//	@RequestMapping("/alterLevel.do")
//	@ResponseBody
//	public Result alterLevel(HttpServletRequest req,int id,int levelId,int money,int jewel)
//			throws ServletException, IOException{
//		System.out.println("修改充值档次");
//		//return rechargeService.alterLevel(req, id, levelId, money, jewel);
//		return null;
//	}
//	//请求增加充值档次页面
//	@RequestMapping("/toAddLevel.do")
//	public String toAddLevel(HttpServletRequest req)throws ServletException, IOException{
//		return rechargeService.toAddLevel(req);
//	}
//	//增加充值档次
//	@RequestMapping("/addLevel.do")
//	@ResponseBody
//	public Result addLevel(HttpServletRequest req,int levelId,int money,int jewel)
//			throws ServletException, IOException{
//		System.out.println("充值档次增加成功！");
//		//return rechargeService.addLevel(req, levelId, money, jewel);
//		return null;
//	}
	
	
	
	
	
	
	
	
	
	
}
