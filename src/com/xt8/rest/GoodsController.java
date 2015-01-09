/**
 * GoodsController  file
 * Date : 2014-11-9
 * author : cxt
 * 
 * Provide restful style web service related to goods 
 */

package com.xt8.rest;

import static com.xt8.util.Constants.EINDEX_CHARM_ID;
import static com.xt8.util.Constants.EINDEX_COMPREHENSIVE_ID;
import static com.xt8.util.Constants.EINDEX_FINISH_BARGAIN_ADDVALUE;
import static com.xt8.util.Constants.EINDEX_INFLUENCE_ID;
import static com.xt8.util.Constants.EINDEX_LIKE_ADDVALUE;
import static com.xt8.util.Constants.EINDEX_WANT_ADDVALUE;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.xt8.model.Category;
import com.xt8.model.City;
import com.xt8.model.DeliveryLog;
import com.xt8.model.District;
import com.xt8.model.EvaluateIndex;
import com.xt8.model.ExpressCorp;
import com.xt8.model.Goods;
import com.xt8.model.GoodsImage;
import com.xt8.model.Image;
import com.xt8.model.Order;
import com.xt8.model.OrderRecvNotify;
import com.xt8.model.OrderSendNotify;
import com.xt8.model.Province;
import com.xt8.model.Setting;
import com.xt8.model.User;
import com.xt8.model.UserEvaluateIndex;
import com.xt8.service.CategoryService;
import com.xt8.service.CityService;
import com.xt8.service.DeliveryLogService;
import com.xt8.service.DistrictService;
import com.xt8.service.EvaluateIndexService;
import com.xt8.service.ExpressCorpService;
import com.xt8.service.GoodsImageService;
import com.xt8.service.GoodsService;
import com.xt8.service.GoodsUserLikeService;
import com.xt8.service.GoodsUserWantService;
import com.xt8.service.ImageService;
import com.xt8.service.OrderRecvNotifyService;
import com.xt8.service.OrderSendNotifyService;
import com.xt8.service.OrderService;
import com.xt8.service.ProvinceService;
import com.xt8.service.SettingService;
import com.xt8.service.UserEvaluateIndexService;
import com.xt8.service.UserLimitedForWantGoodsService;
import com.xt8.service.UserService;
import com.xt8.service.UserSettingService;
import com.xt8.timer.ReminderCheckUserPayPostage;
import com.xt8.timer.ReminderGenerateReceiver;
import com.xt8.util.ComputePostage;
import com.xt8.util.Constants;
import com.xt8.util.GenerateOrderNumber;
import com.xt8.util.StringUtil;

/**
 * Create a GoodsControllers class contains all the restful webservice related
 * to goods under the namespace of "/goods".
 * 
 * @author cxt
 *
 */
@Path("/goods")
public class GoodsController {

	@Resource
	private GoodsService goodsService;
	@Resource
	private UserService userService;
	@Resource
	private ProvinceService provinceService;
	@Resource
	private CityService cityService;
	@Resource
	private DistrictService districtService;
	@Resource
	private CategoryService categoryService;
	@Resource
	private SettingService settingService;
	@Resource
	private UserSettingService userSettingService;
	@Resource
	private EvaluateIndexService evaluateIndexService;
	@Resource
	private UserEvaluateIndexService userEvaluateIndexService;
	@Resource
	private OrderService orderService;
	@Resource
	private OrderRecvNotifyService orderRecvNotifyService;
	@Resource
	private OrderSendNotifyService orderSendNotifyService;
	@Resource
	private ImageService imageService;
	@Resource
	private GoodsImageService goodsImageService;
	@Resource
	private GoodsUserWantService goodsUserWantService;
	@Resource
	private GoodsUserLikeService goodsUserLikeService;
	@Resource
	private UserLimitedForWantGoodsService userLimitedForWantGoodsService;
	@Resource
	private ExpressCorpService expressCorpService;
	@Resource
	private DeliveryLogService deliveryLogService;

	@Resource
	private ReminderGenerateReceiver reminderGenerateReceiver;
	
	@Resource
	private ReminderCheckUserPayPostage reminderCheckUserPayPostage;

	private JSONObject json = new JSONObject();

