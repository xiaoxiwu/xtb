package com.xt8.model;

import java.io.Serializable;
import java.util.Date;

import com.xt8.util.Common;

import net.sf.json.JSONObject;

public class FAQ implements Serializable {
  private Integer id;   //主键
  private String question;  //问题
  private String answer;		//答案
  private Date time;			//写入时间
  private Integer admin;		//写入管理员
  private Integer ifDel;		//是否删除

	public Integer getId() {
	return id;
}

public void setId(Integer id) {
	this.id = id;
}

public String getQuestion() {
	return question;
}

public void setQuestion(String question) {
	this.question = question;
}

public String getAnswer() {
	return answer;
}

public void setAnswer(String answer) {
	this.answer = answer;
}

public Date getTime() {
	return time;
}

public void setTime(Date time) {
	this.time = time;
}

public Integer getAdmin() {
	return admin;
}

public void setAdmin(Integer admin) {
	this.admin = admin;
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

	@Override
	public String toString() {
		return toJSON().toString();
	}

}
