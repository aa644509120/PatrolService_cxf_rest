/** * A级 */
package com.meiah.core.util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Random;

import org.apache.log4j.Logger;

/**
 * Date Utility Class This is used to convert Strings to Dates and Timestamps
 * 
 * <p>
 * <a href="DateUtil.java.html"><i>View Source</i></a>
 * </p>
 * 
 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible</a> Modified by
 *         <a href="mailto:dan@getrolling.com">Dan Kibler </a> to correct time
 *         pattern. Minutes should be mm not MM (MM is month).
 */
public class DateUtil {
	// ~ Static fields/initializers
	// =============================================

//	private static Log log = LogFactory.getLog(DateUtil.class);
	private static Logger log = Logger.getLogger(DateUtil.class);
	private static String timePattern = "HH:mm";
	private static Random random = new Random(new Date().getTime());
	
	public static String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
	public static String DATE_FORMAT_NUM = "yyyyMMddHHmmss";
	public static String DATE_FOREMT_NOTIME="yyyyMMdd";

	// ~ Methods
	// ================================================================

	/**
	 * Return default datePattern (MM/dd/yyyy)
	 * 
	 * @return a string representing the date pattern on the UI
	 */
	public static String getDatePattern() {
		// Locale locale = LocaleContextHolder.getLocale();
		// try {
		// defaultDatePattern = ResourceBundle.getBundle(Constants.BUNDLE_KEY,
		// locale)
		// .getString("date.format");
		// } catch (MissingResourceException mse) {
		// defaultDatePattern = "yyyy-MM-dd";
		// }
		String defaultDatePattern = "yyyy-MM-dd HH:mm:ss";
		return defaultDatePattern;
	}

	public static String getDateTimePattern() {
		return DateUtil.getDatePattern() + " HH:mm:ss.S";
	}

	public static Timestamp convertDate2TimStamp(Date d){
		Timestamp stamp = new Timestamp(d.getTime());
		return stamp;
	}
	
	/**
	 * This method attempts to convert an Oracle-formatted date in the form
	 * dd-MMM-yyyy to mm/dd/yyyy.
	 * 
	 * @param aDate
	 *            date from database as a string
	 * @return formatted string for the ui
	 */
	public static final String getDate(Date aDate) {
		SimpleDateFormat df = null;
		String returnValue = "";

		if (aDate != null) {
			df = new SimpleDateFormat(getDateTimePattern());
			returnValue = df.format(aDate);
		}

		return (returnValue);
	}
	
	
	public static final String getDate(Date aDate , String pattern) {
		SimpleDateFormat df = null;
		String returnValue = "";

		if (aDate != null) {
			df = new SimpleDateFormat(pattern);
			returnValue = df.format(aDate);
		}

		return (returnValue);
	}
	

