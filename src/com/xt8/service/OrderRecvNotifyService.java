package com.xt8.service;

import java.io.Serializable;
import java.util.List;

import com.xt8.model.City;
import com.xt8.model.District;
import com.xt8.model.Order;
import com.xt8.model.OrderRecvNotify;
import com.xt8.model.Province;
import com.xt8.model.User;

public interface OrderRecvNotifyService extends BasicService {
	public OrderRecvNotify findById(Serializable id);
	
	public OrderRecvNotify insert(OrderRecvNotify orderRecvNotify);
	
	public OrderRecvNotify findByOrder(Order order);
	
	public void updateOrderRecvNotify(OrderRecvNotify orderRecvNotify);
	
	public List<OrderRecvNotify> findAllNoti(User user,Integer pageIndex,Integer pageSize);

	public String GetRecvAddr(Order order);
}
