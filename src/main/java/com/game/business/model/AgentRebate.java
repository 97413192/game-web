package com.game.business.model;


/**
 * 代理返点
 * @author Administrator
 *
 */
public class AgentRebate {
	private Integer id;
	private Integer grade;//代理等级
	private Double rebate;//返点比例
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getGrade() {
		return grade;
	}
	public void setGrade(Integer grade) {
		this.grade = grade;
	}
	public Double getRebate() {
		return rebate;
	}
	public void setRebate(Double rebate) {
		this.rebate = rebate;
	}
	@Override
	public String toString() {
		return "agentRebate [id=" + id + ", grade=" + grade + ", rebate=" + rebate + "]";
	}
	
	
}