package com.xt8.model;

import java.io.Serializable;
import java.util.Date;

import com.xt8.util.Common;

import net.sf.json.JSONObject;

public class VerifyFriend implements Serializable {

	private Integer id;
	private User fstUser; // 添加者ID
	private User secUser; // 被添加者ID
	private Integer status; // 2:成功 1:失败 -1:等待对方验证 -2:等待自己验证
	private String description;

	public VerifyFriend() {
	}

	public VerifyFriend(User fstUser, User secUser, Integer status,
			String description) {
		super();
		this.fstUser = fstUser;
		this.secUser = secUser;
		this.status = status;
		this.description = description;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public User getFstUser() {
		return fstUser;
	}

	public void setFstUser(User fstUser) {
		this.fstUser = fstUser;
	}

	public User getSecUser() {
		return secUser;
	}

	public void setSecUser(User secUser) {
		this.secUser = secUser;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public JSONObject toJSON() {
		return Common.toJSON(this);
	}

	@Override
	public String toString() {
		return toJSON().toString();
	}

}
