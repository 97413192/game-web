package com.game.business.model;

import java.io.Serializable;

public class Agent implements Serializable{

	/**
	 * 代理商
	 */
	private static final long serialVersionUID = 1L;
	//代理商登录时间
	public static long logintime;
	
	private Integer userID;				//用户ID
	private String account;				//账户
	private Integer roomCard;			//房卡
	private String higherAgent;			//上级代理
	private Integer lowerAgentNum;		//下级代理数
	private Integer lowerPlayerNum;		//下级玩家数
	private String documentType;		//证件类型（身份证、护照、驾驶证）
	private String documentNumber;		//证件编号
	private String bankName;			//银行名称
	private String bankCardNumber;		//银行卡号
	private String realName;			//真实姓名
	private String cellPhoneNumber;		//手机号码
	private String email;				//邮箱
	private String regTime;				//注册时间
	private Integer loginNumber;		//登录次数
	private String lastLoginIP;			//上次登录IP
	private String lastLoginTime;		//上次登录登录时间
	/**代理商的状态,1表示正常,2冻结,3表示离线*/
	private String state;
	private Integer category;			//用来区分一级代理商还是二级代理商
	private String password;			//代理密码
	private Double 	rebate;			//返点
	
	public Integer getUserID() {
		return userID;
	}
	public void setUserID(Integer userID) {
		this.userID = userID;
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
	public String getHigherAgent() {
		return higherAgent;
	}
	public void setHigherAgent(String higherAgent) {
		this.higherAgent = higherAgent;
	}
	public Integer getLowerAgentNum() {
		return lowerAgentNum;
	}
	public void setLowerAgentNum(Integer lowerAgentNum) {
		this.lowerAgentNum = lowerAgentNum;
	}
	public Integer getLowerPlayerNum() {
		return lowerPlayerNum;
	}
	public void setLowerPlayerNum(Integer lowerPlayerNum) {
		this.lowerPlayerNum = lowerPlayerNum;
	}
	public String getDocumentType() {
		return documentType;
	}
	public void setDocumentType(String documentType) {
		this.documentType = documentType;
	}
	public String getDocumentNumber() {
		return documentNumber;
	}
	public void setDocumentNumber(String documentNumber) {
		this.documentNumber = documentNumber;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public String getBankCardNumber() {
		return bankCardNumber;
	}
	public void setBankCardNumber(String bankCardNumber) {
		this.bankCardNumber = bankCardNumber;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public String getCellPhoneNumber() {
		return cellPhoneNumber;
	}
	public void setCellPhoneNumber(String cellPhoneNumber) {
		this.cellPhoneNumber = cellPhoneNumber;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getRegTime() {
		return regTime;
	}
	public void setRegTime(String regTime) {
		this.regTime = regTime;
	}
	public Integer getLoginNumber() {
		return loginNumber;
	}
	public void setLoginNumber(Integer loginNumber) {
		this.loginNumber = loginNumber;
	}
	public String getLastLoginIP() {
		return lastLoginIP;
	}
	public void setLastLoginIP(String lastLoginIP) {
		this.lastLoginIP = lastLoginIP;
	}
	public String getLastLoginTime() {
		return lastLoginTime;
	}
	public void setLastLoginTime(String lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public Integer getCategory() {
		return category;
	}
	public void setCategory(Integer category) {
		this.category = category;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public Double getRebate() {
		return rebate;
	}
	public void setRebate(Double rebate) {
		this.rebate = rebate;
	}
	@Override
	public String toString() {
		return "Agent [userID=" + userID + ", account=" + account + ", roomCard=" + roomCard + ", higherAgent="
				+ higherAgent + ", lowerAgentNum=" + lowerAgentNum + ", lowerPlayerNum=" + lowerPlayerNum
				+ ", documentType=" + documentType + ", documentNumber=" + documentNumber + ", bankName=" + bankName
				+ ", bankCardNumber=" + bankCardNumber + ", realName=" + realName + ", cellPhoneNumber="
				+ cellPhoneNumber + ", email=" + email + ", regTime=" + regTime + ", loginNumber=" + loginNumber
				+ ", lastLoginIP=" + lastLoginIP + ", lastLoginTime=" + lastLoginTime + ", state=" + state + ", category="
				+ category + ", password=" + password + "]";
	}
	
}
