package com.xt8.service;

import java.io.Serializable;

import com.xt8.model.Goods;
import com.xt8.model.Order;

public interface OrderService extends BasicService {
	public Order findById(Serializable id);
	
	public Order insert(Order order);
	
	public Order findByOrderNumber(String orderNumber);
	
	public Order findByOrder(Goods goods);

}
