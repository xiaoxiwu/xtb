package com.xt8.service;

import java.io.Serializable;
import java.util.List;

import com.xt8.model.Deliver;
import com.xt8.model.ExpressOrder;
import com.xt8.model.ExpressSite;

public interface ExpressSiteService extends BasicService {
	
	public ExpressSite insertExpressSite(ExpressSite expressSite);
	
	public ExpressSite findById(Serializable id);
	/**
	 * 按网点Id取快递员列表
	 * @param expId
	 * @return
	 */
	public List<Deliver> listDeliverById(int expSiteId);

	/**
	 * 按网点Id取快递订单列表
	 * @param deliverId
	 * @return
	 */
	public List<ExpressOrder> listExpressOrderById(int deliverId);
	
}
