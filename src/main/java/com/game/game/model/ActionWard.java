package com.game.game.model;
/**
 * <li>@ClassName: 活动奖励实体类
 * <li>@author 周强
 * <li>@date 2016年11月17日
 */
public class ActionWard {
	private Integer id;  //编号id
	private Integer type;  //奖项类型
	private String wardName;  //奖项名字
	private Integer gold;  //金币
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getWardName() {
		return wardName;
	}
	public void setWardName(String wardName) {
		this.wardName = wardName;
	}
	public Integer getGold() {
		return gold;
	}
	public void setGold(Integer gold) {
		this.gold = gold;
	}
	@Override
	public String toString() {
		return "ActionWard [id=" + id + ", type=" + type + ", wardName=" + wardName + ", gold=" + gold + "]";
	}
	
}
