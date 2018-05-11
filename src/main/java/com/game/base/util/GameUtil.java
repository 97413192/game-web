package com.game.base.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import org.apache.commons.logging.Log;

@SuppressWarnings("unchecked")
public class GameUtil{
	
	public static BlockingQueue<ArrayList> cacheList = new LinkedBlockingQueue<ArrayList>(2000);

	public static BlockingQueue<Map> cacheMap = new LinkedBlockingQueue<Map>(2000);

	public static final Random r = new Random();

//	public static final Calendar  c= Calendar.getInstance();
	
	static{
		Thread listCreateT = new Thread(){
			@Override
			public void run() {
				while(true){
					try {
						cacheList.put(new ArrayList());
					} catch (InterruptedException e) {
					}
				}
			}
		};
		listCreateT.setName("listCreateThread");
		listCreateT.setDaemon(true);//设置为守护线程
		listCreateT.start();

		Thread mapCreateT = new Thread(){
			@Override
			public void run() {
				while(true){
					try {
						cacheMap.put(new HashMap());
					} catch (InterruptedException e) {
					}
				}
			}
		};
		mapCreateT.setName("mapCreateThread");
		mapCreateT.setDaemon(true);
		mapCreateT.start();
	}


	/**
	 * 创建并发操作安全的Map
	 * 注意此处安全代表该Map的各方法内部安全
	 * @param <T>
	 * @param <K>
	 * @param capacity
	 * @return
	 */
	public static <T,K> Map<T,K> createMap(int capacity){
		return new ConcurrentHashMap<T,K>(capacity);
	}


	public static final List toList(Map m) {
		List ret = new ArrayList();
		ret.addAll(m.values());
		return ret;
	}

	public static <T,K> Map<T,K> createMap(){
		return new ConcurrentHashMap<T,K>();
	}


	public static  <T,K> Map <T,K> createSimpleMap(){
		Map m = cacheMap.poll();
		if(m==null)
			m =  new HashMap <T,K>();
		return m;
	}


	public static final <T> List<T> createList(){
		ArrayList a = cacheList.poll();
		if(a==null)
			a = new ArrayList<T>();
		return a;
	}


	public static Object arrayStrToTgtArray(String input,Class<?> tgtClz){
		String[] values = input.split(",");
		if(tgtClz.equals( String[].class)){
			if(input.length()==0)
				return new String[0];
			return values;
		}else if(tgtClz.equals(Integer[].class)){
			if(input.length()==0)
				return new Integer[0];
			Integer[] ret = new Integer[values.length];
			for(int i=0;i<ret.length;i++){
				try{
					ret[i] = Integer.valueOf(values[i]);
				}catch (NumberFormatException e) {
					ret[i] = 0;
				}
			}
			return ret;
		}else if(tgtClz.equals(Long[].class)){
			if(input.length()==0)
				return new Long[0];
			Long[] ret = new Long[values.length];
			for(int i=0;i<ret.length;i++){
				try{
					ret[i] = Long.valueOf(values[i]);
				}catch (NumberFormatException e) {
					ret[i] = 0L;
				}
			}
			return ret;
		}else if(tgtClz.equals(Boolean[].class)){
			if(input.length()==0)
				return new Boolean[0];
			Boolean[] ret = new Boolean[values.length];
			for(int i=0;i<ret.length;i++){
				ret[i] = Boolean.valueOf(values[i]);
			}
			return ret;
		}
		return null;
	}


	public static Object strToTgtType(String value,Class<?> tgtClz){

		if(tgtClz.equals( String.class)){
			return value;
		}else if(tgtClz.equals(Integer.class)){
			if(value == null||value.trim().length()==0)
				return new Integer(0);
			Integer ret = Integer.valueOf(value);
			return ret;
		}else if(tgtClz.equals(Long.class)){
			if(value == null||value.trim().length()==0)
				return new Long(0);
			Long ret = Long.valueOf(value);
			return ret;
		}else if(tgtClz.equals(Boolean.class)){
			if(value == null||value.trim().length()==0)
				return new Boolean(false);
			Boolean ret = Boolean.valueOf(value);
			return ret;
		}
		return null;
	}

	/**
	 * 返回一段字符串中 {} 中的内容
	 * 不支持嵌套
	 * @param input
	 * @return
	 */
	public static String getContentFromBrace(String input){
		return input.replaceAll(".*\\{","").replaceAll("\\}.*", "");
	}


	/**
	 * 获取大于输入数的 最小的 2的N次幂
	 * @param input
	 * @return
	 */
	public static int getLeastPowerOf2BiggerThan(int input){
		input = Math.abs(input);
		input |= input>>1;
			input |= input>>2;
			input |= input>>4;
			input |= input>>8;
			input |= input>>16;
			input +=1;
			if(input<0)
				input>>=1;
			return input;
	}

