package com.github.qrenfeng.common.utils;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * <p>格式化数据工具类</p>
 * <p>Created by qrf on 2016/8/12.</p>
 * @author qrf
 */
public class FormatUtils {

    public static final String FORMAT_DATE = "yyyyMMdd";

    public static final String FORMAT_TIME = "HHmmss";

    public static final String FORMAT_DATE_TIME = "yyyyMMddHHmmss";

    public static final String FORMAT_DATE_SECOND = "yyyyMMddHHmmssSSS";

    private static final int DATE_TIME_LENGTH = 14;

    private static DecimalFormat decimalFormat = new DecimalFormat("0.00");

    /**
     * 元转分
     *
     * @param amount
     * @return
     */
    public static int amountToCent(String amount) {
        return amountToCent(new BigDecimal(amount));
    }

    /**
     * 元转分
     *
     * @param amount
     * @return
     */
    public static int amountToCent(BigDecimal amount) {
        return new BigDecimal(100).multiply(amount).intValue();
    }

    /**
     * 分转元
     *
     * @param cent
     * @return
     */
    public static String centToAmount(Integer cent) {
        return decimalFormat.format(new BigDecimal(cent).divide(new BigDecimal(100)));
    }

    /**
     * 格式化金额
     *
     * @param money 金额字符
     * @return 两位小数
     */
    public static String formatMoney(String money) {
        return decimalFormat.format(new BigDecimal(money));
    }

    /**
     * 格式化时间
     *
     * @param time      时间字符
     * @param srcFormat 原格式：yyyyMMddHHmmss
     * @param aimFormat 目标格式：yyyy-MM-dd HH:mm:ss
     * @return 格式化完的时间
     */
    public static String formatTime(String time, String srcFormat, String aimFormat) {
        try {
            SimpleDateFormat sdfa = new SimpleDateFormat(aimFormat);
            SimpleDateFormat sdfs = new SimpleDateFormat(srcFormat);
            time = sdfa.format(sdfs.parse(time));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return time;
    }


    /**
     * 获取当前时间
     *
     * @return yyyyMMddHHmmss
     */
    public static String getNowDateTime() {
        Date date = new Date();
        DateFormat format = new SimpleDateFormat(FORMAT_DATE_TIME);
        return format.format(date);
    }

    /**
     * 获取当前日期
     *
     * @return yyyyMMdd
     */
    public static String getNowDate() {
        Date date = new Date();
        DateFormat format = new SimpleDateFormat(FORMAT_DATE);
        return format.format(date);
    }

    /**
     * 获取当前时间
     *
     * @return HHmmss
     */
    public static String getNowTime() {
        Date date = new Date();
        DateFormat format = new SimpleDateFormat(FORMAT_TIME);
        return format.format(date);
    }

    /**
     * 格式化日期时间
     * @param dateTime yyyyMMddHHmmss
     * @return Date
     */
    public static Date formatDateTime(String dateTime) {
        if (dateTime == null || dateTime.length() != DATE_TIME_LENGTH) {
            return null;
        }
        return FormatUtils.formatDateTime(dateTime, FORMAT_DATE_TIME);
    }

    /**
     * 格式化日期时间
     * @param dateTime
     * @param format yyyyMMddHHmmssSSS
     * @return
     */
    public static Date formatDateTime(String dateTime, String format) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            return sdf.parse(dateTime);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 格式化日期时间
     * @param date
     * @param aimFormat 例：yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static String formatTime(Date date, String aimFormat){
        DateFormat format = new SimpleDateFormat(aimFormat);
        return format.format(date);
    }


    /**
     * 获取间隔时间秒
     * @param starTime yyyyMMddHHmmss
     * @param endTime yyyyMMddHHmmss
     * @return
     */
    public static String getTimes(String starTime, String endTime) {
        SimpleDateFormat fdt = new SimpleDateFormat(FORMAT_DATE_TIME);
        try {
            Date starDate = fdt.parse(starTime);
            Date endDate = fdt.parse(endTime);
            //单位是秒
            long timeDelta = (endDate.getTime() - starDate.getTime()) / 1000;
            if (timeDelta < 0) {
                return "0";
            }
            return timeDelta + "";
        } catch (Exception e) {
            return "0";
        }
    }

    /**
     * 日期间隔天数
     * @param bgnDate yyyyMMdd
     * @param endDate yyyyMMdd
     * @return
     */
    public static int getDays(String bgnDate, String endDate) {
        SimpleDateFormat sdf = new SimpleDateFormat(FORMAT_DATE);
        long day = 24L * 60L * 60L * 1000L;
        try {
            Date d1 = sdf.parse(bgnDate);
            Date d2 = sdf.parse(endDate);

            return (int) ((d2.getTime() - d1.getTime()) / day);
        } catch (ParseException e1) {
            e1.printStackTrace();
            return -1;
        }

    }

    /**
     * 获取相隔的日期值
     * @param dateStr yyyyMMdd
     * @param step -1 前一天， 1 后一天
     * @return
     */
    public static String getStepDate(String dateStr, int step) {
        SimpleDateFormat sdf = new SimpleDateFormat(FORMAT_DATE);
        Calendar cal = Calendar.getInstance();
        Date dateTmp = new Date();
        try {
            dateTmp = sdf.parse(dateStr);
        } catch (ParseException e1) {
            return dateStr;
        }
        cal.setTime(dateTmp);
        cal.add(Calendar.DAY_OF_MONTH, step);
        return sdf.format(cal.getTime());
    }

}
