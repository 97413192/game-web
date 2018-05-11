package com.game.business.service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import com.game.base.util.Result;

/**
 * <li>@ClassName: 登陆奖励管理业务层接口
 * <li>@author 周强
 * <li>@date 2016年11月17日
 */
public interface LandingWardService {
	//查询所有登陆奖励
	public String selectAllLandingWard(HttpServletRequest req)throws ServletException, IOException;
	//修改登陆奖励金币和概率
	public Result alterLandingWard(HttpServletRequest req,int id,int gold,int chance) throws ServletException, IOException;
}
