package com.game.base.util;

public class Code {
	//修改管理员密码
	public static final String ALTER_SUC_MANAGERPWD = "修改管理员密码：成功！";
	public static final String ALTER_PWDERROR_MANAGERPWD = "修改管理员密码：由于密码错误修改失败！";
	public static final String ALTER_ERROR_MANAGERPWD = "修改管理员密码：失败！";
	public static final String ALTER_EXCEPTION_MANAGERPWD = "修改管理员密码：由于未知异常修改失败！";
	public static final String ALTER_MANAGERPWD_OUTTIME = "修改管理员密码：管理员存在session超时或者没有登录";
	//请求管理中心页面
	public static final String SELECT_SUC_MANAGEMENTPAGE = "请求管理中心页面：成功！";
	public static final String SELECT_MANAGEMENTPAGE_OUTTIME = "请求管理中心页面：由于session超时或者没有登录";
	//请求登陆
	public static final String REQUEST_SUC_LOGIN = "请求登陆：成功！";
	public static final String REQUEST_NOUSER_LOGIN = "没找到用户：失败！";
	//请求修改管理员密码页面
	public static final String ALTER_SUC_MANAGERPWDPAGE = "请求修改管理员页面：成功！";
	public static final String ALTER_MANAGERPWDPAGE_OUTTIME = "请求修改管理员页面：由于session超时或者没有登录";
	
	
	//系统设置模块
	//请求增加管理员页面
	public static final String ADD_SUC_MANAGERPAGE = "增加管理员：成功！";
	public static final String ADD_MANAGERPAGE_OUTTIME = "增加管理员：管理员存在session超时或者没有登录";
	public static final String SELECT_MANAGERPAGE_NOT_EXIST = "请求查询管理员信息：由于管理员不存在查询失败！";
	//增加管理员
	public static final String ADD_SUC_MANAGER_NOW = "增加管理员：成功！";
	public static final String ADD_ERROREXIST_MANAGER_NOW = "增加管理员：由于数据库已经存在相同名增加失败！";
	public static final String ADD_ERROREXIST_NOT_POWER = "增加管理员：由于不是超级管理员,操作失败！";
	public static final String ADD_ERROR_MANAGER_NOW = "增加管理员：失败！";
	public static final String ADD_MANAGER_OUTTIME_NOW = "增加管理员：管理员存在session超时或者没有登录";
	public static final String ALL_EXCEPTION_MANAGER = "增加管理员：由于未知异常增加失败！";
	//请求修改管理员页面
	public static final String ALTER_SUC_MANAGERPAGE = "请求修改管理员页面：成功！";
	public static final String ALTER_MANAGERPAGE_OUTTIME = "请求修改管理员页面：管理员存在session超时或者没有登录";
	//修改管理员
	public static final String ALTER_SUC_MANAGER = "修改管理员：成功！";
	public static final String ALTER_MANAGER_OUTTIME = "修改管理员：管理员存在session超时或者没有登录";
	public static final String ALTER_ERROR_NOMANAGER = "修改管理员：由于数据库没有此用户修改失败";
	public static final String ALTER_ERROR_NOT_POWER = "修改管理员：由于不是超级管理员,操作失败！";
	public static final String ALTER_ERROR_MANAGEREXIST = "修改管理员：由于数据库已经有修改的管理员名而修改失败";
	public static final String ALTER_ERROR_MANAGER = "修改管理员：失败";
	public static final String ALTER_EXCEPTION_MANAGER = "修改管理员：由于未知异常修改失败！";
	//请求删除管理员页面
	public static final String DELETE_SUC_MANAGERPAGE = "请求删除管理员页面：成功！";
	public static final String DELETE_MANAGERPAGE_OUTTIME = "请求删除管理员页面：管理员存在session超时或者没有登录";
	//删除管理员
	public static final String DELETE_SUC_MANAGER = "删除管理员：成功！";
	public static final String DELETE_MANAGER_OUTTIME = "删除管理员：管理员存在session超时或者没有登录";
	public static final String DELETE_ERROR_NOMANAGER = "删除管理员：由于数据库没有此用户删除失败";
	public static final String DELETE_ERROR_NOT_POWER = "删除管理员：由于不是超级管理员,操作失败！";
	public static final String DELETE_ERROR_MANAGER = "删除管理员：失败";
	public static final String DELETE_EXCEPTION_MANAGER = "删除管理员：由于未知异常删除失败！";
	//请求管理员日志页面
	public static final String LOG_SUC_MANAGERPAGE = "请求管理员日志页面：成功！";
	public static final String LOG_MANAGERPAGE_OUTTIME = "请求管理员日志页面：管理员存在session超时或者没有登录";
	public static final String LOG_EXCEPTION_MANAGER = "请求管理员日志页面：由于未知异常删除失败！";
	//请求代理商日志页面
	public static final String LOG_SUC_AGENTLOGPAGE = "请求代理商日志页面：成功！";
	public static final String LOG_AGENTLOGPAGE_OUTTIME = "请求代理商日志页面：管理员存在session超时或者没有登录";
	public static final String LOG_EXCEPTION_AGENTLOG = "请求代理商日志页面：由于未知异常删除失败！";
	//请求查询所有玩家
	public static final String SELECT_ALL_SUC_MANAGERPAGE = "请求所有管理员：成功！";
	public static final String SELECT_ALL_OUT_TIME = "请求所有管理员失败：管理员存在session超时或者没有登录！";
	public static final String SELECT_ALL_NOT_POWER = "请求所有管理员：管理员没有权限查询失败！";
	//请求查询单个玩家信息页面
	public static final String SELECT_SUC_MANAGERPAGE = "请求单个管理员信息：成功！";
	public static final String SELECT_OUT_TIME = "请求单个管理员信息：管理员存在session超时或者没有登录！";
	public static final String SELECT_NOT_POWER = "请求单个管理员信息：管理员没有权限查询失败！";
	
	
	//玩家管理模块
	//请求查询玩家信息页面
	public static final String SELECT_PLAYERPAGE_SUC_PAGE = "请求查询玩家页面：操作成功！";
	public static final String SELECT_PLAYERPAGE_OUTTIME_PAGE = "请求查询玩家页面：管理员存在时间超时或者没有登录导致操作失败!";
	public static final String SELECT_PLAYERPAGE_NOT_POWER_PAGE = "请求查询玩家页面：操作者由于没有权限操作失败!";
	public static final String SELECT_PLAYERPAGE_UNKNOWN_PAGE = "请求查询玩家页面：由于未知错误操作失败!";
	public static final String SELECT_PLAYERPAGE_NO_INFORMATION_PAGE = "请求查询玩家页面：由于没有此条信息记录操作失败!";
	
