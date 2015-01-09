package com.xt8.rest;

import static com.xt8.util.Constants.APIKEY;
import static com.xt8.util.Constants.MESSAGE;
import static com.xt8.util.Constants.STATUS;
import static com.xt8.util.Constants.TOKEN;
import static com.xt8.util.Constants.USERBALANCE;
import static com.xt8.util.Constants.USERDETAILINFO;
import static com.xt8.util.Constants.USEREVALUATEINDEX;
import static com.xt8.util.Constants.USERINFO;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import net.sf.json.JSONObject;

import com.xt8.model.City;
import com.xt8.model.EvaluateIndex;
import com.xt8.model.Image;
import com.xt8.model.Province;
import com.xt8.model.Setting;
import com.xt8.model.User;
import com.xt8.model.UserDetail;
import com.xt8.model.UserEvaluateIndex;
import com.xt8.model.UserSetting;
import com.xt8.service.CityService;
import com.xt8.service.EvaluateIndexService;
import com.xt8.service.ImageService;
import com.xt8.service.ProvinceService;
import com.xt8.service.SettingService;
import com.xt8.service.UserDetailService;
import com.xt8.service.UserEvaluateIndexService;
import com.xt8.service.UserService;
import com.xt8.service.UserSettingService;
import com.xt8.util.Blowfish;
import com.xt8.util.Common;
import com.xt8.util.Constants;
import com.xt8.util.ImageUtil;
import com.xt8.util.RongIM;
import com.xt8.util.StringUtil;

@Path("/user")
public class UserController {

	@Resource(name = "userService")
	private UserService userService;

	@Resource(name = "imageService")
	private ImageService imageService;

	@Resource(name = "userDetailService")
	private UserDetailService userDetailService;

	@Resource(name = "evaluateIndexService")
	private EvaluateIndexService evaluateIndexService;

	@Resource(name = "userEvaluateIndexService")
	private UserEvaluateIndexService userEvaluateIndexService;

	@Resource(name = "provinceService")
	private ProvinceService provinceService;

	@Resource(name = "cityService")
	private CityService cityService;

	@Resource(name = "settingService")
	private SettingService settingService;

	@Resource(name = "userSettingService")
	private UserSettingService userSettingService;

	private JSONObject json = new JSONObject();

	private static Blowfish xt8_hash_generator = new Blowfish("xt8_1.0");// 本机:SaYCP85RzR3oxf8

	@POST 
	@Path("login.do")
	@Produces(MediaType.APPLICATION_JSON)
	public String login(@FormParam("phone") String phone,
			@FormParam("password") String password,
			@FormParam("versionInfo") Integer versionInfo,
			@FormParam("deviceInfo") String deviceInfo,
			@FormParam("longtitude") Double longtitude,
			@FormParam("latitude") Double latitude,
			@Context HttpServletRequest request) {
		String[] strs = { phone, password };
		if (StringUtil.haveNullOrBlank(strs)) {// 提交参数没有无效值
			// 提交参数无效
			json.put(STATUS, -1);
			json.put(MESSAGE, "操作失败,参数异常");
			json.put(APIKEY, null);
			json.put(USERINFO, null);
			return json.toString();
		}

		User user = new User(phone, password, null, null);
		User quser = userService.checkUser(user);// 通过手机号检索用户
		if (null == quser) {
			// 没有查询到用户，说明该手机号尚未注册
			json.put(STATUS, -2);
			json.put(MESSAGE, "该手机号尚未注册用户");
			json.put(APIKEY, null);
			json.put(USERINFO, null);
			return json.toString();
		}

		if (!user.getPlainPassword().equals(quser.getPlainPassword())) {
			// 密码错误
			json.put(STATUS, -3);
			json.put(MESSAGE, "密码错误!");
			json.put(APIKEY, null);
			json.put(USERINFO, null);
			return json.toString();
		}

		// 密码验证通过
		String apiKey = xt8_hash_generator.encryptString(phone + password
				+ System.currentTimeMillis());
		quser.setApiKey(apiKey);
		userService.update(quser);
		json.put(STATUS, 1);
		json.put(MESSAGE, "登陆成功");
		json.put(APIKEY, apiKey);
		json.put(USERINFO, quser.toJSON4Login());
		return json.toString();
	}

