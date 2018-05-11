package com.game.business.service;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.game.base.util.BeanFactory;
import com.game.base.util.Code;
import com.game.base.util.GetAdmin;
import com.game.base.util.GetData;
import com.game.base.util.Md5Util;
import com.game.base.util.PowerManagment;
import com.game.base.util.Result;
import com.game.business.controller.MgmentCenterController;
import com.game.business.mapper.AgentMapper;
import com.game.business.mapper.LogMapper;
import com.game.business.mapper.MgerMapper;
import com.game.business.mapper.PromoCodeMapper;
import com.game.business.model.Agent;
import com.game.business.model.PromoCode;
import com.game.business.model.manager.Manager;
import com.game.business.model.manager.ManagerLog;

/** 
 * <li>ClassName:ManagerService <br/> 
 * <li>@Description: 管理中心业务层
 * <li>@Date:     2016年10月31日 <br/> 
 * <li>@author   周强      
 */
@Service
@Transactional
public class MgmentCenterServiceImpl implements MgmentCenterService {
	Log log = LogFactory.getLog(MgmentCenterServiceImpl.class);
	@Resource
	private MgerMapper managerMapper;
	@Resource
	private LogMapper logMapper;
	@Resource
	private AgentMapper agentMapper;
	@Resource
	private PromoCodeMapper promoCodeMapper;
	
	/**
	 * 修改当前管理员密码
	 */
	public Result aditPwd(HttpServletRequest req,String AdminPwd,String AdminPwd1)
			throws ServletException, IOException{
		Result result = new Result();  						//返回结果的数据
		String admin = GetAdmin.getAdmin(req);  			//获取当前管理员名
		GetData getData = (GetData) BeanFactory.getBean(GetData.class);
		String password = null;
		int count = 0;
		
		//判断当前管理员是否超时
		if(admin == null){
			//保存日志
			getData.saveCurrentLog(req,Code.ALTER_MANAGERPWD_OUTTIME, ManagerLog.error);
			
			//返回结果
			result.setStatus(-1);
			return result;
		}
		try{
			//管理员
			Manager manager = managerMapper.findByName(admin);
			//代理商
			Agent agent = agentMapper.findAgentByName(admin);
			
			//管理员
			if(PowerManagment.checkOperCategory(admin) == 1
					&& PowerManagment.checkManagmentCategory(admin)){
				password = manager.getPassword();
				//验证修改密码的权限,超级管理可以修改,有响应的权限可以修改
				if(!password.equals(Md5Util.getMD5(AdminPwd.getBytes(), true))){
					//将由于密码错误导致失败存入日志
					getData.saveCurrentLog(req,admin+Code.ALTER_PWDERROR_MANAGERPWD, ManagerLog.error);
					
					//返回消息
					result.setStatus(2);
					result.setMsg(admin+Code.ALTER_PWDERROR_MANAGERPWD);
					return result;
				}
				
				manager.setName(admin);
				manager.setPassword(Md5Util.getMD5(AdminPwd1.getBytes(), true));
				count = managerMapper.aditPwd(manager);
				
			}else if(PowerManagment.checkOperCategory(admin) == 2){
				password = agent.getPassword();
				//验证修改密码的权限,超级管理可以修改,有响应的权限可以修改
				if(!password.equals(Md5Util.getMD5(AdminPwd.getBytes(), true))){
					//将由于密码错误导致失败存入日志
					getData.saveCurrentLog(req,admin+Code.ALTER_PWDERROR_MANAGERPWD, ManagerLog.error);
					
					//返回消息
					result.setStatus(2);
					result.setMsg(admin+Code.ALTER_PWDERROR_MANAGERPWD);
					return result;
				}
				
				agent.setPassword(Md5Util.getMD5(AdminPwd1.getBytes(), true));
				count = agentMapper.alertAgentById(agent);
				
			}else{
				//将由于密码错误导致失败存入日志
				getData.saveCurrentLog(req,"操作者修改密码:操作者不存在!", ManagerLog.error);
				
				//返回消息
				result.setStatus(3);
				result.setMsg("操作者修改密码:操作者不存在!");
				return result;
			}
			
			if(count > 0){
				//将修改成功存入日志
				getData.saveCurrentLog(req,"操作者修改密码:修改成功!", ManagerLog.success);
				
				//返回结果
				result.setStatus(0);
				result.setMsg("操作者修改密码:修改成功!");
				return result;
			}else{
				//将修改失败存入日志
				getData.saveCurrentLog(req,admin+Code.ALTER_ERROR_MANAGERPWD, ManagerLog.error);
				
				//返回消息
				result.setStatus(4);
				result.setMsg(admin+Code.ALTER_ERROR_MANAGERPWD);
				return result;
			}
		}catch(Exception e){
			e.printStackTrace();
			//TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			//将修改失败存入日志
			getData.saveCurrentLog(req,admin+Code.ALTER_EXCEPTION_MANAGERPWD, ManagerLog.error);
			
			//返回结果
			result.setStatus(5);
			result.setMsg(admin+Code.ALTER_ERROR_MANAGERPWD);
			//throw new RuntimeException("异常");
		}
		return result;
	}
	
