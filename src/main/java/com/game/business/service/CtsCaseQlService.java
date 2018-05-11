package com.game.business.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.game.business.model.CtsCaseQl;

/** 
 * <li>ClassName:CtsCaseQlService <br/> 
 * <li>@Description: TODO(类描述)
 * <li>@Date:     2016年10月10日 <br/> 
 * <li>@author   zzy       
 */
public interface CtsCaseQlService {
	public int addCtsCaseQl(CtsCaseQl cases);
	
	public List<CtsCaseQl> getCtsCaseQL(Map<String,Object> map);
	
	public String login(HttpServletRequest req,HttpServletResponse res)throws ServletException, IOException;
	public void createImg(HttpServletRequest req,HttpServletResponse res)
			throws ServletException,IOException;
	public String regist(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException;
}
