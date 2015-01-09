package com.xt8.util;

import javax.servlet.http.HttpServletRequest;

import com.xt8.model.User;

public class Security {
	public static User loginedUser(HttpServletRequest request) {
		Object obj = request.getSession().getAttribute(Constants.LOGINED_USER);
		if (obj == null) {
			return null;
		}
		return (User) obj;
	}

}