	public static int[] getNumAround(int tgt,int delta,int floor,int ceiling){
		int len = 0;
		int sm = 0;
		int bg = 0;
		boolean smOk = false;
		boolean bgOk = false;
		if(smOk=(sm=tgt-delta)>floor)
			len++;
		if(bgOk=(bg=tgt+delta)<ceiling)
			len++;
		int[] ret = new int[len];
		int ix = 0;
		if(smOk)
			ret[ix++] = sm;
		if(bgOk)
			ret[ix] = bg;
		return ret;
	}



	/**
	 * 交叉填充制定序列
	 * @param <T>
	 * @param first
	 * @param second
	 * @param sequence
	 */
	public static <T> void  crossFillSequence(List<T> first,List<T> second,List<T> sequence){
		int asize = first.size();
		int dsize = second.size();
		int size = Math.max(asize,dsize);
		for(int i = 0 ; i < size ; i++){
			if(i<asize)
				sequence.add(first.get(i));
			if(i<dsize)
				sequence.add(second.get(i));
		}
	}

	public static String writeExInfo(String str,String infoSign,Object value){
		if(str == null)
			str = "";
		StringBuffer sb = new StringBuffer(str.startsWith(",")?str:","+str);
		sb.append(infoSign);
		sb.append("=");
		sb.append(value);
		sb.append(",");
		return sb.toString();
	}

	public static String readExInfo(String exInfo,String infoSign){
		if(exInfo==null)
			return "";
		if(!exInfo.matches(".*,"+infoSign+"=.*"))
			return null;
		return exInfo.replaceAll(".*,"+infoSign+"=", "").replaceAll(",.*", "");
	}


	private static String KEY_WORDS = "!\"#$%&'()*+,-./:;<=>?@[\\]^`{|}~";

	public static boolean haveKeywords(String s) {
		char[] cs = KEY_WORDS.toCharArray();
		for (char c : cs) {
			int p = s.indexOf(c);
			if (p >= 0)
				return true;
		}

		return false;
	}

	
	
	
//	public static boolean haveDirtywords(String s){
//		Collection<DirtyWordsCfg> dirtyWords  = CacheCfg.DirtyWordsCfg.values();
//		s = s.replaceAll("\\s+","");
//		for(DirtyWordsCfg cfg:dirtyWords){
//			if(s.indexOf(cfg.getDirtyWord())>=0){
//				return true;
//			}
//		}
//		return false;
//	}
	
//	public static void main(String[] args) throws Exception {
//		CfgServiceImpl s = new CfgServiceImpl();
//		s.loadAllCfg();
//	
//		
//		
//		
//		
//	}
	
	
	
//	public static String changeDirtywords(String s){
//		Collection<DirtyWordsCfg> dirtyWords  = CacheCfg.DirtyWordsCfg.values();
//		s = s.replaceAll("\\s+","");
//		for(DirtyWordsCfg cfg:dirtyWords){
//			if(s.indexOf(cfg.getDirtyWord())>=0){
//				s  = s.replaceAll(cfg.getDirtyWord(), "*");
//			}
//		}
//		return s;
//	}
	
	

	public static int setIntFlag(int src,int index){
		return src|(1<<index);
	}

	public static int resetIntFlag(int src,int index){
		return src&~(1<<index);
	}

	public static boolean checkIntFlag(int src,int index){
		return (src&(1<<index))!= 0;
	}


	public static String getJavaType(ResultSetMetaData rsmd, String columnName)
	throws SQLException {
		int count = rsmd.getColumnCount();
		for (int i = 1; i <= count; i++) {
			String key = rsmd.getColumnName(i);
			if (!key.equals(columnName))
				continue;
			return getJavaType(rsmd, i);
		}
		return "";
	}



