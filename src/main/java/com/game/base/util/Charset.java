package com.game.base.util;

public class Charset {

	
	/** 半角标点符号开始位置 */

	private static final int SINGLE_BYTE_SYMBOL_START = 0x0020;

	/** 半角标点符号结束位置 */

	private static final int SINGLE_BYTE_SYMBOL_END = 0x007E;

	/** 半角片假名开始位置 */

	private static final int SINGLE_BYTE_KATAKANA_START = 0xFF61;

	/** 半角片假名结束位置 */

	private static final int SINGLE_BYTE_KATAKANA_END = 0xFF9F;

	/** 半角空格 */

	private static final int SINGLE_BYTE_SPACE_END = 0x0020;

	/**

	 * 半角数字判定

	 * @return 判定結果 true:半角数字

	 */

	public static boolean isSingleByteDigit( final char c ) {

	    return ( '0' <= c ) && ( c <= '9' );

	}

	/**

	 * 半角英字判定

	 * @return 判定結果 true:半角英字

	 */

	public static boolean isSingleByteAlpha( final char c ) {

	    return ( ( 'a' <= c ) && ( c <= 'z' ) ) || ( ( 'A' <= c ) && ( c <= 'Z' ) );

	}

	/**

	 * 半角标点符号判定

	 * @return 判定結果 true:半角标点符号

	 */

	public static boolean isSingleByteSymbol( final char c ) {

	    return ( SINGLE_BYTE_SYMBOL_START <= c ) &&

	                ( c <= SINGLE_BYTE_SYMBOL_END ) && 

	                !isSingleByteAlpha( c ) && 

	                !isSingleByteDigit( c );

	}

	/**

	 * 半角片假名判定

	 * @return 判定結果 true:半角片假名

	 */

	public static boolean isSingleByteKatakana( final char c ) {

	    return ( SINGLE_BYTE_KATAKANA_START <= c ) && ( c <= SINGLE_BYTE_KATAKANA_END );

	}

	/**

	 * 半角空格判定

	 * @return 判定結果 true:半角空格

	 */

	public static boolean isSingleByteSpace( final char c ) {   

	    boolean bRet = false;    

	    if ( c == SINGLE_BYTE_SPACE_END ) {        

	        bRet = true;

	    }    

	    return bRet;

	}

	/** 全角标点符号开始位置 */

	private static final int DOUBLE_BYTE_SYMBOL_START = 0xFF01;

	/** 全角标点符号结束位置 */

	private static final int DOUBLE_BYTE_SYMBOL_END = 0xFF5E;

	/** 全角片假名开始位置 */

	private static final int DOUBLE_BYTE_KATAKANA_START = 0x30A0;

	/** 全角片假名结束位置 */

	private static final int DOUBLE_BYTE_KATAKANA_END = 0x30FF;

	/** 全角空格 */

	private static final int DOUBLE_BYTE_SPACE_END = 0x3000;

	/**

	 * 判定半角

	 * @return 判定結果 true:验证合法

	 */

	public static boolean isSingle( final String str ) {

	    for ( int i = str.length() - 1; 0 <= i; i-- ) {
	    	
	    	char c = str.charAt( i );
	    	
	        if ( !Charset.isSingleByteDigit( c ) && 

	              !Charset.isSingleByteAlpha( c ) &&
	              
	        		!Charset.isSingleByteSymbol( c ) &&
	        		
	        			!Charset.isSingleByteKatakana( c ) && 
	        			
	        				!Charset.isSingleByteSpace( c )) {
	           return false;

	        }
	        if(c == ' ')
	        	return false;
	    }
	    return true;

	}
	
	public static void main(String[] args) {
		String str = "Abcｂｂ";
		System.out.println(isSingle(str));
	}
}
