package com.game.business.service;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.game.base.util.BeanFactory;
import com.game.base.util.Code;
import com.game.base.util.GetAdmin;
import com.game.base.util.GetData;
import com.game.base.util.Md5Util;
import com.game.base.util.Result;
import com.game.business.mapper.AgentLogMapper;
import com.game.business.mapper.AgentMapper;
import com.game.business.mapper.LogMapper;
import com.game.business.mapper.MgerMapper;
import com.game.business.model.Agent;
import com.game.business.model.AgentLog;
import com.game.business.model.manager.Manager;
import com.game.business.model.manager.ManagerLog;
import com.game.cache.CacheLoginServer;
import com.game.constant.D;
import com.game.constant.LoginRMIConstant;
import com.ranger.module.po.Usr;
import com.ranger.remote.LoginServerRMI;
import com.ranger.remote.RMIResult;
/** 
 * <li>ClassName:ManagerService <br/> 
 * <li>@Description: 系统设置业务层
 * <li>@Date:     2016年11月1日 <br/> 
 * <li>@author   周强      
 */

@Service
public class SysSetServiceImpl implements SysSetService {
	@Resource
	private MgerMapper mgMapper;
	@Resource
	private LogMapper logMapper;
	@Resource
	private AgentMapper agentMapper;
	@Resource
	private AgentLogMapper agentLogMapper;
	Map<String,Object> map = new HashMap<String,Object>();
	
	/**增加管理员*/
	public Result add(HttpServletRequest req, String name, String password, String system, String power)
			throws ServletException, IOException {
		System.out.println("小测试!");
		Result result = new Result();  						//创建返回结果
		String admin = GetAdmin.getAdmin(req);  			//获取当前管理员名
		Manager manager = new Manager();					//新建一个管理员
		String ip = GetData.getRemoteHost(req); 			//获取管理员ip
		Timestamp time = GetData.getTime(); 				//获取当前系统时间
		GetData getData = (GetData) BeanFactory.getBean(GetData.class);
		
		//判断当前管理员是否超时或者是否存在
		if(admin == null){
			
			//保存日志
			getData.saveCurrentLog(req, Code.ADD_MANAGER_OUTTIME_NOW, ManagerLog.error);
			//返回结果
			result.setStatus(-1);
			return result;
		}
		try{
			//判断数据库是否有此用户
			Manager mg = mgMapper.findByName(name);
			//通过名字得到代理商信息,并验证代理商是否存在
			Agent agent = agentMapper.findAgentByName(name);
			
			//管理员和代理商名字不能重名
			if(mg != null || agent != null){
				//记录失败日志
				getData.saveCurrentLog(req, Code.ADD_ERROREXIST_MANAGER_NOW, ManagerLog.error);
				
				//返回结果
				result.setMsg(Code.ADD_ERROREXIST_MANAGER_NOW);
				result.setStatus(2);
				return result;
			}
			
			//检查传入的name是否为空
			if(name == null){
				//记录失败日志
				getData.saveCurrentLog(req, "传入参数错误!", ManagerLog.error);
				
				//返回结果
				result.setMsg("传入参数错误!");
				result.setStatus(3);
				return result;
			}
			
			//验证传入的名字是否合法
			String regex1 = "^[0-9]+[A-Za-z]+$";
			String regex2 = "^[A-Za-z]+[0-9]+$";
			System.out.println("name:"+name);
			if(!name.matches(regex1) && !name.matches(regex2)){
				//记录失败日志
				getData.saveCurrentLog(req, "名字必须是数字和字母组合!", ManagerLog.error);
				System.out.println("小测试3!");
				
				//返回结果
				result.setMsg("名字必须是数字和字母组合!");
				result.setStatus(4);
				return result;
			}
			
			//获取当前操作管理员
			Manager nowMg = mgMapper.findByName(admin);
			//判断这个管理员是否是超级管理员
			if(nowMg.getCategory() != 1){
				//记录失败日志
				getData.saveCurrentLog(req,Code.ADD_ERROREXIST_NOT_POWER, ManagerLog.error);
				
				//返回结果
				result.setMsg(Code.ADD_ERROREXIST_NOT_POWER);
				result.setStatus(5);
				return result;
			}
			
			//manager(被创建者)属性赋值
			manager.setName(name);
			manager.setPassword(Md5Util.getMD5(password.getBytes(), true));
			manager.setSystem(system);
			manager.setTime(time);
			manager.setIp(ip);
			manager.setPower(power);
			int count = mgMapper.mgerSave(manager);  //执行SQL语句
			
			if(count>0){
				/*
				 * 将创建的管理员名存入req中，
				 * 当请求到修改页面是显示刚刚创建的管理员
				 */
				req.setAttribute("name", name);
				//记录创建成功日志
				getData.saveCurrentLog(req,name+"被创建成功",ManagerLog.success);
				
				//返回结果
				result.setMsg("创建成功！");
				result.setStatus(0);
				return result;
			}else{
				//记录创建管理员失败日志
				getData.saveCurrentLog(req,Code.ADD_ERROR_MANAGER_NOW,ManagerLog.error);
				
				//返回消息
				result.setMsg("创建失败！");
				result.setStatus(1);
				return result;
			}
		}catch(Exception e){
			e.printStackTrace();
			//记录失败日志
			getData.saveCurrentLog(req,Code.ALL_EXCEPTION_MANAGER,ManagerLog.error);
			
			//返回结果
			result.setData(6);
			result.setMsg("未知错误");
		}
		return result;
	}
	
