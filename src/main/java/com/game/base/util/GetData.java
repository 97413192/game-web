package com.game.base.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import com.game.business.mapper.AgentLogMapper;
import com.game.business.mapper.AgentMapper;
import com.game.business.mapper.LogMapper;
import com.game.business.mapper.MgerMapper;
import com.game.business.model.Agent;
import com.game.business.model.AgentLog;
import com.game.business.model.manager.Manager;
import com.game.business.model.manager.ManagerLog;


/** 
 * <li>ClassName:GetIP <br/> 
 * <li>@Description: ip相关操作
 * <li>@Date:     2016年10月26日 <br/> 
 * <li>@author   周强      
 */
@Component
public class GetData {
	@Resource
	private MgerMapper mgMapper;
	@Resource
	private LogMapper logMapper;
	@Resource
	private AgentMapper agentMapper;
	@Resource
	private AgentLogMapper agentLogMapper;
	
	/** 
	 * <li>@Description:获取当前管理员好ip地址
	 * <li>@return ip字符串
	 * <li>创建人：周强
	 * <li>创建时间：2016年10月26日
	 * <li>修改人：
	 * <li>修改时间：
	 */  
	public static String getRemoteHost(javax.servlet.http.HttpServletRequest request){
	    String ip = request.getHeader("x-forwarded-for");
	    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
	        ip = request.getHeader("Proxy-Client-IP");
	    }
	    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
	        ip = request.getHeader("WL-Proxy-Client-IP");
	    }
	    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
	        ip = request.getRemoteAddr();
	    }
	    return ip.equals("0:0:0:0:0:0:0:1")?"127.0.0.1":ip;
	}
	/** 
	 * <li>@Description:获取当前管理员好操作系统
	 * <li>@return system字符串
	 * <li>创建人：周强
	 * <li>创建时间：2016年11月8日
	 * <li>修改人：
	 * <li>修改时间：
	 * @throws UnsupportedEncodingException 
	 */
	public static String getSystem(javax.servlet.http.HttpServletRequest req) throws UnsupportedEncodingException{
		req.setCharacterEncoding("utf-8");
		HttpSession session = req.getSession();
		return (String)session.getAttribute("system");
	}
	/** 
	 * <li>@Description:获取当前系统时间
	 * <li>@return Timestamp
	 * <li>创建人：周强
	 * <li>创建时间：2016年11月8日
	 * <li>修改人：
	 * <li>修改时间：
	 */
	public static Timestamp getTime(){
		return new Timestamp(System.currentTimeMillis());
	}
	/** 
	 * <li>@Description:查询所有记录
	 * <li>@return Timestamp
	 * <li>创建人：周强
	 * <li>创建时间：2016年11月8日
	 * <li>修改人：
	 * <li>修改时间：
	 */
	public static<T> void selectAll(HttpServletRequest req,List<T> totalList,int pagesize,int indexpage,
			String objCount,String objIndex,String objPagesize,String objPagecount,String objList){
		int count = totalList.size();  //获取玩家总个数
		pagesize = 20;  //定义每页显示的记录数
		int pagecount;  //定义总共有多少页面
		List<T> list = null; //此次请求页面的记录数
		
		//判断count的条数是够显示一个页面
		if(count<pagesize){ //总记录数不足显示一个页面，第一页的显示的记录为count条
			pagesize = count;  //显示的记录数
			pagecount = 1;  //总页面数为1
			list = totalList.subList(0, count);
		}else{
			if(count%pagesize == 0){  //判断总记录数是否能被每页显示的条数整除
				pagecount = count/pagesize;  //总页面数
				//每页显示记录数
				list = totalList.subList(pagesize*indexpage-pagesize, pagesize*indexpage);
			}else{
				pagecount = count/pagesize+1; //当总页面数不能被pagesize整除时的：总页面数
				/*
				 * 当总页面数不能被pagesize整除时的数目，
				 * 并且请求显示最后一页的数目时
				 */
				if(pagecount == indexpage){
					//最后一页显示的记录（不足pagesize条个记录）
					list = totalList.subList(pagesize*indexpage-pagesize, (pagesize*indexpage-pagesize)+(count-pagesize*indexpage+pagesize));
				}else{
					//非最后一页显示的记录数
					list = totalList.subList(pagesize*indexpage-pagesize, pagesize*indexpage);
				}
			}
		}
		
		/*
		 * 将日志总的条数用req保存
		 * 方便转发到JSP页面取值，和后面请求页面分页显示取值
		 * 注意：count是动态变化的
		 */
		
//		System.out.println(objCount+":"+count);
//		System.out.println(objIndex+":"+indexpage);
//		System.out.println(objPagesize+":"+pagesize);
//		System.out.println(objPagecount+":"+pagecount);
//		System.out.println(objList+":"+list);
		
		
		
		req.setAttribute(objCount, count);
		req.setAttribute(objIndex, indexpage);  //将传进来的index页面数保存到req中
		req.setAttribute(objPagesize, pagesize);  //将每页显示的记录数存入req中
		req.setAttribute(objPagecount, pagecount);  //将pagecount用req保存
		req.setAttribute(objList, list);  //将此次要显示的记录放入计划list中
	}
	
	/**
	 * 当前管理员或者代理商的操作日志保存
	 * @param req
	 * @param massage 操作描述
	 * @param status 成功或者失败
	 * @throws ServletException
	 * @throws IOException
	 */
	public void saveCurrentLog(HttpServletRequest req,String massage,String status) throws ServletException, IOException{
		ManagerLog log = new ManagerLog(); 		 //创建日志实体类(超级管理员)
		AgentLog al=new AgentLog();              //创建代理商实体类
		String admin = GetAdmin.getAdmin(req);   //获取当前操作名
		String system = GetData.getSystem(req);  //获取管理员操作系统
		String ip = GetData.getRemoteHost(req);  //获取管理员ip
		Timestamp time = GetData.getTime(); //获取当前系统时间
		
		//System.out.println("admin:"+admin);
		//通过管理员名得到管理员具体信息,并验证管理员是否存在
		Manager manager = mgMapper.findByName(admin);
		//System.out.println(manager);
		//通过名字得到代理商信息,并验证代理商是否存在
		Agent agent = agentMapper.findAgentByName(admin);
		if(manager != null){
			log.setStatus(status);
			log.setIp(ip);
			if(admin != null){
				log.setName(admin);
			}
			if(system != null){
				log.setSystem(system);
			}
			log.setTime(time);
			log.setDsc(massage);
			logMapper.logSave(log);
		}else if(agent != null){
			al.setStatus(status);
			al.setIp(ip);
			if(admin != null){
				al.setName(admin);
			}
			if(system != null){
				al.setSystem(system);
			}
			al.setTime(time);
			al.setDsc(massage);
			agentLogMapper.agentLogSave(al);
		}else{
			//throw new NullPointerException();
			log.setStatus(status);
			log.setIp(ip);
			log.setName("无用户");
			log.setSystem("未知");
			log.setTime(time);
			log.setDsc(massage);
			logMapper.logSave(log);
			//代理商日志异常
			al.setStatus(status);
			al.setIp(ip);
			al.setName("未知或者代理商登录未操作时间超时");
			al.setSystem("未知");
			al.setTime(time);
			al.setDsc(massage);
			agentLogMapper.agentLogSave(al);
		}
	}
	
	
}
