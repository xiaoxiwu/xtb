package com.xt8.model;

import java.io.Serializable;
import java.util.Date;

import com.xt8.util.Common;

import net.sf.json.JSONObject;

public class UserCommAddr implements Serializable {
 
	private Integer addrId;  //主键
	private User user;	//	用户ID
	private String contactPerson;	//联系人
	private Province province;		
	private City city;
	private District district;
	private String detailAddr;
	private String tel;          
	private String postcode;
	private Date addTime = new Date();
	private Integer ifDefault=0; // 是否默认地址
	private Integer useCount=0;  //  使用次数
	private Integer ifDel=0; //  是否删除

	public UserCommAddr(){
		
	}
	
	public UserCommAddr(User user,String contactPerson,Province province,City city,District district,String detailAddr,
			String tel){
		
		this.user = user;
		this.contactPerson = contactPerson;
		this.province = province;
		this.city = city;
		this.district = district;
		this.detailAddr = detailAddr;
		this.tel = tel;
	}
	
	public Integer getAddrId() {
		return addrId;
	}

	public void setAddrId(Integer addrId) {
		this.addrId = addrId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getContactPerson() {
		return contactPerson;
	}

	public void setContactPerson(String contactPerson) {
		this.contactPerson = contactPerson;
	}

	public Province getProvince() {
		return province;
	}

	public void setProvince(Province province) {
		this.province = province;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public District getDistrict() {
		return district;
	}

	public void setDistrict(District district) {
		this.district = district;
	}

	public String getDetailAddr() {
		return detailAddr;
	}

	public void setDetailAddr(String detailAddr) {
		this.detailAddr = detailAddr;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public Date getAddTime() {
		return addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	public Integer getIfDefault() {
		return ifDefault;
	}

	public void setIfDefault(Integer ifDefault) {
		this.ifDefault = ifDefault;
	}

	public Integer getUseCount() {
		return useCount;
	}

	public void setUseCount(Integer useCount) {
		this.useCount = useCount;
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


	public JSONObject toSimpleJSON(){
		
		String[] attrs ={"addrId","contactPerson","detailAddr","tel"};
		
		JSONObject json = Common.toJSON(this,attrs);
		
		json.put("user", user == null ? null : user.toSimpleJSON());
		json.put("province", province == null ? null : province.toSimpleJSON());
		json.put("city", city == null ? null : city.toSimpleJSON());
		json.put("district", district == null ? null : district.toSimpleJSON());
		
		return json;
	}
	
	
	@Override
	public String toString() {
		return toJSON().toString();
	}

}
