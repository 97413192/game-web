package com.game.business.model.player;

	/**
	*此类由MySQLToBean工具自动生成

	*@author 
	*@since 2016-07-24 15:41:09
	*/

public class AliPayOrder{
	private int id;//ID
	private String uname;//玩家登录用户名
	private String pdbid;//玩家数据库id
	private String pname;//玩家名
	private String channel;//推广渠道号
	private int recvmoney;//收到的钱
	private String orderUUID;//订单号
	private long orderID;//平台端生成的订单id
	private String payURL;//发送给客户端的支付地址
	private int ptid;//充值平台类型
	private int ordermoney;//订单金额
	private int status;//订单状态
	private String serverid;//服务器ID
	private String modifydate;//修改时间
	private int scaleId;//RMB->钻石 转换比例 id
	private String paydate;//生成时间
	private int lv;//玩家主角等级
	private String device_type;//设备类型
	private String device_ios;//系统类型
	private String device_net;//网络类型
	private String device_yy;//是否越狱
	private int issid;//issid
	private int totalTre;//充值总钻石
	public int getId(){
		return this.id;
	}
	public void setId(int id){
		this.id=id;
	}
	public String getUname(){
		return this.uname;
	}
	public void setUname(String uname){
		this.uname=uname;
	}
	public String getPdbid(){
		return this.pdbid;
	}
	public void setPdbid(String pdbid){
		this.pdbid=pdbid;
	}
	public String getPname(){
		return this.pname;
	}
	public void setPname(String pname){
		this.pname=pname;
	}
	public String getChannel(){
		return this.channel;
	}
	public void setChannel(String channel){
		this.channel=channel;
	}
	public int getRecvmoney(){
		return this.recvmoney;
	}
	public void setRecvmoney(int recvmoney){
		this.recvmoney=recvmoney;
	}
	public String getOrderUUID(){
		return this.orderUUID;
	}
	public void setOrderUUID(String orderUUID){
		this.orderUUID=orderUUID;
	}
	public long getOrderID(){
		return this.orderID;
	}
	public void setOrderID(long orderID){
		this.orderID=orderID;
	}
	public String getPayURL(){
		return this.payURL;
	}
	public void setPayURL(String payURL){
		this.payURL=payURL;
	}
	public int getPtid(){
		return this.ptid;
	}
	public void setPtid(int ptid){
		this.ptid=ptid;
	}
	public int getOrdermoney(){
		return this.ordermoney;
	}
	public void setOrdermoney(int ordermoney){
		this.ordermoney=ordermoney;
	}
	public int getStatus(){
		return this.status;
	}
	public void setStatus(int status){
		this.status=status;
	}
	public String getServerid(){
		return this.serverid;
	}
	public void setServerid(String serverid){
		this.serverid=serverid;
	}
	public String getModifydate(){
		return this.modifydate;
	}
	public void setModifydate(String modifydate){
		this.modifydate=modifydate;
	}
	public int getScaleId(){
		return this.scaleId;
	}
	public void setScaleId(int scaleId){
		this.scaleId=scaleId;
	}
	public String getPaydate(){
		return this.paydate;
	}
	public void setPaydate(String paydate){
		this.paydate=paydate;
	}
	public int getLv(){
		return this.lv;
	}
	public void setLv(int lv){
		this.lv=lv;
	}
	public String getDevice_type(){
		return this.device_type;
	}
	public void setDevice_type(String device_type){
		this.device_type=device_type;
	}
	public String getDevice_ios(){
		return this.device_ios;
	}
	public void setDevice_ios(String device_ios){
		this.device_ios=device_ios;
	}
	public String getDevice_net(){
		return this.device_net;
	}
	public void setDevice_net(String device_net){
		this.device_net=device_net;
	}
	public String getDevice_yy(){
		return this.device_yy;
	}
	public void setDevice_yy(String device_yy){
		this.device_yy=device_yy;
	}
	public int getIssid(){
		return this.issid;
	}
	public void setIssid(int issid){
		this.issid=issid;
	}
	public int getTotalTre(){
		return this.totalTre;
	}
	public void setTotalTre(int totalTre){
		this.totalTre=totalTre;
	}

}