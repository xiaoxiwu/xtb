package com.xt8.model;

import java.io.Serializable;
import java.util.Date;

import com.xt8.util.Common;

import net.sf.json.JSONObject;

public class GoodsUserLike implements Serializable {
	private Integer id;  //主键
	private Goods goods;  //物品ID   
	private User user;   //用户ID
	private Date time;   //喜欢时间

	public GoodsUserLike(){
		
	}
	
	public GoodsUserLike(Goods goods,User user){
		this.goods = goods;
		this.user = user;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Goods getGoods() {
		return goods;
	}

	public void setGoods(Goods goods) {
		this.goods = goods;
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

	public JSONObject toSimpleJSON(){
		String[] attrs = {"id"};
		
		JSONObject json = Common.toJSON(this,attrs);
		json.put("goods", goods == null ? null : goods.toSimpleJSON());
		json.put("user", user == null ? null : user.toSimpleJSON());
		return json;
	}
	
	@Override
	public String toString() {
		return toJSON().toString();
	}

}
