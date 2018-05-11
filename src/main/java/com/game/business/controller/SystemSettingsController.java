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
import com.game.business.service.SysSetService;
/**
 * 
 * <li>@ClassName: 管理中心控制层
 * <li>@author 周强
 * <li>@date 2016年10月28日
 *
 */
@Controller
@RequestMapping("/systemSettings")
public class SystemSettingsController {
	Log log = LogFactory.getLog(SystemSettingsController.class);
	@Resource
	private SysSetService sysSetService;
	
	/**
	 * 请求增加管理员页面
	 * 只有超级管理员才有此权限
	 * @param req
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping("/toAddManager.do")
	public String toAddMger(HttpServletRequest req)throws ServletException, IOException{
		return sysSetService.toAddMger(req);
	}
	/**
	 * 增加管理员
	 * 只有超级管理员才有此权限
	 * @param req
	 * @param name
	 * @param password
	 * @param system
	 * @param power
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping("/addManager.do")
	@ResponseBody
	public Result add(HttpServletRequest req,String name,String password,String system,String power)
		throws ServletException, IOException{
		return sysSetService.add(req, name, password, system, power);
	}
	/**
	 * 请求修改页面
	 * 只有超级管理员才有此权限
	 * @param req
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping("toAlter.do")
	public String toAlter(HttpServletRequest req)throws ServletException, IOException{
		return sysSetService.toAlter(req);
	}
	/**
	 * 修改管理员
	 * 只有超级管理员才有此权限
	 * @param req
	 * @param name
	 * @param newname
	 * @param password
	 * @param power
	 * @param system
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping("/alter.do")
	@ResponseBody
	public Result alter(HttpServletRequest req,String name,String newname,String password,String power,String system)
			throws ServletException, IOException{
		return sysSetService.alter(req, name,newname, password,power,system);
	}
	/**
	 * 请求删除管理员页面
	 * 只有超级管理员才有此权限
	 * @param req
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping("/toDelete.do")
	public String toDelete(HttpServletRequest req)throws ServletException, IOException{
		return sysSetService.toDelete(req);
	}
	/**
	 * 删除管理员
	 * 只有超级管理员才有此权限
	 * @param req
	 * @param name
	 * @param system
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping("/delete.do")
	@ResponseBody
	public Result delete(HttpServletRequest req,String name,String system)throws ServletException, IOException{
		return sysSetService.delete(req, name, system);
	}
	/**
	 * 请求管理员日志网页
	 * 只有超级管理员才有此权限
	 * @param req
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping("/toSystemLog.do")
	public String toSystemLog(HttpServletRequest req)throws ServletException, IOException{
		return sysSetService.toSysLog(req);
	}
	/**
	 * 请求查询所有管理员
	 * 只有超级管理员才有此权限
	 * @param req
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping("/selectAllManager.do")
	public String selectAllManager(HttpServletRequest req)throws ServletException, IOException{
		return sysSetService.selectAllManager(req);
	}
	/**
	 * 请求查询单个管理员详细信息
	 * 只有超级管理员才有此权限
	 * @param req
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping("/selectManager.do")
	public String selectManager(HttpServletRequest req)throws ServletException, IOException{
		return sysSetService.selectManager(req);
	}
	
	/**
	 * 修改管理员,包括修改管理的状态
	 * 只有超级管理员才有此权限
	 * @param req
	 * @param olduser
	 * @param newuser
	 * @param status
	 * @param newpwd
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping("/alterAadStatus.do")
	@ResponseBody
	public Result alterAndStatus(HttpServletRequest req,String olduser,String newuser,Integer status,String newpwd)
			throws ServletException, IOException{
		return sysSetService.alterAndStatus(req, olduser,newuser, status,newpwd);
	}
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * 通过名字查询管理员详细信息
	 * @param req
	 * @param name
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping("/selectManagerByName.do")
	@ResponseBody
	public Result selectManagerByName(HttpServletRequest req,String name)
			throws ServletException, IOException{
		
		return sysSetService.selectManagerByName(req, name);
	}
	
	/**
	 * 到日志查询界面
	 * @param req
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping("/toLogSelect.do")
	public String toLogSelect(HttpServletRequest req) throws ServletException, IOException{
		
		return sysSetService.toLogSelect(req);
	}
	/**
	 * 到高级日志查询界面
	 * @param req
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping("/toAdvancedLogSelect.do")
	public String toAdvancedLogSelect(HttpServletRequest req) throws ServletException, IOException{
		
		return sysSetService.toAdvancedLogSelect(req);
	}
	/**
	 * 删除日志
	 * @param req
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping("/toClearLog.do")
	public String toClearLog(HttpServletRequest req) throws ServletException, IOException{
		
		return sysSetService.toClearLog(req);
	}
	/**
	 * 根据参数返回查询日志界面
	 * @param req
	 * @param name
	 * @param startDate
	 * @param endDate
	 * @param ip
	 * @param index
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping("/toSelectLogByCondition.do")
	public String toSelectLogByCondition(HttpServletRequest req,String name,String startDate,String endDate,String ip,String index) throws ServletException, IOException{
		
		return sysSetService.toSelectLogByCondition(req,name,startDate,endDate,ip,index);
	}
	
	/**
	 * 根据日期删除日志
	 * @param req
	 * @param startDate
	 * @param endDate
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping("/clearLogByDate.do")
	@ResponseBody
	public Result clearLogByDate(HttpServletRequest req,String startDate,String endDate) throws ServletException, IOException{
		
		return sysSetService.clearLogByDate(req,startDate,endDate);
	}
	/**
	 * 请求代理商日志网页
	 * @param req
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	 @RequestMapping("/toSystemAgentLog.do")
	 public String toSystemAgentLog(HttpServletRequest req)throws ServletException, IOException{	
			return sysSetService.toAgentLogSelect(req);
		} 
	 
	 /**
	  * 没有操作权限
	  * @param req
	  * @return
	  * @throws ServletException
	  * @throws IOException
	  */
	 @RequestMapping("/noPower.do")
	 public String noPower(HttpServletRequest req)throws ServletException, IOException{	
		 return "systemSettingsManagement/selectAllManagerNoPower";
		} 
}