	@POST
	@Path("publishGoods.do")
	@Produces(MediaType.APPLICATION_JSON)
	public String publishGoods(@FormParam("apiKey") String apiKey,
			@FormParam("myId") Integer myId,
			@FormParam("goodsName") String goodsName,
			@FormParam("goodsSort") Integer goodsSort,
			@FormParam("goodsImagePath") String goodsImagePath,
			@FormParam("goodsProvince") Integer goodsProvince,
			@FormParam("goodsCity") Integer goodsCity,
			@FormParam("goodsWeight") Integer goodsWeight,
			@FormParam("goodsPostageType") Integer goodsPostageType,
			@FormParam("tag") String tag,
			@FormParam("goodsExpireTime") Integer goodsExpireTime,
			@FormParam("receiverId") Integer receiverId,
			@FormParam("description") String description) {

		// User me = userService.findByApiKey(apiKey); //
		User me = userService.findById(myId);
		Province province = provinceService.findById(goodsProvince);
		City city = cityService.findById(goodsCity);
		Category cate = categoryService.findById(goodsSort);

		User receiver = null; // 接收者,可能有,也可能没有,取决于用户只是想上传到我的专柜还是想送给某个人

		if (null == me || null == province || null == city || null == cate) {
			json.put("status", -1);
			json.put("msg", "操作失败,参数异常");
			return json.toString();
		}

		if (!apiKey.equals(me.getApiKey()))// 检测APIKEY是否相等
		{
			json.put("status", 0);
			json.put("msg", "您的apiKey已过期,您的账户可能被别人登陆，请修改密码或重新登陆");
			return json.toString();
		}

		if (null != receiverId)// 如果有接收者,产生接收者
		{
			receiver = userService.findById(receiverId);

			if (null == receiver) {
				json.put("status", -1);
				json.put("msg", "操作失败,参数异常");
				return json.toString();
			}
			// /////////////
			// /检查用户是否没有打开接收快件的开关如果没有不能发送
			Setting setting = settingService
					.findById(Constants.SETTING_RECEIVE_GOODS_ID);
			if (!userSettingService.ifOpenThisSetting(receiver, setting)) {
				json.put("status", -2);
				json.put("msg", "该用户不想接收物品");
				return json.toString();
			}
			// /////////////

		}

		Goods goods = new Goods(goodsName, cate, province, city, goodsWeight,
				me, goodsPostageType);

		if (!StringUtil.isNullOrBlank(tag)) {
			goods.setTag(tag);
		}

		// //////////////////////////////////////////////////////

		// 设置物品到期时间,当前时间加上 goodsExpireTime个小时.
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		c.add(Calendar.HOUR, goodsExpireTime);
		// Date date = c.getTime();
		// SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		// String time = df.format(date);
		goods.setExpireTime(c.getTime());

		// ////////////////////////////////////////////////////////

		if (!StringUtil.isNullOrBlank(description)) {
			goods.setDescription(description);
		}

		if (null != receiver) {
			goods.setIfUndercarriage(1); // 如果有人接收了,就此下架
		}

		Goods resultgoods = goodsService.insert(goods);// 插入物品数据库

		// ///////////////插入物品图片/////////////////////
		Image image = new Image(me, "", goodsImagePath);
		image = imageService.insert(image);

		// GoodsImage goodsImage = new GoodsImage(resultgoods,image);
		goodsImageService.insert(new GoodsImage(resultgoods, image));
		// /////////////////////////////////////////////

		if (null != receiver)// 如果有接收者,产生订单信息
		{
			// /// 产生订单信息
			String orderNumber = GenerateOrderNumber.getOrderNumber(); // 产生订单编号
			Order order = new Order(orderNumber, resultgoods, receiver);
			order.setOrderTime(new Date());
			order.setDescription("give sb equally");
			Order resultorder = orderService.insert(order);

			OrderRecvNotify orderRecvNotify = new OrderRecvNotify(resultorder);// 产生接收者用户通知
			orderRecvNotify.setReceiver(receiver);
			orderRecvNotifyService.insert(orderRecvNotify);
			// 下面这个函数只是单纯的检测所要送的用户是不是在规定时间内付款,如果不是,则终止,因此第一个参数和第四个参数无用
		//	new ReminderCheckUserPayPostage(1, Constants.WAIT_TO_PAY_TIME * 60,
				//	resultorder, null, false);
			reminderCheckUserPayPostage.trigger(1, Constants.WAIT_TO_PAY_TIME * 3600,
					resultorder, null, false);
			// // 产生接收通知信息
		} else {
			// 开始计时,从现在开始goodsExpireTime小时以后 goodsExpireTime
			// new ReminderGenerateReceiver(1, 2 * 60, resultgoods);//
			// 到时间后,会送排队中的第一个人
			reminderGenerateReceiver.trigger(1, goodsExpireTime * 3600, resultgoods);
		}

		json.put("status", 1);
		json.put("msg", "共享物品成功");
		json.put("goodsId", resultgoods.getGoodsId());
		return json.toString();

	}

	@POST
	@Path("wantGoods.do")
	@Produces(MediaType.APPLICATION_JSON)
	public String wantGoods(@FormParam("apiKey") String apiKey,
			@FormParam("myId") Integer myId,
			@FormParam("goodsId") Integer goodsId) {

		User me = userService.findById(myId);
		if (null == me) {
			json.put("status", -1);
			json.put("msg", "操作失败,参数异常");
			return json.toString();
		}

		if (!apiKey.equals(me.getApiKey()))// 检测APIKEY是否相等
		{
			json.put("status", 0);
			json.put("msg", "您的apiKey已过期,您的账户可能被别人登陆，请修改密码或重新登陆");
			return json.toString();
		}

		Goods goods = goodsService.findById(goodsId);
		if (null == goods) {
			json.put("status", -1);
			json.put("msg", "操作失败,参数异常");
			return json.toString();
		}

		User goodsOwner = goods.getOwner();// 检测对方是否限制我参与他的共享
		boolean result = userLimitedForWantGoodsService
				.ifuserhostLimitedByuserlimited(goodsOwner, me);

		if (result)// true 表现已限制
		{
			json.put("status", -2);
			json.put("msg", "对方不允许你参与TA的共享");
			return json.toString();
		} else {
			goodsUserWantService.SetWantedUserForGoods(me, goods);
		}

		json.put("status", 1);
		json.put("msg", "想要成功");
		return json.toString();
	}

