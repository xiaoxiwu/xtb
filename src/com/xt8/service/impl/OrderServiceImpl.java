package com.xt8.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.xt8.model.Goods;
import com.xt8.model.Order;
import com.xt8.service.OrderService;

@Transactional
public class OrderServiceImpl extends BasicServiceImpl implements OrderService {

	@Override
	public Order findById(Serializable id) {
		return (Order) super.findById(Order.class, id);
	}

	@Override
	public Order insert(Order order) {
		return (Order) super.saveAndReturn(Order.class, order);
	}

	@Override
	public Order findByOrderNumber(String orderNumber) {
		// TODO Auto-generated method stub
		String hql = "from Order bean where bean.orderNumber = ?";
		Object[] params = { orderNumber };
		List<Order> list = super.executeQuery(hql, params);

		if (null == list || list.isEmpty()) {
			return null;
		} else {
			return list.get(0);
		}
	}

	@Override
	public Order findByOrder(Goods goods) {
		// TODO Auto-generated method stub
		return null;
	}
}
