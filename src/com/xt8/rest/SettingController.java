/**
 * author: xrc
 * time  : 2014-11-14
 * function: restful webservice related to user setting
 */
package com.xt8.rest;

import java.util.List;

import javax.annotation.Resource;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.xt8.model.BlackList;
import com.xt8.model.City;
import com.xt8.model.ConsumeRecord;
import com.xt8.model.District;
import com.xt8.model.MoodAuthorization;
import com.xt8.model.Province;
import com.xt8.model.RechargeRecord;
import com.xt8.model.RemarkFriendName;
import com.xt8.model.Setting;
import com.xt8.model.User;
import com.xt8.model.UserAdvice;
import com.xt8.model.UserCommAddr;
import com.xt8.model.UserLimitedForWantGoods;
import com.xt8.model.UserSetting;
import com.xt8.service.BlackListService;
import com.xt8.service.CityService;
import com.xt8.service.ConsumeRecordService;
import com.xt8.service.DistrictService;
import com.xt8.service.MoodAuthorizationService;
import com.xt8.service.ProvinceService;
import com.xt8.service.RechargeRecordService;
import com.xt8.service.RemarkFriendNameService;
import com.xt8.service.SettingService;
import com.xt8.service.UserAdviceService;
import com.xt8.service.UserCommAddrService;
import com.xt8.service.UserLimitedForWantGoodsService;
import com.xt8.service.UserService;
import com.xt8.service.UserSettingService;
import com.xt8.util.Constants;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Path("/setting")
public class SettingController {
	
	@Resource
	private UserAdviceService userAdviceService;
	@Resource
	private UserService userService;
	@Resource
	private UserLimitedForWantGoodsService userLimitedForWantGoodsService;
	@Resource
	private SettingService settingService;
	@Resource
	private UserSettingService userSettingService;
	@Resource
	private ProvinceService provinceService;
	@Resource
	private CityService cityService;
	@Resource
	private DistrictService districtService;
	@Resource
	private UserCommAddrService userCommAddrService;
	@Resource
	private RechargeRecordService rechargeRecordService;
	@Resource
	private ConsumeRecordService consumeRecordService;
	@Resource
	private RemarkFriendNameService remarkFriendNameService;
	@Resource
	private BlackListService blackListService;
	@Resource
	private MoodAuthorizationService moodAuthorizationService;
	
	
	private JSONObject json = new JSONObject();

	/**
	 * 
	 * @param apiKey
	 * @param myId    
	 * @param content   建议的内容
	 * @param contact  
	 * @return
	 * 
	 * 要操作的表是:UserAdvice
	 */
	@POST
	@Path("submitAdvice.do")
	@Produces(MediaType.APPLICATION_JSON)
	public String submitAdvice(@FormParam("apiKey") String apiKey,
			@FormParam("myId") Integer myId,
			@FormParam("content") String content,
			@FormParam("contact") String contact){
		
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
		
		UserAdvice userAdvice = new UserAdvice(me,contact,content);
		userAdviceService.insertUserAdvice(userAdvice);
		
		json.put("status", 1);
		json.put("msg", "提交成功,感谢您的反馈");
		return json.toString();
		
	}
	

	  
	@POST
	@Path("setAccountGuard.do")
	@Produces(MediaType.APPLICATION_JSON)
	public String setAccountGuard(@FormParam("apiKey") String apiKey,
			@FormParam("myId") Integer myId,
			@FormParam("flag") Integer flag){
		
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
		
		if(null == flag)
		{
			json.put("status", -1);
			json.put("msg", "操作失败,参数异常");
			return json.toString();
		}
		
		
		Setting setting = settingService.findById(Constants.SETTING_ACCOUNT_GUARD_ID);
		UserSetting userSetting = userSettingService.findByUserAndSetting(me, setting);
		
		userSetting.setValue(flag);
		
		userSettingService.update(userSetting);
		
		json.put("status", 1);
		json.put("msg", "设置成功");
		return json.toString();
	}
	