	/**
	 * 修改管理员
	 */
	public Result alter(HttpServletRequest req, String name,String newname,String password,String power,String system)
			throws ServletException, IOException {
		Result result = new Result();  						//创建返回结果
		String admin = GetAdmin.getAdmin(req);  			//获取当前管理员名
		GetData getData = (GetData) BeanFactory.getBean(GetData.class);
		
		//判断当前管理员是否超时
		if(admin == null){
			//保存日志
			getData.saveCurrentLog(req,Code.ALTER_MANAGER_OUTTIME,ManagerLog.error);
			
			//返回结果
			result.setStatus(-1);
			return result;
		}
		try{
			//判断数据库有无此用户
			Manager manager1 = mgMapper.findByName(name);
			if(manager1 == null){
				//记录失败日志
				getData.saveCurrentLog(req,Code.ALTER_ERROR_NOMANAGER,ManagerLog.error);
				
				//返回信息
				result.setStatus(2);
				result.setMsg("此用户不存在");
				return result;
			}
			
			//获取当前操作管理员
			Manager nowMg = mgMapper.findByName(admin);
			//判断这个管理员是否是超级管理员
			if(nowMg.getCategory() != 1){
				//记录失败日志
				getData.saveCurrentLog(req,Code.ALTER_ERROR_NOT_POWER,ManagerLog.error);
				
				//返回结果
				result.setMsg(Code.ALTER_ERROR_NOT_POWER);
				result.setStatus(3);
				return result;
			}
			
			
			//判断数据库中是否含有新传入的名字
			Manager manager2 = mgMapper.findByName(newname);
			if(manager2 != null && !newname.equals(name)){  //允许只修改密码
				//记录失败日志
				getData.saveCurrentLog(req,Code.ALTER_ERROR_MANAGEREXIST,ManagerLog.error);
				
				//返回结果
				result.setStatus(4);
				result.setMsg("名字已被占用");
				return result;
			}
			
			
			//清空上次增加的name
			req.setAttribute("name", "");
			
			Integer id = manager1.getId();  //获取当前用户的id
			//将修改的管理员名，密码，权限存入实体类Manager中
			Manager mger = mgMapper.findByName(name);
			mger.setId(id);
			mger.setName(newname);
			mger.setPassword(Md5Util.getMD5(password.getBytes(), true));
			mger.setPower(power);
			int count = mgMapper.update(mger);  //执行SQL语句
			
			//判断修改是否成功
			if(count>0){ 
				//将修改操作记录到日志表中(超级用户日志)
				getData.saveCurrentLog(req,Code.ALTER_SUC_MANAGER,ManagerLog.success);
				
				//返回消息
				result.setMsg("修改成功");
				result.setStatus(0);
				return result;
			}else{
				//将修改失败操作记录到日志表中(超级用户日志)
				getData.saveCurrentLog(req,Code.ALTER_ERROR_MANAGER,ManagerLog.error);
				
				//返回消息
				result.setMsg("修改管理员失败");
				result.setStatus(1);
			}
		}catch(Exception e){
			e.printStackTrace();
			//记录失败日志
			getData.saveCurrentLog(req,Code.ALTER_EXCEPTION_MANAGER,ManagerLog.error);
			
			//返回消息
			result.setStatus(5);
			result.setMsg("未知错误");
		}
		return result;
	}
	
