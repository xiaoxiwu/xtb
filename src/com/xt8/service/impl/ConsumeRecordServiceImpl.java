package com.xt8.service.impl;

import java.io.Serializable;

import org.springframework.transaction.annotation.Transactional;

import com.xt8.model.ConsumeRecord;
import com.xt8.service.ConsumeRecordService;

@Transactional
public class ConsumeRecordServiceImpl extends BasicServiceImpl implements ConsumeRecordService {

	@Override
	public ConsumeRecord insertConsumeRecord(ConsumeRecord consumeRecord) {
		// TODO Auto-generated method stub
		
		Serializable id = super.save(consumeRecord);
		return findById(id);
	}

	@Override
	public ConsumeRecord findById(Serializable id) {
		// TODO Auto-generated method stub
		return (ConsumeRecord) super.findById(ConsumeRecord.class, id);
	}
}
