package com.game.business.model.player;

import java.io.Serializable;
import java.util.UUID;

import com.game.base.util.DBBuffer;




public class Usr implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5454557086772811183L;

	public static final String preKey = "usr";
	
	public static final String unameRelationKey = "uname_uid";
	  
	public static final String baiduRelationKey = "baidu_uid";
	
	private String uuid;
	private String uname;
	private String psd;
	
	private int sex; // 0女，1男
	private String imei;  //手机
	private String imsi;  //
	private String platform;  //
	private String system;  //系统类型
	private String systype;  //系统版本
	private String nettype;  //网络型号
	private String creDate;  //创建时期
	private String serverID;  //服务器id
	private String modifyDate;  //最近一次修改时期
	private int isBind;//0未绑定，1已绑定
	private int isResetPassWord;//0未重置，1已重置
	private String channel;//用户渠道
	private String channelNo;//用户渠道
	private String baiduId;  //百度id

	
	public String getChannelNo() {
		return channelNo;
	}
	public void setChannelNo(String channelNo) {
		this.channelNo = channelNo;
	}
	public String getChannel() {
		return channel;
	}
	public void setChannel(String channel) {
		this.channel = channel;
	}
	public String getBaiduId() {
		return baiduId;
	}
	public void setBaiduId(String baiduId) {
		this.baiduId = baiduId;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String id) {
		this.uuid = id;
	}
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public String getPsd() {
		return psd;
	}
	public void setPsd(String psd) {
		this.psd = psd;
	}
	public int getSex() {
		return sex;
	}
	public void setSex(int sex) {
		this.sex = sex;
	}
	public String getImei() {
		return imei;
	}
	public void setImei(String imei) {
		this.imei = imei;
	}
	public String getImsi() {
		return imsi;
	}
	public void setImsi(String imsi) {
		this.imsi = imsi;
	}
	public String getPlatform() {
		return platform;
	}
	public void setPlatform(String platform) {
		this.platform = platform;
	}
	public String getSystem() {
		return system;
	}
	public void setSystem(String system) {
		this.system = system;
	}
	public String getSystype() {
		return systype;
	}
	public void setSystype(String systype) {
		this.systype = systype;
	}
	public String getNettype() {
		return nettype;
	}
	public void setNettype(String nettype) {
		this.nettype = nettype;
	}
	public String getCreDate() {
		return creDate;
	}
	public void setCreDate(String creDate) {
		this.creDate = creDate;
	}
	public String getServerID() {
		return serverID;
	}
	public void setServerID(String serverID) {
		this.serverID = serverID;
	}
	public String getModifyDate() {
		return modifyDate;
	}
	public void setModifyDate(String modifyDate) {
		this.modifyDate = modifyDate;
	}
	public int getIsBind() {
		return isBind;
	}
	public void setIsBind(int isBind) {
		this.isBind = isBind;
	}
	public int getIsResetPassWord() {
		return isResetPassWord;
	}
	public void setIsResetPassWord(int isResetPassWord) {
		this.isResetPassWord = isResetPassWord;
	}
	public static String getPrekey() {
		return preKey;
	}
	public static String getUnamerelationkey() {
		return unameRelationKey;
	}

	public byte[] toBytes(){
		DBBuffer db = DBBuffer.allocate();
		db.putString(uuid);
		db.putString(uname);
		db.putString(psd);
		db.putInt(sex);
		db.putString(imei);
		db.putString(imsi);
		db.putString(platform);
		db.putString(system);
		db.putString(systype);
		db.putString(nettype);
		db.putString(creDate);
		db.putString(serverID);
		db.putString(modifyDate);
		db.putInt(isBind);
		db.putInt(isResetPassWord);
		byte[] ret = db.toBytes();
		db.free();
		return ret;
	}
	
	
	public void fromBytes(byte[] bytes){
		DBBuffer db = DBBuffer.warp(bytes);
		setUuid(db.getString());
		setUname(db.getString());
		setPsd(db.getString());
		setSex(db.getInt());
		setImei(db.getString());
		setImsi(db.getString());
		setPlatform(db.getString());
		setSystem(db.getString());
		setSystype(db.getString());
		setNettype(db.getString());
		setCreDate(db.getString());
		setServerID(db.getString());
		setModifyDate(db.getString());
		setIsBind(db.getInt());
		setIsResetPassWord(db.getInt());
		db.free();
	}
	
	public static Usr createUsr(String uname,String password, String channel) {
		Usr usr = new Usr();
		usr.setUuid(UUID.randomUUID().toString().replace("-", "").toUpperCase());
		usr.setUname(uname);
		usr.setPsd(password);
		usr.setImei("");
		usr.setImsi("");
		usr.setIsBind(0);
		usr.setIsResetPassWord(0);
		usr.setNettype("");
		usr.setPlatform("");
		usr.setServerID("");
		usr.setSex(0);
		usr.setSystem("");
		usr.setSystype("");
		usr.setChannel(channel);
		usr.setChannelNo("");
		return usr;
	}
	
	public static Usr createUsr(String channel){
		String uname = UUID.randomUUID().toString().replace("-", "").toUpperCase();
		String password = UUID.randomUUID().toString().replace("-", "").toUpperCase();	
		return createUsr(uname, password, channel);		
	}
	
	@Override
	public String toString() {
		return "Usr [uuid=" + uuid + ", uname=" + uname + ", psd=" + psd + ", sex=" + sex + ", imei=" + imei + ", imsi=" + imsi + ", platform=" + platform + ", system=" + system + ", systype="
				+ systype + ", nettype=" + nettype + ", creDate=" + creDate + ", serverID=" + serverID + ", modifyDate=" + modifyDate + ", isBind=" + isBind + ", isResetPassWord=" + isResetPassWord
				+ ", channel=" + channel + ", channelNo=" + channelNo + ", baiduId=" + baiduId + "]";
	}
}
