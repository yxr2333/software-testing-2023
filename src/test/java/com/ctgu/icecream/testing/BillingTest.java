package com.ctgu.icecream.testing;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created By IntelliJ IDEA
 *
 * @author IceCreamQAQ
 * @datetime 2023/3/21 星期二
 * Happy Every Coding Time~
 */
@DisplayName("Billing类的测试")
public class BillingTest {

    Billing billing = new Billing();

    @Test
    @DisplayName("测试通话时间小于1分钟的计费情况")
    public void testCalculateCostLessThan1Minute() {
        int callDurationInSeconds = 30;
        double expectedCost = 0.05;
        double actualCost = billing.calculateCost(callDurationInSeconds);
        assertEquals(expectedCost, actualCost, 0.001, "Should calculate cost correctly for duration less than 1 minute");
    }

    @Test
    @DisplayName("测试通话时间在20分钟以内的计费情况")
    public void testCalculateCostUnder20Minutes() {
        int callDurationInSeconds = 20 * 60;
        double expectedCost = 1.0;
        double actualCost = billing.calculateCost(callDurationInSeconds);
        assertEquals(expectedCost, actualCost, 0.001, "Should calculate cost correctly for duration under 20 minutes");
    }

    @Test
    @DisplayName("测试通话时间在20分钟以内的计费情况")
    public void testCalculateCostOver20Minutes() {
        int callDurationInSeconds = 25 * 60;
        double expectedCost = 1.5;
        double actualCost = billing.calculateCost(callDurationInSeconds);
        assertEquals(expectedCost, actualCost, 0.001, "Should calculate cost correctly for duration over 20 minutes");
    }

    @Test
    @DisplayName("测试将秒数向上取整为分钟的功能")
    public void testRoundUpToMinutes() {
        int seconds = 90;
        int expectedMinutes = 2;
        int actualMinutes = billing.roundUpToMinutes(seconds);
        assertEquals(expectedMinutes, actualMinutes, "Should round up to minutes correctly");
    }

    @Test
    @DisplayName("测试将秒数向上取整为分钟的功能")
    public void testCalculateCostInvalidInput() {
        int callDurationInSeconds = -10;
        assertThrows(IllegalArgumentException.class, () -> billing.calculateCost(callDurationInSeconds), "Should throw IllegalArgumentException for invalid input");
    }

    @Test
    @DisplayName("测试夏令时判断函数在夏令时内的情况")
    public void testIsDaylightSavingTime() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2022, Calendar.MARCH, 15);
        Date date = calendar.getTime();
        boolean expected = true;
        boolean actual = billing.isDaylightSavingTime(date);
        assertEquals(expected, actual, "Should return true for date within daylight saving time period");
    }

    @Test
    @DisplayName("测试夏令时判断函数在夏令时之外的情况")
    public void testIsNotDaylightSavingTime() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2022, Calendar.JANUARY, 15);
        Date date = calendar.getTime();
        boolean expected = false;
        boolean actual = billing.isDaylightSavingTime(date);
        assertEquals(expected, actual, "Should return false for date outside daylight saving time period");
    }
}