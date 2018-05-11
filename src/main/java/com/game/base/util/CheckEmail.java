package com.game.base.util;

public class CheckEmail {
	
	private static char[] NotChar = new char[]{'~','`','!','#','$','%','^','&','*','(',')','[',']','{','}',';',':','"',',','<','>','?','/','\\','\'','=','+','|'};

	public static boolean checkEmail(String email) {// 验证邮箱的正则表达式
		if (email.equals(""))
			return false;
		for(char c : NotChar){
			if(email.indexOf(String.valueOf(c)) > 0)
				return false;
			
		}
		char b = email.charAt(0);
		if (b == ' ' && b == '@' && b == '.')
			return false;
		b = email.charAt(email.length() - 1);
		if (b == ' ' && b == '@' && b == '.')
			return false;
		int i = email.indexOf("@");
		if(i <= 0)
			return false;
		i = email.indexOf(".", i);
		if(i <= 0)
			return false;
		if(email.indexOf("..") >= 0)
			return false;
		if(email.indexOf("@@") >= 0)
			return false;
		if(email.indexOf("@.") >= 0)
			return false;
		if(email.indexOf(".@") >= 0)
			return false;
		return true;
	}

	public static void main(String[] args) {
		String name = "shogoman-es-white@oasis.ocn.ne.jp";
		
		System.out.println(checkEmail(name));
	}

	public static boolean checkPwd(String pwd) {
		char[] cs = pwd.toCharArray();
		for (char c : cs) {
			if(( c >= '0' && c <= '9' ) ||
					( c >= 'a' && c <= 'z' ) || 
					( c >= 'A' && c <= 'Z')){
				continue;
			}else
				return false;
		}
		return true;
	}
	
}