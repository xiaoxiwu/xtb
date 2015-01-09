package com.xt8.service;

import java.io.Serializable;

import com.xt8.model.DeliveryLog;

public interface DeliveryLogService extends BasicService {
	
	public DeliveryLog insertDeliveryLog(DeliveryLog deliveryLog);
	
	public DeliveryLog findById(Serializable id);
	
	

}