	@POST
	@Path("setShake.do")
	@Produces(MediaType.APPLICATION_JSON)
	public String setShake(@FormParam("apiKey") String apiKey,
			@FormParam("myId") Integer myId,
			@FormParam("flag") Integer flag){
		
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
		
		if(null == flag)
		{
			json.put("status", -1);
			json.put("msg", "操作失败,参数异常");
			return json.toString();
		}
		
		
		Setting setting = settingService.findById(Constants.SETTING_SHAKE_ID);
		UserSetting userSetting = userSettingService.findByUserAndSetting(me, setting);
		
		userSetting.setValue(flag);
		
		userSettingService.update(userSetting);
		
		json.put("status", 1);
		json.put("msg", "设置成功");
		return json.toString();
		
	}
	
	@POST
	@Path("setVoice.do")
	@Produces(MediaType.APPLICATION_JSON)
	public String setVoice(@FormParam("apiKey") String apiKey,
			@FormParam("myId") Integer myId,
			@FormParam("flag") Integer flag){
		
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
		
		if(null == flag)
		{
			json.put("status", -1);
			json.put("msg", "操作失败,参数异常");
			return json.toString();
		}
		
		
		Setting setting = settingService.findById(Constants.SETTING_VOICE_ID);
		UserSetting userSetting = userSettingService.findByUserAndSetting(me, setting);
		
		userSetting.setValue(flag);
		
		userSettingService.update(userSetting);
		
		json.put("status", 1);
		json.put("msg", "设置成功");
		return json.toString();
		
	}
	
	@POST
	@Path("setVerifyFriend.do")
	@Produces(MediaType.APPLICATION_JSON)
	public String setVerifyFriend(@FormParam("apiKey") String apiKey,
			@FormParam("myId") Integer myId,
			@FormParam("flag") Integer flag){
		
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
		
		if(null == flag)
		{
			json.put("status", -1);
			json.put("msg", "操作失败,参数异常");
			return json.toString();
		}
		
		
		Setting setting = settingService.findById(Constants.SETTING_VERIFY_FRIEND_ID);
		UserSetting userSetting = userSettingService.findByUserAndSetting(me, setting);
		
		userSetting.setValue(flag);
		
		userSettingService.update(userSetting);
		
		json.put("status", 1);
		json.put("msg", "设置成功");
		return json.toString();
		
	}
	
	@POST
	@Path("setFriendAcception.do")
	@Produces(MediaType.APPLICATION_JSON)
	public String setFriendAcception(@FormParam("apiKey") String apiKey,
			@FormParam("myId") Integer myId,
			@FormParam("flag") Integer flag){
		
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
		
		if(null == flag)
		{
			json.put("status", -1);
			json.put("msg", "操作失败,参数异常");
			return json.toString();
		}
		
		
		Setting setting = settingService.findById(Constants.SETTING_FRIENDACCEPTION_ID);
		UserSetting userSetting = userSettingService.findByUserAndSetting(me, setting);
		
		userSetting.setValue(flag);
		
		userSettingService.update(userSetting);
		
		json.put("status", 1);
		json.put("msg", "设置成功");
		return json.toString();
		
	}
	
	@POST
	@Path("setIfReceiveMsg.do")
	@Produces(MediaType.APPLICATION_JSON)
	public String setIfReceiveMsg(@FormParam("apiKey") String apiKey,
			@FormParam("myId") Integer myId,
			@FormParam("flag") Integer flag){
		
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
		
		if(null == flag)
		{
			json.put("status", -1);
			json.put("msg", "操作失败,参数异常");
			return json.toString();
		}
		
		
		Setting setting = settingService.findById(Constants.SETTING_RECEIVE_GOODS_ID);
		UserSetting userSetting = userSettingService.findByUserAndSetting(me, setting);
		
		userSetting.setValue(flag);
		
		userSettingService.update(userSetting);
		
		json.put("status", 1);
		json.put("msg", "设置成功");
		return json.toString();
		
	}
	
