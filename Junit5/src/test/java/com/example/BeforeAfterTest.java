package com.example;

import org.junit.jupiter.api.*;

public class BeforeAfterTest {

    @BeforeAll
    public static void beforeAll() {
        System.out.println("Before all");
    }

    @AfterAll
    public static void afterAll() {
        System.out.println("After all");
    }

    @AfterEach
    public void afterEach() {
        System.out.println("After Each");
    }

    @Test
    public void test1() {
        System.out.println("Test1");
    }

    @Test
    public void test3() {
        System.out.println("Test3");
    }

    @Test
    public void test2() {
        System.out.println("Test2");
    }

    @BeforeEach
    public void beforeEach() {
        System.out.println("Before Each");
    }
}
