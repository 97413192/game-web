package com.game.game.model;
/**
 * <li>@ClassName: 登陆奖励实体类
 * <li>@author 周强
 * <li>@date 2016年11月17日
 */
public class LandingWard {
	private Integer id;  //登陆奖励编号啊id
	private Integer gold;  //登陆奖励对应概率的金币
	private Integer chance;  //登陆奖励概率
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getGold() {
		return gold;
	}
	public void setGold(Integer gold) {
		this.gold = gold;
	}
	public Integer getChance() {
		return chance;
	}
	public void setChance(Integer chance) {
		this.chance = chance;
	}
	@Override
	public String toString() {
		return "LandingWard [id=" + id + ", gold=" + gold + ", chance=" + chance + "]";
	}
	
}
