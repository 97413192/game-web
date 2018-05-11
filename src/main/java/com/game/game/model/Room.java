package com.game.game.model;

import java.io.Serializable;
import com.game.base.util.DBBuffer;
import java.io.InvalidClassException;
	/**
	*此类由MySQLToBean工具自动生成
	*
	*@author 
	*@since 2016-11-10 12:16:25
	*/

public class Room implements Serializable {
	//private static final long serialVersionUID = -5961090718136358753L;
	private static final long serialVersionUID = 1L;
//	public static final String preKey = "room";
//	public static final String playerRelationshipKey = "player_room:";
	private int id;//房间ID
	private int gameId;//游戏ID
	private int vipLimit;//
	private int goldLimit;//
	//private int level;//
	private String name;//房间名
	private int peopleNumLimit;//人数限制
	private int creditRate;//表示多少金币兑换1积分
	private int betBaseScore;//基础押注分
	private int betMinScore;//最小押注分
	private int betMaxScore;//最大押注分
	//private int a //抽水率 百分比 整数型，
	//private int b //是否激活，两个状态，0表示激活，1表示不激活
	//private int c  //倒计时，以秒为单位
	//private int d  //留桌配置id号，连接到留桌配置中的id编号
	public byte[] toBytes(){
		DBBuffer db = DBBuffer.allocate();
		db.putInt(this.id);//房间ID
		db.putInt(this.gameId);//游戏ID
		db.putInt(this.vipLimit);//
		db.putInt(this.goldLimit);//
		db.putString(this.name);//
		db.putInt(this.peopleNumLimit);//人数限制
		db.putInt(this.creditRate);//表示多少金币兑换1积分
		db.putInt(this.betBaseScore);//基础押注分
		db.putInt(this.betMinScore);//最小押注分
		db.putInt(this.betMaxScore);//最大押注分
		byte[] ret = db.toBytes();
		db.free();
		return ret;
	}
	@Override
	public String toString() {
		return "Room [id=" + id + ", gameId=" + gameId + ", vipLimit=" + vipLimit + ", goldLimit=" + goldLimit
				+ ", name=" + name + ", peopleNumLimit=" + peopleNumLimit + ", creditRate="
				+ creditRate + ", betBaseScore=" + betBaseScore + ", betMinScore=" + betMinScore + ", betMaxScore="
				+ betMaxScore + "]";
	}
	public void fromBytes(byte[] bytes) {
		DBBuffer db = DBBuffer.warp(bytes);
		this.id = db.getInt();//房间ID
		this.gameId = db.getInt();//游戏ID
		this.vipLimit = db.getInt();//
		this.goldLimit = db.getInt();//
		this.name = db.getString();//
		this.peopleNumLimit = db.getInt();//人数限制
		this.creditRate = db.getInt();//表示多少金币兑换1积分
		this.betBaseScore = db.getInt();//基础押注分
		this.betMinScore = db.getInt();//最小押注分
		this.betMaxScore = db.getInt();//最大押注分
		db.free();
	}
	public int getId(){
		return this.id;
	}
	public void setId(int id){
		this.id=id;
	}
	public int getGameId(){
		return this.gameId;
	}
	public void setGameId(int gameId){
		this.gameId=gameId;
	}
	public int getVipLimit(){
		return this.vipLimit;
	}
	public void setVipLimit(int vipLimit){
		this.vipLimit=vipLimit;
	}
	public int getGoldLimit(){
		return this.goldLimit;
	}
	public void setGoldLimit(int goldLimit){
		this.goldLimit=goldLimit;
	}
	public String getName(){
		return this.name;
	}
	public void setName(String name){
		this.name=name;
	}
	public int getPeopleNumLimit(){
		return this.peopleNumLimit;
	}
	public void setPeopleNumLimit(int peopleNumLimit){
		this.peopleNumLimit=peopleNumLimit;
	}
	public int getCreditRate(){
		return this.creditRate;
	}
	public void setCreditRate(int creditRate){
		this.creditRate=creditRate;
	}
	public int getBetBaseScore(){
		return this.betBaseScore;
	}
	public void setBetBaseScore(int betBaseScore){
		this.betBaseScore=betBaseScore;
	}
	public int getBetMinScore(){
		return this.betMinScore;
	}
	public void setBetMinScore(int betMinScore){
		this.betMinScore=betMinScore;
	}
	public int getBetMaxScore(){
		return this.betMaxScore;
	}
	public void setBetMaxScore(int betMaxScore){
		this.betMaxScore=betMaxScore;
	}

}