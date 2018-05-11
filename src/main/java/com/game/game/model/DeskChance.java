package com.game.game.model;
/**
 * <li>@ClassName: 桌子概率实体类
 * <li>@author 周强
 * <li>@date 2016年11月21日
 */
public class DeskChance {
	private Integer id;
	private Integer K5;  //牌型5.K
	private Integer RS;  //牌型R.S
	private Integer SF;  //牌型S.F
	private Integer K4;  //牌型4.K
	private Integer FH;  //牌型F.H
	private Integer FL;  //牌型F.L
	private Integer ST;  //牌型S.T
	private Integer K3;  //牌型3.K
	private Integer P2;  //牌型2.P
	private Integer P1;  //牌型1.P
	private Integer NP;  //牌型N.F
	private Integer top;  //触发条件上限
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getK5() {
		return K5;
	}
	public void setK5(Integer k5) {
		K5 = k5;
	}
	public Integer getRS() {
		return RS;
	}
	public void setRS(Integer rS) {
		RS = rS;
	}
	public Integer getSF() {
		return SF;
	}
	public void setSF(Integer sF) {
		SF = sF;
	}
	public Integer getK4() {
		return K4;
	}
	public void setK4(Integer k4) {
		K4 = k4;
	}
	public Integer getFH() {
		return FH;
	}
	public void setFH(Integer fH) {
		FH = fH;
	}
	public Integer getFL() {
		return FL;
	}
	public void setFL(Integer fL) {
		FL = fL;
	}
	public Integer getST() {
		return ST;
	}
	public void setST(Integer sT) {
		ST = sT;
	}
	public Integer getK3() {
		return K3;
	}
	public void setK3(Integer k3) {
		K3 = k3;
	}
	public Integer getP2() {
		return P2;
	}
	public void setP2(Integer p2) {
		P2 = p2;
	}
	public Integer getP1() {
		return P1;
	}
	public void setP1(Integer p1) {
		P1 = p1;
	}
	public Integer getNP() {
		return NP;
	}
	public void setNP(Integer nP) {
		NP = nP;
	}
	
	public Integer getTop() {
		return top;
	}
	public void setTop(Integer top) {
		this.top = top;
	}
	@Override
	public String toString() {
		return "DeskChance [id=" + id + ", K5=" + K5 + ", RS=" + RS + ", SF=" + SF + ", K4=" + K4 + ", FH=" + FH
				+ ", FL=" + FL + ", ST=" + ST + ", K3=" + K3 + ", P2=" + P2 + ", P1=" + P1 + ", NP=" + NP + ", top="
				+ top + "]";
	}
	
}
