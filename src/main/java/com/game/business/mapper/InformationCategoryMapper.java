package com.game.business.mapper;

import java.util.List;

import com.game.business.model.Information;

/**
 * 消息业务接口
 * @author Administrator
 *
 */
public interface InformationCategoryMapper {
	/** 增加消息记录*/
	int save(Information information);
	/** 查询所有消息记录  */
	List<Information> selectAllInformation();
	/** 通过类别查询消息*/
	List<Information> selectByCategory(Integer category);
	/** 通过类别修改消息内容*/
	int updateByCategory(Information information);
	
}
