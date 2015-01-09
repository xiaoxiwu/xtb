package com.xt8.service;

import java.io.Serializable;

import com.xt8.model.ConsumeRecord;

public interface ConsumeRecordService extends BasicService {
	
	public ConsumeRecord insertConsumeRecord(ConsumeRecord consumeRecord);
	
	public ConsumeRecord findById(Serializable id);

}
