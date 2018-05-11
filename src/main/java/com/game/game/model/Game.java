package com.game.game.model;

import java.io.Serializable;

import com.game.base.util.DBBuffer;

/**
	*此类由MySQLToBean工具自动生成

	*@author 
	*@since 2016-11-14 12:17:12
	*/

public class Game implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;//游戏ID
	private String name;//游戏名
	public int getId(){
		return this.id;
	}
	public void setId(int id){
		this.id=id;
	}
	public String getName(){
		return this.name;
	}
	public void setName(String name){
		this.name=name;
	}
	@Override
	public String toString() {
		return "Game [id=" + id + ", name=" + name + "]";
	}
	public byte[] toBytes() {
		DBBuffer db = DBBuffer.allocate();
		db.putInt(id);
		db.putString(name);
		byte[] ret = db.toBytes();
		db.free();
		return ret;
	}
	public void fromBytes(byte[] bytes) {
		DBBuffer db = DBBuffer.warp(bytes);
		setId(db.getInt());
		setName(db.getString());
		db.free();
	}

	
}