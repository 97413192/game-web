package com.game.business.model.player;

/**
 * 此类由MySQLToBean工具自动生成
 * 
 * @author
 * @since 2016-07-13 11:25:52
 */

public class IshangScale {
	private int id;// ID
	private int ptid;// 充值渠道号3->神州付移动,4->神州付联通
	private int price;// 面值
	private int total;// 总额
	private String info;//

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPtid() {
		return this.ptid;
	}

	public void setPtid(int ptid) {
		this.ptid = ptid;
	}

	public int getPrice() {
		return this.price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getTotal() {
		return this.total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public String getInfo() {
		return this.info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

}