	@POST
	@Path("register.do")
	@Produces(MediaType.APPLICATION_JSON)
	public String register(@FormParam("phone") String phone,
			@FormParam("password") String password,
			@FormParam("simpleWord") String simpleWord,
			@FormParam("nickName") String nickName,
			@Context HttpServletRequest request) {
		String[] strs = { phone, password, simpleWord };
		if (StringUtil.haveNullOrBlank(strs)) {
			// 提交参数无效
			json.put(STATUS, -1);
			json.put(MESSAGE, "操作失败,参数异常");
			json.put(APIKEY, null);
			json.put(USERINFO, null);
			return json.toString();
		}

		User user = new User(phone, password, nickName, simpleWord);
		User quser = userService.checkUser(user);// 通过手机号检索用户
		if (null != quser) {
			// 查询到用户，说明该手机号已被注册
			json.put(STATUS, -2);
			json.put(MESSAGE, "该手机号已被注册!");
			json.put(APIKEY, null);
			json.put(USERINFO, null);
			return json.toString();
		}

		// 没有查询到用户，说明该手机号尚未注册
		String apiKey = xt8_hash_generator.encryptString(phone + password
				+ System.currentTimeMillis());
		String heartbeatNumber = phone;// 心跳号有待生成
		user.setRegisterDate(new java.util.Date());
		user.setApiKey(apiKey);
		user.setHeartbeatNumber(heartbeatNumber);
		user = userService.insert(user);// 注册该用户
		UserDetail userDetail = new UserDetail(user, user.getRegisterDate(),
				null, null, null, null, null);
		userDetailService.save(userDetail);

		// 添加用户默认设置
		List<Setting> settingsList = settingService.findAll();
		for (Setting setting : settingsList) {
			UserSetting uSetting = new UserSetting(user, setting, 1);
			userSettingService.save(uSetting);
		}
		Setting shakeSetting = settingService
				.findById(Constants.SETTING_SHAKE_ID);
		UserSetting userShakeSetting = userSettingService.findByUserAndSetting(
				user, shakeSetting);
		userShakeSetting.setValue(0);
		userSettingService.update(userShakeSetting);

		// 添加用户默认指数
		List<EvaluateIndex> evaluateIndexList = evaluateIndexService
				.getEvaluateIndexList();
		for (EvaluateIndex evaluateIndex : evaluateIndexList) {
			UserEvaluateIndex uEvaluateIndex = new UserEvaluateIndex(user,
					evaluateIndex);
			userEvaluateIndexService.save(uEvaluateIndex);
		}

		json.put(STATUS, 1);
		json.put(MESSAGE, "注册成功");
		json.put(APIKEY, apiKey);
		json.put(USERINFO, user.toJSON4Register());
		return json.toString();
	}
	
	@POST
	@Path("getUserToken.do")
	@Produces(MediaType.APPLICATION_JSON)
	public String getUserToken(@FormParam("userId") Integer userId,
			@FormParam("apiKey") String apiKey,
			@Context HttpServletRequest request) {
		if (null == userId || StringUtil.isNullOrBlank(apiKey)) {
			json.put(STATUS, -2);
			json.put(MESSAGE, "参数无效");
			json.put(TOKEN, null);
			return json.toString();
		}

		User user = userService.findById(userId);
		if (null == user) {
			// 没有查询到用户，说明该手机号尚未注册
			json.put(STATUS, -1);
			json.put(MESSAGE, "用户不存在");
			json.put(TOKEN, null);
			return json.toString();
		}
		String token = RongIM.getToken("" + user.getUserId(),
				user.getNickName(), user.getAvatar());
		if (null == token) {
			json.put(STATUS, -3);
			json.put(MESSAGE, "获取token失败");
			json.put(TOKEN, null);
			return json.toString();
		}
		json.put(STATUS, 1);
		json.put(MESSAGE, "用户token");
		json.put(TOKEN, token);
		return json.toString();
	}

