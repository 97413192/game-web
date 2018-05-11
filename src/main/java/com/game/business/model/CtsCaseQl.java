package com.game.business.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "cts_case_ql")
public class CtsCaseQl{
    /**
     * ID
     */
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @Column(name = "SOURCE_TYPE")
    private String sourceType;
    @Column(name = "INCIDENT_DATE")
    private Date incidentDate;

    @Column(name = "CITY")
    private String city;

    @Column(name = "POS_NO")
    private String posNo;
    
    @Column(name = "POS_NAME")
    private String posName;

    @Column(name = "LOAN_NO")
    private String loanNo;

    @Column(name = "TAR_NAME")
    private String tarName;

    @Column(name = "TAR_NO")
    private String tarNO;

    @Column(name = "TAR_ORG")
    private String tarOrg;

    @Column(name = "TAR_POST")
    private String tarPost;

    @Column(name = "TAR_PHONE")
    private String tarPhone;

    @Column(name = "DEAL_STAFF_NO")
    private String dealStaffNo;

    @Column(name = "DEAL_STAT")
    private String dealStat;

    @Column(name = "DEAL_DATE")
    private Date dealDate;

    @Column(name = "INST_DATE")
    private Date instDate;

    @Column(name = "CUST_NAME")
    private String custName;

    @Column(name = "CUST_CARD")
    private String custCard;

    @Column(name = "CUST_TEL")
    private String custTel;

    @Column(name = "CASE_DESC")
    private String caseDesc;

    @Column(name = "ARCH_NOTE")
    private String archNote;


	public String getId() {
		return id;
	}



	public void setId(String id) {
		this.id = id;
	}



	public String getSourceType() {
		return sourceType;
	}



	public void setSourceType(String sourceType) {
		this.sourceType = sourceType;
	}



	public Date getIncidentDate() {
		return incidentDate;
	}



	public void setIncidentDate(Date incidentDate) {
		this.incidentDate = incidentDate;
	}



	public String getCity() {
		return city;
	}



	public void setCity(String city) {
		this.city = city;
	}



	public String getPosNo() {
		return posNo;
	}



	public void setPosNo(String posNo) {
		this.posNo = posNo;
	}



	public String getPosName() {
		return posName;
	}



	public void setPosName(String posName) {
		this.posName = posName;
	}



	public String getLoanNo() {
		return loanNo;
	}



	public void setLoanNo(String loanNo) {
		this.loanNo = loanNo;
	}



	public String getTarName() {
		return tarName;
	}



	public void setTarName(String tarName) {
		this.tarName = tarName;
	}



	public String getTarNO() {
		return tarNO;
	}



	public void setTarNO(String tarNO) {
		this.tarNO = tarNO;
	}



	public String getTarOrg() {
		return tarOrg;
	}



	public void setTarOrg(String tarOrg) {
		this.tarOrg = tarOrg;
	}



	public String getTarPost() {
		return tarPost;
	}



	public void setTarPost(String tarPost) {
		this.tarPost = tarPost;
	}



	public String getTarPhone() {
		return tarPhone;
	}



	public void setTarPhone(String tarPhone) {
		this.tarPhone = tarPhone;
	}



	public String getDealStaffNo() {
		return dealStaffNo;
	}



	public void setDealStaffNo(String dealStaffNo) {
		this.dealStaffNo = dealStaffNo;
	}



	public String getDealStat() {
		return dealStat;
	}



	public void setDealStat(String dealStat) {
		this.dealStat = dealStat;
	}



	public Date getDealDate() {
		return dealDate;
	}



	public void setDealDate(Date dealDate) {
		this.dealDate = dealDate;
	}



	public Date getInstDate() {
		return instDate;
	}



	public void setInstDate(Date instDate) {
		this.instDate = instDate;
	}



	public String getCustName() {
		return custName;
	}



	public void setCustName(String custName) {
		this.custName = custName;
	}



	public String getCustCard() {
		return custCard;
	}



	public void setCustCard(String custCard) {
		this.custCard = custCard;
	}



	public String getCustTel() {
		return custTel;
	}



	public void setCustTel(String custTel) {
		this.custTel = custTel;
	}



	public String getCaseDesc() {
		return caseDesc;
	}



	public void setCaseDesc(String caseDesc) {
		this.caseDesc = caseDesc;
	}



	public String getArchNote() {
		return archNote;
	}



	public void setArchNote(String archNote) {
		this.archNote = archNote;
	}



	@Override
	public String toString() {
		return "CtsCaseQl [id=" + id + ", sourceType=" + sourceType
				+ ", incidentDate=" + incidentDate + ", city=" + city
				+ ", posNo=" + posNo + ", loanNo=" + loanNo + ", tarName="
				+ tarName + ", tarNo=" + tarNO + ", tarOrg=" + tarOrg
				+ ", tarPost=" + tarPost + ", tarPhone=" + tarPhone
				+ ", dealStaffNo=" + dealStaffNo + ", dealStat=" + dealStat
				+ ", dealDate=" + dealDate + ", instDate=" + instDate
				+ ", custName=" + custName + ", custCard=" + custCard
				+ ", custTel=" + custTel + ", caseDesc="
				+ caseDesc + ", archNote="
				+ archNote + "]";
	}
    
}