package com.xt8.service.impl;

import java.io.Serializable;

import org.springframework.transaction.annotation.Transactional;

import com.xt8.model.DeliveryLog;
import com.xt8.model.ExpressCorp;
import com.xt8.model.File;
import com.xt8.service.DeliveryLogService;
import com.xt8.service.ExpressCorpService;
import com.xt8.service.FileService;

@Transactional
public class DeliveryLogServiceImpl extends BasicServiceImpl implements DeliveryLogService {

	@Override
	public DeliveryLog insertDeliveryLog(DeliveryLog deliveryLog) {
		// TODO Auto-generated method stub

		Serializable id = super.save(deliveryLog);
		
		return findById(id);
	}
	
	
	@Override
	public DeliveryLog findById(Serializable id) {
		return (DeliveryLog) findById(DeliveryLog.class, id);
	}

}
