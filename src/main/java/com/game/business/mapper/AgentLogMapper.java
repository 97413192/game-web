package com.game.business.mapper;

import java.util.List;

import com.game.business.model.Agent;
import com.game.business.model.AgentLog;
import com.game.business.model.manager.ManagerLog;

/** 
 * <li>ClassName:ManagerMapper <br/> 
 * <li>@Description:  持久层代理商接口
 * <li>@Date:     2017年2月22日 <br/> 
 * <li>@author   周永财      
 */
public interface AgentLogMapper {
	//添加代理商日志
	void agentLogSave(AgentLog agentLog);
	//查询所有代理商日志
	List<AgentLog> findAgentLogAllSelect();
}
