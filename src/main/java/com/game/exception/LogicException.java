package com.game.exception;


public class LogicException extends Exception{
	private static final long serialVersionUID = 7243881150126768082L;
	private int code;
	private String info;
	public LogicException(int code){
		this.code = code;
	}
	
	public LogicException(int code,String info){
		this.code = code;
		this.info = info;
	}
	
	
	@Override
	public String getMessage() {
		return super.getMessage()+" "+code+(info!=null?info:"");
	}
	
	public int getCode(){
		return code;
	}
}