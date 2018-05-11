package com.game.base.util;


/** 
 * <li>ClassName:NoteResult <br/> 
 * <li>@Description: 结果对象
 * <li>@Date:     2016年10月26日 <br/> 
 * <li>@author   周强       
 */
public class Result  {
	private int status;//0表示成功,其他表示失败
	private String msg;//消息
	private Object data;//返回的数据

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "Result [status=" + status + ", msg=" + msg + ", data=" + data + "]";
	}

}
