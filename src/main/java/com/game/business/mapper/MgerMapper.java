package com.game.business.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.game.business.model.manager.Manager;
/** 
 * <li>ClassName:ManagerMapper <br/> 
 * <li>@Description:  登陆持久层接口
 * <li>@Date:     2016年10月25日 <br/> 
 * <li>@author   周强      
 */
@Repository
public interface MgerMapper {
	//注册管理员
	int mgerSave(Manager manager);
	//管理员登陆
	Manager findByName(String name);
	//修改管理员密码
	int aditPwd(Manager manager);
	//修改管理员参数
	int update(Manager manager);
	//删除管理员
	int deleteByName(String name);
	//获取所有管理员
	List<Manager> selectAllManager();
	//修改最后一次登录ip,登录次数
	int updateByName(Manager manager);
	//修改管理员参数包括状态
	int updateAndStatus(Manager manager);
	
	
}
