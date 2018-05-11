package com.game.game.model;
/**
 * <li>@ClassName: 签到实体类
 * <li>@author 周强
 * <li>@date 2016年11月17日
 */
public class Sign {
	private int id;  //编号id
	private int day;  //签到天数
	private int gold;  //签到金币
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getDay() {
		return day;
	}
	public void setDay(int day) {
		this.day = day;
	}
	public int getGold() {
		return gold;
	}
	public void setGold(int gold) {
		this.gold = gold;
	}
	@Override
	public String toString() {
		return "Sign [id=" + id + ", day=" + day + ", gold=" + gold + "]";
	}
	
}
