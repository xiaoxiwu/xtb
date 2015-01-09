package com.xt8.service;

import java.io.Serializable;
import java.util.List;

import com.xt8.model.ExpressOrder;



public interface ExpressOrderService extends BasicService {
	public ExpressOrder findById(Serializable id);
	public ExpressOrder insertExpressOrder(ExpressOrder expressOrder);
	/**
	 * 将订单推给网点
	 * @param expSiteId
	 * @param expOrderId
	 * @return
	 */
	public void sendOrderToExpressSite(int expSiteId,int expOrderId);
	/**
	 * 将订单推给快递员
	 * @param deliverId
	 * @param expOrderId
	 * @return
	 */
	public void sendOrderToDeliver(int deliverId,int expOrderId);
	/**
	 * 更新订单那状态
	 * @param deliverOrderId
	 * @param statuts
	 * @return
	 */
	public void updateOrderStatus(int expOrderId,int status);	
}
