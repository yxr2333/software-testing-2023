package com.ctgu.icecream.testing;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Created By IntelliJ IDEA
 *
 * @author IceCreamQAQ
 * @datetime 2023/4/4 星期二
 * Happy Every Coding Time~
 */
@DisplayName("针对LinkedList的测试")

public class LinkedListTest2 {

    private MyLinkedList<String> list;

    @BeforeEach
    void setUp() {
        list = new MyLinkedList<>();
    }

    @Test
    @DisplayName("测试输入参数为 null")
    void testRemoveNull() {
        assertFalse(list.remove(null));
    }

    @Test
    @DisplayName("测试输入参数为链表中已存在的元素")
    void testRemoveExistingElement() {
        list.add("A");
        assertTrue(list.remove("A"));
        assertFalse(list.contains("A"));
    }

    @Test
    @DisplayName("测试输入参数为链表中不存在的元素")
    void testRemoveNonExistingElement() {
        list.add("A");
        assertFalse(list.remove("B"));
        assertTrue(list.contains("A"));
    }
}
