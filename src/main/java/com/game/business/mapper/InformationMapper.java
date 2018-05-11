package com.game.business.mapper;

import java.util.List;

import com.game.business.model.Information;

/**
 * 消息业务接口
 * @author Administrator
 *
 */
public interface InformationMapper {
	/** 增加消息记录*/
	int save(Information information);
	/** 查询所有消息记录  */
	List<Information> selectAllInformation();
	/** 通过id查询消息*/
	Information selectById(Integer id);
	/** 通过id修改消息内容*/
	int updateById(Information information);
	/** 通过id删除消息记录*/
	int deleteById(Integer id);
	
}
