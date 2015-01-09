package com.xt8.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.xt8.model.Province;
import com.xt8.service.ProvinceService;

@Transactional
public class ProvinceServiceImpl extends BasicServiceImpl implements
		ProvinceService {

	@Override
	public Province findById(Serializable id) {
		return (Province) super.findById(Province.class, id);
	}

	@Override
	public List<Province> listProvinces() {
		return find(Province.class, null);
	}
}
