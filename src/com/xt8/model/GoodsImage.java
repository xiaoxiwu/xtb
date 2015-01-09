package com.xt8.model;

import java.io.Serializable;
import java.util.Date;

import com.xt8.util.Common;

import net.sf.json.JSONObject;

public class GoodsImage implements Serializable {
	private Integer id;  //主键
	private Goods goods;  //物品ID   
	private Image image;

	public GoodsImage(){
		
	}
	
	public GoodsImage(Goods goods,Image image){
		this.goods = goods;
		this.image = image;
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

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	public JSONObject toJSON() {
		return Common.toJSON(this);
	}

	public JSONObject toSimpleJSON(){
		String[] attrs = {"id"};
		
		JSONObject json = Common.toJSON(this,attrs);
		json.put("goods", goods == null ? null : goods.toSimpleJSON());
		json.put("image", image == null ? null : image.toSimpleJSON());
		return json;
	}
	
	@Override
	public String toString() {
		return toJSON().toString();
	}

}
