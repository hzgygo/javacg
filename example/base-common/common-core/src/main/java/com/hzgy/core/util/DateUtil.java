package com.hzgy.core.util;


import com.hzgy.core.annotation.ThreadSafe;
import com.hzgy.core.common.DateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.Locale;
import java.text.*;
import java.util.*;



/**
 * 线程安全的日期工具类
 */
@ThreadSafe
public class DateUtil {

    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern(DateFormat.DATE_HYPHEN.getValue());
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern(DateFormat.DATE_TIME_HYPHEN.getValue());
    private static final String ZERO_HOUR_MINUTE = " 00:00:00";
    public static final String MAX_TIME = "2999-12-31 23:59:59";

    private static final String BLANK = "";
    private static final Long MIN_TIMESTAMP = 0L;
    private static final Long MAX_TIMESTAMP = 2147483648000L;
    private static boolean LENIENT_DATE = false;
    private static Random random = new java.util.Random();
    private static final int ID_BYTES = 10;


    public enum TimeTransformPeriodEnum {
        DAY,WEEK,MONTH,QUARTER,HALFYEAR,YEAR
    }

    public static String format(Date date) {
        String timeStr = formatTime(date);
        if (timeStr.endsWith(ZERO_HOUR_MINUTE)) {
            return timeStr.replace(ZERO_HOUR_MINUTE, BLANK);
        }
        return timeStr;
    }

    public static String formatTime(Date date) {
        return format(date, TIME_FORMATTER);
    }

