package com.game.business.controller;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.game.base.util.Result;
import com.game.business.service.InformationService;
import com.game.business.service.SysSetService;
/**
 * 
 * <li>@ClassName: 消息中心控制层
 * <li>@author 周强
 * <li>@date 2017年02月7日
 *
 */
@Controller
@RequestMapping("/information")
public class InformationController {
	Log log = LogFactory.getLog(SystemSettingsController.class);
	@Resource
	private InformationService informationService;
	
	/**请求增加消息页面*/
	@RequestMapping("/toAddInformation.do")
	public String toAddInformation(HttpServletRequest req)throws ServletException, IOException{
		return informationService.toAddInformation(req);
	}
	
	/**添加消息*/
	@RequestMapping("/addInformation.do")
	@ResponseBody
	public Result addInformation(HttpServletRequest req,String text,Integer category)
		throws ServletException, IOException{
		//System.out.println("text:"+text+",category:"+category);
		return informationService.addInformation(req, text, category);
	}
	
	/**请求所有消息记录的页面*/
	@RequestMapping("/toSelectAllInformation.do")
	public String toSelectAllInformation(HttpServletRequest req)throws ServletException, IOException{
		return informationService.toSelectAllInformation(req);
	}
	
	/**请求单个消息记录的页面*/
	@RequestMapping("/toSelectInformation.do")
	public String toSelectInformation(HttpServletRequest req)throws ServletException, IOException{
		return informationService.toSelectInformation(req);
	}
	
	/** 修改信息*/
	@RequestMapping("/alertInformation.do")
	@ResponseBody
	public Result alertInformation(HttpServletRequest req,Integer id,String message,Integer category)
			throws ServletException, IOException{
			return informationService.alertInformation(req,id,message, category);
		}
	
	/** 通过id删除信息*/
	@RequestMapping("/deleteInformation.do")
	@ResponseBody
	public Result deleteInformation(HttpServletRequest req,Integer id)throws ServletException, IOException{
			return informationService.deleteInformation(req,id);
		}
	
	/**请求现在使用的跑马灯和消息公告*/
	@RequestMapping("/toSelectAllCategoryInformation.do")
	public String toSelectAllCategoryInformation(HttpServletRequest req)throws ServletException, IOException{
		return informationService.toSelectAllCategoryInformation(req);
	}
	
	/**请求现在使用的跑马灯和消息公告*/
	@RequestMapping("/toSelectCategoryInformation.do")
	@ResponseBody
	public Result toSelectCategoryInformation(HttpServletRequest req)throws ServletException, IOException{
		return informationService.toSelectCategoryInformation(req);
	}
	
	
	
	/**请求现在使用的跑马灯和消息公告*/
//	@RequestMapping("/toTestInformation.do")
//	@ResponseBody
//	public Result toTestInformation(HttpServletRequest req)throws ServletException, IOException{
//		Result result = new Result();
//		result.setData("跑马灯公告!");
//		
//		
//		return result;
//	}
	/**----------------------------------------------------------------------*/	
	/**请求到联盟超端查询playerId页面*/
	@RequestMapping("/queryPlayerPage.do")
	public String queryPlayerPage(HttpServletRequest req)throws ServletException, IOException{
		return "informationManagment/queryPlayerPage";
	}
	/**判断playerId是否存在*/
	@RequestMapping("/selectOnePage.do")
	@ResponseBody
	public Result selectOnePage(HttpServletRequest req,Integer gameId)throws ServletException, IOException{
		return informationService.selectOnePage(req,gameId);
	}
	/**查询单个玩家信息*/
	@RequestMapping("/selectOneInformation.do")
	public String selectOneInformation(HttpServletRequest req,Integer gameId,Integer index) throws ServletException, IOException{
		return informationService.selectOneInformation(req,gameId,index);
	}
	/**修改玩家信息页面*/
	@RequestMapping("/updateSuperPortPage.do")
	public String updateSuperPortPage(HttpServletRequest req,Integer gameId) throws ServletException, IOException{
		return informationService.updateSuperPortPage(req,gameId);
		
	}
	/**查询分享配置信息页面*/
	@RequestMapping("/toSelectShareConfig.do")
	public String toSelectShareConfig(HttpServletRequest req) throws ServletException, IOException{
		return informationService.toSelectShareConfig(req);
		
	}
	/**修改分享配置信息页面*/
	@RequestMapping("/updateShareConfig.do")
	public String updateShareConfig(HttpServletRequest req) throws ServletException, IOException{
		return informationService.updateShareConfig(req);
		
	}
	/**查询绑定奖励配置信息页面*/
	@RequestMapping("/toSelectBindConfig.do")
	public String toSelectBindConfig(HttpServletRequest req) throws ServletException, IOException{
		return informationService.toSelectBindConfig(req);
		
	}
	/**修改绑定奖励配置信息页面*/
	@RequestMapping("/updateBindConfig.do")
	public String updateBindConfig(HttpServletRequest req) throws ServletException, IOException{
		return informationService.updateBindConfig(req);
		
	}
	/**查询商品兑换信息页面*/
	@RequestMapping("/toSelectRedemption.do")
	public String toSelectRedemption(HttpServletRequest req) throws ServletException, IOException{
		return informationService.toSelectRedemption(req);
		
	}
	/**到修改商品兑换信息页面*/
	@RequestMapping("/toUpdateRedemption.do")
	public String toUpdateRedemption(HttpServletRequest req,Integer id,String title,Integer integral,Integer fk) throws ServletException, IOException{
		return informationService.toUpdateRedemption(req,id,title,integral,fk);
		
	}
	/**修改商品兑换信息页面*/
	@RequestMapping("/updateRedemption.do")
	public String updateRedemption(HttpServletRequest req) throws ServletException, IOException{
		return informationService.updateRedemption(req);
		
	}
	/**增加商品兑换信息页面*/
	@RequestMapping("/addRedemption.do")
	public String addRedemption(HttpServletRequest req) throws ServletException, IOException{
		return informationService.addRedemption(req);
		
	}
	/**到增加商品兑换信息页面*/
	@RequestMapping("/toAddRedemption.do")
	public String toAddRedemption(HttpServletRequest req) throws ServletException, IOException{
		return "informationManagment/addRedemption";
		
	}
	/**删除商品兑换信息页面*/
	@RequestMapping("/delRedemption.do")
	public String delRedemption(HttpServletRequest req,Integer id) throws ServletException, IOException{
		return informationService.delRedemption(req,id);
		
	}
	/**修改超端功能*/
	@RequestMapping("/updateSuperPortGL.do")
	@ResponseBody
	public Result updateSuperPortGL(HttpServletRequest req,Integer gameId,Integer isSuperClient)throws ServletException, IOException{
		return informationService.updateSuperPortGL(req,gameId,isSuperClient);
	}
	
	
	
	
	
	
	
}























