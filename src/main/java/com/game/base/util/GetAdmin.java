package com.game.base.util;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/** 
 * <li>@Description:获得当前用户名
 * <li>@return admin字符串
 * <li>创建人：周强
 * <li>创建时间：2016年11月2日
 * <li>修改人：
 * <li>修改时间：
 */ 
/*
 * 返回当前用户的名字
 */
public class GetAdmin {
	/**
	 * 
	 * @param req
	 * @return admin 当前管理员名
	 * @throws ServletException
	 * @throws IOException
	 */
	public static String getAdmin(HttpServletRequest req)throws ServletException, IOException{
		HttpSession session = req.getSession();
		String admin = (String)session.getAttribute("admin");
		return admin;
	}
	
	/**
	 * 将session中的用户名置为null
	 * @param req
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public static void dropOutAdmin(HttpServletRequest req)throws ServletException, IOException{
		HttpSession session = req.getSession();
		session.setAttribute("admin", null);
	}
	
	/**
	 * 
	 * @param req
	 * @return Result 当当前管理员不存在是，返回此结果
	 * @throws ServletException
	 * @throws IOException
	 */
	public static Result getResult(HttpServletRequest req)throws ServletException, IOException{
		Result result = new Result();
		result.setStatus(-1);
		return result;
	}
	
}
