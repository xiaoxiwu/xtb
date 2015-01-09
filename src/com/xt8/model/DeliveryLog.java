package com.xt8.model;

import java.io.Serializable;
import java.util.Date;

import com.xt8.util.Common;

import net.sf.json.JSONObject;

public class DeliveryLog implements Serializable {
 
	private Integer id; //主键
	private ExpressCorp expressCorp;
	private Order order;  //订单编号  外键
	private String detail;  
	private Date time=new Date();  //发送时间

	public DeliveryLog(){
		
	}
	
	public DeliveryLog(ExpressCorp expressCorp,Order order,String detail){
	
		this.expressCorp = expressCorp;
		this.order = order;
		this.detail = detail;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public ExpressCorp getExpressCorp() {
		return expressCorp;
	}

	public void setExpressCorp(ExpressCorp expressCorp) {
		this.expressCorp = expressCorp;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public JSONObject toJSON() {
		return Common.toJSON(this);
	}

	@Override
	public String toString() {
		return toJSON().toString();
	}

}