	/**
	 * 删除管理员
	 */
	public Result delete(HttpServletRequest req, String name, String system) throws ServletException, IOException {
		Result result = new Result();  						//创建返回结果
		String admin = GetAdmin.getAdmin(req);  			//获取当前管理员名
		GetData getData = (GetData) BeanFactory.getBean(GetData.class);
			
		//判断当前管理员是否超时
		if(admin == null){
			//保存日志
			getData.saveCurrentLog(req,Code.DELETE_MANAGER_OUTTIME,ManagerLog.error);
			
			//返回结果
			result.setStatus(-1);
			return result;
		}
		try{
			//判断数据库有无此用户
			Manager manager = mgMapper.findByName(name);
			if(manager == null){
				//记录了失败日志
				getData.saveCurrentLog(req,Code.DELETE_ERROR_NOMANAGER,ManagerLog.error);
				
				//返回信息
				result.setStatus(2);
				result.setMsg("此用户不存在");
				return result;
			}
			
			//获取当前操作管理员
			Manager nowMg = mgMapper.findByName(admin);
			//判断这个管理员是否是超级管理员
			if(nowMg.getCategory() != 1){
				//记录失败日志
				getData.saveCurrentLog(req,Code.DELETE_ERROR_NOT_POWER,ManagerLog.error);
				
				//返回结果
				result.setMsg(Code.DELETE_ERROR_NOT_POWER);
				result.setStatus(3);
				return result;
			}
			
			int count = mgMapper.deleteByName(name);  //执行SQL语句
			
			if(count>0){  //删除成功
				//将基本信息写入日志实体类(超级管理员)
				getData.saveCurrentLog(req,Code.DELETE_SUC_MANAGER,ManagerLog.success);
				
				//返回信息
				result.setMsg("删除成功");
				result.setStatus(0);
				return result;
			}else{//删除失败
				//将基本信息写入日志实体类(超级管理员)
				getData.saveCurrentLog(req,Code.DELETE_ERROR_MANAGER,ManagerLog.error);
				
				//返回信息
				result.setMsg("删除失败");
				result.setStatus(1);
				return result;
			}
		}catch(Exception e){
			e.printStackTrace();
			//记录失败日志
			getData.saveCurrentLog(req,Code.DELETE_EXCEPTION_MANAGER,ManagerLog.error);
			
			//返回结果
			result.setStatus(4);
			result.setMsg("未知错误");
		}
		return result;
	}
	
	/**
	 * 请求管理员操作日志显示
	 */
	public String toSysLog(HttpServletRequest req)throws ServletException, IOException{
		String admin = GetAdmin.getAdmin(req);  			//获取当前管理员名
		GetData getData = (GetData) BeanFactory.getBean(GetData.class);
		
		//判断当前管理员是否超时
		if(admin == null){
			//保存日志
			getData.saveCurrentLog(req,Code.LOG_MANAGERPAGE_OUTTIME,ManagerLog.error);
			
			//返回结果
			return "login/login";
		}
		try{
			String index = (String)req.getParameter("index");
			int indexpage = Integer.valueOf(index);
			if(indexpage <0){  //判断index是否为第一次请求
				indexpage = 1;
			}
			
			List<ManagerLog> totalList = logMapper.findLogByName();  //获取所有的记录
			int count = totalList.size();  //获取日志总条数
			//System.out.println(count);
			
			int pagesize = 20;  //定义每页显示的记录数
			int pagecount;  //定义总共有多少页面
			List<ManagerLog> list = null; //此次请求页面的记录数
			
			//判断count的条数是够显示一个页面
			if(count<pagesize){ //总记录数不足显示一个页面
				pagesize = count;  //显示的记录数
				pagecount = 1;  //总页面数为1
				list = totalList.subList(0, count);
			}else{
				if(count%pagesize == 0){
					pagecount = count/pagesize;  //总页面数
					//获取请求页面数的记录
					list = totalList.subList(pagesize*indexpage-pagesize, pagesize*indexpage);
				}else{
					pagecount = count/pagesize+1; //当总页面数不能被pagesize整除时的数目
					/*
					 * 当总页面数不能被pagesize整除时的数目，
					 * 并且请求显示最后一页的数目时
					 */
					if(pagecount == indexpage){
						//取最后一页的记录（不足pagesize条个记录）
						list = totalList.subList(pagesize*indexpage-pagesize, (pagesize*indexpage-pagesize)+(count-pagesize*indexpage+pagesize));
					}else{
						list = totalList.subList(pagesize*indexpage-pagesize, pagesize*indexpage);
					}
				}
			}
			
			/*
			 * 将日志总的条数用req保存
			 * 方便转发到JSP页面取值，和后面请求页面分页显示取值
			 * 注意：count是动态变化的
			 */
			req.setAttribute("count", count);
			req.setAttribute("index", indexpage);  //将传进来的index页面数保存到req中
			req.setAttribute("pagesize", pagesize);  //将每页显示的记录数存入req中
			req.setAttribute("pagecount", pagecount);  //将pagecount用req保存
			req.setAttribute("list", list);  //将此次要显示的记录放入计划list中
			
			//保存成功日志
			getData.saveCurrentLog(req,Code.LOG_SUC_MANAGERPAGE,ManagerLog.success);
			
			return "systemSettingsManagement/systemLog";
		}catch(Exception e){
			e.printStackTrace();
			//记录异常日志
			getData.saveCurrentLog(req,Code.LOG_EXCEPTION_MANAGER,ManagerLog.error);
		}
		return "systemSettingsManagement/systemLog";
	}
	
