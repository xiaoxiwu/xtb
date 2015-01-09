package com.xt8.model;

import java.io.Serializable;
import java.util.Date;

import com.xt8.util.Common;

import net.sf.json.JSONObject;

public class ConsumeRecord implements Serializable {
	 
	private Integer id;// 主键
	//private Integer userId ;//  用户ID
	private User user; // 外键 用户ID
	private Double money; //消费金额
	private String description;// 消费原因描述 
	private Date time = new java.util.Date();; //消费时间
	private Integer result ;// 消费结果  1:成功  -1:失败
	private String resultDesc;// 消费结果描述 
	private Integer ifDel=0;   //用户是否删除   0:未删除   1: 已删除
	
	
	public ConsumeRecord(){
		
	}
	
	public ConsumeRecord(User user,Double money,String description){
		this.user = user;
		this.money = money;
		this.description = description;
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

	public Double getMoney() {
		return money;
	}

	public void setMoney(Double money) {
		this.money = money;
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

	public Integer getResult() {
		return result;
	}

	public void setResult(Integer result) {
		this.result = result;
	}

	public String getResultDesc() {
		return resultDesc;
	}

	public void setResultDesc(String resultDesc) {
		this.resultDesc = resultDesc;
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
