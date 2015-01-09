package com.xt8.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.xt8.model.City;
import com.xt8.model.District;
import com.xt8.model.Order;
import com.xt8.model.OrderRecvNotify;
import com.xt8.model.Province;
import com.xt8.model.User;
import com.xt8.service.OrderRecvNotifyService;
import com.xt8.service.OrderService;

@Transactional
public class OrderRecvNotifyServiceImpl extends BasicServiceImpl implements
		OrderRecvNotifyService {

	@Override
	public OrderRecvNotify findById(Serializable id) {
		return (OrderRecvNotify) super.findById(OrderRecvNotify.class, id);
	}

	@Override
	public OrderRecvNotify insert(OrderRecvNotify orderRecvNotify) {
		return (OrderRecvNotify) super.saveAndReturn(OrderRecvNotify.class,
				orderRecvNotify);
	}

	@Override
	public OrderRecvNotify findByOrder(Order order) {
		// TODO Auto-generated method stub
		String hql = "from OrderRecvNotify bean where bean.order = ?";
		Object[] params = { order };
		List<OrderRecvNotify> list = super.executeQuery(hql, params);

		if (null == list || list.isEmpty()) {
			return null;
		} else {
			return list.get(0);
		}
	}

	@Override
	public void updateOrderRecvNotify(OrderRecvNotify orderRecvNotify) {
		// TODO Auto-generated method stub

		super.update(orderRecvNotify);

	}

	@Override
	public  List<OrderRecvNotify> findAllNoti(User user,Integer pageIndex,Integer pageSize) {
		// TODO Auto-generated method stub
		
		String hql = "from OrderRecvNotify bean where bean.receiver = ? ";
		
		Object[] params = { user };
		List<OrderRecvNotify> list = super.executeQueryByPage(hql, params,pageIndex,pageSize);
		
		if (null == list || list.isEmpty()) {
			return null;
		} else {
			return list;
		}
	}

	@Override
	public String GetRecvAddr(Order order) {
		// TODO Auto-generated method stub

		OrderRecvNotify orderRecvNotify = findByOrder(order);
		
		String addr = orderRecvNotify.getRecvProvince().getProvinceName()+
				orderRecvNotify.getRecvCity().getCityName()+
				orderRecvNotify.getRecvDistrict().getDistName()+" "+orderRecvNotify.getRecvDetailAddr()+
				orderRecvNotify.getRecvPerson()+" "+orderRecvNotify.getRecvContact();
		
		return addr;
	}
}
