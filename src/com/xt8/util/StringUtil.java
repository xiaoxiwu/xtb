package com.xt8.util;

import javax.servlet.http.HttpServletRequest;

public class StringUtil {
	public static boolean isNullOrBlank(String str) {
		if (str == null || str.trim().length() == 0)
			return true;
		else
			return false;
	}

	public static boolean haveNullOrBlank(String[] strs) {
		for (String str : strs) {
			if (isNullOrBlank(str)) {
				return true;
			}
		}
		return false;
	}

	public static String addZeroForNum(String str, int strLength) {
		int strLen = str.length();
		if (strLen < strLength) {
			while (strLen < strLength) {
				StringBuffer sb = new StringBuffer();
				sb.append("0").append(str);// add 0 at left
				// sb.append(str).append("0");//add 0 at right
				str = sb.toString();
				strLen = str.length();
			}
		}
		return str;
	}

	public static String getIP(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("PRoxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}
}
