package com.review.junit.Juint_Demo.components;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MyMathTest {

    private final MyMath myMath = new MyMath();

    @Test
    public void calculateSumTest_1() {
        // Absence of failure is success

        int result = myMath.calculateSum(new int[]{1, 2, 3, 4, 5});
        assertEquals(15, result);
        System.out.println("Sum is "+ result);
    }

    @Test
    public void calculateSumTest_2() {
        int result = myMath.calculateSum(new int[]{});
        assertEquals(0, result);
        System.out.println("The result is " + result);

    }

}
