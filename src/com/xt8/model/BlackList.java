package com.xt8.model;

import java.io.Serializable;
import java.util.Date;

import com.xt8.util.Common;

import net.sf.json.JSONObject;

public class BlackList implements Serializable {
	private Integer id;  //主键
	private User user; 
	private User blackUser;
	private Date addTime = new Date();
	

	public BlackList(){
		
	}
	
	public BlackList(User user,User blackUser){
		this.user = user;
		this.blackUser = blackUser;
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

	public User getBlackUser() {
		return blackUser;
	}

	public void setBlackUser(User blackUser) {
		this.blackUser = blackUser;
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
