package com.game.business.service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import com.game.base.util.Result;

/**
 * <li>@ClassName: 活动奖励管理业务层接口
 * <li>@author 周强
 * <li>@date 2016年11月17日
 */
public interface ActionWardService {
	//查询所有活动奖励
	public String selectAllActionWard(HttpServletRequest req)throws ServletException, IOException;
	//查询分享奖励
	public String selectShareWard(HttpServletRequest req)throws ServletException, IOException;
	//修改活动奖励
	public Result alterActionWard(HttpServletRequest req,int id,String wardName,int gold) 
			throws ServletException, IOException;
	//请求修改分享奖励页面
	public String updatePrize(HttpServletRequest req) 
			throws ServletException, IOException;
	//修改分享奖励
	public Result toUpdatePrize(HttpServletRequest req,int id,int prize) 
			throws ServletException, IOException;
}
