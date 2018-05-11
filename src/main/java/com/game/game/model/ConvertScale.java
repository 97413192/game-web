package com.game.game.model;
/**
 * <li>@ClassName: 钻石兑换金币比例实体类
 * <li>@author 周强
 * <li>@date 2016年11月18日
 */
public class ConvertScale {
	private Integer id;  //编号id
	private Integer convertScnle;  //钻石兑换金币比例
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getConvertScnle() {
		return convertScnle;
	}
	public void setConvertScnle(Integer convertScnle) {
		this.convertScnle = convertScnle;
	}
	@Override
	public String toString() {
		return "ConvertScale [id=" + id + ", convertScnle=" + convertScnle + "]";
	}
	
	
}
