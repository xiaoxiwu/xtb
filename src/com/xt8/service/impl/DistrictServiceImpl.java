package com.xt8.service.impl;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.transaction.annotation.Transactional;

import com.xt8.model.City;
import com.xt8.model.District;
import com.xt8.service.CityService;
import com.xt8.service.DistrictService;

@Transactional
public class DistrictServiceImpl extends BasicServiceImpl implements
		DistrictService {

	@Resource(name = "cityService")
	private CityService cityService;

	@Override
	public District findById(Serializable id) {
		return (District) super.findById(District.class, id);
	}

	@Override
	public List<District> findByCityId(Integer cityId) {
		City city = cityService.findById(cityId);
		Map<String, Object> conditions = new HashMap<String, Object>();
		conditions.put("city", city);
		return find(District.class, conditions);
	}
}
