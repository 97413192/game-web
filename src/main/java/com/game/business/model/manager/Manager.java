package com.game.business.model.manager;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
/** 
 * <li>ClassName:Manager <br/> 
 * <li>@Description: 管理员实体类
 * <li>@Date:     2016年10月25日 <br/> 
 * <li>@author   周强       
 */
@Table(name = "manager")
public class Manager {
	/** 管理员或者代理商登时间*/
	public static long loginTime;
	
	//管理员ID
	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	//管理员名字
	@Column(name = "name")
	private String name;
	
	//管理员密码
	@Column(name = "password")
	private String password;
	
	//管理员权限
	@Column(name = "power")
	private String power;
	
	//管理员注册的IP
	@Column(name = "ip")
	private String ip;
	
	//管理员注册的操作系统
	@Column(name = "system")
	private String system;
	
	//管理员注册的时间
	@Column(name = "time")
	private Timestamp time;
	
	//上次登录的时间
	@Column(name = "lastLoginTime")
	private Timestamp lastLoginTime;
	
	//上次登录的ip
	@Column(name = "lastLoginIp")
	private String lastLoginIp;
	
	//上次修改时间
	@Column(name = "modifyTime")
	private Timestamp modifyTime;
	
	//在线时间
	@Column(name = "onlineTime")
	private Integer onlineTime;
	
	//累积在线时间
	@Column(name = "heapOnlineTime")
	private Integer heapOnlineTime;
	
	//管理员标志,用于区分代理商
	@Column(name = "managerMark")
	private Integer mark;
	
	//管理员分类,1表示超级管理员,2表示其他的管理员
	@Column(name = "category")
	private Integer category;
	
	//管理员的状态,1表示正常,2冻结,3表示离线
	@Column(name = "status")
	private Integer status;
	
	//登录次数
	@Column(name = "loginTimes")
	private Integer loginTimes;
	
	public Integer getCategory() {
		return category;
	}
	public void setCategory(Integer category) {
		this.category = category;
	}
	public Integer getStatus() {
		return status;
	}
	public Timestamp getLastLoginTime() {
		return lastLoginTime;
	}
	public void setLastLoginTime(Timestamp lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}
	public String getLastLoginIp() {
		return lastLoginIp;
	}
	public void setLastLoginIp(String lastLoginIp) {
		this.lastLoginIp = lastLoginIp;
	}
	public Timestamp getModifyTime() {
		return modifyTime;
	}
	public void setModifyTime(Timestamp modifyTime) {
		this.modifyTime = modifyTime;
	}
	public Integer getOnlineTime() {
		return onlineTime;
	}
	public void setOnlineTime(Integer onlineTime) {
		this.onlineTime = onlineTime;
	}
	public Integer getHeapOnlineTime() {
		return heapOnlineTime;
	}
	public void setHeapOnlineTime(Integer heapOnlineTime) {
		this.heapOnlineTime = heapOnlineTime;
	}
	public Integer getLoginTimes() {
		return loginTimes;
	}
	public void setLoginTimes(Integer loginTimes) {
		this.loginTimes = loginTimes;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPower() {
		return power;
	}
	public void setPower(String power) {
		this.power = power;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getSystem() {
		return system;
	}
	public void setSystem(String system) {
		this.system = system;
	}
	public Timestamp getTime() {
		return time;
	}
	public void setTime(Timestamp time) {
		this.time = time;
	}
	
	public Integer getMark() {
		return mark;
	}
	public void setMark(Integer mark) {
		this.mark = mark;
	}
	
	
	@Override
	public String toString() {
		return "Manager [id=" + id + ", name=" + name + ", password=" + password + ", power=" + power + ", ip=" + ip
				+ ", system=" + system + ", time=" + time + ", lastLoginTime=" + lastLoginTime + ", lastLoginIp="
				+ lastLoginIp + ", modifyTime=" + modifyTime + ", onlineTime=" + onlineTime + ", heapOnlineTime="
				+ heapOnlineTime + ", mark=" + mark + ", category=" + category + ", status=" + status + ", loginTimes="
				+ loginTimes + "]";
	}
	
	
	
	
	
	
	
}