	public static String getJavaType(ResultSetMetaData rsmd, int i) throws SQLException{
		int count = rsmd.getColumnCount();
		if (i > count)
			return "";
		int columnType = rsmd.getColumnType(i);
		switch (columnType) {
		case java.sql.Types.TIMESTAMP:
			return java.util.Date.class.getName();
		case java.sql.Types.TIME:
			return Time.class.getName();
		case java.sql.Types.VARBINARY:
		case java.sql.Types.SQLXML:
		case java.sql.Types.ROWID:
		case java.sql.Types.OTHER:
		case java.sql.Types.NCLOB:
		case java.sql.Types.LONGVARBINARY:
		case java.sql.Types.JAVA_OBJECT:
		case java.sql.Types.NUMERIC:
		case java.sql.Types.DECIMAL:
		case java.sql.Types.DATE:
		case java.sql.Types.CLOB:
		case java.sql.Types.BLOB:
		case java.sql.Types.BINARY:
		case java.sql.Types.ARRAY:
			return byte[].class.getSimpleName();
		case java.sql.Types.TINYINT:
			return Byte.class.getSimpleName();
		case java.sql.Types.SMALLINT:
			return Short.class.getSimpleName();
		case java.sql.Types.REAL:
		case java.sql.Types.INTEGER:
		case java.sql.Types.FLOAT:
		case java.sql.Types.DOUBLE:
			return Integer.class.getSimpleName();
		case java.sql.Types.BIGINT:
			return Long.class.getSimpleName();
		case java.sql.Types.BOOLEAN:
		case java.sql.Types.BIT:
			return Boolean.class.getSimpleName();
		case java.sql.Types.VARCHAR:
		case java.sql.Types.NVARCHAR:
		case java.sql.Types.NCHAR:
		case java.sql.Types.LONGNVARCHAR:
		case java.sql.Types.LONGVARCHAR:
			return String.class.getSimpleName();
		case java.sql.Types.CHAR:{
			return String.class.getSimpleName();
//			return columnName.equals("id")?UUID.class.getSimpleName():String.class.getSimpleName();
		}
		default:
			break;
		}
		return "";
	}


	public static String getJavaTypeDefaultValue(ResultSetMetaData rsmd, int i) throws SQLException{
		int count = rsmd.getColumnCount();
		if (i > count)
			return "";
		int columnType = rsmd.getColumnType(i);
		switch (columnType) {
		case java.sql.Types.VARBINARY:
		case java.sql.Types.TIMESTAMP:
		case java.sql.Types.TIME:
		case java.sql.Types.SQLXML:
		case java.sql.Types.ROWID:
		case java.sql.Types.OTHER:
		case java.sql.Types.NCLOB:
		case java.sql.Types.LONGVARBINARY:
		case java.sql.Types.JAVA_OBJECT:
		case java.sql.Types.NUMERIC:
		case java.sql.Types.DECIMAL:
		case java.sql.Types.DATE:
		case java.sql.Types.CLOB:
		case java.sql.Types.BLOB:
		case java.sql.Types.BINARY:
		case java.sql.Types.ARRAY:
			return "null";
		case java.sql.Types.TINYINT:
		case java.sql.Types.SMALLINT:
		case java.sql.Types.REAL:
		case java.sql.Types.INTEGER:
		case java.sql.Types.FLOAT:
		case java.sql.Types.DOUBLE:
			return "0";
		case java.sql.Types.BIGINT:
			return "0L";
		case java.sql.Types.BOOLEAN:
		case java.sql.Types.BIT:
			return "false";
		case java.sql.Types.VARCHAR:
		case java.sql.Types.NVARCHAR:
		case java.sql.Types.NCHAR:
		case java.sql.Types.LONGNVARCHAR:
		case java.sql.Types.LONGVARCHAR:
			return "\"\"";
		case java.sql.Types.CHAR:
			return "\"\"";
//			return columnName.equals("id")?null: "\"\"";
		default:
			break;
		}
		return "";
	}






