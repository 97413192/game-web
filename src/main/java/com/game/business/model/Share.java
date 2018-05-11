package com.game.business.model;

import java.util.Date;

/**
 *  奖励分享
 * @author 杨浩零
 * 
 */
public class Share {
	
	private Integer gameId;			//游戏id
	private Integer shareType;		//分享类型(1是微信,2是朋友圈)
	private Integer number;			//领取物品数量
	private Integer numberType;		//奖励类型(1是金币,2是房卡,3是元宝)
	private Integer rewardNumber;		//每天可以领取多少次
	private Long    receiveCD; 		//领取间隔时间
	public Long getReceiveCD() {
		return receiveCD;
	}
	public void setReceiveCD(Long receiveCD) {
		this.receiveCD = receiveCD;
	}
	private Integer shareNumber;    //分享次数
	private Date    shareTime;		//分享时间
	public Integer getGameId() {
		return gameId;
	}
	public Integer getShareType() {
		return shareType;
	}
	public Integer getNumber() {
		return number;
	}
	public Integer getNumberType() {
		return numberType;
	}
	public Integer getRewardNumber() {
		return rewardNumber;
	}
	public Integer getShareNumber() {
		return shareNumber;
	}
	public Date getShareTime() {
		return shareTime;
	}
	public void setGameId(Integer gameId) {
		this.gameId = gameId;
	}
	public void setShareType(Integer shareType) {
		this.shareType = shareType;
	}
	public void setNumber(Integer number) {
		this.number = number;
	}
	public void setNumberType(Integer numberType) {
		this.numberType = numberType;
	}
	public void setRewardNumber(Integer rewardNumber) {
		this.rewardNumber = rewardNumber;
	}
	public void setShareNumber(Integer shareNumber) {
		this.shareNumber = shareNumber;
	}
	public void setShareTime(Date shareTime) {
		this.shareTime = shareTime;
	}
	
	
	
}