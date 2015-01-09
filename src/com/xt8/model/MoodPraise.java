package com.xt8.model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.xt8.util.Common;

import net.sf.json.JSONObject;

public class MoodPraise implements Serializable {
	Integer id; // 主键
	Mood mood; // 说说ID
	User user; // 赞用户ID
	Date time; // 评论时间

	public MoodPraise() {
	}

	public MoodPraise(Mood mood, User user, Date time) {
		super();
		this.mood = mood;
		this.user = user;
		this.time = time;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Mood getMood() {
		return mood;
	}

	public void setMood(Mood mood) {
		this.mood = mood;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public JSONObject toJSON() {
		return Common.toJSON(this);
	}

	public JSONObject toSimpleJSON() {
		JSONObject json = new JSONObject();
		json.put("id", getId());
		json.put("mood", getMood().toSimpleJson());
		json.put("user", getUser().toSimpleJSON());
		SimpleDateFormat dfmt = new SimpleDateFormat("yyyy-MM-dd");
		json.put("time", dfmt.format(getTime()));
		return json;
	}

	@Override
	public String toString() {
		return toJSON().toString();
	}

}
