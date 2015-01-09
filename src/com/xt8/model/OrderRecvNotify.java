package com.xt8.model;

import java.io.Serializable;
import java.util.Date;

import com.xt8.util.Common;

import net.sf.json.JSONObject;

public class OrderRecvNotify implements Serializable {
	private Integer  id;  //主键
	private Order  order;  //外键
	private User receiver;
	private Double postage=0.0;  //邮费
	private Date payTime;  //付费时间
	private Integer ifPay=0; //是否付费
	private Province recvProvince;
	private City recvCity;          //收获地址
	private District recvDistrict;
	private String recvDetailAddr;
	private String recvPerson;
	private String recvContact;
	private String postCode;
	private Integer  isLookup=0;    //浏览标记
	private Integer ifDel=0;
	
	public OrderRecvNotify(){
		
	}
	
	public OrderRecvNotify(Order order){
		this.order = order;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public User getReceiver() {
		return receiver;
	}

	public void setReceiver(User receiver) {
		this.receiver = receiver;
	}

	public Double getPostage() {
		return postage;
	}

	public void setPostage(Double postage) {
		this.postage = postage;
	}

	public Date getPayTime() {
		return payTime;
	}

	public void setPayTime(Date payTime) {
		this.payTime = payTime;
	}

	public Integer getIfPay() {
		return ifPay;
	}

	public void setIfPay(Integer ifPay) {
		this.ifPay = ifPay;
	}

	public Province getRecvProvince() {
		return recvProvince;
	}

	public void setRecvProvince(Province recvProvince) {
		this.recvProvince = recvProvince;
	}

	public City getRecvCity() {
		return recvCity;
	}

	public void setRecvCity(City recvCity) {
		this.recvCity = recvCity;
	}

	public District getRecvDistrict() {
		return recvDistrict;
	}

	public void setRecvDistrict(District recvDistrict) {
		this.recvDistrict = recvDistrict;
	}

	public String getRecvDetailAddr() {
		return recvDetailAddr;
	}

	public void setRecvDetailAddr(String recvDetailAddr) {
		this.recvDetailAddr = recvDetailAddr;
	}

	public String getRecvPerson() {
		return recvPerson;
	}

	public void setRecvPerson(String recvPerson) {
		this.recvPerson = recvPerson;
	}

	public String getRecvContact() {
		return recvContact;
	}

	public void setRecvContact(String recvContact) {
		this.recvContact = recvContact;
	}

	public String getPostCode() {
		return postCode;
	}

	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

	public Integer getIsLookup() {
		return isLookup;
	}

	public void setIsLookup(Integer isLookup) {
		this.isLookup = isLookup;
	}

	public Integer getIfDel() {
		return ifDel;
	}

	public void setIfDel(Integer ifDel) {
		this.ifDel = ifDel;
	}

	public JSONObject toJSON() {
		
	//	String[] attr = {"id","order","receiver","payTime","ifPay"};
		//return Common.toJSON(this,attr);
		return null;
	}

	public JSONObject toSimpleJSON(){
		String[] attrs ={"id","postage","payTime","ifPay"};
		
		JSONObject json = Common.toJSON(this,attrs);
		json.put("order", order == null ? null : order.toSimpleJSON());
		json.put("receiver", receiver == null ? null : receiver.toSimpleJSON());
		return json;
	}
	
	@Override
	public String toString() {
		return toJSON().toString();
	}

}
