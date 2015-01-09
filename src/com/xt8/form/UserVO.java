package com.xt8.form;

import java.io.Serializable;

import net.sf.json.JSONObject;

import com.xt8.model.Image;
import com.xt8.model.User;

public class UserVO implements Serializable {

	private static final long serialVersionUID = 1L;
	private String nickName;
	private String userAvatarPath;
	private String phone;
	private String SimpleWord;
	private Integer userId;
	private String heartbeatNumber;
	private String avatar;
	private String personalizedSignature;
	private Integer level;
	private String registerDate;
	private String password;
	private Integer versionInfo;
	private String deviceInfo;
	private Double longtitude;
	private Double latitude;

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getUserAvatarPath() {
		return userAvatarPath;
	}

	public void setUserAvatarPath(String userAvatarPath) {
		this.userAvatarPath = userAvatarPath;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getSimpleWord() {
		return SimpleWord;
	}

	public void setSimpleWord(String simpleWord) {
		SimpleWord = simpleWord;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getHeartbeatNumber() {
		return heartbeatNumber;
	}

	public void setHeartbeatNumber(String heartbeatNumber) {
		this.heartbeatNumber = heartbeatNumber;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getPersonalizedSignature() {
		return personalizedSignature;
	}

	public void setPersonalizedSignature(String personalizedSignature) {
		this.personalizedSignature = personalizedSignature;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public String getRegisterDate() {
		return registerDate;
	}

	public void setRegisterDate(String registerDate) {
		this.registerDate = registerDate;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getVersionInfo() {
		return versionInfo;
	}

	public void setVersionInfo(Integer versionInfo) {
		this.versionInfo = versionInfo;
	}

	public String getDeviceInfo() {
		return deviceInfo;
	}

	public void setDeviceInfo(String deviceInfo) {
		this.deviceInfo = deviceInfo;
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

	public User getUser4Register() {
		User u = new User();
		u.setNickName(nickName);
		u.setPhoneNumber(phone);
		u.setPlainPassword(password);
		u.setSimpleCode(SimpleWord);
		return u;
	}

	public static JSONObject genResponseJson4Register(final User u,
			final Image img) {
		JSONObject json = new JSONObject();
		json.put("userId", u.getUserId());
		json.put("nickName", u.getNickName());
		json.put("heartbeatNumber", u.getHeartbeatNumber());
		json.put("avatar", img.getImagePath());
		json.put("personalizedSignature", u.getPersonalizedSignature());
		json.put("level", u.getLevel());
		json.put("registerDate", u.getRegisterDate());
		return json;
	}

	public User getUser4Login() {
		User u = new User();
		u.setPhoneNumber(getPhone());
		u.setPlainPassword(getPassword());
		// u.set 版本
		// u.set 经度
		// u.set 纬度
		return u;
	}

	public static JSONObject genResponseJson4Login(final User u, final Image img) {
		return genResponseJson4Register(u, img);
	}

}
