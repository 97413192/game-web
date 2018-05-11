package com.game.business.model.player;

import java.io.Serializable;

public class District implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4507298754736819257L;
	private int id;   //id 可以不用展示。不能修改
	private String serverID;//服务器ID  不能修改
	private String serverName; //服务器名称
	private int state;		//状态看我给你发的文本文档
	private int isRmd;		//展示RMD即可
	private String serverIP;//ip
	private int luaPort;	//端口
	private String resURL;	//资源服务器地址
	private String payServerURL;//充值服务器地址
	private String gameServerRMI;//游戏服务器RMI地址
	private int olNum;			//当前在线人数--只展示 不能修改  添加时默认为0
	private int regNum;			//当前注册人数--只展示 不能修改  添加时默认为0
	private int payPro;			//充值比例 默认为0
	
	
	public int getPayPro() {
		return payPro;
	}
	public void setPayPro(int payPro) {
		this.payPro = payPro;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getServerID() {
		return serverID;
	}
	public void setServerID(String serverID) {
		this.serverID = serverID;
	}
	public String getServerName() {
		return serverName;
	}
	public void setServerName(String serverName) {
		this.serverName = serverName;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public int getIsRmd() {
		return isRmd;
	}
	public void setIsRmd(int isRmd) {
		this.isRmd = isRmd;
	}
	public String getServerIP() {
		return serverIP;
	}
	public void setServerIP(String serverIP) {
		this.serverIP = serverIP;
	}
	public int getLuaPort() {
		return luaPort;
	}
	public void setLuaPort(int luaPort) {
		this.luaPort = luaPort;
	}
	public String getResURL() {
		return resURL;
	}
	public String getPayServerURL() {
		return payServerURL;
	}
	public void setPayServerURL(String payServerURL) {
		this.payServerURL = payServerURL;
	}
	public void setResURL(String resURL) {
		this.resURL = resURL;
	}
	public String getGameServerRMI() {
		return gameServerRMI;
	}
	public void setGameServerRMI(String gameServerRMI) {
		this.gameServerRMI = gameServerRMI;
	}
	public int getOlNum() {
		return olNum;
	}
	public void setOlNum(int olNum) {
		this.olNum = olNum;
	}
	public int getRegNum() {
		return regNum;
	}
	public void setRegNum(int regNum) {
		this.regNum = regNum;
	}
	@Override
	public String toString() {
		return "District [id=" + id + ", serverID=" + serverID + ", state="
				+ state + ", isRmd=" + isRmd + ", serverIP=" + serverIP
				+ ", luaPort=" + luaPort + ", resURL=" + resURL
				+ ", payServerURL=" + payServerURL + ", gameServerRMI="
				+ gameServerRMI + ", olNum=" + olNum + ", regNum=" + regNum
				+ "]";
	}
	
}
