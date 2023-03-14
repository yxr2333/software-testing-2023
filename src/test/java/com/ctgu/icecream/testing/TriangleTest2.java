package com.ctgu.icecream.testing;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Created By IntelliJ IDEA
 *
 * @author IceCreamQAQ
 * @datetime 2023/3/14 星期二
 * Happy Every Coding Time~
 */
public class TriangleTest2 {

    @Test
    @DisplayName("一般边界值分析法")
    void testClassifyNormal() {
        Triangle triangle = new Triangle();
        assertEquals("等边三角形", triangle.classify(1, 1, 1));
        assertEquals("等腰三角形", triangle.classify(2, 2, 3));
        assertEquals("不等边三角形", triangle.classify(3, 4, 5));
        assertEquals("等边三角形", triangle.classify(2, 2, 2));
        assertEquals("非三角形", triangle.classify(1, 2, 3));
        assertEquals("等边三角形", triangle.classify(100, 100, 100));
    }

    @Test
    @DisplayName("健壮性边界值分析法")
    void testClassifyStrong() {
        Triangle triangle = new Triangle();
        assertEquals("输入错误", triangle.classify(0, 1, 1));
        assertEquals("输入错误", triangle.classify(1, 0, 1));
        assertEquals("输入错误", triangle.classify(1, 1, 0));
        assertEquals("非三角形", triangle.classify(100, 1, 1));
        assertEquals("非三角形", triangle.classify(1, 100, 1));
        assertEquals("非三角形", triangle.classify(1, 1, 100));
        assertEquals("输入错误", triangle.classify(101, 1, 1));
        assertEquals("输入错误", triangle.classify(1, 101, 1));
        assertEquals("输入错误", triangle.classify(1, 1, 101));
        assertEquals("输入错误", triangle.classify(-1, 1, 1));
        assertEquals("输入错误", triangle.classify(1, -1, 1));
        assertEquals("输入错误", triangle.classify(1, 1, -1));
    }

    @ParameterizedTest
    @DisplayName("最坏情况一般边界值分析方法")
    @CsvFileSource(resources = "/testdata3.csv", numLinesToSkip = 1)
    void testClassifyWorstNormal(int a, int b, int c, String expected) {
        Triangle triangle = new Triangle();
        assertEquals(expected, triangle.classify(a, b, c));
    }

    @ParameterizedTest
    @DisplayName("最坏情况健壮性边界值分析方法")
    @CsvFileSource(resources = "/testdata4.csv", numLinesToSkip = 1)
    void testClassifyWorstRobust(int a, int b, int c, String expected) {
        Triangle triangle = new Triangle();
        assertEquals(expected, triangle.classify(a, b, c));
    }
}
