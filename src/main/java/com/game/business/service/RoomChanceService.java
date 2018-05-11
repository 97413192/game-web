package com.game.business.service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import com.game.base.util.Result;
/**
 * <li>@ClassName: 房间概率管理业务层接口
 * <li>@author 周强
 * <li>@date 2016年11月21日
 */
public interface RoomChanceService {
	//查询所有桌子概率
	public String selectAllRoomChance(HttpServletRequest req)throws ServletException, IOException;
	//请求修改房间概率页面
	public String toAlterRoomChance(HttpServletRequest req)throws ServletException, IOException;
	//修改房间概率
	public Result alterRoomChance(HttpServletRequest req,int id,int K5,int RS,int SF,int K4,int FH,int FL,int ST,int K3,int P2,int P1,int NP,int top)
			throws ServletException, IOException;
}
