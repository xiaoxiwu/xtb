package com.xt8.model;

import java.io.Serializable;
import java.util.Date;

import com.xt8.util.Common;

import net.sf.json.JSONObject;

public class UserEvaluateIndex implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id; // 主键
	private User user; // 用户ID
	private EvaluateIndex indexSort; // 指数类别ID
	private Double score = 0.0;// 得分
	private Date updatedTime;
	private Integer rank = 0; // 排名

	public UserEvaluateIndex() {

	}

	public UserEvaluateIndex(User user, EvaluateIndex indexSort) {

		this.user = user;
		this.indexSort = indexSort;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public EvaluateIndex getIndexSort() {
		return indexSort;
	}

	public void setIndexSort(EvaluateIndex indexSort) {
		this.indexSort = indexSort;
	}

	public Double getScore() {
		return score;
	}

	public void setScore(Double score) {
		this.score = score;
	}

	public Date getUpdatedTime() {
		return updatedTime;
	}

	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}

	public Integer getRank() {
		return rank;
	}

	public void setRank(Integer rank) {
		this.rank = rank;
	}

	public JSONObject toJSON() {
		return Common.toJSON(this);
	}

	@Override
	public String toString() {
		return toJSON().toString();
	}

}
