package com.xt8.service.impl;

import java.io.Serializable;
import com.xt8.model.Deliver;
import com.xt8.model.ExpressOrder;
import com.xt8.model.ExpressSite;
import com.xt8.service.DeliverService;
import com.xt8.service.ExpressOrderService;
import com.xt8.service.ExpressSiteService;

public class ExpressOrderServiceImpl extends BasicServiceImpl implements ExpressOrderService {

	private DeliverService deliverServiceImpl;
	private ExpressSiteService expressSiteServiceImpl;
	public ExpressSiteService getExpressSiteServiceImpl() {
		return expressSiteServiceImpl;
	}

	public void setExpressSiteServiceImpl(
			ExpressSiteServiceImpl expressSiteServiceImpl) {
		this.expressSiteServiceImpl = expressSiteServiceImpl;
	}

	public DeliverService getDeliverServiceImpl() {
		return deliverServiceImpl;
	}

	public void setDeliverServiceImpl(DeliverServiceImpl deliverServiceImpl) {
		this.deliverServiceImpl = deliverServiceImpl;
	}
	@Override
	public void sendOrderToDeliver(int deliverId, int expOrderId) {
		// TODO Auto-generated method stub
		ExpressOrder exporder=findById(expOrderId);
		Deliver deliver=deliverServiceImpl.findById(deliverId);
		exporder.setDeliver(deliver);
		super.update(exporder);
	}

	@Override
	public ExpressOrder insertExpressOrder(ExpressOrder expressOrder) {
		// TODO Auto-generated method stub
		Serializable id=super.save(expressOrder);
		return findById(id);
	}

	@Override
	public void updateOrderStatus(int expOrderId, int status) {
		// TODO Auto-generated method stub
		ExpressOrder exporder=findById(expOrderId);
		exporder.setStatus(status);
		super.update(exporder);
	}

	@Override
	public ExpressOrder findById(Serializable id) {
		// TODO Auto-generated method stub
		return (ExpressOrder)findById(ExpressOrder.class, id);
	}

	@Override
	public void sendOrderToExpressSite(int expSiteId, int expOrderId) {
		// TODO Auto-generated method stub
		ExpressOrder exporder=findById(expOrderId);
		ExpressSite expSite=expressSiteServiceImpl.findById(expSiteId);
		exporder.setExpSite(expSite);
		super.update(exporder);
	}

	
}
