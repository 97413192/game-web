package com.game.business.service;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import com.game.base.util.Result;
import com.game.game.model.Player;
/** 
 * <li>ClassName:ManagerService <br/> 
 * <li>@Description: 玩家业务接口
 * <li>@Date:     2016年11月8日 <br/> 
 * <li>@author   周强      
 */
public interface GamePlayerService {
	
	/** 请求通过游戏id查询玩家信息页面*/
	public String toSelectPlayer(HttpServletRequest req)throws ServletException, IOException;
	/** 通过游戏id查询玩家信息*/
	public Result selectPlayerById(HttpServletRequest req,Integer gameId)throws ServletException, IOException;
	/** 给玩家添加房卡*/
	public String toAddRoomCard(HttpServletRequest req,String pNiceName,Integer gameId,Integer roomCard)throws ServletException, IOException;
	/** 给玩家添加房卡*/
	public Result addRoomCard(HttpServletRequest req,Integer gameId,Integer addRoomCard)throws ServletException, IOException;
	/** 请求查询玩家所有交易记录页面*/
	public String toSelectRecodByCondition(HttpServletRequest req,Integer index)throws ServletException, IOException;
	/** 请求查询玩家注册时房卡数的页面*/
	public String toSelectPlayerStartRoomCard(HttpServletRequest req)throws ServletException, IOException;
	/** 修改玩家注册时房卡数*/
	public Result alertPlayerStartRoomCard(HttpServletRequest req,String roomCardNumber)throws ServletException, IOException;
	/** 修改玩家绑定时赠送的房卡*/
	public Result updateRoomCardBin(HttpServletRequest req,Integer roomCardNumber)throws ServletException, IOException;
	/** 查询所有玩家*/
	public String selectAllPlayer(HttpServletRequest req,Integer index)throws ServletException, IOException;
	//请求增加玩家页面
	public String toAddPlayer(HttpServletRequest req)throws ServletException, IOException;
	//请求修改玩家参数页面
	public String toAlterPlayer(HttpServletRequest req)throws ServletException, IOException;
	//请求删除玩家页面
	public String toDeletePlayer(HttpServletRequest req)throws ServletException, IOException;
	//添加玩家
	Result addPlayer(HttpServletRequest req,String name,String pwd,String system)
			throws ServletException, IOException;
	//修改玩家名字和密码
	public Result alterPlayer(HttpServletRequest req,String olduser,String newuser,String newpwd)
			throws ServletException, IOException;
	//查询玩家参数
	public Result 
	selectPlayer(HttpServletRequest req,String name)
			throws ServletException, IOException;
	//请求所有玩家
	public String toAllPlayer(HttpServletRequest req,String playerIndex)throws ServletException, IOException;
	//请求查询页面
	public String showPlayer(HttpServletRequest req)throws ServletException, IOException;
	/**查询绑定玩家页面*/
	public String toSelectBindedPlayer(HttpServletRequest req,String name)throws ServletException, IOException;

	public String toSelectBindedPlayers(HttpServletRequest req) throws ServletException, IOException ;
	
	//查询所有的实名认证的玩家
	public String getAllRealNamePlayer(HttpServletRequest req,String playerIndex) throws ServletException,IOException;
	//查询所有的实名认证记申请
	public String getRealNameApply(HttpServletRequest req, String index) throws ServletException,IOException;
	//查询所有的实名申请记录 是否批准或者未批准等相关信息
	public String getApplyRecord(HttpServletRequest req, String index) throws ServletException, IOException;
	//将实名申请记录插入数据库
	public Result insertRealNameApply(HttpServletRequest request,Player player) throws ServletException,IOException;
	//查询所有的实名申请记录
	public String getAllRealNameApply(HttpServletRequest req, String index) throws ServletException,IOException;
	//查询单个玩家信息
	public Player getSinglePlayer(Integer valueOf);
	//更新玩家实名认证状态
	public Result updateApplyState(HttpServletRequest req, Integer gameId,String state)throws ServletException, IOException;
	//更新玩家钻石奖励
	public Result updateTreasure(HttpServletRequest req, Integer gameId)throws ServletException, IOException;
	//查询代理商账户所绑定的所有玩家
	public String getAllBindPlayers(HttpServletRequest req,String account,String index)throws ServletException, IOException;
	//执行解绑操作
	public Result unbindPlayer(HttpServletRequest req, String account, String gameId)throws ServletException, IOException;
	
	
}
