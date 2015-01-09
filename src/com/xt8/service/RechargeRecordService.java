package com.xt8.service;

import java.io.Serializable;

import com.xt8.model.Province;
import com.xt8.model.RechargeRecord;

public interface RechargeRecordService extends BasicService {
	
	public RechargeRecord insertRechargeRecord(RechargeRecord rechargeRecord);
	
	public RechargeRecord findById(Serializable id);

}