	@POST
	@Path("modifyUserInfo.do")
	@Produces(MediaType.APPLICATION_JSON)
	public String modifyUserInfo(@FormParam("myId") Integer myId,
			@FormParam("apiKey") String apiKey,
			@FormParam("nickName") String nickName,
			@FormParam("htNumber") String htNumber,
			@FormParam("sex") Integer sex,
			@FormParam("userAvatarPath") String userAvatarPath,
			@FormParam("nation") Integer nation,
			@FormParam("province") Integer province,
			@FormParam("city") Integer city,
			@FormParam("personalizedSignature") String personalizedSignature,
			@FormParam("personalizedSignature") Double longitude,
			@FormParam("personalizedSignature") Double latitude,
			@FormParam("description") String description,
			@Context HttpServletRequest request) {
		if (null == myId) {
			// 提交参数无效
			json.put(STATUS, -1);
			json.put(MESSAGE, "操作失败,参数异常");
			json.put(USERDETAILINFO, null);
			return json.toString();
		}

		User user = userService.findById(myId);// 通过主键检索用户
		if (null == user) {
			// 没有查询到用户，说明该手机号尚未注册
			json.put(STATUS, -1);
			json.put(MESSAGE, "操作失败,参数异常");
			json.put(USERDETAILINFO, null);
			return json.toString();
		}

		// 查询到用户，说明该手机号已注册
		String[] strs = { user.getApiKey(), apiKey };
		if (StringUtil.haveNullOrBlank(strs)
				|| !user.getApiKey().equals(apiKey)) {
			// apiKey过期
			json.put(STATUS, 0);
			json.put(MESSAGE, "您的apiKey已过期,您的账户可能被别人登陆，请修改密码或重新登陆");
			json.put(USERDETAILINFO, null);
			return json.toString();
		}

		// 验证通过
		Image image = ImageUtil.setImageNameTypeFromPath(userAvatarPath);
		image.setUser(user);
		image = imageService.insert(image);// 添加图片
		//user.setAvatar(image.getImageId());// 设置头像
		user.setAvatar(userAvatarPath);// 设置头像
		user.setNickName(nickName);
		user.setHeartbeatNumber(htNumber);// 设置心跳号码
		userService.update(user);// 更新用户基本信息
		UserDetail userDetail = userDetailService.findByUser(user);
		Integer objNation = nation;// 国家，待添加
		Province objProvince = null;
		if (null != province) {
			objProvince = provinceService.findById(province);// 省份，待添加
		}
		City objCity = null;
		if (null != city) {
			objCity = cityService.findById(city);// 城市，待添加
		}
		if (null == userDetail) {
			// 不存在，创建用户详细信息
			userDetail = new UserDetail(user, new Date(), longitude, latitude,
					objNation, objProvince, objCity);
			userDetail = userDetailService.insert(userDetail);
		} else {
			// 已经存在，更新用户详细信息
			userDetail.setUpdateTime(new Date());
			userDetail.setNation(objNation);
			userDetail.setProvince(objProvince);
			userDetail.setCity(objCity);
			userDetailService.update(userDetail);
		}
		json.put(STATUS, 1);
		json.put(MESSAGE, "更新信息成功");
		json.put(USERINFO, user.toJSON4Register());
		return json.toString();
	}

	@POST
	@Path("getUserDetailInfo.do")
	@Produces(MediaType.APPLICATION_JSON)
	public String getUserDetailInfo(@FormParam("myId") Integer myId,
			@FormParam("apiKey") String apiKey,
			@FormParam("userId") Integer userId,
			@Context HttpServletRequest request) {
		if (null == myId || null == userId || null == apiKey) {
			// 提交参数无效
			json.put(STATUS, -1);
			json.put(MESSAGE, "操作失败,参数异常");
			json.put(USERDETAILINFO, null);
			return json.toString();
		}
		User me = userService.findById(myId);
		User user = userService.findById(userId);// 通过主键检索用户
		if (null == user || null == me) {
			// 没有查询到用户，说明该手机号尚未注册
			json.put(STATUS, -1);
			json.put(MESSAGE, "操作失败,参数异常");
			json.put(USERDETAILINFO, null);
			return json.toString();
		}

		// 查询到用户，说明该手机号已注册
		String[] strs = { me.getApiKey(), apiKey };
		if (StringUtil.haveNullOrBlank(strs)
				|| !me.getApiKey().equals(apiKey)) {
			// apiKey过期
			json.put(STATUS, 0);
			json.put(MESSAGE, "您的apiKey已过期,您的账户可能被别人登陆，请修改密码或重新登陆");
			json.put(USERDETAILINFO, null);
			return json.toString();
		}

		// 验证通过
		UserDetail userDetail = userDetailService.findByUser(user);
		List<UserEvaluateIndex> userEvaluateIndexList = userEvaluateIndexService
				.findByUser(user);
		JSONObject userInfoJson = user.toJson4Detail();
		JSONObject userDetailInfoJson = userDetail.toSimpleJson();
		JSONObject totalJson = Common.mergeJsons(userInfoJson,
				userDetailInfoJson);
		for (UserEvaluateIndex userEvaluate : userEvaluateIndexList) {
			totalJson.put(userEvaluate.getIndexSort().geteName(),
					userEvaluate.getScore());
		}

		json.put(STATUS, 1);
		json.put(MESSAGE, "查找成功");
		json.put(USERDETAILINFO, totalJson);
		return json.toString();
	}

