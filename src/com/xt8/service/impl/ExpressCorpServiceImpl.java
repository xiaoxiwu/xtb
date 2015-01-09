package com.xt8.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.xt8.model.ExpressCorp;
import com.xt8.service.ExpressCorpService;

@Transactional
public class ExpressCorpServiceImpl extends BasicServiceImpl implements
		ExpressCorpService {

	@Override
	public ExpressCorp insertExpressCorp(ExpressCorp expressCorp) {
		// TODO Auto-generated method stub

		Serializable id = super.save(expressCorp);

		return findById(id);
	}

	@Override
	public ExpressCorp findById(Serializable id) {
		return (ExpressCorp) findById(ExpressCorp.class, id);
	}

	@Override
	public String SendMessage(ExpressCorp expressCorp, String recvAddr,
			String takeGoodsAddr) {
		// TODO Auto-generated method stub

		// 获取到该快递公司联系方式,然后发送recvAddr+takeGoodsAddr
		String str = "快递公司(" + expressCorp.getEcName() + "),上门取货地址: "
				+ takeGoodsAddr + "; 收货地址: " + recvAddr;

		return str;
	}

	@Override
	public List<ExpressCorp> listExpressCorps() {
		return find(ExpressCorp.class, null);
	}

}
