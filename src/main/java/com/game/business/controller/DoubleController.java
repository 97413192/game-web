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
import com.game.business.service.DoubleService;


/**
 * <li>@ClassName: 比倍管理控制层
 * <li>@author 周强
 * <li>@date 2016年11月16日
 */
@Controller
@RequestMapping("/double")
public class DoubleController {
//	Log log = LogFactory.getLog(PlayerController.class);
//	@Resource
//	private DoubleService doubleService;
//	
//	//查询所有比倍
//	@RequestMapping("/selectAllDouble.do")
//	public String selectAllDouble(HttpServletRequest req)throws ServletException, IOException{
//		System.out.println("成功！");
//		//return doubleService.selectAllDouble(req);
//		return null;
//	}
//	//修改获胜概率
//	@RequestMapping("/alterDouble")
//	@ResponseBody
//	public Result alterDouble(HttpServletRequest req,int chance,int id)throws ServletException, IOException{
//		//doubleService.alterDouble(req, chance, id);
//		return null;
//	}
}
