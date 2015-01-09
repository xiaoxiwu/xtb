package com.xt8.model;

import java.io.Serializable;
import java.util.Date;

import net.sf.json.JSONObject;

import com.xt8.util.Common;

public class ExpressOrder implements Serializable {
  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/*
   * Id
ExpSiteId
DeliverId
OrderDeliverId
OrderTime
ProcessingTime
Status
   */
	private Integer Id;	
	private Order Order;
	private ExpressSite ExpSite;
	private Deliver Deliver;
	private Integer WayBillNum;
	private Date NotifyTime;
	private Date ProcessingTime;
	private Integer Status;
	public Integer getWayBillNum() {
		return WayBillNum;
	}
	public void setWayBillNum(Integer wayBillNum) {
		WayBillNum = wayBillNum;
	}
	public Integer getId() {
		return Id;
	}
	public void setId(Integer id) {
		Id = id;
	}
	public Order getOrder() {
		return Order;
	}
	public void setOrder(Order order) {
		Order = order;
	}
	public Date getNotifyTime() {
		return NotifyTime;
	}
	public void setNotifyTime(Date notifyTime) {
		NotifyTime = notifyTime;
	}
	public Date getProcessingTime() {
		return ProcessingTime;
	}
	public void setProcessingTime(Date processingTime) {
		ProcessingTime = processingTime;
	}
	public Integer getStatus() {
		return Status;
	}
	/**
	 * 0:未处理，1:已发货，2:发货异常
	 * @param status
	 */
	public void setStatus(Integer status) {
		Status = status;
	}
	public ExpressSite getExpSite() {
		return ExpSite;
	}
	public void setExpSite(ExpressSite expSite) {
		ExpSite = expSite;
	}
	public Deliver getDeliver() {
		return Deliver;
	}
	public void setDeliver(Deliver deliver) {
		Deliver = deliver;
	}
	public JSONObject toJSON() {
		return Common.toJSON(this);
	}
	
//	public JSONObject toSimpleJSON(){
//		String[] attrs = {"cityId","cityName"};
//		
//		JSONObject json = Common.toJSON(this,attrs);
//		json.put("province", province == null ? null : province.toSimpleJSON());
//		return json;
//	}

	@Override
	public String toString() {
		return toJSON().toString();
	}
}
