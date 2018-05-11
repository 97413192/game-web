package com.game.business.model.player;

import java.util.Date;

public class RealNameInfo {

		private String  realName;		//名字
		private String  idCard;			//身份证号码
		private String  phoneNumber;    //电话号码
		private Date    applyTime;		//申请时间
		private Integer applyState;		//申请状态  1.正在申请当中 2. 通过实名申请 3. 拒绝实名申请
		private Date    approveTime;	//批准时间
		private Integer shareNum; 		//每天分享次数
		public Integer getShareNum() {
			return shareNum;
		}
		public void setShareNum(Integer shareNum) {
			this.shareNum = shareNum;
		}
		public String getRealName() {
			return realName;
		}
		public String getIdCard() {
			return idCard;
		}
		public String getPhoneNumber() {
			return phoneNumber;
		}
		public Date getApplyTime() {
			return applyTime;
		}
		public Integer getApplyState() {
			return applyState;
		}
		public Date getApproveTime() {
			return approveTime;
		}
		public void setRealName(String realName) {
			this.realName = realName;
		}
		public void setIdCard(String idCard) {
			this.idCard = idCard;
		}
		public void setPhoneNumber(String phoneNumber) {
			this.phoneNumber = phoneNumber;
		}
		public void setApplyTime(Date applyTime) {
			this.applyTime = applyTime;
		}
		public void setApplyState(Integer applyState) {
			this.applyState = applyState;
		}
		public void setApproveTime(Date approveTime) {
			this.approveTime = approveTime;
		}
		
		
		
}
