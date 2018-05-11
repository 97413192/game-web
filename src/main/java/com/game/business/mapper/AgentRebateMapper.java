package com.game.business.mapper;

import java.util.List;

import com.game.business.model.AgentRebate;


public interface AgentRebateMapper {

 List<AgentRebate> selectAll();
 
 int update(AgentRebate ar);
 
 AgentRebate selectOne(int as);
}
