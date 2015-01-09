package com.xt8.timer;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.annotation.Resource;

import com.xt8.model.EvaluateIndex;
import com.xt8.model.Goods;
import com.xt8.model.Order;
import com.xt8.model.OrderRecvNotify;
import com.xt8.model.User;
import com.xt8.service.EvaluateIndexService;
import com.xt8.service.GoodsService;
import com.xt8.service.GoodsUserWantService;
import com.xt8.service.OrderRecvNotifyService;
import com.xt8.service.OrderService;
import com.xt8.util.Constants;
import com.xt8.util.GenerateOrderNumber;

public class ReminderGenerateReceiver {

	@Resource
	private GoodsService goodsService;
	@Resource
	private OrderService orderService;
	@Resource
	private GoodsUserWantService goodsUserWantService;
	@Resource
	private OrderRecvNotifyService orderRecvNotifyService;
	@Resource
	private EvaluateIndexService evaluateIndexService;
	@Resource
	private ReminderCheckUserPayPostage reminderCheckUserPayPostage;

	private Integer count = 1; // 初始化为1
	private Timer timer;
	// private List<User> userWantedList;//想要用户列表
	private Goods goods;

	// 参数count为当前分配给排序后的第几个人

	public ReminderGenerateReceiver() {

	}

	public ReminderGenerateReceiver(int count, int second, Goods goods) {
		this.count = count;//
		// this.userWantedList = new ArrayList<User>();
		this.goods = goods;

		this.timer = new Timer();

		System.out.println("*******************************"
				+ evaluateIndexService);

		this.timer.schedule(new RemindTask(), second * 1000);
	}

	public void trigger(int count, int second, Goods goods) {
		this.count = count;//
		// this.userWantedList = new ArrayList<User>();
		this.goods = goods;

		this.timer = new Timer();

		System.out.println("*******************************"
				+ evaluateIndexService);

		this.timer.schedule(new RemindTask(), second * 1000);
	}

	class RemindTask extends TimerTask {

		public void run() {

			User receiver = null;

			// System.out.println("timer start~~~");
			// ///// 查找出想要的人以及他们的各种指数//////////////
			// 找出指数最大或最开始点击想要的人. 待写
			// 把想要的人相关参数排序之后,送给想要该物品的第(count-1)个人,因为前几个人没付款,
			// 系统就依次送给下一个,初始的时候是送给第一个人.
			System.out.println("*******************************"
					+ evaluateIndexService);
			EvaluateIndex eIndex = evaluateIndexService
					.findById(Constants.EINDEX_COMPREHENSIVE_ID);
			List<User> userWantedList = goodsUserWantService
					.findWantedUserByGoodsBySort(goods, eIndex);

			if (null == userWantedList || userWantedList.isEmpty()) {
				goods.setIfUndercarriage(1);
				goodsService.update(goods);
				timer.cancel();
				return;
			}
			receiver = userWantedList.get(count - 1);
			// /////////////////////////////////////////

			// //////新建一个订单以及通知订单 ////////////////////

			String orderNumber = GenerateOrderNumber.getOrderNumber(); // 产生订单编号
			Order order = new Order(orderNumber, goods, receiver);
			order.setOrderTime(new Date());
			order.setDescription("order system generated");
			Order resultorder = orderService.insert(order);

			OrderRecvNotify orderRecvNotify = new OrderRecvNotify(resultorder);// 产生接收者用户通知
			orderRecvNotify.setReceiver(receiver);
			orderRecvNotifyService.insert(orderRecvNotify);
			// ////////////////////////////////////////////

			// /////////////////物品下架///////////////////
			// 设置终止时间
			// 更新本物品
			goods.setIfUndercarriage(1);
			goodsService.update(goods);
			// ///////////////////////////////////////////

			// 启动另一个定时器,计算用户付款时间 传递count Constants.WAIT_TO_PAY_TIME
		//	new ReminderCheckUserPayPostage(count, 1 * 60, resultorder,
				//	userWantedList, true);
			reminderCheckUserPayPostage.trigger(count, Constants.WAIT_TO_PAY_TIME * 3600, resultorder,
					userWantedList, true);
			timer.cancel(); // Terminate the timer thread
		}
	}
}