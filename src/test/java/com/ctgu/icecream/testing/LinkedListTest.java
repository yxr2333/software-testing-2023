package com.ctgu.icecream.testing;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
/**
 * Created By IntelliJ IDEA
 *
 * @author IceCreamQAQ
 * @datetime 2023/4/4 星期二
 * Happy Every Coding Time~
 */
@DisplayName("针对LinkedList的测试")
public class LinkedListTest {
    private MyLinkedList<String> customLinkedList;

    @BeforeEach
    void setUp() {
        customLinkedList = new MyLinkedList<>();
    }

    @Test
    @DisplayName("当列表中包含`null`元素时，尝试移除`null`元素")
    void testRemove_nullElementPresent() {
        customLinkedList.add("1");
        customLinkedList.add(null);
        customLinkedList.add("2");

        assertTrue(customLinkedList.remove(null));
        assertEquals(2, customLinkedList.size());
        assertFalse(customLinkedList.contains(null));
    }

    @Test
    @DisplayName("当列表中不包含`null`元素时，尝试移除`null`元素")
    void testRemove_nullElementNotPresent() {
        customLinkedList.add("1");
        customLinkedList.add("2");
        customLinkedList.add("3");

        assertFalse(customLinkedList.remove(null));
        assertEquals(3, customLinkedList.size());
    }

    @Test
    @DisplayName("当列表中包含非`null`元素时，尝试移除非`null`元素")
    void testRemove_nonNullElementPresent() {
        customLinkedList.add("1");
        customLinkedList.add("2");
        customLinkedList.add("3");

        assertTrue(customLinkedList.remove("2"));
        assertEquals(2, customLinkedList.size());
        assertFalse(customLinkedList.contains(2));
    }

    @Test
    @DisplayName("当列表中不包含非`null`元素时，尝试移除非`null`元素")
    void testRemove_nonNullElementNotPresent() {
        customLinkedList.add("1");
        customLinkedList.add("2");
        customLinkedList.add("3");
        assertFalse(customLinkedList.remove("4"));
        assertEquals(3, customLinkedList.size());
    }

    @Test
    @DisplayName("当列表为空时，尝试移除元素")
    void testRemove_fromEmptyList() {
        assertFalse(customLinkedList.remove("1"));
        assertEquals(0, customLinkedList.size());
    }
}
