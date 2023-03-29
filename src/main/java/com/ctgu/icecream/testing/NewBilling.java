package com.ctgu.icecream.testing;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZoneOffset;

/**
 * Created By IntelliJ IDEA
 *
 * @author IceCreamQAQ
 * @datetime 2023/3/28 星期二
 * Happy Every Coding Time~
 */
public class NewBilling {

    private static final BigDecimal CHARGE_PER_MINUTE_UNDER_TWENTY_MINUTES = new BigDecimal("0.05");

    private static final BigDecimal CHARGE_PER_MINUTE_OVER_TWENTY_MINUTES = new BigDecimal("0.10");
    private static final BigDecimal CHARGE_BASE_CALL_OVER_TWENTY_MINUTES = new BigDecimal("1.00");

    /**
     * 计算通话时长，输入参数为通话开始时间和结束时间，返回通话时长，类型为long。
     *
     * @param startTime 通话开始时间
     * @param endTime   通话结束时间
     * @return 通话时长，类型为long，单位为秒
     */
    public long calculateTimeSpan(LocalDateTime startTime, LocalDateTime endTime) {
        return endTime.toEpochSecond(ZoneOffset.UTC) - startTime.toEpochSecond(ZoneOffset.UTC);
    }

    /**
     * 计算通话费用，输入参数为通话时长，返回通话费用，类型为BigDecimal。
     * 在此方法中，需要根据实验要求采用指定的计费规则进行计算通话费用。
     *
     * @param timeLength 通话时长，类型为long，单位为秒
     * @return 通话费用，类型为BigDecimal
     */
    public BigDecimal calculateFee(long timeLength) {
        if (timeLength <= 0) {
            return BigDecimal.ZERO;
        } else if (timeLength < 60) {
            return CHARGE_PER_MINUTE_UNDER_TWENTY_MINUTES;
        } else if (timeLength <= 20 * 60) {
            int minutes = (int) Math.ceil(timeLength / 60.0);
            return new BigDecimal(minutes).multiply(CHARGE_PER_MINUTE_UNDER_TWENTY_MINUTES);
        } else {
            BigDecimal fee = CHARGE_BASE_CALL_OVER_TWENTY_MINUTES;
            int minutes = (int) Math.ceil((timeLength - 20 * 60) / 60.0);
            fee = fee.add(CHARGE_PER_MINUTE_OVER_TWENTY_MINUTES.multiply(new BigDecimal(minutes)));
            return fee;
        }
    }

    /**
     * 判断指定的时间是否为夏令时转换时间
     *
     * @param dateTime 要判断的时间
     * @return 如果是夏令时转换时间，返回true；否则返回false
     */
    public boolean isDstTransitionTime(LocalDateTime dateTime) {
        int dayOfWeek = dateTime.getDayOfWeek().getValue();
        int dayOfMonth = dateTime.getDayOfMonth();
        int hour = dateTime.getHour();

        if (dateTime.getMonth() == Month.MARCH) {
            // 春季转换时间：3月的某个星期日凌晨2:00点，需要将时钟调整到凌晨3:00点
            return (dayOfWeek == 7 && dayOfMonth >= 8 && dayOfMonth <= 14 && hour == 2);
        } else if (dateTime.getMonth() == Month.NOVEMBER) {
            // 秋季转换时间：11月的第一个星期日凌晨2:00点，需要将时钟调整到凌晨1:00点
            return dayOfWeek == 7 && dayOfMonth <= 7 && hour == 2;
        } else {
            return false;
        }
    }

    public int getDstOffset(LocalDateTime dateTime) {
        int dayOfWeek = dateTime.getDayOfWeek().getValue();
        int dayOfMonth = dateTime.getDayOfMonth();
        int hour = dateTime.getHour();

        if (dateTime.getMonth() == Month.MARCH) {
            // 春季转换时间：3月的某个星期日凌晨2:00点，需要将时钟调整到凌晨3:00点，时钟向前调整1小时，即加上3600秒
            if (dayOfWeek == 7 && dayOfMonth >= 8 && dayOfMonth <= 14 && hour == 2) {
                return 3600;
            } else {
                return 0;
            }
        } else if (dateTime.getMonth() == Month.NOVEMBER) {
            // 秋季转换时间：11月的第一个星期日凌晨2:00点，需要将时钟调整到凌晨1:00点，时钟向后调整1小时，即减去3600秒
            if (dayOfWeek == 7 && dayOfMonth <= 7 && hour == 2) {
                return -3600;
            } else {
                return 0;
            }
        } else {
            return 0;
        }
    }
}
