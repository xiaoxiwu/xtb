package com.xt8.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.xt8.model.Setting;
import com.xt8.service.SettingService;

@Transactional
public class SettingServiceImpl extends BasicServiceImpl implements
		SettingService {

	@Override
	public Setting findById(Serializable id) {
		return (Setting) super.findById(Setting.class, id);
	}

	@Override
	public Setting insertSettingCategory(Setting setting) {
		Serializable id = super.save(setting);
		return findById(id);
	}

	@Override
	public List<Setting> findList(int maxRecords) {
		String hql = "from Setting bean";
		Object[] params = null;
		return super.executeQueryByPage(hql, params, 1, maxRecords);
	}

	@Override
	public List<Setting> findAll() {
		String hql = "from Setting bean";
		Object[] params = null;
		return super.executeQuery(hql, params);
	}
}