	public static final Date getDate(String aDate) {
		try {
			return convertStringToDate(aDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * This method generates a string representation of a date/time in the
	 * format you specify on input
	 * 
	 * @param aMask
	 *            the date pattern the string is in
	 * @param strDate
	 *            a string representation of a date
	 * @return a converted Date object
	 * @see java.text.SimpleDateFormat
	 * @throws ParseException
	 */
	public static final Date convertStringToDate(String aMask, String strDate) throws ParseException {
		SimpleDateFormat df = null;
		Date date = null;
		df = new SimpleDateFormat(aMask);

		if (log.isDebugEnabled()) {
			log.debug("converting '" + strDate + "' to date with mask '" + aMask + "'");
		}

		try {
			date = df.parse(strDate);
		} catch (ParseException pe) {
			// log.error("ParseException: " + pe);
			throw new ParseException(pe.getMessage(), pe.getErrorOffset());
		}

		return (date);
	}

	/**
	 * This method returns the current date time in the format: MM/dd/yyyy HH:MM
	 * a
	 * 
	 * @param theTime
	 *            the current time
	 * @return the current date/time
	 */
	public static String getTimeNow(Date theTime) {
		return getDateTime(timePattern, theTime);
	}

	/**
	 * This method returns the current date in the format: MM/dd/yyyy
	 * 
	 * @return the current date
	 * @throws ParseException
	 */
	public static Calendar getToday() throws ParseException {
		Date today = new Date();
		SimpleDateFormat df = new SimpleDateFormat(getDatePattern());

		// This seems like quite a hack (date -> string -> date),
		// but it works ;-)
		String todayAsString = df.format(today);
		Calendar cal = new GregorianCalendar();
		cal.setTime(convertStringToDate(todayAsString));

		return cal;
	}

	public static Date getFormatDate() throws ParseException{
		Date today = new Date();
		SimpleDateFormat df = new SimpleDateFormat(getDatePattern());
        String date = df.format(today);
        Date dt = convertStringToDate(date);
		return dt;
	}
	/**
	 * This method generates a string representation of a date's date/time in
	 * the format you specify on input
	 * 
	 * @param aMask
	 *            the date pattern the string is in
	 * @param aDate
	 *            a date object
	 * @return a formatted string representation of the date
	 * 
	 * @see java.text.SimpleDateFormat
	 */
	public static final String getDateTime(String aMask, Date aDate) {
		SimpleDateFormat df = null;
		String returnValue = "";

		if (aDate == null) {
			log.error("aDate is null!");
		} else {
			df = new SimpleDateFormat(aMask);
			returnValue = df.format(aDate);
		}

		return (returnValue);
	}

	/**
	 * This method generates a string representation of a date based on the
	 * System Property 'dateFormat' in the format you specify on input
	 * 
	 * @param aDate
	 *            A date to convert
	 * @return a string representation of the date
	 */
	public static final String convertDateToString(Date aDate) {
		return getDateTime(getDatePattern(), aDate);
	}
	
	/**
	 * 增加一个自定义的格式类型
	 * add by huanglb, 20121106
	 * @param aDate
	 * @param pattern
	 * @return
	 */
	public static final String convertDateToString(Date aDate, String pattern) {
		return getDateTime(pattern, aDate);
	}
	
	/**
	 * 返回与当前时间间隔的天数
	 * add by huanglb, 20121106
	 * @param count
	 * @return yyyy-MM-dd
	 */
	public static final String calculateDateFromNow(int count) {
		Date d = DateUtil.add(new Date(), Calendar.DAY_OF_YEAR, count);
		return DateUtil.convertDateToString(d, "yyyy-MM-dd");
	}
	
	public static void main(String[] args) {
		System.err.println(getIncrementNo());
	}

	/**
	 * This method converts a String to a date using the datePattern
	 * 
	 * @param strDate
	 *            the date to convert (in format MM/dd/yyyy)
	 * @return a date object
	 * 
	 * @throws ParseException
	 */
	public static Date convertStringToDate(String strDate) throws ParseException {
		Date aDate = null;

		try {
			if (log.isDebugEnabled()) {
				log.debug("converting date with pattern: " + getDatePattern());
			}

			aDate = convertStringToDate(getDatePattern(), strDate);
		} catch (ParseException pe) {
			log.error("Could not convert '" + strDate + "' to a date, throwing exception");
			pe.printStackTrace();
			throw new ParseException(pe.getMessage(), pe.getErrorOffset());

		}

		return aDate;
	}

	public final static String getIncrementNo() {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddhhmmss");
		int nextInt = random.nextInt(9999);
		StringBuffer stringBuffer = new StringBuffer(simpleDateFormat.format(new Date()));
		String valueOf = String.valueOf(nextInt);
		if (valueOf.length() < 4) {
			for (int i = 0; i < 4 - valueOf.length(); i++) {
				stringBuffer.append(0);
			}
		}
		stringBuffer.append(valueOf);
		return stringBuffer.toString();
	}

	public static String get_yyyymmddhh24miss(int daydiff) {
		java.util.Calendar Date_time;
		String Tyear, Tmonth, Tday, Thour, Tminute, Tsecond;
		Date_time = Calendar.getInstance();
		Date_time.add(Calendar.HOUR, daydiff);
		Tyear = String.valueOf(Date_time.get(Calendar.YEAR));
		Tmonth = String.valueOf(Date_time.get(Calendar.MONTH) + 1);
		if (Tmonth.length() < 2)
			Tmonth = "0" + Tmonth;
		Tday = String.valueOf(Date_time.get(Calendar.DAY_OF_MONTH));
		if (Tday.length() < 2)
			Tday = "0" + Tday;
		Thour = String.valueOf(Date_time.get(Calendar.HOUR_OF_DAY));
		if (Thour.length() < 2)
			Thour = "0" + Thour;
		Tminute = String.valueOf(Date_time.get(Calendar.MINUTE));
		if (Tminute.length() < 2)
			Tminute = "0" + Tminute;
		Tsecond = String.valueOf(Date_time.get(Calendar.SECOND));
		if (Tsecond.length() < 2)
			Tsecond = "0" + Tsecond;
		return Tyear + Tmonth + Tday + Thour + Tminute + Tsecond;
	}

	public static String getFormateDate(Date date, String format, int field, int amount) {
		if (date == null) {
			date = new Date();
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(field, amount);
		if (Validator.isNull(format))
			format = "yyyy-MM-dd HH:mm:ss";
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(cal.getTime());
	}

	public static Date add(Date date, int field, int amount) {
		if (date == null) {
			date = new Date();
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(field, amount);
		return cal.getTime();
	}

	public static String formatDateTime(Date date, String pattern) {
		if (date == null)
			return "";
		if (pattern == null || pattern.trim().length() == 0)
			pattern = "yyyy-MM-dd HH:mm:ss";
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		return (sdf.format(date));
	}

	public static String getFormateDate(Date date, int field, int amount) {
		if (date == null) {
			date = new Date();
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(field, amount);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(cal.getTime());
	}

	/**
	 * 计算两个日期之间的差值
	 * 
	 * @param k
	 *            日期1
	 * @param d
	 *            日期2
	 * @return
	 */
	public static int diffDate(int i, Date k, Date d) {
		int diffnum = 0;
		int needdiff = 0;
		switch (i) {
		case Calendar.SECOND: {
			needdiff = 1000;
			break;
		}
		case Calendar.MINUTE: {
			needdiff = 60 * 1000;
			break;
		}
		case Calendar.HOUR: {
			needdiff = 60 * 60 * 1000;
			break;
		}
		case Calendar.DATE: {
			needdiff = 24 * 60 * 60 * 1000;
			break;
		}
		}
		if (needdiff != 0) {
			diffnum = (int) (d.getTime() / needdiff) - (int) (k.getTime() / needdiff);
			;
		}

		return diffnum;
	}

	// 获得本周一的日期
	public static Date getFristOFWeek() {
		int mondayPlus =getMondayPlus();
		GregorianCalendar currentDate = new GregorianCalendar();
		currentDate.add(GregorianCalendar.DATE, mondayPlus);
		return currentDate.getTime();
	}

	// 获得当前日期与本周日相差的天数
	public static int getMondayPlus() {
		Calendar cd = Calendar.getInstance();
		// 获得今天是一周的第几天，星期日是第一天，星期二是第二天......
		int dayOfWeek = cd.get(Calendar.DAY_OF_WEEK); // 因为按中国礼拜一作为第一天所以这里减1
		if (dayOfWeek == 1) {
			return 0;
		} else {
			return 1 - dayOfWeek;
		}
	}
	
	public static String getFristOFMonth(String format){
		if (Validator.isNull(format)) {
			format="yyyy-MM-dd";
		}
		Calendar cal  =   new  GregorianCalendar();
		cal.set( Calendar.DATE,  1 );
		return DateUtil.formatDateTime(cal.getTime(), format);
	}
	
	public static Date addDay(String date,int dayNumber) throws ParseException{
		Date tempDate = DateUtil.convertStringToDate("yyyy-MM-dd", date);
		Calendar   cal=Calendar.getInstance();
		cal.setTime(tempDate);
		cal.add(Calendar.DAY_OF_MONTH,dayNumber);
		return cal.getTime();
	}
	
	public static String getYesterday(String format) throws ParseException{
		if (Validator.isNull(format)) {
			format="yyyy-MM-dd";
		}
		Calendar cal = DateUtil.getToday();
		cal.add(Calendar.DATE, -1);
		return DateUtil.formatDateTime(cal.getTime(),format);
	}

	public final static Date getOneDayStart(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}
	
	public static Long StringToTime(String date,String format){
		Date tempDate = null;
		try {
			tempDate = DateUtil.convertStringToDate(format, date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return tempDate.getTime();
	}
	
	public final static Date getPublishTime(String publishTime) throws ParseException {
		return convertStringToDate(DATE_FORMAT_NUM, publishTime);
	}

	public final static Date getPublishDate(String publishTime) throws ParseException {
		return convertStringToDate("yyyyMMdd", publishTime);
	}
	/***
     * 获取日期相差天数
     * 
     * @param day1
     * @param day2
     * @return
     */
    public static long getDays(String date1, String date2) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        long days = 0;
        try {
            Date d1 = sdf.parse(date1);
            Date d2 = sdf.parse(date2);
            days = (d2.getTime() - d1.getTime()) / (24 * 60 * 60 * 1000);
        } catch (ParseException e) {
        	log.error("", e);
        }
        return days;
    }

	 /***
     * 获取若干天后的日期
     * 
     * @param date
     * @param days
     * @return
     */
    public static String getDateAfterDays(String date, long days) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String retDate = date;
        try {
            Date d1 = sdf.parse(date);
            Date d = new Date(d1.getTime() + days * 24 * 60 * 60 * 1000);
            retDate = sdf.format(d);
        } catch (ParseException e) {
            log.error("", e);
        }
        return retDate;
    }
    
    public static String getNowDateBefore(int day, int iFlag) {
		return getDateBefore(new Date(), day, iFlag);
	}
    
	// 获得几天前的时间
	public static String getDateBefore(Date d, int day, int iFlag) {
		Calendar now = Calendar.getInstance();
		now.setTime(d);
		now.set(Calendar.DATE, now.get(Calendar.DATE) - day);
		return DatetimetoStr(now.getTime(), iFlag);
	}
	
	public static final String DatetimetoStr(Date date, int iFlag) {
		SimpleDateFormat simpleDateFormat;
		switch (iFlag) {
		case 0:
			simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
			break;
		case 1:
			simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			break;
		case 2:
			simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
			break;
		case 3:
			simpleDateFormat = new SimpleDateFormat("yyyy.MM.dd");
			break;
		case 4:
			simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
			break;
		case 5:
			simpleDateFormat = new SimpleDateFormat("yyyy-MM");
			break;
		case 6:
			simpleDateFormat = new SimpleDateFormat("yyyy-MM HH");
			break;
		case 7:
			simpleDateFormat = new SimpleDateFormat("yyyy-MM HH:mm");
			break;
		case 8:
			simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			break;
		case 9:
			simpleDateFormat = new SimpleDateFormat("HH");
			break;
		default:
			simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		}

		return simpleDateFormat.format(date);
	}

	public static List<String> getDateList(String btime, String etime,
			String string, String string2) throws ParseException {
		long be = getDays(btime,etime);
		List<String> list = new ArrayList<String>();
		for(int i = 0 ; i <= be;i++){
			Date date = addDay(btime,i);
			String d = DatetimetoStr(date, 4);
			list.add(d);
		}
		return list;
	}
    
}
