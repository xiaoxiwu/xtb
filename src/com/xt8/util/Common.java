package com.xt8.util;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import net.sf.json.JSONObject;

public class Common {

	public static JSONObject toJSON(final Object obj) {
		JSONObject json = new JSONObject();
		Map<String, Object> map = getGetMethodsValue(obj);
		Set<String> keys = map.keySet();
		for (String key : keys) {
			json.put(key, map.get(key));
		}
		return json;
	}

	/**
	 * parse some attributions of a object to json, attribution need have get
	 * method
	 * 
	 * @param obj
	 * @param attrs
	 * @param keepsNull
	 *            When the attribution value is null, need use string null to
	 *            replace it
	 * @return
	 */
	public static JSONObject toJSON(final Object obj, String[] attrs) {
		JSONObject json = new JSONObject();
		Map<String, Object> map = getGetMethodsValue(obj);
		Set<String> keys = map.keySet();
		for (String attr : attrs) {
			if (keys.contains(attr)) {
				Object value = map.get(attr);
				if ((null != value) && (value instanceof java.util.Date)) {
					java.util.Date date = (Date) value;
					SimpleDateFormat dfmt = new SimpleDateFormat("yyyy-MM-dd");
					json.put(attr, dfmt.format(date));
				} else {
					json.put(attr, value);
				}
			}
		}
		return json;
	}

	public static Map<String, Object> getGetMethodsValue(Object obj) {
		Map<String, Object> keyValue = new HashMap<String, Object>();
		try {
			Class clazz = obj.getClass();
			Field[] fields = obj.getClass().getDeclaredFields();// 获得属性
			for (Field field : fields) {
				if (field.getName().equals("serialVersionUID")) {
					continue;
				}
				PropertyDescriptor pd = new PropertyDescriptor(field.getName(),
						clazz);
				Method getMethod = pd.getReadMethod();// 获得get方法
				Object value = getMethod.invoke(obj);// 执行get方法返回一个Object
				keyValue.put(field.getName(), value);
			}
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (IntrospectionException e) {
			e.printStackTrace();
		}
		return keyValue;
	}

	public static JSONObject mergeJsons(final JSONObject json1,
			final JSONObject json2) {
		JSONObject json = json1;
		Set<Object> keys = json2.keySet();
		for (Object key : keys) {
			Object value = json2.get(key);
			json.put(key, value);
		}
		return json;
	}
	/**
	 * 获取系统当前时间:yyyy-MM-dd HH:mm:ss"
	 * @return
	 */
	public static String getSystemTime() {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置显示格式
		return df.format(new Date());//用DateFormat的format()方法在dt中获取并以yyyy/MM/dd HH:mm:ss格式显示
	}
    /**
     * 将指定格式的日期字符串转日期型
     * @param date
     * @param format
     * @return
     */
	public static Date ConvertToDate(String date,String format) {
		DateFormat df = new SimpleDateFormat(format);//设置显示格式
		Date d=null;
		try {
			d=df.parse(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			return d;
		}
		return d;
	}
}
