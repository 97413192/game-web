package com.game.game.model;

import java.io.Serializable;
import com.game.base.util.DBBuffer;
import com.game.business.model.player.RealNameInfo;

import java.io.InvalidClassException;
	/**
	*此类由MySQLToBean工具自动生成
	*
	*@author 
	*@since 2016-11-10 12:16:21
	*/

public class Player implements Serializable {
	private static final long serialVersionUID = -8531451850532157749L;
	private String id;//
	private String uid;//
	private String pName;//
	private long mny;//
	private long tre;//
	private int ap;//
	private int credit;//
	private int pp;//
	private int vip;//
	private int bagCap;//
	private int ghBagCap;//
	private int ghpBagCap;//
	private int storeCap;//
	private int dupId;//
	private int spDupId;//
	private int leaderCid;//
	private int sid;//
	private int sx;//
	private int sy;//
	private int fid;//
	private long nextRtDate;//
	private int rtCD;//
	private int mcExp;//
	private int mcPart;//
	private int alcProgress;//
	private int pvpRanking;//
	private int pvpRewardMny;//
	private int pvpRewardPp;//
	private boolean isUsePvpReward;//
	private int fightNum;//
	private long lastFightTime;//
	private int dailyQuestNum;//
	private int buyApNum;//
	private int buyMnyNum;//
	private int spDupNum;//
	private int newProc;//
	private long nextABDate;//
	private long bakSpeakEndTime;//
	private long frozenEndTime;//
	private String modifyDate;//
	private String loginDate;//
	private String logoutDate;//
	private int logTimes;//
	private long buyTreasure;//
	private long salary;//
	private int plv;//
	private int dupResetTime;//
	private int countGuajiExp;//
	private String startGuaJiTime;//
	private String endGuaJiTime;//
	private String lastCalExpTime;//
	private int sendRewardRanking;//
	private int rewardIdx;//
	private int avatarId;//
	private long tutorialStatus;//
	private long lastTeamFightDate;//
	private int lastTeamFightTimes;//
	private int buyTeamFightTimes;//
	private int refGemNum;//
	private String serverid;//
	
	/* 2018 4-25 新增加*/
	private RealNameInfo realNameInfo;
	
	private Integer gameId;
	
