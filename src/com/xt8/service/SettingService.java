package com.xt8.service;

import java.io.Serializable;
import java.util.List;

import com.xt8.model.Setting;

public interface SettingService extends BasicService {
	public Setting findById(Serializable id);
	
	public Setting insertSettingCategory(Setting setting);
	
	public List<Setting> findAll();
	
	public List<Setting> findList(int maxRecords);

}
