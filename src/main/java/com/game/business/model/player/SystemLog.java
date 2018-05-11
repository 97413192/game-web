package com.game.business.model.player;

import java.io.Serializable;

public class SystemLog implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8950705521211282506L;

	private int id;
	
	private String date;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
	
}
