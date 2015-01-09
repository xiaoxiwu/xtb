package com.xt8.model;

import java.io.Serializable;

import com.xt8.util.Common;

import net.sf.json.JSONObject;

public class MoodImage implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id; // 主键
	private Mood mood; // 物品ID
	private Image image;

	public MoodImage() {
		super();
	}

	public MoodImage(Mood mood, Image image) {
		super();
		this.mood = mood;
		this.image = image;
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

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	public JSONObject toJSON() {
		return Common.toJSON(this);
	}

	@Override
	public String toString() {
		return toJSON().toString();
	}

}
