package com.game.business.service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import com.game.base.util.Result;
/**
 * <li>@ClassName: 游戏桌子业务层接口
 * <li>@author 周强
 * <li>@date 2016年11月14日
 */
public interface GameDeskService {
	//请求增加桌子页面
	public String toAddDesk(HttpServletRequest req)throws ServletException, IOException;
	//通过游戏id获取房间名
	public Result getRoom(HttpServletRequest req,String gameId)throws ServletException, IOException;
	//增加桌子
	public Result addRoom(HttpServletRequest req,String gameId,String roomId,String maxGoldStock,String curGoldStock)
			throws ServletException, IOException;
	//请求查询桌子页面
	public String toSelectDesk(HttpServletRequest req)throws ServletException, IOException;
	//请求查询桌子参数
	public Result selectDesk(HttpServletRequest req,String gameId,String roomId,String id)
			throws ServletException, IOException;
	//查询所有桌子参数
	public String selectAllDesk(HttpServletRequest req)throws ServletException, IOException;
	//获取修改桌子页面
	public String toAlterDesk(HttpServletRequest req)throws ServletException, IOException;
	//修改桌子参数
	public Result alterDesk(HttpServletRequest req,int gameid,int roomid,int id,int maxGoldStock,int curGoldStock)
			throws ServletException, IOException;
}
