package com.game.game.model;
/**
 * <li>@ClassName: 充值档次实体类
 * <li>@author 周强
 * <li>@date 2016年11月18日
 */
public class Level {
	private Integer id;
	private Integer levelId;
	private Integer money;
	private Integer jewel;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getLevelId(){
		return levelId;
	}
	public void setLevelId(Integer levelId) {
		this.levelId = levelId;
	}
	public Integer getMoney() {
		return money;
	}
	public void setMoney(Integer money) {
		this.money = money;
	}
	public Integer getJewel() {
		return jewel;
	}
	public void setJewel(Integer jewel) {
		this.jewel = jewel;
	}
	@Override
	public String toString() {
		return "Level [id=" + id + ", levelId=" + levelId + ", money=" + money + ", jewel=" + jewel + "]";
	}
	
}
