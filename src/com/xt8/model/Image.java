package com.xt8.model;

import java.util.Date;

import net.sf.json.JSONObject;

import com.xt8.util.Common;

public class Image {
	private Integer imageId; // 主键
	private User user; // 外键
	private String imageName; // 文件名
	private Integer imageSize; // 文件大小
	private Date upTime = new Date(); // 上传日期
	private String imageType; // 图片类型
	private Integer isCloud = 0;// 1：在云端 0：服务器端
	private String imagePath; // 原图片文件路径
	private String thumbailPath; // 缩略图地址
	private Integer propose = 0; // 用途 : 0:物品 1:动态 2:头像

	public Image() {
	}

	public Image(User user, String imageName, String imageType, String imagePath) {
		super();
		this.user = user;
		this.imageName = imageName;
		this.imageType = imageType;
		this.imagePath = imagePath;
	}
	
	public Image(User user,String imageName,String imagePath){
		this.user = user;
		this.imageName = imageName;
		this.imagePath = imagePath;
	}

	public Integer getImageId() {
		return imageId;
	}

	public void setImageId(Integer imageId) {
		this.imageId = imageId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public Integer getImageSize() {
		return imageSize;
	}

	public void setImageSize(Integer imageSize) {
		this.imageSize = imageSize;
	}

	public Date getUpTime() {
		return upTime;
	}

	public void setUpTime(Date upTime) {
		this.upTime = upTime;
	}

	public String getImageType() {
		return imageType;
	}

	public void setImageType(String imageType) {
		this.imageType = imageType;
	}

	public Integer getIsCloud() {
		return isCloud;
	}

	public void setIsCloud(Integer isCloud) {
		this.isCloud = isCloud;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public String getThumbailPath() {
		return thumbailPath;
	}

	public void setThumbailPath(String thumbailPath) {
		this.thumbailPath = thumbailPath;
	}

	public Integer getPropose() {
		return propose;
	}

	public void setPropose(Integer propose) {
		this.propose = propose;
	}
	
	public JSONObject toSimpleJSON(){
		String[] attrs = {"imageId","isCloud","imagePath","thumbailPath"};
		
		JSONObject json = Common.toJSON(this,attrs);
		json.put("user", user == null ? null : user.toSimpleJSON());
		return json;
	}

}
