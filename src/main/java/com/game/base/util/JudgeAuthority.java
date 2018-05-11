package com.game.base.util;

import java.util.HashMap;
import java.util.Map;

import com.game.business.mapper.AgentMapper;
import com.game.business.mapper.MgerMapper;
import com.game.business.model.Agent;
import com.game.business.model.manager.Manager;

/**
 * 用来判断权限（级别）
 * @author z
 *
 */
public class JudgeAuthority {
	public static String freeze = "2";
	public static String onLine = "1";
	public static String offLine = "3";
	public static String sell = "出售";
	public static String sysPresent = "系统赠送";
	//初始化密码
	public static String password = "123456";
	
	
	public static Map<String,Object> judgeAuthority(MgerMapper mgerMapper,AgentMapper agentMapper,String admin){
		Map<String,Object> map = new HashMap<String,Object>();
		//获取当前操作管理员或者代理
		Manager nowMg = mgerMapper.findByName(admin);
		Agent agent =  agentMapper.findAgentByName(admin);
		if(nowMg == null && agent == null){
			return null;
		}
		if(nowMg != null && agent !=null){
	
			return null;
		}
		if(nowMg != null && agent == null){
			String higherAgent = null;
			Integer mark = 1;
			map.put("higherAgent", higherAgent);
			map.put("mark", mark);
			map.put("judge", "admin");
			return map;
		}
		if(nowMg == null && agent != null){
			String higherAgent = agent.getAccount();
			Integer mark = null;
			map.put("higherAgent", higherAgent);
			map.put("mark", mark);
			map.put("judge","agent");
			return map;
		}
	
		return null;
	}
	
}
