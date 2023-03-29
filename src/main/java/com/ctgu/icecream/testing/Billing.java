package com.ctgu.icecream.testing;

import java.util.Calendar;
import java.util.Date;

/**
 * Created By IntelliJ IDEA
 *
 * @author IceCreamQAQ
 * @datetime 2023/3/21 星期二
 * Happy Every Coding Time~
 */
public class Billing {
    private static final double COST_PER_MINUTE_UNDER_20_MINUTES = 0.05;
    private static final double COST_PER_MINUTE_OVER_20_MINUTES = 0.10;
    private static final double COST_FOR_FIRST_20_MINUTES = 1.00;
    private static final int SECONDS_PER_MINUTE = 60;
    private static final int MINUTES_PER_HOUR = 60;
    private static final int HOURS_PER_DAY = 24;
    private static final int MAX_DURATION_IN_SECONDS = 30 * HOURS_PER_DAY * MINUTES_PER_HOUR * SECONDS_PER_MINUTE;

    private static final int TWENTY_MINUTES = 20;

    private static final int ONE_MINUTE = 1;

    public final double calculateCost(int callDurationInSeconds) {
        // 检查输入的通话时长是否合法
        if (callDurationInSeconds < 0 || callDurationInSeconds > MAX_DURATION_IN_SECONDS) {
            throw new IllegalArgumentException("Invalid input");
        }

        // 将通话时长向上取整到分钟
        int callDurationInMinutes = roundUpToMinutes(callDurationInSeconds);
        double cost;

        // 根据不同的通话时长范围计算通话费用
        if (callDurationInMinutes <= ONE_MINUTE) {
            // 通话时长不足1分钟，费用为固定的每分钟费用
            cost = COST_PER_MINUTE_UNDER_20_MINUTES;
        } else if (callDurationInMinutes <= TWENTY_MINUTES) {
            // 通话时长大于1分钟且不足20分钟，费用为通话时长（以分钟为单位）乘以每分钟费用
            cost = callDurationInMinutes * COST_PER_MINUTE_UNDER_20_MINUTES;
        } else {
            // 通话时长超过20分钟，费用由前20分钟的固定费用和超过20分钟的部分每分钟费用组成
            cost = COST_FOR_FIRST_20_MINUTES + (callDurationInMinutes - TWENTY_MINUTES) * COST_PER_MINUTE_OVER_20_MINUTES;
        }

        // 返回计算得到的通话费用
        return cost;
    }

    /**
     * 将秒数向上取整为分钟
     * @param seconds 秒数
     * @return 分钟数
     */
    public int roundUpToMinutes(int seconds) {
        return (int) Math.ceil((double) seconds / SECONDS_PER_MINUTE);
    }

    /**
     * 判断指定日期是否为夏令时
     * @param date 日期
     * @return 是否为夏令时
     */
    public boolean isDaylightSavingTime(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int year = cal.get(Calendar.YEAR);
        Date dstStart = getDstStart(year);
        Date dstEnd = getDstEnd(year);
        return date.after(dstStart) && date.before(dstEnd);
    }

    /**
     * 获取指定年份的夏令时开始时间
     * @param year 年份
     * @return 夏令时开始时间
     */
    private Date getDstStart(int year) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, Calendar.MARCH);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        cal.set(Calendar.HOUR_OF_DAY, 2);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        while (cal.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY) {
            cal.add(Calendar.DAY_OF_MONTH, 1);
        }
        return cal.getTime();
    }

    /**
     * 给出指定年份的夏令时结束时间
     * @param year 年份
     * @return 夏令时结束时间
     */
    private Date getDstEnd(int year) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, Calendar.NOVEMBER);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        cal.set(Calendar.HOUR_OF_DAY, 1);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        cal.set(Calendar.MILLISECOND, 999);
        while (cal.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY) {
            cal.add(Calendar.DAY_OF_MONTH, -1);
        }
        return cal.getTime();
    }
}