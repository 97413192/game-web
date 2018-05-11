package com.game.business.mapper;

import java.util.List;

import com.game.business.model.PlayerTradeRecode;

/**
 * 玩家交易记录操作数据库接口
 * @author Administrator
 *
 */
public interface PlayerTradeRecordMapper {

	/** 查询所有玩家交易记录*/
	List<PlayerTradeRecode> selectAllRecord();
	/** 保存消息记录*/
	int save(PlayerTradeRecode playerTradeRecode);
}
