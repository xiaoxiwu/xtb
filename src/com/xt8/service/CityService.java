package com.xt8.service;

import java.io.Serializable;
import java.util.List;

import com.xt8.model.City;

public interface CityService extends BasicService {
	public City findById(Serializable id);

	public List<City> findByProvinceId(Integer provinceId);

}
