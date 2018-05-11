package com.game.business.controller;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.game.base.util.Result;
import com.game.business.service.LoginService;

/**
 * 
 * <li>@ClassName: 登陆控制层
 * <li>@author 周强
 * <li>@date 2016年10月25日
 *
 */
@Controller
@RequestMapping("/login")
public class LoginController {
	Log log = LogFactory.getLog(LoginController.class);
	@Resource
	private LoginService loginService;
	
	/**
	 * 请求登陆页面
	 * @return
	 */
	@RequestMapping("/toLogin.do")
	public String toRegest() {
		return "login/login";
	}
	/**
	 * 请求验证码
	 * @param req
	 * @param res
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping("/createImg.do")
	public void createImg(HttpServletRequest req,HttpServletResponse res)throws ServletException, IOException{
		loginService.createImg(req,res);
	}
	/**
	 * 请求登陆
	 * @param req
	 * @param name
	 * @param password
	 * @param imgCode
	 * @param system
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping("/login.do")
	@ResponseBody
	public Result login(HttpServletRequest req,String name,String password,String system)
			throws ServletException, IOException{
		return loginService.login(req,name,password,system);
	}
	/**
	 * 请求进入管理中心
	 * @param req
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping("/managementCenter.do")
	public String tomanagementCenter(HttpServletRequest req)throws ServletException, IOException {
		return loginService.tomanagementCenter(req);
	}
}
