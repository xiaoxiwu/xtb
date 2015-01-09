package com.xt8.rest;

import static com.xt8.util.Constants.MESSAGE;
import static com.xt8.util.Constants.MOODLIST;
import static com.xt8.util.Constants.MOODPRAISELIST;
import static com.xt8.util.Constants.MOODREPLYLIST;
import static com.xt8.util.Constants.STATUS;

import java.util.ArrayList;
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

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.xt8.model.Image;
import com.xt8.model.Mood;
import com.xt8.model.MoodImage;
import com.xt8.model.MoodPraise;
import com.xt8.model.MoodReply;
import com.xt8.model.User;
import com.xt8.service.FriendsService;
import com.xt8.service.ImageService;
import com.xt8.service.MoodImageService;
import com.xt8.service.MoodPraiseService;
import com.xt8.service.MoodReplyService;
import com.xt8.service.MoodService;
import com.xt8.service.UserService;
import com.xt8.util.ImageUtil;
import com.xt8.util.StringUtil;

@Path("/mood")
public class MoodController {

	@Resource(name = "userService")
	private UserService userService;
	@Resource(name = "moodService")
	private MoodService moodService;
	@Resource(name = "imageService")
	private ImageService imageService;
	@Resource(name = "moodImageService")
	private MoodImageService moodImageService;
	@Resource(name = "friendsService")
	private FriendsService friendsService;
	@Resource(name = "moodReplyService")
	private MoodReplyService moodReplyService;
	@Resource(name = "moodPraiseService")
	private MoodPraiseService moodPraiseService;

	private JSONObject json = new JSONObject();

	@POST
	@Path("publishMood.do")
	@Produces(MediaType.APPLICATION_JSON)
	public String publishMood(@FormParam("myId") Integer myId,
			@FormParam("apiKey") String apiKey,
			@FormParam("moodImagePath") String moodImagePath,
			@FormParam("moodContent") String moodContent,
			@FormParam("locationInfo") String locationInfo,
			@FormParam("extraFlag") String extraFlag,
			@FormParam("extraProperty1") String extraProperty1,
			@FormParam("extraValue1") String extraValue1,
			@FormParam("extraProperty2") String extraProperty2,
			@FormParam("extraValue2") String extraValue2,
			@Context HttpServletRequest request) {
		try {
			String[] strs = { apiKey, moodContent };
			if ((null == myId) || StringUtil.haveNullOrBlank(strs)) {
				// 提交参数无效
				json.put(STATUS, -1);
				json.put(MESSAGE, "操作失败,参数异常");
				return json.toString();
			}

			// ////////////////////ａpiKey无效/////////////////////////
			User user = userService.findById(myId);
			if ((null == user) || !apiKey.equals(user.getApiKey())) {
				// 通过apiKey不能查询到用户，说明用户apiKey已经更换，在别处登陆了
				json.put(STATUS, 0);
				json.put(MESSAGE, "您的apiKey已过期,您的账户可能被别人登陆，请修改密码或重新登陆");
				return json.toString();
			}

			// ////////////////用户在线//////////////////////////
			Integer extFlag = null;
			Integer extPropery1 = null;
			Integer extValue1 = null;
			Integer extPropery2 = null;
			Integer extValue2 = null;
			if (!StringUtil.isNullOrBlank(extraFlag)) {
				extFlag = Integer.parseInt(extraFlag);
			}
			if (!StringUtil.isNullOrBlank(extraProperty1)) {
				extPropery1 = Integer.parseInt(extraProperty1);
			}
			if (!StringUtil.isNullOrBlank(extraValue1)) {
				extValue1 = Integer.parseInt(extraValue1);
			}
			if (!StringUtil.isNullOrBlank(extraProperty2)) {
				extPropery2 = Integer.parseInt(extraProperty2);
			}
			if (!StringUtil.isNullOrBlank(extraValue2)) {
				extValue2 = Integer.parseInt(extraValue2);
			}
			String device = null;// 设备信息，待添加
			Mood mood = new Mood(user, moodContent, device, locationInfo);
			mood.setExtraFlag(extFlag);
			mood.setExtraProperty1(extPropery1);
			mood.setExtraValue1(extValue1);
			mood.setExtraProperty2(extPropery2);
			mood.setExtraValue2(extValue2);
			mood = moodService.insert(mood);
			List<MoodImage> moodImages = new ArrayList<MoodImage>();
			String[] imagePaths = { moodImagePath };// 如果上传多张图片，这里需要修改
			for (String path : imagePaths) {
				if (!StringUtil.isNullOrBlank(path)) {
					Image image = ImageUtil.setImageNameTypeFromPath(path);
					image.setUser(user);
					image = imageService.insert(image);
					MoodImage moodImage = new MoodImage(mood, image);
					moodImage = moodImageService.saveAndReturn(moodImage);
					moodImages.add(moodImage);
				}
			}
			json.put(STATUS, 1);
			json.put(MESSAGE, "发表成功");
			return json.toString();
		} catch (Exception e) {
			json.put(STATUS, -1);
			json.put(MESSAGE, "操作失败,参数异常");
			return json.toString();
		}

	}

