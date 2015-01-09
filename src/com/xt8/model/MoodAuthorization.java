package com.xt8.model;

import java.io.Serializable;
import java.util.Date;

import com.xt8.util.Common;

import net.sf.json.JSONObject;

public class MoodAuthorization implements Serializable {
	private Integer id;  //主键
	private User userHost; 
	private User userLimited;
	private Date addTime = new Date();
	

	public MoodAuthorization(){
		
	}
	
	public MoodAuthorization(User userHost,User userLimited){
		this.userHost = userHost;
		this.userLimited = userLimited;
	}



	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public User getUserHost() {
		return userHost;
	}

	public void setUserHost(User userHost) {
		this.userHost = userHost;
	}

	public User getUserLimited() {
		return userLimited;
	}

	public void setUserLimited(User userLimited) {
		this.userLimited = userLimited;
	}

	public Date getAddTime() {
		return addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	public JSONObject toJSON() {
		return Common.toJSON(this);
	}

	@Override
	public String toString() {
		return toJSON().toString();
	}

}
