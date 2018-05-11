package com.game.business.service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import com.game.base.util.Result;

/**
 * <li>@ClassName: 充值管理业务层接口
 * <li>@author 周强
 * <li>@date 2016年11月18日
 */
public interface RechargeService {
	/** 根据条件查询多条充值记录*/
	public String toSelectManyRechargeRecord(HttpServletRequest req)throws ServletException, IOException;
	
	
	
	
	
	
	
	
	
	
	//查询钻石兑换金币比例
	public String selectConverScale(HttpServletRequest req)throws ServletException, IOException;
	//修改钻石兑换金币比例
	public Result alterConvertScale(HttpServletRequest req,int convertScale)throws ServletException, IOException;
	//查询所有充值档次
	public String selectAllLevel(HttpServletRequest req)throws ServletException, IOException;
	//修改充值档次
	public Result alterLevel(HttpServletRequest req,int id,int levelId,int money,int jewel)
			throws ServletException, IOException;
	//请求增加充值档次页面
	public String toAddLevel(HttpServletRequest req)throws ServletException, IOException;
	//增加充值档次
	public Result addLevel(HttpServletRequest req,int levelId,int money,int jewel)
			throws ServletException, IOException;
	
}
