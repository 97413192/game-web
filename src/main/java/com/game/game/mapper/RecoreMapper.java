package com.game.game.mapper;

import java.util.List;
import java.util.Map;

import com.game.game.model.OrderRecord;

/**
 * 玩家订单sql接口
 * @author Administrator
 *
 */
public interface RecoreMapper {
	/** 根据时间段查询订单记录*/
	List<OrderRecord> findOrderByTime(Map<String,Object> map); 
	/** 查询所有订单记录*/
	List<OrderRecord> findAllOrder(); 
	
	
}
