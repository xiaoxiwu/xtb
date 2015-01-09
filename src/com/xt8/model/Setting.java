package com.xt8.model;

import java.io.Serializable;
import java.util.Date;

import com.xt8.util.Common;

import net.sf.json.JSONObject;

public class Setting implements Serializable {
	private Integer sId; // 主键	
	private String sName; //设置名称
	private Integer ifDel=0; 

	public Setting(){
		
	}
	public Setting(String sName){
		this.sName = sName;
	}
	public Integer getsId() {
		return sId;
	}

	public void setsId(Integer sId) {
		this.sId = sId;
	}

	public String getsName() {
		return sName;
	}

	public void setsName(String sName) {
		this.sName = sName;
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
		String[] attrs ={"sId","sName"};
		JSONObject json = Common.toJSON(this,attrs);
		return json;
	}
	

	@Override
	public String toString() {
		return toJSON().toString();
	}

}
