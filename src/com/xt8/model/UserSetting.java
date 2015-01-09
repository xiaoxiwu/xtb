package com.xt8.model;

import java.io.Serializable;
import java.util.Date;


import com.xt8.util.Common;

import net.sf.json.JSONObject;

public class UserSetting implements Serializable {

	private Integer id;  // 主键
	private User user;  //用户ID
	private Setting setting; //设置类别ID
	private Integer value;// 设置值
	private Date updatedTime;

	public UserSetting(){
		
	}
	public UserSetting(User user,Setting setting,Integer value){
		this.user = user;
		this.setting = setting;
		this.value = value;
	}
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

	public Setting getSetting() {
		return setting;
	}

	public void setSetting(Setting setting) {
		this.setting = setting;
	}

	public Integer getValue() {
		return value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}

	public Date getUpdatedTime() {
		return updatedTime;
	}

	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}

	public JSONObject toJSON() {
		return Common.toJSON(this);
	}

	@Override
	public String toString() {
		return toJSON().toString();
	}

}
