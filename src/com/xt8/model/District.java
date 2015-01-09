package com.xt8.model;

import java.io.Serializable;

import net.sf.json.JSONObject;

import com.xt8.util.Common;

public class District implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer distId ;
	private String distName;
	private City city;


	public Integer getDistId() {
		return distId;
	}

	public void setDistId(Integer distId) {
		this.distId = distId;
	}

	public String getDistName() {
		return distName;
	}

	public void setDistName(String distName) {
		this.distName = distName;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public JSONObject toJSON() {
		return Common.toJSON(this);
	}
	
	public JSONObject toSimpleJSON(){
		String[] attrs = {"distId","distName"};
		
		JSONObject json = Common.toJSON(this,attrs);
		json.put("city", city == null ? null : city.toSimpleJSON());
		return json;
	}

	@Override
	public String toString() {
		return toJSON().toString();
	}

}
