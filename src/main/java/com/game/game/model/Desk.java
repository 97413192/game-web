package com.game.game.model;

import java.io.Serializable;
import com.game.base.util.DBBuffer;
import java.io.InvalidClassException;
	/**
	*此类由MySQLToBean工具自动生成
	*
	*@author 
	*@since 2016-11-10 12:14:30
	*/

public class Desk implements Serializable {
	private static final long serialVersionUID = 6086401240758514815L;
	private int id;//
	private int gameid;//对应的游戏id
	private int roomid;//对应的房间id
	private int maxGoldStock;//最大金币库存
	private int curGoldStock;//当前金币库存
	private int extraBonusScore;//额外的皮子分
	//private int a  //概率组
	
	public byte[] toBytes(){
		DBBuffer db = DBBuffer.allocate();
		db.putInt(this.id);//
		db.putInt(this.gameid);//对应的游戏id
		db.putInt(this.roomid);//对应的房间id
		db.putInt(this.maxGoldStock);//最大金币库存
		db.putInt(this.curGoldStock);//当前金币库存
		db.putInt(this.extraBonusScore);//额外的皮子分
		byte[] ret = db.toBytes();
		db.free();
		return ret;
	}
	@Override
	public String toString() {
		return "Desk [id=" + id + ", gameid=" + gameid + ", roomid=" + roomid + ", maxGoldStock=" + maxGoldStock
				+ ", curGoldStock=" + curGoldStock + ", extraBonusScore=" + extraBonusScore + "]";
	}
	public void fromBytes(byte[] bytes) throws InvalidClassException {
		DBBuffer db = DBBuffer.warp(bytes);
		this.id = db.getInt();//
		this.gameid = db.getInt();//对应的游戏id
		this.roomid = db.getInt();//对应的房间id
		this.maxGoldStock = db.getInt();//最大金币库存
		this.curGoldStock = db.getInt();//当前金币库存
		this.extraBonusScore = db.getInt();//额外的皮子分
		db.free();
	}
	public int getId(){
		return this.id;
	}
	public void setId(int id){
		this.id=id;
	}
	public int getGameid(){
		return this.gameid;
	}
	public void setGameid(int gameid){
		this.gameid=gameid;
	}
	public int getRoomid(){
		return this.roomid;
	}
	public void setRoomid(int roomid){
		this.roomid=roomid;
	}
	public int getMaxGoldStock(){
		return this.maxGoldStock;
	}
	public void setMaxGoldStock(int maxGoldStock){
		this.maxGoldStock=maxGoldStock;
	}
	public int getCurGoldStock(){
		return this.curGoldStock;
	}
	public void setCurGoldStock(int curGoldStock){
		this.curGoldStock=curGoldStock;
	}
	public int getExtraBonusScore(){
		return this.extraBonusScore;
	}
	public void setExtraBonusScore(int extraBonusScore){
		this.extraBonusScore=extraBonusScore;
	}

}