	public Integer getGameId() {
		return gameId;
	}
	public void setGameId(Integer gameId) {
		this.gameId = gameId;
	}
	public RealNameInfo getRealNameInfo() {
		return realNameInfo;
	}
	public void setRealNameInfo(RealNameInfo realName) {
		this.realNameInfo = realName;
	}
	public byte[] toBytes(){
		DBBuffer db = DBBuffer.allocate();
		db.putLong(serialVersionUID);
		db.putString(this.id);//
		db.putString(this.uid);//
		db.putString(this.pName);//
		db.putLong(this.mny);//
		db.putLong(this.tre);//
		db.putInt(this.ap);//
		db.putInt(this.credit);//
		db.putInt(this.pp);//
		db.putInt(this.vip);//
		db.putInt(this.bagCap);//
		db.putInt(this.ghBagCap);//
		db.putInt(this.ghpBagCap);//
		db.putInt(this.storeCap);//
		db.putInt(this.dupId);//
		db.putInt(this.spDupId);//
		db.putInt(this.leaderCid);//
		db.putInt(this.sid);//
		db.putInt(this.sx);//
		db.putInt(this.sy);//
		db.putInt(this.fid);//
		db.putLong(this.nextRtDate);//
		db.putInt(this.rtCD);//
		db.putInt(this.mcExp);//
		db.putInt(this.mcPart);//
		db.putInt(this.alcProgress);//
		db.putInt(this.pvpRanking);//
		db.putInt(this.pvpRewardMny);//
		db.putInt(this.pvpRewardPp);//
		db.putBoolean(this.isUsePvpReward);//
		db.putInt(this.fightNum);//
		db.putLong(this.lastFightTime);//
		db.putInt(this.dailyQuestNum);//
		db.putInt(this.buyApNum);//
		db.putInt(this.buyMnyNum);//
		db.putInt(this.spDupNum);//
		db.putInt(this.newProc);//
		db.putLong(this.nextABDate);//
		db.putLong(this.bakSpeakEndTime);//
		db.putLong(this.frozenEndTime);//
		db.putString(this.modifyDate);//
		db.putString(this.loginDate);//
		db.putString(this.logoutDate);//
		db.putInt(this.logTimes);//
		db.putLong(this.buyTreasure);//
		db.putLong(this.salary);//
		db.putInt(this.plv);//
		db.putInt(this.dupResetTime);//
		db.putInt(this.countGuajiExp);//
		db.putString(this.startGuaJiTime);//
		db.putString(this.endGuaJiTime);//
		db.putString(this.lastCalExpTime);//
		db.putInt(this.sendRewardRanking);//
		db.putInt(this.rewardIdx);//
		db.putInt(this.avatarId);//
		db.putLong(this.tutorialStatus);//
		db.putLong(this.lastTeamFightDate);//
		db.putInt(this.lastTeamFightTimes);//
		db.putInt(this.buyTeamFightTimes);//
		db.putInt(this.refGemNum);//
		db.putString(this.serverid);//
		byte[] ret = db.toBytes();
		db.free();
		return ret;
	}
	public void fromBytes(byte[] bytes) throws InvalidClassException {
		DBBuffer db = DBBuffer.warp(bytes);
		long suid = db.getLong();
		if (serialVersionUID != suid)
			throw new InvalidClassException("serialVersionUID != serialVersionUID");
		this.id = db.getString();//
		this.uid = db.getString();//
		this.pName = db.getString();//
		this.mny = db.getLong();//
		this.tre = db.getLong();//
		this.ap = db.getInt();//
		this.credit = db.getInt();//
		this.pp = db.getInt();//
		this.vip = db.getInt();//
		this.bagCap = db.getInt();//
		this.ghBagCap = db.getInt();//
		this.ghpBagCap = db.getInt();//
		this.storeCap = db.getInt();//
		this.dupId = db.getInt();//
		this.spDupId = db.getInt();//
		this.leaderCid = db.getInt();//
		this.sid = db.getInt();//
		this.sx = db.getInt();//
		this.sy = db.getInt();//
		this.fid = db.getInt();//
		this.nextRtDate = db.getLong();//
		this.rtCD = db.getInt();//
		this.mcExp = db.getInt();//
		this.mcPart = db.getInt();//
		this.alcProgress = db.getInt();//
		this.pvpRanking = db.getInt();//
		this.pvpRewardMny = db.getInt();//
		this.pvpRewardPp = db.getInt();//
		this.isUsePvpReward = db.getBoolean();//
		this.fightNum = db.getInt();//
		this.lastFightTime = db.getLong();//
		this.dailyQuestNum = db.getInt();//
		this.buyApNum = db.getInt();//
		this.buyMnyNum = db.getInt();//
		this.spDupNum = db.getInt();//
		this.newProc = db.getInt();//
		this.nextABDate = db.getLong();//
		this.bakSpeakEndTime = db.getLong();//
		this.frozenEndTime = db.getLong();//
		this.modifyDate = db.getString();//
		this.loginDate = db.getString();//
		this.logoutDate = db.getString();//
		this.logTimes = db.getInt();//
		this.buyTreasure = db.getLong();//
		this.salary = db.getLong();//
		this.plv = db.getInt();//
		this.dupResetTime = db.getInt();//
		this.countGuajiExp = db.getInt();//
		this.startGuaJiTime = db.getString();//
		this.endGuaJiTime = db.getString();//
		this.lastCalExpTime = db.getString();//
		this.sendRewardRanking = db.getInt();//
		this.rewardIdx = db.getInt();//
		this.avatarId = db.getInt();//
		this.tutorialStatus = db.getLong();//
		this.lastTeamFightDate = db.getLong();//
		this.lastTeamFightTimes = db.getInt();//
		this.buyTeamFightTimes = db.getInt();//
		this.refGemNum = db.getInt();//
		this.serverid = db.getString();//
		db.free();
	}
	public String getId(){
		return this.id;
	}
	public void setId(String id){
		this.id=id;
	}
	public String getUid(){
		return this.uid;
	}
	public void setUid(String uid){
		this.uid=uid;
	}
	public String getPName(){
		return this.pName;
	}
	public void setPName(String pName){
		this.pName=pName;
	}
	public long getMny(){
		return this.mny;
	}
	public void setMny(long mny){
		this.mny=mny;
	}
	public long getTre(){
		return this.tre;
	}
	public void setTre(long tre){
		this.tre=tre;
	}
	public int getAp(){
		return this.ap;
	}
	public void setAp(int ap){
		this.ap=ap;
	}
	public int getCredit(){
		return this.credit;
	}
	public void setCredit(int credit){
		this.credit=credit;
	}
	public int getPp(){
		return this.pp;
	}
	public void setPp(int pp){
		this.pp=pp;
	}
	public int getVip(){
		return this.vip;
	}
	public void setVip(int vip){
		this.vip=vip;
	}
	public int getBagCap(){
		return this.bagCap;
	}
	public void setBagCap(int bagCap){
		this.bagCap=bagCap;
	}
	public int getGhBagCap(){
		return this.ghBagCap;
	}
	public void setGhBagCap(int ghBagCap){
		this.ghBagCap=ghBagCap;
	}
	public int getGhpBagCap(){
		return this.ghpBagCap;
	}
	public void setGhpBagCap(int ghpBagCap){
		this.ghpBagCap=ghpBagCap;
	}
	public int getStoreCap(){
		return this.storeCap;
	}
	public void setStoreCap(int storeCap){
		this.storeCap=storeCap;
	}
	public int getDupId(){
		return this.dupId;
	}
	public void setDupId(int dupId){
		this.dupId=dupId;
	}
	public int getSpDupId(){
		return this.spDupId;
	}
	public void setSpDupId(int spDupId){
		this.spDupId=spDupId;
	}
	public int getLeaderCid(){
		return this.leaderCid;
	}
	public void setLeaderCid(int leaderCid){
		this.leaderCid=leaderCid;
	}
	public int getSid(){
		return this.sid;
	}
	public void setSid(int sid){
		this.sid=sid;
	}
	public int getSx(){
		return this.sx;
	}
	public void setSx(int sx){
		this.sx=sx;
	}
	public int getSy(){
		return this.sy;
	}
	public void setSy(int sy){
		this.sy=sy;
	}
	public int getFid(){
		return this.fid;
	}
	public void setFid(int fid){
		this.fid=fid;
	}
	public long getNextRtDate(){
		return this.nextRtDate;
	}
	public void setNextRtDate(long nextRtDate){
		this.nextRtDate=nextRtDate;
	}
	public int getRtCD(){
		return this.rtCD;
	}
	public void setRtCD(int rtCD){
		this.rtCD=rtCD;
	}
	public int getMcExp(){
		return this.mcExp;
	}
	public void setMcExp(int mcExp){
		this.mcExp=mcExp;
	}
	public int getMcPart(){
		return this.mcPart;
	}
	public void setMcPart(int mcPart){
		this.mcPart=mcPart;
	}
	public int getAlcProgress(){
		return this.alcProgress;
	}
	public void setAlcProgress(int alcProgress){
		this.alcProgress=alcProgress;
	}
	public int getPvpRanking(){
		return this.pvpRanking;
	}
	public void setPvpRanking(int pvpRanking){
		this.pvpRanking=pvpRanking;
	}
	public int getPvpRewardMny(){
		return this.pvpRewardMny;
	}
	public void setPvpRewardMny(int pvpRewardMny){
		this.pvpRewardMny=pvpRewardMny;
	}
	public int getPvpRewardPp(){
		return this.pvpRewardPp;
	}
	public void setPvpRewardPp(int pvpRewardPp){
		this.pvpRewardPp=pvpRewardPp;
	}
	public boolean isUsePvpReward(){
		return this.isUsePvpReward;
	}
	public void isUsePvpReward(boolean isUsePvpReward){
		this.isUsePvpReward=isUsePvpReward;
	}
	public int getFightNum(){
		return this.fightNum;
	}
	public void setFightNum(int fightNum){
		this.fightNum=fightNum;
	}
	public long getLastFightTime(){
		return this.lastFightTime;
	}
	public void setLastFightTime(long lastFightTime){
		this.lastFightTime=lastFightTime;
	}
	public int getDailyQuestNum(){
		return this.dailyQuestNum;
	}
	public void setDailyQuestNum(int dailyQuestNum){
		this.dailyQuestNum=dailyQuestNum;
	}
	public int getBuyApNum(){
		return this.buyApNum;
	}
	public void setBuyApNum(int buyApNum){
		this.buyApNum=buyApNum;
	}
	public int getBuyMnyNum(){
		return this.buyMnyNum;
	}
	public void setBuyMnyNum(int buyMnyNum){
		this.buyMnyNum=buyMnyNum;
	}
	public int getSpDupNum(){
		return this.spDupNum;
	}
	public void setSpDupNum(int spDupNum){
		this.spDupNum=spDupNum;
	}
	public int getNewProc(){
		return this.newProc;
	}
	public void setNewProc(int newProc){
		this.newProc=newProc;
	}
	public long getNextABDate(){
		return this.nextABDate;
	}
	public void setNextABDate(long nextABDate){
		this.nextABDate=nextABDate;
	}
	public long getBakSpeakEndTime(){
		return this.bakSpeakEndTime;
	}
	public void setBakSpeakEndTime(long bakSpeakEndTime){
		this.bakSpeakEndTime=bakSpeakEndTime;
	}
	public long getFrozenEndTime(){
		return this.frozenEndTime;
	}
	public void setFrozenEndTime(long frozenEndTime){
		this.frozenEndTime=frozenEndTime;
	}
	public String getModifyDate(){
		return this.modifyDate;
	}
	public void setModifyDate(String modifyDate){
		this.modifyDate=modifyDate;
	}
	public String getLoginDate(){
		return this.loginDate;
	}
	public void setLoginDate(String loginDate){
		this.loginDate=loginDate;
	}
	public String getLogoutDate(){
		return this.logoutDate;
	}
	public void setLogoutDate(String logoutDate){
		this.logoutDate=logoutDate;
	}
	public int getLogTimes(){
		return this.logTimes;
	}
	public void setLogTimes(int logTimes){
		this.logTimes=logTimes;
	}
	public long getBuyTreasure(){
		return this.buyTreasure;
	}
	public void setBuyTreasure(long buyTreasure){
		this.buyTreasure=buyTreasure;
	}
	public long getSalary(){
		return this.salary;
	}
	public void setSalary(long salary){
		this.salary=salary;
	}
	public int getPlv(){
		return this.plv;
	}
	public void setPlv(int plv){
		this.plv=plv;
	}
	public int getDupResetTime(){
		return this.dupResetTime;
	}
	public void setDupResetTime(int dupResetTime){
		this.dupResetTime=dupResetTime;
	}
	public int getCountGuajiExp(){
		return this.countGuajiExp;
	}
	public void setCountGuajiExp(int countGuajiExp){
		this.countGuajiExp=countGuajiExp;
	}
	public String getStartGuaJiTime(){
		return this.startGuaJiTime;
	}
	public void setStartGuaJiTime(String startGuaJiTime){
		this.startGuaJiTime=startGuaJiTime;
	}
	public String getEndGuaJiTime(){
		return this.endGuaJiTime;
	}
	public void setEndGuaJiTime(String endGuaJiTime){
		this.endGuaJiTime=endGuaJiTime;
	}
	public String getLastCalExpTime(){
		return this.lastCalExpTime;
	}
	public void setLastCalExpTime(String lastCalExpTime){
		this.lastCalExpTime=lastCalExpTime;
	}
	public int getSendRewardRanking(){
		return this.sendRewardRanking;
	}
	public void setSendRewardRanking(int sendRewardRanking){
		this.sendRewardRanking=sendRewardRanking;
	}
	public int getRewardIdx(){
		return this.rewardIdx;
	}
	public void setRewardIdx(int rewardIdx){
		this.rewardIdx=rewardIdx;
	}
	public int getAvatarId(){
		return this.avatarId;
	}
	public void setAvatarId(int avatarId){
		this.avatarId=avatarId;
	}
	public long getTutorialStatus(){
		return this.tutorialStatus;
	}
	public void setTutorialStatus(long tutorialStatus){
		this.tutorialStatus=tutorialStatus;
	}
	public long getLastTeamFightDate(){
		return this.lastTeamFightDate;
	}
	public void setLastTeamFightDate(long lastTeamFightDate){
		this.lastTeamFightDate=lastTeamFightDate;
	}
	public int getLastTeamFightTimes(){
		return this.lastTeamFightTimes;
	}
	public void setLastTeamFightTimes(int lastTeamFightTimes){
		this.lastTeamFightTimes=lastTeamFightTimes;
	}
	public int getBuyTeamFightTimes(){
		return this.buyTeamFightTimes;
	}
	public void setBuyTeamFightTimes(int buyTeamFightTimes){
		this.buyTeamFightTimes=buyTeamFightTimes;
	}
	public int getRefGemNum(){
		return this.refGemNum;
	}
	public void setRefGemNum(int refGemNum){
		this.refGemNum=refGemNum;
	}
	public String getServerid(){
		return this.serverid;
	}
	public void setServerid(String serverid){
		this.serverid=serverid;
	}

}