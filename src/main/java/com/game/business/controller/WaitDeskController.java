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
import com.game.business.service.WaitDeskService;
/**
 * <li>@ClassName: 留桌管理控制层
 * <li>@author 周强
 * <li>@date 2016年11月15日
 */
@Controller
@RequestMapping("/waitDesk")
public class WaitDeskController {
//	Log log = LogFactory.getLog(PlayerController.class);
//	@Resource
//	private WaitDeskService waitDeskService;
//	//请求增加留桌查看配置页面
//	@RequestMapping("/toAddWaitDesk.do")
//	public String toAddWaitDesk(HttpServletRequest req)throws ServletException, IOException{
//		return waitDeskService.toAddWaitDesk(req);
//	}
//	//增加留桌查看配置
//	@RequestMapping("/addWaitDesk.do")
//	@ResponseBody
//	public Result addWaitDesk(HttpServletRequest req,int time,int gold,String typeName,int typeId)
//			throws ServletException, IOException{
//		System.out.println(time);
//		System.out.println(gold);
//		System.out.println(typeName);
//		System.out.println(typeId);
//		return null;
//		//return waitDeskService.addWaitDesk(req,time,gold,typeName,typeId);
//	}
//	//请求查看所有留桌查看设置
//	@RequestMapping("/selectAllWaitDesk.do")
//	public String selectAllWaitDesk(HttpServletRequest req,String WaitDeskIndex)throws ServletException, IOException{
//		return waitDeskService.selectAllWaitDesk(req, WaitDeskIndex);
//		//return null;
//	}
//	//请求修改留桌查看页面
//	@RequestMapping("/toAlterWaitDesk.do")
//	public String toAlterWaitDesk(HttpServletRequest req,int id)
//			throws ServletException, IOException{
//		return waitDeskService.toAlterWaitDesk(req, id);
//		//return null;
//	}
//	//修改留桌查看配置
//	@RequestMapping("/alterWaitDesk.do")
//	@ResponseBody
//	public Result alterWaitDesk(HttpServletRequest req,int id,int time,int gold,String typeName,int typeId)
//			throws ServletException, IOException{
//		System.out.println(id);
//		System.out.println(time);
//		System.out.println(gold);
//		System.out.println(typeName);
//		System.out.println(typeId);
//		return null;
//		//return waitDeskService.alterWaitDesk(req, id, time, gold, typeName, typeId);
//	}
//	//通过查询所有的增加到已有的typeName或者typeId的留桌查看设置的增加页面
//	@RequestMapping("/toAllWaitDesk_selectAll.do")
//	public String toAllWaitDesk_selectAll(HttpServletRequest req,String typeName,int typeId)
//			throws ServletException, IOException{
//		//return waitDeskService.toAlterWaitDesk(req, id);
//		return null;
//	}
	

	
	
	
	
	
	
	
}
