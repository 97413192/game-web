package com.game.business.service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import com.game.base.util.Result;

/**
 * <li>@ClassName: 留桌管理业务层接口
 * <li>@author 周强
 * <li>@date 2016年11月15日
 */
public interface WaitDeskService {
	//请求增加留桌查看配置页面
	public String toAddWaitDesk(HttpServletRequest req)throws ServletException, IOException;
	//增加留桌查看配置
	public Result addWaitDesk(HttpServletRequest req,int time,int gold,String typeName,int typeId)
			throws ServletException, IOException;
	//请求查看所有留桌查看设置
	public String selectAllWaitDesk(HttpServletRequest req,String WaitDeskIndex)throws ServletException, IOException;
	//请求修改留桌参看页面
	public String toAlterWaitDesk(HttpServletRequest req,int id)throws ServletException, IOException;
	//修改留桌查看配置
	public Result alterWaitDesk(HttpServletRequest req,int id,int time,int gold,String typeName,int typeId)
			throws ServletException, IOException;
	//通过查询所有的增加到已有的typeName或者typeId的留桌查看设置的增加页面
	public String toAllWaitDesk_selectAll(HttpServletRequest req,String typeName,int typeId)
			throws ServletException, IOException;
}
