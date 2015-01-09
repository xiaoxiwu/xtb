package com.xt8.service;

import java.io.Serializable;
import java.util.List;

import com.xt8.model.Express;
import com.xt8.model.ExpressSite;

public interface ExpressService extends BasicService {
	
	public Express  insertExpress(Express express);
	
	public Express findById(Serializable id);
	/**
	 * 按快递公司Id取快递网点列表
	 * @param expId
	 * @return
	 */
	public List<ExpressSite> listExpressSiteById(int expeId);
	
	
}
