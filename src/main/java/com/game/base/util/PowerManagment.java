package com.game.base.util;

import com.game.business.mapper.AgentMapper;
import com.game.business.mapper.MgerMapper;
import com.game.business.model.Agent;
import com.game.business.model.manager.Manager;

/**
 * 权限管理
 * 超级管理员与普通管理员
 * 管理员与代理商
 * 代理商的层级
 * @author Administrator
 *
 */
public class PowerManagment {
	
	/** 管理员sql接口*/
	private static MgerMapper mgerMapper = (MgerMapper) BeanFactory.getBean(MgerMapper.class);
	
	/** 代理商sql接口*/
	private static AgentMapper agentMapper = (AgentMapper) BeanFactory.getBean(AgentMapper.class);
	
	/**
	 * 前提为已经确定admin是管理员了
	 * 超级管理和普通管理
	 * 返回true,为超级管理员
	 * @param admin
	 * @return
	 */
	public static boolean checkManagmentCategory(String admin){
		Manager mg = mgerMapper.findByName(admin);
		try{
			if(mg.getCategory() == 1){
				return true;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return false;
	}
	
	/**
	 * 查询是管理员还是代理商
	 * 1表示管理员,2表示代理商,-1操作者不存在
	 * @param admin
	 * @return
	 */
	public static Integer checkOperCategory(String admin){
		//管理员
		Manager mg = mgerMapper.findByName(admin);
		//代理商
		Agent agent = agentMapper.findAgentByName(admin);
		try{
			if(mg != null && agent == null){
				return 1;
			}else if(mg == null && agent != null){
				return 2;
			}else{
				return 3;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return 3;
	}
	
	/**
	 * 查询是几级代理商
	 * 1表示1级,2表示2级,3表示3级,-1表示代理商不存在,-2表示查询代理商层级出现异常
	 * @param admin
	 * @return
	 */
	public static Integer checkAgentCategory(String admin){
		//代理商
		Agent agent = agentMapper.findAgentByName(admin);
		
		try{
			if(agent == null){
				return -1;
			}
			return agent.getCategory();
		}catch(Exception e){
			e.printStackTrace();
		}
		return -2;
	}
}