	//通过gameId查询玩家信息
	public static final String SELETE_PLAYERPAGE_SUC = "通过游戏玩家Id查询玩家信息：操作成功！";
	public static final String SELETE_PLAYERPAGE_OUT_TIME = "通过游戏玩家Id查询玩家信息：管理员存在时间超时或者没有登录导致操作失败!";
	public static final String SELETE_PLAYERPAGE_PARAMETERS_ERROR  = "通过游戏玩家Id查询玩家信息：由于传入参数错误操作失败!";
	public static final String SELETE_PLAYERPAGE_UNKNOWN = "通过游戏玩家Id查询玩家信息：由于未知错误操作失败!";
	public static final String SELETE_PLAYERPAGE_NOT_SERVER = "通过游戏玩家Id查询玩家信息：由于未获得游戏服务器连接操作失败!";
	public static final String SELETE_PLAYERPAGE_NOT_SERVER_DATA = "通过游戏玩家Id查询玩家信息：由于未从游戏服务器获得数据操作失败!";
	public static final String SELETE_PLAYERPAGE_NO_INFORMATION = "通过游戏玩家Id查询玩家信息：由于没有此条信息记录操作失败!";
	public static final String SELETE_PLAYERPAGE_ERROR = "通过游戏玩家Id查询玩家信息：操作失败!";
	//增加游戏玩家的房卡数
	public static final String ADD_PLAYER_ROOMCARD_SUC = "给玩家修改房卡：操作成功！";
	public static final String ADD_PLAYER_ROOMCARD_OUT_TIME = "给玩家修改房卡：管理员存在时间超时或者没有登录导致操作失败!";
	public static final String ADD_PLAYER_ROOMCARD_PARAMETERS_ERROR  = "给玩家修改房卡：由于传入参数错误操作失败!";
	public static final String ADD_PLAYER_ROOMCARD_UNKNOWN = "给玩家修改房卡：由于未知错误操作失败!";
	public static final String ADD_PLAYER_ROOMCARD_NOT_SERVER = "给玩家修改房卡：由于未获得游戏服务器连接操作失败!";
	public static final String ADD_PLAYERP_ROOMCARD_NOT_SERVER_DATA = "给玩家修改房卡：由于未从游戏服务器获得数据操作失败!";
	public static final String ADD_PLAYERP_ROOMCARD_NOT_POWER = "给玩家修改房卡：操作者由于没有权限操作失败!";
	public static final String ADD_PLAYERP_ROOMCARD_NUMBER = "给玩家修改房卡：由于传入房卡数错误操作失败!";
	public static final String ADD_PLAYERP_ROOMCARD_BEYOND = "给玩家修改房卡：减去的房卡数超出了玩家最大的房卡数!";
	public static final String ADD_PLAYER_ROOMCARD_ERROR = "给玩家修改房卡：操作失败!";
	
