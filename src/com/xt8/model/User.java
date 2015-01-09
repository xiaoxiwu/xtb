package com.xt8.model;

import static com.xt8.util.Constants.*;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import net.sf.json.JSONObject;

import com.xt8.util.Common;

public class User implements Serializable {

	private Integer userId; // 自动生成 主键
	private String phoneNumber; // 手机号
	private String heartbeatNumber; // 生成唯一标识符，用户可修改一次(系统保证唯一)
	private String plainPassword; // 明文密码
	private String encryptedPassword; // 加密密码
	private Integer gender;// 性别
	private String simpleCode; // 简码
	private String nickName; // 昵称
	private String personalizedSignature; // 个性签名
	// private Integer avatar = 1; // 头像
	private String avatar = DEFAULT_PORTRAIT_URL;
	private Integer level = 1; // 级别 0:管理员 1:普通用户
	private Double balance = 0.0; // 帐号余额

	private Date registerDate = new java.util.Date(); // 注册日期
	private String apiKey;// 唯一标识,每次登陆自动重新生成并填入.
	private Integer state = 0;// 在线状态. 1:在线 0:离线

	private Set<Mood> moods;// 用户发表的心情
	private Set<com.xt8.model.File> files;// 用户上传的文件
	private Set<com.xt8.model.Image> images;// 用户图片

	public User() {
	}

	public User(String phoneNumber, String plainPassword, String nickName,
			String simpleCode) {
		super();
		this.phoneNumber = phoneNumber;
		this.plainPassword = plainPassword;
		this.nickName = nickName;
		this.simpleCode = simpleCode;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getHeartbeatNumber() {
		return heartbeatNumber;
	}

	public void setHeartbeatNumber(String heartbeatNumber) {
		this.heartbeatNumber = heartbeatNumber;
	}

	public String getPlainPassword() {
		return plainPassword;
	}

	public void setPlainPassword(String plainPassword) {
		this.plainPassword = plainPassword;
	}

	public String getEncryptedPassword() {
		return encryptedPassword;
	}

	public void setEncryptedPassword(String encryptedPassword) {
		this.encryptedPassword = encryptedPassword;
	}

	public Integer getGender() {
		return gender;
	}

	public void setGender(Integer gender) {
		this.gender = gender;
	}

	public String getSimpleCode() {
		return simpleCode;
	}

	public void setSimpleCode(String simpleCode) {
		this.simpleCode = simpleCode;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getPersonalizedSignature() {
		return personalizedSignature;
	}

	public void setPersonalizedSignature(String personalizedSignature) {
		this.personalizedSignature = personalizedSignature;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	public Date getRegisterDate() {
		return registerDate;
	}

	public void setRegisterDate(Date registerDate) {
		this.registerDate = registerDate;
	}

	public String getApiKey() {
		return apiKey;
	}

	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Set<Mood> getMoods() {
		return moods;
	}

	public void setMoods(Set<Mood> moods) {
		this.moods = moods;
	}

	public Set<com.xt8.model.File> getFiles() {
		return files;
	}

	public void setFiles(Set<com.xt8.model.File> files) {
		this.files = files;
	}

	public Set<com.xt8.model.Image> getImages() {
		return images;
	}

	public void setImages(Set<com.xt8.model.Image> images) {
		this.images = images;
	}

	public JSONObject toJSON() {
		return Common.toJSON(this);
	}

	public JSONObject toJSON4Login() {
		String[] attrs = { "userId", "nickName", "heartbeatNumber", "avatar",
				"personalizedSignature", "level", "registerDate", };
		return Common.toJSON(this, attrs);
	}

	public JSONObject toJSON4Query() {
		return toJSON4Login();
	}

	public JSONObject toJSON4Register() {
		return toJSON4Login();
	}

	public JSONObject toSimpleJSON() {
		String[] attrs = { "userId", "nickName", "heartbeatNumber", "avatar",
				"personalizedSignature", "level", "registerDate", };
		JSONObject json = Common.toJSON(this, attrs);
		return json;
	}

	public JSONObject toJson4Detail() {
		String[] attrs = { "userId", "nickName", "heartbeatNumber", "avatar",
				"personalizedSignature", "level", "registerDate", "gender" };
		JSONObject json = Common.toJSON(this, attrs);
		return json;
	}

	@Override
	public String toString() {
		return toJSON().toString();
	}

}
