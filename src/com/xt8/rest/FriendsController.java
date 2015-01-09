package com.xt8.rest;

import static com.xt8.util.Constants.*;

import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import com.xt8.model.Friends;
import com.xt8.model.Setting;
import com.xt8.model.User;
import com.xt8.model.UserSetting;
import com.xt8.model.VerifyFriend;
import com.xt8.service.FriendsService;
import com.xt8.service.SettingService;
import com.xt8.service.UserService;
import com.xt8.service.UserSettingService;
import com.xt8.service.VerifyFriendService;
import com.xt8.util.Logic;
import com.xt8.util.StringUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Path("/friends")
public class FriendsController {

	@Resource(name = "userService")
	private UserService userService;

	@Resource(name = "friendsService")
	private FriendsService friendsService;

	@Resource(name = "userSettingService")
	private UserSettingService userSettingService;

	@Resource(name = "settingService")
	private SettingService settingService;

	@Resource(name = "verifyFriendService")
	private VerifyFriendService verifyFriendService;

	private JSONObject json = new JSONObject();

	@POST
	@Path("findStranger.do")
	@Produces(MediaType.APPLICATION_JSON)
	public String findStranger(@FormParam("myId") Integer myId,
			@FormParam("apiKey") String apiKey,
			@FormParam("condition") String condition,
			@FormParam("pageIndex") Integer pageIndex,
			@FormParam("pageSize") Integer pageSize,
			@FormParam("sortStyle") Integer sortStyle,
			@FormParam("siftCondition") Integer siftCondition,
			@FormParam("siftValue") String siftValue,
			@Context HttpServletRequest request) {
		if (null == myId) {
			// 提交参数无效
			json.put(STATUS, -1);
			json.put(MESSAGE, "操作失败,参数异常");
			json.put(USERLIST, null);
			return json.toString();
		}

		User user = userService.findById(myId);// 通过主键检索用户
		if (null == user) {
			// 没有查询到用户，说明该手机号尚未注册
			json.put(STATUS, -1);
			json.put(MESSAGE, "操作失败,参数异常");
			json.put(USERLIST, null);
			return json.toString();
		}

		// 查询到用户，说明该手机号已注册
		String[] strs = { user.getApiKey(), apiKey };
		if (StringUtil.haveNullOrBlank(strs)
				|| !user.getApiKey().equals(apiKey)) {
			// apiKey过期
			json.put(STATUS, 0);
			json.put(MESSAGE, "您的apiKey已过期,您的账户可能被别人登陆，请修改密码或重新登陆");
			json.put(USERLIST, null);
			return json.toString();
		}

		// 验证通过
		if (StringUtil.isNullOrBlank(condition)) {
			// apiKey过期
			json.put(STATUS, -2);
			json.put(MESSAGE, "查询条件不能为空");
			json.put(USERLIST, null);
			return json.toString();
		}

		Map<String, String> map = new HashMap<String, String>();
		map.put("phoneNumber", condition);
		map.put("heartbeatNumber", condition);
		map.put("nickName", condition);

		List<User> list = userService.patternQueryByPage(User.class, map,
				Logic.OR, pageIndex, pageSize);
		JSONArray jsonArr = new JSONArray();
		for (User u : list) {
			jsonArr.add(u.toJSON4Query());
		}
		json.put(STATUS, 1);
		json.put(MESSAGE, "查找成功");
		json.put(USERLIST, jsonArr);
		return json.toString();
	}

