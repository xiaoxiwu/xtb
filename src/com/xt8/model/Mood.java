package com.xt8.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import com.xt8.util.Common;

import net.sf.json.JSONObject;

public class Mood implements Serializable {
	private Integer moodId; // 主键
	private User user; // 用户
	private String content; // 内容
	private Date time = new Date(); // 发布时间
	private String device; // 发布设备
	private String location; // 发布区域

	private Integer browseNum = 0; // 浏览量
	private Integer ifLookup; // 是否浏览标记 0:未浏览 1:已浏览
	private Integer ifDel = 0; // 是否删除标记

	private Integer extraFlag;
	private Integer extraProperty1;
	private Integer extraValue1;
	private Integer extraProperty2;
	private Integer extraValue2;

	public Mood() {
	}

	public Mood(User user, String content, String device, String location) {
		super();
		this.user = user;
		this.content = content;
		this.device = device;
		this.location = location;
	}

	public Mood(User user, String content, Date time) {
		super();
		this.user = user;
		this.content = content;
		this.time = time;
	}

	public Integer getMoodId() {
		return moodId;
	}

	public void setMoodId(Integer moodId) {
		this.moodId = moodId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public String getDevice() {
		return device;
	}

	public void setDevice(String device) {
		this.device = device;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Integer getBrowseNum() {
		return browseNum;
	}

	public void setBrowseNum(Integer browseNum) {
		this.browseNum = browseNum;
	}

	public Integer getIfLookup() {
		return ifLookup;
	}

	public void setIfLookup(Integer ifLookup) {
		this.ifLookup = ifLookup;
	}

	public Integer getIfDel() {
		return ifDel;
	}

	public void setIfDel(Integer ifDel) {
		this.ifDel = ifDel;
	}

	public Integer getExtraFlag() {
		return extraFlag;
	}

	public void setExtraFlag(Integer extraFlag) {
		this.extraFlag = extraFlag;
	}

	public Integer getExtraProperty1() {
		return extraProperty1;
	}

	public void setExtraProperty1(Integer extraProperty1) {
		this.extraProperty1 = extraProperty1;
	}

	public Integer getExtraValue1() {
		return extraValue1;
	}

	public void setExtraValue1(Integer extraValue1) {
		this.extraValue1 = extraValue1;
	}

	public Integer getExtraProperty2() {
		return extraProperty2;
	}

	public void setExtraProperty2(Integer extraProperty2) {
		this.extraProperty2 = extraProperty2;
	}

	public Integer getExtraValue2() {
		return extraValue2;
	}

	public void setExtraValue2(Integer extraValue2) {
		this.extraValue2 = extraValue2;
	}

	public JSONObject toJSON() {
		return Common.toJSON(this);
	}

	@Override
	public String toString() {
		return toJSON().toString();
	}

	public Object toSimpleJson() {
		String[] attrs = { "moodId", "content", "time", "device", "location", "extraFlag" ,"extraProperty1","extraValue1"};
		return Common.toJSON(this, attrs);
	}
}
