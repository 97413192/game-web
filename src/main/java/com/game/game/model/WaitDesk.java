package com.game.game.model;
/**
 * <li>@ClassName: 留桌查看设置实体类
 * <li>@author 周强
 * <li>@date 2016年11月14日
 */
public class WaitDesk {
	private Integer id;  
	private Integer time;  //留桌时间
	private Integer gold;  //留桌金币
	private String typeName;  //房间类型名字
	private Integer typeId;  //房间类型id
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getTime() {
		return time;
	}
	public void setTime(Integer time) {
		this.time = time;
	}
	public Integer getGold() {
		return gold;
	}
	public void setGold(Integer gold) {
		this.gold = gold;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public Integer getTypeId() {
		return typeId;
	}
	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}
	@Override
	public String toString() {
		return "WaitDesk [id=" + id + ", time=" + time + ", gold=" + gold + ", typeName=" + typeName + ", typeId="
				+ typeId + "]";
	}
	
	
}
