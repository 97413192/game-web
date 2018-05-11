package com.game.base.util;

import java.text.DecimalFormat;

/** 
 * <li>ClassName:FormatUtil <br/> 
 * <li>@Description: TODO(类描述)
 * <li>@Date:     2016年10月10日 <br/> 
 * <li>@author   zzy       
 */
public class FormatUtil {
	
	/** 
	 * <li>@Description:字符串数字转百分比
	 * <li>@param num
	 * <li>@return
	 * <li>创建人：曾志远
	 * <li>创建时间：2016年10月10日
	 * <li>修改人：
	 * <li>修改时间：
	 */  
	public static String StringToPercent(String num){
		return format(Double.valueOf(num).doubleValue()*100)+"%";
	}
	
	/** 
	 * <li>@Description:保留两位小数点
	 * <li>@param num
	 * <li>@return
	 * <li>创建人：曾志远
	 * <li>创建时间：2016年10月10日
	 * <li>修改人：
	 * <li>修改时间：
	 */  
	public static String format(double num){
		DecimalFormat de = new DecimalFormat("#.##");
		return de.format(num);
	}
	
}