	//请求多个玩家交易记录页面
	public static final String SELECT_MANY_PLAYER_ROOMCARD_SUC = "请求查询多条玩家操作记录：操作成功！";
	public static final String SELECT_MANY_PLAYER_ROOMCARD_OUT_TIME = "请求查询多条玩家操作记录：管理员存在时间超时或者没有登录导致操作失败!";
	public static final String SELECT_MANY_PLAYER_ROOMCARD_PARAMETERS_ERROR  = "请求查询多条玩家操作记录：由于传入参数错误操作失败!";
	public static final String SELECT_MANY_PLAYER_ROOMCARD_UNKNOWN = "请求查询多条玩家操作记录：由于未知错误操作失败!";
	public static final String SELECT_MANY_PLAYER_ROOMCARD_NOT_SERVER = "请求查询多条玩家操作记录：由于未获得游戏服务器连接操作失败!";
	public static final String SELECT_MANY_PLAYERP_ROOMCARD_NOT_SERVER_DATA = "请求查询多条玩家操作记录：由于未从游戏服务器获得数据操作失败!";
	public static final String SELECT_MANY_PLAYERP_ROOMCARD_NOT_POWER = "请求查询多条玩家操作记录：操作者由于没有权限操作失败!";
	public static final String SELECT_MANY_PLAYERP_ROOMCARD_NUMBER = "请求查询多条玩家操作记录：由于传入房卡数错误操作失败!";
	public static final String SELECT_MANY_PLAYER_ROOMCARD_ERROR = "请求查询多条玩家操作记录：操作失败!";
	//请求玩家注册时赠送房卡数
	public static final String SELECT_PLAYER_STRAT_ROOMCARD_SUC_PAGE = "请求查询玩家房间注册时赠送房卡数：操作成功！";
	public static final String SELECT_PLAYER_STRAT_ROOMCARD_OUTTIME_PAGE = "请求查询玩家房间注册时赠送房卡数：管理员存在时间超时或者没有登录导致操作失败!";
	public static final String SELECT_PLAYER_STRAT_ROOMCARD_NOT_POWER_PAGE = "请求查询玩家房间注册时赠送房卡数：操作者由于没有权限操作失败!";
	public static final String SELECT_PLAYER_STRAT_ROOMCARD_UNKNOWN_PAGE = "请求查询玩家房间注册时赠送房卡数：由于未知错误操作失败!";
	public static final String SELECT_PLAYER_STRAT_ROOMCARD_NOT_SERVER = "请求查询玩家房间注册时赠送房卡数：由于未获得游戏服务器连接操作失败!";
	public static final String SELECT_PLAYER_STRAT_ROOMCARD_NOT_SERVER_DATA = "请求查询玩家房间注册时赠送房卡数：由于未从游戏服务器获得数据操作失败!";
	//修改玩家注册时赠送的房卡数
	public static final String ALERT_PLAYER_ROOMCARD_START_SUC = "修改玩家注册赠送的房卡：操作成功！";
	public static final String ALERT_PLAYER_ROOMCARD_START_OUT_TIME = "修改玩家注册赠送的房卡：管理员存在时间超时或者没有登录导致操作失败!";
	public static final String ALERT_PLAYER_ROOMCARD_START_PARAMETERS_ERROR  = "修改玩家注册赠送的房卡：由于传入参数错误操作失败!";
	public static final String ALERT_PLAYER_ROOMCARD_START_UNKNOWN = "修改玩家注册赠送的房卡：由于未知错误操作失败!";
	public static final String ALERT_PLAYER_ROOMCARD_START_NOT_SERVER = "修改玩家注册赠送的房卡：由于未获得游戏服务器连接操作失败!";
	public static final String ALERT_PLAYERP_ROOMCARD_START_NOT_SERVER_DATA = "修改玩家注册赠送的房卡：通过服务器修改玩家注册赠送的房卡数失败!";
	public static final String ALERT_PLAYERP_ROOMCARD_START_NOT_POWER = "修改玩家注册赠送的房卡：操作者由于没有权限操作失败!";
	public static final String ALERT_PLAYERP_ROOMCARD_START_NUMBER = "修改玩家注册赠送的房卡：由于传入房卡数错误操作失败!";
	public static final String ALERT_PLAYER_ROOMCARD_START_ERROR = "修改玩家注册赠送的房卡：操作失败!";
	
	
	
	
	
	
	
	
	//请求增加玩家页面
	public static final String ADD_SUC_PLAYERPAGE = "请求增加玩家页面：成功！";
	public static final String ADD_PLAYERPAGE_OUTTIME = "请求增加玩家页面：由于session超时或者没有登录";
	//增加玩家
	public static final String ADD_PLAYER_OUTTIME = "增加玩家：由于session超时或者没有登录";
	//请求修改玩家名和密码页面
	public static final String ALTER_SUC_PLAYERPAGE = "请求修改玩家页面：成功！";
	public static final String ALTER_PLAYERPAGE_OUTTIME = "请求修改玩家页面：由于session超时或者没有登录";
	//修改玩家名和密码
	public static final String ALTER_PLAYER_OUTTIME = "修改玩家：由于session超时或者没有登录";
	//请求删除玩家页面
	public static final String DELETE_SUC_PLAYERPAGE = "请求删除玩家页面：成功！";
	public static final String DELETE_PLAYERPAGE_OUTTIME = "请求删除玩家页面：由于session超时或者没有登录";
	//请求查询玩家参数页面
	public static final String SELETE_PLAYERPAGE_OUTTIME = "请求玩家参数页面：由于session超时或者没有登录";
	//查询玩家参数
	public static final String SELETE_PLAYER_OUTTIME = "查询玩家参数：由于session超时或者没有登录";
	//查询所有玩家
	public static final String SELETEALL_PLAYER_OUTTIME = "查询所有玩家参数：由于session超时或者没有登录";
	
	
	/**游戏房间模块*/
	//请求增加游戏房间页面
	public static final String ADD_SUC_ROOMPAGE = "请求增加游戏房间页面：成功！";
	public static final String ADD_ROOMPAGE_OUTTIME = "请求增加游戏房间页面：由于session超时或者没有登录";
	public static final String SELECT_GAMES_ALL_NOSERVER = "请求游戏服务器：由于没有获得游戏服务器请求失败!";
	public static final String SELECT_GAMES_ALL_NOGETGAMES = "请求获取所有游戏：请求失败!";
	public static final String SELECT_EXCEPTION_GAMES = "请求获取所有游戏：由于未知异常请求失败!";
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//房间管理模块  
	// 添加房间
	public static final String ROOM_SUC_CREROOM = "房间创建成功";
	public static final String ROOM_OUTTIME = "创建房间时：管理员存在session超时或者没有登录";
	public static final String ROOM_IN_NEWS_ERROR = "创建房间时：传入实体room为null！";
	public static final String ROOM_IN_ROOM_ERROR = "创建房间时：房间名格式错误！";
	public static final String ROOM_LOGSERVER_ERROR = "创建房间时：获取登陆服务失败！";
	public static final String ROOM_GETRESULT_ERROR = "创建房间时：由于登陆服务原因创建房间失败！";
	public static final String ROOM_UNKNOWN_ERROR = "创建房间：由于未知原因创建失败！";
	//查询所有房间信息
	public static final String SELECT_ALLROOM_SUC_CREROOM = "查询所有房间成功！";
	public static final String SELECT_ALLROOM_OUTTIME = "查询所有房间时：管理员存在session超时或者没有登录";
	public static final String SELECT_ALLROOM_LOGSERVER_ERROR = "查询所有房间获取登陆服务失败！";
	public static final String SELECT_ALLROOM_GETRESULT_ERROR = "由于登陆服务原因创建查询所有房间失败！";
	public static final String SELECT_ALLROOM_UNKNOWN_ERROR = "查询所有房间：由于未知原因查询失败！";
	//通过查询所有房间链接到修改页面
	//public static String ALTERPAGE_ROOM_SUC_CREROOM = "修改房间参数成功！";
	public static final String ALTERPAGE_ROOM_OUTTIME = "链接到修改页面时：管理员存在session超时或者没有登录";
	public static final String ALTERPAGE_ROOM_UNKNOWN_ERROR = "修改房间：由于未知原因修改失败！";
	//修改房间信息
	public static final String ALTER_ROOM_SUC_CREROOM = "修改房间参数成功！";
	public static final String ALTER_ROOM_OUTTIME = "修改房间参数时：管理员存在session超时";
	public static final String ALTER_ROOM_IN_NEWS_ERROR = "修改房间参数时：传入实体room为null！";
	public static final String ALTER_ROOM_IN_ROOM_ERROR = "修改房间时：房间名格式错误！";
	public static final String ALTER_ROOM_LOGSERVER_ERROR = "修改房间时：获取登陆服务失败！";
	public static final String ALTER_ROOM_GETRESULT_ERROR = "修改房间时：由于登陆服务原因创建房间失败！";
	public static final String ALTER_ROOM_SELECT_ERROR = "修改房间时：传入原来的名字，没有从登陆服务获得room";
	public static final String ALTER_ROOM_UNKNOWN_ERROR = "修改房间：由于未知原因修改失败！";
	//查询房间信息
	public static final String SELECT_ROOM_SUC_CREROOM = "查询房间信息成功！";
	public static final String SELECT_ROOM_OUTTIME = "查询房间参数时：管理员存在session超时或者没有登录";
	public static final String SELECT_ROOM_LOGSERVER_ERROR = "查询房间信息时：获取登陆服务失败！";
	public static final String SELECT_ROOM_SELECT_ERROR = "查询房间信息时：传入原来的名字，没有从登陆服务获得room信息";
	public static final String SELECT_ROOM_UNKNOWN_ERROR = "查询房间信息：由于未知原因修改失败！";
	
	
	//桌子模块
	//请求增加桌子页面
	public static final String ADD_DESKPAGE_SUC_CREROOM = "请求增加桌子页面时：成功！";
	public static final String ADD_DESKPAGE_OUTTIME = "请求增加桌子页面时：管理员存在session超时或者没有登录";
	public static final String ADD_DESKPAGE_LOGSERVER_ERROR = "请求增加桌子页面时：获取登陆服务失败！";
	public static final String ADD_DESKPAGE_GETRESULT_ERROR = "请求增加桌子页面时：由于登陆服务原因创建房间失败！";
	public static final String ADD_DESKPAGE_UNKNOWN_ERROR = "请求增加桌子页面：由于未知原因创建失败！";
	//通过游戏id获取房间名
	public static final String GET_ROOMNAME_SUC_CREROOM = "通过游戏id获取房间名时：成功！";
	public static final String GET_ROOMNAME_OUTTIME = "通过游戏id获取房间名时：管理员存在session超时或者没有登录";
	public static final String GET_ROOMNAME_LOGSERVER_ERROR = "通过游戏id获取房间名时：获取登陆服务失败！";
	public static final String GET_ROOMNAME_GETRESULT_ERROR = "通过游戏id获取房间名时：由于登陆服务原因创建房间失败！";
	public static final String GET_ROOMNAME_UNKNOWN_ERROR = "通过游戏id获取房间名时：由于未知原因创建失败！";
	//增加桌子
	public static final String ADD_DESK_SUC_CREROOM = "增加桌子时：成功！";
	public static final String ADD_DESK_OUTTIME = "增加桌子时：管理员存在session超时或者没有登录";
	public static final String ADD_DESK_LOGSERVER_ERROR = "增加桌子时：获取登陆服务失败！";
	public static final String ADD_DESK_GETRESULT_ERROR = "增加桌子时：由于登陆服务原因创建房间失败！";
	public static final String ADD_DESK_UNKNOWN_ERROR = "增加桌子时：由于未知原因创建失败！";
	//请求查询桌子页面
	public static final String SELECT_DESKPAGE_SUC_CREROOM = "请求查询桌子页面时：成功！";
	public static final String SELECT_DESKPAGE_OUTTIME = "请求查询桌子页面时：管理员存在session超时或者没有登录";
	public static final String SELECT_DESKPAGE_LOGSERVER_ERROR = "请求查询桌子页面时：获取登陆服务失败！";
	public static final String SELECT_DESKPAGE_GETRESULT_ERROR = "请求查询桌子页面时：由于登陆服务原因创建房间失败！";
	public static final String SELECT_DESKPAGE_UNKNOWN_ERROR = "请求查询桌子页面：由于未知原因创建失败！";
	//查询桌子
	public static final String SELECT_DESK_SUC_CREROOM = "查询桌子时：成功！";
	public static final String SELECT_DESK_OUTTIME = "查询桌子时：管理员存在session超时或者没有登录";
	public static final String SELECT_DESK_LOGSERVER_ERROR = "查询桌子时：获取登陆服务失败！";
	public static final String SELECT_DESK_GETRESULT_ERROR = "查询桌子时：由于登陆服务原因创建房间失败！";
	public static final String SELECT_DESK_UNKNOWN_ERROR = "查询桌子时：由于未知原因创建失败！";
	//查询所有桌子
	public static final String SELECTALL_DESKPAGE_SUC_CREROOM = "查询所有桌子时：成功！";
	public static final String SELECTALL_DESKPAGE_OUTTIME = "查询所有桌子时：管理员存在session超时或者没有登录";
	public static final String SELECTALL_DESKPAGE_LOGSERVER_ERROR = "查询所有桌子时：获取登陆服务失败！";
	public static final String SELECTALL_DESKPAGE_GETRESULT_ERROR = "查询所有桌子时：由于登陆服务原因创建房间失败！";
	public static final String SELECTALL_DESKPAGE_UNKNOWN_ERROR = "查询所有桌子时：由于未知原因创建失败！";
	//请求修改桌子页面
	public static final String ALTER_DESKPAGE_SUC_CREROOM = "请求修改桌子页面时：成功！";
	public static final String ALTER_DESKPAGE_OUTTIME = "请求修改桌子页面时：管理员存在session超时或者没有登录";
	public static final String ALTER_DESKPAGE_UNKNOWN_ERROR = "请求修改桌子页面：由于未知原因创建失败！";
	//修改桌子参数
	public static final String ALTER_DESK_SUC_CREROOM = "修改桌子参数时：成功！";
	public static final String ALTER_DESK_OUTTIME = "修改桌子参数时：管理员存在session超时或者没有登录";
	public static final String ALTER_DESK_LOGSERVER_ERROR = "修改桌子参数时：获取登陆服务失败！";
	public static final String ALTER_DESK_GETRESULT_ERROR = "修改桌子参数时：由于登陆服务原因创建房间失败！";
	public static final String ALTER_DESK_UNKNOWN_ERROR = "修改桌子参数时：由于未知原因创建失败！";
	
	
	//留桌设置管理模块
	//请求增加留桌设置页面
	public static final String ADD_WAITDESKPAGE_SUC_CREROOM = "请求设置留桌设置页面时：成功！";
	public static final String ADD_WAITDESKPAGE_OUTTIME = "请求设置留桌设置页面时：管理员存在session超时或者没有登录";
	//增加留桌设置
	public static final String ADD_WAITDESK_SUC_CREROOM = "添加设置留桌时：成功！";
	public static final String ADD_WAITDESK_OUTTIME = "添加设置留桌时：管理员存在session超时或者没有登录";
	public static final String ADD_WAITDESK_LOGSERVER_ERROR = "添加设置留桌时：获取登陆服务失败！";
	public static final String ADD_WAITDESK_GETRESULT_ERROR = "添加设置留桌时：由于登陆服务原因创建失败！";
	public static final String ADD_WAITDESK_UNKNOWN_ERROR = "添加设置留桌时：由于未知原因创建失败！";
	//查询所有留桌设置
	public static final String SELECT_ALLWAITDESK_SUC_CREROOM = "查询所有设置留桌时：成功！";
	public static final String SELECT_ALLWAITDESK_OUTTIME = "查询所有设置留桌时：管理员存在session超时或者没有登录";
	public static final String SELECT_ALLWAITDESK_LOGSERVER_ERROR = "查询所有设置留桌时：获取登陆服务失败！";
	public static final String SELECT_ALLWAITDESK_GETRESULT_ERROR = "查询所有设置留桌时：由于登陆服务原因查询失败！";
	public static final String SELECT_ALLWAITDESK_UNKNOWN_ERROR = "查询所有设置留桌时：由于未知原因查询失败！";
	//修改留桌设置
	public static final String ALTER_WAITDESK_SUC_CREROOM = "修改设置留桌时：成功！";
	public static final String ALTER_WAITDESK_OUTTIME = "修改设置留桌时：管理员存在session超时或者没有登录";
	public static final String ALTER_WAITDESK_LOGSERVER_ERROR = "修改设置留桌时：获取登陆服务失败！";
	public static final String ALTER_WAITDESK_NAMEANDID_ERROR = "修改设置留桌时：typeName或者typeID已经存在！";
	public static final String ALTER_WAITDESK_FINDNAMEANDID_ERROR = "修改设置留桌时：typeName或者typeID由于登陆服务原因查询失败！";
	public static final String ALTER_WAITDESK_GETRESULT_ERROR = "修改设置留桌时：由于登陆服务原因修改失败！";
	public static final String ALTER_WAITDESK_UNKNOWN_ERROR = "修改设置留桌时：由于未知原因修改失败！";
	//通过查询所有留桌设置链接到修改页面
	public static final String ALTER_WAITDESKPAGE_SUC_CREROOM = "请求修改设置留桌页面时：成功！";
	public static final String ALTER_WAITDESKPAGE_OUTTIME = "请求修改设置留桌页面时：管理员存在session超时或者没有登录";
	//通过查询所有的增加到已有的typeName或者typeId的留桌查看设置的增加页面
	//复用单纯增加页面的的错误码
	
	
	//比倍模块
	//查询所有比倍
	public static final String SELECT_ALLDOUBLE_SUC_CREROOM = "查询所有比倍时：成功！";
	public static final String SELECT_ALLDOUBLE_OUTTIME = "查询所有比倍时：管理员存在session超时或者没有登录";
	public static final String SELECT_ALLDOUBLE_LOGSERVER_ERROR = "查询所有比倍时：获取登陆服务失败！";
	public static final String SELECT_ALLDOUBLE_GETRESULT_ERROR = "查询所有比倍时：由于登陆服务原因查询失败！";
	public static final String SELECT_ALLDOUBLE_UNKNOWN_ERROR = "查询所有比倍时：由于未知原因查询失败！";
	//修改获胜概率
	public static final String ALTER_DOUBLE_SUC_CREROOM = "修改比倍时：成功！";
	public static final String ALTER_DOUBLE_OUTTIME = "修改比倍时：管理员存在session超时或者没有登录";
	public static final String ALTER_DOUBLE_LOGSERVER_ERROR = "修改比倍时：获取登陆服务失败！";
	public static final String ALTER_DOUBLE_GETRESULT_ERROR = "修改比倍时：由于登陆服务原因修改失败！";
	public static final String ALTER_DOUBLE_UNKNOWN_ERROR = "修改比倍时：由于未知原因修改失败！";
	
	
	//签到模块
	//查询所有签到
	public static final String SELECT_ALLSIGN_SUC_CREROOM = "查询所有签到时：成功！";
	public static final String SELECT_ALLSIGN_OUTTIME = "查询所有签到时：管理员存在session超时或者没有登录";
	public static final String SELECT_ALLSIGN_LOGSERVER_ERROR = "查询所有签到时：获取登陆服务失败！";
	public static final String SELECT_ALLSIGN_GETRESULT_ERROR = "查询所有签到时：由于登陆服务原因查询失败！";
	public static final String SELECT_ALLSIGN_UNKNOWN_ERROR = "查询所有签到时：由于未知原因查询失败！";
	//修改签到金币
	public static final String ALTER_ALLSIGN_SUC_CREROOM = "修改签到金币时：成功！";
	public static final String ALTER_ALLSIGN_OUTTIME = "修改签到金币时：管理员存在session超时或者没有登录";
	public static final String ALTER_ALLSIGN_LOGSERVER_ERROR = "修改签到金币时：获取登陆服务失败！";
	public static final String ALTER_ALLSIGN_GETRESULT_ERROR = "修改签到金币时：由于登陆服务原因修改失败！";
	public static final String ALTER_ALLSIGN_UNKNOWN_ERROR = "修改签到金币时：由于未知原因修改失败！";
	
	
	//登陆奖励
	//查询所有登陆奖励
	public static final String SELECT_LANGINGWARD_SUC_CREROOM = "查询所有登陆奖励时：成功！";
	public static final String SELECT_LANGINGWARD_OUTTIME = "查询所有登陆奖励时：管理员存在session超时或者没有登录";
	public static final String SELECT_LANGINGWARD_LOGSERVER_ERROR = "查询所有登陆奖励时：获取登陆服务失败！";
	public static final String SELECT_LANGINGWARD_GETRESULT_ERROR = "查询所有登陆奖励时：由于登陆服务原因查询失败！";
	public static final String SELECT_LANGINGWARD_UNKNOWN_ERROR = "查询所有登陆奖励时：由于未知原因查询失败！";
	//修改登陆奖励金币和概率
	public static final String ALTER_LANGINGWARD_SUC_CREROOM = "修改登陆奖励金币和概率时：成功！";
	public static final String ALTER_LANGINGWARD_OUTTIME = "修改登陆奖励金币和概率时：管理员存在session超时或者没有登录";
	public static final String ALTER_LANGINGWARD_LOGSERVER_ERROR = "修改登陆奖励金币和概率时：获取登陆服务失败！";
	public static final String ALTER_LANGINGWARD_GETRESULT_ERROR = "修改登陆奖励金币和概率时：由于登陆服务原因修改失败！";
	public static final String ALTER_LANGINGWARD_UNKNOWN_ERROR = "修改登陆奖励金币和概率时：由于未知原因修改失败！";
	
	
	//活动奖励
	//查询所有活动奖励项
	public static final String SELECT_ACTIONWARD_SUC_CREROOM = "查询所有活动奖励时：成功！";
	public static final String SELECT_ACTIONWARD_OUTTIME = "查询所有活动奖励时：管理员存在session超时或者没有登录";
	public static final String SELECT_ACTIONWARD_LOGSERVER_ERROR = "查询所有活动奖励时：获取登陆服务失败！";
	public static final String SELECT_ACTIONWARD_GETRESULT_ERROR = "查询所有活动奖励时：由于登陆服务原因查询失败！";
	public static final String SELECT_ACTIONWARD_UNKNOWN_ERROR = "查询所有活动奖励时：由于未知原因查询失败！";
	//修改活动奖励
	public static final String ALTER_ACTIONWARD_SUC_CREROOM = "修改活动奖励时：成功！";
	public static final String ALTER_ACTIONWARD_OUTTIME = "修改活动奖励时：管理员存在session超时或者没有登录";
	public static final String ALTER_ACTIONWARD_LOGSERVER_ERROR = "修改活动奖励时：获取登陆服务失败！";
	public static final String ALTER_ACTIONWARD_GETRESULT_ERROR = "修改活动奖励时：由于登陆服务原因修改失败！";
	public static final String ALTER_ACTIONWARD_UNKNOWN_ERROR = "修改活动奖励时：由于未知原因修改失败！";
	
	
	//充值管理
	//更加条件查询玩家订单记录
	public static final String SELECT_MANY_ORDER_SUC = "查询订单记录：成功！";
	public static final String SELECT_MANY_ORDER_OUTTIME = "查询订单记录：管理员存在超时或者没有登录";
	public static final String SELECT_MANY_ORDER_PARAMETER_ERROR = "查询订单记录：由于传入参数错误查询失败！";
	public static final String SELECT_MANY_ORDER_NOT_POWER = "查询订单记录：由于没有权限查询失败！";
	public static final String SELECT_MANY_ORDER_ERROR = "查询订单记录：查询失败！";
	public static final String SELECT_MANY_ORDER_UNKNOWN_ERROR = "查询订单记录：由于未知原因查询失败！";
	
	
	
	
	
