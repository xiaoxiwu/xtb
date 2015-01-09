package com.xt8.model;

import java.util.Date;

public class Friends {
	private Integer connId; // 主键
	private Integer selfId; // 外键 用户ID
	private Integer frId; // 同上
	private Date time; // 时间
	
	public Friends(){
	}

	public Friends(Integer selfId, Integer frId, Date time) {
		super();
		this.selfId = selfId;
		this.frId = frId;
		this.time = time;
	}

	public Integer getConnId() {
		return connId;
	}

	public void setConnId(Integer connId) {
		this.connId = connId;
	}

	public Integer getSelfId() {
		return selfId;
	}

	public void setSelfId(Integer selfId) {
		this.selfId = selfId;
	}

	public Integer getFrId() {
		return frId;
	}

	public void setFrId(Integer frId) {
		this.frId = frId;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}
}
