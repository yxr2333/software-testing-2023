package com.ctgu.icecream.testing;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TriangleTest {
    @Test
    @DisplayName("输入错误")
    void parameters_error_test() {
        Triangle triangle = new Triangle();
        String type = triangle.classify(0, 4, 5);
        assertEquals("输入错误", type);
    }
    @Test
    @DisplayName("不等边三角形")
    void scalene_test() {
        Triangle triangle = new Triangle();
        String type = triangle.classify(3, 4, 6);
        assertEquals("不等边三角形", type);
    }
    @Test
    @DisplayName("输入错误")
    public void testInvalidInput() {
        assertEquals("输入错误", new Triangle().classify(0, 1, 2));
        assertEquals("输入错误", new Triangle().classify(1, 2, 101));
        assertEquals("输入错误", new Triangle().classify(1, 2, -1));
    }

    @Test
    @DisplayName("非三角形")
    public void testNotTriangle() {
        assertEquals("非三角形", new Triangle().classify(1, 2, 3));
        assertEquals("非三角形", new Triangle().classify(3, 4, 7));
        assertEquals("非三角形", new Triangle().classify(100, 1, 100));
    }

    @Test
    @DisplayName("等边三角形")
    public void testEquilateral() {
        assertEquals("等边三角形", new Triangle().classify(5, 5, 5));
        assertEquals("等边三角形", new Triangle().classify(100, 100, 100));
    }

    @Test
    @DisplayName("等腰三角形")
    public void testIsosceles() {
        assertEquals("等腰三角形", new Triangle().classify(5, 5, 3));
        assertEquals("等腰三角形", new Triangle().classify(1, 50, 50));
        assertEquals("等腰三角形", new Triangle().classify(100, 50, 100));
    }

    @Test
    @DisplayName("不等边三角形")
    public void testScalene() {
        assertEquals("不等边三角形", new Triangle().classify(3, 4, 5));
        assertEquals("不等边三角形", new Triangle().classify(5, 12, 13));
        assertEquals("不等边三角形", new Triangle().classify(50, 51, 52));
    }
}