	@POST
	@Path("likeGoods.do")
	@Produces(MediaType.APPLICATION_JSON)
	public String likeGoods(@FormParam("apiKey") String apiKey,
			@FormParam("myId") Integer myId,
			@FormParam("goodsId") Integer goodsId) {

		User me = userService.findById(myId);
		if (null == me) {
			json.put("status", -1);
			json.put("msg", "操作失败,参数异常");
			return json.toString();
		}

		if (!apiKey.equals(me.getApiKey()))// 检测APIKEY是否相等
		{
			json.put("status", 0);
			json.put("msg", "您的apiKey已过期,您的账户可能被别人登陆，请修改密码或重新登陆");
			return json.toString();
		}

		Goods goods = goodsService.findById(goodsId);
		if (null == goods) {
			json.put("status", -1);
			json.put("msg", "操作失败,参数异常");
			return json.toString();
		}

		goodsUserLikeService.SetLikeUserForGoods(me, goods);
		json.put("status", 1);
		json.put("msg", "喜欢成功");
		return json.toString();
	}

	@POST
	@Path("deleteGoods.do")
	@Produces(MediaType.APPLICATION_JSON)
	public String deleteGoods(@FormParam("apiKey") String apiKey,
			@FormParam("myId") Integer myId,
			@FormParam("goodsId") Integer goodsId) {

		User me = userService.findById(myId);
		if (null == me) {
			json.put("status", -1);
			json.put("msg", "操作失败,参数异常");
			return json.toString();
		}

		if (!apiKey.equals(me.getApiKey()))// 检测APIKEY是否相等
		{
			json.put("status", 0);
			json.put("msg", "您的apiKey已过期,您的账户可能被别人登陆，请修改密码或重新登陆");
			return json.toString();
		}

		Goods goods = goodsService.findById(goodsId);
		
		if(null == goods){
			
			json.put("status", -1);
			json.put("msg", "操作失败,参数异常");
			return json.toString();
		}
		
		// 如果正在进行中,无法删除
		if (goods.getIfUndercarriage() == 0) {

			json.put("status", -2);
			json.put("msg", "该物品正在共享中,无法删除");
			return json.toString();
		}

		goodsService.deleteGoods(goods);

		json.put("status", 1);
		json.put("msg", "删除成功");
		return json.toString();
	}

	@POST
	@Path("findGoodsById.do")
	@Produces(MediaType.APPLICATION_JSON)
	public String findGoodsById(@FormParam("apiKey") String apiKey,
			@FormParam("myId") Integer myId,
			@FormParam("goodsId") Integer goodsId) {

		User me = userService.findById(myId);
		if (null == me) {
			json.put("status", -1);
			json.put("msg", "操作失败,参数异常");
			return json.toString();
		}

		if (!apiKey.equals(me.getApiKey()))// 检测APIKEY是否相等
		{
			json.put("status", 0);
			json.put("msg", "您的apiKey已过期,您的账户可能被别人登陆，请修改密码或重新登陆");
			return json.toString();
		}

		Goods goods = goodsService.findById(goodsId);

		if (null == goods) {

			json.put("status", -1);
			json.put("msg", "操作失败,参数异常");
			return json.toString();
		}

		json = goods.toSimpleJSON();
		json.put("status", 1);
		json.put("msg", "查找成功");
		json.put("goods", goods.toSimpleJSON());
		return json.toString();

	}

	@POST
	@Path("findGoodsByUser.do")
	@Produces(MediaType.APPLICATION_JSON)
	public String findGoodsByUser(@FormParam("apiKey") String apiKey,
			@FormParam("myId") Integer myId,
			@FormParam("userId") Integer userId,
			@FormParam("pageIndex") Integer pageIndex,
			@FormParam("pageSize") Integer pageSize,
			@FormParam("sortStyle") Integer sortStyle) {

		User me = userService.findById(myId);
		if (null == me) {
			json.put("status", -1);
			json.put("msg", "操作失败,参数异常");
			return json.toString();
		}

		if (!apiKey.equals(me.getApiKey()))// 检测APIKEY是否相等
		{
			json.put("status", 0);
			json.put("msg", "您的apiKey已过期,您的账户可能被别人登陆，请修改密码或重新登陆");
			return json.toString();
		}

		User user = userService.findById(userId);

		if (null == user) {
			json.put("status", -1);
			json.put("msg", "操作失败,参数异常");
			return json.toString();
		}

		List<Goods> list = goodsService.findByUser(user, pageIndex, pageSize);

		if (null == list || list.isEmpty()) {

			json.put("status", -2);
			json.put("msg", "该用户没有相关物品");
			return json.toString();
		}

		JSONArray array = new JSONArray();
		for (Goods g : list) {

			array.add(g.toSimpleJSON());
		}

		json.put("status", 1);
		json.put("msg", "查找成功");
		json.put("goodsList", array);

		return json.toString();

	}

