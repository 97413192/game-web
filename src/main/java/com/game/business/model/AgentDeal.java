package com.game.business.model;
/**
 * 代理交易记录实体类
 * @author Z
 */
import java.io.Serializable;
import java.sql.Timestamp;

public class AgentDeal implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer agentDealID;		//交易ID
	private Timestamp dealHour;			//交易时间
	//private String dealHour;			//交易时间
	private String remitterAccount;		//汇款人账户,管理员和代理商
	private String payeeAccount;		//收款人账户
	private String dealType;			//交易类型,1.管理员充值2.代理商充值
	private Integer remitterRoomCard;	//汇款人房卡
	private Integer payeeRoomCard;		//收款人房卡
	private String operateIP;			//操作IP
	private Integer sellNumber;			//出售数量
	private Double sellMoney;			//出售金额
	private String sellReason;			//出售原因
	
	
	public Integer getAgentDealID() {
		return agentDealID;
	}
	public void setAgentDealID(Integer agentDealID) {
		this.agentDealID = agentDealID;
	}
	public Timestamp getDealHour() {
		return dealHour;
	}
	public void setDealHour(Timestamp dealHour) {
		this.dealHour = dealHour;
	}
	public String getRemitterAccount() {
		return remitterAccount;
	}
	public void setRemitterAccount(String remitterAccount) {
		this.remitterAccount = remitterAccount;
	}
	public String getPayeeAccount() {
		return payeeAccount;
	}
	public void setPayeeAccount(String payeeAccount) {
		this.payeeAccount = payeeAccount;
	}
	public String getDealType() {
		return dealType;
	}
	public void setDealType(String dealType) {
		this.dealType = dealType;
	}
	public Integer getRemitterRoomCard() {
		return remitterRoomCard;
	}
	public void setRemitterRoomCard(Integer remitterRoomCard) {
		this.remitterRoomCard = remitterRoomCard;
	}
	public Integer getPayeeRoomCard() {
		return payeeRoomCard;
	}
	public void setPayeeRoomCard(Integer payeeRoomCard) {
		this.payeeRoomCard = payeeRoomCard;
	}
	public String getOperateIP() {
		return operateIP;
	}
	public void setOperateIP(String operateIP) {
		this.operateIP = operateIP;
	}
	public Integer getSellNumber() {
		return sellNumber;
	}
	public void setSellNumber(Integer sellNumber) {
		this.sellNumber = sellNumber;
	}
	public Double getSellMoney() {
		return sellMoney;
	}
	public void setSellMoney(Double sellMoney) {
		this.sellMoney = sellMoney;
	}
	public String getSellReason() {
		return sellReason;
	}
	public void setSellReason(String sellReason) {
		this.sellReason = sellReason;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((agentDealID == null) ? 0 : agentDealID.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AgentDeal other = (AgentDeal) obj;
		if (agentDealID == null) {
			if (other.agentDealID != null)
				return false;
		} else if (!agentDealID.equals(other.agentDealID))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "AgentDeal [agentDealID=" + agentDealID + ", dealHour=" + dealHour + ", remitterAccount="
				+ remitterAccount + ", payeeAccount=" + payeeAccount + ", dealType=" + dealType + ", remitterRoomCard="
				+ remitterRoomCard + ", payeeRoomCard=" + payeeRoomCard + ", operateIP=" + operateIP + ", sellNumber="
				+ sellNumber + ", sellMoney=" + sellMoney + ", sellReason=" + sellReason + "]";
	}
	
}
