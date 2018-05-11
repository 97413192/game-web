package com.game.business.model.player;

	/**
	*此类由MySQLToBean工具自动生成

	*@author 
	*@since 2016-09-14 17:58:58
	*/

public class RegLog{
	private int id;//ID
	private String uname;//账号名
	private String ip;//注册IP
	private String regdate;//注册时间
	private String uuid;//uuid
	public int getId(){
		return this.id;
	}
	public void setId(int id){
		this.id=id;
	}
	public String getUname(){
		return this.uname;
	}
	public void setUname(String uname){
		this.uname=uname;
	}
	public String getIp(){
		return this.ip;
	}
	public void setIp(String ip){
		this.ip=ip;
	}
	public String getRegdate(){
		return this.regdate;
	}
	public void setRegdate(String logindate){
		this.regdate=logindate;
	}
	
	public String getUuid(){
		return this.uuid;
	}
	public void setUuid(String uuid){
		this.uuid=uuid;
	}

}