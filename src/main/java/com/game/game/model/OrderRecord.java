package com.game.game.model;

import java.sql.Timestamp;

/**
 * 
 * 订单记录
 * @author Administrator
 *
 */
public class OrderRecord {

	private Integer id;
	/** 玩家登录用户名*/
	private String uname;
	/** 玩家数据库id*/
	private String pdbid;
	/** 玩家名*/
	private String pname;
	/** 推广渠道号*/
	private String channel;
	/** 收到的钱*/
	private Integer recvmoney;
	/** 订单号*/
	private String orderUUID;
	/** 平台端生成的订单id*/
	private String orderID;
	/** 发送给客户端的支付地址*/
	private String payURL;
	/** 充值平台类型*/
	private Integer ptid;
	/** 订单金额*/
	private Integer ordermoney;
	/** 订单状态*/
	private Integer status;
	/** 服务器ID*/
	private String serverid;
	/** 修改时间*/
	private Timestamp modifydate;
	/** RMB->钻石 转换比例*/
	private Integer scaleId;
	/** 生成时间*/
	private Timestamp paydate;
	/** 玩家主角等级*/
	private Integer lv;
	/** 设备类型*/
	private String device_type;
	/** 系统类型*/
	private String device_ios;
	/** 网络类型*/
	private String device_net;
	/** 是否越狱*/
	private String device_yy;
	/** issid*/
	private Integer issid;
	/** 充值总钻石*/
	private Integer totalTre;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public String getPdbid() {
		return pdbid;
	}
	public void setPdbid(String pdbid) {
		this.pdbid = pdbid;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public String getChannel() {
		return channel;
	}
	public void setChannel(String channel) {
		this.channel = channel;
	}
	public Integer getRecvmoney() {
		return recvmoney;
	}
	public void setRecvmoney(Integer recvmoney) {
		this.recvmoney = recvmoney;
	}
	public String getOrderUUID() {
		return orderUUID;
	}
	public void setOrderUUID(String orderUUID) {
		this.orderUUID = orderUUID;
	}
	public String getOrderID() {
		return orderID;
	}
	public void setOrderID(String orderID) {
		this.orderID = orderID;
	}
	public String getPayURL() {
		return payURL;
	}
	public void setPayURL(String payURL) {
		this.payURL = payURL;
	}
	public Integer getPtid() {
		return ptid;
	}
	public void setPtid(Integer ptid) {
		this.ptid = ptid;
	}
	public Integer getOrdermoney() {
		return ordermoney;
	}
	public void setOrdermoney(Integer ordermoney) {
		this.ordermoney = ordermoney;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getServerid() {
		return serverid;
	}
	public void setServerid(String serverid) {
		this.serverid = serverid;
	}
	public Timestamp getModifydate() {
		return modifydate;
	}
	public void setModifydate(Timestamp modifydate) {
		this.modifydate = modifydate;
	}
	public Integer getScaleId() {
		return scaleId;
	}
	public void setScaleId(Integer scaleId) {
		this.scaleId = scaleId;
	}
	public Timestamp getPaydate() {
		return paydate;
	}
	public void setPaydate(Timestamp paydate) {
		this.paydate = paydate;
	}
	public Integer getLv() {
		return lv;
	}
	public void setLv(Integer lv) {
		this.lv = lv;
	}
	public String getDevice_type() {
		return device_type;
	}
	public void setDevice_type(String device_type) {
		this.device_type = device_type;
	}
	public String getDevice_ios() {
		return device_ios;
	}
	public void setDevice_ios(String device_ios) {
		this.device_ios = device_ios;
	}
	public String getDevice_net() {
		return device_net;
	}
	public void setDevice_net(String device_net) {
		this.device_net = device_net;
	}
	public String getDevice_yy() {
		return device_yy;
	}
	public void setDevice_yy(String device_yy) {
		this.device_yy = device_yy;
	}
	public Integer getIssid() {
		return issid;
	}
	public void setIssid(Integer issid) {
		this.issid = issid;
	}
	public Integer getTotalTre() {
		return totalTre;
	}
	public void setTotalTre(Integer totalTre) {
		this.totalTre = totalTre;
	}
	@Override
	public String toString() {
		return "OrderRecord [id=" + id + ", uname=" + uname + ", pdbid=" + pdbid + ", pname=" + pname + ", channel="
				+ channel + ", recvmoney=" + recvmoney + ", orderUUID=" + orderUUID + ", orderID=" + orderID
				+ ", payURL=" + payURL + ", ptid=" + ptid + ", ordermoney=" + ordermoney + ", status=" + status
				+ ", serverid=" + serverid + ", modifydate=" + modifydate + ", scaleId=" + scaleId + ", paydate="
				+ paydate + ", lv=" + lv + ", device_type=" + device_type + ", device_ios=" + device_ios
				+ ", device_net=" + device_net + ", device_yy=" + device_yy + ", issid=" + issid + ", totalTre="
				+ totalTre + "]";
	}
	
	
}