	@POST
	@Path("findFriends.do")
	@Produces(MediaType.APPLICATION_JSON)
	public String findFriends(@FormParam("myId") Integer myId,
			@FormParam("apiKey") String apiKey,
			@FormParam("condition") String condition,
			@FormParam("pageIndex") Integer pageIndex,
			@FormParam("pageSize") Integer pageSize,
			@FormParam("sortStyle") Integer sortStyle,
			@FormParam("siftCondition") Integer siftCondition,
			@FormParam("siftValue") String siftValue,
			@Context HttpServletRequest request) {
		try {
			if (null == myId) {
				// 提交参数无效
				json.put(STATUS, -1);
				json.put(MESSAGE, "操作失败,参数异常");
				json.put(USERLIST, null);
				return json.toString();
			}

			User user = userService.findById(myId);// 通过主键检索用户
			if (null == user) {
				// 没有查询到用户，说明该手机号尚未注册
				json.put(STATUS, -1);
				json.put(MESSAGE, "操作失败,参数异常");
				json.put(USERLIST, null);
				return json.toString();
			}

			// 查询到用户，说明该手机号已注册
			String[] strs = { user.getApiKey(), apiKey };
			if (StringUtil.haveNullOrBlank(strs)
					|| !user.getApiKey().equals(apiKey)) {
				// apiKey过期
				json.put(STATUS, 0);
				json.put(MESSAGE, "您的apiKey已过期,您的账户可能被别人登陆，请修改密码或重新登陆");
				json.put(USERLIST, null);
				return json.toString();
			}

			// 验证通过
			// 尚未完成，待具体实现
			if (StringUtil.isNullOrBlank(condition)) {
				// apiKey过期
				json.put(STATUS, -2);
				json.put(MESSAGE, "查询条件不能为空");
				json.put(USERLIST, null);
				return json.toString();
			}
			
			List<User> list = null;
			list = friendsService.patternQueryFriendsByPage(myId, condition,
					pageIndex, pageSize);
			
			//list = friendsService.patternQueryFriends(myId, condition);
			
			JSONArray jsonArr = new JSONArray();
			for (User u : list) {
				jsonArr.add(u.toJSON4Query());
			}
			json.put(STATUS, 1);
			json.put(MESSAGE, "查找成功");
			json.put(USERLIST, jsonArr);
			return json.toString();
		} catch (Exception e) {
			json.put(STATUS, -1);
			json.put(MESSAGE, "操作失败,参数异常");
			json.put(USERLIST, null);
			return json.toString();
		}
	}

	@POST
	@Path("addFriend.do")
	@Produces(MediaType.APPLICATION_JSON)
	public String addFriend(@FormParam("myId") Integer myId,
			@FormParam("frId") Integer frId,
			@FormParam("apiKey") String apiKey,
			@Context HttpServletRequest request) {
		// ////////////////--验证参数 --////////////
		if (null == myId || null == frId) {
			// 提交参数无效
			json.put(STATUS, -1);
			json.put(MESSAGE, "操作失败,参数异常");
			return json.toString();
		}

		// ////////////////--不能添加自己为好友 --////////////
		if (myId == frId) {
			// 提交参数无效
			json.put(STATUS, -3);
			json.put(MESSAGE, "请不要添加自己为好友");
			return json.toString();
		}

		// ////////////////--检查用户账户是否已经存在 --////////////
		User selfUser = userService.findById(myId);// 通过主键检索用户
		User frUser = userService.findById(frId);// 通过主键检索好友用户
		if (null == selfUser || null == frUser) {
			// 没有查询到用户，说明该手机号尚未注册
			json.put(STATUS, -1);
			json.put(MESSAGE, "操作失败,参数异常");
			return json.toString();
		}

		// ////////////////--检查apiKey是否过期 --////////////
		// 查询到用户，说明该手机号已注册
		String[] strs = { selfUser.getApiKey(), apiKey };
		if (StringUtil.haveNullOrBlank(strs)
				|| !selfUser.getApiKey().equals(apiKey)) {
			// apiKey过期
			json.put(STATUS, 0);
			json.put(MESSAGE, "您的apiKey已过期,您的账户可能被别人登陆，请修改密码或重新登陆");
			return json.toString();
		}

		// //////////////// --查找对方是否已经是自己的好友 --////////////
		List<User> friendsList = friendsService.findBySelfId(myId);
		Set<Integer> friendsIds = new HashSet<Integer>();
		for (User u : friendsList) {
			friendsIds.add(u.getUserId());
		}
		if (friendsIds.contains(frId)) {
			// 对方已经在自己的好友列表里面了
			json.put(STATUS, -2);
			json.put(MESSAGE, "对方已经是您的好友,请不要重复添加");
			return json.toString();
		}

		// 用户设置如果设置为不允许别人添加，则直接返回
		Integer settingId = SETTING_FRIENDACCEPTION_ID;//
		Setting setting = settingService.findById(settingId);
		UserSetting userSetting = userSettingService.findByUserAndSetting(
				frUser, setting);

		if (0 == userSetting.getValue()) {
			// 设置值为-1，说明对方不允许添加
			json.put(STATUS, -4);
			json.put(MESSAGE, "对方不允许加为好友");
			return json.toString();
		}

		// ///根据用户设置添加好友，直接添加或需要验证//////
		settingId = SETTING_VERIFY_FRIEND_ID;// 待修改，根据用户验证添加的设置在数据库中的记录来确定
		setting = settingService.findById(settingId);
		userSetting = userSettingService.findByUserAndSetting(frUser, setting);
		if (0 == userSetting.getValue()) {
			// 设置值为0，说明可以直接添加
			Friends friends = new Friends(myId, frId, new Date());
			Friends inverseFriends = new Friends(frId, myId, new Date());
			friendsService.save(friends);
			friendsService.save(inverseFriends);
			json.put(STATUS, 1);
			json.put(MESSAGE, "添加成功");
			return json.toString();
		} else {
			// 设置值为1，说明添加需要验证
			json.put(STATUS, -5);
			json.put(MESSAGE, "对方需要验证你的身份信息");
			return json.toString();
		}
	}

