package com.game.constant;

import java.util.Map;

import com.game.base.util.GameUtil;

public class D {
	//系统
	public static final String DEF_RETURN   = "r";
	public static final String DEF_COMMAND   = "cmd";
	public static final String DEF_CODE = "code"; // 标识码
	public static final String TO_CLIENT_PARAM_UUID = "uuid";//登陆码
	public static final String TO_CLIENT_PARAM_UNAME = "uname";//账户名
	public static final String TO_CLIENT_PARAM_PWD = "pwd";//密码
	public static final String TO_CLIENT_PARAM_SERVER = "sid";//服务器
	public static final String TO_CLIENT_PARAM_PORT = "port";//端口
	public static final String TO_CLIENT_PARAM_IP = "host";//ip
	public static final String TO_CLIENT_PARAM_PAYMENT_URL = "payurl";//充值服地址
	public static final String TO_CLIENT_PARAM_IS_BIND = "zhbd";//账号绑定
	
	
	public static final int CODE_SUC = 0; // 标识码:成功
	public static final int CODE_SERVER_EXCEPTION = 1; //服务器异常
	
//	public static final int CODE_INVOKE_PARAM_ERR = 2; /** 传递参数错误 */
//	public static final int CODE_PARAM_ERR = 2; /** 传递参数错误 */
	public static final int CODE_PARAM_ERROR = 2;//传递参数错误
	
	public static final int CODE_INVOKE_NONE_ERR = 3; /** 没有找到调用 */
	public static final int CODE_RETURN_TYPE_ERR = 4;//逻辑方法返回异常
	

	public static final int CODE_HAVENT_LOGIN = 5;//尚未登录
	public static final int CODE_DISCTRICT_ERROR = 7;//disctrict表中没有当前游戏服务器的配置
	public static final int CODE_CONNECTION_GAME_ERROR = 8;//连接游戏服务器RMI失败
	
	
	
	
	//注册
	public static final int CODE_REG = 100000;
	public static final int CODE_REG_IMEI_USED = CODE_REG+12;//IMEI已被注册
	public static final int CODE_REG_GAMESERVER_MAX_REGISTER = CODE_REG+13;//服务器已满
	public static final int CODE_REG_GAMESERVER_NOT_FIND = CODE_REG+14;//没有游戏服务器
	public static final int CODE_REG_GAMESERVER_NOT_OPEN= CODE_REG+15;//服务器没有开放
	public static final int CODE_REG_PASSWORD_ERROR = CODE_REG+16;//密码不合法
	public static final int CODE_REG_USERNAME_USED = CODE_REG+17;//用户名已被注册
	public static final int CODE_REG_FAILED = CODE_REG+18;//注册失败
	public static final int CODE_REG_TWO_PASSWORD_ERROR = CODE_REG+19;//两次密码不一致
	//登陆
	public static final int CODE_LOGIN = 100100;
	public static final int CODE_LOGIN_NO_USR = CODE_LOGIN+3;//没有用户
	public static final int CODE_LOGIN_PSD_ERR = CODE_LOGIN+4;//密码错误
	public static final int CODE_LOGIN_LOGIN_TOO_FAST = CODE_LOGIN+6;//小于规定时间的连续登录
	public static final int CODE_LOGIN_ERR = CODE_LOGIN+13;//登陆失败
	public static final int CODE_LOGIN_USR_NAME_ERR = CODE_LOGIN+15;//用户名非法
	public static final int CODE_LOGIN_VALIDATE_ERROR = CODE_LOGIN+16;//登陆验证失败
	public static final int CODE_LOGIN_BAIDU_VAL_ERROR = CODE_LOGIN+17;//百度验证失败
	public static final int CODE_REG_UNAME_OR_PWD_LENGTH_ERROR = CODE_REG+18;//账号或密码长度不合法
	//绑定账号 修改密码
	public static final int CODE_BIND = 100200;
	public static final int CODE_BIND_USER_IS_BINDED = CODE_BIND + 1;//该玩家已经绑定了账号，不能重复绑定
	public static final int CODE_BIND_USER_IS_CHANL = CODE_BIND + 2;//账号包含中文
	public static final int CODE_BIND_USER_PWD_CHANL = CODE_BIND + 3;//密码包含中文
	public static final int CODE_BIND_USER_UNAME_ERROR = CODE_BIND + 4;//账号重复
	public static final int CODE_BIND_USER_PWD_EXIST_SPACE = CODE_BIND + 5;//密码存在空格
	public static final int CODE_BIND_USER_UNAME_EMAIL_ERROR = CODE_BIND + 6;//邮件不合法
	public static final int CODE_BIND_USER_OLD_PWD_ERROR = CODE_BIND + 7;//旧密码错误
	
