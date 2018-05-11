package com.game.business.mapper;

import java.util.List;

import com.game.business.model.PromoCode;

public interface PromoCodeMapper {
	/** 通过玩家id查询promoCode*/
	PromoCode selectByGameId(Integer gameId);
	/** 保存promoCode*/
	int save(PromoCode promoCode);
	/** 查询所有绑定玩家信息*/
	List<PromoCode> selectAllBindedPlayers();
	/** 通过代理商名查询promoCode*/
	List<PromoCode> selectByAccount(String account);
	/** 通过代理商id查询promoCode*/
	List<PromoCode> selectByAccount1(Integer account);
	/** 通过代理商名修改promoCode*/
	int update(PromoCode promoCode);
	/** 通过代理商名查询下级玩家个数*/
	int findPlayerCountbyAgent(String account);
	/** 通过玩家id修改promoCode*/
	void updateALL(PromoCode promoCode);
	/**
	 * 更新绑定状态
	 * @param account
	 * @param gameId
	 */
	void unbindPlayer(PromoCode promoCode);
	/**
	 * 通过代理商查询promocode 绑定状态 
	 * @param account
	 * @return
	 */
	List<PromoCode> selectByAccountAndBindState(String account);
}