	@POST
	@Path("addCommAddr.do")
	@Produces(MediaType.APPLICATION_JSON)
	public String addCommAddr(@FormParam("apiKey") String apiKey,
			@FormParam("myId") Integer myId,
			@FormParam("name") String name,
			@FormParam("provinceId") Integer provinceId,
			@FormParam("cityId") Integer cityId,
			@FormParam("districtId") Integer districtId,
			@FormParam("detailAddr") String detailAddr,
			@FormParam("telphone") String telphone){
		
		
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
		
		Province province = provinceService.findById(provinceId);
		City city = cityService.findById(cityId);
		District district = districtService.findById(districtId);
		
		if(null == province || null == city || null == district){
			json.put("status", -1);
			json.put("msg", "操作失败,参数异常");
			return json.toString();
		}
		
		UserCommAddr userCommAddr = new UserCommAddr(me,name,province,city,district,detailAddr,telphone);
		userCommAddrService.insertUserCommAddr(userCommAddr);
		
		
		json.put("status", 1);
		json.put("msg", "添加地址成功");
		return json.toString();
	}
	
	@POST
	@Path("deleteCommAddr.do")
	@Produces(MediaType.APPLICATION_JSON)
	public String deleteCommAddr(@FormParam("apiKey") String apiKey,
			@FormParam("myId") Integer myId,
			@FormParam("commAddrId") Integer commAddrId){
		
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
		
		UserCommAddr userCommAddr = userCommAddrService.findById(commAddrId);
		if(null == userCommAddr){
			
			json.put("status", -1);
			json.put("msg", "操作失败,参数异常");
			return json.toString();
		}
		userCommAddrService.deleteUserCommAddr(userCommAddr);
		
		json.put("status", 1);
		json.put("msg", "删除成功");
		return json.toString();
	}
	
	@POST
	@Path("getMyCommAddr.do")
	@Produces(MediaType.APPLICATION_JSON)
	public String getMyCommAddr(@FormParam("apiKey") String apiKey,
			@FormParam("myId") Integer myId){
		
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
		
		List<UserCommAddr> list = userCommAddrService.findByUser(me);
		
		if(null == list){
			
			json.put("status", -2);
			json.put("msg", "用户暂无地址");
			return json.toString();
		}
		
		 JSONArray array = new JSONArray();
		 
		 for(UserCommAddr addr : list){
			 array.add(addr.toSimpleJSON());
		 }
		 
			json.put("status", 1);
			json.put("msg", "查找成功");
			json.put("addressList", array);
			return json.toString();
		
	}
	
	@POST
	@Path("rechargeMoney.do")
	@Produces(MediaType.APPLICATION_JSON)
	public String rechargeMoney(@FormParam("apiKey") String apiKey,
			@FormParam("myId") Integer myId,
			@FormParam("paymentTypeName") String paymentTypeName,
			@FormParam("money") Double money){
		
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
		
		me.setBalance(me.getBalance()+money);
		userService.update(me);
		
		RechargeRecord rechargeRecord = new RechargeRecord(me,money,paymentTypeName);
		rechargeRecordService.insertRechargeRecord(rechargeRecord);
		
		
		json.put("status", 1);
		json.put("msg", "充值成功");
		return json.toString();
	}
	
	@POST
	@Path("consumeMoney.do")
	@Produces(MediaType.APPLICATION_JSON)
	public String consumeMoney(@FormParam("apiKey") String apiKey,
			@FormParam("myId") Integer myId,
			@FormParam("money") Double money,
			@FormParam("description") String description){
		
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
		
		if(me.getBalance() - money < 0){
			
			json.put("status", -2);
			json.put("msg", "余额不足");
			return json.toString();
		}
		
		
		me.setBalance(me.getBalance()-money);
		userService.update(me);
		
		ConsumeRecord consumeRecord = new ConsumeRecord(me,money,description);
		consumeRecordService.insertConsumeRecord(consumeRecord);
		
		
		json.put("status", 1);
		json.put("msg", "转出成功");
		return json.toString();
		
	}
	
	
	@POST
	@Path("remarkName.do")
	@Produces(MediaType.APPLICATION_JSON)
	public String remarkName(@FormParam("apiKey") String apiKey,
			@FormParam("myId") Integer myId,
			@FormParam("userId") Integer userId,
			@FormParam("newName") String newName){
		
		
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
		
		RemarkFriendName remarkFriendName = null;
		remarkFriendName = remarkFriendNameService.findByUser(me, user);
		
		if(null == remarkFriendName){
			remarkFriendName = new RemarkFriendName(me, user, newName);
			remarkFriendNameService.insertRemarkFriendName(remarkFriendName);
		}
		else{
			remarkFriendName.setRemarkName(newName);
			
			remarkFriendNameService.updateRemarkFriendName(remarkFriendName);
		}
		
		json.put("status", 1);
		json.put("msg", "修改成功");
		return json.toString();
			
	}
	
