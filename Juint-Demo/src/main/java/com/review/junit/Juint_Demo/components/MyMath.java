package com.review.junit.Juint_Demo.components;


public class MyMath {
    public int calculateSum(int[] numbers) {
        int sum = 0;
        for (int num : numbers) {
            sum += num;
        }
        return sum;

    }
}
