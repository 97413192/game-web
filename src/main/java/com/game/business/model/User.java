package com.game.business.model;

import java.io.Serializable;

import javax.persistence.Column;
/**
 * 实体类
 * 
 * @author Administrator
 *
 */
public class User implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2656965243715693610L;
	//id
    @Column(name = "user_name")
	private Integer userName;
	//用户名
    @Column(name = "user_name")
	private String password;
	
	//用户名
    @Column(name = "user_nickname")
	private String userNickname;

	public Integer getUserName() {
		return userName;
	}

	public void setUserName(Integer userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserNickname() {
		return userNickname;
	}

	public void setUserNickname(String userNickname) {
		this.userNickname = userNickname;
	}	
    
    
}
