package com.xt8.model;

import java.io.Serializable;
import java.util.Date;

import com.xt8.util.Common;

import net.sf.json.JSONObject;

public class UserAdvice implements Serializable {
	
	private Integer id;   // 主键
	private User user;//  用户ID
	private String contact;	//	联系方式
	private Date time = new Date();
	private String content;
	private Integer ifLookup=0;//   是否浏览过
	private Integer ifDel=0;

	public UserAdvice(){
		
	}
	
	public UserAdvice(User user,String contact,String content){
		
		this.user = user;
		this.contact = contact;
		this.content = content;
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

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getIfLookup() {
		return ifLookup;
	}

	public void setIfLookup(Integer ifLookup) {
		this.ifLookup = ifLookup;
	}

	public Integer getIfDel() {
		return ifDel;
	}

	public void setIfDel(Integer ifDel) {
		this.ifDel = ifDel;
	}

	public JSONObject toJSON() {
		return Common.toJSON(this);
	}

	@Override
	public String toString() {
		return toJSON().toString();
	}

}
