package com.ctgu.icecream.testing;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Created By IntelliJ IDEA
 *
 * @author IceCreamQAQ
 * @datetime 2023/3/28 星期二
 * Happy Every Coding Time~
 */
@DisplayName("NewBilling类的测试")
public class NewBillingTest {
    private final NewBilling billing = new NewBilling();

    @Test
    @DisplayName("通话时间小于等于1分钟，未跨时区")
    void testLessThan1MinWithoutDST() {
        // 准备测试数据
        LocalDateTime startTime = LocalDateTime.of(2023, 3, 29, 0, 0);
        LocalDateTime endTime = LocalDateTime.of(2023, 3, 29, 0, 59);
        // 调用被测试方法
        long timeLength = billing.calculateTimeSpan(startTime, endTime);
        BigDecimal actualFee = billing.calculateFee(timeLength);
        // 验证预期输出和动作
        BigDecimal expectedFee = new BigDecimal("4.90");
        Assertions.assertEquals(expectedFee, actualFee, "通话时间小于等于1分钟，未跨时区，通话费用计算不正确");
    }

    @Test
    @DisplayName("通话时间在1分钟和20分钟之间，未跨时区")
    void testBetween1MinAnd20MinWithoutDST() {
        // 准备测试数据
        LocalDateTime startTime = LocalDateTime.of(2023, 3, 29, 0, 0);
        LocalDateTime endTime = LocalDateTime.of(2023, 3, 29, 1, 0);
        // 调用被测试方法
        long timeLength = billing.calculateTimeSpan(startTime, endTime);
        BigDecimal actualFee = billing.calculateFee(timeLength);
        // 验证预期输出和动作
        BigDecimal expectedFee = new BigDecimal("5.00");
        Assertions.assertEquals(expectedFee, actualFee, "通话时间在1分钟和20分钟之间，未跨时区，通话费用计算不正确");
    }

    @Test
    @DisplayName("通话时间大于20分钟，未跨时区")
    void testMoreThan20MinWithoutDST() {
        // 准备测试数据
        LocalDateTime startTime = LocalDateTime.of(2023, 3, 29, 0, 0);
        LocalDateTime endTime = LocalDateTime.of(2023, 3, 29, 0, 25);
        // 调用被测试方法
        long timeLength = billing.calculateTimeSpan(startTime, endTime);
        BigDecimal actualFee = billing.calculateFee(timeLength);
        // 验证预期输出和动作
        BigDecimal expectedFee = new BigDecimal("1.50");
        Assertions.assertEquals(expectedFee, actualFee, "通话时间大于20分钟，未跨时区，通话费用计算不正确");
    }

    @Test
    @DisplayName("通话时间小于等于1分钟，跨时区")
    void testLessThan1MinWithDST() {
        // 准备测试数据
        LocalDateTime startTime = LocalDateTime.of(2023, 3, 10, 1, 59);
        LocalDateTime endTime = LocalDateTime.of(2023, 3, 10, 3, 0);
        // 调用被测试方法
        long timeLength = billing.calculateTimeSpan(startTime, endTime);
        BigDecimal actualFee = billing.calculateFee(timeLength);
        int dstOffset = billing.getDstOffset(startTime) + billing.getDstOffset(endTime);
        // 验证预期输出和动作
        BigDecimal expectedFee = new BigDecimal("5.10");
        Assertions.assertEquals(expectedFee, actualFee, "通话时间小于等于1分钟，跨时区，通话费用计算不正确");
        Assertions.assertTrue(dstOffset > 0, "通话跨越夏令时，但未计算夏令时偏移量");
    }

    @Test
    @DisplayName("通话时间在1分钟和20分钟之间，跨时区")
    void testBetween1MinAnd20MinWithDST() {
        // 准备测试数据
        LocalDateTime startTime = LocalDateTime.of(2023, 3, 12, 1, 59);
        LocalDateTime endTime = LocalDateTime.of(2023, 3, 12, 3, 10);
        // 调用被测试方法
        long timeLength = billing.calculateTimeSpan(startTime, endTime);
        BigDecimal actualFee = billing.calculateFee(timeLength);
        boolean isDST = billing.isDstTransitionTime(startTime) || billing.isDstTransitionTime(endTime.minusSeconds(1));
        // 验证预期输出和动作
        BigDecimal expectedFee = new BigDecimal("6.10");
        Assertions.assertEquals(expectedFee, actualFee, "通话时间在1分钟和20分钟之间，跨时区，通话费用计算不正确");
        Assertions.assertTrue(isDST, "通话时间在1分钟和20分钟之间，跨时区，但未检测到夏令时");
    }

    @Test
    @DisplayName("通话时间大于20分钟，跨时区")
    void testMoreThan20MinWithDST() {
        // 准备测试数据
        LocalDateTime startTime = LocalDateTime.of(2023, 3, 10, 1, 59);
        LocalDateTime endTime = LocalDateTime.of(2023, 3, 10, 3, 30);
        // 调用被测试方法
        long timeLength = billing.calculateTimeSpan(startTime, endTime);
        BigDecimal actualFee = billing.calculateFee(timeLength);
        int dstOffset = billing.getDstOffset(startTime) + billing.getDstOffset(endTime);
        // 验证预期输出和动作
        BigDecimal expectedFee = new BigDecimal("8.10");
        Assertions.assertEquals(expectedFee, actualFee, "通话时间大于20分钟，跨时区，通话费用计算不正确");

        if (dstOffset != 0) {
            boolean isDst = billing.isDstTransitionTime(startTime) || billing.isDstTransitionTime(endTime);
            Assertions.assertTrue(isDst, "通话跨越了夏令时转换时间，但isDstTransitionTime方法返回错误的结果");
        }
    }

}