	/**
	 * 请求增加管理员页面
	 */
	public String toAddMger(HttpServletRequest req) throws ServletException, IOException {
		String admin = GetAdmin.getAdmin(req);  			//获取当前管理员名
		GetData getData = (GetData) BeanFactory.getBean(GetData.class);
		
		//判断当前管理员是否超时
		if(admin == null){
			//保存日志
			getData.saveCurrentLog(req,Code.ADD_MANAGERPAGE_OUTTIME,ManagerLog.error);
			
			//返回结果
			return "login/login";
		}
		//成功
		getData.saveCurrentLog(req,Code.ADD_SUC_MANAGERPAGE,ManagerLog.success);
		return "systemSettingsManagement/addManager";
	}
	
	//请求修改管理员页面
	public String toAlter(HttpServletRequest req) throws ServletException, IOException {
		String admin = GetAdmin.getAdmin(req);  			//获取当前管理员名
		GetData getData = (GetData) BeanFactory.getBean(GetData.class);
		
		//判断当前管理员是否超时
		if(admin == null){
			//保存日志
			getData.saveCurrentLog(req,Code.ALTER_MANAGERPAGE_OUTTIME,ManagerLog.error);
			
			//返回结果
			return "login/login";
		}
		//成功
		getData.saveCurrentLog(req,Code.ALTER_SUC_MANAGERPAGE,ManagerLog.success);
		return "systemSettingsManagement/alterManager";
	}
	
	//请求删除管理员页面
	public String toDelete(HttpServletRequest req) throws ServletException, IOException {
		String admin = GetAdmin.getAdmin(req);  			//获取当前管理员名
		GetData getData = (GetData) BeanFactory.getBean(GetData.class);
		
		//判断当前管理员是否超时
		if(admin == null){
			//保存日志
			getData.saveCurrentLog(req,Code.DELETE_MANAGERPAGE_OUTTIME,ManagerLog.error);
			
			//返回结果
			return "login/login";
		}
		
		//成功
		getData.saveCurrentLog(req,Code.DELETE_SUC_MANAGERPAGE,ManagerLog.success);
		return "systemSettingsManagement/deleteManager";
	}

	/**
	 * 请求查询所有管理员
	 */
	public String selectAllManager(HttpServletRequest req) throws ServletException, IOException {
		String admin = GetAdmin.getAdmin(req);  			//获取当前管理员名
		GetData getData = (GetData) BeanFactory.getBean(GetData.class);
		String playerIndex = (String)req.getParameter("index");  //获取传入的请求页数
		
		//判断当前管理员是否超时
		if(admin == null){
			//保存日志
			getData.saveCurrentLog(req,admin+Code.SELECT_ALL_OUT_TIME,ManagerLog.error);
			
			//返回结果
			return "login/login";
		}
		try{
			//获取当前操作管理员
			Manager nowMg = mgMapper.findByName(admin);
			//判断这个管理员是否是超级管理员
			if(nowMg.getCategory() != 1){
				//记录失败日志
				getData.saveCurrentLog(req,Code.SELECT_ALL_NOT_POWER,ManagerLog.error);
				
				//返回结果
				return "systemSettingsManagement/selectAllManagerNoPower";
			}
			
			int indexpage = Integer.valueOf(playerIndex);
			//判断index是否为第一次请求
			if(indexpage <0){
				indexpage = 1;
			}
			
			//获取所有管理员
			List<Manager> list = mgMapper.selectAllManager();
			System.out.println(list);
			
			if(list != null){
				int pagesize = 20;  //定义每页显示的记录数
				//加入查询所有记录方法中
				GetData.selectAll(req, list, pagesize, indexpage,
						"count", "index", "pagesize", "pagecount", "list");
				
				//将管理员查询成功存入到日志表中
				getData.saveCurrentLog(req,admin+"查询所有管理员"+"："+ManagerLog.success,ManagerLog.success);
				return "systemSettingsManagement/selectAllManager";
			}else{
				//将管理员查询失败存入到日志表中
				getData.saveCurrentLog(req,admin+"查询所有管理员"+"：失败!",ManagerLog.error);
			}
		}catch(Exception e){
			e.printStackTrace();
			//将管理员查询失败存入到日志表中
			getData.saveCurrentLog(req,admin+"查询所有管理员"+"：由于未知异常查询失败!",ManagerLog.error);
		}
		return "systemSettingsManagement/selectAllManager";
	}