	@POST
	@Path("deleteFriend.do")
	@Produces(MediaType.APPLICATION_JSON)
	public String deleteFriend(@FormParam("myId") Integer myId,
			@FormParam("frId") Integer frId,
			@FormParam("apiKey") String apiKey,
			@Context HttpServletRequest request) {
		// ////////////////--验证参数 --////////////
		
		if (null == myId || null == frId) {
			// 提交参数无效
			json.put(STATUS, -1);
			json.put(MESSAGE, "操作失败,参数异常");
			return json.toString();
		}

		
		if(myId == frId){
			json.put(STATUS, -3);
			json.put(MESSAGE, "不能删除自己");
			return json.toString();
		}
		
		// ////////////////--检查用户账户是否已经存在 --////////////
		User selfUser = userService.findById(myId);// 通过主键检索用户
		User frUser = userService.findById(frId);// 通过主键检索好友用户
		if (null == selfUser || null == frUser) {
			// 没有查询到用户，说明该手机号尚未注册
			json.put(STATUS, -1);
			json.put(MESSAGE, "操作失败,参数异常");
			return json.toString();
		}

		// ////////////////--检查apiKey是否过期 --////////////
		// 查询到用户，说明该手机号已注册
		String[] strs = { selfUser.getApiKey(), apiKey };
		if (StringUtil.haveNullOrBlank(strs)
				|| !selfUser.getApiKey().equals(apiKey)) {
			// apiKey过期
			json.put(STATUS, 0);
			json.put(MESSAGE, "您的apiKey已过期,您的账户可能被别人登陆，请修改密码或重新登陆");
			return json.toString();
		}

		// //////////////// 删除好友////////////
		Friends friends = friendsService.findBySelfAndFrId(myId, frId);
		Friends inverseFriends = friendsService.findBySelfAndFrId(frId, myId);
		
		if(null == friends || null == inverseFriends){
			json.put(STATUS, -2);
			json.put(MESSAGE, "删除失败,该用户不是你的好友");
			return json.toString();
		}
		
		friendsService.delete(friends);
		friendsService.delete(inverseFriends);
		json.put(STATUS, 1);
		json.put(MESSAGE, "好友删除成功");
		return json.toString();
	}

