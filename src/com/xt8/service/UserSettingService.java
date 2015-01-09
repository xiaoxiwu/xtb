package com.xt8.service;

import java.io.Serializable;

import com.xt8.model.Setting;
import com.xt8.model.User;
import com.xt8.model.UserSetting;

public interface UserSettingService extends BasicService {
	public UserSetting findById(Serializable id);
	
	public UserSetting insertUserSetting(UserSetting setting);
	
	public UserSetting findByUserAndSetting(User user,Setting setting);
	
	public boolean ifOpenThisSetting(User user,Setting setting);
	

}
