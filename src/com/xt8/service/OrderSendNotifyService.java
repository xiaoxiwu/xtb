package com.xt8.service;

import java.io.Serializable;
import java.util.List;

import com.xt8.model.Order;
import com.xt8.model.OrderSendNotify;
import com.xt8.model.User;

public interface OrderSendNotifyService extends BasicService {
	public OrderSendNotify findById(Serializable id);
	
	public OrderSendNotify insert(OrderSendNotify orderSendNotify);
	
	public OrderSendNotify findByOrder(Order order);
	
	public void updateOrderSendNotify(OrderSendNotify orderSendNotify);
	
	public List<OrderSendNotify> findAllNoti(User user,Integer pageIndex,Integer pageSize);

	
}
