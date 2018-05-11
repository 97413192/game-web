package com.game.business.mapper;

import java.util.List;
import java.util.Map;

import com.game.business.model.manager.ManagerLog;
/** 
 * <li>ClassName:ManagerMapper <br/> 
 * <li>@Description:  持久层日志接口
 * <li>@Date:     2016年10月28日 <br/> 
 * <li>@author   周强      
 */
public interface LogMapper {
	//添加管理员日志
	void logSave(ManagerLog log);
	//查询所有的系统日志
	List<ManagerLog> findLogByName();
	//根据条件查询系统日志
	List<ManagerLog> selectLogByCondition(Map<String, Object> map);
	//根据日期删除系统日志
	Integer clearLogByDate(Map<String, Object> map);
}
