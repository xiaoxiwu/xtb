package com.xt8.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.xt8.model.EvaluateIndex;
import com.xt8.model.Setting;
import com.xt8.service.EvaluateIndexService;
import com.xt8.service.SettingService;

@Transactional
public class EvaluateIndexServiceImpl extends BasicServiceImpl implements
		EvaluateIndexService {

	@Override
	public EvaluateIndex findById(Serializable id) {
		return (EvaluateIndex) super.findById(EvaluateIndex.class, id);
	}

	@Override
	public EvaluateIndex insertEvaluateIndex(EvaluateIndex evaluateIndex) {
		// TODO Auto-generated method stub

		Serializable id = super.save(evaluateIndex);

		return findById(id);
	}

	@Override
	public List<EvaluateIndex> getEvaluateIndexList() {
		String hql = "from EvaluateIndex bean";
		Object[] params = null;
		return super.executeQuery(hql, params);
	}
}
