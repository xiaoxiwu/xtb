package com.xt8.model;

import java.io.Serializable;
import java.util.Date;

import com.xt8.util.Common;

import net.sf.json.JSONObject;

public class Order implements Serializable {

	private Integer  id; //主键
	private String  orderNumber;   //订单编号;
	private Goods  goods;      //物品ID
	private User  receiver;       //接收者ID
	private Date   orderTime;         //订单产生时间
	private Integer  status=0;  //状态码        1 :成功  0:交易中  -1: 接收方取消  -2：发货方取消  -3：接收方未付邮费  -4：未发货   -5：其他原因  
	private String  statusDesc; //状态描述
	private String waybillNum;  //运单号码
	
	private String description;  //订单描述

	public Order(){
		
	}
	
	public Order(String orderNumber,Goods goods,User receiver){
		this.orderNumber = orderNumber;
		this.goods = goods;
		this.receiver = receiver;
	}
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public Goods getGoods() {
		return goods;
	}

	public void setGoods(Goods goods) {
		this.goods = goods;
	}

	public User getReceiver() {
		return receiver;
	}

	public void setReceiver(User receiver) {
		this.receiver = receiver;
	}

	public Date getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(Date orderTime) {
		this.orderTime = orderTime;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getWaybillNum() {
		return waybillNum;
	}

	public void setWaybillNum(String waybillNum) {
		this.waybillNum = waybillNum;
	}

	public String getStatusDesc() {
		return statusDesc;
	}

	public void setStatusDesc(String statusDesc) {
		this.statusDesc = statusDesc;
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

	
	public JSONObject toSimpleJSON(){
		String[] attrs = {"id","orderNumber","orderTime","status","statusDesc","waybillNum","description"};
		
		JSONObject json = Common.toJSON(this,attrs);
		json.put("goods", goods == null ? null : goods.toSimpleJSON());
		json.put("receiver", receiver == null ? null : receiver.toSimpleJSON());
		return json;
	}
	
	
	@Override
	public String toString() {
		return toJSON().toString();
	}

}
