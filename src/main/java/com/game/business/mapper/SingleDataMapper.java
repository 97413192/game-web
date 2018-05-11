package com.game.business.mapper;

import java.util.Map;


public interface SingleDataMapper {
	
	int update(Map map); 
	
	int selectBy(int type);

	void updateNum(int num);

	int selectById(int i);

}
