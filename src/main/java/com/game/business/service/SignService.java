package com.game.business.service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import com.game.base.util.Result;

/**
 * <li>@ClassName: 签到管理业务层接口
 * <li>@author 周强
 * <li>@date 2016年11月17日
 */
public interface SignService {
	//查询所有签到
	public String selectAllSign(HttpServletRequest req)throws ServletException, IOException;
	//修改签到金币
	public Result alterSign(HttpServletRequest req,int gold,int id)throws ServletException, IOException;
}