	/**
	 * 请求查询单个管理员详细信息
	 */
	public String selectManager(HttpServletRequest req) throws ServletException, IOException {
		String admin = GetAdmin.getAdmin(req);  			//获取当前管理员名
		GetData getData = (GetData) BeanFactory.getBean(GetData.class);
		req.setCharacterEncoding("UTF-8");
		String name = new String(req.getParameter("name").getBytes("iso8859-1"),"utf-8"); 
		
		//判断当前管理员是否超时
		if(admin == null){
			//保存日志
			getData.saveCurrentLog(req,admin+Code.SELECT_OUT_TIME,ManagerLog.error);
			
			//返回结果
			return "login/login";
		}
		
		try{
			//获取当前操作管理员
			Manager nowMg = mgMapper.findByName(admin);
			//判断这个管理员是否是超级管理员
			if(nowMg.getCategory() != 1){
				//记录失败日志
				getData.saveCurrentLog(req,admin+Code.SELECT_NOT_POWER,ManagerLog.error);
				
				//返回结果
				return "systemSettingsManagement/selectAllManagerNoPower";
			}
			
			//获取需要查询的管理员的详细信息
			Manager selectMgr = mgMapper.findByName(name);
			
			if(selectMgr != null){
				//将管理员信息传入到页面中
				req.setAttribute("mgr", selectMgr);
				
				//将管理员查询成功存入到日志表中
				getData.saveCurrentLog(req,admin+"查询管理员:"+name+ManagerLog.success,ManagerLog.success);
				return "systemSettingsManagement/selectManager";
			}else{
				//将管理员查询失败存入到日志表中
				getData.saveCurrentLog(req,admin+"查询管理员:"+name+"由于管理员不存在,查询失败!",ManagerLog.error);
			}
		}catch(Exception e){
			e.printStackTrace();
			//将管理员查询失败存入到日志表中
			getData.saveCurrentLog(req,admin+"查询所有玩家"+"：由于未知异常查询失败!",ManagerLog.error);
		}
		return "systemSettingsManagement/selectManager";
	}

	
	/**
	 * 修改管理员,包括修改管理员的状态
	 */
	@Transactional
	public Result alterAndStatus(HttpServletRequest req, String olduser, String newuser, Integer status, String newpwd)
			throws ServletException, IOException {
		Result result = new Result();  //创建返回结果
		String admin = GetAdmin.getAdmin(req);  			//获取当前管理员名
		GetData getData = (GetData) BeanFactory.getBean(GetData.class);
		
		//判断当前管理员是否超时
		if(admin == null){
			//保存日志
			getData.saveCurrentLog(req,Code.ALTER_MANAGER_OUTTIME,ManagerLog.error);
			
			//返回结果
			result.setStatus(-1);
			return result;
		}
		try{
			//判断数据库有无此用户
			Manager manager1 = mgMapper.findByName(olduser);
			if(manager1 == null){
				//记录失败日志
				getData.saveCurrentLog(req,Code.ALTER_ERROR_NOMANAGER,ManagerLog.error);
				
				//返回信息
				result.setStatus(2);
				result.setMsg("此用户不存在");
				return result;
			}
			
			//获取当前操作管理员
			Manager nowMg = mgMapper.findByName(admin);
			//判断这个管理员是否是超级管理员
			if(nowMg.getCategory() != 1){
				//记录失败日志
				getData.saveCurrentLog(req,Code.ALTER_ERROR_NOT_POWER,ManagerLog.error);
				
				//返回结果
				result.setMsg(Code.ALTER_ERROR_NOT_POWER);
				result.setStatus(3);
				return result;
			}
			
			//判断数据库中是否含有新传入的名字
			Manager manager2 = mgMapper.findByName(newuser);
			Agent agent = agentMapper.findAgentByName(newuser);
			if(manager2 != null || agent != null){  //允许只修改密码
				//记录失败日志
				getData.saveCurrentLog(req, Code.ALTER_ERROR_MANAGEREXIST, ManagerLog.error);
				
				//返回结果
				result.setStatus(4);
				result.setMsg("名字已被占用");
				return result;
			}
			
			
			//清空上次增加的name
			req.setAttribute("name", "");
			
			Integer id = manager1.getId();  //获取当前用户的id
			//将修改的管理员名，密码，权限存入实体类Manager中
			Manager mger = new Manager();
			mger.setId(id);
			mger.setName(newuser);
			mger.setPassword(Md5Util.getMD5(newpwd.getBytes(), true));
			mger.setStatus(status);
			int count = mgMapper.updateAndStatus(mger);  //执行SQL语句
			//判断修改是否成功
			if(count>0){ 
				//将修改操作记录到日志表中(超级用户日志)
				getData.saveCurrentLog(req, Code.ALTER_SUC_MANAGER, ManagerLog.success);
				
				//返回消息
				result.setMsg(Code.ALTER_SUC_MANAGER);
				result.setStatus(0);
				return result;
			}else{
				//将修改失败操作记录到日志表中(超级用户日志)
				getData.saveCurrentLog(req, Code.ALTER_ERROR_MANAGER, ManagerLog.error);
				
				//返回消息
				result.setMsg("修改管理员失败");
				result.setStatus(1);
			}
		}catch(Exception e){
			e.printStackTrace();
			//记录失败日志
			getData.saveCurrentLog(req, Code.ALTER_EXCEPTION_MANAGER, ManagerLog.error);
			
			//返回消息
			result.setStatus(5);
			result.setMsg("未知错误");
		}
		return result;
	}