	public static byte[] zip(byte[] b){
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			GZIPOutputStream gos = new GZIPOutputStream(baos);
			gos.write(b);
			gos.finish();
			baos.flush();
			
			byte[] ret = baos.toByteArray();
			baos.close();
			gos.close();
			baos = null;
			gos = null;
			return ret;
		} catch (IOException e) {
			e.printStackTrace();
			return new byte[0];
		}
	}

	public static byte[] unzip(byte[] b){
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ByteArrayInputStream bais = new ByteArrayInputStream(b);
			GZIPInputStream gis = new GZIPInputStream(bais);
			do {
				byte[] buff = new byte[1024];
				int len = gis.read(buff);
				if(len <= 0)
					break;
				baos.write(buff, 0, len);
			} while (true);
			baos.flush();
			return baos.toByteArray();
		} catch (IOException e) {
			e.printStackTrace();
			return new byte[0];
		}
	}

	public static boolean notSameDate(Date d1,Date d2){
		Calendar c = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();
		c.setTimeInMillis(Math.max(0, d1.getTime()-4*3600*1000));
		c2.setTimeInMillis(Math.max(0, d2.getTime()-4*3600*1000));
		if(c.get(Calendar.YEAR)!=(c2.get(Calendar.YEAR)))
				return true;
		return c.get(Calendar.DAY_OF_YEAR)!=c2.get(Calendar.DAY_OF_YEAR);
	}
	
	public static void main(String[] args) {
		Date d1 = new Date();
	
		
		Date d2 = new Date();
		
		
		notSameDate(d1, d2);
	}
	
	
	public static void logError(Log log,Exception e){
		StringBuffer sb  = new StringBuffer();
		StackTraceElement[] sts = e.getStackTrace();
		sb.append(e+"\r\n");
		
		for(StackTraceElement st:sts){
			sb.append(st.toString());
		}
		log.error(sb.toString());
	}
	
	public static List<byte[]> splitByteArray(byte[] src,int splitSize){
		int pos = 0;
		int len = src .length;
		List<byte[]> tempList = GameUtil.createList();
		if(len>splitSize){
			for(int i = 0 ; i < len ; i += splitSize ){
				byte[] block = new byte[splitSize];
				if((pos+splitSize)<len){
					System.arraycopy(src, pos, block, 0, splitSize);
					tempList.add(block);
					pos+=splitSize;	
				}
		
			}
		}
		if(pos<src.length){
			int last = len - pos;
			if(last>0){
				byte[] block  = new byte[last];
				System.arraycopy(src, pos, block,0, last);
				tempList.add(block);
			}
		}
		return tempList;
	}
	
	final static char[] codedigits = {
		'1' , '2' , '3' , '4' , '5' ,
		'6' , '7' , '8' , '9' ,
		'C' , 'D' , 'E' , 'F' , 'G' , 'H' ,
		'J' , 'K' , 'L' , 'M' , 'N' ,
		'P' , 'Q' , 'R' , 'S' , 'T' ,
		'U' , 'V' , 'W' , 'X' , 'Y' , 'Z',
		
	};
	
	public static String getCode(int num){
		char[] code = new char[num];
		for(int i = 0;i<num;i++){
			code[i] = codedigits[r.nextInt(codedigits.length)];
		}
		return new String(code);
	}
	
	/** 
     * 大陆号码或香港号码均可 
     */  
    public static boolean isPhoneLegal(String str)throws PatternSyntaxException { 
        return isChinaPhoneLegal(str) || isHKPhoneLegal(str);
    }  
  
    /** 
     * 大陆手机号码11位数，匹配格式：前三位固定格式+后8位任意数 
     * 此方法中前三位格式有： 
     * 13+任意数 
     * 15+除4的任意数 
     * 18+除1和4的任意数 
     * 17+除9的任意数 
     * 147 
     */  
    private static boolean isChinaPhoneLegal(String str) throws PatternSyntaxException {  
        String regExp = "^((13[0-9])|(15[^4])|(18[0,2,3,5-9])|(17[0-8])|(147))\\d{8}$";  
        Pattern p = Pattern.compile(regExp);  
        Matcher m = p.matcher(str);  
        return m.matches();  
    }  
  
    /** 
     * 香港手机号码8位数，5|6|8|9开头+7位任意数 
     */  
    private static boolean isHKPhoneLegal(String str)throws PatternSyntaxException {  
        String regExp = "^(5|6|8|9)\\d{7}$";  
        Pattern p = Pattern.compile(regExp);  
        Matcher m = p.matcher(str);  
        return m.matches();  
    }  
    
    
    
    /** 
     * 18位身份证校验,粗略的校验 
     * @author lyl 
     * @param idCard 
     * @return 
     */  
    public static boolean is18IdCard(String idCard){  
    	//粗略的校验  
       final Pattern pattern = Pattern.compile("^(\\d{6})(19|20)(\\d{2})(1[0-2]|0[1-9])(0[1-9]|[1-2][0-9]|3[0-1])(\\d{3})(\\d|X|x)?$");  
        return pattern.matcher(idCard).matches() || StringUtil.isEmpty(idCard);  
    }  
    
    /**
     * 可输入1-6位数字、英文、汉字 
     * 这里理解为，只要不输入特殊符号，并且在1-6位之间，则可以通过
     * @param name
     * @return
     */
    public static boolean isName(String name) {
		 final Pattern pattern = Pattern.compile("[`~!@#$%^&*()+=|{}':;',\\\\[\\\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]");
			Matcher m = pattern.matcher(name);
		    String newName = m.replaceAll("").trim();
		 return (newName.length() > 6 || newName.length() <1 ) ? false : true;
    }
   
    /**
     * 判断输入名称是否含有特殊字符 找到了返回true 
     * @param name
     * @return
     */
    public static boolean isHaveSpecialChar(String name) {
    	
    	return Pattern.compile("[`~!@#$%^&*()+=|{}':;',\\\\[\\\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]").matcher(name).find();
    }
	
}