	//查询钻石兑换金币比例
	public static final String SELECT_CONVERTSCALE_SUC_CREROOM = "查询钻石兑换金币时：成功！";
	public static final String SELECT_CONVERTSCALE_OUTTIME = "查询钻石兑换金币时：管理员存在session超时或者没有登录";
	public static final String SELECT_CONVERTSCALE_LOGSERVER_ERROR = "查询钻石兑换金币时：获取登陆服务失败！";
	public static final String SELECT_CONVERTSCALE_GETRESULT_ERROR = "查询钻石兑换金币时：由于登陆服务原因查询失败！";
	public static final String SELECT_CONVERTSCALE_UNKNOWN_ERROR = "查询钻石兑换金币时：由于未知原因查询失败！";
	//修改钻石兑换金币比例
	public static final String ALTER_CONVERTSCALE_SUC_CREROOM = "修改钻石兑换金币时：成功！";
	public static final String ALTER_CONVERTSCALE_OUTTIME = "修改钻石兑换金币时：管理员存在session超时或者没有登录";
	public static final String ALTER_CONVERTSCALE_LOGSERVER_ERROR = "修改钻石兑换金币时：获取登陆服务失败！";
	public static final String ALTER_CONVERTSCALE_GETRESULT_ERROR = "修改钻石兑换金币时：由于登陆服务原因修改失败！";
	public static final String ALTER_CONVERTSCALE_UNKNOWN_ERROR = "修改钻石兑换金币时：由于未知原因修改失败！";
	//查询充值档次
	public static final String SELECT_AllLEVEL_SUC_CREROOM = "查询充值档次时：成功！";
	public static final String SELECT_AllLEVEL_OUTTIME = "查询充值档次时：管理员存在session超时或者没有登录";
	public static final String SELECT_AllLEVEL_LOGSERVER_ERROR = "查询充值档次时：获取登陆服务失败！";
	public static final String SELECT_AllLEVEL_GETRESULT_ERROR = "查询充值档次时：由于登陆服务原因查询失败！";
	public static final String SELECT_AllLEVEL_UNKNOWN_ERROR = "查询充值档次时：由于未知原因查询失败！";
	//修改充值档次
	public static final String ALTER_AllLEVEL_SUC_CREROOM = "修改充值档次时：成功！";
	public static final String ALTER_AllLEVEL_OUTTIME = "修改充值档次时：管理员存在session超时或者没有登录";
	public static final String ALTER_AllLEVEL_LOGSERVER_ERROR = "修改充值档次时：获取登陆服务失败！";
	public static final String ALTER_AllLEVEL_GETRESULT_ERROR = "修改充值档次时：由于登陆服务原因修改失败！";
	public static final String ALTER_AllLEVEL_UNKNOWN_ERROR = "修改充值档次时：由于未知原因修改失败！";
	//增加充值档次
	public static final String ADD_AllLEVEL_SUC_CREROOM = "增加充值档次时：成功！";
	public static final String ADD_AllLEVEL_OUTTIME = "增加充值档次时：管理员存在session超时或者没有登录";
	public static final String ADD_AllLEVEL_LOGSERVER_ERROR = "增加充值档次时：获取登陆服务失败！";
	public static final String ADD_AllLEVEL_GETRESULT_ERROR = "增加充值档次时：由于登陆服务原因增加失败！";
	public static final String ADD_AllLEVEL_UNKNOWN_ERROR = "增加充值档次时：由于未知原因增加失败！";
	//请求增加充值档次页面
	public static final String ADD_AllLEVELPAGE_SUC_CREROOM = "请求增加充值档次页面时：成功！";
	public static final String ADD_AllLEVELPAGE_OUTTIME = "请求增加充值档次页面时：管理员存在session超时或者没有登录";
	
	
	//桌子概率模块
	//查询所有桌子概率
	public static final String SELECT_ALLDESKCHANCE_SUC_CREROOM = "查询所有桌子概率时：成功！";
	public static final String SELECT_ALLDESKCHANCE_OUTTIME = "查询所有桌子概率时：管理员存在session超时或者没有登录";
	public static final String SELECT_ALLDESKCHANCE_LOGSERVER_ERROR = "查询所有桌子概率时：获取登陆服务失败！";
	public static final String SELECT_ALLDESKCHANCE_GETRESULT_ERROR = "查询所有桌子概率时：由于登陆服务原因查询失败！";
	public static final String SELECT_ALLDESKCHANCE_UNKNOWN_ERROR = "查询所有桌子概率时：由于未知原因查询失败！";
	//请求修改桌子概率页面
	public static final String ALTER_DESKCHANCEPAGE_SUC_CREROOM = "请求修改桌子概率页面时：成功！";
	public static final String ALTER_DESKCHANCEPAGE_OUTTIME = "请求修改桌子概率页面时：管理员存在session超时或者没有登录";
	public static final String ALTER_DESKCHANCEPAGE_UNKNOWN_ERROR = "请求修改桌子概率页面时：由于未知原因查询失败！";
	//修改桌子
	public static final String ALTER_DESKCHANCE_SUC_CREROOM = "请求修改桌子概率时：成功！";
	public static final String ALTER_DESKCHANCE_OUTTIME = "请求修改桌子概率时：管理员存在session超时或者没有登录";
	public static final String ALTER_DESKCHANCE_LOGSERVER_ERROR = "请求修改桌子概率时：获取登陆服务失败！";
	public static final String ALTER_DESKCHANCE_GETRESULT_ERROR = "请求修改桌子概率时：由于登陆服务原因修改失败！";
	public static final String ALTER_DESKCHANCE_UNKNOWN_ERROR = "请求修改桌子概率时：由于未知原因修改失败！";
	
	
	//房间概率模块
	//查询所有房间概率
	public static final String SELECT_ALLROOMCHANCE_SUC_CREROOM = "查询所有房间概率时：成功！";
	public static final String SELECT_ALLROOMCHANCE_OUTTIME = "查询所有房间概率时：管理员存在session超时或者没有登录";
	public static final String SELECT_ALLROOMCHANCE_LOGSERVER_ERROR = "查询所有房间概率时：获取登陆服务失败！";
	public static final String SELECT_ALLROOMCHANCE_GETRESULT_ERROR = "查询所有房间概率时：由于登陆服务原因查询失败！";
	public static final String SELECT_ALLROOMCHANCE_UNKNOWN_ERROR = "查询所有房间概率时：由于未知原因查询失败！";
	//请求修改房间概率页面
	public static final String ALTER_ROOMCHANCEPAGE_SUC_CREROOM = "请求修改房间概率页面时：成功！";
	public static final String ALTER_ROOMCHANCEPAGE_OUTTIME = "请求修改房间概率页面时：管理员存在session超时或者没有登录";
	public static final String ALTER_ROOMCHANCEPAGE_UNKNOWN_ERROR = "请求修改房间概率页面时：由于未知原因查询失败！";
	//修改房间概率
	public static final String ALTER_ROOMCHANCE_SUC_CREROOM = "请求修改房间概率时：成功！";
	public static final String ALTER_ROOMCHANCE_OUTTIME = "请求修改房间概率时：管理员存在session超时或者没有登录";
	public static final String ALTER_ROOMCHANCE_LOGSERVER_ERROR = "请求修改房间概率时：获取登陆服务失败！";
	public static final String ALTER_ROOMCHANCE_GETRESULT_ERROR = "请求修改房间概率时：由于登陆服务原因修改失败！";
	public static final String ALTER_ROOMCHANCE_UNKNOWN_ERROR = "请求修改房间概率时：由于未知原因修改失败！";
	
	
	//消息模块
	//请求增加消息界面
	public static final String ADD_INFORMATION_SUC_PAGE = "请求增加消息页面：成功！";
	public static final String ADD_INFORMATION_OUTTIME_PAGE = "请求增加消息页面时：管理员存在session超时或者没有登录";
	public static final String ADD_INFORMATION_NOT_POWER_PAGE = "请求增加消息页面时：操作者由于没有权限操作失败!";
	//增加消息
	public static final String ADD_INFORMATION_SUC = "请求增加消息：成功！";
	public static final String ADD_INFORMATION_OUTTIME = "请求增加消息时：管理员存在session超时或者没有登录";
	public static final String ADD_INFORMATION_NOT_POWER = "请求增加消息时：操作者由于没有权限操作失败!";
	public static final String ADD_INFORMATION_UNKNOWN = "请求增加消息时：由于未知错误操作失败!";
	//请求所有消息记录页面
	public static final String SELECT_INFORMATION_ALL_SUC = "请求查询所有消息记录页面：操作成功！";
	public static final String SELECT_INFORMATION_ALL_OUTTIME = "请求查询所有消息记录页面：管理员存在时间超时或者没有登录导致操作失败!";
	public static final String SELECT_INFORMATION_ALL_NOT_POWER = "请求查询所有消息记录页面：操作者由于没有权限操作失败!";
	public static final String SELECT_INFORMATION_ALL_UNKNOWN = "请求查询所有消息记录页面：由于未知错误操作失败!";
	public static final String SELECT_INFORMATION_ALL_ERROR = "请求查询所有消息记录页面：操作失败!";
	//请求单个消息记录页面
	public static final String SELECT_INFORMATION_SUC = "请求查询消息记录页面：操作成功！";
	public static final String SELECT_INFORMATION_OUTTIME = "请求查询消息记录页面：管理员存在时间超时或者没有登录导致操作失败!";
	public static final String SELECT_INFORMATION_NOT_POWER = "请求查询消息记录页面：操作者由于没有权限操作失败!";
	public static final String SELECT_INFORMATION_UNKNOWN = "请求查询消息记录页面：由于未知错误操作失败!";
	public static final String SELECT_INFORMATION_NO_INFORMATION = "请求查询消息记录页面：由于没有此条信息记录操作失败!";
	//修改消息记录
	public static final String ALERT_INFORMATION_SUC = "修改消息记录：操作成功！";
	public static final String ALERT_INFORMATION_OUTTIME = "修改消息记录页面：管理员存在时间超时或者没有登录导致操作失败!";
	public static final String ALERT_INFORMATION_NOT_POWER = "修改消息记录：操作者由于没有权限操作失败!";
	public static final String ALERT_INFORMATION_UNKNOWN = "修改消息记录：由于未知错误操作失败!";
	public static final String ALERT_INFORMATION_NO_INFORMATION = "修改消息记录：由于没有此条信息记录操作失败!";
	public static final String ALERT_INFORMATION_ERROR = "修改消息记录：操作失败!";
	//删除消息记录
	public static final String DELETE_INFORMATION_SUC = "删除消息记录：操作成功！";
	public static final String DELETE_INFORMATION_OUTTIME = "删除消息记录：管理员存在时间超时或者没有登录导致操作失败!";
	public static final String DELETE_INFORMATION_NOT_POWER = "删除消息记录：操作者由于没有权限操作失败!";
	public static final String DELETE_INFORMATION_UNKNOWN = "删除消息记录：由于未知错误操作失败!";
	public static final String DELETE_INFORMATION_NO_INFORMATION = "删除消息记录：由于没有此条信息记录操作失败!";
	public static final String DELETE_INFORMATION_ERROR = "删除消息记录：操作失败!";
	//请求跑马灯和公告页面
	public static final String SELECT_INFORMATION_NOW_SUC = "请求查询跑马灯和公告页面：操作成功！";
	public static final String SELECT_INFORMATION_NOW_OUTTIME = "请求查询跑马灯和公告页面：管理员存在时间超时或者没有登录导致操作失败!";
	public static final String SELECT_INFORMATION_NOW_NOT_POWER = "请求查询跑马灯和公告页面：操作者由于没有权限操作失败!";
	public static final String SELECT_INFORMATION_NOW_UNKNOWN = "请求查询跑马灯和公告页面：由于未知错误操作失败!";
	public static final String SELECT_INFORMATION_NOW_ERROR = "请求查询跑马灯和公告页面：操作失败!";
	
	
	public static final String UPDATE_OK = "ok";
	public static final String UPDATE_AWORD_SUCCESS = "更新奖励成功";
	public static final String UPDATE_SHARENUMBER_SUCCESS = "更新分享信息成功";
	public static final String UPDATE_APPLYINFO_SUCCESS = "更新信息成功";
	
	public static final String UPDATE_ERROR = "error";
	public static final String UPDATE_APPLYINFO_ERROR = "更新信息失败";
	
	public static final String STATE_APPROVE = "state_approve";
	public static final String STATE_APPROVE_INFO = "认证申请正在审核中，请勿重复提交";
	
	public static final String STATE_AGREE = "state_agree";
	public static final String STATE_AGREE_INFO = "已同意该玩家的认证申请，请勿重复提交";
	
	public static final String STATE_REFUSE = "state_refuse";
	public static final String STATE_REFUSE_INFO = "您的认证申请正在批准中，请勿重复提交";
			
	
	public static final String STATE_OK = "1";
	public static final String STATE_OK_INFO = "您的实名申请已发送成功";
	
	public static final String RMISERVER_ERROR = "rmiServer_error";
	public static final String RMISERVER_ERROR_INFO = "游戏服链接异常，请稍后再试";
	
	public static final String STATE_ERROR = "state_error";
	public static final String STATE_ERROR_INFO = "参数异常，请输入合法参数";
	
	
	public static final String INSERT_ERROR ="insert_error";
	public static final String INSERT_ERROR_INFO = "插入失败，请稍后再试";
}



