	@POST
	@Path("findMood.do")
	@Produces(MediaType.APPLICATION_JSON)
	public String findMood(@FormParam("myId") Integer myId,
			@FormParam("apiKey") String apiKey,
			@FormParam("property") Integer property,
			@FormParam("propertyValue") String propertyValue,
			@FormParam("pageIndex") Integer pageIndex,
			@FormParam("pageSize") Integer pageSize,
			@FormParam("sortStyle") Integer sortStyle,
			@FormParam("siftCondition") Integer siftCondition,
			@FormParam("siftValue") String siftValue,
			@Context HttpServletRequest request) {
		try {
			String[] strs = { apiKey, propertyValue };
			if ((null == myId) || (null == property)
					|| StringUtil.haveNullOrBlank(strs)) {
				// 提交参数无效
				json.put(STATUS, -1);
				json.put(MESSAGE, "操作失败,参数异常");
				json.put(MOODLIST, null);
				return json.toString();
			}

			// ////////////////////ａpiKey无效/////////////////////////
			User user = userService.findById(myId);
			if ((null == user) || !apiKey.equals(user.getApiKey())) {
				// 通过apiKey不能查询到用户，说明用户apiKey已经更换，在别处登陆了
				json.put(STATUS, 0);
				json.put(MESSAGE, "您的apiKey已过期,您的账户可能被别人登陆，请修改密码或重新登陆");
				json.put(MOODLIST, null);
				return json.toString();
			}

			JSONArray moodListJson = new JSONArray();
			// ///////////根据动态ID精确查找/////////////////////
			if (0 == property) {
				Integer mid = Integer.parseInt(propertyValue);// 动态ID
				Mood mood = moodService.findById(mid);
				moodListJson.add(mood.toSimpleJson());
			}

			// ///////////根据用户ID查找某一用户发表的所有动态/////////////////////
			else if (1 == property) {
				Integer uid = Integer.parseInt(propertyValue);// 用户ID
				User u = new User();
				u.setUserId(uid);
				List<Mood> moodList = moodService.findByUserWithPage(u,
						pageIndex, pageSize);
				for (Mood mood : moodList) {
					moodListJson.add(mood.toSimpleJson());
				}
			}

			// ///////////根据用户ID查找该用户的所有好友的动态/////////////////////
			else if(2 == property) {
				Integer uid = Integer.parseInt(propertyValue);// 用户ID
				List<User> friendList = friendsService.findBySelfId(uid);
				List<Mood> moodList = moodService.findByUsersWithPage(
						friendList, pageIndex, pageSize, "time");
				for (Mood mood : moodList) {
					moodListJson.add(mood.toSimpleJson());
				}
			}

			json.put(STATUS, 1);
			json.put(MESSAGE, "查找成功");
			json.put(MOODLIST, moodListJson);
			return json.toString();
		} catch (Exception e) {
			json.put(STATUS, -1);
			json.put(MESSAGE, "操作失败,参数异常");
			json.put(MOODLIST, null);
			return json.toString();
		}

	}

	@POST
	@Path("deleteMood.do")
	@Produces(MediaType.APPLICATION_JSON)
	public String deleteMood(@FormParam("myId") Integer myId,
			@FormParam("apiKey") String apiKey,
			@FormParam("moodId") Integer moodId,
			@Context HttpServletRequest request) {
		try {
			String[] strs = { apiKey };
			if ((null == myId) || (null == moodId)
					|| StringUtil.haveNullOrBlank(strs)) {
				// 提交参数无效
				json.put(STATUS, -1);
				json.put(MESSAGE, "操作失败,参数异常");
				return json.toString();
			}

			// ////////////////////ａpiKey无效/////////////////////////
			User user = userService.findById(myId);
			if ((null == user) || !apiKey.equals(user.getApiKey())) {
				// 通过apiKey不能查询到用户，说明用户apiKey已经更换，在别处登陆了
				json.put(STATUS, 0);
				json.put(MESSAGE, "您的apiKey已过期,您的账户可能被别人登陆，请修改密码或重新登陆");
				return json.toString();
			}

			Mood mood = new Mood();
			mood.setMoodId(moodId);
			moodService.delete(mood);

			json.put(STATUS, 1);
			json.put(MESSAGE, "删除成功");
			return json.toString();
		} catch (Exception e) {
			json.put(STATUS, -1);
			json.put(MESSAGE, "操作失败,参数异常");
			return json.toString();
		}

	}

