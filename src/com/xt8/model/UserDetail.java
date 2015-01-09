package com.xt8.model;

import java.io.Serializable;
import java.util.Date;

import com.xt8.util.Common;

import net.sf.json.JSONObject;

public class UserDetail implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id; // 主键
	private User user; // 用户ID
	private Date birthDay; // 生日
	private String identityCard; // 身份证
	private String briefIntroduction; // 简短描述
	private Date updateTime;
	// private Integer occupation; //职业:学生，经理，职员......等
	// private Integer industry; //所处行业

	// private Integer educationBackgroud; //学历
	// private String primarySchool; //就读小学
	// private String midSchool; //中学
	// private String college; //大学

	private Double longtitude; // 经度
	private Double latitude; // 纬度
	private Integer nation; // 所在国家
	private Province province; // 所在省
	private City city; // 所在市
	private District district; // 所在县
	private String detailAddr; // 所在详细地址

	private String qq;
	private String weChat;
	private String email;
	private String weibo;

	public UserDetail() {
	}

	public UserDetail(User user, Date updateTime, Double longtitude,
			Double latitude, Integer nation, Province province, City city) {
		super();
		this.user = user;
		this.updateTime = updateTime;
		this.longtitude = longtitude;
		this.latitude = latitude;
		this.nation = nation;
		this.province = province;
		this.city = city;
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

	public Date getBirthDay() {
		return birthDay;
	}

	public void setBirthDay(Date birthDay) {
		this.birthDay = birthDay;
	}

	public String getIdentityCard() {
		return identityCard;
	}

	public void setIdentityCard(String identityCard) {
		this.identityCard = identityCard;
	}

	public String getBriefIntroduction() {
		return briefIntroduction;
	}

	public void setBriefIntroduction(String briefIntroduction) {
		this.briefIntroduction = briefIntroduction;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Double getLongtitude() {
		return longtitude;
	}

	public void setLongtitude(Double longtitude) {
		this.longtitude = longtitude;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Integer getNation() {
		return nation;
	}

	public void setNation(Integer nation) {
		this.nation = nation;
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

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public String getWeChat() {
		return weChat;
	}

	public void setWeChat(String weChat) {
		this.weChat = weChat;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getWeibo() {
		return weibo;
	}

	public void setWeibo(String weibo) {
		this.weibo = weibo;
	}

	public JSONObject toJSON() {
		return Common.toJSON(this);
	}

	@Override
	public String toString() {
		return toJSON().toString();
	}

	public JSONObject toSimpleJson() {
		String[] attrs = { "birthDay", "briefIntroduction" };
		JSONObject json = Common.toJSON(this, attrs);
		json.put("province", (null != province) ? province.toSimpleJSON()
				: null);
		json.put("city", (null != city) ? city.toSimpleJSON() : null);
		return json;
	}

}
