package com.game.business.model;

import java.sql.Timestamp;

/**
 * 玩家交易记录实体类
 * @author Administrator
 *
 */
public class PlayerTradeRecode {
	/** id*/
	private Integer id;
	/** 交易时间*/
	private Timestamp tradeTime;
	/** 汇款人账号*/
	private String remitAccount;
	/** 汇款人类别,1表示管理员,2表示代理商*/
	private Integer remitCategory;
	/** 玩家id*/
	private Integer gameId;
	/** 交易类型,1表示管理员充值,2表示代理商充值*/
	private Integer tradeCategory;
	/** 汇款人房卡数*/
	private Integer remitRoomCard;
	/** 玩家房卡数*/
	private Integer playerRoomCard;
	/** 操作者id*/
	private Integer operateId;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public Timestamp getTradeTime() {
		return tradeTime;
	}
	public void setTradeTime(Timestamp tradeTime) {
		this.tradeTime = tradeTime;
	}
	
	public String getRemitAccount() {
		return remitAccount;
	}
	public void setRemitAccount(String remitAccount) {
		this.remitAccount = remitAccount;
	}
	
	public Integer getRemitCategory() {
		return remitCategory;
	}
	public void setRemitCategory(Integer remitCategory) {
		this.remitCategory = remitCategory;
	}
	
	public Integer getGameId() {
		return gameId;
	}
	public void setGameId(Integer gameId) {
		this.gameId = gameId;
	}
	
	public Integer getTradeCategory() {
		return tradeCategory;
	}
	public void setTradeCategory(Integer tradeCategory) {
		this.tradeCategory = tradeCategory;
	}
	
	public Integer getRemitRoomCard() {
		return remitRoomCard;
	}
	public void setRemitRoomCard(Integer remitRoomCard) {
		this.remitRoomCard = remitRoomCard;
	}
	
	public Integer getPlayerRoomCard() {
		return playerRoomCard;
	}
	public void setPlayerRoomCard(Integer playerRoomCard) {
		this.playerRoomCard = playerRoomCard;
	}
	
	public Integer getOperateId() {
		return operateId;
	}
	public void setOperateId(Integer operateId) {
		this.operateId = operateId;
	}
	
	@Override
	public String toString() {
		return "PalyerTradeRecode [id=" + id + ", tradeTime=" + tradeTime + ", remitAccount=" + remitAccount
				+ ", remitCategory=" + remitCategory + ", gameId=" + gameId + ", tradeCategory=" + tradeCategory
				+ ", remitRoomCard=" + remitRoomCard + ", playerRoomCard=" + playerRoomCard + ", operateId=" + operateId
				+ "]";
	}
	
	
}