	/**
	 * 通过名字查询管理员详细信息
	 */
	@Transactional
	public Result selectManagerByName(HttpServletRequest req, String name) throws ServletException, IOException {
		Result result = new Result();  						//创建返回结果
		String admin = GetAdmin.getAdmin(req);  			//获取当前管理员名
		GetData getData = (GetData) BeanFactory.getBean(GetData.class);
		
		//判断当前管理员是否超时
		if(admin == null){
			//保存日志
			getData.saveCurrentLog(req, Code.ADD_MANAGERPAGE_OUTTIME, ManagerLog.error);
			//返回结果
			result.setStatus(-1);
			return result;
		}
		//判断数据库中是否含有新传入的名字
		Manager manager = mgMapper.findByName(name);
		Agent agent = agentMapper.findAgentByName(name);
		if(manager == null && agent == null){  //允许只修改密码
			//记录失败日志
			getData.saveCurrentLog(req, Code.SELECT_MANAGERPAGE_NOT_EXIST, ManagerLog.error);
			
			//返回结果
			result.setStatus(1);
			result.setMsg("用户名不存在!");
			return result;
		}
		
		result.setData(name);
		result.setMsg("成功!");
		result.setStatus(0);
		
		return result;
	}
	//到日志查询界面
	public String toLogSelect(HttpServletRequest req) throws ServletException, IOException {
		ManagerLog log = new ManagerLog(); 		 //创建日志
		String admin = GetAdmin.getAdmin(req);   //获取当前管理员名
		String ip = GetData.getRemoteHost(req);  //获取管理员ip
		String system = GetData.getSystem(req);  //获取管理员操作系统
		Timestamp time = GetData.getTime(); 	 //获取当前系统时间
		//将ip地址，系统，管理员名，当前系统时间存入log中
		log.setIp(ip);
		log.setName(admin);
		log.setSystem(system);
		log.setTime(time);
		//判断当前管理员是否超时
		if(admin == null){
			//保存日志
			log.setIp(ip);
			log.setName("未知");
			log.setSystem("未知");
			log.setTime(time);
			log.setStatus(ManagerLog.error);
			log.setDsc(admin+Code.SELECT_ALL_OUT_TIME);
			logMapper.logSave(log);
			//返回结果
			return "login/login";
		}
		return "systemSettingsManagement/logSelect";
	}
	//到高级日志查询界面
	public String toAdvancedLogSelect(HttpServletRequest req) throws ServletException, IOException {
		ManagerLog log = new ManagerLog(); 		 //创建日志
		String admin = GetAdmin.getAdmin(req);   //获取当前管理员名
		String ip = GetData.getRemoteHost(req);  //获取管理员ip
		String system = GetData.getSystem(req);  //获取管理员操作系统
		Timestamp time = GetData.getTime(); 	 //获取当前系统时间
		//将ip地址，系统，管理员名，当前系统时间存入log中
		log.setIp(ip);
		log.setName(admin);
		log.setSystem(system);
		log.setTime(time);
		//判断当前管理员是否超时
		if(admin == null){
			//保存日志
			log.setIp(ip);
			log.setName("未知");
			log.setSystem("未知");
			log.setTime(time);
			log.setStatus(ManagerLog.error);
			log.setDsc(admin+Code.SELECT_ALL_OUT_TIME);
			logMapper.logSave(log);
			//返回结果
			return "login/login";
		}
		return "systemSettingsManagement/advancedLogSelect";
	}
	//删除日志
	public String toClearLog(HttpServletRequest req) throws ServletException, IOException {
		ManagerLog log = new ManagerLog(); 		 //创建日志
		String admin = GetAdmin.getAdmin(req);   //获取当前管理员名
		String ip = GetData.getRemoteHost(req);  //获取管理员ip
		String system = GetData.getSystem(req);  //获取管理员操作系统
		Timestamp time = GetData.getTime(); 	 //获取当前系统时间
		//将ip地址，系统，管理员名，当前系统时间存入log中
		log.setIp(ip);
		log.setName(admin);
		log.setSystem(system);
		log.setTime(time);
		//判断当前管理员是否超时
		if(admin == null){
			//保存日志
			log.setIp(ip);
			log.setName("未知");
			log.setSystem("未知");
			log.setTime(time);
			log.setStatus(ManagerLog.error);
			log.setDsc(admin+Code.SELECT_ALL_OUT_TIME);
			logMapper.logSave(log);
			//返回结果
			return "login/login";
		}
		return "systemSettingsManagement/clearLogByDate";
	}
	//根据参数返回查询日志界面
	public String toSelectLogByCondition(HttpServletRequest req,String name, String startDate, String endDate,String ip, String index) throws ServletException, IOException {
		ManagerLog log = new ManagerLog(); 		 //创建日志
		String admin = GetAdmin.getAdmin(req);   //获取当前管理员名
		String ip1 = GetData.getRemoteHost(req);  //获取管理员ip
		String system = GetData.getSystem(req);  //获取管理员操作系统
		Timestamp time = GetData.getTime(); 	 //获取当前系统时间
		//将ip地址，系统，管理员名，当前系统时间存入log中
		log.setIp(ip1);
		log.setName(admin);
		log.setSystem(system);
		log.setTime(time);
		//判断当前管理员是否超时
		if(admin == null){
			//保存日志
			log.setIp(ip);
			log.setName("未知");
			log.setSystem("未知");
			log.setTime(time);
			log.setStatus(ManagerLog.error);
			log.setDsc(admin+Code.SELECT_ALL_OUT_TIME);
			logMapper.logSave(log);
			
			//返回结果
			return "login/login";
		}
		try{
			map.put("name", name);
			map.put("startDate", startDate);
			map.put("endDate", endDate);
			map.put("ip", ip);
			List<ManagerLog> list = logMapper.selectLogByCondition(map);
			int indexpage = Integer.valueOf(index);
			//判断index是否为第一次请求
			if(indexpage <0){
				indexpage = 1;
			}
			if(list != null){
				int pagesize = 20;  //定义每页显示的记录数
				//加入查询所有记录方法中
				GetData.selectAll(req, list, pagesize, indexpage,
						"count", "index", "pagesize", "pagecount", "list");
				//将管理员查询成功存入到日志表中
				log.setStatus(ManagerLog.success);
				log.setDsc(admin+"查询系统日志记录"+"："+ManagerLog.success);
				logMapper.logSave(log);
				req.setAttribute("name", name);
				req.setAttribute("startDate", startDate);
				req.setAttribute("endDate", endDate);
				req.setAttribute("ip", ip);
				return "systemSettingsManagement/logSelect";
			}else{
				//将管理员查询失败存入到日志表中
				log.setStatus(ManagerLog.error);
				log.setDsc(admin+"查询系统日志记录"+"：失败!");
				logMapper.logSave(log);
			}
		}catch(Exception e){
			e.printStackTrace();
			log.setStatus(ManagerLog.error);
			log.setDsc(admin+"查询系统日志记录"+"：由于未知异常查询失败!");
			logMapper.logSave(log);
		}
		return "login/login";
	}

