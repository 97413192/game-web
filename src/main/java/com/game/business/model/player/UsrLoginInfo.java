package com.game.business.model.player;

import com.game.base.util.DBBuffer;

public class UsrLoginInfo {

	public static final String preKey = "usr_login";

	private String uuid;
	private Boolean isLogin;
	
	public String getUuid() {
		return uuid;
	}

	public void setUid(String uid) {
		this.uuid = uid;
	}

	public Boolean getIsLogin() {
		return isLogin;
	}

	public void setIsLogin(Boolean isLogin) {
		this.isLogin = isLogin;
	}

	public String getPreKey() {
		return preKey;
	}

	public byte[] toBytes() {
		DBBuffer db = DBBuffer.allocate();
		db.putString(uuid);
		db.putBoolean(isLogin);
		byte[] ret = db.toBytes();
		db.free();
		return ret;
	}

	public void fromBytes(byte[] bytes) {
		DBBuffer db = DBBuffer.warp(bytes);
		setUid(db.getString());
		setIsLogin(db.getBoolean());
		db.free();
	}

	@Override
	public String toString() {
		return "UsrLoginInfo [uid=" + uuid + ", isLogin=" + isLogin + "]";
	}
	
	
	
	
}
