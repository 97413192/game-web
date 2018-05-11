package com.game.business.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.game.business.model.AgentDeal;

public interface AgentDealMapper {

	List<AgentDeal> findAllAgentDeal(@Param(value="account")String account);
	//保存交易记录信息
	Integer insertAgentDealRecord(Map<String, Object> map);
	//代理交易记录查询，根据条件查出信息
	List<AgentDeal> findAllAgentDealByCondition(Map<String, Object> map);
	
	/** 添加交易记录*/
	Integer insertAgentDeal(AgentDeal agentDeal);
}
