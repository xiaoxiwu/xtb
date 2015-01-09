package com.xt8.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class GenerateOrderNumber {
	
	private static long orderNum = 1;
	private static String date;

	public static String getOrderNumber(){
		
		String str = new SimpleDateFormat("yyMMddHHmm").format(new Date());
		
		if(null == date || !date.equals(str)){
			date = str;
			orderNum = 01;
		}
		orderNum++;
		long orderNo = Long.parseLong((date))* 10000;
		orderNo += orderNum;
		
		return orderNo+" ";
	}
}