	public Result clearLogByDate(HttpServletRequest req, String startDate, String endDate) throws ServletException, IOException {
		Result result = new Result();
		ManagerLog log = new ManagerLog(); 		 //创建日志
		String admin = GetAdmin.getAdmin(req);   //获取当前管理员名
		String ip = GetData.getRemoteHost(req);  //获取管理员ip
		String system = GetData.getSystem(req);  //获取管理员操作系统
		Timestamp time = GetData.getTime(); 	 //获取当前系统时间
		//将ip地址，系统，管理员名，当前系统时间存入log中
		log.setIp(ip);
		log.setName(admin);
		log.setSystem(system);
		log.setTime(time);
		//判断当前管理员是否超时
		if(admin == null){
			//保存日志
			log.setIp(ip);
			log.setName("未知");
			log.setSystem("未知");
			log.setTime(time);
			log.setStatus(ManagerLog.error);
			log.setDsc(admin+Code.SELECT_ALL_OUT_TIME);
			logMapper.logSave(log);
			
			//返回结果
			result.setStatus(-1);
			return result;
		}
		try{
			map.put("startDate", startDate);
			map.put("endDate", endDate);
			Integer num = logMapper.clearLogByDate(map);
			if(num!=0&&num!=null){
				log.setStatus(ManagerLog.success);
				log.setDsc(admin+"删除系统日志记录"+"："+ManagerLog.success);
				logMapper.logSave(log);
				result.setStatus(0);
				return result;
			}
		}catch(Exception e){
			e.printStackTrace();
			log.setStatus(ManagerLog.error);
			log.setDsc(admin+"删除系统日志记录"+"：由于未知异常查询失败!");
			logMapper.logSave(log);
		}
		result.setStatus(1);
		return result;
	}
	
