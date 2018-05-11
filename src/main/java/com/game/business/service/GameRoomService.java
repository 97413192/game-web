package com.game.business.service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestBody;

import com.game.base.util.Result;
import com.game.game.model.Room;
/** 
 * <li>ClassName:GameRoomService <br/> 
 * <li>@Description: 房间管理业务接口
 * <li>@Date:     2016年11月10日 <br/> 
 * <li>@author   周强      
 */
public interface GameRoomService {
	//请求增加房间页面
	public String toAddGameRoom(HttpServletRequest req)throws ServletException, IOException;
	//增加房间
	public Result addGameRoom(HttpServletRequest req,Room room)throws ServletException, IOException;
	//请求显示所有房间信息
	public String toSelectAllRoom(HttpServletRequest req)throws ServletException, IOException;
	//通过查询所有房间链接请求修改游戏房间页面
	public String toAlterGameRoom(HttpServletRequest req)throws ServletException, IOException;
	//修改房间
	public Result alterGameRoom(HttpServletRequest req,Room room,String oldname)throws ServletException, IOException;
	//查询房间信息
	public Result selectRoom(HttpServletRequest req,int gameId,String oldName)throws ServletException, IOException;
}
