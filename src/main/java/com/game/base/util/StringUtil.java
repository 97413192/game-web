package com.game.base.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 
 * @author Administrator
 */
public class StringUtil {
	
	/**
	 * 将String转换为Integer
	 * @param s
	 * @return
	 */
	public static Integer parseInt(String s) {
		Integer n 	= 0;
		try {
			n 		= Integer.parseInt(s);
		} catch (Exception e) {
			
		}
		return n;
	}
	
	/**
	 * 将String转换为Float
	 * @param s
	 * @return
	 */
	public static Float parseFloat(String s) {
		Float f 	= 0.0f;
		try {
			f  		= Float.parseFloat(s);
		} catch (Exception e) {
			
		}
		return f;
	}

	/**
	 * 将String转换为Double
	 * @param s
	 * @return
	 */
	public static double parseDouble(String s) {
		Double d 	= 0.0;
		try {
			d	  	= Double.parseDouble(s);
		} catch (Exception e) {
			
		}
		return d;
	}
	
	/**
	 * 字符串转换为日期
	 * 更多日期处理的请参考：DateUtil.java
	 * @param s
	 * @return
	 */
	public static Date strToDate(String s) {
		Date date 		   = new Date();
		ParsePosition pos  = new ParsePosition(0);;
		try {
			if (s.matches("[0-9]{4}-[0-9]{2}-[0-9]{2}")) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				date 	   = sdf.parse(s, pos);
			}
		} catch (Exception e) {
			
		}
		return date;
	}
	
	public static String join(Object...os) {
		StringBuilder sb = new StringBuilder();
		for (Object o : os) {
			sb.append(o);
		}
		return sb.toString();
	}
	
	/*public static boolean isEmpty(String str){
		if(str == null || str.equals(""))
			return true;
		return false;
	}*/
	
	public static boolean isEmpty(String str){
		if(str != null || !str.isEmpty())
			return true;
		return false;
	}
	
	
	public static String toMacFormat(String mac){
		mac = mac.replace(":", "");
		if(mac.length() < 1)
			return "";
		String value = mac.toUpperCase();
		String k = "";
        for(int i = 0;i < value.length(); i++){
        	char c = value.charAt(i);
        	if(i % 2 == 0 && i > 0)
        		k += (":" + c);
        	else
        		k += c;
        }
        return k;
	}
	
	/**
	 * 参数修复
	 * @param param
	 * @return
	 */
	public static String paramRevise(Object param){
		if(param == null){
			return "";
		}else{
			try {
				return URLDecoder.decode(param.toString().trim(), "utf-8").trim();
			} catch (UnsupportedEncodingException e) {
//				logger.error("参数修复出现异常：" +  param);
				e.printStackTrace();
			}
		}
		return param.toString().trim();
	}
	
	public static boolean isEmail(String email) {
//		  String regex = "[a-zA-Z_-]{0,}[0-9]{0,}@(([a-zA-z0-9]-*){1,}\\.){1,3}[a-zA-z\\-]{1,}" ;
//      String regex = "[a-zA-Z0-9_-]{0,}@(([a-zA-Z0-9]-*){1,}\\.){1,3}[a-zA-Z\\-]{1,}";
//      boolean result = match( regex ,email );
//      return result;
//		合法E-mail地址：     
//		邮箱的组成；邮箱用户名@域名
//		邮箱用户名：必须是有a-z0-9.-_组成，并且开头和结尾必须是有a-z0-9组成。长度在18位
//		域名：大部分是由a-z0-9.-组成（注意没有"_",只能使用中横线
//		说明：上面的只是正常情况，但是存在特殊域和邮箱用户名名比如中文等
		int firstIndexAt  = email.indexOf("@");
		int lastIndexAt = email.lastIndexOf("@");
		int firstIndexDo = email.indexOf(".");
		int firstIndexApostrophe = email.indexOf("'");//不能出现撇号(')，避免SQL注入
		int firstIndexSemicolon = email.indexOf(";");//不能出现分号(;)，避免SQL注入
		if(firstIndexAt != lastIndexAt || firstIndexAt == 0 || firstIndexDo == 0 || firstIndexApostrophe > -1 || firstIndexSemicolon > -1){
			return false;
		}
		//@符号不能出现在最后一位
		if(lastIndexAt == email.length()-1){
			return false;
		}
		return true;
  } 
}
