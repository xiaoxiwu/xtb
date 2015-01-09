package com.xt8.service.impl;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.xt8.model.Express;
import com.xt8.model.ExpressSite;
import com.xt8.service.ExpressService;

public class ExpressServiceImpl extends BasicServiceImpl implements ExpressService {

	@Override
	public Express findById(Serializable id) {
		// TODO Auto-generated method stub
		return (Express) findById(Express.class, id);
	}

	@Override
	public Express insertExpress(Express express) {
		// TODO Auto-generated method stub
		Serializable id = super.save(express);

		return findById(id);
	}

	@Override
	public List<ExpressSite> listExpressSiteById(int expeId) {
		// TODO Auto-generated method stub
		Map<String, Object> condition=new HashMap<String, Object>();
		condition.put("ExpressId", expeId);
		return find(ExpressSite.class,condition );
	}

	@Override
	public List<Express> listExpress() {
		// TODO Auto-generated method stub
		return find(Express.class,null );
	}

	
}
