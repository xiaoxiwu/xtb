package com.xt8.model;

import java.io.Serializable;
import java.util.Set;

import net.sf.json.JSONObject;

import com.xt8.util.Common;

public class Express implements Serializable {
	private int Id;
	private String Name;
	private String ContactPerson;
	private String Tel;
	private String Remark;
	public String getRemark() {
		return Remark;
	}
	public void setRemark(String remark) {
		Remark = remark;
	}

	private Set<ExpressSite> ExpSiteList;
 	public Set<ExpressSite> getExpSiteList() {
		return ExpSiteList;
	}
	public void setExpSiteList(Set<ExpressSite> expSiteList) {
		ExpSiteList = expSiteList;
	}
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getContactPerson() {
		return ContactPerson;
	}
	public void setContactPerson(String contactPerson) {
		ContactPerson = contactPerson;
	}
	public String getTel() {
		return Tel;
	}
	public void setTel(String tel) {
		Tel = tel;
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
