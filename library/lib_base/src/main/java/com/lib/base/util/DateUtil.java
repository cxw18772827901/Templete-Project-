package com.lib.base.util;

import android.annotation.SuppressLint;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 时间 / 时间戳的工具类
 * Created by henery on 2015/09/11.
 */
@SuppressLint("SimpleDateFormat")
public class DateUtil {
    public static String[] weekName = {"日", "一", "二", "三", "四", "五", "六"};
    private static SimpleDateFormat sf = null;

    public static int getMonthDays(int year, int month) {
        if (month > 12) {
            month = 1;
            year += 1;
        } else if (month < 1) {
            month = 12;
            year -= 1;
        }
        int[] arr = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        int days = 0;
        if ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0) {
            arr[1] = 29; // ????2??29??
        }
        try {
            days = arr[month - 1];
        } catch (Exception e) {
            e.getStackTrace();
        }
        return days;
    }

    public static int getYear() {
        return Calendar.getInstance().get(Calendar.YEAR);
    }

    public static int getMonth() {
        return Calendar.getInstance().get(Calendar.MONTH) + 1;
    }

    public static int getCurrentMonthDay() {
        return Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
    }

    public static int getWeekDay() {
        return Calendar.getInstance().get(Calendar.DAY_OF_WEEK);
    }

    public static int getHour() {
        return Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
    }

    public static int getMinute() {
        return Calendar.getInstance().get(Calendar.MINUTE);
    }

    public static int[] getWeekSunday(int year, int month, int day, int pervious) {
        int[] time = new int[3];
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.DAY_OF_MONTH, day);
        c.add(Calendar.DAY_OF_MONTH, pervious);
        time[0] = c.get(Calendar.YEAR);
        time[1] = c.get(Calendar.MONTH) + 1;
        time[2] = c.get(Calendar.DAY_OF_MONTH);
        return time;

    }

    public static int getWeekDayFromDate(int year, int month) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(getDateFromString(year, month));
        int week_index = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (week_index < 0) {
            week_index = 0;
        }
        return week_index;
    }

    @SuppressLint("SimpleDateFormat")
    public static Date getDateFromString(int year, int month) {
        String dateString = year + "-" + (month > 9 ? month : ("0" + month))
                + "-01";
        Date date = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            date = sdf.parse(dateString);
        } catch (ParseException e) {
            //System.out.println(e.getMessage());
        }
        return date;
    }

    public static String formatDisplayTime(String time) {
        String displayTime = time;
        int min = 60000;
        int hour = 60 * min;
        int day = 24 * hour;
        if (time != null) {
            try {
                Date today = new Date();
                Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(time);
                SimpleDateFormat thisYearDf = new SimpleDateFormat("yyyy");
                SimpleDateFormat todayDf = new SimpleDateFormat("yyyy-MM-dd");
                Date thisYear = new Date(thisYearDf.parse(thisYearDf.format(today)).getTime());
                Date yesterday = new Date(todayDf.parse(todayDf.format(today)).getTime());
                Date beforeYes = new Date(yesterday.getTime() - day);
                if (date != null) {
                    SimpleDateFormat halfDf = new SimpleDateFormat("MM月dd日");
                    long dTime = today.getTime() - date.getTime();
                    if (date.before(thisYear)) {
                        displayTime = new SimpleDateFormat("yyyy年MM月dd日").format(date);
                    } else {
                        if (dTime < min) {
                            displayTime = "刚刚";
                        } else if (dTime < hour) {
                            displayTime = (int) Math.ceil(dTime / min) + "分钟前";
                        } else if (dTime < day && date.after(yesterday)) {
                            displayTime = (int) Math.ceil(dTime / hour) + "小时前";
                        } else if (date.after(beforeYes) && date.before(yesterday)) {
                            displayTime = "昨天" + new SimpleDateFormat("HH:mm").format(date);
                        } else {
                            displayTime = halfDf.format(date);
                        }
                    }
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else {
            displayTime = "";
        }

        return displayTime;
    }

    public static String formatNotifyDate(long time) {
        String timeFormat = "";
//        int min = 60000;
//        int hour = 60 * min;
//        int day = 24 * hour;
        Date today = new Date();
        Date date = new Date(time);
        SimpleDateFormat todayDf = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat todayDf1 = new SimpleDateFormat("HH:mm");
        Date yesterday;
        try {
            yesterday = new Date(todayDf.parse(todayDf.format(today)).getTime());
//            Date beforeYes = new Date(yesterday.getTime() - day);
            if (/*date.after(beforeYes) &&*/ !date.before(yesterday)) {//今天的
                timeFormat = todayDf1.format(date);
            } else {//今天以前的
                timeFormat = todayDf.format(date);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return timeFormat;
    }

    public static String formatDisplayTime(long time) {
        if (String.valueOf(time).length() == 13) {
            time = time / 1000;
        }
        String displayTime = "";

        int min = 60000;
        int hour = 60 * min;
        int day = 24 * hour;

        if (time != 0L) {
            try {
                Date today = new Date();

                Date date = new Date(time * 1000);

                SimpleDateFormat thisYearDf = new SimpleDateFormat("yyyy");
                SimpleDateFormat todayDf = new SimpleDateFormat("yyyy-MM-dd");
                //今年一月一号
                Date thisYear = new Date(thisYearDf.parse(thisYearDf.format(today)).getTime());
                //今天的年月日
                Date yesterday = new Date(todayDf.parse(todayDf.format(today)).getTime());
                //昨天的年月日
                Date beforeYes = new Date(yesterday.getTime() - day);
                if (date != null) {
                    SimpleDateFormat halfDf = new SimpleDateFormat("MM月dd日");
                    long dTime = today.getTime() - date.getTime();
                    if (dTime < 0 || date.before(thisYear)) {
                        displayTime = new SimpleDateFormat("yyyy年MM月dd日").format(date);
                    } else {
                       /* if (dTime < min) {
                            displayTime = "刚刚";
                        } else*/
                        if (dTime < hour) {
                            displayTime = (int) Math.ceil(dTime / min) + "分钟前";
                        } else if (dTime < day && date.after(yesterday)) {
                            displayTime = (int) Math.ceil(dTime / hour) + "小时前";
                        } else if (date.after(beforeYes) && date.before(yesterday)) {
                            displayTime = "昨天" + new SimpleDateFormat("HH:mm").format(date);
                        } else {
                            displayTime = halfDf.format(date);
                        }
                    }
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else {
            displayTime = "";
        }

        return displayTime;
    }

    public static String formatPublishTime(long time) {
        int min = 60 * 1000;
        int hour = 60 * min;
        int day = 24 * hour;
        int month = 30 * day;
        String formatTime = "";
        //比对
        Date publishDayDate = new Date(time);
        Date toDayDate = new Date();
        long delayTime = toDayDate.getTime() - publishDayDate.getTime();
        if (delayTime < hour) {
            formatTime = (int) Math.ceil(delayTime / min) + "分钟前";
        } else if (delayTime < day) {
            formatTime = (int) Math.ceil(delayTime / hour) + "小时前";
        } else if (delayTime < month) {
            formatTime = (int) Math.ceil(delayTime / day) + "天前";
        } else {//超出一个月.yy-mm-dd
            formatTime = new SimpleDateFormat("yyyy-MM-dd").format(publishDayDate);
        }
        return formatTime;
    }

    public static String formatDisplayTimeIm(long time) {
        if (String.valueOf(time).length() == 13) {
            time = time / 1000;
        }
        String displayTime = "";

        int min = 60000;
        int hour = 60 * min;
        int day = 24 * hour;

        if (time != 0L) {
            try {
                Date today = new Date();

                Date date = new Date(time * 1000);

                SimpleDateFormat thisYearDf = new SimpleDateFormat("yyyy");
                SimpleDateFormat todayDf = new SimpleDateFormat("yyyy-MM-dd");

                Date thisYear = new Date(thisYearDf.parse(thisYearDf.format(today)).getTime());
                Date yesterday = new Date(todayDf.parse(todayDf.format(today)).getTime());
                Date beforeYes = new Date(yesterday.getTime() - day);
                if (date != null) {
                    SimpleDateFormat halfDf = new SimpleDateFormat("MM月dd日");
                    long dTime = today.getTime() - date.getTime();
                    if (date.before(thisYear)) {
                        displayTime = new SimpleDateFormat("yyyy年MM月dd日").format(date);
                    } else {

                        if (dTime <= min * 2) {
                            displayTime = "刚刚";
                        } else if (dTime > min * 2 && dTime < hour) {
//                            displayTime = (int) Math.ceil(dTime / min) + "分钟前";
                            displayTime = new SimpleDateFormat("HH:mm").format(date);
                        } else if (dTime < day && date.after(yesterday)) {
//                            displayTime = (int) Math.ceil(dTime / hour) + "小时前";
                            displayTime = new SimpleDateFormat("HH:mm").format(date);
                        } else if (date.after(beforeYes) && date.before(yesterday)) {
                            displayTime = "昨天" + new SimpleDateFormat("HH:mm").format(date);
                        } else {
                            displayTime = halfDf.format(date) + new SimpleDateFormat("HH:mm").format(date);
                        }
                    }
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else {
            displayTime = "";
        }

        return displayTime;
    }

    public static String formatDisplayTimeImMsg(long time) {
        if (String.valueOf(time).length() == 13) {
            time = time / 1000;
        }
        String displayTime = "";

        int min = 60000;
        int hour = 60 * min;
        int day = 24 * hour;

        if (time != 0L) {
            try {
                Date today = new Date();

                Date date = new Date(time * 1000);

                SimpleDateFormat thisYearDf = new SimpleDateFormat("yyyy");
                SimpleDateFormat todayDf = new SimpleDateFormat("yyyy-MM-dd");
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH:mm");
                SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("HH:mm");

                Date thisYear = new Date(thisYearDf.parse(thisYearDf.format(today)).getTime());
                Date yesterday = new Date(todayDf.parse(todayDf.format(today)).getTime());
                Date beforeYes = new Date(yesterday.getTime() - day);
                if (date != null) {
                    SimpleDateFormat halfDf = new SimpleDateFormat("MM月dd日");
                    long dTime = today.getTime() - date.getTime();
                    if (date.before(thisYear)) {
                        displayTime = simpleDateFormat.format(date);
                    } else {

                        if (dTime <= min * 2) {
                            displayTime = "刚刚";
                        } else if (dTime > min * 2 && dTime < hour) {
//                            displayTime = (int) Math.ceil(dTime / min) + "分钟前";
                            displayTime = simpleDateFormat1.format(date);
                        } else if (dTime < day && date.after(yesterday)) {
//                            displayTime = (int) Math.ceil(dTime / hour) + "小时前";
                            displayTime = simpleDateFormat1.format(date);
                        } else if (date.after(beforeYes) && date.before(yesterday)) {
                            displayTime = "昨天" + simpleDateFormat1.format(date);
                        } else {
                            displayTime = simpleDateFormat.format(date);
                        }
                    }
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else {
            displayTime = "";
        }

        return displayTime;
    }

    public static String formatDisplayTimeImMsgGift(long time) {
        if (String.valueOf(time).length() == 13) {
            time = time / 1000;
        }
        String displayTime = "";

        int min = 60000;
        int hour = 60 * min;
        int day = 24 * hour;

        if (time != 0L) {
            try {
                Date today = new Date();

                Date date = new Date(time * 1000);

                SimpleDateFormat thisYearDf = new SimpleDateFormat("yyyy");
                SimpleDateFormat todayDf = new SimpleDateFormat("yyyy-MM-dd");

                Date thisYear = new Date(thisYearDf.parse(thisYearDf.format(today)).getTime());
                Date yesterday = new Date(todayDf.parse(todayDf.format(today)).getTime());
                Date beforeYes = new Date(yesterday.getTime() - day);
                if (date != null) {
                    SimpleDateFormat halfDf = new SimpleDateFormat("MM月dd日");
                    long dTime = today.getTime() - date.getTime();
                    if (date.before(thisYear)) {
                        displayTime = new SimpleDateFormat("yyyy-MM-dd" /*HH:mm*/).format(date);
                    } else {

                        if (dTime <= min * 2) {
                            displayTime = "刚刚";
                        } else if (dTime > min * 2 && dTime < hour) {
//                            displayTime = (int) Math.ceil(dTime / min) + "分钟前";
                            displayTime = new SimpleDateFormat("HH:mm").format(date);
                        } else if (dTime < day && date.after(yesterday)) {
//                            displayTime = (int) Math.ceil(dTime / hour) + "小时前";
                            displayTime = new SimpleDateFormat("HH:mm").format(date);
                        } else if (date.after(beforeYes) && date.before(yesterday)) {
                            displayTime = "昨天" + new SimpleDateFormat("HH:mm").format(date);
                        } else {
                            displayTime = new SimpleDateFormat("yyyy-MM-dd"/* HH:mm"*/).format(date);
                        }
                    }
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else {
            displayTime = "";
        }

        return displayTime;
    }

    //路况列表
    public static String formatDisplayTimeUsedByTrafficArticleActivity(long time) {
        if (String.valueOf(time).length() == 13) {
            time = time / 1000;
        }
        String displayTime = "";

        int min = 60000;
        int hour = 60 * min;
        int day = 24 * hour;

        if (time != 0L) {
            try {
                Date today = new Date();

                Date date = new Date(time * 1000);

                SimpleDateFormat thisYearDf = new SimpleDateFormat("yyyy");
                SimpleDateFormat todayDf = new SimpleDateFormat("yyyy-MM-dd");

                Date thisYear = new Date(thisYearDf.parse(thisYearDf.format(today)).getTime());
                Date yesterday = new Date(todayDf.parse(todayDf.format(today)).getTime());
                Date beforeYes = new Date(yesterday.getTime() - day);
                if (date != null) {
                    SimpleDateFormat halfDf = new SimpleDateFormat("MM月dd日");
                    long dTime = today.getTime() - date.getTime();
                    if (date.before(thisYear)) {
                        displayTime = new SimpleDateFormat("yyyy年MM月dd日").format(date);
                    } else {

                       /* if (dTime < min) {
                            displayTime = "刚刚";
                        } else*/
                        if (dTime < hour) {
                            displayTime = (int) Math.ceil(dTime / min) + "分钟前";
                        } else if (dTime < day && date.after(yesterday)) {
                            displayTime = (int) Math.ceil(dTime / hour) + "小时前";
                        } else if (date.after(beforeYes) && date.before(yesterday)) {
                            displayTime = "昨天" + new SimpleDateFormat("HH:mm").format(date);
                        } else {
                            displayTime = halfDf.format(date);
                        }
                    }
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else {
            displayTime = "";
        }

        return displayTime;
    }

    //    路况底层页面
    public static String formatDisplayTimeUsedByTrafficReportArticleActivity(long time) {
        if (String.valueOf(time).length() == 13) {
            time = time / 1000;
        }
        String displayTime = "";

        int min = 60000;
        int hour = 60 * min;
        int day = 24 * hour;

        if (time != 0L) {
            try {
                Date today = new Date();

                Date date = new Date(time * 1000);

                SimpleDateFormat thisYearDf = new SimpleDateFormat("yyyy");
                SimpleDateFormat todayDf = new SimpleDateFormat("yyyy-MM-dd");

                Date thisYear = new Date(thisYearDf.parse(thisYearDf.format(today)).getTime());
                Date yesterday = new Date(todayDf.parse(todayDf.format(today)).getTime());
                Date beforeYes = new Date(yesterday.getTime() - day);
                if (date != null) {
                    SimpleDateFormat halfDf = new SimpleDateFormat("MM月dd日");
                    long dTime = today.getTime() - date.getTime();
                    if (date.before(thisYear)) {
                        displayTime = new SimpleDateFormat("yyyy年MM月dd日").format(date);
                    } else {

                       /* if (dTime < min) {
                            displayTime = "刚刚";
                        } else*/
                        if (dTime < hour) {
                            displayTime = (int) Math.ceil(dTime / min) + "分钟前";
                        } else if (dTime < day && date.after(yesterday)) {
                            displayTime = (int) Math.ceil(dTime / hour) + "小时前";
                        } else if (date.after(beforeYes) && date.before(yesterday)) {
                            displayTime = "昨天" + new SimpleDateFormat("HH:mm").format(date);
                        } else {
                            displayTime = halfDf.format(date);
                        }
                    }
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else {
            displayTime = "";
        }

        return displayTime;
    }

    public static String formatDisplayDate(long time) {
        if (String.valueOf(time).length() == 13) {
            time = time / 1000;
        }
        String displayTime = "";


        if (time != 0L) {
            try {

                Date date = new Date(time * 1000);

                if (date != null) {
                    displayTime = new SimpleDateFormat("yyyy年MM月dd日").format(date);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else {
            displayTime = "";
        }

        return displayTime;
    }

    /**
     * 计算两个日期之间的天数
     *
     * @param smdate 起始时间
     * @param bdate  终止时间
     * @return 相差天数
     * @throws ParseException
     */
    public static int daysBetween(Date smdate, Date bdate) {

        long time1 = smdate.getTime();
        long time2 = bdate.getTime();
        long between_days = (time2 - time1) / (1000 * 3600 * 24);
        return Integer.parseInt(String.valueOf(between_days));
    }


    /**
     * 时间戳/秒 转换为年月日
     *
     * @param second 秒
     * @return 几年几月几日
     */
    public static String DateFormat(String second) {

        Date date = new Date(Long.parseLong(second) * 1000);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
        String newDate = sdf.format(date);
//        if (newDate.startsWith("0")) {
//            newDate = newDate.substring(1);
//        }
        return newDate;

    }

    public static String DateFormatNoYear(String second) {

        Date date = new Date(Long.parseLong(second) * 1000);
        SimpleDateFormat sdf = new SimpleDateFormat("MM月dd日");
        String newDate = sdf.format(date);
//        if (newDate.startsWith("0")) {
//            newDate = newDate.substring(1);
//        }
        return newDate;

    }

    /**
     * 将字符串转为时间戳
     * Created by Sky on 20156/03/30.
     */
    public static long getStringToDate(String time) {
        sf = new SimpleDateFormat("yyyy年MM月dd日");
        Date date = new Date();
        try {
            date = sf.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date.getTime();
    }

    /**
     * 将字符串转为时间戳
     * Created by Sky on 20156/03/30.
     */
    public static long getStringToDateHms(String time) {
        sf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
        Date date = new Date();
        try {
            date = sf.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date.getTime();
    }


    /**
     * 将字符串转为时间戳
     * Created by Sky on 20156/03/30.
     */
    public static long getStringToDateh(String time) {
        sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        try {
            date = sf.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date.getTime();
    }

    /**
     * 时间戳转换成字符窜
     * Created by Sky on 20156/03/30.
     */
    public static String getDateToString(long time) {
        Date d = new Date(time);
        sf = new SimpleDateFormat("yyyy年MM月dd日");
        return sf.format(d);
    }

    /**
     * 直接获取时间戳
     * Created by Sky on 20156/03/30.
     */
    public static String getTimeStamp() {
        String currentDate = getCurrentDate();
        sf = new SimpleDateFormat("yyyy年MM月dd日");
        Date date = new Date();
        try {
            date = sf.parse(currentDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return String.valueOf(date.getTime());
    }

    /**
     * 获取系统时间 格式为："yyyy/MM/dd "
     * Created by Sky on 20156/03/30.
     */
    public static String getCurrentDate() {
        Date d = new Date();
        sf = new SimpleDateFormat("yyyy年MM月dd日");
        return sf.format(d);
    }

    public static boolean isSameDay(long time1, long time2) {
        Date date1 = new Date(time1 * 1000);
        Date date2 = new Date(time2 * 1000);

        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");

        String dateStr1 = sf.format(date1);
        String dateStr2 = sf.format(date2);

        return dateStr1.equals(dateStr2);
    }

    /**
     * 获取现在时间
     *
     * @return 返回短时间字符串格式yyyy-MM-dd
     */
    public static String getStringDateShort() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = formatter.format(currentTime);
        return dateString;
    }

    public static String getTimeByLong(long time) {
        Date d = new Date(time);
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sf.format(d);
    }

    public static String getHistoryTimeByLong(long time) {
        Date d = new Date(time);
        SimpleDateFormat sf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        return sf.format(d);
    }

    public static String getNowTime() {
        Date d = new Date();
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sf.format(d);
    }

    public static String getDelayDayYMD() {
        Date d = new Date(System.currentTimeMillis() + 24 * 60 * 60 * 1000);
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        return sf.format(d);
    }

    public static String getDelayThreeDayYMD() {
        Date d = new Date(System.currentTimeMillis() + 3 * 24 * 60 * 60 * 1000);
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        return sf.format(d);
    }

    //一周 604800000
    //一月 2592000000
    public static String getWeekBeforeTime() {
        long longTimeNow = System.currentTimeMillis();
        long weekBefore = longTimeNow - 604800000L;
        Date d = new Date(weekBefore);
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sf.format(d);
    }

    //一周 604800000
    //一月 2592000000
    public static String getMonthBeforeTime() {
        long longTimeNow = System.currentTimeMillis();
        long weekBefore = longTimeNow - 2592000000L;
        Date d = new Date(weekBefore);
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sf.format(d);
    }

}
