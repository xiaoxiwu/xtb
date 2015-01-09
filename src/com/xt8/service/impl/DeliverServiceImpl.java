package com.xt8.service.impl;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.xt8.model.Deliver;
import com.xt8.model.Express;
import com.xt8.model.ExpressOrder;
import com.xt8.service.DeliverService;

public class DeliverServiceImpl extends BasicServiceImpl implements DeliverService {


	@Override
	public Deliver findById(Serializable id) {
		// TODO Auto-generated method stub
		return (Deliver) findById(Deliver.class, id);
	}

	@Override
	public Deliver insertExpressSite(Deliver deliver) {
		// TODO Auto-generated method stub
		Serializable id = super.save(deliver);

		return findById(id);
	}

	@Override
	public List<ExpressOrder> listExpressOrderById(int deliverId) {
		// TODO Auto-generated method stub
		Map<String, Object> condition=new HashMap<String, Object>();
		condition.put("DeliverId", deliverId);
		return find(Deliver.class, condition);
	}

	
}
