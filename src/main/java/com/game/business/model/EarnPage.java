package com.game.business.model;

public class EarnPage {
	private Integer userId;
	private Integer grade;
	private String name;
	private Long player;
	private Double earn;
	
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getGrade() {
		return grade;
	}
	public void setGrade(Integer grade) {
		this.grade = grade;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getPlayer() {
		return player;
	}
	public void setPlayer(Long player) {
		this.player = player;
	}
	public Double getEarn() {
		return earn;
	}
	public void setEarn(Double earn) {
		this.earn = earn;
	}
	@Override
	public String toString() {
		return "EarnPage [userId=" + userId + ", grade=" + grade + ", name=" + name + ", player=" + player + ", earn="
				+ earn + "]";
	}

}
