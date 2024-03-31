package com.example;

import org.example.MyMath;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MyMathTest {
    private final MyMath myMath = new MyMath();
    @Test
    public void calcSumThreeMemberArray(){
        int expResult = 0b01100100;
        assertEquals(expResult, myMath.calcSumOfArray(new int[]{4, 65, 31}));
    }
    @Test
    public void calcSumEmptyArray(){
        int expectedResult = 0b0;
        assertEquals(expectedResult, myMath.calcSumOfArray(new int[]{}));
    }
}
