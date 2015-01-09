package com.xt8.service;

import java.io.Serializable;
import java.util.List;

import com.xt8.model.ExpressCorp;

public interface ExpressCorpService extends BasicService {
	
	public ExpressCorp insertExpressCorp(ExpressCorp expressCorp);
	
	public ExpressCorp findById(Serializable id);
	
	public String SendMessage(ExpressCorp expressCorp,String recvAddr,String takeGoodsAddr);
	
	public List<ExpressCorp> listExpressCorps();

}