	@POST
	@Path("replyMood.do")
	@Produces(MediaType.APPLICATION_JSON)
	public String replyMood(@FormParam("myId") Integer myId,
			@FormParam("apiKey") String apiKey,
			@FormParam("moodId") Integer moodId,
			@FormParam("commentContent") String commentContent,
			@Context HttpServletRequest request) {
		try {
			String[] strs = { apiKey, commentContent };
			if ((null == myId) || (null == moodId)
					|| StringUtil.haveNullOrBlank(strs)) {
				// 提交参数无效
				json.put(STATUS, -1);
				json.put(MESSAGE, "操作失败,参数异常");
				json.put(MOODREPLYLIST, null);
				return json.toString();
			}

			// ////////////////////ａpiKey无效/////////////////////////
			User user = userService.findById(myId);
			if ((null == user) || !apiKey.equals(user.getApiKey())) {
				// 通过apiKey不能查询到用户，说明用户apiKey已经更换，在别处登陆了
				json.put(STATUS, 0);
				json.put(MESSAGE, "您的apiKey已过期,您的账户可能被别人登陆，请修改密码或重新登陆");
				json.put(MOODREPLYLIST, null);
				return json.toString();
			}

			Mood mood = moodService.findById(moodId);
			MoodReply moodReply = new MoodReply(mood, mood.getUser(), null,
					new Date(), commentContent, 0);
			moodReply = moodReplyService.insert(moodReply);
			List<MoodReply> list = moodReplyService.findByMood(mood);
			JSONArray moodReplyList = new JSONArray();
			for (MoodReply mr : list) {
				moodReplyList.add(mr.toSimpleJSON());
			}

			json.put(STATUS, 1);
			json.put(MESSAGE, "评论成功");
			json.put(MOODREPLYLIST, moodReplyList);
			return json.toString();
		} catch (Exception e) {
			json.put(STATUS, -1);
			json.put(MESSAGE, "操作失败,参数异常");
			json.put(MOODREPLYLIST, null);
			return json.toString();
		}

	}

	@POST
	@Path("praiseMood.do")
	@Produces(MediaType.APPLICATION_JSON)
	public String praiseMood(@FormParam("myId") Integer myId,
			@FormParam("apiKey") String apiKey,
			@FormParam("moodId") Integer moodId,
			@Context HttpServletRequest request) {
		try {
			String[] strs = { apiKey };
			if ((null == myId) || (null == moodId)
					|| StringUtil.haveNullOrBlank(strs)) {
				// 提交参数无效
				json.put(STATUS, -1);
				json.put(MESSAGE, "操作失败,参数异常");
				json.put(MOODPRAISELIST, null);
				return json.toString();
			}

			// ////////////////////ａpiKey无效/////////////////////////
			User user = userService.findById(myId);
			if ((null == user) || !apiKey.equals(user.getApiKey())) {
				// 通过apiKey不能查询到用户，说明用户apiKey已经更换，在别处登陆了
				json.put(STATUS, 0);
				json.put(MESSAGE, "您的apiKey已过期,您的账户可能被别人登陆，请修改密码或重新登陆");
				json.put(MOODPRAISELIST, null);
				return json.toString();
			}

			Mood mood = moodService.findById(moodId);
			MoodPraise moodPraise = new MoodPraise(mood, mood.getUser(),
					new Date());
			moodPraise = moodPraiseService.insert(moodPraise);
			List<MoodPraise> list = moodPraiseService.findByMood(mood);
			JSONArray moodPraiseJsons = new JSONArray();
			for (MoodPraise mp : list) {
				moodPraiseJsons.add(mp.toSimpleJSON());
			}
			json.put(STATUS, 1);
			json.put(MESSAGE, "点赞成功");
			json.put(MOODPRAISELIST, moodPraiseJsons);
			return json.toString();
		} catch (Exception e) {
			json.put(STATUS, -1);
			json.put(MESSAGE, "操作失败,参数异常");
			json.put(MOODPRAISELIST, null);
			return json.toString();
		}

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
