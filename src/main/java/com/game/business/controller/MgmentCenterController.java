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

import com.game.base.util.GetAdmin;
import com.game.base.util.Result;
import com.game.business.service.MgmentCenterService;

/**
 * 
 * <li>@ClassName: 管理中心控制层
 * <li>@author 周强
 * <li>@date 2016年10月28日
 *
 */
@Controller
@RequestMapping("/mgmentConter")
public class MgmentCenterController {
	Log log = LogFactory.getLog(MgmentCenterController.class);
	@Resource
	private MgmentCenterService mgmentCenterService;
	
	/**
	 * 请求管理中心头部网页
	 * @param req
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping("/header.do")
	public String header(HttpServletRequest req)throws ServletException, IOException{
		return mgmentCenterService.header(req);
	}
	/**
	 * 请求管理中心右部网页
	 * @return
	 */
	@RequestMapping("/right.do")
	public String right() {
		return "mgCenterManagement/content";
	}
	/**
	 * 请求管理中心左部网页
	 * @param req
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping("/left.do")
	public String left(HttpServletRequest req)
			throws ServletException, IOException{
		return mgmentCenterService.left(req);
	}
	/**
	 * 请求修改管理员密码页面
	 * @param req
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping("/adit.do")
	public String edit(HttpServletRequest req)throws ServletException, IOException {
		return "mgCenterManagement/adit_adminPsw";
	}
	/**
	 * 修改密码
	 * @param req
	 * @param AdminPwd
	 * @param AdminPwd1
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping("/aditPwd.do")
	@ResponseBody
	public Result aditPwd(HttpServletRequest req,String AdminPwd,String AdminPwd1)
			throws ServletException, IOException{
		return mgmentCenterService.aditPwd(req,AdminPwd,AdminPwd1);
	}
	/**
	 * 请求退出后台管理系统
	 * @param req
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping("/dropOutSystem.do")
	public String dropOutSystem(HttpServletRequest req)
			throws ServletException, IOException{
		GetAdmin.dropOutAdmin(req);  //清除当前管理员
		return "login/login";
	}
	
}