	@POST
	@Path("getUserIndex.do")
	@Produces(MediaType.APPLICATION_JSON)
	public String getUserIndex(@FormParam("myId") Integer myId,
			@FormParam("userId") Integer userId,
			@FormParam("apiKey") String apiKey,
			@Context HttpServletRequest request) {
		if (null == myId || null == userId) {
			// 提交参数无效
			json.put(STATUS, -1);
			json.put(MESSAGE, "操作失败,参数异常");
			json.put(USEREVALUATEINDEX, null);
			return json.toString();
		}

		User me = userService.findById(myId);
		User user = userService.findById(userId);// 通过主键检索用户
		if (null == user || null == me) {
			// 没有查询到用户，说明该手机号尚未注册
			json.put(STATUS, -1);
			json.put(MESSAGE, "操作失败,参数异常");
			json.put(USERDETAILINFO, null);
			return json.toString();
		}

		// 查询到用户，说明该手机号已注册
		String[] strs = { me.getApiKey(), apiKey };
		if (StringUtil.haveNullOrBlank(strs)
				|| !me.getApiKey().equals(apiKey)) {
			// apiKey过期
			json.put(STATUS, 0);
			json.put(MESSAGE, "您的apiKey已过期,您的账户可能被别人登陆，请修改密码或重新登陆");
			json.put(USEREVALUATEINDEX, null);
			return json.toString();
		}

		// 验证通过
		// 查询所有的评价指标
		List<EvaluateIndex> evaluateIndexList = evaluateIndexService
				.getEvaluateIndexList();
		JSONObject evaluateJson = new JSONObject();
		for (EvaluateIndex evaluateIndex : evaluateIndexList) {
			UserEvaluateIndex userEvaluateIndex = userEvaluateIndexService
					.findByUserAndIndex(user, evaluateIndex);
			evaluateJson.put(evaluateIndex.geteName(),
					userEvaluateIndex.getScore());
		}
		json.put(STATUS, 1);
		json.put(MESSAGE, "查找成功");
		json.put(USEREVALUATEINDEX, evaluateJson);
		return json.toString();
	}

	@POST
	@Path("getUserAccount.do")
	@Produces(MediaType.APPLICATION_JSON)
	public String getUserAccount(@FormParam("myId") Integer myId,
			@FormParam("userId") Integer userId,
			@FormParam("apiKey") String apiKey,
			@Context HttpServletRequest request) {
		if (null == myId || null == userId) {
			// 提交参数无效
			json.put(STATUS, -1);
			json.put(MESSAGE, "操作失败,参数异常");
			json.put(USERBALANCE, null);
			return json.toString();
		}

		User me = userService.findById(myId);
		User user = userService.findById(userId);// 通过主键检索用户
		if (null == user || null == me) {
			// 没有查询到用户，说明该手机号尚未注册
			json.put(STATUS, -1);
			json.put(MESSAGE, "操作失败,参数异常");
			json.put(USERBALANCE, null);
			return json.toString();
		}

		// 查询到用户，说明该手机号已注册
		String[] strs = { me.getApiKey(), apiKey };
		if (StringUtil.haveNullOrBlank(strs)
				|| !me.getApiKey().equals(apiKey)) {
			// apiKey过期
			json.put(STATUS, 0);
			json.put(MESSAGE, "您的apiKey已过期,您的账户可能被别人登陆，请修改密码或重新登陆");
			json.put(USERBALANCE, null);
			return json.toString();
		}

		// 验证通过
		json.put(STATUS, 1);
		json.put(MESSAGE, "查找成功");
		json.put(USERBALANCE, user.getBalance());
		return json.toString();
	}

	@POST
	@Path("modifyBriefIntroduction.do")
	@Produces(MediaType.APPLICATION_JSON)
	public String modifyBriefIntroduction(@FormParam("apiKey") String apiKey,
			@FormParam("myId") Integer myId,
			@FormParam("briefIntroduction") String briefIntroduction) {

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

		/*
		 * if(null == description){ json.put("status", -1); json.put("msg",
		 * "参数异常"); return json.toString(); }
		 */

		UserDetail userDetail = userDetailService.findByUser(me);
		userDetail.setBriefIntroduction(briefIntroduction);
		userDetailService.update(userDetail);

		json.put("status", 1);
		json.put("msg", "修改成功");
		return json.toString();

	}

