package com.game.constant;

/**
 * RMI常量
 * @author QiHan
 *
 */

public class LoginRMIConstant {	
	/**
	 * gm tools
	 */
	public static final int GM_TOOL_RMI_JOIN = 400000;
	/**
	 * 区表操作
	 */
	
	public static final int GM_TOOL_DISTRICT = 400000;
	public static final int GM_TOOL_DISTRICT_SELECT = GM_TOOL_DISTRICT + 1;			//查询District表所有          
	public static final int GM_TOOL_DISTRICT_UPDATE = GM_TOOL_DISTRICT + 2;		//修改District表
	public static final int GM_TOOL_DISTRICT_SELECT_BY_ID = GM_TOOL_DISTRICT + 3;	//查询District表根据ID
	public static final int GM_TOOL_DISTRICT_INSERT = GM_TOOL_DISTRICT + 4;			//增加District

	
	/**
	 * 用户表操作
	 */
	public static final int GM_TOOL_USR = 400100;
	public static final int GM_TOOL_USR_INSERT = GM_TOOL_USR + 1; //增加Usr 参数Usr usr
	public static final int GM_TOOL_USR_DELETE = GM_TOOL_USR + 2; //删除Usr 没有实现
	public static final int GM_TOOL_USR_UPDATE = GM_TOOL_USR + 3; //修改Usr 参数Usr usr
	public static final int GM_TOOL_USR_SELECT = GM_TOOL_USR + 4; //查询Usr 参数无 返回List<Usr>
	public static final int GM_TOOL_USR_INSERT_BY_NAME_PWD = GM_TOOL_USR + 5; //以玩家账号和密码生成账号 String name String pwd 
	public static final int GM_TOOL_USR_UPDATE_BY_NAME_PWD = GM_TOOL_USR + 6; //以玩家账号和密码更新账号 String name String pwd
	public static final int GM_TOOL_USR_SELECT_BY_NAME = GM_TOOL_USR + 7; //查询Usr通过用户名 String name 返回Usr
	public static final int GM_TOOL_LIST_USR_SELECT_BY_NAME = GM_TOOL_USR + 8; //查询用户列表 String name 返回List<Usr> 模糊查询
	
	
	/**游戏房间操作*/
	//请求所有房间操作
	public static final int GM_TOOL_GET_ROOM_LIST = 400101;	  //查询所有房间信息
	//增加房间
	public static final int GM_TOOL_ADD_ROOM = 400102;			//增加room
	public static final int GM_TOOL_ADD_ROOM_PARAMERROR = 400103;  //传过领导房间名为空错误
	public static final int GM_TOOL_ADD_ROOM_ERROR = 400104;  //增加房间失败
	public static final int GM_TOOL_GET_GAME_LIST = 400105;   //请求所有游戏
	public static final int GM_TOOL_GET_GAME_LIST_ERROR = 400106;   //未获得游戏
	
	
}
