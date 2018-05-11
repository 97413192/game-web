package com.game.business.mapper;

import java.util.List;

import com.game.business.model.Share;

/**
 * 
 * @author 杨浩零
 *  分享奖励操作
 */
public interface PlayerShareInfoMapper {

	    /**
	     * 
 	     * @return 查询所有的分享记录
	     */
		List<Share> findShare();
		/**
		 *  根据游戏Id 查询分享记录
		 * @param gameId  == 》 游戏Id
		 * @return 分享记录
		 */
		Share selectShare(Integer gameId);
		/**
		 * 更新游戏分享状态
		 * @param share
		 */
		void updateNumber(Share share);
		/**
		 * 插入一条分享记录
		 * @param share
		 * @return 刚插入数据的主键Id
		 */
		Integer insertShare(Share share);
		/**
		 * 定时任务，每天0点删除每个玩家的分享记录
		 */
		void removeShareRecord();
}
