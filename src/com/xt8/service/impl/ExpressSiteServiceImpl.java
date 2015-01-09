package com.xt8.service.impl;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.xt8.model.ExpressCorp;
import com.xt8.model.ExpressOrder;
import com.xt8.model.ExpressSite;
import com.xt8.model.OrderSendNotify;
import com.xt8.service.ExpressCorpService;
import com.xt8.service.ExpressSiteService;
import com.xt8.util.Common;

@Transactional
public class ExpressSiteServiceImpl extends BasicServiceImpl implements
		ExpressSiteService {

	@Override
	public ExpressSite findById(Serializable id) {
		// TODO Auto-generated method stub
		return (ExpressSite) findById(ExpressSite.class, id);
	}

	@Override
	public ExpressSite insertExpressSite(ExpressSite expressSite) {
		// TODO Auto-generated method stub
		Serializable id = super.save(expressSite);

		return findById(id);
	}

	@Override
	public List<ExpressSite> listExpressSites() {
		// TODO Auto-generated method stub
		return find(ExpressSite.class, null);
	}
	

}