	@POST
	@Path("findGoodsByCategory.do")
	@Produces(MediaType.APPLICATION_JSON)
	public String findGoodsByCategory(@FormParam("apiKey") String apiKey,
			@FormParam("myId") Integer myId,
			@FormParam("categoryId") Integer categoryId,
			@FormParam("pageIndex") Integer pageIndex,
			@FormParam("pageSize") Integer pageSize,
			@FormParam("sortStyle") Integer sortStyle) {

		User me = userService.findById(myId);
		if (null == me) {
			json.put("status", -1);
			json.put("msg", "操作失败,参数异常");
			return json.toString();
		}

		if (!apiKey.equals(me.getApiKey()))// 检测APIKEY是否相等
		{
			json.put("status", 0);
			json.put("msg", "您的apiKey已过期,您的账户可能被别人登陆，请修改密码或重新登陆");
			return json.toString();
		}

		Category category = categoryService.findById(categoryId);

		if (null == category) {
			json.put("status", -1);
			json.put("msg", "操作失败,参数异常");
			return json.toString();
		}

		List<Goods> list = goodsService.findByCategory(category, pageIndex,
				pageSize);

		if (null == list || list.isEmpty()) {

			json.put("status", -2);
			json.put("msg", "该分类没有相关物品");
			return json.toString();
		}

		JSONArray array = new JSONArray();
		for (Goods g : list) {

			array.add(g.toSimpleJSON());
		}

		json.put("status", 1);
		json.put("msg", "查找成功");
		json.put("goodsList", array);

		return json.toString();

	}

	@POST
	@Path("findGoodsByName.do")
	@Produces(MediaType.APPLICATION_JSON)
	public String findGoodsByName(@FormParam("apiKey") String apiKey,
			@FormParam("myId") Integer myId,
			@FormParam("goodsName") String goodsName,
			@FormParam("pageIndex") Integer pageIndex,
			@FormParam("pageSize") Integer pageSize,
			@FormParam("sortStyle") Integer sortStyle) {

		User me = userService.findById(myId);
		if (null == me) {
			json.put("status", -1);
			json.put("msg", "操作失败,参数异常");
			return json.toString();
		}

		if (!apiKey.equals(me.getApiKey()))// 检测APIKEY是否相等
		{
			json.put("status", 0);
			json.put("msg", "您的apiKey已过期,您的账户可能被别人登陆，请修改密码或重新登陆");
			return json.toString();
		}

		List<Goods> list = goodsService.findByName(goodsName, pageIndex,
				pageSize);

		if (null == list || list.isEmpty()) {

			json.put("status", -2);
			json.put("msg", "没有相关物品");
			return json.toString();
		}

		JSONArray array = new JSONArray();
		for (Goods g : list) {

			array.add(g.toSimpleJSON());
		}

		json.put("status", 1);
		json.put("msg", "查找成功");
		json.put("goodsList", array);

		return json.toString();

	}

	@POST
	@Path("modifyGoodsName.do")
	@Produces(MediaType.APPLICATION_JSON)
	public String modifyGoodsName(@FormParam("apiKey") String apiKey,
			@FormParam("myId") Integer myId,
			@FormParam("goodsId") Integer goodsId,
			@FormParam("goodsName") String goodsName) {

		User me = userService.findById(myId);
		if (null == me) {
			json.put("status", -1);
			json.put("msg", "操作失败,参数异常");
			return json.toString();
		}

		if (!apiKey.equals(me.getApiKey()))// 检测APIKEY是否相等
		{
			json.put("status", 0);
			json.put("msg", "您的apiKey已过期,您的账户可能被别人登陆，请修改密码或重新登陆");
			return json.toString();
		}

		Goods goods = goodsService.findById(goodsId);
		
		if(null == goods || StringUtil.isNullOrBlank(goodsName)){
			json.put("status", -1);
			json.put("msg", "操作失败,参数异常");
			return json.toString();
		}
		
		goodsService.modifyName(goods, goodsName);

		json.put("status", 1);
		json.put("msg", "修改成功");
		return json.toString();
	}

