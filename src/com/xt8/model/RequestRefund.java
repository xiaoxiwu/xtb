package com.xt8.model;

import java.io.Serializable;
import java.util.Date;

import com.xt8.util.Common;

import net.sf.json.JSONObject;

public class RequestRefund implements Serializable {

	private Integer id; // 主键
	private User user; // 用户ID
	private Order order; // 订单ID
	private Integer reason; // 退款原因编号(是否保留,只在系统自动检测退款条件是否成立时才有用)
	private String description; // //退款理由描述
	private Date time;
	private Admin admin; // 哪一个管理员处理的 -1:系统自动处理
	private Integer status; // 1:成功 0:处理中 -1:系统不允许
	private String statusDecs;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public Integer getReason() {
		return reason;
	}

	public void setReason(Integer reason) {
		this.reason = reason;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public Admin getAdmin() {
		return admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getStatusDecs() {
		return statusDecs;
	}

	public void setStatusDecs(String statusDecs) {
		this.statusDecs = statusDecs;
	}

	public JSONObject toJSON() {
		return Common.toJSON(this);
	}

	@Override
	public String toString() {
		return toJSON().toString();
	}

}
