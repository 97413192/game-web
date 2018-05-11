package com.game.business.model;

import java.io.Serializable;
/**
 * 实体类
 * 
 * @author Administrator
 *
 */
public class Admin implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5623348833435534317L;
	//id
	private Integer id;
	//用户名
	private String name;
	//密码
	private String password;
	//邮箱
	private String email;
	//兴趣爱好
	private String interest;
	//电话号码
	private String phoneCode;
	//地址
	private String address;
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getInterest() {
		return interest;
	}
	public void setInterest(String interest) {
		this.interest = interest;
	}
	public String getPhoneCode() {
		return phoneCode;
	}
	public void setPhoneCode(String phoneCode) {
		this.phoneCode = phoneCode;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	@Override
	public String toString() {
		return "Admin [id=" + id + ", name=" + name + ", password=" + password + ", email=" + email + ", interest="
				+ interest + ", phoneCode=" + phoneCode + ", address=" + address + "]";
	}
	
	
	
}
