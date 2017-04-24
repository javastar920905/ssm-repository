package com.ouzhx.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Tool {
	
	/**
	 * 字符串转日期
	 * 
	 * @param date
	 *            日期字符串
	 * @param format
	 *            转换的格式
	 * @return 转换后的日期
	 */
	public static Date strToDate(String date, String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		Date d = null;
		try {
			d = sdf.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return d;
	}
	
	/**
	 * 日期转字符串
	 * 
	 * @param date
	 *            日期
	 * @param format
	 *            转换的格式
	 * @return 转换后的字符串
	 */
	public static String dateToStr(Date date, String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		String strDate = null;
		strDate = sdf.format(date);
		return strDate;
	}

	/**
	 * 输出返回结果或者JSON格式的数据
	 * 
	 * @param resule
	 */
	/*public static void printOrJson(Object result,HttpServletResponse response) {
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = null;
		try {
			out = response.getWriter();
			out.print(result);
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}*/
	
}
