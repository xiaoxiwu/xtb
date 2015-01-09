package com.xt8.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.xt8.util.Common;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class Goods implements Serializable {

	private Integer goodsId; // 主键
	private String name; // 物品名
	private Category category; // 物品所属分类
	private Province province; // 物品所在地省份编号
	private City city; // 物品所在城市编号
	private District district; // 物品所在区县编号
	private String detailAddr; // 物品所在详细地址
	private String tag; // 物品标签
	private Integer weight; // 物品重量 0:小于1kg 1:大于1kg
	private Double weightValue; // 实际重量值
	private Double sizeValue; // 物品大小
	private String description; // 物品其他描述

	private User owner; // 物品拥有者ID
	private Integer postageType; // 物品的邮寄类型 0:自付邮费, 1:对方付邮费
	private Date expireTime; // ///物品终止日期
	private Integer browseCount = 0;
	private Integer ifUndercarriage = 0;// 是否下架
	private Integer ifDel = 0; // 物品是否删除

	public Goods() {

	}

	public Goods(String name, Category category, Province province, City city,
			Integer weight, User owner, Integer postageType) {
		this.name = name;
		this.category = category;
		this.province = province;
		this.city = city;
		this.weight = weight;
		this.owner = owner;
		this.postageType = postageType;

	}

	public Integer getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(Integer goodsId) {
		this.goodsId = goodsId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Province getProvince() {
		return province;
	}

	public void setProvince(Province province) {
		this.province = province;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public District getDistrict() {
		return district;
	}

	public void setDistrict(District district) {
		this.district = district;
	}

	public String getDetailAddr() {
		return detailAddr;
	}

	public void setDetailAddr(String detailAddr) {
		this.detailAddr = detailAddr;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public Integer getWeight() {
		return weight;
	}

	public void setWeight(Integer weight) {
		this.weight = weight;
	}

	public Double getWeightValue() {
		return weightValue;
	}

	public void setWeightValue(Double weightValue) {
		this.weightValue = weightValue;
	}

	public Double getSizeValue() {
		return sizeValue;
	}

	public void setSizeValue(Double sizeValue) {
		this.sizeValue = sizeValue;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

	public Integer getPostageType() {
		return postageType;
	}

	public void setPostageType(Integer postageType) {
		this.postageType = postageType;
	}

	public Date getExpireTime() {
		return expireTime;
	}

	public void setExpireTime(Date expireTime) {
		this.expireTime = expireTime;
	}

	public Integer getBrowseCount() {
		return browseCount;
	}

	public void setBrowseCount(Integer browseCount) {
		this.browseCount = browseCount;
	}

	public Integer getIfUndercarriage() {
		return ifUndercarriage;
	}

	public void setIfUndercarriage(Integer ifUndercarriage) {
		this.ifUndercarriage = ifUndercarriage;
	}

	public Integer getIfDel() {
		return ifDel;
	}

	public void setIfDel(Integer ifDel) {
		this.ifDel = ifDel;
	}

	public JSONObject toJSON() {
		
		String[] attrs = {"goodsId","name","tag","weight","weightValue","description",
				"postageType","expireTime","ifUndercarriage"};
		return Common.toJSON(this,attrs);
	}
	
	public JSONObject toSimpleJSON(){
		String[] attrs = {"goodsId","name","tag","weight","weightValue","description",
				"postageType","expireTime","ifUndercarriage"};
		
		JSONObject json = Common.toJSON(this,attrs);
		
		json.put("province", province == null ? null : province.toSimpleJSON());
		json.put("city", city == null ? null : city.toSimpleJSON());
	//	json.put("district", district == null ? null : district.toSimpleJSON());
		json.put("category", category == null ? null : category.toSimpleJSON());
		json.put("owner", owner == null ? null : owner.toSimpleJSON());
		return json;
	}

	/*public JSONObject toSimpleJSON() {
		String[] attrs = { "goodsId", "name", "tag", "weight", "weightValue",
				"description" };
		JSONObject json = Common.toJSON(this, attrs);
		json.put("category", this.getCategory().toSimpleJSON());
		List<Goods> list = null;
		JSONArray array = new JSONArray();
		for (Goods g : list) {
			array.add(g.toSimpleJSON());
		}
		return json;
	}*/

	@Override
	public String toString() {
		return toJSON().toString();
	}

}