	@POST
	@Path("modifyGoodsCategory.do")
	@Produces(MediaType.APPLICATION_JSON)
	public String modifyGoodsCategory(@FormParam("apiKey") String apiKey,
			@FormParam("myId") Integer myId,
			@FormParam("goodsId") Integer goodsId,
			@FormParam("goodsCategoryId") Integer goodsCategoryId) {

		User me = userService.findById(myId);
		if (null == me) {
			json.put("status", -1);
			json.put("msg", "操作失败,参数异常");
			return json.toString();
		}

		if (!apiKey.equals(me.getApiKey()))// 检测APIKEY是否相等
		{
			json.put("status", 0);
			json.put("msg", "您的apiKey已过期,您的账户可能被别人登陆，请修改密码或重新登陆");
			return json.toString();
		}

		Goods goods = goodsService.findById(goodsId);
		Category cate = categoryService.findById(goodsCategoryId);
		
		if(null == goods || null == cate){
			json.put("status", -1);
			json.put("msg", "操作失败,参数异常");
			return json.toString();
		}
		
		goodsService.modifyCategory(goods, cate);

		json.put("status", 1);
		json.put("msg", "修改成功");
		return json.toString();
	}

	@POST
	@Path("modifyGoodsImagePath.do")
	@Produces(MediaType.APPLICATION_JSON)
	public String modifyGoodsImagePath(@FormParam("apiKey") String apiKey,
			@FormParam("myId") Integer myId,
			@FormParam("goodsId") Integer goodsId,
			@FormParam("goodsImagePath") String goodsImagePath) {

		return null;
	}

	@POST
	@Path("modifyGoodsAddr.do")
	@Produces(MediaType.APPLICATION_JSON)
	public String modifyGoodsAddr(@FormParam("apiKey") String apiKey,
			@FormParam("myId") Integer myId,
			@FormParam("goodsId") Integer goodsId,
			@FormParam("goodsProvinceId") Integer goodsProvinceId,
			@FormParam("goodsCityId") Integer goodsCityId) {

		User me = userService.findById(myId);
		if (null == me) {
			json.put("status", -1);
			json.put("msg", "操作失败,参数异常");
			return json.toString();
		}

		if (!apiKey.equals(me.getApiKey()))// 检测APIKEY是否相等
		{
			json.put("status", 0);
			json.put("msg", "您的apiKey已过期,您的账户可能被别人登陆，请修改密码或重新登陆");
			return json.toString();
		}

		Goods goods = goodsService.findById(goodsId);
		Province province = provinceService.findById(goodsProvinceId);
		City city = cityService.findById(goodsCityId);
		
		if (null == goods || null == province || null == city) {
			json.put("status", -1);
			json.put("msg", "操作失败,参数异常");
			return json.toString();
		}
		
		goodsService.modifyAddr(goods, province, city);

		json.put("status", 1);
		json.put("msg", "修改成功");
		return json.toString();
	}

	@POST
	@Path("modifyGoods.do")
	@Produces(MediaType.APPLICATION_JSON)
	public String modifyGoodsWeight(@FormParam("apiKey") String apiKey,
			@FormParam("myId") Integer myId,
			@FormParam("goodsId") Integer goodsId,
			@FormParam("goodsWeight") Integer goodsWeight) {

		return null;
	}

	@POST
	@Path("modifyGoodsPostageType.do")
	@Produces(MediaType.APPLICATION_JSON)
	public String modifyGoodsPostageType(@FormParam("apiKey") String apiKey,
			@FormParam("myId") Integer myId,
			@FormParam("goodsId") Integer goodsId,
			@FormParam("goodsPostageType") Integer goodsPostageType) {

		User me = userService.findById(myId);
		if (null == me) {
			json.put("status", -1);
			json.put("msg", "操作失败,参数异常");
			return json.toString();
		}

		if (!apiKey.equals(me.getApiKey()))// 检测APIKEY是否相等
		{
			json.put("status", 0);
			json.put("msg", "您的apiKey已过期,您的账户可能被别人登陆，请修改密码或重新登陆");
			return json.toString();
		}

		Goods goods = goodsService.findById(goodsId);
		
		if(null == goods || null == goodsPostageType){
			json.put("status", -1);
			json.put("msg", "操作失败,参数异常");
			return json.toString();
		}
		
		goodsService.modifyPostageType(goods, goodsPostageType);

		json.put("status", 1);
		json.put("msg", "修改成功");
		return json.toString();
	}

	@POST
	@Path("modifyGoodsTag.do")
	@Produces(MediaType.APPLICATION_JSON)
	public String modifyGoodsTag(@FormParam("apiKey") String apiKey,
			@FormParam("myId") Integer myId,
			@FormParam("goodsId") Integer goodsId,
			@FormParam("goodsTag") String tag) {

		User me = userService.findById(myId);
		if (null == me) {
			json.put("status", -1);
			json.put("msg", "操作失败,参数异常");
			return json.toString();
		}

		if (!apiKey.equals(me.getApiKey()))// 检测APIKEY是否相等
		{
			json.put("status", 0);
			json.put("msg", "您的apiKey已过期,您的账户可能被别人登陆，请修改密码或重新登陆");
			return json.toString();
		}

		Goods goods = goodsService.findById(goodsId);
		
		if(null == goods){
			json.put("status", -1);
			json.put("msg", "操作失败,参数异常");
			return json.toString();
		}
		
		goodsService.modifyTag(goods, tag);

		json.put("status", 1);
		json.put("msg", "修改成功");
		return json.toString();
	}

