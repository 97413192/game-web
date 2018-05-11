package com.game.game.hallMapper;

import java.util.List;

import com.game.business.model.PromoCode;

public interface PromoCode1Mapper {
	
	/**通过玩家id查询当月总充值金额*/
	int selectNowMonthPay(Integer gameId);
}
