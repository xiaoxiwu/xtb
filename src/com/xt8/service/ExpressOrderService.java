package com.xt8.service;

import java.util.List;

import com.xt8.model.ExpressOrder;



public interface ExpressOrderService extends BasicService {
	/**
	 * 将订单推给网点
	 * @param expSiteId
	 * @param deliverOrderId
	 * @return
	 */
	public String sendOrderToExpressSite(int expSiteId,int deliverOrderId);
	/**
	 * 将订单推给快递员
	 * @param deliverId
	 * @param deliverOrderId
	 * @return
	 */
	public String sendOrderToDeliver(int deliverId,int deliverOrderId);
	/**
	 * 更新订单那状态
	 * @param deliverOrderId
	 * @param statuts
	 * @return
	 */
	public String updateOrderStatus(int deliverOrderId,int statuts);
	/**
	 * 按快递网点Id取快递订单
	 * @return
	 */
	public List<ExpressOrder> getExpressOrdersByExpSiteId(int expSiteId);
	/**
	 * 按快递员Id取快递订单
	 * @return
	 */
	public List<ExpressOrder> getExpressOrdersByDeliverId(int deliverId);
}
