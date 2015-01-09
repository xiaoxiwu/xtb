package com.xt8.model;

import java.io.Serializable;
import java.util.Date;

import com.xt8.util.Common;

import net.sf.json.JSONObject;

public class RechargeRecord implements Serializable {
	 
	private Integer id;// 主键
	//private Integer userId ;//  用户ID
	private User user; // 外键 用户ID
	private Double money; //消费金额
	//private Integer payType; //外键  用哪一家第三方平台支付  
	private PaymentType payType;
	private String paymentTypeName;
	private Date time = new java.util.Date();; //消费时间
	private Integer result ;// 消费结果  1:成功  -1:失败
	private String resultDesc;// 消费结果描述 
	private Integer ifDel=0;   //用户是否删除   0:未删除   1: 已删除
	
	public RechargeRecord(){
		
	}
	
	public RechargeRecord(User user,Double money,String paymentTypeName){
		this.user = user;
		this.money = money;
		this.paymentTypeName = paymentTypeName;
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


	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public PaymentType getPayType() {
		return payType;
	}

	public void setPayType(PaymentType payType) {
		this.payType = payType;
	}

	public String getPaymentTypeName() {
		return paymentTypeName;
	}

	public void setPaymentTypeName(String paymentTypeName) {
		this.paymentTypeName = paymentTypeName;
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
