package com.xt8.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import net.sf.json.JSONObject;

import com.xt8.util.Common;

public class ExpressSite implements Serializable {
		/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/*
		 * Id
CompanyName
SiteName
SiteAddress
SiteTel
SiteArea
PassportImage
SiteHead
HeadTel
RegisterTime
Remark
		 */
	private Integer Id;
	private int ExpressId;
	private String SiteName;
	private String SiteAddress;
	private Image PassportImage;	
	private String SiteTel;
	private String SiteCoverArea;
	private String SiteHead;
	private String HeadTel;
	private Date RegisterTime;
	private String Remark;
	private Set<Deliver> DeliverList;
	private Set<ExpressOrder> ExpOrderList;
	
	public Integer getId() {
		return Id;
	}
	public void setId(Integer id) {
		Id = id;
	}
		public String getSiteName() {
		return SiteName;
	}
	public void setSiteName(String siteName) {
		SiteName = siteName;
	}
	public String getSiteAddress() {
		return SiteAddress;
	}
	public void setSiteAddress(String siteAddress) {
		SiteAddress = siteAddress;
	}
	public String getSiteTel() {
		return SiteTel;
	}
	public void setSiteTel(String siteTel) {
		SiteTel = siteTel;
	}
	public String getSiteCoverArea() {
		return SiteCoverArea;
	}
	public void setSiteCoverArea(String siteCoverArea) {
		SiteCoverArea = siteCoverArea;
	}
		public String getSiteHead() {
		return SiteHead;
	}
	public void setSiteHead(String siteHead) {
		SiteHead = siteHead;
	}
	public String getHeadTel() {
		return HeadTel;
	}
	public void setHeadTel(String headTel) {
		HeadTel = headTel;
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
	public Image getPassportImage() {
		return PassportImage;
	}
	public void setPassportImage(Image passportImage) {
		PassportImage = passportImage;
	}
	public Set<Deliver> getDeliverList() {
		return DeliverList;
	}
	public void setDeliverList(Set<Deliver> deliverList) {
		DeliverList = deliverList;
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
	public void setExpOrderList(Set<ExpressOrder> expOrderList) {
		ExpOrderList = expOrderList;
	}
	public Set<ExpressOrder> getExpOrderList() {
		return ExpOrderList;
	}
	public void setExpressId(int expressId) {
		ExpressId = expressId;
	}
	public int getExpressId() {
		return ExpressId;
	}
}
