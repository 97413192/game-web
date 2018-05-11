package com.game.business.service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import com.game.base.util.Result;

/**
 * <li>@ClassName: 比倍业务层接口
 * <li>@author 周强
 * <li>@date 2016年11月16日
 */
public interface DoubleService {
	//查询所有比倍
	public String selectAllDouble(HttpServletRequest req)throws ServletException, IOException;
	//修改获胜概率
	public Result alterDouble(HttpServletRequest req,int chance,int id)throws ServletException, IOException;
}
