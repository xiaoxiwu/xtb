package com.xt8.service;

import java.io.Serializable;
import java.util.List;

import com.xt8.model.ExpressCorp;
import com.xt8.model.ExpressSite;

public interface ExpressSiteService extends BasicService {
	
	public ExpressSite insertExpressSite(ExpressSite expressSite);
	
	public ExpressSite findById(Serializable id);
	public List<ExpressSite> listExpressSites();

}
