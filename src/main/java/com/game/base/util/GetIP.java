package com.game.base.util;
/** 
 * <li>ClassName:GetIP <br/> 
 * <li>@Description: ip相关操作
 * <li>@Date:     2016年10月26日 <br/> 
 * <li>@author   周强      
 */
public class GetIP {
	/** 
	 * <li>@Description:获取当前用好ip地址
	 * <li>@return ip字符串
	 * <li>创建人：周强
	 * <li>创建时间：2016年10月26日
	 * <li>修改人：
	 * <li>修改时间：
	 */  
	public static String getRemoteHost(javax.servlet.http.HttpServletRequest request){
	    String ip = request.getHeader("x-forwarded-for");
	    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
	        ip = request.getHeader("Proxy-Client-IP");
	    }
	    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
	        ip = request.getHeader("WL-Proxy-Client-IP");
	    }
	    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
	        ip = request.getRemoteAddr();
	    }
	    return ip.equals("0:0:0:0:0:0:0:1")?"127.0.0.1":ip;
	}
}
