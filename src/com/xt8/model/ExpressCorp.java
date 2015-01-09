package com.xt8.model;

import java.io.Serializable;
import java.util.Date;

import com.xt8.util.Common;

import net.sf.json.JSONObject;

public class ExpressCorp implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;   // 主键
	private Integer ecId;
	private String ecName;// 名字  
	private String contact; 
	private String contactPerson;
	private Date time;// 添加时间
	private String description;

	public ExpressCorp(){
		
	}
	
	public ExpressCorp(Integer ecId,String ecName){
		this.ecId = ecId;
		this.ecName = ecName;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getEcId() {
		return ecId;
	}

	public void setEcId(Integer ecId) {
		this.ecId = ecId;
	}

	public String getEcName() {
		return ecName;
	}

	public void setEcName(String ecName) {
		this.ecName = ecName;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getContactPerson() {
		return contactPerson;
	}

	public void setContactPerson(String contactPerson) {
		this.contactPerson = contactPerson;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
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
		String[] attrs = {"id","ecName","contact","contactPerson"};
		
		JSONObject json = Common.toJSON(this,attrs);
		return json;
	}

	@Override
	public String toString() {
		return toJSON().toString();
	}

}
