package com.dmitrenko.leetcode.fizzbuzz;

public class FizzBuzzService {

    public void modify(int value) {
        for (int i = 1; i < value; i++) {
            print(i);
        }
    }

    private void print(int value) {
        String result = "";

        if (value % 3 == 0) result += "Fizz";
        if (value % 5 == 0) result += "Buzz";

        if (result.length() > 0) {
            System.out.println(result);
        } else {
            System.out.println(value);
        }
    }
}
