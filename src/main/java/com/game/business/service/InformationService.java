package com.game.business.service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.game.base.util.Result;
/** 
 * <li>ClassName:ManagerService <br/> 
 * <li>@Description: 消息业务接口
 * <li>@Date:     2017年02月7日<br/> 
 * <li>@author   周强      
 */
public interface InformationService {
	/**请求增加管理员页面*/
	public String toAddInformation(HttpServletRequest req)throws ServletException, IOException;
	/**添加消息*/
	public Result addInformation(HttpServletRequest req,String text,Integer category)throws ServletException, IOException;
	/**请求所有消息记录的页面*/
	public String toSelectAllInformation(HttpServletRequest req)throws ServletException, IOException;
	/**请求单个消息记录的页面*/
	public String toSelectInformation(HttpServletRequest req)throws ServletException, IOException;
	/** 修改信息*/
	public Result alertInformation(HttpServletRequest req,Integer id,String message,Integer category)
			throws ServletException, IOException;
	/** 通过id删除信息*/
	public Result deleteInformation(HttpServletRequest req,Integer id)throws ServletException, IOException;
	/**请求现在使用的跑马灯和消息公告*/
	public String toSelectAllCategoryInformation(HttpServletRequest req)throws ServletException, IOException;
	/**请求现在使用的跑马灯和消息公告*/
	public Result toSelectCategoryInformation(HttpServletRequest req)throws ServletException, IOException;
	
	/**----------------------------------------------------------------------*/
	/**判断playerId是否存在*/
	public Result selectOnePage(HttpServletRequest req,Integer gameId)throws ServletException, IOException;
	/**查询单个玩家信息*/
	public String selectOneInformation(HttpServletRequest req,Integer gameId,Integer index)throws ServletException, IOException;
	/**修改玩家信息页面*/
	public String updateSuperPortPage(HttpServletRequest req,Integer gameId)throws ServletException, IOException;
	/**修改超端功能*/
	public Result updateSuperPortGL(HttpServletRequest req,Integer gameId,Integer isSuperClient)throws ServletException, IOException;
	public String toSelectShareConfig(HttpServletRequest req)throws ServletException, IOException;
	public String updateShareConfig(HttpServletRequest req)throws ServletException, IOException;
	public String updateBindConfig(HttpServletRequest req)throws ServletException, IOException;
	public String toSelectBindConfig(HttpServletRequest req)throws ServletException, IOException;
	public String toSelectRedemption(HttpServletRequest req)throws ServletException, IOException;
	public String toUpdateRedemption(HttpServletRequest req, Integer id, String title, Integer integral, Integer fk)throws ServletException, IOException;
	public String updateRedemption(HttpServletRequest req)throws ServletException, IOException;
	public String delRedemption(HttpServletRequest req, Integer id)throws ServletException, IOException;
	public String addRedemption(HttpServletRequest req)throws ServletException, IOException;
	
	
}