	@POST
	@Path("modifyPosition.do")
	@Produces(MediaType.APPLICATION_JSON)
	public String modifyPosition(@FormParam("apiKey") String apiKey,
			@FormParam("myId") Integer myId,
			@FormParam("longitude") Double longitude,
			@FormParam("latitude") Double latitude) {

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

		/*
		 * if(null == longitude || null == latitude){ json.put("status", -1);
		 * json.put("msg", "参数异常"); return json.toString(); }
		 */

		UserDetail userDetail = userDetailService.findByUser(me);
		userDetail.setLatitude(latitude);
		userDetail.setLongtitude(longitude);
		userDetailService.update(userDetail);

		json.put("status", 1);
		json.put("msg", "修改成功");
		return json.toString();

	}

	@POST
	@Path("modifyPersonalizedSignature.do")
	@Produces(MediaType.APPLICATION_JSON)
	public String modifyPersonalizedSignature(
			@FormParam("apiKey") String apiKey,
			@FormParam("myId") Integer myId,
			@FormParam("personalizedSignature") String personalizedSignature) {

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

		/*
		 * if(null == personalizedSignature){ json.put("status", -1);
		 * json.put("msg", "参数异常"); return json.toString(); }
		 */

		me.setPersonalizedSignature(personalizedSignature);
		userService.update(me);

		json.put("status", 1);
		json.put("msg", "修改成功");
		return json.toString();

	}

	@POST
	@Path("modifyAddr.do")
	@Produces(MediaType.APPLICATION_JSON)
	public String modifyAddr(@FormParam("apiKey") String apiKey,
			@FormParam("myId") Integer myId,
			@FormParam("provinceId") Integer provinceId,
			@FormParam("cityId") Integer cityId) {

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

		/*
		 * if(null == provinceId || null == cityId){ json.put("status", -1);
		 * json.put("msg", "参数异常"); return json.toString(); }
		 */

		UserDetail userDetail = userDetailService.findByUser(me);
		userDetail.setProvince(provinceService.findById(provinceId));
		userDetail.setCity(cityService.findById(cityId));
		userDetailService.update(userDetail);

		json.put("status", 1);
		json.put("msg", "修改成功");
		return json.toString();

	}

	
     @POST
	 @Path("modifyGender.do")
	 @Produces(MediaType.APPLICATION_JSON)
	 public String modifyGender(@FormParam("apiKey") String apiKey,  
			 @FormParam("myId") Integer myId,	
			 @FormParam("gender") Integer gender){  
		  
		  User me = userService.findById(myId); 
		  if (null == me) {
			  json.put("status", -1); 
			  json.put("msg", "操作失败,参数异常s"); 
			  return json.toString(); 
		  }  
		  
		  if (!apiKey.equals(me.getApiKey()))// 检测APIKEY是否相等
		  { 
		     json.put("status",  0);
		     json.put("msg", "您的apiKey已过期,您的账户可能被别人登陆，请修改密码或重新登陆"); 
		     return	json.toString(); 
	      }
		  
		  
		  if(null == gender){ 
			  
			  json.put("status", -1); 
			  json.put("msg", "操作失败,参数异常");
		      return json.toString(); 
		      
		   }
		  
		   me.setGender(gender);
		  userService.update(me);
		  
		  json.put("status", 1); 
		  json.put("msg", "修改成功");
		  return json.toString();
	  
	  }
	 
     @POST
	 @Path("modifyAvatar.do")
	 @Produces(MediaType.APPLICATION_JSON)
	 public String modifyAvatar(@FormParam("apiKey") String apiKey,  
			 @FormParam("myId") Integer myId,	
			 @FormParam("userAvatarPath") String userAvatarPath){  
		  
		  User me = userService.findById(myId); 
		  if (null == me) {
			  json.put("status", -1); 
			  json.put("msg", "操作失败,参数异常"); 
			  return json.toString(); 
		  }  
		  
		  if (!apiKey.equals(me.getApiKey()))// 检测APIKEY是否相等
		  { 
		     json.put("status",  0);
		     json.put("msg", "您的apiKey已过期,您的账户可能被别人登陆，请修改密码或重新登陆"); 
		     return	json.toString(); 
	      }
		  
		  
		  if(null == userAvatarPath){ 
			  
			  json.put("status", -1); 
			  json.put("msg", "操作失败,参数异常");
		      return json.toString(); 
		      
		   }
		  
		  
			Image image = ImageUtil.setImageNameTypeFromPath(userAvatarPath);
			image.setUser(me);
			image = imageService.insert(image);// 添加图片
			//user.setAvatar(image.getImageId());// 设置头像
			me.setAvatar(userAvatarPath);// 设置头像
		  
		 
		  userService.update(me);
		  
		  json.put("status", 1); 
		  json.put("msg", "修改成功");
		  return json.toString();
	  
	  }

