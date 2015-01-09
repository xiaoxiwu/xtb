package com.xt8.service;

import java.io.Serializable;
import java.util.List;

import com.xt8.model.Deliver;
import com.xt8.model.ExpressOrder;

public interface DeliverService extends BasicService {
public Deliver insertExpressSite(Deliver deliver);
	
	public  Deliver findById(Serializable id);
	/**
	 * 按快递员Id取快递订单列表
	 * @param deliverId
	 * @return
	 */
	public  List<ExpressOrder> listExpressOrderById(int deliverId);

}