	@POST
	@Path("deleteRemarkName.do")
	@Produces(MediaType.APPLICATION_JSON)
	public String deleteRemarkName(@FormParam("apiKey") String apiKey,
			@FormParam("myId") Integer myId,
			@FormParam("userId") Integer userId){
		
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
		
		RemarkFriendName remarkFriendName = null;
		remarkFriendName = remarkFriendNameService.findByUser(me, user);
		
		if(null == remarkFriendName)
		{
			json.put("status", -2);
			json.put("msg", "您没有备注该用户");
			return json.toString();
		}
		remarkFriendNameService.deleteRemarkFriendName(remarkFriendName);
		
		json.put("status", 1);
		json.put("msg", "删除成功");
		return json.toString();
	}
	
	
	@POST
	@Path("setMoodAuthorization.do")
	@Produces(MediaType.APPLICATION_JSON)
	public String setMoodAuthorization(@FormParam("apiKey") String apiKey,
			@FormParam("myId") Integer myId,
			@FormParam("userId") Integer userId){
		
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
		
		MoodAuthorization moodAuthorization = null;
		moodAuthorization = moodAuthorizationService.findByTwoUser(me, user);
		
		if(null == moodAuthorization)
		{
			moodAuthorization = new MoodAuthorization(me,user);
			moodAuthorizationService.insertMoodAuthorization(moodAuthorization);
		}
		else
		{
			moodAuthorizationService.removeUserFromMoodAuthorization(moodAuthorization);
		}
	
		
		json.put("status", 1);
		json.put("msg", "设置成功");
		return json.toString();
	}
	

	@POST
	@Path("addPersonToBlackList.do")
	@Produces(MediaType.APPLICATION_JSON)
	public String addPersonToBlackList(@FormParam("apiKey") String apiKey,
			@FormParam("myId") Integer myId,
			@FormParam("userId") Integer userId){
		
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
		
		BlackList blackList = null;
		blackList = blackListService.findByTwoUser(me, user);
		
		if(null == blackList)
		{
			blackList = new BlackList(me,user);
			blackListService.insertBlackList(blackList);
		}
		else
		{
			blackListService.removeUserFromBlackList(blackList);
		}
	
		
		json.put("status", 1);
		json.put("msg", "设置成功");
		return json.toString();
	}
	
	@POST
	@Path("setUserLimitedForShare.do")
	@Produces(MediaType.APPLICATION_JSON)
	public String setUserLimitedForShare(@FormParam("apiKey") String apiKey,
			@FormParam("myId") Integer myId,
			@FormParam("userId") Integer userId){
		
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
		
		UserLimitedForWantGoods userLimitedForWantGoods = null;
		userLimitedForWantGoods = userLimitedForWantGoodsService.findyByHostuserAndLimiteduser(me, user);
		
		if(null == userLimitedForWantGoods)
		{
			userLimitedForWantGoods = new UserLimitedForWantGoods(me,user);
			userLimitedForWantGoodsService.insertUserLimitedForWantGoods(userLimitedForWantGoods);
		}
		else
		{
			userLimitedForWantGoodsService.deleteUserLimitedForWantGoods(userLimitedForWantGoods);
		}
	
		
		json.put("status", 1);
		json.put("msg", "设置成功");
		return json.toString();
	}
}
