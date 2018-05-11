package com.game.business.service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import com.game.base.util.Result;
/** 
 * <li>ClassName:ManagerService <br/> 
 * <li>@Description: 管理中心业务接口
 * <li>@Date:     2016年10月31日 <br/> 
 * <li>@author   周强      
 */
public interface MgmentCenterService {
	//修改管理员密码
	Result aditPwd(HttpServletRequest req,String AdminPwd,String AdminPwd1)
			throws ServletException, IOException;
	//请求修改管理员密码页面
	public String edit(HttpServletRequest req)throws ServletException, IOException;
	//请求管理中心左边网页
	public String left(HttpServletRequest req)throws ServletException, IOException;
	//请求管理中心头部页面
	public String header(HttpServletRequest req)throws ServletException, IOException;
}