	@POST
	@Path("verifyFriend.do")
	@Produces(MediaType.APPLICATION_JSON)
	public String verifyFriend(@FormParam("myId") Integer myId,
			@FormParam("reqId") Integer reqId,
			@FormParam("apiKey") String apiKey,
			@FormParam("type") Integer type, @Context HttpServletRequest request) {
		// ////////////////--验证参数 --////////////
		if (null == myId || null == reqId) {
			// 提交参数无效
			json.put(STATUS, -1);
			json.put(MESSAGE, "操作失败,参数异常");
			return json.toString();
		}

		User selfUser = userService.findById(myId);// 通过主键检索用户
		User reqUser = userService.findById(reqId);
		if (null == selfUser || null == reqUser) {
			json.put(STATUS, -1);
			json.put(MESSAGE, "操作失败,参数异常");
			return json.toString();
		}

		// ////////////////--检查apiKey是否过期 --////////////
		// 查询到用户，说明该手机号已注册
		String[] strs = { selfUser.getApiKey(), apiKey };
		if (StringUtil.haveNullOrBlank(strs)
				|| !selfUser.getApiKey().equals(apiKey)) {
			// apiKey过期
			json.put(STATUS, 0);
			json.put(MESSAGE, "您的apiKey已过期,您的账户可能被别人登陆，请修改密码或重新登陆");
			return json.toString();
		}

		// ////////验证好友////////////////
		VerifyFriend verifyFriend = verifyFriendService.findByFstAndSecUser(
				selfUser, reqUser);
		VerifyFriend inverseVerifyFrd = verifyFriendService
				.findByFstAndSecUser(reqUser, selfUser);
		if (1 == type) {// 同意添加
			Friends friends = new Friends(myId, reqId, new Date());
			Friends inverseFriends = new Friends(reqId, myId, new Date());
			friendsService.save(friends);
			friendsService.save(inverseFriends);
			verifyFriend.setStatus(2);
			inverseVerifyFrd.setStatus(2);
			verifyFriendService.update(verifyFriend);
			verifyFriendService.update(inverseVerifyFrd);

			json.put(STATUS, 1);
			json.put(MESSAGE, "发送成功,已添加");
			return json.toString();
		} else {
			verifyFriend.setStatus(-1);
			inverseVerifyFrd.setStatus(-1);
			verifyFriendService.update(verifyFriend);
			verifyFriendService.update(inverseVerifyFrd);

			json.put(STATUS, 1);
			json.put(MESSAGE, "已拒绝对方的好友请求");
			return json.toString();
		}
	}

	@POST
	@Path("sendVerificationInfo.do")
	@Produces(MediaType.APPLICATION_JSON)
	public String sendVerificationInfo(@FormParam("myId") Integer myId,
			@FormParam("reqId") Integer reqId,
			@FormParam("apiKey") String apiKey,
			@FormParam("description") String description,
			@Context HttpServletRequest request) {
		// ////////////////--验证参数 --////////////
		if (null == myId || null == reqId) {
			// 提交参数无效
			json.put(STATUS, -1);
			json.put(MESSAGE, "操作失败,参数异常");
			return json.toString();
		}

		User selfUser = userService.findById(myId);// 通过主键检索用户
		User reqUser = userService.findById(reqId);
		if (null == selfUser || null == reqUser) {
			json.put(STATUS, -1);
			json.put(MESSAGE, "操作失败,参数异常");
			return json.toString();
		}

		// ////////////////--检查apiKey是否过期 --////////////
		// 查询到用户，说明该手机号已注册
		String[] strs = { selfUser.getApiKey(), apiKey };
		if (StringUtil.haveNullOrBlank(strs)
				|| !selfUser.getApiKey().equals(apiKey)) {
			// apiKey过期
			json.put(STATUS, 0);
			json.put(MESSAGE, "您的apiKey已过期,您的账户可能被别人登陆，请修改密码或重新登陆");
			return json.toString();
		}

		// ////////发送验证消息////////////////

		VerifyFriend reqestUser = new VerifyFriend(reqUser, selfUser, -1,
				description);
		VerifyFriend requestedUser = new VerifyFriend(selfUser, reqUser, -2,
				description);
		verifyFriendService.save(reqestUser);
		verifyFriendService.save(requestedUser);
		json.put(STATUS, 1);
		json.put(MESSAGE, "验证消息已发送");
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
