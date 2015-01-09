package com.xt8.service.impl;

import java.io.Serializable;

import org.springframework.transaction.annotation.Transactional;

import com.xt8.model.RechargeRecord;
import com.xt8.service.RechargeRecordService;

@Transactional
public class RechargeRecordServiceImpl extends BasicServiceImpl implements RechargeRecordService {

	@Override
	public RechargeRecord insertRechargeRecord(RechargeRecord rechargeRecord) {
		// TODO Auto-generated method stub
		
		Serializable id = super.save(rechargeRecord);
		return findById(id);
	}

	@Override
	public RechargeRecord findById(Serializable id) {
		// TODO Auto-generated method stub
		return (RechargeRecord) super.findById(RechargeRecord.class, id);
	}
}
