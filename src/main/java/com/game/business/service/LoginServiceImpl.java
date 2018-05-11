package com.game.business.service;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Timestamp;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.game.base.util.BeanFactory;
import com.game.base.util.Code;
import com.game.base.util.DateUtil;
import com.game.base.util.GetAdmin;
import com.game.base.util.GetData;
import com.game.base.util.ImageUtil;
import com.game.base.util.JudgeAuthority;
import com.game.base.util.Md5Util;
import com.game.base.util.PowerManagment;
import com.game.base.util.Result;
import com.game.base.util.SessionAttributeListener;
import com.game.business.mapper.AgentMapper;
import com.game.business.mapper.LogMapper;
import com.game.business.mapper.MgerMapper;
import com.game.business.model.Agent;
import com.game.business.model.manager.Manager;
import com.game.business.model.manager.ManagerLog;
/** 
 * <li>ClassName:ManagerServiceImpl <br/> 
 * <li>@Description: 登陆业务层
 * <li>@Date:     2016年10月25日 <br/> 
 * <li>@author   周强       
 */
@Service
@Transactional
public class LoginServiceImpl implements LoginService{
	@Resource
	private MgerMapper managerMapper;
	@Resource
	private LogMapper logMapper;
	@Resource
	private AgentMapper agentMapper;
	
	//生成验证码
	public void createImg(HttpServletRequest req,HttpServletResponse res)
		throws ServletException,IOException{
		//生成验证码及图片
		Object[] objects = ImageUtil.createImage();
		String imgcode = (String)objects[0];
		//将验证码存入session
		HttpSession session = req.getSession();
		session.setAttribute("imgcode", imgcode);
		//将图片输出给浏览器
		res.setContentType("image/png");
		OutputStream os = res.getOutputStream();
		BufferedImage image = (BufferedImage)objects[1];
		ImageIO.write(image, "png", os);
		os.close();
	}
	
