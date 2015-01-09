package com.xt8.service;

import java.io.Serializable;
import java.util.List;

import com.xt8.model.Province;

public interface ProvinceService extends BasicService {
	public Province findById(Serializable id);

	public List<Province> listProvinces();

}
