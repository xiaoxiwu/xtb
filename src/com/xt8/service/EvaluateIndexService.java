package com.xt8.service;

import java.io.Serializable;
import java.util.List;

import com.xt8.model.EvaluateIndex;

public interface EvaluateIndexService extends BasicService {
	public EvaluateIndex findById(Serializable id);
	
	public EvaluateIndex insertEvaluateIndex(EvaluateIndex evaluateIndex);
	
	public List<EvaluateIndex> getEvaluateIndexList();

}
