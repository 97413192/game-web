package com.game.business.service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import com.game.base.util.Result;
/** 
 * <li>ClassName:ManagerService <br/> 
 * <li>@Description: 系统设置业务接口
 * <li>@Date:     2016年11月1日 <br/> 
 * <li>@author   周强      
 */
public interface SysSetService {
	//请求增加管理员页面
	public String toAddMger(HttpServletRequest req)throws ServletException, IOException;
	//请求修改管理员页面
	public String toAlter(HttpServletRequest req)throws ServletException, IOException;
	//请求删除管理员页面
	public String toDelete(HttpServletRequest req)throws ServletException, IOException;
	//添加管理员
	Result add(HttpServletRequest req,String name,String password,String system,String power)
			throws ServletException, IOException;
	//修改管理员
	Result alter(HttpServletRequest req,String newname,String name,String password,String power,String system)
			throws ServletException, IOException;
	//删除管理员
	public Result delete(HttpServletRequest req,String name,String system)throws ServletException, IOException;
	//请求管理员操作日志显示页
	public String toSysLog(HttpServletRequest req)throws ServletException, IOException;
	//请求查询所有管理员
	public String selectAllManager(HttpServletRequest req)throws ServletException, IOException;
	//请求查询单个管理员详细信息
	public String selectManager(HttpServletRequest req)throws ServletException, IOException;
	//修改管理员,包括修改管理的状态
	public Result alterAndStatus(HttpServletRequest req,String olduser,String newuser,Integer status,String newpwd)
			throws ServletException, IOException;
	//通过名字查询管理员详细信息
	public Result selectManagerByName(HttpServletRequest req,String name)throws ServletException, IOException;
	//到日志查询界面
	public String toLogSelect(HttpServletRequest req) throws ServletException, IOException;
	//到高级日志查询界面
	public String toAdvancedLogSelect(HttpServletRequest req) throws ServletException, IOException;
	//删除日志
	public String toClearLog(HttpServletRequest req) throws ServletException, IOException;
	//根据参数返回查询日志界面
	public String toSelectLogByCondition(HttpServletRequest req, String name, String startDate, String endDate,String ip, String index) throws ServletException, IOException;
	//根据日期删除日志
	public Result clearLogByDate(HttpServletRequest req, String startDate, String endDate) throws ServletException, IOException;
	//请求到代理商日志页面
	public String toAgentLogSelect(HttpServletRequest req) throws ServletException, IOException;
}











