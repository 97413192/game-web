package com.game.business.mapper;

import java.util.List;
import java.util.Map;


import com.game.business.model.Agent;
import com.game.business.model.AgentLog;

/**
 * 代理商接口
 * @author 张洁
 *
 */
public interface AgentMapper {
	//代理商列表
	List<Agent> findAllAgentMessage();
	//根据用户名查找代理用户
	Agent findAgentByName(String name);
	//根据级别查询所有代理
	List<Agent> findAllStairAgent(Map<String, Object> map);
	//根据新信息修改代理商信息
	Integer operatinAgent(Map<String, Object> map);
//	//根据用户名删除代理用户
//	void deleteAgent(String account);
	//根据用户名删除代理用户以后修改其次级代理的上级代理
	int updateSecondaryAgent(Map<String,Object> map);
	//当前用户的所有次级代理
	int countTotalByAccount(String account);
	//修改上次登录时间,ip,登录次数
	int updateByName(Agent agent);
	//增加代理
	Integer addAgent(Map<String, Object> map);
	//根据账户修改下级代理数量
	void updateLowAgentNumByAccount(String admin,Integer num);
	//根据修改信息修改代理商信息
	void updateRoomCardByAgent(Agent agent);
	//查询所有代理日志
	List<AgentLog> findAllAgentLog(Map<String, Object> map);
	//根据日期删除代理日志
	Integer deleteAgentLogByDate(Map<String, Object> map);
	/** 根据代理商id查询代理商信息*/
	Agent findAgentById(Integer userID);
	
	/** 查询所有代理商*/
	List<Agent> findAllAgent();
	
	/** 通过上级代理查询代理商*/
	List<Agent> findManyAgentByName(String name);
	
	/** 通过id修改代理商*/
	int alertAgentById(Agent agent);
	
	/** 增加代理商*/
	int saveAgent(Agent agent);
	
	/** 删除代理商*/
	int deleteAgent(Agent agent);
	
	void updateLowerPlayerNum(Agent agent);
}
















