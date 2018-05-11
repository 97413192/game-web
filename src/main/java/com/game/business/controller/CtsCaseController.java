package com.game.business.controller;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.game.business.model.CtsCaseQl;
import com.game.business.service.CtsCaseQlService;
import com.game.cache.CacheLoginServer;
import com.game.constant.D;
import com.game.constant.LoginRMIConstant;
import com.game.exception.LogicException;
import com.ranger.module.po.District;
import com.ranger.module.po.Usr;
import com.ranger.remote.LoginServerRMI;
import com.ranger.remote.RMIResult;


/**
 * <li>@ClassName: CtsCaseController
 * <li>@author zzy
 * <li>@date 2016年8月31日
 * <li>
 */
@Controller
@RequestMapping("/user")
public class CtsCaseController {
	
	Logger log = Logger.getLogger(CtsCaseController.class);
	
	@Autowired private CtsCaseQlService ctsCaseQlService;
	
	@ResponseBody
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String selectDetail() {
		List<CtsCaseQl> list = ctsCaseQlService.getCtsCaseQL(new HashMap<String, Object>());
//		for(CtsCaseQl ql:list){
//			System.out.println(ql.toString());
//		}
		log.warn("list[0] = " + list.get(0).toString());
		try {
			LoginServerRMI loginServerRMI = CacheLoginServer.getLoginServer();
			if (null == loginServerRMI) {
				throw new LogicException(D.CODE_SERVER_EXCEPTION, "登录服务连接失败");
			}
			
			String retStr = "";
			//查询多个
			RMIResult result = (RMIResult) loginServerRMI.exce(LoginRMIConstant.GM_TOOL_DISTRICT_SELECT);
			if (result.getErrorCode() == D.CODE_SUC) {
					List<District> listDistrict = (List<District>) result.getResult();
					log.warn(listDistrict.get(0).toString());
					retStr = retStr + listDistrict.get(0).toString() + "\n\n\n\n";
			}else{
				throw new LogicException(result.getErrorCode());
			}

			//通过id查询
			result = (RMIResult) loginServerRMI.exce(LoginRMIConstant.GM_TOOL_DISTRICT_SELECT_BY_ID, 1);
			if (result.getErrorCode() == D.CODE_SUC) {
					District district = (District)result.getResult();
					log.warn(district.toString());
					retStr = retStr + district.toString() + "\n\n\n\n";
			}else{
				throw new LogicException(result.getErrorCode());
			}

			//查询多个
			result = (RMIResult) loginServerRMI.exce(LoginRMIConstant.GM_TOOL_USR_SELECT_BY_NAME, "%lovehappy%");
			if (result.getErrorCode() == D.CODE_SUC) {
				List<Usr> listUsr = (List<Usr>) result.getResult();
				log.warn(listUsr.get(0).toString());
				for (int i = 0; i < listUsr.size(); i++) {
					retStr = retStr + listUsr.get(i).toString() + "\n\n\n\n";
				}
				
			}else{
				throw new LogicException(result.getErrorCode());
			}		
			
			RMIResult result1 = (RMIResult) loginServerRMI.exce(LoginRMIConstant.GM_TOOL_USR_SELECT);
			
			if (result1.getErrorCode() == D.CODE_SUC) {
				List<Usr> listUsr = (List<Usr>) result1.getResult();
				for (int i = 0; i < listUsr.size(); i++) {
					log.warn(listUsr.get(i).toString());
				}
				log.warn(listUsr.get(0).toString());
				retStr = retStr + listUsr.get(0).toString() + "\n\n\n\n";
			}else{
				throw new LogicException(result1.getErrorCode());
			}
			
			return retStr;
			
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			log.warn(e.getMessage());
		} catch (LogicException e) {
			// TODO Auto-generated catch block
			log.warn(e.getMessage());
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.warn(e.getMessage());
		}
		return "ok";
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String welcome(ModelMap model) {
		model.addAttribute("message", "Maven Web Project + Spring 3 MVC");
		return "welcome";
	}

	@RequestMapping(value = "/welcome/{name}", method = RequestMethod.GET)
	public String welcomeName(@PathVariable String name, ModelMap model) {

		model.addAttribute("message", "Maven Web Project + Spring 3 MVC - "
				+ name);
		return "welcome";

	}
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String main() {
		log.warn("mainmainmain");
		return "main";
	}
	
	@RequestMapping("/tologin.do")
	public String toLogin(){
		System.out.println("dsfefw");
		return "login";
	}
	@RequestMapping("/login.do")
	public String login(HttpServletRequest req,HttpServletResponse res)throws ServletException, IOException{
		String path = ctsCaseQlService.login(req,res);
		return path;
	}
	@RequestMapping("/createImg.do")
	public void createImg(HttpServletRequest req,HttpServletResponse res)throws ServletException, IOException{
		ctsCaseQlService.createImg(req,res);
	}
	@RequestMapping("/toRegist.do")
	public String regist(){
		return "regist";
	}
	@RequestMapping("/regist.do")
	public String regist(HttpServletRequest req,HttpServletResponse res)throws ServletException, IOException{
		String path = ctsCaseQlService.regist(req,res);
		return path;
	}
}








