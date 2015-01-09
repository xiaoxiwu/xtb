package com.xt8.service.impl;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.xt8.model.Order;
import com.xt8.model.OrderSendNotify;
import com.xt8.model.User;
import com.xt8.service.OrderSendNotifyService;

@Transactional
public class OrderSendNotifyServiceImpl extends BasicServiceImpl implements
		OrderSendNotifyService {

	@Override
	public OrderSendNotify findById(Serializable id) {
		return (OrderSendNotify) super.findById(OrderSendNotify.class, id);
	}

	@Override
	public OrderSendNotify insert(OrderSendNotify orderSendNotify) {
		return (OrderSendNotify) super.saveAndReturn(OrderSendNotify.class,
				orderSendNotify);
	}

	@Override
	public OrderSendNotify findByOrder(Order order) {
		// TODO Auto-generated method stub
		String hql = "from OrderSendNotify bean where bean.order = ?";
		Object[] params = { order };
		List<OrderSendNotify> list = super.executeQuery(hql, params);

		if (null == list || list.isEmpty()) {
			return null;
		} else {
			return list.get(0);
		}
	}

	@Override
	public void updateOrderSendNotify(OrderSendNotify orderSendNotify) {
		// TODO Auto-generated method stub

		super.update(orderSendNotify);

	}

	@Override
	public  List<OrderSendNotify> findAllNoti(User user,Integer pageIndex,Integer pageSize) {
		// TODO Auto-generated method stub
		
		String hql = "from OrderSendNotify bean where bean.sender = ? ";
		
		Object[] params = { user };
		List<OrderSendNotify> list = super.executeQueryByPage(hql, params,pageIndex,pageSize);
		
		if (null == list || list.isEmpty()) {
			return null;
		} else {
			return list;
		}
	}

	
}
