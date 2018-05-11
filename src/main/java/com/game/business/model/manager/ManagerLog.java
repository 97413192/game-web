package com.game.business.model.manager;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/** 
 * <li>ClassName:ManagerLogin <br/> 
 * <li>@Description:  管理员日志实体类
 * <li>@Date:    2016年10月25日 <br/> 
 * <li>@author   周强      
 */

@Table(name = "manager_login")
public class ManagerLog {
	public static String success = "成功！";
	public static String error = "失败！";
	//日志id
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	//管理员名字
	@Column(name = "name")
	private String name;
	//管理员上次登陆的ip
	@Column(name = "ip")
	private String ip;
	//管理员上次登陆的时间
	@Column(name = "time")
	private Timestamp time;
	@Column(name = "system")
	//管理员上次登陆的操作系统
	private String system;
	//管理员关键操作描述
	@Column(name = "dsc")
	private String dsc;
	//管理员登陆成功的与否的状态
	@Column(name = "status")
	private String status;
	
	public String getDsc() {
		return dsc;
	}
	public void setDsc(String dsc) {
		this.dsc = dsc;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getSystem() {
		return system;
	}
	public void setSystem(String system) {
		this.system = system;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public Timestamp getTime() {
		return time;
	}
	public void setTime(Timestamp time) {
		this.time = time;
	}
	@Override
	public String toString() {
		return "ManagerLog [id=" + id + ", name=" + name + ", ip=" + ip + ", time=" + time + ", system=" + system
				+ ", dsc=" + dsc + ", status=" + status + "]";
	}
	
}
