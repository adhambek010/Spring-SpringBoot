package com.example;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class AssertTest {
    List<String> todos = Arrays.asList("AWS", "Azure", "DevOps");

    @Test
    public void test(){
        boolean test1 = todos.contains("AWS");
        boolean test2 = todos.contains("GCP");

        //assertEquals(true, test1);
        assertTrue(test1);
        assertFalse(test2);
        //assertArrayEquals(new int[] {1,2}, new int[] {1,2});

        assertEquals(3, todos.size());
    }
}