	@POST
	@Path("modifyHtNumber.do")
	@Produces(MediaType.APPLICATION_JSON)
	public String modifyHtNumber(@FormParam("apiKey") String apiKey,
			@FormParam("myId") Integer myId,
			@FormParam("newHtName") String newHtName) {

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

		if (null == newHtName) {
			json.put("status", -1);
			json.put("msg", "操作失败,参数异常");
			return json.toString();
		}

		User user = userService.findByHtNumber(newHtName);
		if (null != user) {

			json.put("status", -2);
			json.put("msg", "修改失败，该心跳号已被注册");
			return json.toString();
		}

		me.setHeartbeatNumber(newHtName);
		userService.update(me);

		json.put("status", 1);
		json.put("msg", "修改成功");
		return json.toString();

	}

	@POST
	@Path("modifyNickName.do")
	@Produces(MediaType.APPLICATION_JSON)
	public String modifyNickName(@FormParam("apiKey") String apiKey,
			@FormParam("myId") Integer myId,
			@FormParam("newName") String newName) {

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

		/*
		 * if(null == newName){ json.put("status", -1); json.put("msg", "参数异常");
		 * return json.toString(); }
		 */

		me.setNickName(newName);
		userService.update(me);

		json.put("status", 1);
		json.put("msg", "修改成功");
		return json.toString();

	}

	@POST
	@Path("modifyPhoneNumber.do")
	@Produces(MediaType.APPLICATION_JSON)
	public String modifyPhoneNumber(@FormParam("apiKey") String apiKey,
			@FormParam("myId") Integer myId,
			@FormParam("newPhone") String newPhone) {

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

		if (null == newPhone) {
			json.put("status", -1);
			json.put("msg", "操作失败,参数异常");
			return json.toString();
		}

		User user = userService.findByPhone(newPhone);

		if (null != user) {

			json.put("status", -2);
			json.put("msg", "修改失败,该手机号已被注册");
			return json.toString();
		}

		me.setPhoneNumber(newPhone);
		userService.update(me);

		json.put("status", 1);
		json.put("msg", "修改成功");
		return json.toString();

	}

	@POST
	@Path("modifyPassword.do")
	@Produces(MediaType.APPLICATION_JSON)
	public String modifyPassword(@FormParam("apiKey") String apiKey,
			@FormParam("myId") Integer myId,
			@FormParam("newPassword") String newPassword) {

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

		if (null == newPassword) {
			json.put("status", -1);
			json.put("msg", "操作失败,参数异常");
			return json.toString();
		}

		me.setPlainPassword(newPassword);
		userService.update(me);

		json.put("status", 1);
		json.put("msg", "修改成功");
		return json.toString();

	}

	@POST
	@Path("checkSimpleWord.do")
	@Produces(MediaType.APPLICATION_JSON)
	public String checkSimpleWord(@FormParam("apiKey") String apiKey,
			@FormParam("myId") Integer myId,
			@FormParam("phoneNumber") String phoneNumber,
			@FormParam("simpleWord") String simpleWord) {

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

		if (null == phoneNumber || null == simpleWord) {
			json.put("status", -1);
			json.put("msg", "操作失败,参数异常");
			return json.toString();
		}

		User user = userService.findByPhone(phoneNumber);

		if (null == user) {
			json.put("status", -1);
			json.put("msg", "操作失败,参数异常");
			return json.toString();
		}
		if (!simpleWord.equals(user.getSimpleCode())) {
			json.put("status", -2);
			json.put("msg", "简码不正确");
			return json.toString();
		}

		json.put("status", 1);
		json.put("msg", "简码输入正确");
		return json.toString();
	}

	@GET
	@Path("test.do")
	@Produces(MediaType.APPLICATION_JSON)
	public String test() {
		json.put("mobile", "111111112");
		json.put("passwd", "123456");
		return json.toString();
	}

}
