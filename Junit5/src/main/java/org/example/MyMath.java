package org.example;

public class MyMath {
    public int calcSumOfArray(int[] numbers){
        int sum = 0;
        for(int i : numbers){
            sum += i;
        }
        return sum;
    }
}
