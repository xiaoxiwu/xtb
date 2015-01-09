package com.xt8.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import net.sf.json.JSONObject;

import com.xt8.util.Common;

public class Deliver implements Serializable {
  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/*
   * Id
ExpSiteId
Name
IdCard
IdCardImgId
Photo
Tel
DeliverArea
RegisterTime
Remark
   */
	private Integer Id;
	private String Name;
	private String IdCard;
	private String Tel;
	private String DeliverArea;
	private String Remark;	
	private Date RegisterTime;
	
	private Image IdCardImage;
	public Set<ExpressOrder> getExpOrderList() {
		return ExpOrderList;
	}
	public void setExpOrderList(Set<ExpressOrder> expOrderList) {
		ExpOrderList = expOrderList;
	}

	private Image Photo;
	private ExpressSite ExpSite;
	private Set<ExpressOrder> ExpOrderList;
 	public Integer getId() {
		return Id;
	}
	public void setId(Integer id) {
		Id = id;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getIdCard() {
		return IdCard;
	}
	public void setIdCard(String idCard) {
		IdCard = idCard;
	}
	public String getTel() {
		return Tel;
	}
	public void setTel(String tel) {
		Tel = tel;
	}
	public String getDeliverArea() {
		return DeliverArea;
	}
	public void setDeliverArea(String deliverArea) {
		DeliverArea = deliverArea;
	}
	public String getRemark() {
		return Remark;
	}
	public void setRemark(String remark) {
		Remark = remark;
	}
	public Date getRegisterTime() {
		return RegisterTime;
	}
	public void setRegisterTime(Date registerTime) {
		RegisterTime = registerTime;
	}
	public Image getIdCardImage() {
		return IdCardImage;
	}
	public void setIdCardImage(Image idCardImage) {
		IdCardImage = idCardImage;
	}
	public Image getPhoto() {
		return Photo;
	}
	public void setPhoto(Image photo) {
		Photo = photo;
	}
	public ExpressSite getExpSite() {
		return ExpSite;
	}
	public void setExpSite(ExpressSite expSite) {
		this.ExpSite = expSite;
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