	 /**
	  * 请求代理商操作日志页面
	  */
	public String toAgentLogSelect(HttpServletRequest req) throws ServletException, IOException {
		String admin = GetAdmin.getAdmin(req);  			                   //获取当前管理员名
		GetData getData = (GetData) BeanFactory.getBean(GetData.class);
		//判断当前管理员是否超时
		if(admin == null){
			//保存日志
			getData.saveCurrentLog(req,Code.LOG_AGENTLOGPAGE_OUTTIME,AgentLog.error);
			
			//返回结果
			return "login/login";
		}
		try{
			String index = (String)req.getParameter("index");
			int indexpage = Integer.valueOf(index);
			if(indexpage <0){            //判断index是否为第一次请求
				indexpage = 1;
			}
			List<AgentLog> totalList = agentLogMapper.findAgentLogAllSelect();//获取所有的代理商日子记录
			int count = totalList.size();//获取日志总条数
			int pagesize = 20;           //定义每页显示的记录数
			int pagecount;               //定义总共有多少页面
			List<AgentLog> list = null;  //此次请求页面的记录数
			//判断count的条数是够显示一个页面
			if(count<pagesize){          //总记录数不足显示一个页面
				pagesize = count;        //显示的记录数
				pagecount = 1;           //总页面数为1
				list = totalList.subList(0, count);
			}else{
				if(count%pagesize == 0){
					pagecount = count/pagesize;   //总页面数
					//获取请求页面数的记录
					list = totalList.subList(pagesize*indexpage-pagesize, pagesize*indexpage);
				}else{
					pagecount = count/pagesize+1; //当总页面数不能被pagesize整除时的数目
					/*
					 * 当总页面数不能被pagesize整除时的数目，
					 * 并且请求显示最后一页的数目时
					 */
					if(pagecount == indexpage){
						//取最后一页的记录（不足pagesize条个记录）
						list = totalList.subList(pagesize*indexpage-pagesize, (pagesize*indexpage-pagesize)+(count-pagesize*indexpage+pagesize));
					}else{
						list = totalList.subList(pagesize*indexpage-pagesize, pagesize*indexpage);
					}
				}
			}
			
			/*
			 * 将日志总的条数用req保存
			 * 方便转发到JSP页面取值，和后面请求页面分页显示取值
			 * 注意：count是动态变化的
			 */
			req.setAttribute("count", count);
			req.setAttribute("index", indexpage);  //将传进来的index页面数保存到req中
			req.setAttribute("pagesize", pagesize);  //将每页显示的记录数存入req中
			req.setAttribute("pagecount", pagecount);  //将pagecount用req保存
			req.setAttribute("list", list);  //将此次要显示的记录放入计划list中
			
			//保存成功日志
			getData.saveCurrentLog(req,Code.LOG_SUC_MANAGERPAGE,ManagerLog.success);
			return "systemSettingsManagement/systemAgentLog";
		}catch(Exception e){
			e.printStackTrace();
			//记录异常日志
			getData.saveCurrentLog(req,Code.LOG_EXCEPTION_AGENTLOG,AgentLog.error);
		}
		return "systemSettingsManagement/systemAgentLog";
}
}


















