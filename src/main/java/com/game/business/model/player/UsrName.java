package com.game.business.model.player;

import com.game.base.util.DBBuffer;

public class UsrName {

	public static final String preKey = "uname";
	
	private String uname;

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}
	
	public byte[] toBytes(){
		DBBuffer db = DBBuffer.allocate();
		db.putString(uname);
		byte[] ret = db.toBytes();
		db.free();
		return ret;
	}
	
	
	public void fromBytes(byte[] bytes){
		DBBuffer db = DBBuffer.warp(bytes);
		setUname(db.getString());
		db.free();
	}
	
	
}
