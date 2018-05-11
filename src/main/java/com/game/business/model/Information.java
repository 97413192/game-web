package com.game.business.model;

import java.sql.Timestamp;

/**
 * 消息实体类
 * @author Administrator
 *
 */
public class Information {

	private Integer id;
	
	private String message;  //消息内容
	
	private Integer category;  //类别,1表示跑马灯,2表示公告
	
	private Timestamp createTime;
	
	private Timestamp modifyTime;
	
//	private Integer status;
//
//	public Integer getStatus() {
//		return status;
//	}
//
//	public void setStatus(Integer status) {
//		this.status = status;
//	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Integer getCategory() {
		return category;
	}

	public void setCategory(Integer category) {
		this.category = category;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public Timestamp getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Timestamp modifyTime) {
		this.modifyTime = modifyTime;
	}

	@Override
	public String toString() {
		return "Information [id=" + id + ", message=" + message + ", category=" + category + ", createTime="
				+ createTime + ", modifyTime=" + modifyTime + "]";
	}
	
	
}