	//验证登陆，请求到管理中心
	public Result login(HttpServletRequest req, String name, String password,String system)
			throws ServletException, IOException {
		Result result = new Result();  //返回结果的数据
		ManagerLog log = new ManagerLog();  //日志实体类
		GetData getData = (GetData) BeanFactory.getBean(GetData.class);
		
		//判断传入的name是否有值
		if(name == null || name == ""){
			result.setStatus(-1);
			result.setMsg("管理员不能为空!");
			return result;
		}
		
		try{
			HttpSession session = req.getSession();
			session.setAttribute("admin", name);  	//将登录操作者存入session中
			session.setMaxInactiveInterval(30*60);  //设置session过期时间30分钟
			
			/*
			 * 将登陆系统存入session中,
			 * 方便当前操作用户每次日志插入时调用
			 */
			session.setAttribute("system", system);
			String ip = GetData.getRemoteHost(req);  //获取客户端ip地址
			
			//将日志信息存入ManagerLog中
			log.setName(name);
			log.setSystem(system);
			Timestamp time = new Timestamp(System.currentTimeMillis());
			log.setTime(time);
			log.setIp(ip);
			
			//通过管理员名得到管理员具体信息,并验证管理员是否存在
			Manager manager = managerMapper.findByName(name);
			//通过名字得到代理商信息,并验证代理商是否存在
			Agent agent = agentMapper.findAgentByName(name);
			if(manager == null && agent == null){
				//将失败存入日志
				getData.saveCurrentLog(req, Code.REQUEST_NOUSER_LOGIN, ManagerLog.error);
				
				result.setStatus(1);
				result.setMsg("用户名不存在");
				return result;
			}
			
			//判断验证码
//			String code = (String)session.getAttribute("imgcode");
//			imgCode = imgCode.toUpperCase();
//			if(!imgCode.equals(code)){
//				//将失败存入日志
//				getData.saveCurrentLog(req, name+"登陆失败:由于验证码错误!", ManagerLog.error);
//				
//				//返回结果
//				result.setStatus(3);
//				result.setMsg("验证码错误");
//				return result;
//			}
			
			//管理员
			if(manager != null){
				//判断密码
				if(!(Md5Util.getMD5(password.getBytes(), true)).equals(manager.getPassword())){
					//将失败存入日志
					getData.saveCurrentLog(req,name+"登陆失败:由于密码错误!", ManagerLog.error);
					
					//返回结果
					result.setStatus(2);
					result.setMsg("密码错误");
					return result;
				}
				
				//判断是否处于冻结状态
				if(manager.getStatus() == 2){
					//将失败存入日志
					getData.saveCurrentLog(req,name+"登陆失败:由于账号已被冻结!", ManagerLog.error);
					
					//返回结果
					result.setStatus(4);
					result.setMsg("此账号已被冻结");
					return result;
				}
				
				//将数据库中上次登录的时间和ip与登录次数更新
				manager.setLastLoginIp(ip);
				manager.setLoginTimes(manager.getLoginTimes()+1);
				managerMapper.updateByName(manager);
				//获取管理员登录时间 (秒)
				Manager.loginTime=System.currentTimeMillis();
				//把登录时间存在session里面
				session.setAttribute("time", Manager.loginTime);
				
				//管理员登入成功后显示为在线
				manager.setStatus(1);
				managerMapper.update(manager);
				
				//记录成功日志
				getData.saveCurrentLog(req,name+"登陆成功！", ManagerLog.success);
			}
			
			//代理商
			if(agent != null){
				//判断密码
				if(!(Md5Util.getMD5(password.getBytes(), true)).equals(agent.getPassword())){
					//记录失败日志
					getData.saveCurrentLog(req,name+"登陆失败:由于密码错误!", ManagerLog.error);
					
					//返回结果
					result.setStatus(2);
					result.setMsg("密码错误");
					return result;
				}
				
				//判断是否处于冻结状态
				if(agent.getState() == JudgeAuthority.freeze){
					//记录失败日志
					getData.saveCurrentLog(req,name+"登陆失败:由于账号已被冻结!", ManagerLog.error);
					
					//返回结果
					result.setStatus(4);
					result.setMsg("此账号已被冻结");
					return result;
				}
				
				//将数据库中上次登录的时间和ip与登录次数更新
				agent.setLastLoginIP(ip);
				agent.setLoginNumber(agent.getLoginNumber()+1);
				
				//代理商登入成功后显示为在线
				agent.setState("1");
				agentMapper.updateByName(agent);
				//获取代理商的登录时间(秒)
				Agent.logintime=System.currentTimeMillis();
				
				//记录成功日志
				getData.saveCurrentLog(req,name+"登陆成功！", ManagerLog.success);
			}
			
			/*
			 * 权限管理
			 */
			String admin = GetAdmin.getAdmin(req);  			//获取当前操作者名
			
			//二级代理商没有给代理商充值的权限(系统代理商的最低级数为二级)
			if(PowerManagment.checkOperCategory(admin) == 2 
					&& PowerManagment.checkAgentCategory(admin) == 2){
				//记录二级代理商为2,用于给代理商充值权限判断
				req.setAttribute("agentPower", 2);
				
			}
			
			
//			//登录时带来商,显示是几级代理商,房卡数
//			if(PowerManagment.checkOperCategory(admin) == 2){
//				//记录二级代理商为2,用于给代理商充值权限判断
//				session.setAttribute("agentPowerbyShow", 2);
//				session.setAttribute("agent", agent);
//			}
			
			
			//登录成功
			result.setStatus(0);
			result.setMsg(name+"登录成功");
			return result;
		}catch(Exception e){
			e.printStackTrace();
			getData.saveCurrentLog(req,name+"登陆失败:由于未知错误!", ManagerLog.error);
			result.setStatus(5);
			result.setMsg("未知错误");
		}
		return result;
	}
	
	//请求管理中心页面
	public String tomanagementCenter(HttpServletRequest req) throws ServletException, IOException {
		String admin=GetAdmin.getAdmin(req);
		GetData getData=(GetData) BeanFactory.getBean(GetData.class);
		
		if(admin == null){
			//保存日志
			getData.saveCurrentLog(req, Code.SELECT_MANAGEMENTPAGE_OUTTIME, ManagerLog.error);
			
			//返回结果
			return "login/login";
		}
		
		//成功
		getData.saveCurrentLog(req, Code.SELECT_SUC_MANAGEMENTPAGE, ManagerLog.error);
		return "mgCenterManagement/mgmentCenter";
	}
		

	 

}
