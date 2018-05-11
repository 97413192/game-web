package com.game.business.model;

import java.util.Date;

/**
 * 游戏玩家与代理商关系对应实体类
 * @author Administrator
 *
 */
public class PromoCode {

	private Integer id;
	
	/** 代理商账号*/
	private String account;
	
	/** 代理商房卡数*/
	private Integer roomCard;
	
	/** 玩家gameId*/
	private Integer gameId;
	
	/** 代理商id*/
	private Integer userID;
	
	private Date bindingTime;
	
	private Date modifyTime;

	private String pNickName;		//玩家昵称
	
	private Long playerRoomCard;	//玩家房卡
	
	private Integer nowMonthPay;	//本月商城充值的金额
	
	private Integer yb; 			//被绑定的状态 0 被绑定，1未被绑定（之前可能绑定过）
	
	

	public Integer getYb() {
		return yb;
	}

	public void setYb(Integer yb) {
		this.yb = yb;
	}

	public Integer getNowMonthPay() {
		return nowMonthPay;
	}

	public void setNowMonthPay(Integer nowMonthPay) {
		this.nowMonthPay = nowMonthPay;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public Integer getRoomCard() {
		return roomCard;
	}

	public void setRoomCard(Integer roomCard) {
		this.roomCard = roomCard;
	}

	public Integer getGameId() {
		return gameId;
	}

	public void setGameId(Integer gameId) {
		this.gameId = gameId;
	}

	public Integer getUserID() {
		return userID;
	}

	public void setUserID(Integer userID) {
		this.userID = userID;
	}

	public Date getBindingTime() {
		return bindingTime;
	}

	public void setBindingTime(Date bindingTime) {
		this.bindingTime = bindingTime;
	}

	public Date getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}



	public String getpNickName() {
		return pNickName;
	}

	public void setpNickName(String pNickName) {
		this.pNickName = pNickName;
	}

	public Long getPlayerRoomCard() {
		return playerRoomCard;
	}

	public void setPlayerRoomCard(Long playerRoomCard) {
		this.playerRoomCard = playerRoomCard;
	}

	@Override
	public String toString() {
		return "PromoCode [id=" + id + ", account=" + account + ", roomCard=" + roomCard + ", gameId=" + gameId
				+ ", userID=" + userID + ", bindingTime=" + bindingTime + ", modifyTime=" + modifyTime + ", pNickName="
				+ pNickName + ", playerRoomCard=" + playerRoomCard + ", nowMonthPay=" + nowMonthPay + "]";
	}

	

	

	
}
