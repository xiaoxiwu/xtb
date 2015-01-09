package com.xt8.model;

import java.io.Serializable;

import net.sf.json.JSONObject;

import com.xt8.util.Common;

public class Province implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer provinceId ;
	private String provinceName;


	public Province(){
		
	}
	
	public Integer getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(Integer provinceId) {
		this.provinceId = provinceId;
	}

	public String getProvinceName() {
		return provinceName;
	}

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}


	public JSONObject toJSON() {
		return Common.toJSON(this);
	}
	
	public JSONObject toSimpleJSON(){
		String[] attrs = {"provinceId","provinceName"};
		
		JSONObject json = Common.toJSON(this,attrs);
		return json;
	}

	@Override
	public String toString() {
		return toJSON().toString();
	}

}