	//获得服务器
	public static final String TO_CLIENT_PARAM_SERVER_LIST = "fwqlb";//服务器列表
	public static final String TO_CLIENT_PARAM_SERVER_ID = "id";//服务ID
	public static final String TO_CLIENT_PARAM_SERVER_NAME = "mz";//服务器名字
	public static final String TO_CLIENT_PARAM_SERVER_IP = "ip";//服务器ip
	public static final String TO_CLIENT_PARAM_SERVER_PORT = "dk";//服务器端口
	public static final String TO_CLIENT_PARAM_SERVER_STATE = "zt"; //服务器状态
	public static final String TO_CLIENT_PARAM_SERVER_RMD = "tj"; //推荐
	public static final String TO_CLIENT_PARAM_SERVER_PAY_PRO = "pro";//充值比例
	public static final int CMD_GET_DISTRICT = 7000;//获得服务器
	public static final int CODE_GET_DISTRICT = 100300;
	public static final int CODE_GET_DISTRICT_ERROR = CODE_GET_DISTRICT + 1; //获得服务器报错
	//usr模块
	public static final int CMD_USER_LOGIN = 1001;//登录
	public static final int CMD_USER_REG = 1002;// 注册
	public static final int CMD_USER_VALIDATA = 6008;//帐号验证
	
	//充值模块
	public static final int CMD_PAY = 8000;//充值
	public static final int CMD_PAY_GET_DATA = CMD_PAY + 1;//获得数据
	public static final int CMD_PAY_CREATE_ORDER = CMD_PAY + 2;//获得数据
	public static final String TO_CLIENT_PARAM_PRODUCT_LIST = "splb";//商品列表
	public static final String TO_CLIENT_PARAM_PRODUCT_PRICE = "price";//商品价格
	public static final String TO_CLIENT_PARAM_PRODUCT_SID = "issid";//商品id
	public static final String TO_CLIENT_PARAM_PRODUCT_INFO = "info";//商品信息
	public static final String TO_CLIENT_PARAM_PRODUCT_TOTAL = "total";//总共钻石
	public static final String TO_CLIENT_PARAM_PRODUCT_TYPE = "ptid";//商品类型 0是QY支付
	public static final String TO_CLIENT_PARAM_PRODUCT_ORDER_ID = "aliczddh"; //充值订单号
	public static final int CODE_PAY = 100400;
	public static final int CODE_PAY_MD5_ERROR = CODE_PAY + 1; //充值md5码不对
	public static final int CODE_PAY_PARAM_ERROR = CODE_PAY + 2; //充值参数错误
	public static final int CODE_PAY_CREATE_ORDER_FAIL = CODE_PAY + 3; //创建订单失败
	public static final int CODE_PAY_ORDER_NOT_EXSIT = CODE_PAY + 4; //订单不存在
	public static final int CODE_PAY_ORDER_PAYED = CODE_PAY + 5; //订单已经支付
	public static final int CODE_PAY_CONFIG_ERROR = CODE_PAY + 6; //配置数据错误
	public static final int CODE_PAY_NOT_EQUALS_MONEY = CODE_PAY + 7; //充值金额与配置金额不相等
	public static final int CODE_PAY_PLAYER_OFFLINE = CODE_PAY + 8; //玩家已经离线
	
	
	public static final int CODE_UPDATE_USR = 100500;
	public static final int CODE_UPDATE_USR_NO_USR = CODE_UPDATE_USR + 1; //没有用此用户，修改失败。
	
	
	public static Map<Integer, String> cacheErrorMsg = GameUtil.createSimpleMap();
	
	static {
		cacheErrorMsg.put(CODE_UPDATE_USR_NO_USR, "没有用此用户，修改失败。");
		cacheErrorMsg.put(CODE_UPDATE_USR_NO_USR, "没有用此用户，修改失败。");
		cacheErrorMsg.put(CODE_UPDATE_USR_NO_USR, "没有用此用户，修改失败。");
		cacheErrorMsg.put(CODE_UPDATE_USR_NO_USR, "没有用此用户，修改失败。");
		cacheErrorMsg.put(CODE_UPDATE_USR_NO_USR, "没有用此用户，修改失败。");
	}
	
	public static String getMsg(int errorCode)
	{
		return cacheErrorMsg.get(errorCode);
	}
	
}