	@POST
	@Path("modifyGoodsDescription.do")
	@Produces(MediaType.APPLICATION_JSON)
	public String modifyGoodsDescription(@FormParam("apiKey") String apiKey,
			@FormParam("myId") Integer myId,
			@FormParam("goodsId") Integer goodsId,
			@FormParam("description") String description) {

		User me = userService.findById(myId);
		if (null == me) {
			json.put("status", -1);
			json.put("msg", "操作失败,参数异常");
			return json.toString();
		}

		if (!apiKey.equals(me.getApiKey()))// 检测APIKEY是否相等
		{
			json.put("status", 0);
			json.put("msg", "您的apiKey已过期,您的账户可能被别人登陆，请修改密码或重新登陆");
			return json.toString();
		}

		Goods goods = goodsService.findById(goodsId);
		
		if(null == goods){
			json.put("status", -1);
			json.put("msg", "操作失败,参数异常");
			return json.toString();
		}
		
		goodsService.modifyDecription(goods, description);

		json.put("status", 1);
		json.put("msg", "修改成功");
		return json.toString();
	}

	@POST
	@Path("getAllSendNotification.do")
	@Produces(MediaType.APPLICATION_JSON)
	public String getAllSendNotification(@FormParam("apiKey") String apiKey,
			@FormParam("myId") Integer myId,
			@FormParam("pageIndex") Integer pageIndex,
			@FormParam("pageSize") Integer pageSize) {

		User me = userService.findById(myId);
		if (null == me) {
			json.put("status", -1);
			json.put("msg", "操作失败,参数异常");
			return json.toString();
		}

		if (!apiKey.equals(me.getApiKey()))// 检测APIKEY是否相等
		{
			json.put("status", 0);
			json.put("msg", "您的apiKey已过期,您的账户可能被别人登陆，请修改密码或重新登陆");
			return json.toString();
		}

		List<OrderSendNotify> list = orderSendNotifyService.findAllNoti(me,
				pageIndex, pageSize);
		// 在这返回JSON对象

		if (null == list || list.isEmpty()) {

			json.put("status", -2);
			json.put("msg", "暂没有相关通知");
			return json.toString();
		}

		JSONArray array = new JSONArray();

		for (OrderSendNotify noti : list) {

			array.add(noti.toSimpleJSON());
		}

		json.put("status", 1);
		json.put("msg", "查询成功");
		json.put("notifyList", array);// 把LIST转成JSON对象
		return json.toString();
	}

	@POST
	@Path("getSendNotificationDetail.do")
	@Produces(MediaType.APPLICATION_JSON)
	public String getSendNotificationDetail(@FormParam("apiKey") String apiKey,
			@FormParam("myId") Integer myId,
			@FormParam("notifyId") Integer notifyId) {

		User me = userService.findById(myId);
		if (null == me) {
			json.put("status", -1);
			json.put("msg", "操作失败,参数异常");
			return json.toString();
		}

		if (!apiKey.equals(me.getApiKey()))// 检测APIKEY是否相等
		{
			json.put("status", 0);
			json.put("msg", "您的apiKey已过期,您的账户可能被别人登陆，请修改密码或重新登陆");
			return json.toString();
		}

	
		OrderSendNotify noti = orderSendNotifyService.findById(notifyId);

		
		if(null == noti){
			
			json.put("status", -1);
			json.put("msg", "操作失败,参数异常");
			return json.toString();
		}
		
		// 在这返回JSON对象

		json.put("status", 1);
		json.put("msg", "查询成功");
		json.put("notify", noti.toSimpleJSON());

		return json.toString();
	}

	@POST
	@Path("getAllRecvNotification.do")
	@Produces(MediaType.APPLICATION_JSON)
	public String getAllRecvNotification(@FormParam("apiKey") String apiKey,
			@FormParam("myId") Integer myId,
			@FormParam("pageIndex") Integer pageIndex,
			@FormParam("pageSize") Integer pageSize) {

		User me = userService.findById(myId);
		if (null == me) {
			json.put("status", -1);
			json.put("msg", "操作失败,参数异常");
			return json.toString();
		}

		if (!apiKey.equals(me.getApiKey()))// 检测APIKEY是否相等
		{
			json.put("status", 0);
			json.put("msg", "您的apiKey已过期,您的账户可能被别人登陆，请修改密码或重新登陆");
			return json.toString();
		}

		List<OrderRecvNotify> list = orderRecvNotifyService.findAllNoti(me,
				pageIndex, pageSize);
		// 在这返回JSON对象 待写

		if (null == list || list.isEmpty()) {

			json.put("status", -2);
			json.put("msg", "暂没有相关通知");
			return json.toString();
		}

		JSONArray array = new JSONArray();

		for (OrderRecvNotify noti : list) {

			array.add(noti.toSimpleJSON());
		}

		json.put("status", 1);
		json.put("msg", "查询成功");
		json.put("notifyList", array);// 把LIST转成JSON对象
		return json.toString();
	}