	/**
	 * 请求修改密码页面
	 * 注意,此权限超级管理和拥有权限的管理员都可以
	 */
	public String edit(HttpServletRequest req) throws ServletException, IOException {
		String admin = GetAdmin.getAdmin(req);  			//获取当前管理员名
		GetData getData = (GetData) BeanFactory.getBean(GetData.class);
		
		//判断当前管理员是否超时
		if(admin == null){
			//保存日志
			getData.saveCurrentLog(req,Code.ALTER_MANAGERPWDPAGE_OUTTIME, ManagerLog.error);
			
			//返回结果
			return "login/login";
		}
		
		//需要验证是否是超级管理员,此权限超级管理和拥有权限的管理员都可以
		
		
		
		//成功
		getData.saveCurrentLog(req,Code.ALTER_SUC_MANAGERPWDPAGE, ManagerLog.success);
		return "mgCenterManagement/adit_adminPsw";
	}

	/**
	 * 请求管理员左边网页
	 * 注意:此处错误是返回login/login有错,需要修改
	 */
	public String left(HttpServletRequest req) throws ServletException, IOException {
		String admin = GetAdmin.getAdmin(req);  			//获取当前管理员名
		GetData getData = (GetData) BeanFactory.getBean(GetData.class);
		
		//判断当前管理员是否超时
		if(admin == null){
			//保存日志
			getData.saveCurrentLog(req,admin+"由于管理员存在超时或者没有登录操作失败!", ManagerLog.error);
			
			//返回结果
			return "login/login";
		}
		try{
			//判断管理员表中是否有此用户,
			Manager mg = managerMapper.findByName(admin);
			Agent agent = agentMapper.findAgentByName(admin);
			
			//管理员
			if(mg != null){
				//将这个管理员存入req中,进行相应权限设定
				req.setAttribute("power", 1);
				req.setAttribute("mg", mg);
				return "mgCenterManagement/left";
			}
			
			//代理商
			if(agent != null){
				
				//如果该代理商有游戏玩家绑定，则显示解除绑定的操作
				List<PromoCode> promoCode = promoCodeMapper.selectByAccount(agent.getAccount());
				if (promoCode != null) {
					req.setAttribute("showUnbind", promoCode);
				}
				req.setAttribute("power", 2);
				req.setAttribute("agent", agent);
				return "mgCenterManagement/left";
			}
			
			
//			if(PowerManagment.checkOperCategory(admin) == 3){
//				//记录失败日志
//				getData.saveCurrentLog(req,"请求管理中心左边网页时:由于管理员不存在请求失败!", ManagerLog.error);
//				return "login/login"; 
//			}
			
			/*
			 * 权限管理
			 */
			//二级代理商没有给代理商充值的权限(系统代理商的最低级数为二级)
			if(PowerManagment.checkOperCategory(admin) == 2 
					&& PowerManagment.checkAgentCategory(admin) == 2){
				//记录二级代理商为2,用于给代理商充值权限判断
				req.setAttribute("agentPower", 2);
				
			}
			
			//将这个管理员存入req中,进行相应权限设定
			return "mgCenterManagement/left";
		}catch(Exception e){
			e.printStackTrace();
		}
		return "login/login";
	}

	//请求管理中心头部页面
	public String header(HttpServletRequest req) throws ServletException, IOException {
		String admin = GetAdmin.getAdmin(req);  			//获取当前管理员名
		GetData getData = (GetData) BeanFactory.getBean(GetData.class);
		
		//判断当前管理员是否超时
		if(admin == null){
			//保存日志
			getData.saveCurrentLog(req,admin+"由于管理员存在超时或者没有登录操作失败!", ManagerLog.error);
			
			//返回结果
			return "login/login";
		}
		try{
			//判断管理员表中是否有此用户
			Manager mg = managerMapper.findByName(admin);
			Agent agent = agentMapper.findAgentByName(admin);
			
			
			//管理员
			if(mg != null){
				//将这个管理员存入req中,进行相应权限设定
				req.setAttribute("mg", mg);
				//进行权限判断,判断在头部网页中管理员是否具有修改密码的权限
				String power = mg.getPower();
				if(power == null){
					req.setAttribute("power", 3);
				}
				String[] powers = power.split(",");
				for(int i = 0 ;i<powers.length;i++){
					if("6".equals(powers[i])){
						req.setAttribute("power", 1);
						return "mgCenterManagement/header";
					}
				}
				
				req.setAttribute("power", 3);
				return "mgCenterManagement/header";
			}
			
			//代理商
			if(agent != null){
				req.setAttribute("power", 2);
				req.setAttribute("agent", agent);
				
				return "mgCenterManagement/header";
			}
			
			return "login/login";
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return "login/login";
	}

}
