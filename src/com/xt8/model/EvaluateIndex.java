package com.xt8.model;

import java.io.Serializable;
import java.util.Date;

import com.xt8.util.Common;

import net.sf.json.JSONObject;

public class EvaluateIndex implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;
	private String eName;

	public EvaluateIndex(){
		
	}
	
	public EvaluateIndex(String eName){
		
		this.eName = eName;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String geteName() {
		return eName;
	}

	public void seteName(String eName) {
		this.eName = eName;
	}

	public JSONObject toJSON() {
		return Common.toJSON(this);
	}
	
	public JSONObject toSimpleJSON(){
		String[] attrs = {"id","eName"};
		
		JSONObject json = Common.toJSON(this,attrs);
		return json;
	}


	@Override
	public String toString() {
		return toJSON().toString();
	}

}