	@POST
	@Path("getRecvNotificationDetail.do")
	@Produces(MediaType.APPLICATION_JSON)
	public String getRecvNotificationDetail(@FormParam("apiKey") String apiKey,
			@FormParam("myId") Integer myId,
			@FormParam("notifyId") Integer notifyId) {

		User me = userService.findById(myId);
		if (null == me) {
			json.put("status", -1);
			json.put("msg", "操作失败,参数异常");
			return json.toString();
		}

		if (!apiKey.equals(me.getApiKey()))// 检测APIKEY是否相等
		{
			json.put("status", 0);
			json.put("msg", "您的apiKey已过期,您的账户可能被别人登陆，请修改密码或重新登陆");
			return json.toString();
		}

		OrderRecvNotify noti = orderRecvNotifyService.findById(notifyId);

		
		if(null == noti){
			
			json.put("status", -1);
			json.put("msg", "操作失败,参数异常");
			return json.toString();
		}
		
		// 在这返回JSON对象 待写

		json.put("status", 1);
		json.put("msg", "查询成功");
		json.put("notify", noti.toSimpleJSON());

		return json.toString();
	}

	@POST
	@Path("submitOrder.do")
	@Produces(MediaType.APPLICATION_JSON)
	public String submitOrder(@FormParam("apiKey") String apiKey,
			@FormParam("myId") Integer myId,
			@FormParam("orderId") Integer orderId,
			@FormParam("notifyId") Integer notifyId,
			@FormParam("provinceId") Integer provinceId,
			@FormParam("cityId") Integer cityId,
			@FormParam("districtId") Integer districtId,
			@FormParam("personName") String personName,
			@FormParam("telphone") String telphone,
			@FormParam("detailAddr") String detailAddr) {

		User me = userService.findById(myId);
		if (null == me) {
			json.put("status", -1);
			json.put("msg", "操作失败,参数异常");
			return json.toString();
		}

		if (!apiKey.equals(me.getApiKey()))// 检测APIKEY是否相等
		{
			json.put("status", 0);
			json.put("msg", "您的apiKey已过期,您的账户可能被别人登陆，请修改密码或重新登陆");
			return json.toString();
		}

		OrderRecvNotify noti = orderRecvNotifyService.findById(notifyId);
		Province province = provinceService.findById(provinceId);
		City city = cityService.findById(cityId);
		District district = districtService.findById(districtId);

		if (null == noti || null == province || null == city || null == district) {
			json.put("status", -1);
			json.put("msg", "操作失败,参数异常");
			return json.toString();
		}
		
		noti.setRecvProvince(province);
		noti.setRecvCity(city);
		noti.setRecvDistrict(district);
		noti.setRecvContact(telphone);
		noti.setRecvPerson(personName);
		noti.setRecvDetailAddr(detailAddr);

		orderRecvNotifyService.updateOrderRecvNotify(noti);

		Province goodsProvince = noti.getOrder().getGoods().getProvince();
		Double postage = ComputePostage.getPostage(goodsProvince, province);
		// 计算运费并返回,

		json.put("status", 1);
		json.put("msg", "提交成功");
		json.put("postage", postage);
		return json.toString();
	}

	@POST
	@Path("setUserPayPostage.do")
	@Produces(MediaType.APPLICATION_JSON)
	public String setUserPayPostage(@FormParam("apiKey") String apiKey,
			@FormParam("myId") Integer myId,
			@FormParam("orderId") Integer orderId,
			@FormParam("recvNotifyId") Integer recvNotifyId,
			@FormParam("postage") Double postage) {

		User me = userService.findById(myId);
		if (null == me) {
			json.put("status", -1);
			json.put("msg", "操作失败,参数异常");
			return json.toString();
		}

		if (!apiKey.equals(me.getApiKey()))// 检测APIKEY是否相等
		{
			json.put("status", 0);
			json.put("msg", "您的apiKey已过期,您的账户可能被别人登陆，请修改密码或重新登陆");
			return json.toString();
		}

		OrderRecvNotify noti = orderRecvNotifyService.findById(recvNotifyId);
		
		if (null == noti) {
			json.put("status", -1);
			json.put("msg", "操作失败,参数异常");
			return json.toString();
		}
		
		Order order = noti.getOrder();

		noti.setIfPay(1);
		noti.setPayTime(new Date());
		orderRecvNotifyService.updateOrderRecvNotify(noti);

		// 付款完成后,产生发货通知
		User sender = order.getGoods().getOwner();
		OrderSendNotify orderSendNotify = new OrderSendNotify(order, sender);
		orderSendNotifyService.insert(orderSendNotify);

		json.put("status", 1);
		json.put("msg", "付款成功");
		return json.toString();
	}

