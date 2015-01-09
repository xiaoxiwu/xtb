package com.xt8.model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.xt8.util.Common;

import net.sf.json.JSONObject;

public class MoodReply implements Serializable {
	private Integer id; // 主键
	private Mood mood; // 说说ID
	private User user; // 评论用户ID
	private MoodReply parent; // 父ID
	private Date time; // 评论时间
	private String content; // 评论内容
	private Integer ifDel = 0;

	public MoodReply() {
	}

	public MoodReply(Mood mood, User user, MoodReply parent, Date time,
			String content, Integer ifDel) {
		super();
		this.mood = mood;
		this.user = user;
		this.parent = parent;
		this.time = time;
		this.content = content;
		this.ifDel = ifDel;
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

	public MoodReply getParent() {
		return parent;
	}

	public void setParent(MoodReply parent) {
		this.parent = parent;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
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

	public JSONObject toSimpleJSON() {
		JSONObject json = new JSONObject();
		json.put("id", getId());
		json.put("mood", getMood().toSimpleJson());
		json.put("user", getUser().toSimpleJSON());
		SimpleDateFormat dfmt = new SimpleDateFormat("yyyy-MM-dd");
		json.put("time", dfmt.format(getTime()));
		json.put("content", getContent());
		json.put("ifDel", getIfDel());
		return json;
	}

	@Override
	public String toString() {
		return toJSON().toString();
	}

}
