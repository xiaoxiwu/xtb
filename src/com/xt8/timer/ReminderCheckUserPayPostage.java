package com.xt8.timer;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.annotation.Resource;

import com.xt8.model.Goods;
import com.xt8.model.Order;
import com.xt8.model.OrderRecvNotify;
import com.xt8.model.User;
import com.xt8.service.OrderRecvNotifyService;
import com.xt8.service.OrderService;
import com.xt8.timer.ReminderGenerateReceiver.RemindTask;
import com.xt8.util.Constants;
import com.xt8.util.GenerateOrderNumber;

public class ReminderCheckUserPayPostage {

	@Resource
	private OrderService orderService;
	@Resource
	private OrderRecvNotifyService orderRecvNotifyService;

	private boolean type = false;// false: one
	private Integer count = 1; // 初始化为1
	// private Timer timer;
	private List<Timer> timerList = new ArrayList<Timer>();
	private Order order;
	private List<User> userWantedList;

	public ReminderCheckUserPayPostage() {

	}

	public ReminderCheckUserPayPostage(int count, int second, Order order,
			List<User> userWantedList, boolean type) {

		this.count = count;
		this.order = order;
		this.type = type;
		this.userWantedList = userWantedList;
		// this.timer = new Timer();
		Timer timer = new Timer();
		timerList.add(timer);
		timer.schedule(new RemindTask(), second * 1000);
		// this.timer.schedule(new RemindTask(), second*1000);

	}

	public void trigger(int count, int second, Order order,
			List<User> userWantedList, boolean type) {
		this.count = count;
		this.order = order;
		this.type = type;
		this.userWantedList = userWantedList;
		Timer timer = new Timer();
		// this.timer = new Timer();
		timerList.add(timer);

		timer.schedule(new RemindTask(), second * 1000);
	}

	class RemindTask extends TimerTask {

		@Override
		public void run() {
			Timer timer = timerList.get(timerList.size() - 1);
			// TODO Auto-generated method stub

			// //////如果在规定的时间用户没有付款,那么首先要取消该订单,填入取消理由.////////
			OrderRecvNotify orderRecvNotify = orderRecvNotifyService
					.findByOrder(order);
			if (1 == orderRecvNotify.getIfPay()) {
				timer.cancel();
				return; // 如果已付款,应取消该定时器
			}
			// //////////////////////////////////////////////////////////
			order.setStatus(-3);
			order.setStatusDesc("接收方未付款");
			orderService.update(order);

			if (!type)// 如果只是送给某一个人,而这个人没在规定时间内付款
			{
				timer.cancel();
				return;
			}

			User receiver = null;

			// 再调用产生接收用户的方法
			// ///// 查找出想要的人以及他们的各种指数//////////////
			// 找出指数最大或最开始点击想要的人. 待写
			// 把想要的人相关参数排序之后,送给想要该物品的第(++count-1)个人,因为前几个人没付款,
			// 系统就依次送给下一个,初始的时候是送给第一个人.
			++count;// 如果未付款,送给下一个人

			if ((count - 1) >= userWantedList.size()) {
				timer.cancel();
				return;
			}

			if (null != userWantedList)
				receiver = userWantedList.get(count - 1);

			if (null == receiver) {
				timer.cancel();
				return;
			}
			// /////////////////////////////////////////

			// //////新建一个订单以及通知订单 ////////////////////
			String orderNumber = GenerateOrderNumber.getOrderNumber(); // 产生订单编号
			Order newOrder = new Order(orderNumber, order.getGoods(), receiver);
			newOrder.setOrderTime(new Date());
			newOrder.setDescription("order generated");
			Order resultOrder = orderService.insert(newOrder);

			orderRecvNotify = new OrderRecvNotify(resultOrder);// 产生接收者用户通知
			orderRecvNotify.setReceiver(receiver);
			orderRecvNotifyService.insert(orderRecvNotify);
			// ////////////////////////////////////////////

			// 重新启动该定时器 Constants.WAIT_TO_PAY_TIME
			// new
			// ReminderCheckUserPayPostage(count,1*60,resultOrder,userWantedList,true);

			timer.cancel();
			trigger(count, Constants.WAIT_TO_PAY_TIME * 3600, resultOrder, userWantedList, true);

			//System.out.println("*******************da du  1.5");

		}

	}

}
