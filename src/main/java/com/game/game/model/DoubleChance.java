package com.game.game.model;
/**
 * <li>@ClassName: 比倍实体类
 * <li>@author 周强
 * <li>@date 2016年11月16日
 */
public class DoubleChance {
	private int times;  //比倍次数
	private int chance;  //比倍概率，0-100 百分比
	public int getTimes() {
		return times;
	}
	public void setTimes(int times) {
		this.times = times;
	}
	public int getChance() {
		return chance;
	}
	public void setChance(int chance) {
		this.chance = chance;
	}
	@Override
	public String toString() {
		return "Double [times=" + times + ", chance=" + chance + "]";
	}
	
}
