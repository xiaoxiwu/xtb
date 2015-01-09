package com.xt8.model;

import java.io.Serializable;
import java.util.Date;

import com.xt8.util.Common;

import net.sf.json.JSONObject;

public class OrderSendNotify implements Serializable {
	private Integer  id;  //主键
	private Order  order;  //外键
	private User sender;
	private Date deliverTime;  //发货时间
	private Integer ifDeliver=0; //是否发货
	private Province Province;
	private City City;          //取货地址
	private District District;
	private String DetailAddr;
	private String recvPerson;
	private String recvContact;
	private String postCode;
	private ExpressCorp expressCorp;
	private Integer  isLookup=0;    //浏览标记
	private Integer ifDel=0;

	public OrderSendNotify(){
		
	}
	
	public OrderSendNotify(Order order,User sender){
		this.order = order;
		this.sender = sender;
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

	public User getSender() {
		return sender;
	}

	public void setSender(User sender) {
		this.sender = sender;
	}

	public Date getDeliverTime() {
		return deliverTime;
	}

	public void setDeliverTime(Date deliverTime) {
		this.deliverTime = deliverTime;
	}

	public Integer getIfDeliver() {
		return ifDeliver;
	}

	public void setIfDeliver(Integer ifDeliver) {
		this.ifDeliver = ifDeliver;
	}

	public Province getProvince() {
		return Province;
	}

	public void setProvince(Province province) {
		Province = province;
	}

	public City getCity() {
		return City;
	}

	public void setCity(City city) {
		City = city;
	}

	public District getDistrict() {
		return District;
	}

	public void setDistrict(District district) {
		District = district;
	}

	public String getDetailAddr() {
		return DetailAddr;
	}

	public void setDetailAddr(String detailAddr) {
		DetailAddr = detailAddr;
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

	public ExpressCorp getExpressCorp() {
		return expressCorp;
	}

	public void setExpressCorp(ExpressCorp expressCorp) {
		this.expressCorp = expressCorp;
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
		
		String[] attr = {"id","order","sender","deliverTime","ifDeliver"};
		return Common.toJSON(this,attr);
	}

	public JSONObject toSimpleJSON(){
		String[] attrs ={"id","deliverTime","ifDeliver"};
		
		JSONObject json = Common.toJSON(this,attrs);
		json.put("order", order == null ? null : order.toSimpleJSON());
		json.put("sender", sender == null ? null : sender.toSimpleJSON());
		return json;
	}
	
	@Override
	public String toString() {
		return toJSON().toString();
	}

}