    public static String format(Date date, String formatter) {
        if (date == null) {
            return null;
        }
        DateTimeFormatter dformatter = DateTimeFormatter.ofPattern(formatter);
        return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault()).format(dformatter);
    }

    public static String format(String date, String formatter) {
        if (date == null) {
            return null;
        }
        DateTimeFormatter dformatter = DateTimeFormatter.ofPattern(formatter);
        return LocalDateTime.ofInstant(DateUtil.parseDate(date).toInstant(), ZoneId.systemDefault()).format(dformatter);
    }

    public static String format(Date date, DateTimeFormatter formatter) {
        if (date == null) {
            return null;
        }
        return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault()).format(formatter);
    }

    public static String formatDate(Date date) {
        return format(date, DATE_FORMATTER);
    }

    /**
     * 获取当前指定格式的日期字符串
     * @param dateFormat 格式字符串
     * @return 日期字符串
     */
    public static String currentFormatDate(String dateFormat){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(dateFormat);
        return format(new Date(), formatter);
    }

    public static Date parse(String dateStr) {
        try {
            return parseTime(dateStr);
        } catch (DateTimeParseException e) {
            // swallow try date type again
        }
        return parseDate(dateStr);
    }

    public static Date parseTime(String dateStr) {
        if (StringUtil.isEmpty(dateStr)) {
            return null;
        }
        Instant instant = LocalDateTime.parse(dateStr, TIME_FORMATTER).atZone(ZoneId.systemDefault()).toInstant();
        return Date.from(instant);
    }

    public static Date parseTime(String dateStr,String dateFormat) {
        if (StringUtil.isEmpty(dateStr)) {
            return null;
        }
        Instant instant = LocalDateTime.parse(dateStr, DateTimeFormatter.ofPattern(dateFormat)).atZone(ZoneId.systemDefault()).toInstant();
        return Date.from(instant);
    }

    public static Date parseDate(String dateStr) {
        if (StringUtil.isEmpty(dateStr)) {
            return null;
        }
        dateStr += ZERO_HOUR_MINUTE;
        return parseTime(dateStr);
    }

    public static boolean validDate(String dateStr) {
        if (StringUtil.isEmpty(dateStr)) {
            return true;
        }
        String str;
        try {
            Date date = parseDate(dateStr);
            str = formatDate(date);
        } catch (DateTimeParseException e) {
            return false;
        }
        return dateStr.equals(str);
    }

    public static boolean validTime(String dateStr) {
        if (StringUtil.isEmpty(dateStr)) {
            return true;
        }
        String str;
        try {
            Date date = parseTime(dateStr);
            str = formatTime(date);
        } catch (DateTimeParseException e) {
            return false;
        }
        return dateStr.equals(str);
    }

    /**
     * 校验时间戳是否有效 1970-01-01 08:00:01 到 2038-01-19 11:14:07
     *
     * @param dateStr
     * @return
     */
    public static boolean validTimeStamp(String dateStr) {
        //如果参数为空，返回true
        if (StringUtil.isEmpty(dateStr)) {
            return true;
        }
        if (!validTime(dateStr)) {
            return false;
        }
        Date date = parseTime(dateStr);
        Long timestamp = date.getTime();
        return timestamp > MIN_TIMESTAMP && timestamp < MAX_TIMESTAMP;
    }

    /**
     * 将日期转换为字符串
     *
     * @param date date日期
     * @return 日期字符串
     */

    public static String parseDateFormat(Date date) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(DateFormat.DATE_TIME_VIRGULE.getValue(), Locale.CHINA);
            return sdf.format(date);
        } catch (Exception ex) {
            return "";
        }
    }


    public synchronized static String generateId() {
        StringBuffer result = new StringBuffer();
        result = result.append(System.currentTimeMillis());
        for (int i = 0; i < ID_BYTES; i++) {
            result = result.append(random.nextInt(10));
        }
        return result.toString();
    }

    protected static final float normalizedJulian(float JD) {

        float f = Math.round(JD + 0.5f) - 0.5f;

        return f;
    }

    /**
     * Returns the Date from a julian. The Julian date will be converted to noon GMT,
     * such that it matches the nearest half-integer (i.e., a julian date of 1.4 gets
     * changed to 1.5, and 0.9 gets changed to 0.5.)
     *
     * @param JD the Julian date
     * @return the Gregorian date
     */
    public static final Date toDate(float JD) {

        /* To convert a Julian Day Number to a Gregorian date, assume that it is for 0 hours, Greenwich time (so
         * that it ends in 0.5). Do the following calculations, again dropping the fractional part of all
         * multiplicatons and divisions. Note: This method will not give dates accurately on the
         * Gregorian Proleptic Calendar, i.e., the calendar you get by extending the Gregorian
         * calendar backwards to years earlier than 1582. using the Gregorian leap year
         * rules. In particular, the method fails if Y<400. */
        float Z = (normalizedJulian(JD)) + 0.5f;
        float W = (int) ((Z - 1867216.25f) / 36524.25f);
        float X = (int) (W / 4f);
        float A = Z + 1 + W - X;
        float B = A + 1524;
        float C = (int) ((B - 122.1) / 365.25);
        float D = (int) (365.25f * C);
        float E = (int) ((B - D) / 30.6001);
        float F = (int) (30.6001f * E);
        int day = (int) (B - D - F);
        int month = (int) (E - 1);

        if (month > 12) {
            month = month - 12;
        }

        int year = (int) (C - 4715); //(if Month is January or February) or C-4716 (otherwise)

        if (month > 2) {
            year--;
        }

        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month - 1); // damn 0 offsets
        c.set(Calendar.DATE, day);

        return c.getTime();
    }

    /**
     * Returns the days between two dates. Positive values indicate that
     * the second date is after the first, and negative values indicate, well,
     * the opposite. Relying on specific times is problematic.
     *
     * @param early the "first date"
     * @param late the "second date"
     * @return the days between the two dates
     */
    public static final int daysBetween(Date early, Date late) {

        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        c1.setTime(early);
        c2.setTime(late);

        return daysBetween(c1, c2);
    }

    /**
     * Returns the days between two dates. Positive values indicate that
     * the second date is after the first, and negative values indicate, well,
     * the opposite.
     *
     * @param early
     * @param late
     * @return the days between two dates.
     */
    public static final int daysBetween(Calendar early, Calendar late) {

        return (int) (toJulian(late) - toJulian(early));
    }

    /**
     * Return a Julian date based on the input parameter. This is
     * based from calculations found at
     * <a href="http://quasar.as.utexas.edu/BillInfo/JulianDatesG.html">Julian Day Calculations
     * (Gregorian Calendar)</a>, provided by Bill Jeffrys.
     * @param c a calendar instance
     * @return the julian day number
     */
    public static final float toJulian(Calendar c) {

        int Y = c.get(Calendar.YEAR);
        int M = c.get(Calendar.MONTH);
        int D = c.get(Calendar.DATE);
        int A = Y / 100;
        int B = A / 4;
        int C = 2 - A + B;
        float E = (int) (365.25f * (Y + 4716));
        float F = (int) (30.6001f * (M + 1));
        float JD = C + D + E + F - 1524.5f;

        return JD;
    }

    /**
     * Return a Julian date based on the input parameter. This is
     * based from calculations found at
     * <a href="http://quasar.as.utexas.edu/BillInfo/JulianDatesG.html">Julian Day Calculations
     * (Gregorian Calendar)</a>, provided by Bill Jeffrys.
     * @param date
     * @return the julian day number
     */
    public static final float toJulian(Date date) {

        Calendar c = Calendar.getInstance();
        c.setTime(date);

        return toJulian(c);
    }

    /**
     * @param isoString
     * @param fmt
     * @param field   Calendar.YEAR/Calendar.MONTH/Calendar.DATE
     * @param amount
     * @return
     * @throws ParseException
     */
    public static final String dateIncrease(String isoString, String fmt,
                                            int field, int amount) {

        try {
            Calendar cal = GregorianCalendar.getInstance(TimeZone.getTimeZone(
                    "GMT"));
            cal.setTime(stringToDate(isoString, fmt, true));
            cal.add(field, amount);

            return dateToString(cal.getTime(), fmt);

        } catch (Exception ex) {
            return null;
        }
    }
    /**
     * 日期增加
     * @param date     日期
     * @param fmt      格式
     * @param field    字段
     * @param amount   增加值
     * @return
     */
    public static final String dateIncrease(Date date, String fmt,int field, int amount) {
        try {
            Calendar cal = GregorianCalendar.getInstance(TimeZone.getTimeZone("GMT"));
            cal.setTime(date);
            cal.add(field, amount);
            return dateToString(cal.getTime(), fmt);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
    /**
     * Time Field Rolling function.
     * Rolls (up/down) a single unit of time on the given time field.
     * @param isoString
     * @param field the time field.
     * @param up Indicates if rolling up or rolling down the field value.
     * @exception ParseException if an unknown field value is given.
     */
    public static final String roll(String isoString, String fmt, int field,boolean up) throws ParseException {
        Calendar cal = GregorianCalendar.getInstance(TimeZone.getTimeZone("GMT"));
        cal.setTime(stringToDate(isoString, fmt));
        cal.roll(field, up);
        return dateToString(cal.getTime(), fmt);
    }

    /**
     * Time Field Rolling function.
     * Rolls (up/down) a single unit of time on the given time field.
     * @param isoString
     * @param field the time field.
     * @param up Indicates if rolling up or rolling down the field value.
     * @exception ParseException if an unknown field value is given.
     */
    public static final String roll(String isoString, int field, boolean up) throws
            ParseException {
        return roll(isoString, DateFormat.DATE_TIME_HYPHEN.getValue(), field, up);
    }

    /**
     *  java.util.Date
     * @param dateText
     * @param format
     * @param lenient
     * @return
     */
    public static Date stringToDate(String dateText, String format,boolean lenient) {
        if (dateText == null) {
            return null;
        }
        java.text.DateFormat df = null;
        try {
            if (format == null) {
                df = new SimpleDateFormat();
            } else {
                df = new SimpleDateFormat(format);
            }
            // setLenient avoids allowing dates like 9/32/2001
            // which would otherwise parse to 10/2/2001
            df.setLenient(true);
            return df.parse(dateText);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * @return Timestamp
     */
    public static java.sql.Timestamp getCurrentTimestamp() {
        return new java.sql.Timestamp(new java.util.Date().getTime());
    }

    /** java.util.Date
     * @param dateString
     * @param format
     * @return
     */
    public static Date stringToDate(String dateString, String format) {

        return stringToDate(dateString, format, LENIENT_DATE);
    }

    /**
     * java.util.Date
     * @param dateString
     */
    public static Date stringToDate(String dateString) {
        return stringToDate(dateString, DateFormat.DATE_HYPHEN.getValue(), LENIENT_DATE);
    }

    /**
     * @return
     * @param pattern
     * @param date
     */
    public static String dateToString(Date date, String pattern) {
        if (date == null) {
            return null;
        }
        try {
            SimpleDateFormat sfDate = new SimpleDateFormat(pattern);
            sfDate.setLenient(false);
            return sfDate.format(date);
        } catch (Exception e) {

            return null;
        }
    }

    /**
     * yyyy-MM-dd
     * @param date
     * @return
     */
    public static String dateToString(Date date) {
        return dateToString(date, DateFormat.DATE_HYPHEN.getValue());
    }

    /**
     * @return
     */
    public static Date getCurrentDateTime() {
        java.util.Calendar calNow = java.util.Calendar.getInstance();
        java.util.Date dtNow = calNow.getTime();

        return dtNow;
    }

    /**
     *
     * @param pattern
     * @return
     */
    public static String getCurrentDateString(String pattern) {
        return dateToString(getCurrentDateTime(), pattern);
    }

    /**
     *   yyyy-MM-dd
     * @return
     */
    public static String getCurrentDateString() {
        return dateToString(getCurrentDateTime(), DateFormat.DATE_HYPHEN.getValue());
    }

    /**
     * 返回固定格式的当前时间
     *   yyyy-MM-dd hh:mm:ss
     * @return
     */
    public static String dateToStringWithTime( ) {
        return dateToString(new java.util.Date(), DateFormat.DATE_TIME_HYPHEN.getValue());
    }

    /**
     *   yyyy-MM-dd hh:mm:ss
     * @param date
     * @return
     */
    public static String dateToStringWithTime(Date date) {
        return dateToString(date, DateFormat.DATE_TIME_HYPHEN.getValue());
    }

    /**
     *
     * @param date
     * @param days
     * @return java.util.Date
     */
    public static Date dateIncreaseByDay(Date date, int days) {
        Calendar cal = GregorianCalendar.getInstance(TimeZone.getTimeZone("GMT"));
        cal.setTime(date);
        cal.add(Calendar.DATE, days);
        return cal.getTime();
    }

    /**
     *
     * @param date
     * @param mnt
     * @return java.util.Date
     */
    public static Date dateIncreaseByMonth(Date date, int mnt) {
        Calendar cal = GregorianCalendar.getInstance(TimeZone.getTimeZone("GMT"));
        cal.setTime(date);
        cal.add(Calendar.MONTH, mnt);
        return cal.getTime();
    }

    /**
     *
     * @param date
     * @param mnt
     * @return java.util.Date
     */
    public static Date dateIncreaseByYear(Date date, int mnt) {
        Calendar cal = GregorianCalendar.getInstance(TimeZone.getTimeZone("GMT"));
        cal.setTime(date);
        cal.add(Calendar.YEAR, mnt);
        return cal.getTime();
    }

    /**
     *
     * @param date   yyyy-MM-dd
     * @param days
     * @return  yyyy-MM-dd
     */
    public static String dateIncreaseByDay(String date, int days) {
        return dateIncreaseByDay(date, DateFormat.DATE.getValue(), days);
    }

    /**
     * @param date
     * @param fmt
     * @param days
     * @return
     */
    public static String dateIncreaseByDay(String date, String fmt, int days) {
        return dateIncrease(date, fmt, Calendar.DATE, days);
    }

    /**
     *
     * @param src
     * @param srcfmt
     * @param desfmt
     * @return
     */
    public static String stringToString(String src, String srcfmt,String desfmt) {
        return dateToString(stringToDate(src, srcfmt), desfmt);
    }

    /**
     *
     * @param date
     * @return string
     */
    public static String getYear(Date date) {
        java.text.SimpleDateFormat formater = new java.text.SimpleDateFormat("yyyy");
        String cur_year = formater.format(date);
        return cur_year;
    }

    /**
     *
     * @param date
     * @return string
     */
    public static String getMonth(Date date) {
        java.text.SimpleDateFormat formater = new java.text.SimpleDateFormat("MM");
        String cur_month = formater.format(date);
        return cur_month;
    }

    /**
     * @param date
     * @return string
     */
    public static String getDay(Date date) {
        java.text.SimpleDateFormat formater = new java.text.SimpleDateFormat("dd");
        String cur_day = formater.format(date);
        return cur_day;
    }

    /**
     * @param date
     * @return string
     */
    public static String getHour(Date date) {
        java.text.SimpleDateFormat formater = new java.text.SimpleDateFormat("HH");
        String cur_day = formater.format(date);
        return cur_day;
    }

    public static int getMinsFromDate(java.util.Date dt) {
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(dt);
        int hour = cal.get(Calendar.HOUR_OF_DAY);
        int min = cal.get(Calendar.MINUTE);
        return ((hour * 60) + min);
    }

    /**
     * Function to convert String to Date Object. If invalid input then current or next day date
     * is returned (Added by Ali Naqvi on 2006-5-16).
     * @param str String input in YYYY-MM-DD HH:MM[:SS] format.
     * @param isExpiry boolean if set and input string is invalid then next day date is returned
     * @return Date
     */
    public static java.util.Date convertToDate(String str, boolean isExpiry) {
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        java.util.Date dt = null;
        try {
            dt = fmt.parse(str);
        } catch (ParseException ex) {
            Calendar cal = Calendar.getInstance();
            if (isExpiry) {
                cal.add(Calendar.DAY_OF_MONTH, 1);
                cal.set(Calendar.HOUR_OF_DAY, 23);
                cal.set(Calendar.MINUTE, 59);
            } else {
                cal.set(Calendar.HOUR_OF_DAY, 0);
                cal.set(Calendar.MINUTE, 0);
            }
            dt = cal.getTime();
        }
        return dt;
    }

    public static String convertToStringDate(long times,String pattern){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        Date date = new Date(times);
        return simpleDateFormat.format(date);
    }

    public static java.util.Date convertToDate(String str) {
        SimpleDateFormat fmt = new SimpleDateFormat(DateFormat.DATE_MINUTE_HYPHEN.getValue());
        java.util.Date dt = null;
        try {
            dt = fmt.parse(str);
        } catch (ParseException ex) {
            dt = new java.util.Date();
        }
        return dt;
    }

    public static String dateFromat(Date date, int minute) {
        String dateFormat = null;
        int year = Integer.parseInt(getYear(date));
        int month = Integer.parseInt(getMonth(date));
        int day = Integer.parseInt(getDay(date));
        int hour = minute / 60;
        int min = minute % 60;
        dateFormat = String.valueOf(year)
                + (month > 9 ? String.valueOf(month) :  "0" + String.valueOf(month))
                + (day > 9 ? String.valueOf(day) : "0" + String.valueOf(day))
                + " "
                + (hour > 9 ? String.valueOf(hour) : "0" + String.valueOf(hour))
                + (min > 9 ? String.valueOf(min) : "0" + String.valueOf(min))
                + "00";
        return dateFormat;
    }
    public static String sDateFormat() {
        return new SimpleDateFormat(DateFormat.DATE_TIME_VIRGULE.getValue()).format(Calendar.getInstance().getTime());
    }
    public static void main(String[] args){
        String timeDir=DateUtil.dateToString(new Date(),DateFormat.DATE_TIME_HYPHEN.getValue());
        System.out.println(timeDir);
    }



    //获取本周的最后一天
    public static Calendar getWeekEnd(Date date){
        Calendar cal = Calendar.getInstance();
        cal.clear();
        cal.setTime(date);
        int day_of_week = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (day_of_week == 0 ) {
            day_of_week = 7 ;
        }
        cal.add(Calendar.DATE , -day_of_week + 7 );
        return cal;
    }

    //获取本月的最后一天
    public static Calendar getMonthEnd(Date date){
        Calendar cal = Calendar.getInstance();
        cal.clear();
        cal.setTime(date);
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
        return cal;
    }

    //获取本季的最后一天
    public static Calendar getQuarterEnd(Date date){
        Calendar cal = Calendar.getInstance();
        cal.clear();
        cal.setTime(date);
        int currentMonth = cal.get(Calendar.MONTH) + 1;
        if (currentMonth >= 1 && currentMonth <= 3){
            cal.set(Calendar.MONTH, 2);
        }else if (currentMonth >= 4 && currentMonth <= 6){
            cal.set(Calendar.MONTH, 5);
        }else if (currentMonth >= 7 && currentMonth <= 9){
            cal.set(Calendar.MONTH, 8);
        }else if (currentMonth >= 10 && currentMonth <= 12){
            cal.set(Calendar.MONTH, 11);
        }
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
        return cal;
    }

    //获取本半年的最后一天
    public static Calendar getHalfYearEnd(Date date){
        Calendar cal = Calendar.getInstance();
        cal.clear();
        cal.setTime(date);
        int currentMonth = cal.get(Calendar.MONTH) + 1;
        if (currentMonth >= 1 && currentMonth <= 6){
            cal.set(Calendar.MONTH, 5);
        }else if (currentMonth >= 7 && currentMonth <= 12){
            cal.set(Calendar.MONTH, 11);
        }
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
        return cal;
    }

    //获取本年的最后一天
    public static Calendar getYearEnd(Date date){
        Calendar cal = Calendar.getInstance();
        cal.clear();
        cal.setTime(date);
        cal.set(Calendar.MONTH,cal.getActualMaximum(Calendar.MONTH));
        cal.set(Calendar.DAY_OF_MONTH,cal.getActualMaximum(Calendar.DAY_OF_MONTH));
        return cal;
    }

    /**
     * 根据日期偏移量获取日期
     * @param srcDate
     * @param offset
     * @return
     */
    public static Date getDay(Date srcDate,int offset){
        Calendar c = Calendar.getInstance();
        c.clear();
        c.setTime(srcDate);
        c.add(Calendar.DAY_OF_MONTH, offset);
        return c.getTime();
    }

    /**
     * 根据偏移量获取周信息
     * @param srcDate
     * @param offset
     * @return
     */
    public static Date getWeek(Date srcDate,int offset){
        Calendar c = Calendar.getInstance();
        c.clear();
        c.setTime(srcDate);
        c.add(Calendar.WEEK_OF_MONTH, offset);
        return c.getTime();
    }

    /**
     * 根据偏移量获取月份
     * @param srcDate
     * @param offset
     * @return
     */
    public static Date getMonth(Date srcDate,int offset){
        Calendar c = Calendar.getInstance();
        c.clear();
        c.setTime(srcDate);
        c.add(Calendar.MONTH, offset);
        return c.getTime();
    }

    /**
     * 根据偏移量获取季度日期
     * @param srcDate
     * @param offset
     * @return
     */
    public static Date getQuarter(Date srcDate,int offset){
        Calendar c = Calendar.getInstance();
        c.clear();
        c.setTime(srcDate);
        c.add(Calendar.MONTH, offset * 3);
        return c.getTime();
    }

    /**
     * 根据偏移量获取半年日期
     * @param srcDate
     * @param offset
     * @return
     */
    public static Date getHalfYear(Date srcDate,int offset){
        Calendar c = Calendar.getInstance();
        c.clear();
        c.setTime(srcDate);
        c.add(Calendar.MONTH, offset * 6);
        return c.getTime();
    }

    /**
     * 根据偏移量获取年的日期
     * @param srcDate
     * @param offset
     * @return
     */
    public static Date getYear(Date srcDate,int offset){
        Calendar c = Calendar.getInstance();
        c.clear();
        c.setTime(srcDate);
        c.add(Calendar.YEAR, offset);
        return c.getTime();
    }


    /**
     * 根据日期的偏移量和日期类型获取，偏移后的日期
     * @param ttpe    日期类型
     * @param srcDate  源日期
     * @param offset   偏移量，大于0：向未来计算  小于0：向过去计算
     * @return
     */
    public static Date getDateOffset(TimeTransformPeriodEnum ttpe,Date srcDate,Integer offset){
        Date resuLtDate = null;
        if (offset == null || offset == 0){
            return srcDate;
        }
        switch(ttpe){
            case DAY:{
                resuLtDate = DateUtil.getDay(srcDate,offset);
                break;
            }
            case WEEK:{
                resuLtDate = DateUtil.getWeek(srcDate,offset);
                break;
            }
            case MONTH:{
                resuLtDate = DateUtil.getMonth(srcDate,offset);
                break;
            }
            case QUARTER:{
                resuLtDate = DateUtil.getQuarter(srcDate,offset);
                break;
            }
            case HALFYEAR:{
                resuLtDate = DateUtil.getHalfYear(srcDate,offset);
                break;
            }
            case YEAR:{
                resuLtDate = DateUtil.getYear(srcDate,offset);
                break;
            }
            default:{
                resuLtDate = srcDate;
                break;
            }
        }
        return resuLtDate;
    }

    /**
     * 获取指定时间段内的所有日期数据
     * @param beginDate
     * @param endDate
     * @return
     */
    public static List<String> findStrDateRange(Date beginDate, Date endDate,String dateFormat){
        List<String> resultDate = new ArrayList<>();
        SimpleDateFormat sd = new SimpleDateFormat(dateFormat);
        resultDate.add(sd.format(beginDate));
        Calendar calBegin = Calendar.getInstance();
        // 使用给定的 Date 设置此 Calendar 的时间
        calBegin.setTime(beginDate);
        Calendar calEnd = Calendar.getInstance();
        // 使用给定的 Date 设置此 Calendar 的时间
        calEnd.setTime(endDate);
        // 验证此日期是否在指定日期之后
        while (endDate.after(calBegin.getTime())){
            // 根据日历的规则，为给定的日历字段添加或减去指定的时间量
            calBegin.add(Calendar.DAY_OF_MONTH, 1);
            resultDate.add(sd.format(calBegin.getTime()));
        }
        return resultDate;
    }

    public static List<Map<String,Object>> findMapDateRange(Date beginDate, Date endDate,String dateFormat){
        List<Map<String,Object>> resultDate = new ArrayList<>();
        SimpleDateFormat sd = new SimpleDateFormat(dateFormat);
        Map<String,Object> hashMap = new HashMap<>();
        hashMap.put("cdate",sd.format(beginDate));
        resultDate.add(hashMap);
        Calendar calBegin = Calendar.getInstance();
        // 使用给定的 Date 设置此 Calendar 的时间
        calBegin.setTime(beginDate);
        Calendar calEnd = Calendar.getInstance();
        // 使用给定的 Date 设置此 Calendar 的时间
        calEnd.setTime(endDate);
        // 验证此日期是否在指定日期之后
        while (endDate.after(calBegin.getTime())){
            // 根据日历的规则，为给定的日历字段添加或减去指定的时间量
            calBegin.add(Calendar.DAY_OF_MONTH, 1);
            Map<String,Object> hashMapTemp = new HashMap<>();
            hashMapTemp.put("cdate",sd.format(calBegin.getTime()));
            resultDate.add(hashMap);
        }
        return resultDate;
    }
}
