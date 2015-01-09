package com.xt8.service.impl;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.transaction.annotation.Transactional;

import com.xt8.model.City;
import com.xt8.model.District;
import com.xt8.model.Province;
import com.xt8.service.CityService;
import com.xt8.service.ProvinceService;

@Transactional
public class CityServiceImpl extends BasicServiceImpl implements CityService {

	@Resource(name = "provinceService")
	private ProvinceService provinceService;

	@Override
	public City findById(Serializable id) {
		return (City) super.findById(City.class, id);
	}

	@Override
	public List<City> findByProvinceId(Integer provinceId) {
		Province province = provinceService.findById(provinceId);
		Map<String, Object> conditions = new HashMap<String, Object>();
		conditions.put("province", province);
		return find(City.class, conditions);
	}
}
