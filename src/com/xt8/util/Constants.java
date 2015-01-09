package com.xt8.util;

public class Constants {
	/*
	 * private static Properties prop = new Properties(); static { try {
	 * InputStream in = Object.class
	 * .getResourceAsStream("/application.properties"); prop.load(in); } catch
	 * (IOException e) { e.printStackTrace(); } }
	 * 
	 * public static String getProperty(String key) { return
	 * prop.getProperty(key); }
	 */
	public static final Integer MAX_RECORDS = 20;
	public static final String MESSAGE = "msg";
	public static final String STATUS = "status";
	public static final String APIKEY = "apiKey";
	public static final String USERINFO = "userInfo";
	public static final String USERDETAILINFO = "userDetailInfo";
	public static final String USEREVALUATEINDEX = "userEvaluateIndex";
	public static final String USERBALANCE = "userBalance";
	public static final String USERLIST = "userList";

	public static final String TOKEN = "token";
	public static final String DEFAULT_PORTRAIT_URL = "http://121.40.182.96:8080/heart/image/xmen.jpg";

	public static final String RONG_YUN_DEVELOPER_KEY = "yongyun.developer.key";
	public static final String RONG_YUN_DEVELOPER_SECRET = "yongyun.developer.secret";

	public static final String MOODLIST = "moodList";
	public static final String MOODREPLYLIST = "moodReplyList";
	public static final String MOODPRAISELIST = "moodPraiseList";

	public static final String LOGINED_USER = "loginedUser";
	public static final String DEFAULT = "default";
	public static final String ROLE = "role";
	public static final String ADMIN = "admin";
	public static final String USER = "user";
	public static final String DIRLIST = "dirList";
	public static final String FILELIST = "fileList";
	public static final String ROOTDIR = "/";
	public static final String PARENTDIR = "pdir";

	// **********User Setting ***********////
	public static final Integer SETTING_RECEIVE_GOODS_ID = 1;// 1
	public static final Integer SETTING_ACCOUNT_GUARD_ID = 2;// 1
	public static final Integer SETTING_SHAKE_ID = 3;// 0
	public static final Integer SETTING_VOICE_ID = 4;// 1
	public static final Integer SETTING_VERIFY_FRIEND_ID = 5;// 1
	public static final Integer SETTING_FRIENDACCEPTION_ID = 6;// 1
	// **********Share goods *************////
	public static final Integer WAIT_TO_PAY_TIME = 24;
	public static final Integer SEARCH_GOODS_ORDERBY_TIME = 0;
	public static final Integer SEARCH_GOODS_ORDERBY_PEOPLE = 1;
	public static final Integer SEARCH_GOODS_ORDERBY_DIS = 2;

	public static final String JSON_GOODSID = "goodsId";
	public static final String JSON_USERID = "userId";
	public static final String JSON_CATEGORYID = "categoryId";
	public static final String JSON_GOODSNAME = "name";
	public static final String JSON_ALLGOODS = "allGoods";

	// ************User Evaluate index********////
	public static final Integer EINDEX_CHARM_ID = 1;
	public static final Integer EINDEX_INFLUENCE_ID = 2;
	public static final Integer EINDEX_GOOD_REVIEW_ID = 3;
	public static final Integer EINDEX_COMPREHENSIVE_ID = 4;

	public static final Integer EINDEX_LIKE_ADDVALUE = 1;
	public static final Integer EINDEX_WANT_ADDVALUE = 5;
	public static final Integer EINDEX_FINISH_BARGAIN_ADDVALUE = 50;

	public static final String PROVINCE_LIST = "provinces";
	public static final String CITY_LIST = "cities";
	public static final String DISTRICT_LIST = "districts";

	public static final String FIRST_CATEGORY_LIST = "firstCategories";
	public static final String SECOND_CATEGORY_LIST = "secondCategories";

	public static final String EXPRESSCORP_LIST = "expressCorps";

}
