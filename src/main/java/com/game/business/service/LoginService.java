package com.game.business.service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.game.base.util.Result;
import com.game.business.model.manager.Manager;
/** 
 * <li>ClassName:ManagerService <br/> 
 * <li>@Description: 登陆业务接口
 * <li>@Date:     2016年10月25日 <br/> 
 * <li>@author   周强      
 */
public interface LoginService {
	//获取验证码
	void createImg(HttpServletRequest req,HttpServletResponse res)throws ServletException, IOException;
	//验证登陆，请求到管理中心
	Result login(HttpServletRequest req,String name,String password,String system)
			throws ServletException, IOException;
	//请求管理中心页面
	public String tomanagementCenter(HttpServletRequest req)throws ServletException, IOException;
}
