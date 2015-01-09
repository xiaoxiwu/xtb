package com.xt8.util;

import static com.xt8.util.Constants.*;

import net.sf.json.JSONObject;
import io.rong.ApiHttpClient;
import io.rong.models.FormatType;
import io.rong.models.SdkHttpResult;

public class RongIM {
	// public static final String NORMAL_CODE = "200";
	// public static final String ATTR_CODE = "code";
	private static final String ATTR_RESULT = "result";
	private static final String ATTR_TOKEN = "token";

	//private static final String key = getProperty(RONG_YUN_DEVELOPER_KEY);// 获取开发者key
	//private static final String secret = getProperty(RONG_YUN_DEVELOPER_SECRET);// 获取开发者secret

	private static final String key ="8luwapkvu0tel";// 获取开发者key
	private static final String secret = "k0g7zicnP7pVHv";// 获取开发者secret

	
	/**
	 * 获取token
	 * 
	 * @param userId
	 * @param userName
	 * @param portraitUri
	 * @return
	 */
	public static String getToken(String userId, String userName,
			String portraitUri) {
		try {
			SdkHttpResult result = ApiHttpClient.getToken(key, secret, userId,
					userName, portraitUri, FormatType.json);
			JSONObject json = JSONObject.fromObject(result.toString());
			p("token_json=" + json);
			// String code = json.getString(ATTR_CODE);
			JSONObject inJson = json.getJSONObject(ATTR_RESULT);
			String token = inJson.getString(ATTR_TOKEN);
			p("token=" + token);
			return token;
		} catch (Exception e) {
			return null;
		}
	}

	private static void p(Object obj) {
		System.out.println(obj);
	}

	public static void main(String[] args) throws Exception {
		getToken("13313", "Tenchael", "http://www.alidsjd/hdsj.jpg");
	}

}
