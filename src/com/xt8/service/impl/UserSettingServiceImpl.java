package com.xt8.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.transaction.annotation.Transactional;

import com.xt8.model.Setting;
import com.xt8.model.User;
import com.xt8.model.UserSetting;
import com.xt8.service.SettingService;
import com.xt8.service.UserSettingService;
import com.xt8.util.Constants;

@Transactional
public class UserSettingServiceImpl extends BasicServiceImpl implements UserSettingService {

	
	@Override
	public UserSetting findById(Serializable id) {
		return (UserSetting) super.findById(UserSetting.class, id);
	}

	@Override
	public UserSetting insertUserSetting(UserSetting setting) {
		// TODO Auto-generated method stub
		
		Serializable id = super.save(setting);
		
		return findById(id);
	}

	@Override
	public UserSetting findByUserAndSetting(User user, Setting setting) {
		// TODO Auto-generated method stub
		
		String hql = "from UserSetting bean where bean.user=? and bean.setting=?";
		Object[] params = {user,setting};
		
		List<UserSetting> list = super.executeQuery(hql, params);
		
		if(null == list || list.isEmpty())
		{
			return null;
		}
		else
		{
			return list.get(0);
		}
	}
	
	@Override
	public boolean ifOpenThisSetting(User user,Setting setting) {
		// TODO Auto-generated method stub
		
		UserSetting userSetting = findByUserAndSetting(user, setting);
		if(0 == userSetting.getValue())
		{
			return false;
		}
		else
		{
			return true;
		}
	}
	
}
