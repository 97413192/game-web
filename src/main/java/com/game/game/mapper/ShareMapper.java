package com.game.game.mapper;

import java.util.List;

import com.game.business.model.Share;


/** 
 * <li>ClassName:ShareMapper <br/> 
 * <li>@Description:  持久层代理商接口
 * <li>@Date:     2017年4月25日 <br/> 
 * <li>@author   牟彦璋      
 */
public interface ShareMapper {
	
	//查询所有分享奖励
	List<Share> findShare();
	Share selectShare(Integer id);
	void updateNumber(Share share);
}
