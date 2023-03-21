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

    public double calculateCost(int callDurationInSeconds) {
        if (callDurationInSeconds < 0 || callDurationInSeconds > MAX_DURATION_IN_SECONDS) {
            throw new IllegalArgumentException("Invalid input");
        }

        int callDurationInMinutes = roundUpToMinutes(callDurationInSeconds);
        double cost;

        if (callDurationInMinutes <= 1) {
            cost = COST_PER_MINUTE_UNDER_20_MINUTES;
        } else if (callDurationInMinutes <= 20) {
            cost = callDurationInMinutes * COST_PER_MINUTE_UNDER_20_MINUTES;
        } else {
            cost = COST_FOR_FIRST_20_MINUTES + (callDurationInMinutes - 20) * COST_PER_MINUTE_OVER_20_MINUTES;
        }

        return cost;
    }

    public int roundUpToMinutes(int seconds) {
        return (int) Math.ceil((double) seconds / SECONDS_PER_MINUTE);
    }

    public boolean isDaylightSavingTime(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int year = cal.get(Calendar.YEAR);
        Date dstStart = getDstStart(year);
        Date dstEnd = getDstEnd(year);
        return date.after(dstStart) && date.before(dstEnd);
    }

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