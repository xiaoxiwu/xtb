package com.xt8.service;

import java.io.Serializable;
import java.util.List;

import com.xt8.model.District;

public interface DistrictService extends BasicService {
	public District findById(Serializable id);
	
	public List<District> findByCityId(Integer cityId);

}