	@POST
	@Path("deliveryGoods.do")
	@Produces(MediaType.APPLICATION_JSON)
	public String deliveryGoods(@FormParam("apiKey") String apiKey,
			@FormParam("myId") Integer myId,
			@FormParam("orderId") Integer orderId,
			@FormParam("notifyId") Integer notifyId,
			@FormParam("provinceId") Integer provinceId,
			@FormParam("cityId") Integer cityId,
			@FormParam("districtId") Integer districtId,
			@FormParam("personName") String personName,
			@FormParam("telphone") String telphone,
			@FormParam("detailAddr") String detailAddr,
			@FormParam("expressCorpId") Integer expressCorpId) {

		User me = userService.findById(myId);
		if (null == me) {
			json.put("status", -1);
			json.put("msg", "操作失败,参数异常");
			return json.toString();
		}

		if (!apiKey.equals(me.getApiKey()))// 检测APIKEY是否相等
		{
			json.put("status", 0);
			json.put("msg", "您的apiKey已过期,您的账户可能被别人登陆，请修改密码或重新登陆");
			return json.toString();
		}

		OrderSendNotify noti = orderSendNotifyService.findById(notifyId);
		Province province = provinceService.findById(provinceId);
		City city = cityService.findById(cityId);
		District district = districtService.findById(districtId);
		ExpressCorp expressCorp = expressCorpService.findById(expressCorpId);
		
		if(null == noti || null == province || null == city || null == district || null == expressCorpService){
			
			json.put("status", -1);
			json.put("msg", "操作失败,参数异常");
			return json.toString();
		}
		
		Order order = noti.getOrder();
		
		noti.setProvince(province);
		noti.setCity(city);
		noti.setDistrict(district);
		noti.setRecvContact(telphone);
		noti.setRecvPerson(personName);
		noti.setDetailAddr(detailAddr);
		noti.setExpressCorp(expressCorp);

		noti.setIfDeliver(1);
		noti.setDeliverTime(new Date());
		orderSendNotifyService.updateOrderSendNotify(noti);

		// 给相应快递公司发信息

		String takeGoodsAddr = province.getProvinceName() + city.getCityName()
				+ district.getDistName() + " " + detailAddr + " " + personName
				+ " " + telphone;
		String recvAddr = orderRecvNotifyService.GetRecvAddr(order);
		String detail = expressCorpService.SendMessage(expressCorp, recvAddr,
				takeGoodsAddr);

		DeliveryLog deliveryLog = new DeliveryLog(expressCorp, order, detail);
		deliveryLogService.insertDeliveryLog(deliveryLog);

		json.put("status", 1);
		json.put("msg", "发送成功,请等待快递公司上门取货");
		return json.toString();
	}

	@POST
	@Path("setAlreadyReceived.do")
	@Produces(MediaType.APPLICATION_JSON)
	public String setAlreadyReceived(
			@FormParam("orderNumber") String orderNumber,
			@FormParam("waybillNumber") String waybillNumber,
			@FormParam("postman") String postman,
			@FormParam("expressCorp") String expressCorp) {

		// 设置订单状态为成功
		// 计算指数
		Order order = orderService.findByOrderNumber(orderNumber);

		Goods goods = order.getGoods();

		User sender = goods.getOwner();

		EvaluateIndex index1 = evaluateIndexService.findById(EINDEX_CHARM_ID);
		EvaluateIndex index2 = evaluateIndexService
				.findById(EINDEX_INFLUENCE_ID);
		EvaluateIndex index3 = evaluateIndexService
				.findById(EINDEX_COMPREHENSIVE_ID);

		UserEvaluateIndex charmIndex = userEvaluateIndexService
				.findByUserAndIndex(sender, index1);
		UserEvaluateIndex influenceIndex = userEvaluateIndexService
				.findByUserAndIndex(sender, index2);
		UserEvaluateIndex comprehensiveIndex = userEvaluateIndexService
				.findByUserAndIndex(sender, index3);

		Integer likeNum = goodsUserLikeService.findyLikeUserNumByGoods(goods);
		Integer wantNum = goodsUserWantService.findyWantedUserNumByGoods(goods);

		charmIndex.setScore(charmIndex.getScore() + likeNum
				* EINDEX_LIKE_ADDVALUE + wantNum * EINDEX_WANT_ADDVALUE);
		influenceIndex.setScore(influenceIndex.getScore()
				+ EINDEX_FINISH_BARGAIN_ADDVALUE);
		comprehensiveIndex.setScore(comprehensiveIndex.getScore()
				+ charmIndex.getScore() + influenceIndex.getScore());

		userEvaluateIndexService.updateUserEvaluateIndex(charmIndex);
		userEvaluateIndexService.updateUserEvaluateIndex(influenceIndex);
		userEvaluateIndexService.updateUserEvaluateIndex(comprehensiveIndex);

		order.setStatus(1);
		order.setStatusDesc("已成功送出宝贝");
		orderService.update(order);

		// 同步两个动态

		json.put("status", 1);
		json.put("msg", "发送成功");
		return json.toString();

	}
}
