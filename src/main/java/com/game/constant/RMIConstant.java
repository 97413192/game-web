package com.game.constant;

public class RMIConstant {
	
	/**
	 * 新登录服的
	 */
	/**接收*/
	public static final int GAME_SERVER_REGISTER = 100001;             //接收游戏服务器注册
	public static final int GAME_VALIDATE_UID_SERVERID = 100002;	   //接收游戏服验证登陆
	public static final int GAME_RMI_JOIN = 100003; //验证RMI连接
	
	
	/**请求*/
	public static final int GAME_SERVER_TIME_UPDATE = 300004;          //游戏服务器定时更新消息
	public static final int GAME_SERVER_STOP = 300005; //游戏服务器关闭
	public static final int GAME_SERVER_PLAYER_OFFLINE = 300006; //游戏服务器玩家下线
	public static final int GAME_SERVER_GET_IMEI = 300007; //getIMEI
	public static final int GAME_SERVER_UPDATE_DATE = 300008; //更新服务器时间
	public static final int GAME_SERVER_REG_NEW_USER_STATE = 300009; //获得新用户注册状态
	public static final int GAME_SERVER_GET_MAX_ROLE = 300010; //获得新用户注册状态
	
	/**
	 *对GM工具提供的CODE码 
	 */
	public static final int GM_TOOL_GET_DISTRICT = 400001;			//查询District表所有          
	public static final int GM_TOOL_UPDATE_DISTRICT = 400002;		//修改District表
	public static final int GM_TOOL_GET_DISTRICT_BY_ID = 400003;	//查询District表根据ID
	public static final int GM_TOOL_ADD_DISTRICT = 400004;			//增加District
	
	/**房间模块*/
	public static final int GM_TOOL_GET_ROOM_LIST = 400101;			//查询所有房间信息
	/**桌子模块*/
	public static final int GM_TOOL_GET_DESK_LIST = 400201;			//查询所有桌子信息        
	
	//操作房卡
	public static final int GM_TOOL_CHARGE_TREASURE = 405001;	//GM工具修改砖石
	//public static final int GM_TOOL_FIND_PLAYER = 410002;		//GM工具查询玩家信息
	public static final int GM_TOOL_FIND_PLAYER = 405002;		//GM工具查询玩家信息
	//public static final int GM_TOOL_FIND_PLAYERS = 410003;		//GM工具分页查询玩家信息
	public static final int GM_TOOL_FIND_PLAYERS = 405003;		//GM工具分页查询玩家信息
	
	public static final int GM_TOOL_RECIVE_TELEPHONE = 405050; // GM工具发送玩家手机，代理，地区
	
	public static final int GM_TOOL_FIND_ROOMCARD_START = 410004;	//GM工具查询注册玩家的初始值
	public static final int GM_TOOL_ALERT_ROOMCARD_START = 410005;	//GM工具修改注册玩家的初始值
	public static final int GM_TOOL_PLAYER_Binding = 410055;	//告诉服务器玩家绑定成功了
	public static final int GM_TOOL_FIND_SHARECONFIG = 410056;	//查询分享配置
	public static final int GM_TOOL_UPDATE_SHARECONFIG = 410057;	//查询分享配置
	public static final int GM_TOOL_FIND_REDEMPTION= 410015;	//查询商品兑换配置
	public static final int GM_TOOL_DEL_REDEMPTION= 410016;	//根据ID删除某一个商品兑换配置
	public static final int GM_TOOL_ADD_REDEMPTION= 410017;	//根据ID增加某一个商品兑换配置
	public static final int GM_TOOL_UPDATE_REDEMPTION= 410018;	//查询商品兑换配置
	
	//操作分享奖励(元宝)
	public static final int GM_TOOL_FIND_SHARE_PRIZE=420001;//GM工具查找分享奖励元宝
	public static final int GM_TOOL_UPDATE_SHARE_PRIZE=420002;//GM工具修改分享奖励元宝
	public static final int GM_TOOL_INSERT_SHARE_PRIZE=420003;//GM工具修改分享奖励元宝
	
	/**查询绑定玩家信息*/
	public static final int GM_TOOL_SELECT_BINDED_PLAYER=100011;//GM工具查询绑定玩家信息
	
	
	
	
	/**游戏服务器状态--服务器异常*/
	public static final int GAME_SERVER_STATE_STOP = 0;
	/**游戏服务器状态--测试*/
	public static final int GAME_SERVER_STATE_MAINTAIN = 1;
	/**游戏服务器状态--开放*/
	public static final int GAME_SERVER_STATE_OPEN = 2;
	/**游戏服务器状态--关闭*/
	public static final int GAME_SERVER_STATE_CLOSE = 3;
	
	
	/**游戏服务器最大能支持注册量*/
	public static final int GAME_SERVER_MAX_REGISTER_NUMBER = 20000;
	
	
	/**超端接口*/
	public static final int GD_TOOL_GET_ROOM_LIST = 410014;	  //查询所有房间信息
	/**充值服务器获取当前所有游戏服*/
	public static final int PAYMENR_SERVER_GET_ALL_GAME_INFO = 500001;
	
	/**充值服务器获取当前所有游戏服*/
	public static final int SELECT_PLAYER_MONERY = 410010; //查询玩家的金额
	/** 修改玩家为超端*/
	public static final int GM_TOOL_CHANGE_PLAYER_SUPER = 405014; 
	
}
