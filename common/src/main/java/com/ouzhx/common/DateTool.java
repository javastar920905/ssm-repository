package com.ouzhx.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Set;
/**
 * 日期时间工具类
 * @author Administrator
 *
 */
public final class DateTool {

	/**
	 * 获取当前时间
	 * @return Date
	 */
	public static Date getCurrentDate(){
		return new Date(System.currentTimeMillis());
	}
	
	/**
	 * 将时间格式化成指定格式的字符串
	 * @param date
	 * @param pattern 格式(yyyMMdd | yyyy-MM-dd等)
	 * @return
	 */
	public static String formatDate(Date date, String pattern){
		if(date == null)
			date = getCurrentDate();
		if(StringTool.isNullOrEmpty(pattern))
			pattern = "yyyy-MM-dd HH:mm:ss";
		SimpleDateFormat dateFormat = new SimpleDateFormat(pattern); 
		return dateFormat.format(date);
	}
	
	/**
	 * 将字符串时间，转化成日期类型
	 * @param strDate yyyy-MM-dd
	 * @return Date
	 */
	public static Date parseDate(String strDate){ 
		return parseDate(strDate, null);
	}
	
	/**
	 * 将日期字符串 转换成 日期类型(strDate 与 pattern格式必须相符)
	 * @param strDate xxxx-xx-xx
	 * @param pattern yyyy-MM-dd HH:mm:ss | yyyy-MM-dd HH:mm等
	 * @return
	 */
	public static Date parseDate(String strDate, String pattern) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(); 
		if(null == pattern || "".equals(pattern))
			dateFormat.applyPattern("yyyy-MM-dd");
		else
			dateFormat.applyPattern(pattern);
		try {
			return dateFormat.parse(strDate);
		} catch (ParseException e) {
			throw new RuntimeException("日期字符串格式错误, 应该为："+pattern);
		}
	}
	
	/**
	 * 获取输入日期的下 n 天 返回 8位 like 20050101
	 * 
	 * @param day
	 * @param n
	 * @return
	 */
	public static String getNextDay(String day, int n) {
		if (day == null || "".equals(day) || day.length() != 8) {
			throw new RuntimeException("由于缺少必要的参数，系统无法进行制定的日期换算.");
		}
		try {
			String sYear = day.substring(0, 4);
			int year = Integer.parseInt(sYear);
			String sMonth = day.substring(4, 6);
			int month = Integer.parseInt(sMonth);
			String sDay = day.substring(6, 8);
			int dday = Integer.parseInt(sDay);
			Calendar cal = Calendar.getInstance();
			cal.set(year, month - 1, dday);
			cal.add(Calendar.DATE, n); 
			return formatDate(cal.getTime(), "yyyyMMdd"); 
		}catch (RuntimeException e) {
		    throw e;
		  }  catch (Exception e) {
			throw new RuntimeException("进行日期运算时输入得参数不符合系统规格." + e);
		}
	}
	
	/**
	 * 获取输入 月份的下 n 月份 返回 6位 like 200501
	 * 
	 * @param month like 200404
	 * @param n
	 * @return
	 */
	public static String getNextMonth(String month, int n) {
		if (month == null || "".equals(month) || month.length() != 6) {
			throw new RuntimeException("由于缺少必要的参数，系统无法进行制定的月份换算.");
		}
		try {
			String sYear = month.substring(0, 4);
			int year = Integer.parseInt(sYear);
			String sMonth = month.substring(4, 6);
			int mon = Integer.parseInt(sMonth);
			Calendar cal = Calendar.getInstance();
			cal.set(year, mon - 1, 1);
			cal.add(Calendar.MARCH, n); 
			return formatDate(cal.getTime(), "yyyyMMdd"); 
		}catch (RuntimeException e) {
		    throw e;
		}catch (Exception e) {
			throw new RuntimeException("进行月份运算时输入得参数不符合系统规格." + e);
		}
	}
	
	/**
	 * 获取输入 月份的前 n 月份 返回 6位 like 200501
	 * 
	 * @param month
	 * @param n
	 * @return
	 */
	public static String getPreviousMonth(String month, int n) {
		return getNextMonth(month, -n);
	}

	/**
	 * 获取输入日期的嵌 n 天 返回 8位 like 20050101
	 * 
	 * @param day
	 * @param n
	 * @return
	 */
	public static String getPreviousDay(String day, int n) {
		return getNextDay(day, -n);
	}
	
	
	/**
	 * 得到指定时间的指定类型的n值后的时间
	 * 
	 * @param cur_date : yyyymmdd
	 * @param cur_time : HHmmss
	 * @param flag :1:second,2:minute,3:hour,4:day,5:month,6:year
	 * @param n :
	 * @return yyyy-MM-dd HH:mm:ss
	 */
	public static String getNextTime(String curDate, String curTime, int flag, int n) {
		if (StringTool.isNullOrEmpty(curDate) || curDate.length() != 8) {
			throw new RuntimeException("由于缺少必要的参数，系统无法进行制定的日期换算.");
		}
		if (StringTool.isNullOrEmpty(curTime) || curTime.length() != 6) {
			throw new RuntimeException("由于缺少必要的参数，系统无法进行制定的日期换算.");
		}
		try {
			String sYear = curDate.substring(0, 4);
			int year = Integer.parseInt(sYear);
			String sMonth = curDate.substring(4, 6);
			int month = Integer.parseInt(sMonth);
			String sDay = curDate.substring(6, 8);
			int day = Integer.parseInt(sDay);
			String sHour = curTime.substring(0, 2);
			int hour = Integer.parseInt(sHour);
			String sMin = curTime.substring(2, 4);
			int min = Integer.parseInt(sMin);
			String sSec = curTime.substring(4, 6);
			int sec = Integer.parseInt(sSec);
			Calendar cal = Calendar.getInstance();
			cal.set(year, month - 1, day, hour, min, sec);
			switch (flag) {
			case 1:
				cal.add(Calendar.SECOND, n);
				break;
			case 2:
				cal.add(Calendar.MINUTE, n);
				break;
			case 3:
				cal.add(Calendar.HOUR, n);
				break;
			case 4:
				cal.add(Calendar.DATE, n);
				break;
			case 5:
				cal.add(Calendar.MONTH, n);
				break;
			case 6:
				cal.add(Calendar.YEAR, n);
				break;
			default:
				break;
			}  
			return formatDate(cal.getTime(), "yyyy-MM-dd HH:mm:ss");
		} catch (RuntimeException e) {
		    throw e;
		} catch (Exception e) {
			throw new RuntimeException("进行日期运算时输入得参数不符合系统规格." + e);
		}
	}

	/**
	 * 得到2个日期之间间隔的天数
	 * 
	 * @param date1  yyyyMdd
	 * @param date2  yyyyMMdd
	 * @return
	 */
	public static int differ(String date1, String date2) { 
		try {
			SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMdd");
			Date date11 = fmt.parse(date1); 
			Date date22 = fmt.parse(date2); 
			int day = differ(date11, date22);
			return day;
		} catch (ParseException e) {
			throw new RuntimeException("日期格式错误！");
		}
	}

	private static int differ(Date date1, Date date2) { 
		long day = date2.getTime() / 86400000 - date1.getTime() / 86400000; // 用立即数，减少乘法计算的开销
		return (int) day;
	}

	/**
	 * 得到2个日期之间间隔的分钟数
	 * 
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static int differMinute(String dateTime1, String dateTime2) {
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss"); 
			Date dateTime11 = dateFormat.parse(dateTime1);
			Date dateTime22 = dateFormat.parse(dateTime2);
			int diffMinute = differMinute(dateTime11, dateTime22);
			return diffMinute;
		}catch (RuntimeException e) {
		    throw e;
		}catch (Exception e) {
		  throw new RuntimeException("日期格式错误！");
		}
	}


	private static int differMinute(Date dateTime1, Date dateTime2) { 
		long second = dateTime2.getTime() / 1000 - dateTime1.getTime() / 1000; // 用立即数，减少乘法计算的开销
		// 先计算相差秒数，再除60得相差分钟数，这样更精确
		return (int) (second / 60);
	}
	
	/**
	 * 获取指定日期为星期几
	 * @param date yyyyMMdd
	 * @return 星期一 | 星期二....
	 */
    public static String getDayOfWeekByDate(String date) {  
        String dayOfweek = "-1";  
        try {  
        	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd"); 
        	dateFormat.applyPattern("yyyyMMdd");
            Date myDate = dateFormat.parse(date);  
            dateFormat.applyPattern("E");
            String str = dateFormat.format(myDate);  
            dayOfweek = str;  
        } catch (Exception e) {  
        	throw new RuntimeException("日期格式错误！");
        }  
        return dayOfweek;   
	} 
	
	/**
	 * 获取当前月份的天数
	 * @return
	 */
	public static int getLastDayOfCurrentMonth(){
		return getLastDayOfYearMonth(0, 0);
	}
	
	/**
	 * 获取指定年份和月的天数
	 * @param year
	 * @param month
	 * @return
	 */
	public static int getLastDayOfYearMonth(int year, int month) {
		Calendar a = Calendar.getInstance();  
		if(year > 0)
			a.set(Calendar.YEAR, year);  
		if(month > 0)
			a.set(Calendar.MONTH, month - 1);  
        a.set(Calendar.DATE, 1);  
        a.roll(Calendar.DATE, -1);  
        return a.get(Calendar.DATE);  
	} 
	
	/**
	 * 获取两个日期之间所有的星期天
	 * @param startDate 起始日期
	 * @param endDate 结束日期
	 * @param resultSet 将结果集存放在此set(实现类用TreeSet, 可实现结果集排序)<br>
	 * (注:) 如果起始日期 和 结束日期刚好也是星期天, 也会包括在TreeSet中
	 * @return TreeSet: [20151115,20151122,...] 或 []
	 */
	public static void getSundayBetweenDay(Date startDate, Date endDate, Set<String> resultSet){
		if(null == startDate || endDate == null)
			return;
		Calendar cal = Calendar.getInstance();
		cal.setTime(startDate);
		int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
		String str1 = formatDate(startDate, "yyyyMMdd");
		String str2 = formatDate(endDate, "yyyyMMdd");
		if(dayOfWeek == 1) //星期天[顺序: 星期天, 一, 二, ... 六]
			resultSet.add(str1);
		Calendar cal2 = Calendar.getInstance();
		cal2.setTime(endDate);
		int dayOfWeek2 = cal2.get(Calendar.DAY_OF_WEEK);
		if(dayOfWeek2 == 1 && !resultSet.contains(str2)) //星期天
			resultSet.add(str2);
		cal.add(Calendar.DAY_OF_MONTH, 7);
		Date newDate = cal.getTime();
		str1 = formatDate(newDate, "yyyyMMdd");
		if(Integer.parseInt(str1) > Integer.parseInt(str2)){
			return;
		}else{
			resultSet.add(str1);
			getSundayBetweenDay(newDate, endDate, resultSet);//递归
		}
	} 
}
