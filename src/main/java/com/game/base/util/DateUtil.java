package com.game.base.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/** 
 * <li>ClassName:DateUtil <br/> 
 * <li>@Description: TODO(类描述)
 * <li>@Date:     2016年9月28日 <br/> 
 * <li>@author   zzy       
 */
public class DateUtil {
	
	/** 
	 * <li>@Description:yyyy-MM-dd HH:mm:ss
	 * <li>@return
	 * <li>创建人：曾志远
	 * <li>创建时间：2016年9月28日
	 * <li>修改人：
	 * <li>修改时间：
	 */  
	public static String getCurrTime(){
		SimpleDateFormat form = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return form.format(new Date());
	}
	
	/** 
	 * <li>@Description:
	 * <li>@return yyyy-MM-01
	 * <li>创建人：曾志远
	 * <li>创建时间：2016年9月29日
	 * <li>修改人：
	 * <li>修改时间：
	 */  
	public static String getCurrMonth(){
		
		SimpleDateFormat form = new SimpleDateFormat("yyyy-MM-");
		String cur = form.format(new Date());
		return cur+"01";
	}
	
	/** 
	 * <li>@Description:
	 * <li>@return yyyyMMdd
	 * <li>创建人：曾志远
	 * <li>创建时间：2016年10月11日
	 * <li>修改人：
	 * <li>修改时间：
	 */  
	public static String getCurrDate(){
		
		SimpleDateFormat form = new SimpleDateFormat("yyyyMMdd");
		String cur = form.format(new Date());
		return cur;
	}
	
	/** 
	 * <li>@Description:得到当前月
	 * <li>@return num
	 * <li>创建人：曾志远
	 * <li>创建时间：2016年10月10日
	 * <li>修改人：
	 * <li>修改时间：
	 */  
	public static int getCurrMonthNum(){
		
		return Calendar.getInstance().get(Calendar.MONTH)+1;
	}
	
	/** 
	 * <li>@Description:yyyy-MM-01
	 * <li>@return
	 * <li>创建人：曾志远
	 * <li>创建时间：2016年9月29日
	 * <li>修改人：
	 * <li>修改时间：
	 */  
	public static String getNextMonth(){
		Calendar ca = Calendar.getInstance();
		int curMonth = ca.get(Calendar.MONTH);
		ca.set(Calendar.MONTH, curMonth+2);
		int nextMonth = ca.get(Calendar.MONTH);
		String _nextMonth = nextMonth<10?"0"+nextMonth:nextMonth+"";
		int year = ca.get(Calendar.YEAR);
		return year+"-"+_nextMonth+"-01";
	}
	
	/** 
	 * <li>@Description:
	 * <li>@return yyyyMM
	 * <li>创建人：曾志远
	 * <li>创建时间：2016年9月29日
	 * <li>修改人：
	 * <li>修改时间：
	 */  
	public static String getCurrMonth2(){
		
		SimpleDateFormat form = new SimpleDateFormat("yyyyMM");
		String cur = form.format(new Date());
		return cur;
	}
	
	/** 
	 * <li>@Description:得到日期偏移量
	 * <li>@return yyyyMMdd
	 * <li>创建人：曾志远
	 * <li>创建时间：2016年9月29日
	 * <li>修改人：
	 * <li>修改时间：
	 */  
	public static String getDateOffset(int num){
		Calendar ca = Calendar.getInstance();
		ca.add(Calendar.DAY_OF_MONTH, num);
		SimpleDateFormat form = new SimpleDateFormat("yyyyMMdd");
		return form.format(ca.getTime());
	}
	
	/** 
	 * <li>@Description:yyyyMM01
	 * <li>@return
	 * <li>创建人：曾志远
	 * <li>创建时间：2016年9月29日
	 * <li>修改人：
	 * <li>修改时间：
	 */  
	public static String getNextMonth2(){
		Calendar ca = Calendar.getInstance();
		int curMonth = ca.get(Calendar.MONTH);
		ca.set(Calendar.MONTH, curMonth+2);
		int nextMonth = ca.get(Calendar.MONTH);
		String _nextMonth = nextMonth<10?"0"+nextMonth:nextMonth+"";
		int year = ca.get(Calendar.YEAR);
		return year+""+_nextMonth+"01";
	}
	
	/** 
	 * <li>@Description:yyyyMM
	 * <li>@return
	 * <li>创建人：曾志远
	 * <li>创建时间：2016年9月29日
	 * <li>修改人：
	 * <li>修改时间：
	 */  
	public static String getLastMonth(){
		Calendar ca = Calendar.getInstance();
		int curMonth = ca.get(Calendar.MONTH);
		ca.set(Calendar.MONTH, curMonth);
		int nextMonth = ca.get(Calendar.MONTH);
		String _nextMonth = nextMonth<10?"0"+nextMonth:nextMonth+"";
		int year = ca.get(Calendar.YEAR);
		return year+""+_nextMonth;
	}
	
	public static void main(String[] args) {
		
//		System.out.println(getCurrMonth2());
//		System.out.println(getNextMonth2());
//		System.out.println(getCurrTime());
//		System.out.println(getLastMonth());
		System.out.println(getCurrDate());
		System.out.println(getDateOffset(-7));
		System.out.println(getDateOffset(-8));
		System.out.println(getDateOffset(-14));
	}
}
